<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <style>
        /* 간단한 스타일링 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container1 {
            max-width: 800px;
            margin: 20px auto;
            padding: 0 20px;
            width:100%;
        }
        .navigation {
            text-align: right;
            margin-bottom: 20px;
        }
        .navigation a {
            text-decoration: none;
            color: #333;
            margin-left: 10px;
        }
        .product {
        	display: flex;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 40px;
            overflow: hidden;
            
        }
        .product img {
            max-width: 100%;
            height: auto;
            margin-bottom: 40px;
        }
        .product h2 {
            margin-top: 0;
        }
        .product p {
            margin: 10px 0;
        }
        .product .price {
            /* font-weight: bold; */
            font-size: 25px;
        }
        .button {
            padding: 10px 20px;
            background-color: #e9bcff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #e55a00;
        }
        .fine-print {
            margin-top: 20px;
            font-style: italic;
            color: #666;
        }
        .article {
            line-height: 1.6;
            color: #333;
        }
	.marketprice{
		width: 100%;
		padding-left: 50px;
		margin : 50px;
	}
	
	.membership-info {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #f9f9f9;
}

.membership-info dl {
    margin: 0;
    padding: 0;
}

.membership-info dt {
    font-weight: bold;
    margin-bottom: 5px;
}

.membership-info dd {
    margin-bottom: 10px;
}

.membership-info .attention {
    color: #ff0000;
    font-style: italic;
}
.imgcenter{
	display:flex;
	flex-direction:column;
	justify-content: center;
	text-align: center;
}
    </style>

<body>
     <%@ include file="/WEB-INF/views/common/header.jsp"%>
	 <div class="container">
        <div class="product">
       		<img src="https://cdn-contents.weverseshop.io/public/shop/8089acae4f647f847a5ed7beeadc8230.png?q=95&w=720" width="500px" height="600px">
            <div class="marketprice">
            	<h2>RIIZE MEMBERSHIP</h2>
	            <p>Subheading</p>
	            <p class="price">30,000원</p>
	            <br>
	            <!-- <button class="button">장바구니</button> -->
	            <button class="button" style="width:100%;" onclick="location.assign('<%=request.getContextPath()%>/membershipinsert.do?no=<%=loginMember.getMemberNo()%>')">구매</button>
            </div>
        </div>
        	<div class="imgcenter">
	        	<p><img src="./image/market/상품설명1.png" width="80%" height="600px"></p>
	        	<p><img src="./image/market/상품설명.jpg" width="80%"></p>
	        	<!-- <p><img src="./image/market/상품설명4.png" width="70%" height="1400px"></p> -->
	        </div>
        <div class="article">

       
        </div>
    </div>
</body>
</html>