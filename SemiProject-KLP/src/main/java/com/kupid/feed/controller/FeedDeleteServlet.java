package com.kupid.feed.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kupid.feed.model.service.FeedService;

/**
 * Servlet implementation class FeedDeleteServlet
 */
@WebServlet("/feed/feeddelete.do")
public class FeedDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int feedNo = Integer.parseInt(request.getParameter("feedNo"));
		int result = new FeedService().deleteFeed(feedNo);

//		request.getRequestDispatcher("/WEB-INF/views/feed/feedView.jsp").forward(request, response);
		Gson gson = new Gson();
		gson.toJson(result,response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
