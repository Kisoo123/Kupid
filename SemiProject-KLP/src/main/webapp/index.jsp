<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kupid.member.model.dto.MemberDto, com.kupid.group.model.dto.GroupDto, java.util.List, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<style>
 .slider-container {
    width: 50vw; 
    max-width: 598px; 
    overflow: hidden;
    border-radius: 30px;
    margin: 15px auto;
    height: ; /* 높이를 자동으로 */
    display: flex;
    position: relative;
 }
  .slider {
    width: 200%; 
    display: flex;
    transition: transform 0.5s ease;
    flex-direction: row;
    position: relative;
    will-change: transform;
    z-index: 1;
 }
 
 .slide {
     width: 100%;
 	 flex: 0 0 100%; /* 각 슬라이드가 컨테이너의 100% */
 	 display: contents;
 }

 .slide img {
    width: 100%;
    height: auto; /* 이미지의 비율을 유지 */
    object-fit: cover;
    display: block;
    border-radius: 30px;
 }

 .prev, .next {
     position: absolute;
     top: 35%;
     transform: translateY(-50%);
     background-color: rgba(255, 255, 255, 0.5);
     color: #333;
     font-size: 24px;
     padding: 10px;
     cursor: pointer;
     transition: background-color 0.3s ease;
 }

 .prev:hover, .next:hover {
     background-color: rgba(0, 0, 0, 0.5);
     color: #fff;
 }
 
 .prev {
     left: 45px; 
 }

 .next {
     right: 45px; 
 }
 .banner{
 	margin: 35px auto;
 	display: flex;
 	justify-content: center;
 }
 .featurette-divider{
 	margin: 3rem 0;
 }
 .img_outline{
 	outline: 5px #faf0ff solid;
 }
   .favorite1_box {
	  position: relative;
	  margin: 8px 0px 0px;
	  min-width: 0px;
	  width: 40%;
	  border-radius: 8px 8px 8px 8px;
	  padding: 8px 16px 8px 16px;
	  outline: 1px solid #e0e0e0;
	  outline-offset: -1px;
	}
	.searchAritist {
      width: 100%;
      height: 50px;
	  color: black;
	  background-color: transparent;
	  border: 0px;
	  padding: 0px;
	  vertical-align: top;
	  position: realtive;
  }
  .position-relative {
	    position: relative;
	}
	 .searchBox{
	 	background: #fff;
	    border-radius: 14px;
	    box-shadow: 0 5px 20px #00000014;
	    /* display: block; */
	    display: none;
	    position: absolute;
	    z-index: 1000;
	    top: 110%;
	    width: 100%;
/* 	    top: 67%;
	    width: 40%; */
	 }
	 .searchUl{
	 	list-style: none;
	 	max-height: calc(100vh-170px);
	 	padding: 5px 8px;
	 	margin-bottom: 0px;
	 }
	 .searchUl li{
	 	display: flex;
	 	align-self: center;
	 }
	 .searchUl .empty{
	 	display: flex;
	 	justify-content: center;
	 	height: 100px;
	 	align-items: center;
	 	color: gray;
	 }
	 .searchUl li:not(:first-child):before{
	 	background-color: #f0f0f0;
        content: "";
        display: block; 
        height: 1px;
        position: absolute; /* 이 부분 추가 */
    	width: 96%; 
	 }
	 .searchUl a{
	 	color: black;
		text-decoration: none;	
		cursor: pointer; 
		padding: 11px 12px;	
		width: 100%;
	 }
	 .searchUl mark{
	 	background-color: white;
	 	/* font-size: 1.4rem; */
	 	font-size: 17px;
	 	padding: 0px;
	 	font-weight: 700;
	 }
	 .searchUl span{
	 	/* font-size: 1.4rem; */
	 	font-weight: 500;
	 	font-size: 17px;
	 }
	 .searchUl em{
	 	/* font-size: 1.4rem; */
	 	font-style: normal;
	 	font-family: Pretendard, -apple-system, BlinkMacSystemFont, Helvetica, Arial, sans-serif;
	 }
	 h4.group-name{
 		text-overflow: ellipsis;
        overflow: hidden;
        white-space: nowrap;
        width: 100%;
        width: 180px;
     	color: black;
	 }
	 .artist-card image {
         transition: transform 0.3s ease-in-out; /* Smooth transition for the scaling */
         transform-origin: center; /* Ensure the image scales from the center */
         width: 100%;
         height: 100%;
         object-fit: cover;
     }
	 .artist-card:hover image{
	 	transform: scale(1.05);
	 }
	 .artist-card:hover{
	 	cursor: pointer;
	 }
	 .show-more a{
	 	color: purple;
	 }
	 .col-2-4{
	 	flex: 0 0 20%;
        max-width: 20%;
        /* border: 1px solid black; */
        padding: 30px;
	 }
	 img#badge{
	 	width: 50px;
	 	height: auto;
	 	z-index: 999;
	 }
	 span.translate-middle{
	 	/* transform: translate(-66%, -47%) !important; */
	 }
	 .row {
        --bs-gutter-x: 1.5rem;
    	--bs-gutter-y: 0;
	   	/* display: -webkit-inline-box !important; */
	    justify-content: flex-start;
	    flex-wrap: wrap;
        margin-right: calc(-.5* var(--bs-gutter-x));
    	margin-left: calc(2.5* var(--bs-gutter-x)); 
    	display: flex;
	    /* justify-content: flex-start;
	    flex-wrap: wrap;
	    margin-right: calc(-1.5rem);
	    margin-left: calc(2.5rem); */
	}
	.row>*{
	    min-width: 200px;
		width: fit-content;
	}
}
</style>
</head>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	//구독여부 확인
	boolean subscribeCk = false;
	List<GroupDto> groupList = (List<GroupDto>) request.getAttribute("GroupList");
	List<MemberDto> favorite = new ArrayList<>();
	if(loginMember!=null){
		int no = loginMember.getMemberNo();
		//favorite=(List<MemberDto>) session.getAttribute("GroupSubscribe");
		favorite=(List<MemberDto>) request.getAttribute("GroupSubscribe");
		//구독테이블에 등록된 회원번호가 로그인회원의 번호와 일치하면 true
		subscribeCk = favorite.stream().anyMatch(e->e.getMemberNo() == no);
	}
	//인기순 정렬?
	/* List<MemberDto> artist_popular =  */
%>
<body>
<div class="banner">
	<div class="slider-container">
        <div class="slider" id="slider1" style="transform: translateX(0px);">
            <div class="slide"><img src="<%=request.getContextPath() %>/image/banner/banner1.png" alt="Slide 1"></div>
            <div class="slide"><img src="<%=request.getContextPath() %>/image/banner/banner2.png" alt="Slide 2"></div>
            <div class="slide"><img src="<%=request.getContextPath() %>/image/banner/banner3.png" alt="Slide 3"></div>
            <div class="slide"><img src="<%=request.getContextPath() %>/image/banner/banner4.png" alt="Slide 4"></div>
	    </div>
    </div>
        <div class="prev">❮</div>
        <div class="next">❯</div>
</div>
<main class="">
    <div class="container mb-5">
		<div class="row d-flex justify-content-center">
		    <form class="d-flex justify-content-center position-relative" role="search">
			    <input class="searchAritist form-control w-50" type="search" placeholder="아티스트명을 입력해주세요" id="searchArtist">
				<div id="searchResult" class="searchBox w-50"></div>
		    </form>
	    </div>
    </div>
    <!-- 구독 아티스트가 있는 회원 : 해당 아티스트 피드로 연결 -->
    <%if(loginMember != null && subscribeCk){ %>
    <div class="container mb-5">
		<h3 class="main_title">구독 아티스트</h3>
	    <div class="row mt-4 text-center">
    	<%for(int i=0; i<favorite.size();i++){
 			if(favorite.get(i).getMemberNo()==loginMember.getMemberNo()){%>
		      <div class="col-2-4 pb-2 mt-2 mb-4">
		      <a href="<%= request.getContextPath() %>/feed/feedView.do?groupNo=<%=favorite.get(i).getGroupNo() %>" class="artist-card">
		      	<div class="shadow-sm d-inline-grid rounded position-relative">
			        <svg class="bd-placeholder-img rounded-top" width="180" height="180" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect><image href="<%=request.getContextPath()%>/upload/artist/<%=favorite.get(i).getGroupImg() %>" width="100%" preserveAspectRatio="xMidYMid slice"/></svg>
			        <div class="rounded-bottom d-inline-flex" style="background-color: #e0d1f6" width="180px" height="180px">
			        	<h4 class="fw-normal mt-2 group-name"><%= favorite.get(i).getGroupName()%></h4>
		        	</div>
		        </div>
		        </a>
		      </div><!-- /.col-lg-4 -->
	      <%} %>
		<%} %>
      </div>
      </div>
	    <hr class="featurette-divider">
	<%} %>
    
    
    <div class="container mb-5">
		<h3 class="main_title">새로운 아티스트를 만나보세요!</h3>
	    <div class="row mt-4 text-center ">
	 <!-- 구독이 없거나 비회원 : 전체 아티스트 목록 출력 -->
	<%if(loginMember!=null && !subscribeCk || loginMember==null){
		for(int i=0; i<groupList.size();i++){ %>
	    	<div class="col-2-4 pb-2 mt-2 mb-4">
    			<a href="<%= request.getContextPath() %>/artist/artist.do" class="artist-card">
	    		<div class="shadow-sm d-inline-grid rounded position-relative">
			        <svg class="bd-placeholder-img rounded-top" width="180" height="180" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false">
				        <title>Placeholder</title>
				        <rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect>
				        <image href="<%=request.getContextPath()%>/upload/artist/<%=groupList.get(i).getGroupImg() %>" width="100%" height="100%" preserveAspectRatio="xMidYMid slice"/>
			        </svg>
			        <!-- <span class="position-absolute top-50 start-50 translate-middle p-2 rounded-circle">
			       			<button>구독</button>
					  </span> -->
			        <div class="rounded-bottom d-inline-flex" style="background-color: #e0d1f6" width="180px" height="180px">
			        	<h4 class="fw-normal mt-2 group-name"><%= groupList.get(i).getGroupName()%></h4>
					</div>
				</div>
				</a>
	      	</div><!-- /.col-lg-4 -->
		<%}%>
		</div>
		<!-- 구독이 있는 회원 : 구독하지 않은 아티스트만 출력 : 해당 아티스트 페이지로 연결 -->
	<% }else if(loginMember!=null && subscribeCk){ %>
		<%for(int i=0; i<favorite.size();i++){ %>
			<%if(favorite.get(i).getMemberNo()==0){ %>
	    	<div class="col-2-4 pb-2 mt-2 mb-2">
    			<a href="<%= request.getContextPath() %>/artist/artist.do" class="artist-card">
	    		<div class="shadow-sm d-inline-grid rounded position-relative">
			        <svg class="bd-placeholder-img rounded-top" width="180" height="180" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false">
				        <title>Placeholder</title>
				        <rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect>
				        <image href="<%=request.getContextPath()%>/upload/artist/<%=favorite.get(i).getGroupImg() %>" width="100%" height="100%" preserveAspectRatio="xMidYMid slice"/>
			        </svg>
						 <%-- <%if(loginMember !=null && (favorite.get(i).getMemberNo()==loginMember.getMemberNo())){ %>
			        	<span class="position-absolute top-0 start-100 translate-middle p-2 rounded-circle">
			       			<img src="<%= request.getContextPath() %>/image/icon/sendingheart2.png" alt="subscribe" id="badge">
					   </span> 
					   <%} %> --%>
			        <div class="rounded-bottom d-inline-flex" style="background-color: #e0d1f6" width="180px" height="180px">
			        	<h4 class="fw-normal mt-2 group-name"><%= favorite.get(i).getGroupName()%></h4>
					</div>
				</div>
				</a>
	      	</div><!-- /.col-lg-4 -->
			<%} %>
		<%} %>
	<%} %>
		<!-- <a class="icon-link mt-4 ms-4 show-more" href="#">
		  더보기
		  <svg class="bi" aria-hidden="true"><use xlink:href="#arrow-right"></use></svg>
		</a> -->
	</div>
    
    
    
    <!-- 비회원이거나 회원중에 아무도 구독하지 않은 회원 -->
	<%-- <%}else if(loginMember != null && subscribeCk==false){ %>
		<hr class="featurette-divider">
	        <%for(int i=0; i<4;i++){ %>
        	<div class="container mb-5">
				<h3 class="main_title">인기 아티스트</h3>
			    <div class="row mt-4 text-center">
			    	<div class="col-2-4 pb-2 mt-2 mb-2">
			    		<div class="shadow-sm d-inline-grid rounded artist-card">
					        <svg class="mt-4 bd-placeholder-img rounded-top" width="180" height="180" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect><image href="<%=request.getContextPath()%>/upload/artist/<%=groupList.get(i).getGroupImg() %>" width="100%" height="100%" preserveAspectRatio="xMidYMid slice"/></svg>
					        <div class="rounded-bottom d-inline-flex" style="background-color: #f4eefd" width="180px" height="180px">
						        <h4 class="fw-normal mt-2"><%= groupList.get(i).getGroupName()%></h4>
						    </div>
						</div>
			      	</div><!-- /.col-lg-4 -->
				</div>
			</div>
				<%} %> --%>
      
   <!--  <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading fw-normal lh-1">And lastly, this one. <span class="text-body-secondary">Checkmate.</span></h2>
        <p class="lead">And yes, this is the last block of representative placeholder content. Again, not really intended to be actually read, simply here to give you a better view of what this would look like with some actual content. Your content.</p>
      </div>
      <div class="col-md-5">
        <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-bg)"></rect><text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text></svg>
      </div>
	<br>
	</div> -->
    
</main>
</body>
<script>
	document.addEventListener('DOMContentLoaded', () => {
		let currentIndex = 0;
	
	   /*  const sliders = [
	        document.getElementById('slider1'),
	        document.getElementById('slider2')
	    ]; */
	    //슬라이더 한개로 통일
	    const sliders = document.querySelectorAll('.slider');

	    // 첫 번째 슬라이더의 슬라이드 개수를 기준으로 설정
	    const slidesCount = sliders[0].querySelectorAll('.slide').length;
	    
	    const updateSliderPosition = () => {
	       /* 	sliders.forEach(slider => {
	       	        if (currentIndex === slidesCount) {
	       	            slider.style.transform = `translateX(0%)`;
	       	        } else {
	       	            slider.style.transform = `translateX(\${-currentIndex * 100}%)`;
	       	        }
	        }); */
	    	sliders.forEach((slider, index) => {
	            const adjustedIndex = currentIndex % slidesCount;
	            slider.style.transform = `translateX(\${-adjustedIndex * 100}%)`;
	        });
	    };
	
	    const showNextSlide = () => {
	    	currentIndex = (currentIndex + 1) % slidesCount;
	        updateSliderPosition();
	    };
	
	    const showPrevSlide = () => {
	    	currentIndex = (currentIndex - 1 + slidesCount) % slidesCount;
	        updateSliderPosition();
	    };
	
	    document.querySelector('div.next').addEventListener('click', () => {
	        showNextSlide();
	        resetAutoSlide();
	    });
	
	    document.querySelector('div.prev').addEventListener('click', () => {
	        showPrevSlide();
	        resetAutoSlide();
	    });
	
	    let autoSlideInterval = setInterval(()=>showNextSlide(), 5000);
	
	    const resetAutoSlide = () => {
	        clearInterval(autoSlideInterval);
	        autoSlideInterval = setInterval(()=>showNextSlide(), 5000);
	    };
	 	/* // 슬라이드가 마지막에 도달하면 맨 앞으로 이동하는 효과
	    sliders.forEach(slider => {
	        slider.addEventListener('transitionend', () => {
	            if (currentIndex === 0) {
	                slider.style.transition = 'none';
	                currentIndex = slidesCount;
	                slider.style.transform = `translateX(-${slidesCount * 100}%)`;
	            }
	            if (currentIndex === slidesCount - 1) {
	                slider.style.transition = 'none';
	                currentIndex = 1;
	                slider.style.transform = `translateX(-${100}%)`;
	            }
	        });
	    }); */
	});
	
	$('#searchArtist').on('input', e=>{
		let target = e.target.value.toLowerCase();
		target = target.replace(/[^a-zA-Z0-9ㄱ-ㅎ가-힣._!@#$%^&*]/g, '');
		e.target.value = target;
		if(target===""){
			$(".searchBox").css('display','none');
		}else{
			$("#searchResult>ul").remove();
			let groups= [];
			groups=<%=new com.google.gson.Gson().toJson(groupList)%>;
			const $ul = $("<ul>").addClass('searchUl');
			let filterG = groups.filter(g=>g.groupName.toLowerCase().includes(target))
			if(filterG.length>0){
				filterG.map(e=>{
				let lis="";
					lis+=`<li><a href="<%= request.getContextPath() %>/artist/artist.do">
						<svg class="bd-placeholder-img rounded-circle" width="30" height="30" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect><image href="<%=request.getContextPath() %>/upload/artist/\${e.groupImg}" width="100%" height="100%" preserveAspectRatio="xMidYMid slice"/></svg>
						<em>
							<span>\${e.groupName}</span>
						</em>
						</a></li>`
						return lis;
				})
				.forEach(e=>{
					$($ul).append(e);
				});
			}else{
				$("<li>").text('검색 결과가 없습니다.').addClass('empty').appendTo($ul);
			}
			$("#searchResult").append($ul);
			$(".searchBox").css('display','block');
	}
	});
</script>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>