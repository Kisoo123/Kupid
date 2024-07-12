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
 * Servlet implementation class GroupUpdateEndServlet
 */
@WebServlet("/manager/groupupdateend.do")
public class GroupUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//절대경로
			String path = getServletContext().getRealPath("/upload/manager/artist");
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
			String rename = mr.getFilesystemName("upfile"); 
				
		
		String name=mr.getParameter("name");
		int count=Integer.parseInt(mr.getParameter("count"));
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
		String company=mr.getParameter("company");
		
		GroupDto g=GroupDto.builder().groupNo(no).groupName(name).memberCount(count).groupCompany(company).groupImg(rename).build();
		int result=new GroupService().updateGroup(g);
		String msg,loc;
		if(result>0) {
			File delFile=new File(path+"/"+nowImg);
			if(delFile.exists()) delFile.delete();
			msg="수정성공";
			loc="/manager/grouplist.do";
		}else {
			File delFile=new File(path+"/"+rename);
			if(delFile.exists()) delFile.delete();
			msg="수정실패";
			loc="/manager/grouplist.do";
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
