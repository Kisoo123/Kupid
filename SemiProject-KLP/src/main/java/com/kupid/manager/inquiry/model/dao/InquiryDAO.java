package com.kupid.manager.inquiry.model.dao;

import static com.kupid.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kupid.manager.inquiry.model.dto.Inquiry;
import com.kupid.manager.notice.model.dto.Notice;

public class InquiryDAO {
	
Properties sql=new Properties();
	
	{
		String path=InquiryDAO.class.getResource("/sql/sql_inquiry.properties").getPath();
		try(FileReader fr=new FileReader(path)){
			sql.load(fr);
			System.out.println("test");
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	
	

	public List<Inquiry> selectInquiryAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Inquiry> inq=new ArrayList<Inquiry>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectInquiryAll"));
			pstmt.setInt(1,(cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				inq.add(getInquiry(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return inq;
	}
	
	public int selectInquiryAllCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectInquiryAllCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public Inquiry selectInquiryByNo(Connection conn,int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Inquiry inq=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectInquiryByNo"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) inq=getInquiry(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return inq;
	}
	
	public static Inquiry getInquiry(ResultSet rs) throws SQLException{
		return Inquiry.builder()
				.inqNo(rs.getInt("inq_no"))
				.inqMember(rs.getInt("inq_member"))
				.inqTitle(rs.getString("inq_title"))
				.inqContent(rs.getString("inq_content"))
				.inqDate(rs.getDate("inq_date"))
				.writer(rs.getString("member_id"))
				.answer(rs.getString("ans_title")==null?true:false)
				.inqFile(rs.getString("inq_file"))
				.build();
	}
}
