package com.kupid.mypage.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.member.model.dto.MemberDto;
import com.kupid.mypage.service.MyPageService;

/**
 * Servlet implementation class PwCheckServlet
 */
@WebServlet("/pwcheck.do")
public class PwCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String prepw = request.getParameter("prepw");
		MemberDto m = new MyPageService().selectMember(id);
//		//해당 회원의 비밀번호가 현재 비밀번호 입력값과 같으면 1 반환
//		int result = m.getMemberPw()==prepw?1:0;
//		response.getWriter().print(result);
		boolean isMatch = m.getMemberPw().equals(prepw);
		response.getWriter().print(isMatch);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
