<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String email =(String) (request.getAttribute("email"));
%>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<style>
body{
     font-family: 'DungGeunMo';
     src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/DungGeunMo.woff') format('woff');
     font-weight: normal;
     font-style: normal;
     letter-spacing: -1.36px;
     font-size: 1.1rem;
	}
.check_email {
    position: relative;
    display: flex;
    flex-direction: column;
    background-color: rgb(255, 255, 255);
    overflow: hidden;
    padding: 10px;
  }
  .check_email .title {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 9.5px auto;
    color: rgb(0, 0, 0);
    text-align: center;
  }
  .check_email .myFavoriteArtist {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 10px;
  }
  .check_email .flex_row {
    position: relative;
    display: flex;
    gap: 0px 6px;
    margin-top-bottom: 20px;
    justify-content: center;
	align-items: end;
  }
  .check_email .input_email {
      width: 100%;
	  color: black;
	  background-color: transparent;
	  border: 0px;
	  padding: 0px;
	  vertical-align: top;
  }
  .check_email .seartchBtn {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 1px 0px;
    min-width: 37px;
    color: rgb(0, 0, 0);
    text-align: center;
    background-color: rgb(224, 224, 224);
    border-radius: 7px 7px 7px 7px;
    padding: 3.5px 8px 3.5px 8px;
    box-shadow: 0px 1px 2px 0px rgba(0, 0, 0, 0.047);
    height: 30px;
  }
  .check_email .btn {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 83px;
  height: 30px;
  max-width: 100%;
  color: black;
  text-align: center;
  background-color: #d1ade3;
  border-radius: 8px 8px 8px 8px;
  padding: 8px 8px 8px 8px;
  }
  .check_email .input_box {
  position: relative;
  margin: 0px;
  margin-top: 8px;
  border-radius: 8px 8px 8px 8px;
  padding: 8px 16px 8px 16px;
  outline: 1px solid #e0e0e0;
  outline-offset: -1px;
  min-width: 200px;
}
 .check_email .btn_container{
	display: flex;
	justify-content: space-between;
	align-content: flex-end;
 	gap: 10px;
    margin: 10px auto;
}
.ckeck_email .result_container{
	display: flex;
	justify-content: center;
	flex-direction: column;
}
	
</style>
<div class="check_email">
  <h2 class="title">이메일 확인</h2>
  <div class="flex_row">
	  	<div class="input_box">
		    <input class="input_email" name="email" id="input_email" placeholder="변경할 메일 주소 입력" value=<%=email %>>
	    </div>
	    <button class="seartchBtn" id="sendBtn">전송</button>
  </div>
  <div id="result_container" class="check_email">
  </div>
  <div class="btn_container">
	  <button class="btn" id="okBtn">확인</button>
	  <button class="btn" id="cancelBtn">취소</button>
  </div>
</div>
<script>
	$('input#input_email').keyup(e=>{
		console.log(e.target.value);
		let target = e.target.value;
		target = target.replace(/[^a-zA-Z0-9ㄱ-ㅎ가-힣._!@#$%^&*]/g, '');
		e.target.value = target;
	});
	$("#sendBtn").click(e=>{
		if($("input#input_email").value ===""){
			alert('값을 입력해주세요.');
		}else{
			$("#result_container").html('');
			$.ajax({
				url: "<%=request.getContextPath()%>/member/signupemailSend.do",
				data: {
					"email": $("input#input_email").val(),
				},
				success: data=>{
					console.log(data);
					if(data === '1'){
						$("<h4>").text("인증코드가 해당 메일로 전송되었습니다.").css({'text-align': 'center', 'margin': '10px'}).appendTo($("#result_container"));
						const $div = $("<div>").css('margin', '0px auto');
						const $inputbox = $("<div>").addClass('input_box').css('justify-content', 'center');
						const $input = $("<input>").attr({'name': 'code', 'id':'codeck', 'placeholder': '인증코드를 입력해주세요'}).addClass('input_email').css('justify-content', 'center');
						$inputbox.append($input).appendTo($div);
						$div.appendTo($("#result_container"));
					}else{
						alert('메일 전송에 실패했습니다. 관리자에게 문의해주세요!');
					} 
				}
			});
		}
	});
	//인증코드 유효성 검증
	$('#okBtn').click(e=>{
		//입력값 유효성
		if($("input#codeck").value ===""){
			alert('값을 입력해주세요.');
		}else{
			let encodeEmail = encodeURIComponent($("#input_email").val());
			$.post({
				url: "<%=request.getContextPath()%>/member/signupemailCheckEnd.do",
				data: {
					"value": $("input#codeck").val(),
					"email" : '<%=email%>',
				},
				method : "post",
				success: data=>{
					console.log(data);
					if(data === '1'){
						alert('이메일이 확인되었습니다.');
						if (window.opener && !window.opener.closed) {
							window.opener.setEmail($("#input_email").val());
							window.close();
						}
					}else{
						alert('코드를 다시 확인해주세요');
					}
				}
			});
		}
	});
	$("#cancelBtn").click(e=>{
		let result = confirm('실행을 취소할까요?');
		if(result==true) this.window.close();
	});
</script>