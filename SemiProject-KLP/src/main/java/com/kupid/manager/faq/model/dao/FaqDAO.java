package com.kupid.manager.faq.model.dao;

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

import com.kupid.manager.faq.model.dto.Faq;
import com.kupid.manager.notice.model.dao.NoticeDAO;
import com.kupid.manager.notice.model.dto.Notice;

public class FaqDAO {

	Properties sql=new Properties();
	
	{
		String path=FaqDAO.class.getResource("/sql/sql_faq.properties").getPath();
		try(FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	
	

	public List<Faq> selectFaqAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Faq> faq=new ArrayList<Faq>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectFaqAll"));
			pstmt.setInt(1,(cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				faq.add(getFaq(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return faq;
	}
	
	public int selectFaqAllCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectFaqAllCount"));
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
	
	public Faq selectFaqByNo(Connection conn,int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Faq f=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectFaqByNo"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) f=getFaq(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return f;
	}
	
	public int faqUpdate(Connection conn,Faq f) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("faqUpdate"));
			pstmt.setString(1, f.getFaqCategory());
			pstmt.setString(2, f.getFaqTitle());
			pstmt.setString(3, f.getFaqContent());
			pstmt.setInt(4, f.getFaqNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
		
			close(pstmt);
		}
		return result;
	}
	
	public int faqInsert(Connection conn,Faq f) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("faqInsert"));
			pstmt.setString(1, f.getFaqCategory());
			pstmt.setString(2, f.getFaqTitle());
			pstmt.setString(3, f.getFaqContent());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int faqDelete(Connection conn,int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("faqDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public List<Faq> searchFaq(Connection conn,String type,String keyword, int cPage,int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Faq> faq=new ArrayList<>();
		try { 
			pstmt=conn.prepareStatement(sql.getProperty("selectSearchFaq"));
			pstmt.setString(1,type);
			pstmt.setString(2,"%"+keyword+"%");
			pstmt.setInt(3, (cPage-1)*numPerpage+1);
			pstmt.setInt(4, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				faq.add(getFaq(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return faq;
	}
	
	public int searchFaqCount(Connection conn,String type,String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=this.sql.getProperty("searchFaqCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,type);
			pstmt.setString(2,"%"+keyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public static Faq getFaq(ResultSet rs) throws SQLException{
		return Faq.builder()
				.faqNo(rs.getInt("faq_no"))
				.faqTitle(rs.getString("faq_title"))
				.faqContent(rs.getString("faq_content"))
				.faqCategory(rs.getString("faq_category"))
				.faqDate(rs.getDate("faq_date"))
				.build();
	}
	
}
