<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.kupid.member.model.dto.MemberDto" %>
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<style>
	section{
		margin: 0px auto;
	}
	.flex_col {
	    position: relative;
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    width: 90vw; /* 90% of viewport width */
	    outline-offset: 70px;
	    margin: 0px auto;
	    padding: 70px 50px 70px 50px;
	}
	@media (max-width: 600px) {
	    .flex_col {
	        width: 90vw;
	        margin: 5vh auto;
	        padding: 20px;
	    }
	   }
	.big_title {
	    position: relative;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    color: rgb(0, 0, 0);
	    text-align: center;
	    letter-spacing: -0.36px;
	}
	
	.input_box {
	    position: relative;
	    margin: 8px 0px 0px;
	    min-width: 0px;
	    border-radius: 8px;
	    padding: 8px 16px;
	    outline: 1px solid #e0e0e0;
	    outline-offset: -1px;
	}
	
	.input_box1 {
	    position: relative;
	    display: flex;
	    flex-direction: column;
	    margin: 19px 0px 0px;
	    width: 100%;
	    background-color: rgb(255, 255, 255);
	    border-radius: 8px;
	    outline: 1px solid rgb(224, 224, 224);
	    outline-offset: -1px;
	}
	 .inputTag {
	  width: 100%;
	  color: black;
	  background-color: transparent;
	  border: 0px;
	  padding: 0px;
	  vertical-align: top;
	}
	.flex_row {
	    position: relative;
	    display: flex;
	    gap: 0px 13px;
	    margin: 10px 3px 0px;
	    width: 91.5%;
	}
	
	.highlight {
	    position: relative;
	    min-width: 0px;
	    color: rgb(0, 0, 0);
	    letter-spacing: -0.9px;
	}
	
	.login_btn {
	    position: relative;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    margin: 20px 0px 0px auto;
	    width: 400px;
	    max-width: 100%;
	    color: rgb(255, 255, 255);
	    text-align: center;
	    background-color: rgb(209, 173, 227);
	    border-radius: 8px;
	    padding: 11.5px 8px;
	    letter-spacing: -1px;
	    height: 40px;
	}
	
	.line {
	    position: relative;
	    display: flex;
	    gap: 0px 8px;
	    margin: 0px auto;
	    width: 100%;
	    align-items: center;
	}
	
	.line1 {
	    position: relative;
	    margin: 12px 0px 11px;
	    width: 153px;
	    min-width: 0px;
	    height: 1px;
	    min-height: 1px;
	    background-color: rgb(230, 230, 230);
	}
	
	.highlight11 {
	    position: relative;
	    display: flex;
	    justify-content: center;
	    min-width: 0px;
	    color: rgb(130, 130, 130);
	    text-align: center;
	    letter-spacing: -2px;
	    margin: 0px auto;
	    font-size: medium;
	}
	
	.line11 {
	    position: relative;
	    margin: 12px 0px 11px;
	    width: 120px;
	    min-width: 0px;
	    height: 1px;
	    min-height: 1px;
	    background-color: rgb(230, 230, 230);
	}
	
	.social_login {
	    position: relative;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    margin: 20px auto;
	    width: 400px;
	    max-width: 100%;
	    color: black;
	    text-align: center;
	    border-radius: 8px;
	    padding: 11.5px 8px;
	    letter-spacing: -1px;
	    background-color: rgb(238, 238, 238);
	    height: 40px;
	    font-size: 1.2rem;
	}
	
	.highlight3_box {
	    position: relative;
	    display: flex;
	    justify-content: center;
	    margin: 18px 0px 0px;
	}
	
	.highlight3 {
	    color: rgb(0, 0, 0);
	    text-align: center;
	}
	
	.highlight3_span0 {
	    color: rgb(130, 130, 130);
	}
	
	.highlight3_span2 {
	    color: rgb(130, 130, 130);
	}
	.flex_row1 {
	    position: relative;
	    display: flex;
	    gap: 0px 12px;
	    margin: 0px auto;
	    width: 100%;
	    justify-content: center;
	    align-items: center;
	}
	
	.subtitle1 {
	    position: relative;
	    display: flex;
	    justify-content: center;
	    min-width: 0px;
	    color: rgb(130, 130, 130);
	    text-align: center;
	    display: block;
	    font-size: 1em;
	    margin-block-start: 1em;
	    margin-block-end: 1em;
	    margin-inline-start: 0px;
	    margin-inline-end: 0px;
	    font-weight: bold;
	    unicode-bidi: isolate;
	    text-decoration: none;
	}
	
	.subtitle11 {
	    position: relative;
	    display: flex;
	    justify-content: center;
	    min-width: 0px;
	    color: rgb(130, 130, 130);
	    text-align: center;
	}
	.login_container{
		position: relative;
	    display: flex;
	    flex-direction: column;
	    margin: 8px 0px 0px;
	    background-color: white;
	    outline-offset: -1px;
	    width: 350px;
	}
	a.policy-link{
		text-decoration: none;
		color: black;
	}
	a.policy-link:hover{
		text-decoration: underline;
		cursor: pointer;
	}
</style>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<section>
<div class="flex_col">
    <div class="login_container">
        <h1 class="big_title">로그인</h1>
        <form action="<%=request.getContextPath() %>/loginEnd.do" method="post" class="my-4">
            <div class="input_box">
                <input type="text" name="userId" id="userId" class="inputTag" value="<%=saveid!=null?saveid:"" %>" placeholder="아이디">
            </div>
            <div class="input_box">
                <input type="password" name="password" id="password" class="inputTag" placeholder="패스워드">
            </div>
            <div class="flex_row">
                <input type="checkbox" name="saveId" id="saveId" class="highlight" <%=saveid!=null?"checked":"" %>>
                <label for="saveId">아이디저장</label>
            </div>
            <button class="login_btn">로그인</button>
        </form>
        <div class="line">
            <hr class="line11" size="1" />
            <p class="highlight11">소셜 로그인</p>
            <hr class="line11" size="1" />
        </div>
        <button class="social_login"><img src="<%=request.getContextPath()%>/image/icon/카카오톡.png" alt="kakao-login" width="15" class="me-1">kakao</button>
        <!-- <h5 class="highlight3_box">
            <span class="highlight3"><span class="highlight3_span0">By clicking login, you agree to our </span><br><a class="policy-link">Terms of Service</a><span class="highlight3_span2"> and </span><a class="policy-link">Privacy Policy</a></span>
        </h5> -->
        <div class="flex_row1">
            <a href='<%=request.getContextPath()%>/member/findid.do' class="subtitle1">아이디 찾기</a>
            <h3 class="subtitle11">|</h3>
            <a href='<%=request.getContextPath()%>/member/findpw.do' class="subtitle1">비밀번호 찾기</a>
            <h3 class="subtitle11">|</h3>
            <a href='<%=request.getContextPath()%>/member/enrollmember.do' class="subtitle1">회원가입</a>
        </div>
    </div>
</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<script>
var contextPath = window.location.pathname.split('/')[1];
var dynamicContextPath = window.location.origin + '/' + contextPath;
	$(document).on("click", "button.social_login", function(e) {
		location.href="https://kauth.kakao.com/oauth/authorize?client_id=483bf9a98ad4edfd5c3b00bb569518bd&redirect_uri=http://14.36.141.71:10079/GDJ79_KUPID_semi/kakao/kakaologinservlet.do&response_type=code";
		
	})
</script>
