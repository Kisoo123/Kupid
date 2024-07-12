<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>inquiry</title>
<style>
    #customer-container {
        width: 900px;
        margin: 50px auto 30px;
        text-align: left; 
    }

    #customer-container table {
        width: 100%;
        border-collapse: collapse;
    }

    #customer-container th, #customer-container td {
        background-color: white;
        color: #b39ddb;
        padding: 10px;
        text-align: left;
        width: 100%; 
    }

    #customer-container input[type="text"],
    #customer-container textarea,
    #customer-container input[type="file"] {
        width: calc(100% - 20px); 
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    #customer-container input[type="submit"] {
        width: 100px;
        background-color: #b39ddb;
        color: white;
        padding: 10px;
        margin: 10px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    #customer-container input[type="submit"]:hover {
        background-color: #6a0dad;
    }

    .btn-container {
        display: flex;
        justify-content: flex-end;
        margin-top: 20px;
    }
</style>

<section id="customer-container">
    <form action="<%=request.getContextPath()%>/inquiry/inquirywriteend.do" method="post" enctype="multipart/form-data">
        <table id="tbl-customer">
            <tr>
                <th>제 목</th>
            </tr>
            <tr>
                <td><input type="text" name="title" required></td>
            </tr>
            <tr>
                <th>문의 내용</th>
            </tr>
            <tr>
                <td><textarea cols="47" rows="10" name="content" style="resize:none"></textarea></td>
            </tr>
            <tr>
                <th>파일 첨부</th>
            </tr>
            <tr>
                <td><input type="file" name="upfile"></td>
            </tr>
            <tr>
                <th>회원 번호</th>
            </tr>
            <tr>
                <td><input type="text" name="inq_member" value="<%=loginMember.getMemberNo()%>"></td>
            </tr>
        </table>
        <div class="btn-container">
            <input type="submit" value="접수하기">
        </div>
    </form>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 
