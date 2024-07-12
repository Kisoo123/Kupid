package com.kupid.faq.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.faq.model.dto.FaqDto;
import com.kupid.faq.model.service.FaqSevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.faq.model.dto.FaqDto;
import com.kupid.faq.model.service.FaqSevice;
import com.kupid.faq.model.dto.FaqDto;



/**
 * Servlet implementation class FaqMainServlet
 */
@WebServlet("/faq/faq.do")
public class FaqMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("pagenum", 3);
		int cPage=1;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e){
			
		}
		int numBerpage = 10;
		try {
			numBerpage = Integer.parseInt(request.getParameter("numBerpage"));
		} catch (NumberFormatException e) {

		}
		
		int totalData=new FaqSevice().selectAllCount();
		int totalPage=(int)Math.ceil((double)totalData/numBerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		StringBuffer sb=new StringBuffer();
		sb.append("<ul class='pagination justify-content-center'>");
		
		if(pageNo==1) {
			sb.append("<li class='page-item'>");
			sb.append("<a href='#'>이전</a>");
			sb.append("</li>");
		}else {
			sb.append("<li class='page-item'>");
			sb.append("<a href='"+request.getRequestURI()+"?cPage="+(pageNo-1)+"&numBerpage="+numBerpage+"'>이전</a>");
			sb.append("</li>");
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				sb.append("<li class='page-item'>");
				sb.append("<a href='#'>"+pageNo+"</a>");
				sb.append("</li>");
			}else {
				sb.append("<li class='page-item'>");
				sb.append("<a href='"+request.getRequestURI()+"?cPage="+(pageNo)+"&numBerpage="+numBerpage+"'>"+pageNo+"</a>");
				sb.append("</li>");
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			sb.append("<li class='page-item'>");
			sb.append("<a href='#'>다음</a>");
			sb.append("</li>");
		}else {
			sb.append("<li class='page-item'>");
			sb.append("<a href='"+request.getRequestURI()+"?cPage="+(pageNo)+"&numBerpage="+numBerpage+"'>다음</a>");
			sb.append("</li>");
		}
		sb.append("</ul>");
		request.setAttribute("pageBar", sb);
		
		
		List<FaqDto> faq=new FaqSevice().selectFaqAll(cPage, numBerpage);
		request.setAttribute("faq", faq);
		request.getRequestDispatcher("/WEB-INF/views/faq/faq.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}