package com.kupid.manager.artist.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.commit;
import static com.kupid.common.JDBCTemplate.getConnection;
import static com.kupid.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kupid.manager.artist.dao.ArtistDAO;
import com.kupid.member.model.dto.MemberDto;

public class ArtistService {

	ArtistDAO dao=new ArtistDAO();
	
	public List<MemberDto> selectArtistAll(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<MemberDto> member=dao.selectArtistAll(conn,cPage,numPerpage);
		close(conn);
		return member;
	}
	
	public int selectArtistAllCount() {
		Connection conn=getConnection();
		int result=dao.selectArtistAllCount(conn);
		close(conn);
		return result;
	}
	
	public MemberDto selectArtistByNo(int no) {
		Connection conn=getConnection();
		MemberDto member=dao.selectArtistByNo(conn,no);
		close(conn);
		return member;
	}
	
	
	public List<MemberDto> searchArtist(String type,String keyword,int cPage,int numPerpage){
		Connection conn=getConnection();
		List<MemberDto> members=dao.searchArtist(conn,type,keyword,cPage,numPerpage);
		close(conn);
		return members;
	}
	
	public int searchArtistCount(String type,String keyword) {
		Connection conn=getConnection();
		int count=dao.searchArtistCount(conn,type,keyword);
		close(conn);
		return count;
	}
	
	public int insertArtist(MemberDto m) {
		Connection conn=getConnection();
		int result=dao.insertArtist(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
		
	}
	
	
}
