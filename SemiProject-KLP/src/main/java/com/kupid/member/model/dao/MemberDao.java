package com.kupid.member.model.dao;

import static com.kupid.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kupid.member.model.dto.MemberDto;
import com.kupid.mypage.dao.MyPageDao;
public class MemberDao {
	private Properties sql = new Properties();
	{
		String path = MemberDao.class.getResource("/sql/member/member.properties").getPath();

		try (FileReader fr = new FileReader(path)){
			sql.load(fr);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public MemberDto selectMemberById(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDto m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberById"));
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) m=memberBuilder(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	public int selectMemberByIdNameEmailBirth(Connection conn, String id, String name,String email, Date birth) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberByIdNameEmailBirth"));
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setDate(4, birth);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("result");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	public MemberDto findId(Connection conn, String name, String email, Date birth) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDto m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("findId"));
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setDate(3, birth );
			rs=pstmt.executeQuery();
			if(rs.next()) m=memberBuilder(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	public MemberDto findPw(Connection conn,String id, String name, String email, Date birth) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDto m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("findId"));
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setDate(4, birth );
			rs=pstmt.executeQuery();
			if(rs.next()) m=memberBuilder(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	public int selectMemberByEmail(Connection conn, String name, String email, Date birth) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result =0;
		try{
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberByEmail"));
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setDate(3, birth );
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("result");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result; 
	}
	//닉네임 중복 조회: count(*)
	public int checkNickname(Connection conn, String nickname) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result =0;
		try{
			pstmt = conn.prepareStatement(sql.getProperty("checkNickname"));
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("result");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result; 
	}
	//아이디 중복 조회
	public int checkId(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result =0;
		try{
			pstmt = conn.prepareStatement(sql.getProperty("checkId"));
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("result");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result; 
	}
	public int insertMember(Connection conn, MemberDto m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result =0;
		try{
			//(seq_member_no.nextval,'abcde','1234','아무개','M','01012345678',주소, 상세주소,이메일,생일,'닉네임','소개(디폴트',기본프로필DEFAULT, 회원등급일반DEFAULT, 등록일DEFAULT, 아티스트그룹번호 null);
			pstmt = conn.prepareStatement(sql.getProperty("insertMember"));
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getAddressDetail());
			pstmt.setString(8, m.getEmail());
			pstmt.setDate(9, m.getBirth());
			pstmt.setString(10, m.getNickname());
			pstmt.setString(11, m.getKakaoId());
			pstmt.setString(12, m.getKakaoPw());

			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result; 
	}
	@SuppressWarnings("static-access")
	public List<MemberDto> selectMemberFavorite(Connection conn) {
		PreparedStatement pstmt = null;
		List<MemberDto> m = new ArrayList<>();
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberFavorite"));
//			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				m.add((MemberDto)new MyPageDao().favoriteGroupBuilder(rs));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return m; 
	}
	public List<MemberDto> selectGroupSubscribe(Connection conn) {
		PreparedStatement pstmt = null;
		List<MemberDto> m = new ArrayList<>();
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(sql.getProperty("selectGroupSubscribe"));
//			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				m.add(GroupSubscribesBuilder(rs));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return m; 
	}
	public MemberDto checkkakaoId(Connection conn, String userid) {
		PreparedStatement pstmt = null;
		MemberDto m = null;;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(sql.getProperty("checkkakaoId"));
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m=memberBuilder(rs);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	public static MemberDto memberBuilder(ResultSet rs) throws SQLException {
//		String email,phone,addressDetail,address,memberPw;
//					try {
//						email=AESEncryptor.decryptData(rs.getString("email"));
//					}catch(Exception e) {
//						email=rs.getString("email");
//					}
//					try {
//						email=AESEncryptor.decryptData(rs.getString("address"));
//					}catch(Exception e) {
//						email=rs.getString("address");
//					}
//					try {
//						memberPw=AESEncryptor.decryptData(rs.getString("member_pw"));
//					}catch(Exception e) {
//						memberPw=rs.getString("MEMBER_PW");
//					}
//					
//					try {
//						phone=AESEncryptor.decryptData(rs.getString("phone"));
//					}catch(Exception e) {
//						phone=rs.getString("phone");
//					}
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
//						.profileImgRenamed(rs.getString("profile_img_renamed"))
						.memberGrade(rs.getString("member_grade"))
						.enrollDate(rs.getDate("enroll_date"))
						.build();
	}
	public static MemberDto GroupSubscribesBuilder(ResultSet rs) throws SQLException {
		return MemberDto.builder()
					.groupNo(rs.getInt("group_no"))
					.groupName(rs.getString("group_name"))
					.groupImg(rs.getString("group_img"))
					.memberNo(rs.getInt("member_no"))
					.subscribeNo(rs.getInt("subscribe_no"))
					.build();
	}
}