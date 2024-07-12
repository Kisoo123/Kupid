package com.kupid.member.log.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kupid.member.model.dto.KakaoProfileDTO;
import com.kupid.member.model.dto.MemberDto;
import com.kupid.member.model.dto.OAuthTokenDTO;
import com.kupid.member.model.service.MemberService;

/**
 * Servlet implementation class KakaoLoginServlet
 */
@WebServlet("/kakao/kakaologinservlet.do")
public class KakaoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakaoLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String bodyData="grant_type=authorization_code&";
        bodyData += "client_id=483bf9a98ad4edfd5c3b00bb569518bd&";
        bodyData += "redirect_uri=http://14.36.141.71:10079/GDJ79_KUPID_semi/kakao/kakaologinservlet.do&";
        bodyData += "code="+code; //인가코드


        URL url = new URL("https://kauth.kakao.com/oauth/token");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        con.setDoOutput(true);

        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8"));
        bw.write(bodyData);
        bw.flush();   
        
        BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "UTF-8")
                );
		String input="";
		StringBuilder sb=new StringBuilder();
		while((input=br.readLine())!=null){
		        sb.append(input);
		}

		Gson gson=new Gson();            
		OAuthTokenDTO oAuthToken=gson.fromJson(sb.toString(), OAuthTokenDTO.class);
        
		String kurl="https://kapi.kakao.com/v2/user/me";
		URL url2 =new URL(kurl);

		HttpsURLConnection con2=(HttpsURLConnection)url2.openConnection();

		//header 값 넣기
		con2.setRequestProperty("Authorization", "Bearer "+oAuthToken.getAccess_token());
		            con2.setDoOutput(true);

		//request 하기
		BufferedReader br2=new BufferedReader(new InputStreamReader(con2.getInputStream(),"UTF-8"));
		String input2="";
		StringBuilder sb2=new StringBuilder();
		while((input2=br2.readLine())!=null) {
		            sb2.append(input2);
		}

		Gson gson2=new Gson();
		KakaoProfileDTO kakaoProfile=gson2.fromJson(sb2.toString(),KakaoProfileDTO.class);
		
		String userid = kakaoProfile.getId() + "_kakao";
		String passwd = kakaoProfile.getId();
		String email = kakaoProfile.getKakao_account().getEmail();

		MemberDto m	= new MemberService().checkkakaoId(userid);
		MemberDto km = null; 

		boolean subscribeCk = false;
		
		if(m==null) {
			//회원가입
			km =MemberDto.builder()
					.kakaoId(userid)
					.kakaoPw(passwd)
					.email(email)
					.build(); 
			HttpSession session = request.getSession();
			session.setAttribute("kakaoMember", km);
			request.getRequestDispatcher("/WEB-INF/views/member/signup.jsp").forward(request, response);
		}else {
			//로그인
			HttpSession session=request.getSession();
				//로그인한 정보를 HttpSession에 저장
				session.setAttribute("loginMember", m);
				//아티스트그룹의 구독자 정보 가져오기(전체 아티스트)
				List<MemberDto> result = new MemberService().selectGroupSubscribe();
				for (MemberDto e : result) {
				    if (m.getMemberNo() == e.getMemberNo()) {
				        subscribeCk = true;
				        break; // 일치하는 항목을 찾으면 루프를 종료합니다.
				    }
				}
				request.setAttribute("subscribeCk", subscribeCk);
				session.setAttribute("GroupSubscribe", result);
				response.sendRedirect(request.getContextPath());
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
