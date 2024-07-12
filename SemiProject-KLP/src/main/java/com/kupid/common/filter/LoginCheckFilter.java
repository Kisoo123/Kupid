package com.kupid.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kupid.member.model.dto.MemberDto;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
//TODO 수정필요

@WebFilter(servletNames = {"memberView", "searchmember", "memberlist", "boardwrite"}, urlPatterns= {"/admin/*","/manager/*","/mypage/*","/inquiry/*","/membershipinsert.do","/marketview.do"})


public class LoginCheckFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginCheckFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		MemberDto loginMember = (MemberDto)session.getAttribute("loginMember");
		if(loginMember == null) {
			request.setAttribute("msg", "로그인 후 이용할 수 있습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher(request.getServletContext().getInitParameter("viewpath")+"common/msg.jsp")
			.forward(request, response);
//		} else if(!loginMember.getUserId().equals("admin")){
//			request.setAttribute("msg", "관리자 로그인 후 이용할 수 있습니다.");
//			request.setAttribute("loc", "/");
//			request.getRequestDispatcher(request.getServletContext().getInitParameter("viewpath")+"common/msg.jsp")
//			.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
