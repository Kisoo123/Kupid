package com.kupid.main.service;

import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kupid.group.model.dto.GroupDto;
import com.kupid.main.dao.MainDao;

public class MainService {
	private MainDao dao = new MainDao();
	public List<GroupDto> selectAllGroup() {
		Connection conn = getConnection();
		List<GroupDto> g = dao.selectAllGroup(conn);
		close(conn);
		return g;
	}
}
