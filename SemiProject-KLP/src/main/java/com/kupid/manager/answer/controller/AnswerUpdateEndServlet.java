package com.kupid.manager.answer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.manager.answer.service.AnswerService;

/**
 * Servlet implementation class AnswerUpdateEndServlet
 */
@WebServlet("/manager/answerupdateend.do")
public class AnswerUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		int ansNo=Integer.parseInt(request.getParameter("no"));

		int result=new AnswerService().updateAnswer(ansNo,title,content);
		String msg,loc;
		if(result>0) {
			msg="수정성공";
			loc="/manager/inquirylist.do";
		}else {
			msg="수정실패";
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
