package com.kupid.group.model.dao;

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

import com.kupid.group.model.dto.GroupDto;

public class GroupDao {
	private Properties sql = new Properties();
	{
		String path = GroupDao.class.getResource("/sql/group/group.properties").getPath();

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
			pstmt = conn.prepareStatement(sql.getProperty("selectMember"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				g.add(groupBuilder(rs));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
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
				.groupDebutday(rs.getDate("groupt_debutday"))
				.groupCompany(rs.getString("group_company"))
				.groupImg(rs.getString("group_img"))
				.build();
	}
}
