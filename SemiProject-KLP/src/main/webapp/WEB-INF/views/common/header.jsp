<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kupid.member.model.dto.MemberDto" %>
<head>
<meta charset="UTF-8">
<link rel="icon" sizes="32x32" href="<%=request.getContextPath() %>/image/favicon/faviconKUPID.png" type="image/png">
<title>KUPID</title>
<!-- 제이쿼리 -->
<script src = "<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<!-- 부트스트랩 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<% 
	MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
	Cookie[] cookies = request.getCookies();
	String saveid = null;
	if(cookies!=null){
		for(Cookie c : cookies) {
			//쿠키에 저장된 네임과 밸류 가져오기
			if(c.getName().equals("saveid")){
				saveid = c.getValue();
			}
		}
	}
	String servletHistory=request.getParameter("return");
	int pagenum = 0;
	if((Integer) request.getAttribute("pagenum")!=null){
		pagenum=(Integer) request.getAttribute("pagenum");
	}
%>
<style>
	/* 폰트 */
	@font-face {
	     font-family: 'DungGeunMo';
	     src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/DungGeunMo.woff') format('woff');
	     font-weight: normal;
	     font-style: normal;
	}
	.header_icon{
		display: flex;
		width: 37px;
	}
	.header_icon_bell{
		display: flex;
		width: 33px;
	}
	.header_icon_gift{
		display: flex;
		width: 40px;
	}
	.header_icon_pin{
		display: flex;
		width: 34px;
	}
	.header_logo{
		display: flex;
		width: 130px;
	}
	body{
     font-family: 'DungGeunMo';
     src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/DungGeunMo.woff') format('woff');
     font-weight: normal;
     font-style: normal;
     letter-spacing: -1.36px;
     font-size: 1.1rem;
	}
	.dropdown-item.active, .dropdown-item:active{
		background-color: #d1ade3;
	}
	.btn:hover{
		background-color: #d1ade3;
		box-shadow: 0 1rem 3rem rgba(black, .175);
	}
</style>
<header class="p-2 border-bottom d-flex">
      <div class="d-flex flex-wrap align-items-center w-100">
        <a class="d-flex align-items-center mb-2 ms-2 mb-lg-0 link-body-emphasis text-decoration-none" href="<%=request.getContextPath() %>/">
        <img src="<%=request.getContextPath() %>/image/logo/KUPID.png" id="logo" alt="logo" class="header_logo">
          <!--작은 로고 <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg> -->
        </a>
        <div class="d-flex ms-auto align-items-center">
	        <ul class="nav col-lg-auto mb-2 mb-md-0 me-5 align-items-center" style="gap: 7px">
	          <li><a href="#" class="nav-link px-2 link-body-emphasis"><img src="<%=request.getContextPath()%>/image/icon/bell.png" alt="alert" class="header_icon_bell"></a></li>
	          <li><a href="#" class="nav-link px-2 link-secondary"><img src="<%=request.getContextPath()%>/image/icon/pin.png" alt="notice" class="header_icon_pin"></a></li>
	          <li><a href="<%=request.getContextPath()%>/customer/customermain.do" class="nav-link px-2 link-body-emphasis"><img src="<%=request.getContextPath()%>/image/icon/question.png" class="header_icon" alt="customer"></a></li>
	          <li><a href="#" class="nav-link px-2 link-body-emphasis"><img src="<%=request.getContextPath()%>/image/icon/gift.png" alt="store" class="header_icon_gift"></a></li>
	        </ul>
        <!-- <div class="d-flex col-lg-auto mb-3 mb-lg-0 me-lg-3 align-items-center" role="search">
          <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
        </div> -->

        <div class="dropdown text-end me-4">
	     <% if(loginMember == null){ %>
          <a href="<%=request.getContextPath()%>/login.do" class="d-block link-body-emphasis text-decoration-none me-2" aria-expanded="false">
            <img src="<%=request.getContextPath()%>/image/icon/profile.png" alt="mdo" width="32" height="32" class="rounded-circle">
          </a>
	     <%}else{ %>
	          <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle me-2 " data-bs-toggle="dropdown" aria-expanded="false">
	            <img src="<%=request.getContextPath()%>/upload/member/profile/<%=loginMember.getProfileImgOriname() %>" alt="mdo" width="32" height="32" class="rounded-circle mb-1">
				<%=loginMember.getMemberName()%>
	          </a>
	          <ul class="dropdown-menu text-small">
	          	<%if(loginMember.getMemberId().equals("admin")){%>
	          		<li><a class="dropdown-item" href="<%=request.getContextPath()%>/manager/home.do">관리자페이지</a></li>
	            	<li><hr class="dropdown-divider"></li>
	          	<%} %>
	            <li class="ms-3 my-1 fs-5">My KUPID</li>
	            <li><a class="dropdown-item <%=pagenum==1?"active":""%>" href="<%=request.getContextPath()%>/mypage/myprofile.do?pagenum=1">내 프로필</a></li>
	            <li><a class="dropdown-item <%=pagenum==2?"active":""%>" href="<%=request.getContextPath()%>/mypage/myinfo.do?pagenum=2">내 정보</a></li>
	            <li><a class="dropdown-item <%=pagenum==3?"active":""%>" href="<%=request.getContextPath()%>/mypage/myprofile.do?pagenum=3">내 활동</a></li>
	            <%-- <li><a class="dropdown-item" href="<%=request.getContextPath()%>/mypage/myactivity.do?pagenum=4">내 문의</a></li> --%>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/logout.do">로그아웃</a></li>
	          </ul>
	     <%} %>
        </div>
      </div>
    </div>
  </header>
  <script>
  	
  </script>