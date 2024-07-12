package com.kupid.inquiry.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.inquiry.model.dto.InquiryDto;
import com.kupid.inquiry.model.service.InquiryService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeWriteEndServlet
 */
@WebServlet("/inquiry/inquirywriteend.do")
public class InquiryWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//enctype이 multipart/form-data 방식인 데이터 처리하기
		//cos.jar라이브러리를 이용해서 -> 톰 캣 9버전까지만 작동, 10버전이상 common-io, common-fileupload2 라이브러리 이용
		//cos.jar에서 제공하는 MultipartRequest 클래스를 이용해서 처리
		//매개변수있는 생성자를 이용해서 생성
		//5가지 매개변수 인자를 대입
		// 1: HttpServletRequest객체 
		// 2: 파일을 업로드할 위치 대입(String) -> 절대경로로 지정하는게 좋음
		// 3: 업로드파일에 대한 최대크기설정(int) -> byte단위 (1024곱할때마다 단위 올라감)
		// byte->kbyte(1024)-> Mbyte(1024*1024) -> Gbyte(1024*1024)
		// 4: 인코딩 방식(String) -> UTF-8
		// 5: 파일 rename규칙 설정(객체) -> 기본제공 DefaultFileRenamePolicy클래스
		
		// MultipartRequest 객체를 생성하면 자동으로 클라이언트가 보낸 파일을 지정한 위치에 저장함.
		
		// 추가데이터(파일외 제목, 작성자, 콘덴츠 등)-> MultipartRequest.getParameter()/getParameterValues() 메소드를 이용해서 가져옴
		
		//파일 업로드 경로 가져오기
	
		String path=getServletContext().getRealPath("/upload/inquiry");
		File dir=new File(path);
		if(!dir.exists()) dir.mkdirs();
		
		//인코딩 방식
		String encode="UTF-8";
		//파일크기
		int maxSize=1024*1024*10;
		//리네임 클래스
		DefaultFileRenamePolicy dfrp=new DefaultFileRenamePolicy();
		MultipartRequest mr=new MultipartRequest(request,path,maxSize,encode,dfrp);
		
		//나머지 정보를 가져오기
		String title=mr.getParameter("title");
		String content=mr.getParameter("content");
		int inq_member=Integer.parseInt(mr.getParameter("inq_member"));
	
		//업로드된 파일정보
		//원본파일명
		String oriname=mr.getOriginalFileName("upfile");
		//리네임파일명
		String rename=mr.getFilesystemName("upfile");
		System.out.println(title+content+oriname+rename); 
		
		//파일 저장하기
		/*
		 * InquiryDto n=InquiryDto.builder() .inqTitle(title) .inqContent(content)
		 * .inqFile(rename) .inq_Member(inq_member) // .inq_Date(null) //
		 * .inq_No(inq_no) .build();
		 */
				
				
		int result=new InquiryService().insertInquiry(title,content,rename,inq_member);
		String msg,loc;
		if(result>0) {
			msg="문의사항등록이 완료되었습니다";
			loc="/customer/customermain.do"; // 나중에 진형언니 내문의로 연결
		}else {
			msg="문의사항등록을 실패하였습니다";
			loc="/inquiry/inquiry.do"; 
			File delFile=new File(path+"/"+rename);
			if(delFile.exists()) delFile.delete();
			
		}	
		request.setAttribute("msg",msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(getServletContext().getInitParameter("viewpath")+"common/msg.jsp")
		.forward(request, response);
				
				
				
		
		
		
		
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
