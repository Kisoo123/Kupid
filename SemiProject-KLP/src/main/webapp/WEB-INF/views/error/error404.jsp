<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<style>
.error404{
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
<div class="error404">
	<img src="<%=request.getContextPath()%>/image/error/404에러.png" width=30%;>
	<button class="btn btn-outline-secondary mb-5" onclick="location.assign('<%=request.getContextPath()%>')">메인으로</button>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>