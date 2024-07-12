package com.kupid.manager.report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.manager.report.model.dto.Report;
import com.kupid.manager.report.service.ReportService;

/**
 * Servlet implementation class ReportViewServlet
 */
@WebServlet("/manager/reportview.do")
public class ReportViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=Integer.parseInt(request.getParameter("no"));
		Report r=new ReportService().selectReportByNo(no);
		request.setAttribute("report", r);
		System.out.println(r);
		request.getRequestDispatcher("/WEB-INF/views/manager/report/reportview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
