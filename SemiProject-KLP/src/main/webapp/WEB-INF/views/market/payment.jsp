<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        select,
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 15px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
            margin-top: 5px;
        }
    </style>

</head>
<body>
   <div class="container">
        <h1>결제 정보 입력</h1>
        <form action="/submit_payment" method="post">
            <label for="name">이름:</label>
            <input type="text" id="name" name="name" required>

            <label for="email">이메일:</label>
            <input type="email" id="email" name="email" required>

            <label for="address">배송 주소:</label>
            <textarea id="address" name="address" rows="4" required></textarea>

            <label for="phone">전화 번호:</label>
            <input type="text" id="phone" name="phone" required>

            <label for="card_number">카드 번호:</label>
            <input type="text" id="card_number" name="card_number" required>

            <label for="expiry_date">만료 날짜:</label>
            <input type="text" id="expiry_date" name="expiry_date" placeholder="MM/YY" required>

            <label for="cvv">CVV:</label>
            <input type="text" id="cvv" name="cvv" required>

            <label for="payment_method">결제 수단:</label>
            <select id="payment_method" name="payment_method" required>
                <option value="">결제 수단 선택</option>
                <option value="credit_card">신용 카드</option>
                <option value="debit_card">직불 카드</option>
                <option value="paypal">PayPal</option>
                <option value="bank_transfer">은행 이체</option>
            </select>

            <input type="submit" value="결제하기">
        </form>
    </div>
</body>
</html>