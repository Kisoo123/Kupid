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
                <h1 class="title">공지사항 등록</h1>
                <div class="content_box">
                    <div class="flex_row">
                        <form id="frm" action="<%=request.getContextPath()%>/manager/noticeinsertend.do" method="post">
                       	<div class="content-container">
							<h3>카테고리</h3>
                       		<div>
								<select name="category" >
	        						<option value="굿즈" >굿즈</option>
	        						<option value="일반" >일반</option>
	        						<option value="행사" >행사</option>
	        					</select>
							</div>
                            <h3>제목</h3>
                            	<div class="input_box">
									<input type="text" name="title" class="inputTag" required>
								</div>
                            <h3>내용</h3>
                            <div class="input_box">
								<textarea rows="20" cols="30" name="content" id="content" class="inputTag" ></textarea>	
							</div>
                           	<br>
	                		<button name="submit" class="btn" id="save">등록</button>
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