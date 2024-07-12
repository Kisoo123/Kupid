package com.kupid.common.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import com.kupid.group.model.dto.GroupDto;
import com.kupid.main.service.MainService;
import com.kupid.member.model.dto.MemberDto;
import com.kupid.member.model.service.MemberService;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class GetgroupListFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public GetgroupListFilter() {
        super();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//페인페이지에서만 필터하여 저장하는 분기처리
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
			//아티스트그룹의 구독자 정보 가져오기(전체 아티스트)
//			List<MemberDto> result = new MemberService().selectGroupSubscribe();
			List<GroupDto> result = new MainService().selectAllGroup();
			request.setAttribute("GroupList", result);
			List<MemberDto> favorite = new MemberService().selectGroupSubscribe();
			request.setAttribute("GroupSubscribe", favorite);
		//화면전환
//		response.sendRedirect(request.getContextPath());
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
