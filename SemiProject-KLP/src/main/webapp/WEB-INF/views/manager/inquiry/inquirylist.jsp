<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kupid.manager.inquiry.model.dto.Inquiry" %>
<%
	List<Inquiry> inq=(List<Inquiry>)request.getAttribute("inquiry");
	StringBuffer pagebar=(StringBuffer)request.getAttribute("pageBar");
	
%>
<!DOCTYPE html>
<html>

<style>
 ul{
   /* width:80%; */
   display:table;
   padding:0px;
   margin:auto;
}
ul li { 
   list-style-type: none; 
   width: 5em; 
   height: 2em;
   float: left; 
   text-align: center; 
   font-family: "휴먼모음T", sans-serif;  
   border-left: 1px white solid;
   background-color: lightgray; 
   border-radius: 9px;
   margin-left: 2px;
   margin-right:2px;
}

ul li:last-of-type{background-color: white; } 
ul li:first-of-type{background-color: white;} 

ul li a {
   display: block;
   padding:.5em;
   text-decoration: none;
   color: #292929;
}
ul li span {
   display: block;
   padding:.5em;
   text-decoration: none;
   color: #292929;
   background-color: #d1ade3;
   border-radius: 9px;
   height: 1em;
   background-color: white;
}
#nowpage{
   display: block;
   padding:.5em;
   text-decoration: none;
   color: #292929;
   background-color: #d1ade3;
   border-radius: 9px;
   height: 1em;
}

/* hover시 배경색 변경 */
ul li:hover{
   background-color: #d1ade3;
}
ul li:hover > a{
   color:#FFF; /* 글자색*/
}
.inq-container{
	display: flex;
	
}
.inq-sec{
	width:85%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	padding: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
	
}
.table-size{
	width:85%;
	height:650px;
	font-size:30px;

}
.section{
	width:95%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
} */


/*gpt css  */


.inq-sec h1 div {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}

.inq-sec select {
    font-size: 14px;
    padding: 10px;
    margin-right: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;

}

.inq-sec form {
    display: inline-block;
    margin: 0;
}

.inq-sec input[type="text"] {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-right: 5px;
}

.inq-sec button {
    background-color: lightgray;
    color: black;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.inq-sec button:hover {
    background-color: #d1ade3;;
}

.section {
    margin-top: 20px;
}

.table-size {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

.table-size th, .table-size td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: center;
}

.table-size th {
    background-color: #faf0ff;/*연보라*/
    font-weight: bold;
}

.table-size td button {
    /* background-color: #e9bcff;/*보라*/ */
    background-color:lightgray;
    color: black;
    border: none;
    padding: 5px 10px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.table-size td button:hover {
    background-color: #d1ade3;/*진보라*/
}

.btn {
    display: flex;
    justify-content: flex-end;
    margin-top: 20px;
    width: 100%;
    padding-right: 15%;
}

.btn button {
    background-color: #e9bcff;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}


.btn button:hover {
    background-color: #d1ade3;
}

.pagebar {
    text-align: center;
    margin-top: 20px;
}
</style>

<body>
<div class="inq-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
<div class="inq-sec">
	<h1>문의</h1>
	<table class="table-size">
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성일</th>
			<th>작성자</th>
			<th colspan="2"></th>
		</tr>
	<%if(inq!=null){ %>
			<%for(Inquiry i : inq){ %>
			<tr>
				<td><%=i.getInqNo()%></td>
				<td><%=i.getInqTitle()%></td>
				<td><%=i.getInqDate()%></td>
				<td><%=i.getWriter()%></td>
				<%if(i.isAnswer()){ %>
					<td><button onclick="location.assign('<%=request.getContextPath()%>/manager/answerinsert.do?no=<%=i.getInqNo()%>')">답변</button></td>
				<%}else{ %>
					<td><button onclick="location.assign('<%=request.getContextPath()%>/manager/answerupdate.do?no=<%=i.getInqNo()%>')">수정</button></td>
				<%} %>
			</tr>
			<%} 
		}else{%>
			<tr><td>문의내역이 없습니다</td></tr>
		<%} %>
	</table>
		<div><%=pagebar %></div>
	</div>
</div>
</body>
</html>