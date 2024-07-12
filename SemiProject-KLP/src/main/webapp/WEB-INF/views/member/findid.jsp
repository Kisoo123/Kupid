<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    padding: 30px;
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
    gap: 0px 15px;
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
  }
  .check_email .btn {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 83px;
  height: 36px;
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
	justify-content: space-between;
	align-content: flex-end;
 	gap: 10px;
    margin: 10px auto;
	display: none;
}
.ckeck_email .result_container{
	display: none;
	justify-content: center;
	flex-direction: column;
	align-items: center;
}
	
</style>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<section>
<div class="check_email">
	  <h2 class="title">아이디 찾기</h2>
	  <p class="title">아이디 찾기를 위한 회원 인증을 진행합니다.<!-- <br>가입시 사용한 메일 주소를 입력해주세요. --></p>
	  <div class="flex_row mt-2">
		    <div class="mb-1">이름</div> <input required class="form-control w-25" name="name" id="input_name" placeholder="이름" value="">
	  </div>
	  <div class="flex_row mt-2">
	     	<div class="mb-1">생일</div> <input required type="date" class="form-control w-25" name="birth" id="input_birth">
	  </div>
	  <div class="flex_row mt-2">
		    <div class="mb-1">메일</div> <input required class="form-control w-25" name="email" id="input_email" placeholder="메일 주소" value="">
	  </div>
		<div class="d-flex justify-content-center py-4">
		    <button class="seartchBtn btn btn-outline-secondary" id="sendBtn">전송</button>
		</div>
	  <div id="result_container" class="check_email result_container p-0 align-items-center">
		  	<div id="result"></div>
			<div class="btn_container" id="btn_container">
			  <button class="btn btn-outline-secondary" id="okBtn">확인</button>
		  </div> 
	  </div>
</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<script>
	$("#input_email").on('input', e=>{
		let target = e.target.value;
		target = target.replace(/[^a-zA-Z0-9ㄱ-ㅎ가-힣._!@#$%^&*]/g, '');
		e.target.value = target;
	});
	$("#input_name").on('input', e=>{
		let target = e.target.value;
		target = target.replace(/[^가-힣ㄱ-ㅎ]/g, '');
		e.target.value = target;
	});
	$("#sendBtn").click(e=>{
		if($("input#input_email").val().trim() ===""){
			alert('이메일을 입력해주세요.');
		}else if($("input#input_name").val().trim() ===""){
			alert('이름을 입력해주세요.');
		}else if($("input#input_birth").val().trim() ===""){
			alert('생일을 입력해주세요.');
		}else{
			$.ajax({
				url: "<%=request.getContextPath()%>/member/findid/send.do",
				data: {
					"email": $("input#input_email").val(),
					"name" : $("input#input_name").val(),
					"birth" : $("input#input_birth").val()
				},
				success: data=>{
					console.log(data);
					if(data === '1'){
						const $div = $("<div>").addClass('justify-content-center d-flex');
						const $input = $("<input>").attr({'name': 'code', 'id':'codeck', 'placeholder': '인증코드를 입력해주세요'}).css( 'width', '250px').addClass('form-control');
						const $p= $("<p>").text("인증코드가 해당 메일로 전송되었습니다.").css({'text-align': 'center', 'margin': '10px'}).addClass('title');
						$input.appendTo($div);
						$div.appendTo($("#result"));
						$("#result_container").addClass('d-flex');
						$("#btn_container").addClass('d-flex');
						$("#result_container").prepend($p);
						
						$("#codeck").on('input', e=>{
							let target = e.target.value;
							target = target.replace(/[^0-9]/g, '');
							e.target.value = target;
						});
					}else{
						alert('없는 회원입니다.');
					}
				}
			});
		}
	});
	//인증코드 유효성 검증
	$('#okBtn').click(e=>{
		//입력값 유효성
		if($("input#codeck").value ===""){
			alert('이메일 주소를 입력해주세요.');
		}else{
			let encodeEmail = encodeURIComponent($("#input_email").val());
			$.ajax({
				url: "<%=request.getContextPath()%>/member/findid/emailCheckEnd.do",
				data: {
					"value": $("input#codeck").val(),
					"email" : $('input#input_email').val(),
					"name" : $("input#input_name").val(),
					"birth" : $("input#input_birth").val()
				},
				method : "post",
				success: data=>{
					const jdata = JSON.parse(data);
					if(jdata !== null){
						alert('이메일이 확인되었습니다.');
						$("#result_container").html('');
						let result = '회원님의 아이디는 "' + jdata.memberId + '"입니다.';
						$("<h3>").text(result).addClass('title py-3').attr({'text-align': 'center', 'color': 'red'}).appendTo($("#result_container"));
						$("<button>").addClass('btn btn-outline-secondary').attr({'id': 'toMain', 'onclick': 'toMain();'}).text('메인으로').appendTo("#result_container");
					} else{
						alert('코드를 다시 확인해주세요');
					}
				}
			});
		}
	});
	const toMain=()=>{
		window.location.href = '<%=request.getContextPath()%>/';
	}
</script>