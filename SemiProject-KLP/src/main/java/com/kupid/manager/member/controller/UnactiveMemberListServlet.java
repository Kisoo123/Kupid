package com.kupid.manager.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.manager.member.service.MemberService;
import com.kupid.member.model.dto.MemberDto;

/**
 * Servlet implementation class UnactiveMemberListServlet
 */
@WebServlet("/manager/unactivememberlist.do")
public class UnactiveMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnactiveMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pagenum", 9);
		
		
		int cPage=1;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e){
			
		}
		int numPerpage=10;
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e){
			
		}
		
		int totalData=new MemberService().selectPenaltyAllCount();
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		StringBuffer sb=new StringBuffer();
		sb.append("<ul class='pagination justify-content-center'>");
		
		if(pageNo==1) {
			sb.append("<li class='page-item'>");
			sb.append("<span>이전</span>");
			sb.append("</li>");
		}else {
			sb.append("<li class='page-item'>");
			sb.append("<a href='"+request.getRequestURI()+"?cPage="+(pageNo-1)+"&numPerpage="+numPerpage+"'>이전</a>");
			sb.append("</li>");
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				sb.append("<li class='page-item'>");
				sb.append("<span id='nowpage'>"+pageNo+"</span>");
				sb.append("</li>");
			}else {
				sb.append("<li class='page-item'>");
				sb.append("<a href='"+request.getRequestURI()+"?cPage="+(pageNo)+"&numPerpage="+numPerpage+"'>"+pageNo+"</a>");
				sb.append("</li>");
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			sb.append("<li class='page-item'>");
			sb.append("<span>다음</span>");
			sb.append("</li>");
		}else {
			sb.append("<li class='page-item'>");
			sb.append("<a href='"+request.getRequestURI()+"?cPage="+(pageNo)+"&numPerpage="+numPerpage+"'>다음</a>");
			sb.append("</li>");
		}
		sb.append("</ul>");
		request.setAttribute("pageBar", sb);
		
		
		List<MemberDto> member=new MemberService().selectPenaltyAll(cPage,numPerpage);
		request.setAttribute("member", member);
//		System.out.println("member : "+member);
		
		request.getRequestDispatcher("/WEB-INF/views/manager/member/managerunmemberlist.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
