<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<body>
	
<div class="myInfo-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
    <main class="main">
        <section class="myInfo">
            <div class="flex_col">
                <h1 class="title">GROUP 등록</h1>
                <div class="content_box">
                    <div class="flex_row">
                    
                        <form action="<%=request.getContextPath()%>/manager/groupinsertend.do" method="post" enctype="multipart/form-data">
                       	<div class="content-container">
                            <h3>그룹명</h3>
                            	<div class="input_box">
									<input type="text" name="name" class="inputTag" required>
								</div>
							<h3>멤버수</h3>
                           	<div class="input_box">
								<input type="number" name="count" class="inputTag" required>
							</div>
							<h3>데뷔일</h3>
                           	<div class="input_box">
								<input type="date" name="debutday" class="inputTag" required>
							</div>
							<h3>소속사</h3>
                           	<div class="input_box">
								<input type="text" name="company" class="inputTag" required>
							</div>
							<h3>사진</h3>
                           	<div class="input_box">
								<input type="file" name="upfile" class="inputTag" required>
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