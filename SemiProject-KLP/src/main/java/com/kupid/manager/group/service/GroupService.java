package com.kupid.manager.group.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.commit;
import static com.kupid.common.JDBCTemplate.getConnection;
import static com.kupid.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kupid.group.model.dto.GroupDto;
import com.kupid.manager.group.model.dao.GroupDAO;
import com.kupid.manager.notice.model.dto.Notice;
import com.kupid.member.model.dto.MemberDto;

public class GroupService {

	GroupDAO dao=new GroupDAO();
	
	public List<GroupDto> selectGroupAll(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<GroupDto> group=dao.selectGroupAll(conn,cPage,numPerpage);
		close(conn);
		return group;
	}
	
	public int selectGroupAllCount() {
		Connection conn=getConnection();
		int result=dao.selectGroupAllCount(conn);
		close(conn);
		return result;
	}
	
	public int deleteGroup(int no) {
		Connection conn=getConnection();
		int result=dao.deleteGroup(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int insertGroup(GroupDto g) {
		Connection conn=getConnection();
		int result=dao.insertGroup(conn,g);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateGroup(GroupDto g) {
		Connection conn=getConnection();
		int result=dao.updateGroup(conn,g);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public GroupDto selectGroupByNo(int no) {
		Connection conn=getConnection();
		GroupDto g=dao.selectGroupByNo(conn,no);
		close(conn);
		return g;
	}
	
	public List<GroupDto> searchGroup(String keyword,int cPage,int numPerpage){
		Connection conn=getConnection();
		List<GroupDto> group=dao.searchGroup(conn,keyword,cPage,numPerpage);
		close(conn);
		return group;
	}
	
	public int searchGroupCount(String keyword) {
		Connection conn=getConnection();
		int count=dao.searchGroupCount(conn,keyword);
		close(conn);
		return count;
	}
	
}
