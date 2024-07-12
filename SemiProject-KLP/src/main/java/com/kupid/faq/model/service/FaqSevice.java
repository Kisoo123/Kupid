package com.kupid.faq.model.service;


import static com.kupid.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import com.kupid.faq.model.dto.FaqDto;
import com.kupid.manager.faq.model.dto.Faq;
import com.kupid.faq.model.dao.FaqDao;

import static com.kupid.common.JDBCTemplate.close;


public class FaqSevice {
	
	public FaqDao faqDao=new FaqDao();
	
	
	public List<FaqDto> selectFaqAll(int cPage,int numberpage){
		Connection conn=getConnection();
		List<FaqDto> faq = faqDao.selectFaqAll(conn, cPage, numberpage);
		System.out.println(cPage);
		close(conn);
		return faq;
		}
	public int selectAllCount() {
        Connection conn = getConnection();
        int result = faqDao.selectFaqAllCount(conn); 
        close(conn);
        return result;
	}
	public List<FaqDto> searchFaq(String category,String keyword,int cPage,int numBerpage){
		Connection conn=getConnection();
		List<FaqDto> faq=faqDao.searchFaq(conn,category,keyword,cPage,numBerpage);
		close(conn);
		return faq;
	}
	public int searchFaqCount(String category,String keyword) {
		Connection conn=getConnection();
		int count=faqDao.searchFaqCount(conn,category,keyword);
		close(conn);
		return count;
	}
}

