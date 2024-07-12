package com.kupid.manager.report.model.dao;

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

import com.kupid.manager.penalty.model.dto.Penalty;
import com.kupid.manager.report.model.dto.Report;
import com.kupid.member.model.dto.MemberDto;


public class ReportDAO {
	Properties sql=new Properties();
	
	{
		String path=ReportDAO.class.getResource("/sql/sql_report.properties").getPath();
		try(FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	public List<Report> selectReportAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Report> report=new ArrayList<Report>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectReportAll"));
			pstmt.setInt(1,(cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				report.add(getReport(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return report;
	}
	
	public int selectReportAllCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectReportAllCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
	
	public Report selectReportByNo(Connection conn,int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Report report=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectReportByNo"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				report=getReportView(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		System.out.println(report);
		return report;
	}
	
	public int insertPenalty(Connection conn,Penalty p) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertPenalty"));
			pstmt.setInt(1, p.getReportNO());
			pstmt.setInt(2, p.getMemberNo());
			pstmt.setString(3, p.getPenaltyCategory());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateReportResult(Connection conn,Penalty p) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateReportResult"));
			pstmt.setString(1, p.getPenaltyCategory());
			pstmt.setInt(2, p.getReportNO());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Report> searchReport(Connection conn,String type,String keyword, int cPage,int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Report> report=new ArrayList<>();
		try {
			String sql="";
			if(type.equals("reporting_member")) {
				sql=this.sql.getProperty("selectSearchReporting");
			}else {
				sql=this.sql.getProperty("selectSearchReported");
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				report.add(getReport(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return report;
	}
	
	public int searchReportCount(Connection conn,String type,String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=this.sql.getProperty("searchReportCount");
		sql=sql.replace("#COL", type);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%" );
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int memberGradeUpdate(Connection conn,Penalty p) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
	
			pstmt=conn.prepareStatement(sql.getProperty("memberGradeUpdate"));
			if(p.getPenaltyCategory().equals("unactive")) {
				pstmt.setString(1, "탈퇴");
			}else if(p.getPenaltyCategory().equals("stop")) {
				pstmt.setString(1, "정지");
			}
//			else if(p.getPenaltyCategory().equals("pass")) {
//				pstmt.setString(1, "회원");
//			}
			//pstmt.setString(1, p.getPenaltyCategory());
			pstmt.setInt(2, p.getMemberNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
//	public String selectPenaltyDate(Connection conn,int no) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		String date="";
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectPenaltyDate"));
//			pstmt.setInt(1,no);
//			rs=pstmt.executeQuery();
//			if(rs.next()) {
//				date=(rs.getString("penalty_start_date"));
//			}
//			
//			
//		}catch(SQLException e) {
//			
//		}finally {
//			close(rs);
//			close(pstmt);
//		}
//		return date;
//	}
	
	
	public static Report getSearchReport(ResultSet rs) throws SQLException {
		return Report.builder()
				.reportNo(rs.getInt("report_no"))
				.reportCategory(rs.getString("report_category"))
				.reportContent(rs.getString("report_content"))
				.reportDate(rs.getDate("report_date"))
				.reportingMember(rs.getInt("reporting_member"))//신고한 회원
				.reportedMember(rs.getInt("reported_member"))//신고받은 회원
				.reportedId(rs.getString("member_id"))
				.reportResult(rs.getString("report_result"))
				.reportEndDate(rs.getDate("report_end_date"))
				.build();
	}
	
	
	
	public static Report getReport(ResultSet rs) throws SQLException {
		return Report.builder()
				.reportNo(rs.getInt("report_no"))
				.reportCategory(rs.getString("report_category"))
				.reportContent(rs.getString("report_content"))
				.reportDate(rs.getDate("report_date"))
				.reportEndDate(rs.getDate("report_end_date"))
				.reportingMember(rs.getInt("reporting_member"))//신고한 회원
				.reportedMember(rs.getInt("reported_member"))//신고받은 회원
				.reportedId(rs.getString(10))//인덱스 번호 11 신고받은 회원
				.reportingId(rs.getString(11))//인덱스 번호 12 신고한 회원
				.reportResult(rs.getString("report_result"))
				.build();
	}
	
	public static Report getReportView(ResultSet rs) throws SQLException {
		return Report.builder()
				.reportNo(rs.getInt("report_no"))
				.reportCategory(rs.getString("report_category"))
				.reportContent(rs.getString("report_content"))
				.reportDate(rs.getDate("report_date"))
				.reportingMember(rs.getInt("reporting_member"))//신고한 회원
				.reportedMember(rs.getInt("reported_member"))//신고받은 회원
				.reportedId(rs.getString(10))//인덱스 번호 11 신고받은 회원
				.reportingId(rs.getString(11))//인덱스 번호 12 신고한 회원
				.reportResult(rs.getString("report_result"))
				.penaltyDate(rs.getString("penalty_start_date")!=null?rs.getString("penalty_start_date"):"")
				.build();
	}
	

	
	
}
