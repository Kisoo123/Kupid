<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.List,com.kupid.group.model.dto.GroupDto" %>
<%
	List<GroupDto> group=(List<GroupDto>)request.getAttribute("group");
	StringBuffer pagebar=(StringBuffer)request.getAttribute("pageBar");
%>
<!DOCTYPE html>
<html>

<body>
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
.group-container{
	display: flex;
	
}
.group-sec{
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


.group-sec h1 div {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}

.group-sec select {
    font-size: 14px;
    padding: 10px;
    margin-right: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;

}

.group-sec form {
    display: inline-block;
    margin: 0;
}

.group-sec input[type="text"] {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-right: 5px;
}

.group-sec button {
    background-color: lightgray;
    color: black;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.group-sec button:hover {
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
</head>
<body>

<div class="group-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
<div class="group-sec">
		<h1>GROUP</h1>
	
		<div>
			<form action="<%=request.getContextPath()%>/manager/searchgroup.do">
							<input type="text" name="searchKeyword" placeholder="검색할 그룹명을 입력하세요" style="width:300px">
							<button type="submit">검색</button>
			</form>
		</div>
		<section class="section">
		<table class="table-size">
			<tr>
				<th>No</th>
				<th>그룹명</th>
				<th>소속사</th>
				<th>멤버수</th>
				<th>데뷔일</th>
				<th colspan="2"></th>
			</tr>
		
			<%if(group!=null){ %>
				<%for(GroupDto m : group){ %>
				<tr>
					<td><%=m.getGroupNo()%></td>
					<td><%=m.getGroupName()%></td>
					<td><%=m.getGroupCompany()%></td>
					<td><%=m.getMemberCount()%></td>
					<td><%=m.getGroupDebutday()%></td>
					<td><button onclick="location.assign('<%=request.getContextPath()%>/manager/groupupdate.do?no=<%=m.getGroupNo()%>')">수정</button></td>
					<td><button onclick="deleteGroup(<%=m.getGroupNo()%>);">삭제</button></td>
				</tr>
				<%} 
			}else{%>
				<span>등록된 group이 없습니다</span>
			<%} %>
		</table>
		</section>
			<div><button onclick="location.assign('<%=request.getContextPath()%>/manager/groupinsert.do')">등록</button></div>
			<br><br><br>
			<div><%=pagebar%></div>
		</div>
</div>
<script>
const deleteGroup=(n)=>{
	if(confirm("정말 삭제 하시겠습니까?")){
		location.assign("<%=request.getContextPath()%>/manager/groupdelete.do?no="+n)
	}else{
		alert("삭제가 취소되었습니다");
	}
}
</script>
</body>
</html>