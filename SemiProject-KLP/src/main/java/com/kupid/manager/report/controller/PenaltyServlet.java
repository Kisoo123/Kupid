package com.kupid.manager.report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.manager.penalty.model.dto.Penalty;
import com.kupid.manager.report.service.ReportService;

/**
 * Servlet implementation class PenaltyServlet
 */
@WebServlet("/manager/penalty.do")
public class PenaltyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PenaltyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reportNo=Integer.parseInt(request.getParameter("reportNo"));
		int memberNo=Integer.parseInt(request.getParameter("memberNo"));
		String penalty=request.getParameter("penalty");
		
		Penalty p=Penalty.builder().reportNO(reportNo).MemberNo(memberNo).penaltyCategory(penalty).build();
		
		int result=new ReportService().insertPenalty(p);
		String msg,loc;
		if(result>0) {
			msg="처리성공";
			loc="/manager/reportlist.do";
		}else {
			msg="처리실패";
			loc="/manager/reportlist.do";
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
