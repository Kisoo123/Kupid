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
 * Servlet implementation class NoticeUpdateEndServlet
 */
@WebServlet("/manager/noticeupdateend.do")
public class NoticeUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=Integer.parseInt(request.getParameter("noticeNo"));
		String category=request.getParameter("category");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		Notice n=Notice.builder()
				.noticeNo(no).noticeCategory(category).noticeTitle(title).noticeContent(content).build();
		int result=new NoticeService().noticeUpdate(n);
		String msg,loc;
		if(result>0) {
			msg="수정성공";
			loc="/manager/noticelist.do";
		}else {
			msg="수정실패";
			loc="/manager/noticelist.do";
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
