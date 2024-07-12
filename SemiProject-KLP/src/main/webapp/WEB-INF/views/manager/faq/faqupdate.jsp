<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kupid.manager.faq.model.dto.Faq" %>    
<%
	Faq f=(Faq)request.getAttribute("faq");
%>
<!DOCTYPE html>
<html>
<body>
<div class="myInfo-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
    <main class="main">
        <section class="myInfo">
            <div class="flex_col">
                <h1 class="title">FAQ 수정</h1>
                <div class="content_box">
                    <div class="flex_row">
                        <form action="<%=request.getContextPath()%>/manager/faqupdateend.do" method="post">
                       	<div class="content-container">
                       		<h3>번호</h3>
                       		<div class="input_box">
								<input type="text" name="faqNo" class="inputTag" value="<%=f.getFaqNo()%>" readOnly>
							</div>
							<h3>카테고리</h3>
                       		<div>
								<select name="category" >
        						<option value="회원" <%=f.getFaqCategory() !=null&&f.getFaqCategory().equals("회원")?"selected":"" %>>회원</option>
        						<option value="상점" <%=f.getFaqCategory()!=null&&f.getFaqCategory().equals("상점")?"selected":"" %>>상점</option>
        						<option value="커뮤니티" <%=f.getFaqCategory()!=null&&f.getFaqCategory().equals("커뮤니티")?"selected":"" %>>커뮤니티</option>
        						</select>
							</div>
                            <h3>제목</h3>
                            	<div class="input_box">
									<input type="text" name="title" class="inputTag" value="<%=f.getFaqTitle()%>" required>
								</div>
                            <h3>내용</h3>
                            <div class="input_box">
								<textarea rows="10" cols="30" name="content" class="inputTag" required><%=f.getFaqContent()%></textarea>	
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