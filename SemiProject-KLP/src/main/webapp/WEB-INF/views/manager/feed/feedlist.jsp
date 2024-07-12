<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kupid.feed.model.dto.Feed" %>
<%
	List<Feed> feed=(List<Feed>)request.getAttribute("feed");
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
.member-container{
	display: flex;
	
}
.member-sec{
	width:85%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	padding: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
	
}
.section{
	width:95%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
} */


/*gpt css  */


.member-sec h1 div {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}

.member-sec select {
    font-size: 14px;
    padding: 10px;
    margin-right: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;

}

.member-sec form {
    display: inline-block;
    margin: 0;
}

.member-sec input[type="text"] {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-right: 5px;
}

.member-sec button {
    background-color: lightgray;
    color: black;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.member-sec button:hover {
    background-color: #d1ade3;;
}

.section {
    margin-top: 20px;
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
<div class="member-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
	<div class="member-sec">
		<h1>FEED</h1>
		<h1>
			<div>
				<select id="searchType">
		       		<option value="회원" <%=searchType!=null&&searchType.equals("회원")?"selected":"" %>>회원</option>
		        	<option value="아티스트" <%=searchType!=null&&searchType.equals("아티스트")?"selected":"" %>>아티스트</option>
		        	<option value="정지" <%=searchType!=null&&searchType.equals("정지")?"selected":"" %>>정지</option>
		        	<option value="탈퇴" <%=searchType!=null&&searchType.equals("탈퇴")?"selected":"" %>>탈퇴</option>
	        	</select>
	        	<div id="search-회원">
					<form action="<%=request.getContextPath()%>/manager/searchfeed.do">
						<input type="hidden" name="searchType" value="회원">
						<input type="text" name="searchKeyword" placeholder="검색할 아이디를 입력하세요" style="width:300px">
						<button type="submit">검색</button>
					</form>
				</div>
				<div id="search-아티스트">
					<form action="<%=request.getContextPath()%>/manager/searchfeed.do">
						<input type="hidden" name="searchType" value="아티스트">
						<input type="text" name="searchKeyword" placeholder="검색할 아이디를 입력하세요" style="width:300px">
						<button type="submit">검색</button>
					</form>
				</div>
				<div id="search-정지">
					<form action="<%=request.getContextPath()%>/manager/searchfeed.do">
						<input type="hidden" name="searchType" value="정지">
						<input type="text" name="searchKeyword" placeholder="검색할 아이디를 입력하세요" style="width:300px">
						<button type="submit">검색</button>
					</form>
				</div>
				<div id="search-탈퇴">
					<form action="<%=request.getContextPath()%>/manager/searchfeed.do">
						<input type="hidden" name="searchType" value="탈퇴">
						<input type="text" name="searchKeyword" placeholder="검색할 아이디를 입력하세요" style="width:300px">
						<button type="submit">검색</button>
					</form>
				</div>
			</div> 
		</h1>
		<section class="section">
		<table class="table-size">
			<tr>
				<th>FeedNo</th>
				<th>작성자</th>
				<th>내용</th>
				<th>등록일</th>
				<th>좋아요</th>
				<th>신고</th>
				<th>회원등급</th>
				<th colspan="2"></th>
				
				
			</tr>
			<%if(feed!=null){ %>
				<%for(Feed f : feed){ %>
				<tr>
					<td style="text-align:center;"><%=f.getFeedNo()%></td>
					<td style="text-align:center;"><%=f.getFeedWriterName()%></td>
					<td style="text-align:center;"><%=f.getFeedContent()%></td>
					<td style="text-align:center;"><%=f.getFeedWriteDate() %></td>
					<td style="text-align:center;"><%=f.getLikes()%></td>
					<td style="text-align:center;"><%=f.getReport() %></td>
					<td style="text-align:center;"><%=f.getMemberGrade() %></td>
					<td><button onclick="deleteFeed(<%=f.getFeedNo()%>);">피드삭제</button></td>
				</tr>
				<%} 
			}else{%>
				<span>피드가 없습니다</span>
			<%} %>
		</table>
		</section>
			
			<br><br>
			<div><%=pagebar %></div>
		</div>
	</div>
</div>
<script>
const deleteFeed=(n)=>{
	if(confirm("정말 삭제 하시겠습니까?")){
		location.assign("<%=request.getContextPath()%>/manager/feeddelete.do?no="+n);
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