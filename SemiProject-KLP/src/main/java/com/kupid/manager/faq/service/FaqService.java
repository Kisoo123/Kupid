package com.kupid.manager.faq.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.commit;
import static com.kupid.common.JDBCTemplate.getConnection;
import static com.kupid.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kupid.manager.faq.model.dao.FaqDAO;
import com.kupid.manager.faq.model.dto.Faq;
import com.kupid.manager.notice.model.dto.Notice;

public class FaqService {

	FaqDAO dao=new FaqDAO();
	
	public List<Faq> selectNoticeAll(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Faq> faq=dao.selectFaqAll(conn, cPage, numPerpage);
		close(conn);
		return faq;
	}
	
	public int selectNoticeAllCount() {
		Connection conn=getConnection();
		int result=dao.selectFaqAllCount(conn);
		close(conn);
		return result;
	}
	
	public Faq selectFaqByNo(int no) {
		Connection conn=getConnection();
		Faq f=dao.selectFaqByNo(conn,no);
		close(conn);
		return f;		
	}
	
	public int faqUpdate(Faq f) {
		Connection conn=getConnection();
		int result=dao.faqUpdate(conn,f);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int faqInsert(Faq f) {
		Connection conn=getConnection();
		int result=dao.faqInsert(conn,f);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int faqDelete(int no) {
		Connection conn=getConnection();
		int result=dao.faqDelete(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Faq> searchFaq(String type,String keyword,int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Faq> faq=dao.searchFaq(conn,type,keyword,cPage,numPerpage);
		close(conn);
		return faq;
	}
	
	public int searchFaqCount(String type,String keyword) {
		Connection conn=getConnection();
		int count=dao.searchFaqCount(conn,type,keyword);
		close(conn);
		return count;
	}
	
}
