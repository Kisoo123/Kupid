<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kupid.member.model.dto.MemberDto" %>    
<%  
	MemberDto m = (MemberDto) request.getAttribute("artist"); 
%>

<!DOCTYPE html>
<html>
<style>
.input_box p{
	margin-top:2px;
}
</style>
<!-- 임시디자인 -->
<div class="myInfo-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
    <main class="main">
        <section class="myInfo">
            <div class="flex_col">
                <h1 class="title">개인정보</h1>
                <div class="content_box1 content_box">
                    <div class="flex_row">
                        
                       	<div class="content-container">
                       		<h3><img src="<%=request.getContextPath()%>/upload/artist/<%=m.getProfileImgOriname()%>" width=150px height=150px></h3>                 
                            <h3>이름</h3>
                            <div class="input_box">
	                            <p><%=m.getMemberName() %></p>
                            </div>
                            <h3>아이디</h3>
                            <div class="input_box">
	                           <p><%=m.getMemberId() %></p>
                            </div>
                             <h3>닉네임</h3>
                            <div class="input_box">
	                           <p><%=m.getNickname() %></p>
                            </div>
                             <h3>생일</h3>
                            <div class="input_box">
	                           <p><%=m.getBirth() %></p>
                            </div>
                            <h3>성별</h3>
                            <div class="input_box">
	                           <p><%=m.getGender() %></p>
                            </div>
                            <h3>연락처</h3>
                            <div class="input_box">
	                            <p><%=m.getPhone() %></p>
                            </div>
                            <h3>이메일</h3>
                            <div class="input_box">
	                            <p><%=m.getEmail() %></p>
                            </div>
                            <h3>주소</h3>
                            <div class="input_box">
                            	<p><%=m.getAddress() %> <%=m.getAddressDetail()%></p>
                            </div>
                             <h3>가입일</h3>
                            <div class="input_box">
                            	<p><%=m.getEnrollDate() %></p>
                            </div>
                        	 <br>
	                		<button class="btn btn_chane_img" onclick="deleteMember(<%=m.getMemberNo()%>);">회원삭제</button>
                        </div>
          
                    </div>
                </div>
            </div>
        </section>
    </main>
</div>
<script>
	const deleteMember=(n)=>{
		if(confirm("정말 삭제 하시겠습니까?")){
			location.assign("<%=request.getContextPath()%>/manager/memberdelete.do?no="+n);
		}else{
			alert("삭제가 취소되었습니다");
		}
	}
</script>


</html>