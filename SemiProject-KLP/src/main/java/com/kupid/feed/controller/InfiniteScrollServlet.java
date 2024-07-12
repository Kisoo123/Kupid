package com.kupid.feed.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kupid.feed.model.dto.Feed;
import com.kupid.feed.model.service.FeedService;

/**
 * Servlet implementation class InfiniteScrollServlet
 */
@WebServlet("/feed/InfiniteScroll.do")
public class InfiniteScrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfiniteScrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage=1;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {}
		
		int numPerpage=10;
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));			
		}catch(NumberFormatException e) {}
		
		List<Feed> feeds=new FeedService().selectFeedAll(cPage,numPerpage);

			
//			int totalData=new FeedService().selectFeedCount();
//			int totalPage=(int)Math.ceil((double)totalData/numPerpage);
			
			response.setContentType("application/json;charset=utf-8");

			Gson gson = new Gson();
			gson.toJson(feeds,response.getWriter());

       }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
