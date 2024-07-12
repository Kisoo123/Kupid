package com.kupid.mypage.info.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyInfoEmailCheckEndServlet
 */
@WebServlet("/mypage/emailCheckEnd.do")
public class MyInfoEmailCheckEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoEmailCheckEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("사용자 번호 : "+no);
		
		int code = (Integer)request.getSession().getAttribute(no+"");
		System.out.println("인증코드 : " + code);
		int value = Integer.parseInt(request.getParameter("value"));
		System.out.println("사용자 입력값 : "+value);
		
		int result = 0;
		if(value == code) {
			result = 1;
			System.out.println("이메일 인증완료");
			request.getSession().removeAttribute(no+"");
		}
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
