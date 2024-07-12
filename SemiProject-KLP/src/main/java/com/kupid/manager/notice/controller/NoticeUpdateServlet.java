package com.kupid.manager.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.manager.notice.model.dto.Notice;
import com.kupid.manager.notice.service.NoticeService;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/manager/noticeupdate.do")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=1;
		try {
			no=Integer.parseInt(request.getParameter("no"));
		}catch(NumberFormatException e) {
			
		}
		Notice n=new NoticeService().selectNoticeByNo(no);
		request.setAttribute("notice", n);
		request.getRequestDispatcher("/WEB-INF/views/manager/notice/noticeupdate.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
