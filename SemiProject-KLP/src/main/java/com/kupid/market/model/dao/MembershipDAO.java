package com.kupid.market.model.dao;

import static com.kupid.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kupid.manager.faq.model.dto.Faq;

public class MembershipDAO {

	Properties sql=new Properties();
	
	{
		String path=MembershipDAO.class.getResource("/sql/sql_membership.properties").getPath();
		try(FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}

	public int insertMembership(Connection conn,int no,String serialkey) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertMembership"));
			pstmt.setString(1, serialkey);
			pstmt.setInt(2, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
}
