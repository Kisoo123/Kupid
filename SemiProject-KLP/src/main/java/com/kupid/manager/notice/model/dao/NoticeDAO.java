package com.kupid.manager.notice.model.dao;

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

import com.kupid.manager.notice.model.dto.Notice;



public class NoticeDAO {
	
	Properties sql=new Properties();
	
	{
		String path=NoticeDAO.class.getResource("/sql/sql_notice.properties").getPath();
		try(FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

	public List<Notice> selectNoticeAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Notice> report=new ArrayList<Notice>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeAll"));
			pstmt.setInt(1,(cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				report.add(getNotice(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return report;
	}
	
	public int selectNoticeAllCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeAllCount"));
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
	
	public Notice selectNoticeByNo(Connection conn,int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Notice n=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeByNo"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) n=getNotice(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return n;
	}
	
	public int noticeUpdate(Connection conn,Notice n) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("noticeUpdate"));
			pstmt.setString(1, n.getNoticeCategory());
			pstmt.setString(2, n.getNoticeTitle());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setInt(4, n.getNoticeNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
		
			close(pstmt);
		}
		return result;
	}
	
	public int noticeInsert(Connection conn,Notice n) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("noticeInsert"));
			pstmt.setString(1, n.getNoticeCategory());
			pstmt.setString(2, n.getNoticeTitle());
			pstmt.setString(3, n.getNoticeContent());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int noticeDelete(Connection conn,int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("noticeDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public List<Notice> searchNotice(Connection conn,String type,String keyword, int cPage,int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Notice> notice=new ArrayList<>();
//		System.out.println(type);
//		System.out.println(keyword);
		try { 
			pstmt=conn.prepareStatement(sql.getProperty("selectSearchNotice"));
			pstmt.setString(1,type);
			pstmt.setString(2,"%"+keyword+"%");
			pstmt.setInt(3, (cPage-1)*numPerpage+1);
			pstmt.setInt(4, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				notice.add(NoticeDAO.getNotice(rs));
			}
			System.out.println(notice);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return notice;
	}
	
	public int searchNoticeCount(Connection conn,String type,String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=this.sql.getProperty("searchNoticeCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,type);
			pstmt.setString(2,"%"+keyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	public static Notice getNotice(ResultSet rs) throws SQLException{
		return Notice.builder()
				.noticeNo(rs.getInt("notice_no"))
				.noticeCategory(rs.getString("notice_category"))
				.noticeTitle(rs.getString("notice_title"))
				.noticeContent(rs.getString("notice_content"))
				.noticeDate(rs.getDate("notice_date"))
				.build();
	}
	
}
