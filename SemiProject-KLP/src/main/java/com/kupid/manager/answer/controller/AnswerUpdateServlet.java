package com.kupid.manager.answer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.manager.answer.model.dto.Answer;
import com.kupid.manager.answer.service.AnswerService;
import com.kupid.manager.inquiry.model.dto.Inquiry;
import com.kupid.manager.inquiry.service.InquiryService;

/**
 * Servlet implementation class AnswerUpdateServlet
 */
@WebServlet("/manager/answerupdate.do")
public class AnswerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inqNo=Integer.parseInt(request.getParameter("no"));
		Inquiry inq=new InquiryService().selectInquiryByNo(inqNo);
		request.setAttribute("inquiry", inq);
		Answer ans=new AnswerService().selectAnswerByNo(inqNo);	
		request.setAttribute("answer", ans);
		request.getRequestDispatcher("/WEB-INF/views/manager/inquiry/answerupdate.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
