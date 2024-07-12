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



/**
 * Servlet implementation class FaqSerchServlet
 */
@WebServlet("/faq/faqserch.do")
public class FaqSerchServlet extends HttpServlet {
	private static final long serialVersionUID  = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqSerchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage=1;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			
		}
		int numBerpage=10;
		try {
			numBerpage=Integer.parseInt(request.getParameter("numBerpage"));
		}catch(NumberFormatException e) {
			
		}
		
		String category=request.getParameter("category");
		String keyword=request.getParameter("searchKeyword");
		System.out.println(category);
		System.out.println(keyword);
		
		
		List<FaqDto> searchFaq=new FaqSevice().searchFaq(category,keyword,cPage,numBerpage);
		
		request.setAttribute("faq", searchFaq);
		
		int totalData=new FaqSevice().searchFaqCount(category, keyword);
		int totalPage=(int)Math.ceil(((double)totalData/numBerpage));
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		StringBuffer sb=new StringBuffer();
		sb.append("<ul class='pagination justify-content-end'>");
		if(pageNo==1) {
			sb.append("<li class='page-item disabled'>");
			sb.append("<a class='page-link' href='#'>이전</a>");
			sb.append("</li>");
		}else {
			sb.append("<li class='page-item'>");
			sb.append("<a class='page-link' href='"+
					request.getRequestURI()
					+"?cPage="+(pageNo-1)+"&"
					+"category="+category+"&"
					+"searchKeyword="+keyword
					+"'>이전</a>");
			sb.append("</li>");
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				sb.append("<li class='page-item active'>");
				sb.append("<a class='page-link' href='#'>"+pageNo+"</a>");
				sb.append("</li>");
			}else {
				sb.append("<li class='page-item'>");
				sb.append("<a class='page-link' href='"+
						request.getRequestURI()
						+"?cPage="+pageNo+"&"
						+"category="+category+"&"
						+"searchKeyword="+keyword
						+"'>"+pageNo+"</a>");
				sb.append("</li>");
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
			sb.append("<li class='page-item disabled'>");
			sb.append("<a class='page-link' href='#'>다음</a>");
			sb.append("</li>");
		}else {
			sb.append("<li class='page-item'>");
			sb.append("<a class='page-link' href='"+
					request.getRequestURI()
					+"?cPage="+(pageNo)+"&"
					+"category="+category+"&"
					+"searchKeyword="+keyword
					+"'>다음</a>");
			sb.append("</li>");
		}
		sb.append("</ul>");
		
		request.setAttribute("pageBar", sb);
		
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
