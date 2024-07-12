<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kupid.group.model.dto.GroupDto" %>      
<%
	GroupDto g=(GroupDto)request.getAttribute("group");
	
%>
<!DOCTYPE html>
<html>

<body>	
	<div class="myInfo-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
    <main class="main">
        <section class="myInfo">
            <div class="flex_col">
                <h1 class="title">GROUP 수정</h1>
                <div class="content_box">
                    <div class="flex_row">
                        <form action="<%=request.getContextPath()%>/manager/groupupdateend.do?no=<%=g.getGroupNo()%>" method="post" enctype="multipart/form-data">
                       	<div class="content-container">
                       	    <%-- <h3><img src="<%=getServletContext().getRealPath("/upload/")%><%=g.getGroupImg()%>" width=150px height=150px></h3> --%>
                       	    <h3><img src="<%=request.getContextPath()%>/upload/artist/<%=g.getGroupImg()%>" width=150px height=150px></h3>
                       	    <h3>사진변경</h3>
                           	<div class="input_box">
								<input type="file" name="upfile" class="inputTag" required>
								<input id="now_profile_img" type="text" name="nowImg" value="<%=g.getGroupImg()%>" style="display: none">
							</div>	
                            <h3>그룹명</h3>
                            	<div class="input_box">
									<input type="text" name="name" value="<%=g.getGroupName()%>" class="inputTag" required>
								</div>
							<h3>멤버수</h3>
                           	<div class="input_box">
								<input type="number" name="count" value="<%=g.getMemberCount()%>" class="inputTag" required>
							</div>
							<h3>소속사</h3>
                           	<div class="input_box">
								<input type="text" name="company" value="<%=g.getGroupCompany()%>" class="inputTag" required>
							</div>					    
                           	<br>
	                		<button name="submit" class="btn">수정</button>
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