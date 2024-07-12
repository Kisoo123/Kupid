package com.kupid.manager.artist.dao;

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

import com.kupid.member.model.dto.MemberDto;

public class ArtistDAO {
Properties sql=new Properties();
	
	{
		String path=ArtistDAO.class.getResource("/sql/sql_artist.properties").getPath();
		try(FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberDto> selectArtistAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDto> mg=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectArtistAll"));
			pstmt.setString(1,"아티스트");
			pstmt.setInt(2,(cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				mg.add(getArtist(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return mg;
	}
	
	public int selectArtistAllCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectArtistAllCount"));
			pstmt.setString(1,"아티스트");
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
	
	public MemberDto selectArtistByNo(Connection conn,int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDto m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectArtistByNo"));
			pstmt.setInt(1,no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getArtist(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public List<MemberDto> searchArtist(Connection conn,String type,String keyword, int cPage,int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDto> artist=new ArrayList<>();
		try {
			String sql=this.sql.getProperty("selectSearchArtist");
			sql=sql.replace("#COL", type);//컬럼값은 ?로 받을 수 없기때문에 문자열로 받아서 replace로 문자자체를 바꿈 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setString(2,"아티스트");
			pstmt.setInt(3, (cPage-1)*numPerpage+1);
			pstmt.setInt(4, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				artist.add(getArtist(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return artist;
	}
	
	public int searchArtistCount(Connection conn,String type,String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=this.sql.getProperty("searchArtistCount");
		sql=sql.replace("#COL", type);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%" );
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int insertArtist(Connection conn,MemberDto m) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertArtist"));
			pstmt.setString(1,m.getMemberId());
			pstmt.setString(2,m.getMemberPw());
			pstmt.setString(3,m.getMemberName());
			pstmt.setString(4,m.getGender());
			pstmt.setString(5,m.getPhone());
			pstmt.setString(6,m.getAddress());
			pstmt.setString(7,m.getAddressDetail());
			pstmt.setString(8,m.getEmail());
			pstmt.setDate(9,m.getBirth());
			pstmt.setString(10,m.getNickname());
			pstmt.setString(11,"아티스트");
			pstmt.setInt(12,m.getGroupNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	

	
	public MemberDto getArtist(ResultSet rs) throws SQLException {
		return MemberDto.builder()
						.memberNo(rs.getInt("member_no"))
						.memberId(rs.getString("member_id"))
						.memberPw(rs.getString("member_pw"))
						.memberName(rs.getString("member_name"))
						.gender(rs.getString("gender"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.addressDetail(rs.getString("address_detail"))
						.email(rs.getString("email"))
						.birth(rs.getDate("birth"))
						.introduce(rs.getString("introduce"))
						.nickname(rs.getString("nickname"))
						.profileImgOriname(rs.getString("profile_img_oriname"))
						//.profileImgRenamed(rs.getString("profile_img_renamed"))
						.memberGrade(rs.getString("member_grade"))
						.enrollDate(rs.getDate("enroll_date"))
						.groupNo(rs.getInt("group_no"))
						.groupName(rs.getString("group_name"))
						.memberCount(rs.getInt("member_count"))
						.groupDebutday(rs.getDate("group_debutday"))
						.groupCompany(rs.getString("group_company"))
						.groupImg(rs.getString("group_img"))
						.build();
	}
}
