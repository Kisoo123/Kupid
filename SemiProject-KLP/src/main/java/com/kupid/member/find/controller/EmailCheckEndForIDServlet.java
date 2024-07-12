package com.kupid.member.find.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kupid.member.model.dto.MemberDto;
import com.kupid.member.model.service.MemberService;

/**
 * Servlet implementation class MyInfoEmailCheckEndServlet
 */
@WebServlet("/member/findid/emailCheckEnd.do")
public class EmailCheckEndForIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailCheckEndForIDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		if(value == code) {
			System.out.println("이메일 인증완료");
			request.getSession().removeAttribute(email+"");
			m = new MemberService().findId(name, email, sqlDate);
		}
			Gson j = new Gson();
			response.getWriter().print(j.toJson(m, MemberDto.class));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
