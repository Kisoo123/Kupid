package com.kupid.market.service;

import com.kupid.market.model.dao.MembershipDAO;
import static com.kupid.common.JDBCTemplate.close;
import static com.kupid.common.JDBCTemplate.commit;
import static com.kupid.common.JDBCTemplate.getConnection;
import static com.kupid.common.JDBCTemplate.rollback;

import java.sql.Connection;

public class MembershipService {

	MembershipDAO dao=new MembershipDAO();
	
	public int insertMembership(int no,String serialkey) {
		Connection conn=getConnection();
		int result=dao.insertMembership(conn,no,serialkey);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
		
	}
	
	
	
	
}
