package com.kupid.manager.notice.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.commit;
import static com.kupid.common.JDBCTemplate.getConnection;
import static com.kupid.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kupid.manager.notice.model.dao.NoticeDAO;
import com.kupid.manager.notice.model.dto.Notice;


public class NoticeService {
	
	NoticeDAO dao=new NoticeDAO();
	
	public List<Notice> selectNoticeAll(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Notice> report=dao.selectNoticeAll(conn,cPage,numPerpage);
		close(conn);
		return report;
	}
	
	public int selectNoticeAllCount() {
		Connection conn=getConnection();
		int result=dao.selectNoticeAllCount(conn);
		close(conn);
		return result;
	}
	
	public Notice selectNoticeByNo(int no) {
		Connection conn=getConnection();
		Notice n=dao.selectNoticeByNo(conn,no);
		close(conn);
		return n;		
	}
	
	public int noticeUpdate(Notice n) {
		Connection conn=getConnection();
		int result=dao.noticeUpdate(conn,n);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int noticeInsert(Notice n) {
		Connection conn=getConnection();
		int result=dao.noticeInsert(conn,n);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int noticeDelete(int no) {
		Connection conn=getConnection();
		int result=dao.noticeDelete(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Notice> searchNotice(String type,String keyword,int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Notice> notices=dao.searchNotice(conn,type,keyword,cPage,numPerpage);
		close(conn);
		return notices;
	}
	
	public int searchNoticeCount(String type,String keyword) {
		Connection conn=getConnection();
		int count=dao.searchNoticeCount(conn,type,keyword);
		close(conn);
		return count;
	}
	
	
	
}
