<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List,java.text.SimpleDateFormat,com.kupid.feed.model.dto.Feed,com.kupid.feed.model.service.FeedService,com.kupid.member.model.dto.MemberDto"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<title>Insert title here</title>
</head>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	int memberNo = loginMember.getMemberNo();
%>
<body>
    <div class="sticky-top z-1000 bgcolor-gra">
	    <!-- <div class="artistSelectBox">
		     <select class="artistSelect">
		     	<option value="">전체보기</option>
		     	<option value="아티스트">아티스트보기</option>
		     </select>
	    </div> -->
	    <div class="d-flex justify-content-center gap-4" style="height: 60px">
		    <a class="d-flex align-items-center nav-font" id="nav-a1" href="#"><span class="fs-3 nav-now">POST</span></a>
		    <a class="d-flex align-items-center nav-font"id="nav-a2" href="#"><span class="fs-3">COMMENT</span></a>
	    </div>
    </div>

   	<h2 class="p-3 d-flex justify-content-center mb-5 mt-3" id="title"></h2>
    <div id="result"></div>
    <div class="container" id="container"></div>  
</body>
<style>
.nav-now{
	color: #735a9d;
	text-decoration: underline;
}
.nav-font{
	text-decoration: none;
	color: gray;
	letter-spacing: -1px;
}
.nav-font:hover{
	cursor: pointer;
	color: #735a9d;
}
.bgcolor-gra{
	background-image: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%);
}
.artistSelectBox{
    display: flex;
    justify-content: center;
	margin-left: 640px;
}
.upfileCount{
	    font-size: 14px;
    top: 74px;
    right: 93px;
    text-align: center;
    width: 142px;
    position: absolute;
}
.board-header{
    padding-left: 34px;
    margin-bottom: 19px;
}
.form-cont::-webkit-scrollbar {
   display: none;
 }

.fileIcon{
    width: 28px;
    right: 21px;
    top: 68px;
}
.submitBt{
    width: 28px;
    right: 17px;
    top: 68px;
}
.textAreaIcon{
	position: absolute;
    top: 30px;
    right: 0;
}
.feedContent{
	margin-bottom: 19px;
    margin-top: 19px;
    padding-left: 46px;
}
.dropdown-content{
	right: 12px;
	display : none;
}
.dropdown-content.clicked{
	right: 12px;
	display : block;
}
.more-contianer{
    position: absolute;
    right: 0;
    top: 7px;
}
/* 피드 더보기 아이콘  */
.reportBt{
	
}
.menu-icon {
  width: 30px;
  height: 30px;
  cursor: pointer;
  
    margin-right: 20px;
}

.menu-icon span {
  display: block;
  width: 24px;
  height: 3px;
  background-color: #0000008c;
  margin-bottom: 5px;
  transition: transform 0.3s ease, opacity 0.3s ease;
}

.menu-icon.clicked span:nth-child(1) {
  transform: translateY(8px) rotate(45deg);background-color: black;
}

.menu-icon.clicked span:nth-child(2) {
  opacity: 0;
}

.menu-icon.clicked span:nth-child(3) {
  transform: translateY(-8px) rotate(-45deg);background-color: black;
}

.textarea-container {
    display: flex;
    justify-content: center;
    margin: 10px 0;
}

#content {
    width: 63vw;
}
a.next, a.prev {
    background-color: transparent;
    transition: background-color 0.5s ease;
}

a.next{
    margin-right: 10px;
}

a.prev{
	margin-left: 10px;
}

a.next:hover, a.prev:hover {
    background-color: rgb(0 0 0 / 92%);
}
.comment svg {
    display: flex;
    justify-content: center; /* 수평 중앙 정렬 */
    align-items: center; /* 수직 중앙 정렬 */
}
        
.comment svg path {
    transform: scale(0.2);
}
.comment {
    width: 41px;
    height: 47px;
}
.commentIcon{
	width: 41px;
    height: 47px;
    
    top: 2px;
    left: 9px;
}
        
.form-cont{
  font-size: 18px;
  resize: none;
  color: black;
  border: none;
  border-bottom: 2px solid #0000007e;
  outline: none;
      top: 30px;
    margin-bottom: 54px;
  }

.indicators {
    position: absolute;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
}

.indicator {
    width: 10px;
    height: 10px;
    background-color: black;
    margin: 0 5px;
    border-radius: 50%;
    cursor: pointer;
}

.indicator.active {
    background-color: gray; /* 활성 상태일 때 색상 변경 */
}


*, *:before, *:after {
  position: relative;
  box-sizing: border-box;
}:root {
  --color-bg: #FDF1F2;
  --color-heart: #EA442B;
  --easing: cubic-bezier(.7,0,.3,1);
  --duration: .5s;
}


.likes1 {
  font-size: 47px;
     -moz-appearance: none;
          appearance: none;
  border: none;
  border-radius: 50%;
  background: transparent;
  width: 1em;
  height: 1em;
  padding: 0;
  margin: 0;
  outline: none;
  z-index: 2;
  transition: -webkit-transform var(--duration) var(--easing);
  transition: transform var(--duration) var(--easing);
  transition: transform var(--duration) var(--easing), -webkit-transform var(--duration) var(--easing);
  cursor: pointer;
}
.likes:before {
  z-index: -1;
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border-radius: inherit;
  transition: inherit;
}
.likes:after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #fff;
  border-radius: inherit;
  z-index: -1;
}


@keyframes depress {
  from, to {
            transform: none;
  }
  50% {
            transform: translateY(5%) scale(0.9);
  }
}

@keyframes depress-shadow {
  from, to {
            transform: none;
  }
  50% {
            transform: scale(0.5);
  }
}
.like-wrapper {
  display: grid;
          align-items: center;
          justify-content: center;
  z-index: 1;
}
.like-wrapper > * {
  margin: auto;
  grid-area: 1 / 1;
}

.heart {
  width: .5em;
  height: .5em;
  display: block;
          transform-origin: center 80%;
}
.heart > path {
  stroke: var(--color-heart);
  stroke-width: 1.5;
  fill: transparent;
  transition: fill var(--duration) var(--easing);
}

/* 빨간 하트로 바꿔주기 */
/*  .likes.clicked .heart > path {
  fill: var(--color-heart);
}   */

/* .heart {
          animation: heart-bounce var(--duration) var(--easing);
} */

@keyframes heart-bounce {
  40% {
     transform: scale(0.7);
  }
  0%, 80%, 100% {
     transform: scale(1);
  }
}
.particles {
  width: 1px;
  height: 1px;
}

.particle {
  position: absolute;
  top: 0;
  left: 0;
  height: .1em;
  width: .1em;
  border-radius: .05em;
  background-color: var(--color);
  --percentage: calc( var(--i) / var(--total-particles) );
  --Θ: calc( var(--percentage) * 1turn );
          transform: translate(-50%, -50%) rotate(var(--Θ)) translateY(0) scaleY(0);
  transition: all var(--duration) var(--easing);
}
/* .likes1:focus .particle {
          animation: particles-out calc(var(--duration) * 1.2) var(--easing) forwards;
} */

@keyframes particles-out {
  50% {
    height: .3em;
  }
  50%, 60% {
    height: .3em;
    transform: translate(-50%, -50%) rotate(var(--Θ)) translateY(0.8em) scale(1);
  }
  60% {
    height: .2em;
  }
  100% {
    transform: translate(-50%, -50%) rotate(var(--Θ)) translateY(1em) scale(0);
  }
}
.ripple {
  height: 1em;
  width: 1em;
  border-radius: 50%;
  overflow: hidden;
  z-index: 1;
}
.ripple:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border: 0.4em solid var(--color-heart);
  border-radius: inherit;
  -webkit-transform: scale(0);
          transform: scale(0);
}
.likes:focus .ripple:before {
          animation: ripple-out var(--duration) var(--easing);
}

@keyframes ripple-out {
  from {
    -webkit-transform: scale(0);
            transform: scale(0);
  }
  to {
    -webkit-transform: scale(5);
            transform: scale(5);
  }
}

/* 다시 클릭하면 원래대로 */
.likes:focus {
  pointer-events: none;
  cursor: normal;
}

.img_list {
    position: relative;
    width: 500px;
    overflow: hidden;
    justify-content: center;
    align-items: center;
    left: 150px;
}

.img_listBt {
    display: flex;
    transition: transform 0.5s ease-in-out;
}

.img_listBt > div {
    min-width: 500px;
}

.slider_btn {
    position: absolute;
    top: 50%;
    width: 100%;
    display: flex;
    justify-content: space-between;
    transform: translateY(-50%);
}

.slider_btn a {
    background-color: rgba(0,0,0,0.5);
    color: white;
    padding: 10px;
    text-decoration: none;
    cursor: pointer;
    opacity: 0.6;
    
}

.board-header {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-top: 13px;
    
}

.board-footer {
    display: flex;
    justify-content: left;
    margin-top: 10px;
}

.textArea {
    background-color: #0000;
    border: none;
    color: #111;
    font-size: 16px;
    line-height: 22px;
    resize: none;
    vertical-align: top;
    height: 24px !important;
    width: 80%;
    margin-left: -90px;
}
div#textareadiv:hover{
	background-color: #dadcdd !important;
}
</style>
<script>


const container = document.getElementById("container");
$(container).css({"display": "flex", "flex-direction": "column", "justify-content": "center", "align-items": "center"});
let page = 1; 
const perPage = 10;
let time = true;

document.addEventListener("DOMContentLoaded", function() {
    loadPage();
    let nomore = false;
    window.addEventListener("scroll", function() {
        const scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        const windowHeight = window.innerHeight;
        const scrollHeight = document.documentElement.scrollHeight;
        if ((scrollTop + windowHeight >= scrollHeight - 150) && (nomore==false)) {
            if (time) {
                time = false;
                loadPage();
				console.log('실행');
            } 
        }
    });
});
//피드 페이지 불러오기
const loadPage = () => {
	$("#title").text('내가 쓴 글');
    $.ajax({
        type: "POST",
        url: "<%=request.getContextPath()%>/mypage/InfiniteScroll.do",
        data: {
            "cPage": page,
            "numPerPage": perPage,
            "memberNo" : "<%=memberNo%>"
        },
        success: function(data) {
        	console.dir(data);
        	if(data.length != 0){
        		//여기 function 함수 따로 빼기
	            $.each(data, function(idx, element) {
	                    const $div = $("<div>").css({
	                        "border-bottom": "1px solid #aba7a7",
	                        "width": "800px",
	                        'overflow': 'hidden',
	                        'padding-bottom': '10px'
	                    }).attr("class", "board");
	
	                    $div.append('<input type="hidden" class="feedNo" value=' + element.feedNo + '>');
	                    $div.append('<input type="hidden" class="memberNo" value=' + element.feedMemberName + '>');
	
	                    const $header = $('<div>').addClass('board-header');
	                    const $moreContainer = $('<div>').addClass('more-contianer');
	                    $header.append('<div><img style="border-radius:50%; height:32px; width:32px;" src="<%=request.getContextPath()%>/upload/member/profile/' + element.profileImgOriname + '"></div>');
	                    $header.append('<div>' + element.feedWriterName + '</div>');
	                    if (element.feedUpdateDate == undefined) {
	                        $header.append('<div>' + element.feedWriteDate + '</div>');
	                    } else {
	                        $header.append('<div>' + element.feedUpdateDate + '</div>');
	                    }
	                    $header.append('<div class="commentsCnt">' + "댓글수" +element.commentCnt + '</div>');
	                    $header.append('<div class="likesCnt">' + "좋아요"+element.likes + '</div>');
	                    $moreContainer.append('<div class="menu-icon">'+'<span></span>'+'<span></span>'+'<span></span>'+'</div>');
	                    $moreContainer.append('<div class="dropdown-content"><button class="feedUpdateBt">수정</button></div>');
	                    $moreContainer.append('<div class="dropdown-content"><button class="feedDeleteBt" style="z-index:1">삭제</button></div>');
	                    $header.append($moreContainer);
	
	                    $div.append($header);
	
	                    $div.append('<div class="feedContent">' + element.feedContent + '</div>');
	
	                    if (element.filePath !== undefined) {
	                        const fileArr = element.filePath.split(",");
	                        const img_list = $('<div>').attr('class', 'img_list');
	                        $div.append(img_list);
	                        const img_listBt = $('<div>').attr('class', 'img_listBt');
	                        img_list.append(img_listBt);
	
	                        for (let i = 0; i < fileArr.length; i++) {
	                            if (fileArr[i] !== "/SemiProject-KLP/upload/feed/null") {
	                                img_listBt.append($('<div>').html($('<img>').attr({
	                                    'src': "<%=request.getContextPath()%>/upload/feed/" + fileArr[i],
	                                    'width': '500px',
	                                    'height': '500px'
	                                })));
	                            }
	                        }
	
	                        const slider_btn = $('<div>').attr('class', 'slider_btn');
	                        slider_btn.append($('<a>').html('<').attr('class', 'prev'));
	                        slider_btn.append($('<a>').html('>').attr('class', 'next'));
	                        img_list.append(slider_btn);
	
	                        initializeCarousel(img_list);
	                    }
	
	                    const $footer = $('<div>').addClass('board-footer ms-4');
	
	                    checkLikes(element.feedNo, function(data) {
	                        if (data == "true") {
	                            $footer.append(
	                                '<button class="likes1 clicked">' +
	                                '<div class="like-wrapper">' +
	                                '<div class="ripple"></div>' +
	                                '<svg class="heart" width="24" height="24" viewBox="0 0 24 24">' +
	                                '<path style="fill:var(--color-heart)" d="M12,21.35L10.55,20.03C5.4,15.36 2,12.27 2,8.5C2,5.41 4.42,3 7.5,3C9.24,3 10.91,3.81 12,5.08C13.09,3.81 14.76,3 16.5,3C19.58,3 22,5.41 22,8.5C22,12.27 18.6,15.36 13.45,20.03L12,21.35Z"></path>' +
	                                '</svg>' +
	                                '<div class="particles" style="--total-particles: 6">' +
	                                '<div class="particle" style="--i: 1; --color: #7642F0"></div>' +
	                                '<div class="particle" style="--i: 2; --color: #AFD27F"></div>' +
	                                '<div class="particle" style="--i: 3; --color: #DE8F4F"></div>' +
	                                '<div class="particle" style="--i: 4; --color: #D0516B"></div>' +
	                                '<div class="particle" style="--i: 5; --color: #5686F2"></div>' +
	                                '<div class="particle" style="--i: 6; --color: #D53EF3"></div>' +
	                                '</div>' +
	                                '</div>' +
	                                '</button>'
	                            );
	
	                            console.log(data);
	                        } else if (data == "false") {
	                            $footer.append(
	                                '<button class="likes1">' +
	                                '<div class="like-wrapper">' +
	                                '<div class="ripple"></div>' +
	                                '<svg class="heart" viewBox="0 0 24 24">' +
	                                '<path d="M12,21.35L10.55,20.03C5.4,15.36 2,12.27 2,8.5C2,5.41 4.42,3 7.5,3C9.24,3 10.91,3.81 12,5.08C13.09,3.81 14.76,3 16.5,3C19.58,3 22,5.41 22,8.5C22,12.27 18.6,15.36 13.45,20.03L12,21.35Z"></path>' +
	                                '</svg>' +
	                                '<div class="particles" style="--total-particles: 6">' +
	                                '<div class="particle" style="--i: 1; --color: #7642F0"></div>' +
	                                '<div class="particle" style="--i: 2; --color: #AFD27F"></div>' +
	                                '<div class="particle" style="--i: 3; --color: #DE8F4F"></div>' +
	                                '<div class="particle" style="--i: 4; --color: #D0516B"></div>' +
	                                '<div class="particle" style="--i: 5; --color: #5686F2"></div>' +
	                                '<div class="particle" style="--i: 6; --color: #D53EF3"></div>' +
	                                '</div>' +
	                                '</div>' +
	                                '</button>'
	                            );
	                        }
	                    });
	
	                    $footer.append(
	                        '<a class="comment">' +
	                        '<svg viewBox="0 -13 35 55" class="commentIcon">' +
	                        '<path d="M61.44,0a61.46,61.46,0,0,1,54.91,89l6.44,25.74a5.83,5.83,0,0,1-7.25,7L91.62,115A61.43,61.43,0,1,1,61.44,0ZM96.63,26.25a49.78,49.78,0,1,0-9,77.52A5.83,5.83,0,0,1,92.4,103L109,107.77l-4.5-18a5.86,5.86,0,0,1,.51-4.34,49.06,49.06,0,0,0,4.62-11.58,50,50,0,0,0-13-47.62Z"/>' +
	                        '</svg>' +
	                        '</a>'
	                    );
	                    $div.append($footer);
	
	                    $('.likes1.clicked .heart > path').css('fill', 'var(--color-heart)');
	
	                    $(container).append($div);
	            
	            });
	            time = true;
	            page++;
        	}else{
        		nomore = true;
        	}
        }
    });
};


//피드 수정
$(document).on("click", "button.feedUpdateBt", function(e) {
    const feedNo = $(e.target).closest('.board').find('.feedNo').val();
    const $feedContent = $(e.target).closest('.board').find('.feedContent');
    const feedContentContainer = $feedContent.text();

    const $textarea = $("<textarea>").val(feedContentContainer);

    const $cancelButton = $("<button>").text("취소").addClass("cancelUpdate");
    const $confirmButton = $("<button>").text("확인").addClass("confirmUpdate");

    const $container = $("<div>").append($textarea, $cancelButton, $confirmButton);

    $feedContent.replaceWith($container);

    $cancelButton.click(function() {
        $container.replaceWith($feedContent);
    });
    $confirmButton.click(function() {
    	console.log("Dsadsa");
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/feed/feedupdate.do",
            data: {
                "feedNo": feedNo,
                "feedContent": $textarea.val()
            },
            success: function(data) {
            	 // 서버 응답 데이터 확인
                console.log('Update success:', data);
                history.go(0);
            },
            error: function(xhr, status, error) {
                console.error('Error updating comment:', error);
            }
        });
    });
});
//신고버튼 클릭
$(document).on("click", "button.reportBt", function(e) {
    const $button = $(e.target);
    const $board = $button.closest('.board');
    const $feedNo = $board.find('.feedNo');
    const feedNoText = $feedNo.val();

    let url = '<%=request.getContextPath()%>/feed/feedreportview.do?feedno=' + feedNoText;
    let popup = window.open(url, "popup", "width=400, height=300, left=100, top=50");
});

//삭제버튼 클릭 
$(document).on("click", "button.feedDeleteBt", function(e) {
    const $button = $(e.target);
    const $board = $button.closest('.board');
    const $feedNo = $board.find('.feedNo');
    const feedNoText = $feedNo.val();
    console.log("ssss");
    if(confirm("게시글을 삭제하시겠습니까?")){
    	
	    $.ajax({
	    	type:"POST",
	    	url:"<%=request.getContextPath()%>/feed/feeddelete.do",
	    	data: {
	    		"feedNo":feedNoText
	    		},
	    	async: false, // 동기처리하면 어케되나 궁금해서 해봄
	    	success:function(data){	    	
	    			console.log(data+"삭제 데이터");
	    			if(data>0){	    				
		    			alert("삭제되었습니다.");
	    			}else{
	    				alert("삭제실패.");
	    			}
	                history.go(0);

	    		}
	    	,
    		 error: function(xhr, status, error) {
	    	            console.error('Error submitting comment:', error);
	    	        }
	    	
	    })
	    
    }
});

//댓글 버튼 클릭  
$(document).on("click", "a.comment", function(e) {
    const $footer = $(e.target).closest('.board-footer');
    const existingDiv = $footer.next('.comment-container');
    const feedNoText = $(e.target).closest('.board').find('input.feedNo').val();

    if (existingDiv.length === 0) { // 기존에 댓글 입력창이 없으면 새로 생성
        const newDiv = $("<div>").addClass('comment-container')
        ;
    	//css추가
        const commentBt = $("<button>").css('margin-right', '-110px').addClass('btn').html('<img width="25px" src="<%=request.getContextPath()%>/image/icon/submission-3-48.png">');
        //댓글 작성창 css추가
        const innerDiv = $("<div>").addClass('d-flex align-items-center justify-content-center my-3 gap-5 bg-body-secondary rounded-4 py-1').attr('id','textareadiv');
        //style css 변경 및 placeholder추가
        const textArea = $("<textarea>").addClass('textArea').attr('placeholder','댓글을 입력하세요.');

	    newDiv.append($("<hr>"));
        innerDiv.append(textArea);
        innerDiv.append(commentBt);
        newDiv.append(innerDiv);
        $footer.after(newDiv);
        selectComment(feedNoText);
        
    } else { // 기존에 댓글 입력창이 있으면 토글
        existingDiv.toggle();
    }
});


// 댓글 작성 버튼 클릭
$(document).on("click", "button.commentBt", function(e) {
    const $button = $(e.target);
    const $board = $button.closest('.board');
    const $feedNo = $board.find('.feedNo');
    const $textArea = $button.closest('div').find('textarea');

    let commentText = $textArea.val().trim();
    const feedNoText = $feedNo.val();

    if (commentText !== undefined && commentText !== "") {
        ajaxComment(commentText, feedNoText, e);
        $textArea.val("");
    } else {
        alert("내용을 입력하세요.");
    }
});

const ajaxComment = (commentText, feedNoText, e) => {
    $.ajax({
        type: "POST",
        url: "<%=request.getContextPath()%>/feed/feedcomment.do",
        data: {
            "loginMember": "<%=loginMember.getMemberNo()%>",
            "commentText": commentText,
            "feedNoText": feedNoText
        },
        success: function(data) {
            selectComment(feedNoText);
        	$(e.target).closest('.board').find('.commentsCnt').text("댓글수"+data);
        },
        error: function(xhr, status, error) {
            console.error('Error submitting comment:', error);
        }
    });
};

// 해당 피드 댓글 목록 불러오기 
const selectComment = (feedNoText) => {
    $.ajax({
        type: "POST",
        url: "<%=request.getContextPath()%>/feed/selectcomment.do",
        data: {
            "feedNo": feedNoText
        },
        success: function(data) {
            const $commentContainer = $('input.feedNo[value="' + feedNoText + '"]').closest('.board').find('.comment-container')
            							.addClass('pt-2 px-5');
         
            $commentContainer.find('.comment-item').remove();

            $.each(data, function(idx, element) {
            	//기존 댓글창 css 추가
                const $commentDiv = $("<div>").css({
                    "border-bottom": "1px solid #aba7a7",
                    "width": "700px",
                    'overflow': 'hidden',
                    'padding-bottom': '10px'
                }).addClass("comment-item");
                const $commentInfoContainer = $("<div>").css({
                    "display": "flex",
                    "align-items": "center"
                }).addClass("commentInfo");
			
                $commentDiv.append('<input type="hidden" class="replyNumber" value=' + element.replyNumber + '>');
                $commentDiv.append('<input type="hidden" class="feedNo" value=' + element.feedNo + '>');
                $commentDiv.append('<input type="hidden" class="likes" value=' + element.likes + '>');
                $commentDiv.append('<input type="hidden" class="memberNo" value=' + element.memberNo + '>');                
                $commentInfoContainer.append('<div style="margin-right: 10px;"><img style="border-radius:50%; height:32px; width:32px;" src="<%=request.getContextPath()%>/upload/member/profile/' + element.profileImgOriname + '"></div>');
                $commentInfoContainer.append('<div style="margin-right: 10px;">' + element.memberId + '</div>');
                $commentInfoContainer.append('<div>' + element.replyDate + '</div>');
                $commentDiv.append($commentInfoContainer);
                //css추가
                $commentDiv.append('<div class="replyContent py-2 mx-2">' + element.replyContent + '</div>');
				
                if (<%=loginMember.getMemberNo()%> == element.memberNo) {
                	//css 추가
                    $commentDiv.append('<button class="updateBt btn btn-sm">수정</button>');
                    $commentDiv.append('<button class="deleteBt btn btn-sm">삭제</button>');
                }
                
                $commentContainer.append($commentDiv);
            });
            $commentContainer.find('.comment-item').last().css('border-bottom', 'none');
        },
        error: function(xhr, status, error) {
            console.error('Error fetching comments:', error);
        }
    });
};

// 댓글 삭제 기능
$(document).on("click", "a.deleteBt", function(e) {    
    const replyNumber = $(e.target).parent().find('.replyNumber').val();
    const feedNo = $(e.target).parent().find('.feedNo').val();
	if(confirm("삭제하시겠습니까?")){
	    $.ajax({
	        type: "POST",
	        url: "<%=request.getContextPath()%>/feed/feedcommentdelete.do",
	        data: {
	            "replyNumber": replyNumber
	        },
	        success: function(data) {
	            selectComment(feedNo);
	            alert("삭제되었습니다.");
	        }
	    });
	}
});

// 댓글 수정 기능
$(document).on("click", "a.updateBt", function(e) {
    const $parent = $(e.target).parent();
    const replyNumber = $parent.find('.replyNumber').val();
    const feedNo = $parent.find('.feedNo').val();
    const $feedReply = $parent.find('div.replyContent');
    const feedReplyContent = $feedReply.text();

    const $textarea = $("<textarea>").val(feedReplyContent);

    const $cancelButton = $("<button>").text("취소").addClass("cancelUpdate");
    const $confirmButton = $("<button>").text("확인").addClass("confirmUpdate");

    const $container = $("<div>").append($textarea, $cancelButton, $confirmButton);

    $feedReply.replaceWith($container);

    $cancelButton.click(function() {
        $container.replaceWith($feedReply);
    });
    $confirmButton.click(function() {
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/feed/feedcommentupdate.do",
            data: {
                "replyNumber": replyNumber,
                "replyContent": $textarea.val()
            },
            success: function(data) {
                selectComment(feedNo);
            },
            error: function(xhr, status, error) {
                console.error('Error updating comment:', error);
            }
        });
    });
});

//캐러셀 기능 
const initializeCarousel = (carousel) => {
    const imgListBt = carousel.find('.img_listBt');
    const slides = imgListBt.children();
    const totalSlides = slides.length;
    let index = 0;

    const indicators = $('<div class="indicators"></div>');
    for (let i = 0; i < totalSlides; i++) {
        const indicatorClass = i === 0 ? ' active' : '';
        indicators.append('<span class="indicator' + indicatorClass + '" data-index="' + i + '"></span>');
    }
    carousel.append(indicators);
    
    const showSlide = (idx) => {
        const slideWidth = slides.first().outerWidth();
        if (idx >= totalSlides) idx = idx-1;
        if (idx < 0) idx = idx+1;
        imgListBt.css('transform', 'translateX(' + (-idx * slideWidth) + 'px)');
        index = idx;

        updateIndicators();
    }
    
    const updateIndicators = () => {
        carousel.find('.indicator').removeClass('active').eq(index).addClass('active');
    }

    carousel.on('click', '.indicator', function() {
    const idx = $(this).data('index');
    showSlide(idx);
});


    carousel.find('.prev').click(() => {
        showSlide(index - 1);
    });

    carousel.find('.next').click(() => {
        showSlide(index + 1);
    });

    showSlide(index);
}

let heartTime = true;
// 좋아요 기능
function switchingLikes(feedNo, e) {
    $.ajax({
        type: "POST",
        url: "<%=request.getContextPath()%>/feed/likesswitch.do",
        data: {
            "memberNo": '<%=loginMember.getMemberNo()%>',
            "feedNo": feedNo
        },
        success: function(data) {
        	console.log(data);
        	$(e.target).closest('.board').find('.likesCnt').text("좋아요"+data);
        }
    });
}



//해당 피드 페이지에 해당 유저의 좋아요가 있는지 확인
const checkLikes = (feedNo, callback) => {
    $.ajax({
        type: "POST",
        url: "<%=request.getContextPath()%>/feed/checklikes.do",
        data: {
            "loginMemberNo": <%=loginMember.getMemberNo()%>,
            "feedNo": feedNo
        },
        success: function(data) {
            callback(data);
        },
        error: function(xhr, status, error) {
            console.error('Error checking likes:', error);
            callback(false); // 에러 발생 시 false를 전달할 수 있습니다.
        }
    });
}

//클릭하면 좋아요 함수 실행
$(document).on('click', '.likes1', function(e) {
	const feedNo = $(this).closest('.board').find('input.feedNo').val();
	if(heartTime){
		heartTime= false;
    	switchingLikes(feedNo, e); 
    	heartAni($(this));
	}
});



// 클릭하면 .heart의 css를 바꿈
function heartAni(e) {
	const $heartPath = $(e).find('.heart > path');
	const $heart = $(e).find('.heart');
	const $particles = $(e).find('.particle');
    const $container = $(e);
    if ($container.hasClass('clicked')) {
        $heartPath.css('fill', '');
        $particles.css('animation', '');
        $heart.css('animation', '');
        $container.removeClass('clicked');
    } else {
        $heartPath.css('fill', 'var(--color-heart)');
        $particles.css('animation', 'particles-out calc(var(--duration) * 1.2) var(--easing) forwards');
        $heart.css('animation', 'heart-bounce var(--duration) var(--easing)');

        $container.addClass('clicked');
    } 
    heartTime=true;
}

//클릭하면 댓글 아이콘 색 변경
$(document).on('click', '.comment', function(e) {
    const $commentIconPath = $(this).find('path');
    const $container = $(this);
    if ($container.hasClass('clicked')) {
        // 클릭되었을 때
        $commentIconPath.css('transition', 'fill 0.5s ease');
        $commentIconPath.css('fill', '');
        $container.removeClass('clicked');
    } else {
        // 클릭되지 않았을 때
        setTimeout(function() {
            $commentIconPath.css('transition', 'fill 0.5s ease');
            $commentIconPath.css('fill', 'var(--color-heart)');
            $container.addClass('clicked');
        }, 10);
    } 
});

$(document).on("click", ".menu-icon", function() {
    $(this).toggleClass('clicked'); 
    $(this).parent().find('.dropdown-content').toggleClass('clicked'); 
});

//파일 선택시 선택한 갯수 표시

$('#upfile').on('change', function() {
    if (this.files.length > 0) {
        const numberOfFiles = this.files.length;
        $(this).parent().find('.upfileCount').text("선택한 파일 : "+numberOfFiles+"개");
    }
});
</script>
</html>
    