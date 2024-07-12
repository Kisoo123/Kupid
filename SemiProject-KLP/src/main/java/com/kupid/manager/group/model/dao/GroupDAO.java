package com.kupid.manager.group.model.dao;

import static com.kupid.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kupid.group.model.dto.GroupDto;
import com.kupid.manager.notice.model.dao.NoticeDAO;
import com.kupid.manager.notice.model.dto.Notice;
import com.kupid.member.model.dto.MemberDto;

public class GroupDAO {

Properties sql=new Properties();
	
	{
		String path=NoticeDAO.class.getResource("/sql/sql_group.properties").getPath();
		try(FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<GroupDto> selectGroupAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<GroupDto> g=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectGroupAll"));
			pstmt.setInt(1,(cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				g.add(getGroup(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return g;
	}
	
	public int selectGroupAllCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectGroupAllCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public GroupDto selectGroupByNo(Connection conn,int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		GroupDto g=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectGroupByNo"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) g=getGroup(rs); 	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return g;
	}
	
	
	public int deleteGroup(Connection conn,int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteGroup"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int insertGroup(Connection conn,GroupDto g) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertGroup"));
			pstmt.setString(1,g.getGroupName() );
			pstmt.setInt(2,g.getMemberCount() );
			pstmt.setDate(3,g.getGroupDebutday() );
			pstmt.setString(4,g.getGroupCompany() );
			pstmt.setString(5,g.getGroupImg() );
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int updateGroup(Connection conn,GroupDto g) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateGroup"));
			pstmt.setString(1,g.getGroupName() );
			pstmt.setInt(2,g.getMemberCount() );
			pstmt.setString(3,g.getGroupCompany() );
			pstmt.setString(4,g.getGroupImg());
			pstmt.setInt(5,g.getGroupNo() );
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public List<GroupDto> searchGroup(Connection conn,String keyword, int cPage,int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<GroupDto> group=new ArrayList<>();
		try { 
			pstmt=conn.prepareStatement(sql.getProperty("selectSearchGroup"));
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				group.add(getGroup(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return group;
	}
	
	public int searchGroupCount(Connection conn,String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=this.sql.getProperty("searchGroupCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public static GroupDto getGroup(ResultSet rs) throws SQLException{
		return GroupDto.builder()
				.groupNo(rs.getInt("group_no"))
				.groupName(rs.getString("group_name"))
				.memberCount(rs.getInt("member_count"))
				.groupDebutday(rs.getDate("group_debutday"))
				.groupCompany(rs.getString("group_company"))
				.groupImg(rs.getString("group_img"))
				.build();
	}
	
	
}
