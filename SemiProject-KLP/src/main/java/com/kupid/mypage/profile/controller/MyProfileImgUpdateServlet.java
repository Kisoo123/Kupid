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
@WebServlet("/mypage/profileImgUpdate.do")
public class MyProfileImgUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfileImgUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pagenum", 1);
		
		System.out.println("프로필 업데이트 실행");
		
		//절대경로
		String path = getServletContext().getRealPath("/upload/member/profile");
		//Not a directory: /Users/pjh/git/SemiProject---KLP/src/main/webapp/upload/member/profile
		System.out.println(path);
		//
		//만약 경로에 파일이 없을 것을 대비하여 생성하는 로직 작성
		File dir = new File(path);
		if(! dir.exists()) dir.mkdir();
		int maxSize = 1024*1024*10; //10mb
		String encode = "utf-8";
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encode, new DefaultFileRenamePolicy());

		int no = Integer.parseInt(mr.getParameter("no"));
		String nowImg = mr.getParameter("nowImg");
		System.out.println(nowImg);
		String rename = mr.getFilesystemName("upfile"); 
		
		int result = new MyPageService().updateProfileImg(no, rename);
		if(result>0) {
			System.out.println("수정성공");
			//기본프로필이었던 사람은 그냥 새로 업로드 후 데이터만 바뀌면 됨
			//기존파일이 있던 사람은 기존파일 삭제
			if(!nowImg.equals("기본프로필.png")) {
				File delFile=new File(path+"/"+nowImg);
				if(delFile.exists()) delFile.delete();
			}
		} else {
			System.out.println("수정실패");
			//업로드된 새로운 파일 다시 삭제
			File delFile=new File(path+"/"+rename);
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
