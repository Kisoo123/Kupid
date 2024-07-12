package com.kupid.manager.answer.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.commit;
import static com.kupid.common.JDBCTemplate.getConnection;
import static com.kupid.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kupid.manager.answer.model.dao.AnswerDAO;
import com.kupid.manager.answer.model.dto.Answer;
import com.kupid.manager.notice.model.dto.Notice;

public class AnswerService {

	AnswerDAO dao=new AnswerDAO();
	
	public int insertAnswer(int no,String title,String content) {
		Connection conn=getConnection();
		int result=dao.insertAnswer(conn,no,title,content);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public Answer selectAnswerByNo(int no) {
		Connection conn=getConnection();
		Answer n=dao.selectAnswerByNo(conn,no);
		close(conn);
		return n;
	}
	
	public int updateAnswer(int no,String title,String content) {
		Connection conn=getConnection();
		int result=dao.updateAnswer(conn,no,title,content);
		System.out.println(result);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}
