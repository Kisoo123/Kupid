<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kupid.manager.notice.model.dto.Notice" %>    
    
<%
	Notice n=(Notice)request.getAttribute("notice");
%>
<!DOCTYPE html>
<html>

<body>

<div class="myInfo-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
    <main class="main">
        <section class="myInfo">
            <div class="flex_col">
                <h1 class="title">공지사항 수정</h1>
                <div class="content_box">
                    <div class="flex_row">
                        <form action="<%=request.getContextPath()%>/manager/noticeupdateend.do" method="post">
                       	<div class="content-container">

							<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>">
							<h3>카테고리</h3>
                       		<div>
								<select name="category" >
	        						<option value="굿즈" <%=n.getNoticeCategory() !=null&&n.getNoticeCategory().equals("굿즈")?"selected":"" %>>굿즈</option>
        							<option value="일반" <%=n.getNoticeCategory()!=null&&n.getNoticeCategory().equals("일반")?"selected":"" %>>일반</option>
        							<option value="행사" <%=n.getNoticeCategory()!=null&&n.getNoticeCategory().equals("행사")?"selected":"" %>>행사</option>
	        					</select>
							</div>
                            <h3>제목</h3>
                            	<div class="input_box">
									<input type="text" name="title" class="inputTag" value="<%=n.getNoticeTitle()%>" required>
								</div>
                            <h3>내용</h3>
                            <div class="input_box">
								<textarea rows="10" cols="30" name="content" class="inputTag" required><%=n.getNoticeContent()%></textarea>	
							</div>
                           	<br>
	                		<button name="submit" class="btn">등록</button>
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