<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kupid.member.model.dto.MemberDto" %>
<% MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="reportForm" action="<%=request.getContextPath()%>/feed/feedReport.do">
		<select name="category">
			<option value ="스팸홍보/도배글">스팸홍보/도배글</option>
			<option value ="음란물">음란물</option>
			<option value ="불법정보가 포함된 게시글">불법정보가 포함된 게시글</option>
			<option value ="욕설/혐오 표현이 포함된 게시글">욕설/혐오 표현이 포함된 게시글</option>
			<option value ="개인정보 노출 게시글">개인정보 노출 게시글</option>
			<option value ="기타">기타</option>
			<input type="hidden" name="reportMemberNo" value="<%=loginMember.getMemberNo()%>">
			<input type="hidden" name="reportedfeedNo"value='<%=request.getAttribute("feedno")%>'>
		</select>
		<br>
		<textarea name="content" cols="35" rows="5"></textarea>
		<br>
		<input type="submit" value="신고">
	</form>
</body>
<!-- <script type="text/javascript">
	function closePopup() {
	    window.close();
	}
    document.getElementById('reportForm').addEventListener('submit', function(event) {
        event.preventDefault(); 
        window.opener.location.reload(); 
        closePopup(); 
    });
</script> -->
</html>