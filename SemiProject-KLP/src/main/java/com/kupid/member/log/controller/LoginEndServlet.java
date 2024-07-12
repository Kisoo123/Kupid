package com.kupid.member.log.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kupid.member.model.dto.MemberDto;
import com.kupid.member.model.service.MemberService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns="/loginEnd.do")
public class LoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 전달한 id, password값으로 DB에 동일한 회원이 있는지 확인해주는 기능
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		
		String saveId=request.getParameter("saveId");
		Cookie saveIdCookie=new Cookie("saveId",userId);
		if(saveId!=null) {			//
			saveIdCookie.setMaxAge(60*60*24*7);
			saveIdCookie.setPath("/");
			response.addCookie(saveIdCookie);
		}else {
//			Cookie saveIdCookie=new Cookie("saveId","삭제");
			saveIdCookie.setMaxAge(0);
		}
		response.addCookie(saveIdCookie);
		
		if(userId.equals("")||password.equals("")) {
			request.setAttribute("msg", "아이디나 패스워드를 입력하세요");
			request.setAttribute("loc", "/");
			RequestDispatcher rd=request
					.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			rd.forward(request, response);
		}else {
			//메인페이지 회원 구독 아티스트 조회를 위한 조인 쿼리문을 통한 메소드로 변경
			MemberDto m=new MemberService().selectMemberById(userId,password);
			HttpSession session=request.getSession();
			if(m!=null&&(m.getMemberGrade().equals("회원")||m.getMemberGrade().equals("아티스트")||m.getMemberGrade().equals("관리자"))) {
				//로그인한 정보를 HttpSession에 저장
				session.setAttribute("loginMember", m);
				//아티스트그룹의 구독자 정보 가져오기(전체 아티스트)
				//화면전환
				if(!userId.equals("admin")) {//관리자로 로그인하면 메인페이지가 아니라 관리자 페이지로 이동되게 하는 로직
					response.sendRedirect(request.getContextPath());
				}else {
					response.sendRedirect(request.getContextPath()+"/manager/home.do");
				}
			}else if(m!=null&&m.getMemberGrade().equals("탈퇴")) {//탈퇴된 애들 로그인 막는 로직
				request.setAttribute("msg", "탈퇴된 회원입니다.");
				request.setAttribute("loc", "/");
				request.getRequestDispatcher(request.getServletContext().getInitParameter("viewpath")+"common/msg.jsp")
				.forward(request, response);
			}else if(m!=null&&m.getMemberGrade().equals("정지")){
				request.setAttribute("msg", "정지된 회원입니다.");
				request.setAttribute("loc", "/");
				request.getRequestDispatcher(request.getServletContext().getInitParameter("viewpath")+"common/msg.jsp")
				.forward(request, response);
			}else{
				request.setAttribute("msg","아이디나 패스워드가 일치하지 않습니다");
				request.setAttribute("loc", "/");
				request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
