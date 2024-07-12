package com.kupid.manager.group.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.group.model.dto.GroupDto;
import com.kupid.manager.group.service.GroupService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class GroupInsertEndServlet
 */
@WebServlet("/manager/groupinsertend.do")
public class GroupInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupInsertEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//file upload에 저장하는 로직
		//절대경로
		String path = getServletContext().getRealPath("/upload/manager/artist");
//		String path = request.getContextPath()+"/upload";
		//Not a directory: /Users/pjh/git/SemiProject---KLP/src/main/webapp/upload/member/profile
		System.out.println(path);
		//
		//만약 경로에 파일이 없을 것을 대비하여 생성하는 로직 작성
		File dir = new File(path);
		if(! dir.exists()) dir.mkdir();
		int maxSize = 1024*1024*10; //10mb
		String encode = "utf-8";
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encode, new DefaultFileRenamePolicy());
		
		String rename = mr.getFilesystemName("upfile"); 
		System.out.println("rename : " + rename);
		
		String name=mr.getParameter("name");
		int count=Integer.parseInt(mr.getParameter("count"));
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
		java.sql.Date debutday=java.sql.Date.valueOf(mr.getParameter("debutday"));
		String company=mr.getParameter("company");

		
		
		
		GroupDto g=GroupDto.builder().groupName(name).memberCount(count).groupCompany(company).groupDebutday(debutday).groupImg(rename).build();
		
		int result=new GroupService().insertGroup(g);
		String msg,loc;
		if(result>0) {
			msg="등록성공";
			loc="/manager/grouplist.do";
		}else {
			msg="등록실패";
			loc="/manager/grouplist.do";
			File delFile=new File(path+"/"+rename);
			if(delFile.exists()) delFile.delete();
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
