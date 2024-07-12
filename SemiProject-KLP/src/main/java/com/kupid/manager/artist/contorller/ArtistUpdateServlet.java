package com.kupid.manager.artist.contorller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.manager.member.service.MemberService;
import com.kupid.member.model.dto.MemberDto;

/**
 * Servlet implementation class ArtistUpdateServlet
 */
@WebServlet("/manager/artistupdate.do")
public class ArtistUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=Integer.parseInt(request.getParameter("no"));
		
		MemberDto m= new MemberService().selectMemberByNo(no);
		request.setAttribute("artist", m);
		
		request.getRequestDispatcher("/WEB-INF/views/manager/artist/artistview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
