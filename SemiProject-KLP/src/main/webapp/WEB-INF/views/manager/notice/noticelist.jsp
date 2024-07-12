<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kupid.manager.notice.model.dto.Notice" %>
<%
	List<Notice> notices=(List<Notice>)request.getAttribute("notice");
	StringBuffer pagebar=(StringBuffer)request.getAttribute("pageBar");
	String searchType=request.getParameter("searchType");
	String searchKeyword=request.getParameter("searchKeyword");
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
.notice-container{
	display: flex;
	
}
.notice-sec{
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


.notice-sec h1 div {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}

.notice-sec select {
    font-size: 14px;
    padding: 10px;
    margin-right: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;

}

.notice-sec form {
    display: inline-block;
    margin: 0;
}

.notice-sec input[type="text"] {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-right: 5px;
}

.notice-sec button {
    background-color: lightgray;
    color: black;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.notice-sec button:hover {
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
<div class="notice-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
	<div class="notice-sec">
		<h1>공지사항</h1>
		<h1>
			<div>
				<select id="searchType">
		       		<option value="일반" <%=searchType!=null&&searchType.equals("일반")?"selected":"" %>>일반</option>
		        	<option value="굿즈" <%=searchType!=null&&searchType.equals("굿즈")?"selected":"" %>>굿즈</option>
		        	<option value="행사" <%=searchType!=null&&searchType.equals("행사")?"selected":"" %>>행사</option>
	        	</select>
	        	<div id="search-일반">
					<form action="<%=request.getContextPath()%>/manager/searchNotice.do">
						<input type="hidden" name="searchType" value="일반">
						<input type="text" name="searchKeyword" placeholder="검색할 내용을 입력하세요" style="width:300px">
						<button type="submit" >검색</button>
					</form>
				</div>
				<div id="search-굿즈">
					<form action="<%=request.getContextPath()%>/manager/searchNotice.do">
						<input type="hidden" name="searchType" value="굿즈">
						<input type="text" name="searchKeyword" placeholder="검색할 내용을 입력하세요" style="width:300px; height:50px">
						<button type="submit" >검색</button>
					</form>
				</div>
				<div id="search-행사">
					<form action="<%=request.getContextPath()%>/manager/searchNotice.do">
						<input type="hidden" name="searchType" value="행사">
						<input type="text" name="searchKeyword" placeholder="검색할 내용을 입력하세요" style="width:300px; height:50px">
						<button type="submit" >검색</button>
					</form>
				</div>
			</div> 
		</h1>
		<section class="section">
		<table class="table-size">
			<tr>
				<th>No</th>
				<th>카테고리</th>
				<th >제목</th>
				<th>작성일</th>
				<th colspan="2"></th>
			</tr>
		
			<%if(notices!=null){ %>
				<%for(Notice n : notices){ %>
				<tr>
					<td style="text-align:center;"><%=n.getNoticeNo()%></td>
					<td style="text-align:center;"><%=n.getNoticeCategory()%></td>
					<td><%=n.getNoticeTitle() %></td>
					<td style="text-align:center;"><%=n.getNoticeDate() %></td>
					<td><button onclick="location.assign('<%=request.getContextPath()%>/manager/noticeupdate.do?no=<%=n.getNoticeNo()%>')">수정</button></td>
					<td><button onclick="deleteNotice(<%=n.getNoticeNo()%>);">삭제</button></td>
				</tr>
				<%} 
			}else{%>
				<span>공지사항이 없습니다</span>
			<%} %>
		</table>
		</section>
			<div class="btn"><button onclick="location.assign('<%=request.getContextPath()%>/manager/noticeinsert.do')">작성</button></div>
			<br><br>
			<div><%=pagebar%></div>
	</div>
</div>
	<script>
		const deleteNotice=(n)=>{
			if(confirm("정말 삭제 하시겠습니까?")){
				location.assign("<%=request.getContextPath()%>/manager/noticedelete.do?no="+n)
			}else{
				alert("삭제가 취소되었습니다");
			}
		}
		
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