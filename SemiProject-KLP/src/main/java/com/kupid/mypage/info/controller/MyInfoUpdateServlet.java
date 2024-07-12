package com.kupid.mypage.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.mypage.service.MyPageService;

/**
 * Servlet implementation class MyInfoUpdateServlet
 */
@WebServlet("/mypage/infoupdate.do")
public class MyInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pagenum", 2);
		
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		String newpw=request.getParameter("newpw");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String addressDetail=request.getParameter("addressDetail");
		int result = 0;
		//변경 비밀번호가 없으면 비밀번호는 바꾸지 않음
		if(newpw == null || newpw.equals("")) {
			result = new MyPageService().updateInfo(id, name, phone, email, address, addressDetail);
		} else {
			result = new MyPageService().updateInfoAll(id, name, newpw, phone, email, address, addressDetail);
		}
		if(result>0) {
			System.out.println("수정성공");
		} else {
			System.out.println("수정실패");
		}
		response.sendRedirect(request.getContextPath() + "/mypage/myinfo.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
