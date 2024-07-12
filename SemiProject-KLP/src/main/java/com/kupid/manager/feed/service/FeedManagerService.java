package com.kupid.manager.feed.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.commit;
import static com.kupid.common.JDBCTemplate.getConnection;
import static com.kupid.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kupid.feed.model.dto.Feed;
import com.kupid.manager.feed.model.dao.FeedManagerDAO;
import com.kupid.member.model.dto.MemberDto;

public class FeedManagerService {

	FeedManagerDAO dao=new FeedManagerDAO();
	
	public List<Feed> selectFeedAll(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Feed> feed=dao.selectFeedAll(conn,cPage,numPerpage);
		close(conn);
		return feed;
	}
	
	
	public int selectFeedAllCount() {
		Connection conn=getConnection();
		int result=dao.selectFeedAllCount(conn);
		close(conn);
		return result;
	}
	
	public int deleteFeed(int no) {
		Connection conn=getConnection();
		int result=dao.deleteFeed(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Feed> searchFeed(String type,String keyword,int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Feed> feed=dao.searchFeed(conn,type,keyword,cPage,numPerpage);
		close(conn);
		return feed;
	}
	
	public int searchFeedCount(String type,String keyword) {
		Connection conn=getConnection();
		int count=dao.searchFeedCount(conn,type,keyword);
		close(conn);
		return count;
	}
	
	
	
	
	
	
	
	
	
	
}
