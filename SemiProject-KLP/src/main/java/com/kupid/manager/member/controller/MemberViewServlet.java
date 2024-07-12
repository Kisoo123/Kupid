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
 * Servlet implementation class MemberViewServlet
 */
@WebServlet("/manager/memberview.do")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=Integer.parseInt(request.getParameter("no"));
		MemberService ms=new MemberService();
		
		MemberDto m= ms.selectMemberByNo(no);
		request.setAttribute("member", m);
		List<MemberDto> sm= ms.selectSubscribeByNo(no);
		request.setAttribute("subscribe", sm);
		List<MemberDto> memship= ms.selectMembershipByNo(no);
		request.setAttribute("membership", memship);

		request.getRequestDispatcher("/WEB-INF/views/manager/member/managermemberview.jsp").forward(request, response);
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
