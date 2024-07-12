<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kupid.faq.model.dto.FaqDto,java.text.SimpleDateFormat"%>     
<%@ include file="/WEB-INF/views/common/header.jsp" %>  

    <title>고객센터</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white ;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .center-header {
            text-align: center;
            margin-bottom: 30px;
        }

        .center-header h1 {
            font-size: 38px;
            color: #4a4a4a;
            margin-top: 50px; 
            margin-bottom: 60px; 
        }
        .center-header h4 {
        	margin-top: 20px;
   		 margin-bottom: -20px; 
}

        .button-container {
            display: flex;
            justify-content: center;
            margin-bottom: 10px;
        }

        .button-container h3 {
            margin-bottom: 20px;
            color: #666;
            font-size: 20px;
            width: 100%;
            text-align: center;
        }

        .button-container button {
            background-color: #fff;
            color: #6c5ce7;
            border: 2px solid #6c5ce7;
            padding: 23px 10px;
            border-radius: 10px;
            cursor: pointer;
            margin: 15px;
            font-size: 18px;
            width: 100px;
            height: 100px;
            text-align: center;
            transition: background-color 0.3s, color 0.3s;
            display: flex;
            flex-direction: column;
            align-items: center;
            
        }

        .button-container button:hover {
            background-color: #d3adf7;
            color: #fff;
        }

        .button-container button i {
            margin-bottom: 10px;
            color: #6c5ce7;
            font-size: 24px;
        }

        .faq-link {
            color: #6c5ce7;
            text-decoration: none;
            font-size: 14px;
            display: inline-flex;
            margin-bottom: 30px;
            margin-top: 10px;
            float: right;
        }

        .faq-link:hover {
            text-decoration: underline;
        }

        .faq-link i {
            margin-right: 5px;
            color: #6c5ce7;
        }
		.help-section p {
		    margin-top: 20px; 
		    line-height:180%;
		}

        .faq-section,
        .contact-section,
        .help-section {
            background-color: #fff;
            padding: 30px;
            margin-bottom: 50px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border: 2px solid #d3adf7;
        }

        .faq-section h1,
        .contact-section h1,
        .help-section h2 {
            color: #4a4a4a;
            margin-bottom: 20px;
            font-size: 26px;
        }

        .faq-section h1 i,
        .contact-section h1 i,
        .help-section h2 i {
            color: #d3adf7;
        }

        .faq-section h4,
        .contact-section p,
        .help-section p {
            color: #888;
            margin-bottom: 20px;
            font-size: 16px;
        }

        .faq_button {
            margin-top: 10px;
            margin-bottom: 40px;
            text-decoration: none;
            font-size: 14px;
            display: flex;
            justify-content: flex-end;
        }

        .faq_button a {
            color: #6c5ce7; 
        }

        .faq_button a:hover {
            text-decoration: underline;
        }

        .faq_button a i {
            margin-right: 5px;
            color: #6c5ce7; 
        }

        .faq-section button {
            background-color: #fff;
            color: #6c5ce7;
            border: 2px solid #6c5ce7;
            padding: 15px 20px;
            border-radius: 20px; 
            cursor: pointer;
            font-size: 16px;
            text-align: center;
            transition: background-color 0.3s, color 0.3s;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .faq-section button:hover {
            background-color: #d3adf7;
            color: #fff;
        }

        .faq-section button i {
            margin-right: 10px;
            color: #6c5ce7;
        }

    </style>
</head>
<body>
    <div class="container">
        <div class="center-header">
            <h1>무엇을 도와드릴까요?</h1>
        </div>
        <div class="center-header">
            <h4>고객님들이 자주하는 질문이에요!</h4>
        </div>

        <div class="button-container">
            <button type="button" onclick="location.href='<%= request.getContextPath() %>/faq/faqserch.do?category=회원&searchKeyword='">
                <i class="fas fa-user"></i>
                회원
            </button>
            <button type="button"  onclick="location.href='<%= request.getContextPath() %>/faq/faqserch.do?category=상점&searchKeyword='">
                <i class="fas fa-store"></i>
                상점
            </button>
            <button type="button"  onclick="location.href='<%= request.getContextPath() %>/faq/faqserch.do?category=커뮤니티&searchKeyword='">
                <i class="fas fa-users"></i>
                커뮤니티
            </button>
        </div>
		<div class="faq_button"><a href="<%= request.getContextPath() %>/faq/faq.do"><i class="fas fa-question-circle"></i> FAQ 바로가기</a></div>


        <div class="faq-section">
            <h1>1:1 정확한 채팅문의 <i class="fas fa-comment"></i></h1>
            <h4>KUPID 담당 상담원에게 자세하고 확실하게 해결해 보세요</h4>
            <button onclick="location.assign('<%= request.getContextPath() %>/inquiry/inquiry.do')"><i class="fas fa-comments"></i> 문의하기</button>
        </div>

        <div class="contact-section">
            <h1>KUPID 고객센터 <i class="fas fa-headset"></i></h1>
            <p>전화번호: 1588-1108(유료)</p>
            <p>월~금요일 09:00 ~ 18:00</p>
            <p>주말, 공휴일 휴무</p>
        </div>

        <div class="help-section">
            <h2>음성메시지 상담 안내 <i class="fas fa-microphone"></i></h2>
            <p>KUPID 고객센터에서는 고객님의 문의를 녹음으로 접수받고 답변 드리는 <br>음성메시지 서비스를 운영하고 있습니다. 음성메시지를 남겨주시면 빠르게 확인하여 답변드리겠습니다.</p>
        </div>
    </div>
    
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>