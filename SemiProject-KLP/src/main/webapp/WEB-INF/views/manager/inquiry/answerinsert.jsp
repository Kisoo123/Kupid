<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ page import="com.kupid.manager.inquiry.model.dto.Inquiry" %>
<%
	Inquiry inq=(Inquiry)request.getAttribute("inquiry");
%>
<!DOCTYPE html>
<html>

<body>

<div class="myInfo-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
    <main class="main">
        <section class="myInfo">
            <div class="flex_col">
                <h1 class="title">1:1문의 답변</h1>
                <div class="content_box">
                <div class="content-container">
					<table style="width:100%">
					<tr>
						<th>No</th>
						<th>제목</th>
						<th>문의내용</th>			
						<th>작성일</th>
						<th>작성자</th>
					</tr>
						<tr>
							<td style="text-align:center;"><%=inq.getInqNo()%></td>
							<td style="text-align:center;"><%=inq.getInqTitle()%></td>
							<td style="text-align:center;"><%=inq.getInqContent()%></td>
							<td style="text-align:center;"><%=inq.getInqDate()%></td>
							<td style="text-align:center;"><%=inq.getWriter()%></td>
						</tr>
					</table>
					<div><h3>첨부파일</h3><p style="text-align:center;"><img src="<%=request.getContextPath()%>/upload/<%=inq.getInqFile()%>"></p></div>
		        </div>
                    <div class="flex_row">
                    
                        <form action="<%=request.getContextPath()%>/manager/answerinsertend.do?no=<%=inq.getInqNo()%>" method="post">
                       	<div class="content-container">
                            <h3>답변 제목</h3>
                            	<div class="input_box">
									<input type="text" name="title" class="inputTag" required>
								</div>
                            <h3>답변 내용</h3>
                            <div class="input_box">
								<textarea rows="10" cols="30" name="content" class="inputTag" required></textarea>	
							</div>
                           	<br>
	                		<button name="submit" class="btn">답변</button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </main>
</div>
	
	
</body>
</html>