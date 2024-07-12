<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.error500{
	display: flex;
	flex-direction:column;
	width: 100%;
	justify-content: center;
	align-items: center;
}
body{
	height: 80vh;
}
.btn {
	  position: relative;
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  margin: 0px auto;
	  max-width: 100%;
	  color: black;
	  text-align: center;
	  background-color: #d1ade3;
	  border-radius: 8px;
	  padding: 8px 8px 8px 8px;
	}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="error500">
<img src="<%=request.getContextPath()%>/image/error/500에러.png" width=50%; class="text-align-center">
<button class="btn btn-outline-secondary mb-5" onclick="location.assign('<%=request.getContextPath()%>')">home</button>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>