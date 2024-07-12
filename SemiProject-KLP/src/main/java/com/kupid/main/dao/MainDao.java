package com.kupid.main.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kupid.group.model.dto.GroupDto;
import static com.kupid.common.JDBCTemplate.close;

public class MainDao {
	private Properties sql = new Properties();
	{
		String path = MainDao.class.getResource("/sql/sql_main.properties").getPath();

		try (FileReader fr = new FileReader(path)){
			sql.load(fr);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	public List<GroupDto> selectAllGroup(Connection conn) {
		PreparedStatement pstmt = null;
		List<GroupDto> g = new ArrayList<>();
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(sql.getProperty("selectAllGroup"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				g.add(groupBuilder(rs));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return g; 
	}
	
	
	
	public static GroupDto groupBuilder(ResultSet rs) throws SQLException{
		return GroupDto.builder()
				.groupNo(rs.getInt("group_no"))
				.groupName(rs.getString("group_name"))
				.memberCount(rs.getInt("member_count"))
				.groupDebutday(rs.getDate("group_debutday"))
				.groupCompany(rs.getString("group_company"))
				.groupImg(rs.getString("group_img"))
				.build();
	}
}
