package com.kupid.calender.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kupid.calender.model.dto.Calendar;
import com.kupid.calender.model.service.CalendarService;

/**
 * Servlet implementation class CalanderSelectBirthServlet
 */
@WebServlet(name = "CalenderSelectBirthServlet", urlPatterns = { "/calendar/calendarbirth.do" })
public class CalanderSelectBirthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalanderSelectBirthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
        List<Calendar> events = new CalendarService().getEventsByGroupNo(groupNo);
        
		Gson gson = new Gson();
		gson.toJson(events,response.getWriter());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
