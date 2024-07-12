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

import com.kupid.common.error.MyError;
import com.kupid.member.model.dto.MemberDto;

/**
 * Servlet Filter implementation class CheckAuthenticateFilter
 */
@WebFilter("/manager/*")
public class CheckAuthenticateFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public CheckAuthenticateFilter() {
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
		//비로그인 접속 차단
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpSession session = hrequest.getSession();
		MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
		if(loginMember!= null && loginMember.getMemberId().equals("admin")) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("prePage", hrequest.getContextPath());
			throw new MyError("접근 권한이 없습니다."); 
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
