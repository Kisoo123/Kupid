package com.kupid.manager.answer.model.dao;

import static com.kupid.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kupid.manager.answer.model.dto.Answer;
import com.kupid.manager.notice.model.dto.Notice;

public class AnswerDAO {
	
	Properties sql=new Properties();
	
	{
		String path=AnswerDAO.class.getResource("/sql/sql_answer.properties").getPath();
		try(FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int insertAnswer(Connection conn,int no,String title,String content) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertAnswer"));
			pstmt.setInt(1, no);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public Answer selectAnswerByNo(Connection conn,int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Answer ans=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectAnswerByNo"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) ans=getAnswer(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return ans;
	}
	
	public int updateAnswer(Connection conn,int no,String title,String content) {
		PreparedStatement pstmt=null;
		int result=0;
		System.out.println(no);
		System.out.println(title);
		System.out.println(content);
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateAnswer"));
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public static Answer getAnswer(ResultSet rs) throws SQLException{
		return Answer.builder()
				.answerNO(rs.getInt("ans_no"))
				.inqNo(rs.getInt("inq_no"))
				.answerTitle(rs.getString("ans_title"))
				.answerContent(rs.getString("ans_content"))
				.answerDate(rs.getDate("ans_date"))
				.build();
	}
	
	
}
