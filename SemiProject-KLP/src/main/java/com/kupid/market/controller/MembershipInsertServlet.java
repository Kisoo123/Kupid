package com.kupid.market.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kupid.market.service.MembershipService;

/**
 * Servlet implementation class MembershipInsertServlet
 */
@WebServlet("/membershipinsert.do")
public class MembershipInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int KEY_LENGTH = 10; // 시리얼 키의 길이
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	public String generateSerialKey() {
        StringBuilder sb = new StringBuilder(KEY_LENGTH);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < KEY_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
	
	
    public MembershipInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=Integer.parseInt(request.getParameter("no"));
		String serialkey="";
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Set<String> serialKeys = new HashSet<>();
       
            String serialKey = generateSerialKey();
            
            // 중복 체크
            while (serialKeys.contains(serialKey)) {
                serialKey = generateSerialKey();
            }
            
            // 중복이 없을 때 추가
            serialKeys.add(serialKey);

            int result=new MembershipService().insertMembership(no,serialKey);
            
            String msg,loc;
    		if(result>0) {
    			msg="구매성공";
    			loc="/";
    		}else {
    			msg="구매실패";
    			loc="/marketview.do";
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
