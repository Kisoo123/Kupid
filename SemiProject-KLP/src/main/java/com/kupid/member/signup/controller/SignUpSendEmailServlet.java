package com.kupid.member.signup.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.common.MemberSendEmail;

/**
 * Servlet implementation class MyInfoEmailCheckServlet
 */
@WebServlet("/member/signupemailSend.do")
public class SignUpSendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpSendEmailServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (request.getParameter("email"));
		String title = "--- KUPID 이메일 인증 메일 ---";
		int code = 10000 + new Random().nextInt(90000);
		String content = "<h2>인증 코드 : " + code+"</h2>";
		request.getSession().setAttribute(email+"", code);
		System.out.println(content);
		
		MemberSendEmail mse = new MemberSendEmail(email);
		Session session = mse.setting(new Properties(), "kupid_79@naver.com", "kupid7979!");
		
		int result = mse.SendMail(session, title, content);
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
