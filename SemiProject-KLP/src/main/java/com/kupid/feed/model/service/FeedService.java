package com.kupid.feed.model.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.commit;
import static com.kupid.common.JDBCTemplate.getConnection;
import static com.kupid.common.JDBCTemplate.rollback;

import java.io.File;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import com.kupid.feed.model.dao.FeedDao;
import com.kupid.feed.model.dto.Feed;
import com.kupid.feed.model.dto.Reply;
import com.kupid.member.model.dto.MemberDto;
public class FeedService {
	private FeedDao dao = new FeedDao();

	
	public int deleteFeed(int feedNo) {
		Connection conn = getConnection();
		int result = dao.deleteFeed(conn,feedNo);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public List<MemberDto> selectFeedComment(int feedNo) {
		Connection conn = getConnection();
		List<MemberDto> fc = dao.selectFeedComment(conn,feedNo);
		close(conn);
		return fc;
	}
	
	public int feedUpdate(int feedNo,String feedContent) {
		Connection conn = getConnection();
		int result = dao.feedUpdate(conn,feedNo,feedContent);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public int checkLikes(int feedNo,int loginMemberNo) {
		Connection conn = getConnection();
		int result = dao.checkLikes(conn,feedNo,loginMemberNo);
		
		return result;
	}
	
	public int insertFeedReport(String category, String content, int reportMemberNo, int reportedfeedNo) {
		Connection conn = getConnection();
		int reportedMemberNo = dao.selectMemberByFeedNo(conn, reportedfeedNo);
		int result = dao.insertFeedReport(conn,category,content,reportMemberNo,reportedMemberNo);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
		
	}
	
	public int countFeedComment(int feedNo) {
		Connection conn = getConnection();
		int result = dao.countFeedComment(conn,feedNo);
		
		return result;
	}
	
	public int deleteFeedComment(int replyNumber) {
		Connection conn = getConnection();
		int result = dao.deleteFeedComment(conn,replyNumber);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
		
	}
	
	public int updateFeedComment(int replyNumber, String replyContent) {
		Connection conn = getConnection();
		int result = dao.updateFeedComment(conn,replyNumber,replyContent);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	

	public int switchingLikes(int memberNo,int feedNo) {
		Connection conn = getConnection();
		int countClick = dao.selectLikes(conn, memberNo, feedNo); 
		System.out.println(countClick);
		int result = 0;
		int likes = 0;
		int commitCheck = 0;
		if(countClick==0) {
			commitCheck=dao.insertLikes(conn,memberNo,feedNo);
			 likes = dao.selectFeedLikes(conn,feedNo);
			 likes +=1;
			 result= dao.updateFeedLikes(conn,likes,feedNo);
			 
		}else {
			commitCheck=dao.deleteLikes(conn,memberNo,feedNo);
			 likes = dao.selectFeedLikes(conn,feedNo);
			 likes -=1;
			 result= dao.updateFeedLikes(conn,likes,feedNo);
		}
		if(commitCheck>0) commit(conn);
		else rollback(conn);
		System.out.println(result);
		 return likes;
		
	}
	
	public int insertProcess(Feed f, List<String> filePath, int groupNo) {
		Connection conn = getConnection();
		int seq = dao.selectSeqFeed(conn);
		int memberNo = dao.selectMemberNoById(conn,f.getFeedWriterName());
		int result = dao.insertFeed(conn,f,seq,memberNo,groupNo);
		Iterator<String>iter = filePath.iterator();
		while(iter.hasNext()) {
			String nextPath = iter.next();
			result = dao.insertFeedFile(conn,nextPath,seq);
			
			if(result==0) {
				File delFile=new File(nextPath);
				if(delFile.exists()) delFile.delete();
			}
		}
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
//	public int insertFeed(Feed f,int seq){
//		Connection conn = getConnection();
//		int result = dao.insertFeed(conn,f,seq);
//		close(conn);
//		return result;
//	}
//	
	public int insertFeedComment(int loginMember,String comment,int feedNo) {
		Connection conn = getConnection();
		int result = dao.insertFeedComment(conn,loginMember,comment,feedNo);
		close(conn);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	public List<Feed> selectFeedAll(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Feed> result=dao.selectFeedAll(conn,cPage,numPerpage);
		close(conn);
		return result;
	}
	
	public int selectFeedCount() {
		Connection conn = getConnection();
		int result = dao.selectFeedCount(conn);
		close(conn);
		return result;
		
	}
	
//	public int switchingLikes() {
//		Connection conn = getConnection();
//		int result = dao.switchingLikes(conn);
//		close(conn);
//		return result;
//	}
	
}