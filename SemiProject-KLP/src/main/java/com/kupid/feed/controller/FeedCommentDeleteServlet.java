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
 * Servlet implementation class FeedCommentDeleteServlet
 */
@WebServlet("/feed/feedcommentdelete.do")
public class FeedCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int replyNumber = Integer.parseInt(request.getParameter("replyNumber"));
			int result = new FeedService().deleteFeedComment(replyNumber);
			System.out.println(result+"ㄴㅁㅇㅇㄴㅁㄴㅇㅁㄴㅇㄴㅁㅇㄴㅁㄴㅁ");
			response.setContentType("application/json;charset=utf-8");

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
