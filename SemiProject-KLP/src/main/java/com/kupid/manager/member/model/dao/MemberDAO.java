 package com.kupid.manager.member.model.dao;

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

import com.kupid.manager.notice.model.dao.NoticeDAO;
import com.kupid.member.model.dto.MemberDto;

public class MemberDAO {
	
	Properties sql=new Properties();
	
	{
		String path=MemberDAO.class.getResource("/sql/sql_member.properties").getPath();
		try(FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	public List<MemberDto> selectMemberAll(Connection conn,int cPage,int numPerpage,String grade){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDto> mg=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberAll"));
			pstmt.setInt(1,(cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				mg.add(getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return mg;
	}
	
	public List<MemberDto> selectPenaltyAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDto> mg=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectPenaltyAll"));
			pstmt.setString(1,"탈퇴");
			pstmt.setString(2,"정지");
			pstmt.setInt(3,(cPage-1)*numPerpage+1);
			pstmt.setInt(4, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				mg.add(getPenalty(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return mg;
	}
	
	public int selectPenaltyAllCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectPenaltyAllCount"));
			pstmt.setString(1,"탈퇴");
			pstmt.setString(2,"정지");
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
	
	public int selectMemberAllCount(Connection conn,String grade) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberAllCount"));
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
	
	public MemberDto selectMemberByNo(Connection conn,int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDto m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberByNo"));
			pstmt.setInt(1,no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public int deleteMember(Connection conn,int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteMember"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<MemberDto> searchMember(Connection conn,String type,String keyword, int cPage,int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDto> members=new ArrayList<>();
		try {
			//String sql=this.sql.getProperty("selectSearchMember");
			//sql=sql.replace("#COL", type);//컬럼값은 ?로 받을 수 없기때문에 문자열로 받아서 replace로 문자자체를 바꿈 
			pstmt=conn.prepareStatement(sql.getProperty("selectSearchMember"));
			pstmt.setString(1,type);
			pstmt.setString(2,"%"+keyword+"%");
			pstmt.setInt(3, (cPage-1)*numPerpage+1);
			pstmt.setInt(4, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getMember(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return members;
	}
	
	public List<MemberDto> searchPenalty(Connection conn,String type,String keyword, int cPage,int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDto> members=new ArrayList<>();
		try {
			String sql=this.sql.getProperty("selectSearchPenalty");
			sql=sql.replace("#COL", type);//컬럼값은 ?로 받을 수 없기때문에 문자열로 받아서 replace로 문자자체를 바꿈 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"탈퇴");
			pstmt.setString(2,"정지");
			pstmt.setString(3,"%"+keyword+"%");
			pstmt.setInt(4, (cPage-1)*numPerpage+1);
			pstmt.setInt(5, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getPenalty(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return members;
	}
	
	
	public int searchMemberCount(Connection conn,String type,String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=this.sql.getProperty("searchMemberCount");
		sql=sql.replace("#COL", type);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, type.equals("userName")?"%"+keyword+"%":keyword );
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int searchPenaltyCount(Connection conn,String type,String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=this.sql.getProperty("searchPenaltyCount");
		sql=sql.replace("#COL", type);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"탈퇴");
			pstmt.setString(2,"정지");
			pstmt.setString(3,"%"+keyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<MemberDto> selectSubscribeByNo(Connection conn,int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDto> members=new ArrayList<>();
		try {
			String sql=this.sql.getProperty("selectSubscribeByNo");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,no);	
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getArtist(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return members;
	}
	
	public List<MemberDto> selectMembershipByNo(Connection conn,int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDto> members=new ArrayList<>();
		try {
			String sql=this.sql.getProperty("selectMembershipByNo");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,no);	
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getMembership(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return members;
	}
	
	public int memberCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("memberCount"));
			pstmt.setString(1, "회원");
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
	
	public int artistCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("artistCount"));
			pstmt.setString(1, "아티스트");
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
	
	public int membershipCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("membershipCount"));
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
	
	public MemberDto getMembership(ResultSet rs) throws SQLException {
		return MemberDto.builder()
				.serialkey(rs.getString("serialkey"))
				.startday(rs.getDate("startday"))
				.endday(rs.getDate("endday"))
				.groupName(rs.getString("group_name"))
				.build();
	
	}
	
	public MemberDto getMember(ResultSet rs) throws SQLException {
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
						//.penaltyDay(rs.getString("penalty_start_date")!=null?rs.getString("penalty_start_date"):"")
						.build();
	}
	
	public MemberDto getPenalty(ResultSet rs) throws SQLException {
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
						.penaltyDate(rs.getString("penalty_start_date")!=null?rs.getString("penalty_start_date"):"")
						.penaltyCate(rs.getString("penalty_category"))
						.build();
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
