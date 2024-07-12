package com.kupid.calender.model.dao;

import static com.kupid.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kupid.calender.model.dto.Calendar;
import com.kupid.feed.model.dao.FeedDao;

public class CalendarDao {
	
	
	private Properties sql = new Properties();
	{
		String path=FeedDao.class.getResource("/sql/sql_calendar.properties").getPath();
		try(FileReader fr=new FileReader(path)) {
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}

	}
	public List<Calendar> getEventsByGroupNo(Connection conn,int groupNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Calendar> result = new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("getEventsByGroupNo"));
			pstmt.setInt(1,groupNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Calendar c = getCalendar(rs);
				result.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public static Calendar getCalendar(ResultSet rs) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startString = sdf.format(rs.getDate("sc_start"));
		String endString = sdf.format(rs.getDate("sc_end"));
		return Calendar.builder()
				.scId(rs.getInt("group_no"))
				.scTitle(rs.getString("sc_title"))
				.scStart(startString)
				.scEnd(endString)
				.field(rs.getString("field"))
				.groupNo(rs.getInt("GROUP_no"))
				.build();
	}
	
}
