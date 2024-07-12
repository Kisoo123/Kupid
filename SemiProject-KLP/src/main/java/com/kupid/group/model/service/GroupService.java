package com.kupid.group.model.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kupid.group.model.dao.GroupDao;
import com.kupid.group.model.dto.GroupDto;
import com.kupid.member.model.dto.MemberDto;

public class GroupService {
	private GroupDao dao = new GroupDao();
	public List<GroupDto> selectAllGroup() {
		Connection conn = getConnection();
		List<GroupDto> g = dao.selectAllGroup(conn);
		close(conn);
		return g;
	}
}
