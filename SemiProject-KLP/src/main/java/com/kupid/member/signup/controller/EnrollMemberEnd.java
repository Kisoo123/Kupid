package com.kupid.member.signup.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kupid.member.model.dto.MemberDto;
import com.kupid.member.model.service.MemberService;

/**
 * Servlet implementation class EnrollMemberEnd
 */
@WebServlet(name = "enrollMember", urlPatterns= "/member/enrollmemberend.do")
public class EnrollMemberEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberEnd() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name"); 
		String phone = request.getParameter("phone"); 
		String email = request.getParameter("email"); 
		String address = request.getParameter("address"); 
		String addressDetail = request.getParameter("addressDetail"); 
		String gender = request.getParameter("gender");
		gender = gender.equals("male")?"M":"F";
		String birth = request.getParameter("birth");
		MemberDto kakaoMember = (MemberDto)session.getAttribute("kakaoMember");
		System.out.println(kakaoMember+"ㅇㄴㅁ");
		String kakaoId = null;
		String kakaoPw = null;
		if(kakaoMember!=null) {
			email = kakaoMember.getEmail();
			System.out.println(email+"email");
			kakaoId = kakaoMember.getKakaoId();
			System.out.println(kakaoId+"kakaoId");
			kakaoPw = kakaoMember.getKakaoPw();
			System.out.println(kakaoPw+"kakaoPw");
		}
		
		
		//임의 닉네임 생성
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
		String nickname = name + (1000+new Random().nextInt(90000)) + sdf.format(System.currentTimeMillis());
		//닉네임 중복체크
		int nicknameCk = new MemberService().checkNickname(nickname); //0이어야 중복 없음
		while(nicknameCk==1) {
			nickname = "user" + (new Random().nextInt(90000));
			nicknameCk = new MemberService().checkNickname(nickname);
		}
		Date utilDate = null;
		try {
			utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		//string -> sql.Date로 형변환
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		//암호화 당분간 생략
//		try {
//			email = AESEncryptor.encrptData(email);
//			phone = AESEncryptor.encrptData(phone);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		MemberDto m = MemberDto.builder()
				.memberId(id)
				.memberPw(pw)
				.memberName(name)
				.gender(gender)
				.email(email)
				.phone(phone)
				.address(address)
				.addressDetail(addressDetail)
				.birth(sqlDate)
				.nickname(nickname)
				.kakaoId(kakaoId)
				.kakaoPw(kakaoPw)
				.build();
		int result = new MemberService().insertMember(m);
		System.out.println(result);
		String msg = "", loc="";
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			msg="회원가입을 축하합니다.";
			loc="/";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			rd.forward(request,response);
		} else {
			msg="회원가입 실패";
			loc="/member/enrollmember.do";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			rd.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
