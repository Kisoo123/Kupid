<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kupid.manager.report.model.dto.Report" %>    
<%
	List<Report> report=(List<Report>)request.getAttribute("report");

	StringBuffer pagebar=(StringBuffer)request.getAttribute("pageBar");
	String searchType=request.getParameter("searchType");
	String searchKeyword=request.getParameter("searchKeyword");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
.report-container{
	display: flex;
	
}
.report-sec{
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
	width:75%;
	height:650px;
	font-size:20px;
}
.section{
	width:95%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

} */


/*gpt css  */


.report-sec h1 div {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}

.report-sec select {
    font-size: 14px;
    padding: 10px;
    margin-right: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;

}

.report-sec form {
    display: inline-block;
    margin: 0;
}

.report-sec input[type="text"] {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-right: 5px;
}

.report-sec button {
    background-color: lightgray;
    color: black;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.report-sec button:hover {
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
<div class="report-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
	<div class="report-sec">
	<h1>신고 내역</h1>
	<h1>
			<div>
				<select id="searchType">
		       		<option value="reporting_member" <%=searchType!=null&&searchType.equals("reporting_member")?"selected":"" %>>신고한 회원</option>
		        	<option value="reported_member" <%=searchType!=null&&searchType.equals("reported_member")?"selected":"" %>>신고받은 회원</option>
	        	</select>
	        	<div id="search-reported_member">
					<form action="<%=request.getContextPath()%>/manager/searchreport.do">
						<input type="hidden" name="searchType" value="reported_member">
						<input type="text" name="searchKeyword" placeholder="검색할 내용을 입력하세요" style="width:300px">
						<button type="submit">검색</button>
					</form>
				</div>
				<div id="search-reporting_member">
					<form action="<%=request.getContextPath()%>/manager/searchreport.do">
						<input type="hidden" name="searchType" value="reporting_member">
						<input type="text" name="searchKeyword" placeholder="검색할 내용을 입력하세요" style="width:300px">
						<button type="submit">검색</button>
					</form>
				</div>
			</div> 
		</h1>
	<section class="section">
	<table class="table-size">
		<tr>
			<th>신고번호</th>
			<th>카테고리</th>
			<th>신고한회원</th>
			<th>신고받은회원</th>
			<th>신고날짜</th>
			<th>처리날짜</th>
			<th>처리결과</th>
			<th></th>
		</tr>
	
		<%if(report!=null){ %>
			<%for(Report rp : report){ %>
			<tr>
				<td style="text-align:center;"><%=rp.getReportNo() %></td>
				<td style="text-align:center;"><%=rp.getReportCategory()%></td>
				<td style="text-align:center;"><%=rp.getReportingId()%></td>
				<td style="text-align:center;"><%=rp.getReportedId() %></td>
				<td style="text-align:center;"><%=rp.getReportDate() %></td>	
				<td style="text-align:center;">
					<%if(rp.getReportEndDate()!=null){ %>
						<%=rp.getReportEndDate() %>
					<%}else{ %>
						<p>처리중...</p>
					<%} %>
					
				</td>	
				<td style="text-align:center;">
					<%if(rp.getReportResult()!=null){ %>
						<%=rp.getReportResult() %>
					<%}else{ %>
						<p>처리중...</p>
					<%} %>
				
				</td>	
				<td><button onclick="location.assign('<%=request.getContextPath()%>/manager/reportview.do?no=<%=rp.getReportNo()%>')">(상세내용)처리</button></td>
			</tr>
			<%} 
		}else{%>
			<span>신고내역이 없습니다</span>
		<%} %>
	</table>
	</section>
		<div><%=pagebar %></div>
		</div>
	</div>
	<script>
	$(()=>{
		 $("#searchType").change();
	})


	$("#searchType").change(e=>{
			const type=e.target.value;
			$(e.target).parent().children("div").hide();
			$("#search-"+type).css("display","inline-block");
		})
	</script>
</body>
</html>