package com.kupid.manager.answer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.manager.answer.service.AnswerService;

/**
 * Servlet implementation class AnswerInsertEndServlet
 */
@WebServlet("/manager/answerinsertend.do")
public class AnswerInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerInsertEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		System.out.println(request.getParameter("no"));
		System.out.println(title);
		System.out.println(content);
		int inqNo=0;
		try {
			inqNo=Integer.parseInt(request.getParameter("no"));
		}catch(NumberFormatException e) {
			System.out.println("넘포에러");
		}
		System.out.println(inqNo);
		int result=new AnswerService().insertAnswer(inqNo,title,content);
		
		String msg,loc;
		if(result>0) {
			msg="등록성공";
			loc="/manager/inquirylist.do";
		}else {
			msg="등록실패";
			loc="/manager/inquirylist.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
