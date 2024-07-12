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
 * Servlet implementation class FeedCommentUpdateServlet
 */
@WebServlet("/feed/feedcommentupdate.do")
public class FeedCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int replyNumber = Integer.parseInt(request.getParameter("replyNumber"));
		String replyContent = request.getParameter("replyContent");
		int result = new FeedService().updateFeedComment(replyNumber,replyContent);
		
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
