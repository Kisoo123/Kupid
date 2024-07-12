package com.kupid.inquiry.model.service;
import java.sql.Connection;
import java.sql.Date;

import com.kupid.inquiry.model.dao.InquiryDao;
import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.getConnection;

public class InquiryService {
	InquiryDao dao=new InquiryDao();

    public int insertInquiry(String inqTitle,String inqContent,String inqFile,int inq_Member) {
    	Connection conn=getConnection();
    	int result=dao.insertInquiry(conn,inqTitle, inqContent, inqFile, inq_Member);
    	close(conn);
    	return result; 
    }
}
