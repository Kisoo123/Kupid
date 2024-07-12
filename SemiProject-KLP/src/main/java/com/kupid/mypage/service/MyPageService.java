package com.kupid.mypage.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.commit;
import static com.kupid.common.JDBCTemplate.getConnection;
import static com.kupid.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kupid.member.model.dto.MemberDto;
import com.kupid.mypage.dao.MyPageDao;

public class MyPageService {
	private MyPageDao dao = new MyPageDao();
	public MemberDto selectMember(String id) {
		Connection conn = getConnection();
		MemberDto m = dao.selectMember(conn, id);
		close(conn);
		return m;
	}
	public List<MemberDto> selectMemberForProfile(String id) {
		Connection conn = getConnection();
		List<MemberDto> result = dao.selectMemberForProfile(conn, id);
		close(conn);
		return result;
	}
	public List<MemberDto> selectMemberFavorite(int no) {
		Connection conn = getConnection();
		List<MemberDto> result = dao.selectMemberFavorite(conn, no);
		close(conn);
		return result;
	}
	public int updateProfile(int no, String nickname, String introduce) {
		Connection conn = getConnection();
		int result = dao.updateProfile(conn, no, nickname, introduce);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int updateProfileImg(int no, String img) {
		Connection conn = getConnection();
		int result = dao.updateProfileImg(conn, no, img);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int deleteProfileImg(int no) {
		Connection conn = getConnection();
		int result = dao.deleteProfileImg(conn, no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int updateInfo(String id, String name, String phone, String email, String address,
			String addressDetail) {
		Connection conn = getConnection();
		int result = dao.updateInfo(conn, id, name, phone, email, address, addressDetail);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int updateInfoAll(String id, String name, String newpw, String phone, String email, String address,
			String addressDetail) {
		Connection conn = getConnection();
		int result = dao.updateInfoAll(conn, id, name, newpw, phone, email, address, addressDetail);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}
