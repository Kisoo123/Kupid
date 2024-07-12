<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kupid.member.model.dto.MemberDto, java.util.List" %>
<% 
	List<MemberDto> profile = (List<MemberDto>) request.getAttribute("memberProfile");
	 MemberDto m = profile.get(0); 
/* 	MemberDto m =(MemberDto) session.getAttribute("loginMember"); */
	String favorite = "";
	
	for(int i=0; i<profile.size(); i++){
		favorite += profile.get(i).getGroupName();
		if(i!=profile.size()-1){
			favorite += ", ";
		}
	}
	String src = "";
	if(m.getProfileImgOriname().equals("기본프로필.png")){
		src=request.getContextPath()+"/image/member/"+m.getProfileImgOriname();
	}else{
		src=request.getContextPath()+"/upload/member/profile/"+m.getProfileImgOriname();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<style>
.myprofile-container{
	display: flex;
	height: 100vh;
	justify-content: left;
}
.myProfile{
	margin: 0px auto;
}
.main {
  margin: 0px auto;
  position: relative;
  display: flex;
  flex-direction: column;
  background-color: white;
}
.myProfile .section1 {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.myProfile .flex_col {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0px auto;
  width: 100%;
  padding: 30px 70px 30px 70px;
}
.myProfile .title {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  color: black;
  text-align: center;
  letter-spacing: -0.36px;
}
.myProfile .content-container{
	padding: 15px;
}
.myProfile .flex_row {
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 0px 30px;
  margin: 71px auto 65px;
  width: 84.97%;
}
.myProfile .flex_col1 {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 25px 0px;
  width: 297px;
  min-width: 0px;
}

.myProfile .profile_img_container {
  border-radius: 148.5px 148.5px 148.5px 148.5px;
  border: 3px #faf0ff solid;
  width: 200px; /* 이미지 크기를 유지하기 위한 컨테이너 크기 */
  height: 200px; /* 이미지 크기를 유지하기 위한 컨테이너 크기 */
  overflow: hidden;
  position: relative;
  display: flex;
  justify-content: center; /* 수평 중앙 정렬 */
  align-items: center; /* 수직 중앙 정렬 */
}
.myProfile .profile_img {
	width: 100%;
	height: auto;
}
.myProfile .profile_img_default {
	width: 60%;
	height: auto;
}
.myProfile .btn_container{
	display: flex;
	justify-content: space-between;
 	gap: 10px;
}
.myProfile .btn {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0px auto;
  width: 83px;
  max-width: 100%;
  color: black;
  text-align: center;
  background-color: #d1ade3;
  border-radius: 8px 8px 8px 8px;
  padding: 8px 8px 8px 8px;
  letter-spacing: -0.9px;
}
.myProfile .btn2 {
  background-color: #dcdcdc;
}
.myProfile .flex_col2 {
  position: relative;
  display: flex;
  flex-direction: column;
  width: 318px;
  min-width: 0px;
}
.myProfile .nickname-container{
	display: flex;
}
.myProfile #nicknameResult{
	margin-left: 10px;
}
.myProfile .nickname {
  position: relative;
  color: black;
}
.myProfile .content_box {
  position: relative;
  display: flex;
  flex-direction: column;
  margin: 8px 0px 0px;
  background-color: white;
  border-radius: 8px 8px 8px 8px;
  outline: 1px solid #e0e0e0;
  outline-offset: -1px;
  width: 100%;
  min-width: 700px;
}
.myProfile .highlight_box {
  position: relative;
  margin: 8px 0px 8px 16px;
  width: 45px;
}
.myProfile .highlight {
  width: 100%;
  color: black;
  background-color: transparent;
  border: 0px;
  padding: 0px;
  letter-spacing: -1px;
  vertical-align: top;
}
.myProfile .highlight:focus {
  outline: none;
}
.myProfile .introduce {
  position: relative;
  margin: 32px 0px 0px;
  color: black;
}
.myProfile .textarea_box {
  position: relative;
  margin: 8px 0px 0px;
  border-radius: 8px 8px 8px 8px;
  padding: 8px 16px 123px 16px;
  outline: 1px solid #e0e0e0;
  outline-offset: -1px;
}
.myProfile .textarea {
  width: 100%;
  color: black;
  background-color: transparent;
  border: 0px;
  padding: 0px;
  letter-spacing: -0.8px;
  vertical-align: top;
}
.myProfile .textarea:focus {
  outline: none;
}
.myProfile .favorite {
  position: relative;
  margin: 32px 0px 0px;
  color: black;
}
.myProfile .favorite1_box {
  position: relative;
  margin: 8px 0px 20px;
  min-width: 0px;
  border-radius: 8px 8px 8px 8px;
  padding: 8px 16px 8px 16px;
  outline: 1px solid #e0e0e0;
  outline-offset: -1px;
}
.myProfile .favorite1 {
  width: 100%;
  color: black;
  background-color: transparent;
  border: 0px;
  padding: 0px;
  vertical-align: top;
}
.myProfile .favorite1:focus {
  outline: none;
}
.myProfile .btn1 {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 40px auto 0px;
  width: 62px;
  max-width: 100%;
  color: black;
  text-align: center;
  background-color: #d1ade3;
  border-radius: 8px 8px 8px 8px;
  padding: 8px 16px 8px 16px;
  letter-spacing: -1px;
}
.myProfile .flex_row1 {
  position: relative;
  display: flex;
  gap: 0px 13px;
  margin: 84px 0px 0px;
  width: 37.09%;
}
.myProfile .highlight2 {
  position: relative;
  display: flex;
  justify-content: center;
  min-width: 0px;
  color: #828282;
  text-align: center;
}
.myProfile .readonly_box{
	background-color: #ededed;
}
</style>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="myprofile-container">
    <main class="main">
        <section class="myProfile flex-fill d-flex align-items-center">
            <div class="flex_col flex-fill">
                <h2 class="title">내 프로필</h2>
                <div class="content_box shadow-sm">
                    <div class="flex_row">
                        <div class="flex_col1">
                        	<div class="profile_img_container">
                            	<img class='<%=(m.getProfileImgOriname().equals("기본프로필.png"))?"profile_img_default":"profile_img" %>' src="<%=src %>" id="profile_img">
                           	</div>
                           	<div class="btn_container">
	                            <button class="btn btn_chane_img text-nowrap" id="changeImg">사진 변경</button>
	                            <%if(!m.getProfileImgOriname().equals("기본프로필.png")){ %>
		                            <form>
		                            	<button class="btn btn_chane_img" id="deleteImg">사진 삭제</button>
		                            </form>
	                            <%} %>
                            </div>
                             <form id="imgChange" action='<%=request.getContextPath() %>/mypage/profileImgUpdate.do' method="post" enctype="multipart/form-data">
	                       		<input type="text" name="no" value="<%=m.getMemberNo()%>" style="display:none">
	                            <input id="now_profile_img" type="text" name="nowImg" value="<%=m.getProfileImgOriname() %>" style="display: none">
	                            <input id="upload_profile_img" type="file" name="upfile" style="display: none">
                            </form>
                        </div>
                        <form action='<%=request.getContextPath() %>/mypage/profileupdate.do' method="post">
                       	<div class="content-container">
                       		<input type="text" name="no" value="<%=m.getMemberNo()%>" style="display:none">
                       		<div class="nickname-container">
                            	<h4>닉네임</h4>
                            	<h5 id="nicknameResult"></h5>
                           	</div>
                            <div class="favorite1_box">
	                            <input type="text" name="nickname" id="nickname" class="favorite1" value="<%=m.getNickname()%>">
                            </div>
                            <h4>소개</h4>
                            <div class="favorite1_box" >
	                            <textarea name="introduce" cols="55" rows="3" class="favorite1" placeholder="소개를 적어주세요 :)" style="resize: none" ><%=m.getIntroduce()%></textarea>
                            </div>
                            <h4>관심 아티스트</h4>
                            <div class="favorite1_box">
                            	<input type="text" name="favorite" id="pickArtist" class="favorite1" style="color: #828282" placeholder="아직 구독한 아티스트가 없습니다" readOnly value='<%= (profile.get(0).getGroupName()!=null)?favorite:""%>'>
                            </div>
                         </div>
	                		<button name="submit" class="btn btn_chane_img">적용</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </main>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<script>

	$("#changeImg").click(e=>{
		$("#upload_profile_img").click();
	});
	$("#upload_profile_img").change(e=>{
		console.dir(e.target.files[0]);
		const img = e.target.files[0];
		var reader = new FileReader();
		reader.readAsDataURL(img);
		reader.onload=()=> $('#profile_img').removeClass("profile_img_default").addClass("profile_img").attr('src',reader.result);
		$("#imgChange").submit();
	});
	$("#deleteImg").click(e=>{
		$(e.target).parent().append($("<input>").attr({'name':'no', 'value':'<%=m.getMemberNo()%>'}).css('display', 'none'));
		$(e.target).parent().append($("<input>").attr({'name':'beforeImg', 'value':'<%=m.getProfileImgOriname()%>'}).css('display', 'none'));
		$(e.target).parent().attr({'action':'<%=request.getContextPath() %>/mypage/profileImgDelete.do','method':'post'}).submit();
	});
	
	const nowNickname = '<%=m.getNickname() %>';
	$("#nickname").keyup(e=>{
		console.log(e.target.value);
		//정규식 활용한 문자열 필터링
		let target = e.target.value;
		target = target.replace(/[^a-zA-Z0-9ㄱ-ㅎ가-힣]/g, '');
		e.target.value = target;
		//빈칸 입력 방지
		if(e.target.value !== ""){
		//중복검사
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/checknickname.do",
			type: "post",
			data: {"nickname": target},
			success: data=>{
				console.dir(data);
				if(target===(nowNickname)){
					$("#nicknameResult").text("");
				}else if(data==0){
					$("#nicknameResult").text("* 사용가능한 닉네임입니다.").css("color","#c552ff");
				} else if(data!=0){
					$("#nicknameResult").text("* 이미 사용중인 닉네임입니다.").css("color","#ff5e5e");
				} 
			}
		});
		}else{
			$("#nicknameResult").text("* 변경할 닉네임을 입력해주세요").css("color","gray");
		}
	});
	<%-- $('#pickArtist').click(e=>{
			window.open('<%=request.getContextPath()%>/mypage/favoriteArtist.select?no=<%=m.getMemberNo()%>','_blank','width=870px, height=930px');
	}); --%>
</script>
</html>