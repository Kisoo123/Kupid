package com.kupid.mypage.profile.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.kupid.member.model.dto.MemberDto;
import com.kupid.mypage.service.MyPageService;

/**
 * Servlet implementation class MyProfileServlet
 */
@WebServlet("/mypage/myprofile.do")
public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pagenum", 1);
		HttpSession session=request.getSession();
		MemberDto m = (MemberDto) session.getAttribute("loginMember");
		System.out.println(m.getProfileImgOriname());
		List<MemberDto> result = new MyPageService().selectMemberForProfile(m.getMemberId());
		System.out.println(result.get(0).toString());
		request.setAttribute("memberProfile", result);
		request.getRequestDispatcher("/WEB-INF/views/mypage/myProfile.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
