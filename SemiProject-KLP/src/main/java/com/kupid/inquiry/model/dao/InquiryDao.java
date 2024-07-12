package com.kupid.inquiry.model.dao;

import static com.kupid.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kupid.manager.inquiry.model.dao.InquiryDAO;

public class InquiryDao {
	private Properties sql= new Properties();
	{
		String path=InquiryDao.class.getResource("/sql/inquiry/sql_inquiry.properties").getPath();
		
		try(FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int insertInquiry(Connection conn,String inqTitle,String inqContent,String inqFile,int inq_Member) {
		PreparedStatement psmt=null;
		int result=0;
		try {
			psmt=conn.prepareStatement(sql.getProperty("insertInquiry"));
			psmt.setString(1, inqTitle);
			psmt.setString(2, inqContent);
			psmt.setString(3, inqFile);
			psmt.setInt(4, inq_Member);
		
			result=psmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
		
	}
	}
