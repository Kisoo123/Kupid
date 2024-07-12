<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.kupid.faq.model.dto.FaqDto, java.text.SimpleDateFormat" %>
<%@ page import="com.kupid.faq.model.dto.FaqDto" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>  

<%
    List<FaqDto> faqs = (List<FaqDto>) request.getAttribute("faq");
    StringBuffer pagebar = (StringBuffer) request.getAttribute("pageBar");
    String category = request.getParameter("category");
    String searchKeyword = request.getParameter("searchkeyword");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FAQ</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #fff; 
        margin: 0;
        padding: 0;
        
    }
    
    .container {
        width: 80%; 
        margin: 0 auto; 
        display: flex;
        flex-direction: column; 
        align-items: center; 
        margin-bottom:50px;
        
    }
    
    .faq-header h1 {
        font-size: 50px; 
    }

    .faq-header {
        text-align: center;
        margin-top: 50px;
        margin-bottom: 50px;
    }
    
    .search-container {
        text-align: center;
        margin-bottom: 20px;
        white-space: nowrap; 
    }
    
    .search-container button,
    .search-container input[type="text"] {
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin: 0 5px;
        font-size: 16px;
        transition: all 0.3s ease;
        background-color: #fff;
    }
    
    .search-container button {
        background-color: #b39ddb; 
        color: #fff;
        cursor: pointer;
        outline: none;
        margin: 10px;        
    }
    
    .search-container button:hover {
        background-color: #9575cd; 
    }
    
    table {
        width: 90%;
        /* border-collapse: collapse; */
        margin-top: 20px;
        border-radius: 5px;
        overflow: hidden;
      /*   box-shadow: 0 0 20px #B39DDB; */
        background-color: #fff;
        border: 2px solid #B39DDB !important;
   }
    
    table th,
    table td {
        padding: 15px;
        text-align: center;
        border-bottom: 1px solid #ddd;
    }
    
    table th {
        background-color: #f2f2f2;
    }
    
    .faq-content-row {
        display: none;
        
    }
     .faq-content-row td {
            text-align: center;
            
        }
    
    .faq-content {
        padding: 15px;
        border-top: 1px solid #ddd;
        background-color: #fff; 
        color: #000; 
        text-align: left;
    }

    .pagination {
        text-align: center;
        margin-top: 20px;
    }
    
    .pagination ul {
        list-style: none;
        padding: 0;
        display: inline-block;
    }
    
    .pagination li {
        display: inline;
        margin-right: 5px;
    }
    
    .footer {
        text-align: center;
        margin-top: 20px;
        padding-top: 20px;
        border-top: 1px solid #ddd;
    }
    
    /* 버튼 스타일 수정 */
    button[type="button"] {
        background-color: #b39ddb;
        border: none;
        color: #fff;
        padding: 5px 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
        border-radius: 5px;
        transition: background-color 0.3s;
        margin-left: 10px;
    }
    
    button[type="button"]:hover {
        background-color: #9575cd; 
    }
    
    .pagination ul {
        list-style: none;
        padding: 0;
        display: inline-block;
    }
    
    .pagination li {
        display: inline;
        margin-right: 5px;
    }
    
    .pagination a {
        display: inline-block;
        padding: 8px 16px;
        text-decoration: none;
        color: #333;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    
    .pagination a.active {
        background-color: #9575cd; 
        color: #fff; 
        border: 1px solid #9575cd; 
    }
    
    .pagination a:hover {
        background-color: #b39ddb; 
    }
</style>
</head>
<body>

<div class="container">
    <div class="faq-header">
        <h1>FAQ <i class="fas fa-question-circle"></i></h1>
    </div>
    <div class="search-container">
        <form id="searchForm" action="<%= request.getContextPath() %>/faq/faqserch.do" method="get">
            <button type="button" onclick="setCategory('전체')" >전체</button>
            <button type="button" id="member" onclick="setCategory('회원')">회원</button>
            <button type="button" id="store" onclick="setCategory('상점')">상점</button>
            <button type="button" id="community" onclick="setCategory('커뮤니티')">커뮤니티</button>
            <input type="hidden" id="category" name="category" value="<%=request.getParameter("category")%>">
            <br>
            <input type="text" id="search" name="searchKeyword" placeholder="검색어 입력" value="">
            <button type="submit">검색</button>
        </form>
    </div>
    <table>
        <thead>
            <tr>
                <th>카테고리</th>
                <th>제목</th>
            </tr>
        </thead>
       <tbody>
    <% if (faqs != null && !faqs.isEmpty()) { %>
        <% for (FaqDto f : faqs) { %>
            <tr>
                <td><%= f.getFaqCategory() %></td>
                <td>
                    <%= f.getFaqTitle() %>
                    <button type="button" onclick="toggleContent(this)">✓</button>
                </td>
            </tr>
            <tr class="faq-content-row">
               
                <td colspan="2" class="faq-content"><%= f.getFaqContent() %></td>
            </tr>
        <% } %>
    <% } else { %>
        <tr>
            <td colspan="2"><span>등록된 FAQ가 없습니다</span></td>
        </tr>
    <% } %>
</tbody>

    </table>
    <div class="pagination">
        <%= pagebar.toString() %>
    </div>
    <div class="footer">
        <button type="button" onclick="location.assign('<%= request.getContextPath() %>/customer/customermain.do')">고객센터 바로가기</button>
        <button type="button" onclick="location.assign('<%= request.getContextPath() %>/inquiry/inquiry.do')">1:1 문의하기</button>
    </div>
</div>

<script>
    function setCategory(cat) {
        const form = document.getElementById('searchForm');
        document.getElementById('category').value = cat;
        if (cat === '전체') {
            form.action = '<%= request.getContextPath() %>/faq/faq.do';
        } else {
            form.action = '<%= request.getContextPath() %>/faq/faqserch.do';
        }
        form.submit();
    }

    function toggleContent(button) {
        const contentRow = button.parentNode.parentNode.nextElementSibling;
        if (contentRow.style.display === 'none' || contentRow.style.display === '') {
            contentRow.style.display = 'table-row';
        } else {
            contentRow.style.display = 'none';
        }
    }
</script>
</body>
</html>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>
