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
import com.kupid.member.model.dto.MemberDto;
import com.kupid.member.model.service.MemberService;
import com.kupid.mypage.info.controller.MyInfoSendEmailServlet;
import com.kupid.mypage.service.MyPageService;

/**
 * Servlet implementation class MyInfoEmailCheckEndServlet
 */
@WebServlet("/member/findpw/emailCheckEnd.do")
public class EmailCheckEndForPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailCheckEndForPwServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String id = request.getParameter("id");
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
		
		int code = (Integer)request.getSession().getAttribute(email+"");
		System.out.println("인증코드 : " + code);
		int value = Integer.parseInt(request.getParameter("value"));
		System.out.println("사용자 입력값 : "+value);
		
		MemberDto m=null;
		int result=0;
		if(value == code) {
			System.out.println("이메일 인증완료");
			request.getSession().removeAttribute(email+"");
			m = new MemberService().findId(name, email, sqlDate);
			//새로운 비밀번호가 이메일로 발송
			String title = "--- KUPID 비밀번호 변경 ---";
			String newpw = (10000 + new Random().nextInt(90000))+"!$@";
			String content = "<h2>새로운 비밀번호 : " + newpw+"</h2>";
			System.out.println(content);
			//새로운 비밀번호로 db 변경
			result = new MyPageService().updateInfoAll(id, name, newpw, m.getPhone(), m.getEmail(), m.getAddress(), m.getAddressDetail());
			
			MemberSendEmail mse = new MemberSendEmail(email);
			Session session = mse.setting(new Properties(), sql.getProperty("id"), sql.getProperty("pw"));
			result = mse.SendMail(session, title, content);
		}
//			Gson j = new Gson();
//			response.getWriter().print(j.toJson(m, MemberDto.class));
			response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
