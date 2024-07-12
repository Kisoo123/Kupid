package com.kupid.feed.model.dao;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.member.model.dao.MemberDao.memberBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kupid.feed.model.dto.Feed;
import com.kupid.feed.model.dto.LikeFeed;
import com.kupid.feed.model.dto.Reply;
import com.kupid.member.model.dto.MemberDto;

public class FeedDao {
	
	private Properties sql = new Properties();
	{
		String path=FeedDao.class.getResource("/sql/sql_feed.properties").getPath();
		try(FileReader fr=new FileReader(path)) {
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int feedUpdate(Connection conn, int feedNo, String feedContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("feedUpdate"));
			pstmt.setString(1,feedContent);
			pstmt.setInt(2,feedNo);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteFeed(Connection conn,int feedNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteFeed"));
			pstmt.setInt(1,feedNo);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int countFeedComment(Connection conn, int feedNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("countFeedComment"));
			pstmt.setInt(1,feedNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
		
		System.out.println(result);
		
			return result;
	}
	
	public int checkLikes(Connection conn,int feedNo,int loginMemberNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("checkLikes"));
			pstmt.setInt(1,feedNo);
			pstmt.setInt(2,loginMemberNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
		
		
			return result;
		
	}

	public int insertFeedReport(Connection conn, String category, String content, int reportMemberNo, int reportedMemberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertFeedReport"));
			pstmt.setInt(1,reportMemberNo);
			pstmt.setInt(2,reportedMemberNo);
			pstmt.setString(3,category);
			pstmt.setString(4,content);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateFeedComment(Connection conn,int replyNumber, String replyContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateFeedComment"));
			pstmt.setString(1,replyContent);
			pstmt.setInt(2,replyNumber);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int selectMemberByFeedNo(Connection conn, int reportedfeedNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberByFeedNo"));
			pstmt.setInt(1,reportedfeedNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int insertLikes(Connection conn,int memberNo,int feedNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertLikes"));
			pstmt.setInt(1,memberNo);
			pstmt.setInt(2,feedNo);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int selectLikes(Connection conn,int memberNo,int feedNo) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectLikes"));
			pstmt.setInt(1,memberNo);
			pstmt.setInt(2,feedNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getInt(1);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return result;
		}

		public List<MemberDto> selectFeedComment(Connection conn,int feedNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDto> result = new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectFeedComment"));
			pstmt.setInt(1, feedNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberDto r=getMember(rs);
				result.add(r);

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int selectFeedLikes(Connection conn,int feedNo) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectFeedLikes"));
			pstmt.setInt(1,feedNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int updateFeedLikes(Connection conn, int likes, int feedNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateFeedLikes"));
			pstmt.setInt(1,likes);
			pstmt.setInt(2,feedNo);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteLikes(Connection conn,int memberNo,int feedNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteLikes"));
			pstmt.setInt(1,memberNo);
			pstmt.setInt(2,feedNo);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int selectSeqFeed(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int seqFeed=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectSeqFeed"));
			rs=pstmt.executeQuery();
			rs.next();
			seqFeed=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return seqFeed;
	}
	
	public int insertFeedComment(Connection conn,int loginMember,String comment,int feedNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertFeedComment"));
			System.out.println(pstmt.toString());
			pstmt.setInt(1, feedNo);
			pstmt.setString(2, comment);
			pstmt.setInt(3, loginMember);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int insertFeedFile(Connection conn, String filePath, int seq) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertFeedFile"));
			pstmt.setInt(1, seq);
			pstmt.setString(2, filePath);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertFeed(Connection conn, Feed f, int seq,int memberNo,int groupNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertFeed"));
			pstmt.setInt(1,seq);
			pstmt.setInt(2,memberNo);
			pstmt.setString(3,f.getFeedWriterName());
			pstmt.setString(4,f.getFeedContent());
			pstmt.setInt(5,0);
			pstmt.setInt(6,0);
			pstmt.setInt(7,groupNo);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
				
	}
	public int selectMemberNoById(Connection conn, String memberName) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int memberNo=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberNoById"));
			pstmt.setString(1,memberName);
			rs=pstmt.executeQuery();
			rs.next();
			memberNo=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberNo;
		
	}
	
	
	public int selectGroupNoById(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int groupId = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectGroupNoById"));
			pstmt.setInt(1,memberNo);
			rs=pstmt.executeQuery();
			rs.next();
			memberNo=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberNo;
	}
	
	public int deleteFeedComment(Connection conn,int replyNumber) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteFeedComment"));
			pstmt.setInt(1,replyNumber);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
//	public int insertFile(Connection conn,String filePath ) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		
//		
//		try {
//			pstmt = conn.prepareStatement(sql.getProperty("insertFeedFile"));
//			pstmt.setString(1, filePath);
//			result = pstmt.executeUpdate();
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}
//		return result;
//	}
	
	public int selectFeedCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectFeedCount"));
			rs=pstmt.executeQuery();
			rs.next();
			result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<Feed> selectFeedAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Feed> result=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectFeedAll"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Feed f=getFeed(rs);
				result.add(f);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public static Feed getFeed(ResultSet rs) throws SQLException{
		return Feed.builder()
				.feedNo(rs.getInt("FEED_NO"))
				.feedMemberName(rs.getString("MEMBER_NO"))
				.feedWriterName(rs.getString("WRITER_NAME"))
				.feedContent(rs.getString("CONTENT"))
				.feedWriteDate(rs.getDate("WRITEDATE"))
				.feedUpdateDate(rs.getDate("UPDATEDATE"))
				.likes(rs.getInt("LIKES"))
				.report(rs.getInt("REPORT"))
				.filePath(rs.getString("FILE_PATH"))
				.profileImgOriname(rs.getString("PROFILE_IMG_ORINAME"))
				.commentCnt(rs.getInt("commentCnt"))
				.groupNo(rs.getInt("GROUP_NO"))
				.memberGrade(rs.getString("MEMBER_GRADE"))
				.build();
	}
	public static LikeFeed getLikeFeed(ResultSet rs) throws SQLException{
		return LikeFeed.builder()
				.memberNo(rs.getInt("MEMBERNO"))
				.feedNo(rs.getInt("FEED_NO"))
				.likes(rs.getInt("LIKES"))
				.build();	}
	
	public static Reply getReply(ResultSet rs) throws SQLException{
		return Reply.builder()
				.replyNumber(rs.getInt("reply_number"))
				.feedNo(rs.getInt("feed_no"))
				.replyDate(rs.getDate("reply_date"))
				.likes(rs.getInt("likes"))
				.memberNo(rs.getInt("memberno"))
				.replyContent(rs.getString("reply_content"))
				.build();
	}
	public static MemberDto getMember(ResultSet rs) throws SQLException{
		return MemberDto.builder()
				.memberNo(rs.getInt("member_no"))
				.memberId(rs.getString("member_id"))
				.memberPw(rs.getString("member_pw"))
				.memberName(rs.getString("member_name"))
				.gender(rs.getString("gender"))
				.phone(rs.getString("phone"))
				.address(rs.getString("address"))
				.addressDetail(rs.getString("address_detail"))
				.email(rs.getString("email"))
				.birth(rs.getDate("birth"))
				.introduce(rs.getString("introduce"))
				.nickname(rs.getString("nickname"))
				.profileImgOriname(rs.getString("profile_img_oriname"))
//				.profileImgRenamed(rs.getString("profile_img_renamed"))
				.memberGrade(rs.getString("member_grade"))
				.enrollDate(rs.getDate("enroll_date"))
				.replyNumber(rs.getInt("REPLY_NUMBER"))
				.feedNo(rs.getInt("feed_no"))
				.replyDate(rs.getDate("reply_date"))
				.likes(rs.getInt("likes"))
				.memberNo(rs.getInt("memberno"))
				.replyContent(rs.getString("reply_content"))
				.groupNo(rs.getInt("GROUP_NO"))
				.build();
	}
}
