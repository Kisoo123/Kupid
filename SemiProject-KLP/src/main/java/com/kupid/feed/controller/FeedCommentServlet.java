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
 * Servlet implementation class FeedCommentServlet
 */
@WebServlet("/feed/feedcomment.do")
public class FeedCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int loginMember = Integer.parseInt(request.getParameter("loginMember")); 
		String comment = request.getParameter("commentText");
		int feedNo = Integer.parseInt(request.getParameter("feedNoText"));
		
		new FeedService().insertFeedComment(loginMember,comment,feedNo);
		
		int result = new FeedService().countFeedComment(feedNo);
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
