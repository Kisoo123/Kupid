package com.kupid.feed.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.feed.model.service.FeedService;

/**
 * Servlet implementation class FeedReportServlet
 */
@WebServlet("/feed/feedReport.do")
public class FeedReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		String content = request.getParameter("content");
		int reportMemberNo = Integer.parseInt(request.getParameter("reportMemberNo"));
		int reportedfeedNo = Integer.parseInt(request.getParameter("reportedfeedNo"));
		new FeedService().insertFeedReport(category,content,reportMemberNo,reportedfeedNo);
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write("<html><body><script type='text/javascript'>window.opener.location.reload(); window.close();</script></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
