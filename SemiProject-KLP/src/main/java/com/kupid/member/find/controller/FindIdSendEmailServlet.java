package com.kupid.member.find.controller;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.common.MemberSendEmail;
import com.kupid.member.model.service.MemberService;
import com.kupid.mypage.info.controller.MyInfoSendEmailServlet;

/**
 * Servlet implementation class MyInfoEmailCheckServlet
 */
@WebServlet("/member/findid/send.do")
public class FindIdSendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdSendEmailServlet() {
        super();
    }
    private Properties sql = new Properties();
	{
		String path = MyInfoSendEmailServlet.class.getResource("/email.properties").getPath();

		try (FileReader fr = new FileReader(path)){
			sql.load(fr);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//존재하는 회원인지 조회
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		Date utilDate = null;
		try {
			utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		//string -> sql.Date로 형변환
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		int memberckResult = new MemberService().selectMemberByEmail(name, email, sqlDate);
		
		int result;
		if(memberckResult ==1) { //존재할 경우 인증메일 발송
			String title = "--- KUPID 이메일 인증 메일 ---";
			int code = 10000 + new Random().nextInt(90000);
			String content = "<h2>인증 코드 : " + code+"</h2>";
			request.getSession().setAttribute(email+"", code);
			System.out.println(content);
			
			MemberSendEmail mse = new MemberSendEmail(email);
			Session session = mse.setting(new Properties(), sql.getProperty("id"), sql.getProperty("pw"));
			result = mse.SendMail(session, title, content);
		} else {
			System.out.println("없는 회원입니다.");
			result = 0;
		}
		response.getWriter().print(result);
//		response.getWriter().print(1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
