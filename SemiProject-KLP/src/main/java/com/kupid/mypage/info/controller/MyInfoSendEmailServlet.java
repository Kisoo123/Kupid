package com.kupid.mypage.info.controller;

import java.io.FileReader;
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
import com.kupid.mypage.dao.MyPageDao;

/**
 * Servlet implementation class MyInfoEmailCheckServlet
 */
@WebServlet("/mypage/emailSend.do")
public class MyInfoSendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoSendEmailServlet() {
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
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(no);
		String title = "--- KUPID 이메일 인증 메일 ---";
		int code = 10000 + new Random().nextInt(90000);
		String content = "<h2>인증 코드 : " + code+"</h2>";
		request.getSession().setAttribute(no+"", code);
		System.out.println(content);
		
		String email = request.getParameter("email");
		MemberSendEmail mse = new MemberSendEmail(email);
		Session session = mse.setting(new Properties(), sql.getProperty("id"), sql.getProperty("pw"));
		
		int result = mse.SendMail(session, title, content);
		if(result>0) {
			response.getWriter().print(result);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
