package com.kupid.mypage.profile.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.mypage.service.MyPageService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class MyProfileImgUpdateServlet
 */
@WebServlet("/mypage/profileImgDelete.do")
public class MyProfileImgDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfileImgDeleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pagenum", 1);
		int no = Integer.parseInt(request.getParameter("no"));
		String beforeImg = request.getParameter("beforeImg");
		String path = getServletContext().getRealPath("/upload/member/profile");
		System.out.println("프로필 사진 삭제 실행");
		
		int result = new MyPageService().deleteProfileImg(no);
		if(result>0) {
			System.out.println("삭제성공");
		} else {
			System.out.println("삭제실패");
			//업로드된 새로운 파일 다시 삭제
			File delFile=new File(path+"/"+beforeImg);
			if(delFile.exists()) delFile.delete();
		}
		response.sendRedirect(request.getContextPath() + "/mypage/myprofile.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
