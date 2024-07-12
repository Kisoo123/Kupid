<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kupid.manager.report.model.dto.Report" %>    
<%
	Report r=(Report)request.getAttribute("report");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.input_box p{
	margin-top:2px;
}
</style>
<body>
<div class="myInfo-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
    <main class="main">
        <section class="myInfo">
            <div class="flex_col">
                <h1 class="title">신고 처리</h1>
                <div class="content_box1 content_box">
                    <div class="flex_row">
                       	<div class="content-container">
                            <h3>신고번호</h3>
                            <div class="input_box">
	                           <p><%=r.getReportNo() %></p>
                            </div>
                             <h3>카테고리</h3>
                            <div class="input_box">
	                           <p><%=r.getReportCategory() %></p>
                            </div>
                             <h3>신고한 회원</h3>
                            <div class="input_box">
	                           <p><%=r.getReportingId() %></p>
                            </div>
                            <h3>신고 내용</h3>
                            <div class="input_box" style="height:150px">
	                           <p><%=r.getReportContent()%></p>
                            </div>
                            <h3>신고 날짜</h3>
                            <div class="input_box">
	                            <p><%=r.getReportDate() %></p>
                            </div>
                            <%if(r.getPenaltyDate()!=""){ %>
                            	<h3>처리 날짜</h3>
                            	<div class="input_box">
	                            <p><%=r.getPenaltyDate() %></p>
                            </div>
                            <%}%>
                            <br>
                            <form action="<%=request.getContextPath()%>/manager/penalty.do" method="post">
                            	<input type="hidden" name="reportNo" value="<%=r.getReportNo()%>">
                            	<input type="hidden" name="memberNo" value="<%=r.getReportedMember()%>">
	                            <div class="input_box" style="height:200px">
		                            <h3>신고받은 회원<h4><%=r.getReportedId() %></h4></h3>
		                            penalty 선택 :
		                            <input type="radio" name="penalty" value="stop">정지
		                            <input type="radio" name="penalty" value="unactive">탈퇴
		                            <input type="radio" name="penalty" value="pass">pass
		                            <br><br>
		                            <p><button type="submit" class="btn btn_chane_img">처리</button></p>
		                		</div>
			             
	                		</form>
                        </div>
          
                    </div>
                </div>
            </div>
        </section>
    </main>
</div>
</body>
</html>