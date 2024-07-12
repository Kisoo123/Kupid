package com.kupid.policies.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewPoliciesServlet
 */
@WebServlet("/policies/terms")
public class ViewPoliciesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPoliciesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		switch(no)
		{
		case 1 : 
			request.getRequestDispatcher("/WEB-INF/views/common/policies/terms.jsp")
			.forward(request, response); break;
		case 2 : 
			request.getRequestDispatcher("/WEB-INF/views/common/policies/privacy.jsp")
			.forward(request, response); break;
		case 3:
			request.getRequestDispatcher("/WEB-INF/views/common/policies/marketing.jsp")
			.forward(request, response); break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
