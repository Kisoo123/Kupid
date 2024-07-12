package com.kupid.manager.inquiry.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;


import com.kupid.manager.inquiry.model.dao.InquiryDAO;
import com.kupid.manager.inquiry.model.dto.Inquiry;

public class InquiryService {
	
	InquiryDAO dao=new InquiryDAO();

	public List<Inquiry> selectInquiryAll(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Inquiry> inq=dao.selectInquiryAll(conn, cPage, numPerpage);
		close(conn);
		return inq;
	}
	
	public int selectInquiryAllCount() {
		Connection conn=getConnection();
		int result=dao.selectInquiryAllCount(conn);
		close(conn);
		return result;
	}
	
	public Inquiry selectInquiryByNo(int no) {
		Connection conn=getConnection();
		Inquiry result=dao.selectInquiryByNo(conn, no);
		close(conn);
		return result;
	}
	
}
