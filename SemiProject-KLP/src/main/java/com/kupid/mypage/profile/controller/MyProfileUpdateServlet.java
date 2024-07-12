package com.kupid.mypage.profile.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.mypage.service.MyPageService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class MyInfoUpdateServlet
 */
@WebServlet("/mypage/profileupdate.do")
public class MyProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfileUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pagenum", 1);
		int no = Integer.parseInt(request.getParameter("no"));
		
		String nickname=request.getParameter("nickname");
		String introduce=request.getParameter("introduce");
		int result = new MyPageService().updateProfile(no, nickname, introduce);
		if(result>0) {
			System.out.println("수정성공");
		} else {
			System.out.println("수정실패");
		}
		response.sendRedirect(request.getContextPath() + "/mypage/myprofile.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
