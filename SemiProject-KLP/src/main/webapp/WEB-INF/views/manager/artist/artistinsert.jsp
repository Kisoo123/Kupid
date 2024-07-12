<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kupid.group.model.dto.GroupDto" %>    
<% 
	List<GroupDto> group=(List<GroupDto>)request.getAttribute("group");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
	.join{
		width: 90vw;
		justify-content: center;
    	display: flex;
    	margin: 20px auto;
	}
	.join>div{
		padding: 125px;
	}
	.title {
	  position: relative;
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  color: black;
	  text-align: center;
	  font-size: 2.2em;
	}
	.flex_col {
	    position: relative;
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    width: 90vw; /* 90% of viewport width */
	    max-width: 490px;
	    border-radius: 8px 8px 8px 8px;
	    outline: 1px solid #e0e0e0;
	    outline-offset: 70px;
	}
	.flex_col2 {
	    outline: 1px solid #e0e0e0;
	    border-radius: 8px 8px 8px 8px;
	    position: relative;
	    display: flex;
	    flex-direction: column;
	    padding: 10px;
	    gap: 5px;
	    margin: 30px 0px 20px auto;
	    background-color: rgb(238, 238, 238);
	}
	.inputTag {
	  width: 100%;
	  color: black;
	  background-color: transparent;
	  border: 0px;
	  padding: 0px;
	  vertical-align: top;
	}
   .btn {
	  position: relative;
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  margin: 0px auto;
	  width: 83px;
	  max-width: 100%;
	  color: black;
	  text-align: center;
	  background-color: #d1ade3;
	  border-radius: 8px;
	  padding: 8px 8px 8px 8px;
	}
	.btn2 {
	   position: relative;
	   display: flex;
	   justify-content: center;
	   align-items: center;
	   min-width: 75px;
	   color: black;
	   text-align: center;
	   background-color: #e9bcff;
	   border-radius: 8px;
	   padding: 4px 8px 4px 8px;
	   border: none;
	   height: 30px;
	}
	.input_box {
	  position: relative;
	  margin: 8px 0px 0px;
	  min-width: 0px;
	  border-radius: 8px 8px 8px 8px;
	  padding: 8px 16px 8px 16px;
	  outline: 1px solid #e0e0e0;
	  outline-offset: -1px;
	}
	.input_box_fail {
	  background-color: #ffc6c6;
	}
	.h3_btn_container{
		display: flex;
		gap: 15px;
		align-items: center; /* 수직 중앙 정렬 */
		justify-content: space-between;
		margin: 0px;
		padding: 0px;
	}
	.result-container{
		display: flex;
		gap: 10px;
	}
	.btn_container{
		display: flex;
		justify-content: space-between;
	 	gap: 10px;
	}
	.join_container{
		position: relative;
	    display: flex;
	    flex-direction: column;
	    margin: 8px 0px 0px;
	    background-color: white;
	    outline-offset: -1px;
	    width: 400px;
	}
	.part{
		border: 1px solid white;
		margin: 0px 0px 20px 0px;
	}
	.part-none{
		border: 1px solid white;
		margin: 0px 0px 20px 0px;
		display: none;
	}
	.radio_box{
		gap: 15px;
		display: flex;
		align-items: center;
	}
	.readonly_box{
		background-color: #ededed;
	}
	.part .p{
		margin: 0px;
	}
	.flex_col2 .ck{
		text-decoration: none;
		color: black;
	}
	.flex_col2 .ck:hover{
		text-decoration: underline;
	}
</style>
<section class="join">
	<div>
	<div class="flex_col">
		<div class="join_container">
	    <h1 class="title">아티스트 등록</h1>
			<form action="<%=request.getContextPath() %>/member/artistinsertend.do" method="post" style="margin-bottom: 0px" onsubmit='fnSignupCk(event);'>
				<div class="part">
					<div class="result-container">
					<h3>아이디</h3>
					<h5 id="idResult"></h5>
					</div>
			        <div class="input_box">
				         <input required type="text" class="inputTag" name="id" id="id"  placeholder="아이디 입력">
			        </div>
		        </div>
		        <!-- <div class="part-none">
		        <div class="result-container">
                	<h3>닉네임</h3>
                	<h5 id="nicknameResult"></h5>
               	</div>
                <div class="input_box">
                 <input type="text" name="nickname" placeholder="닉네임"  id="nickname" class="inputTag" value="">
                </div>
                </div> -->
		        <div class="part">
		        <div class="result-container">
				<h3>비밀번호</h3><h5 id="pwckResult"></h5>
				</div>
		        <div class="pw_box">
                	<p class="p">영문, 숫자, 특수문자가 포함된 4~20글자 사용가능</p>
                </div>
		        <div class="input_box">
		         	<input required type="password" class="inputTag" name="pw" id="pw" placeholder="비밀번호 입력">
		        </div>
		        <div class="input_box">
		        	 <input required type="password" class="inputTag" id="pwck" placeholder="비밀번호 확인">
		        </div>
		        </div>
		        <div class="part">
				<h3>이름</h3>
			     <div class="input_box">
			      	<input required type="text"  placeholder="이름" class="inputTag" name="name" id="name">
			    </div>
			    </div>
		        <div class="part">
				<h3>생년월일</h3>
			     <div class="input_box">
			      	<input required type="date"  class="inputTag" name="birth" id="birth">
<!-- 			      	<input required type="date"  placeholder="" class="inputTag" name="name" id="name"> -->
			    </div>
			    </div>
			    <div class="part">
				<h3>성별</h3>
				<div class="radio_box">
					<div>
					<input type='radio' name='gender' id="female" value='female'>
					<label for="female">여성</label>
					</div>
					<div>
					<input type='radio' name='gender' id="male" value='male'>
					<label for="male">남성</label>
					</div>
					<!-- <div>
					<input type='radio' name='gender' id="none" value='none'>
					<label for="none">모름</label>
					</div> -->
				</div>
				</div>
				<div class="part">
				<h3>연락처</h3>
		        <div class="input_box">
		         	<input required type="text" class="inputTag" name="phone" id="phone" placeholder="'-'를 제외하고 숫자만 입력">
		        </div>
		        </div>
		        <div class="part">
		        <div class="h3_btn_container">
			       	<h3>이메일</h3>
			       	<button class="btn2" type="button" onclick="emailValidCk();">이메일 검증</button>
		       </div>
		        <div class="input_box">
		        	 <input required type="text" name="email" placeholder="예) kupid_79@naver.com" class="inputTag" id="inputEmail" value="">
		        </div>
		        </div>
		        <div class="part">
		         <div class="h3_btn_container">
			       	<h3>주소</h3>
			       	<button class="btn2" type="button" id="searchAddress" onclick="addressSearch();">주소 검색</button>
		      	</div>
		        <div class="input_box readonly_box">
		      		<input type="text" id="inputAddress" class="inputTag" placeholder="주소검색"  name="address" readOnly value="">
		      	</div>
		      	<div class="input_box">
		      		<input type="text" id="inputAddressDetail" class="inputTag" name="addressDetail" placeholder="상세주소 입력"  value="">
		     	</div>
		     	</div>
		     	<div>
		     		<%for(GroupDto g : group){ %>
		     			<p><%=g.getGroupNo()%> : <%=g.getGroupName() %></p>
		     		<%} %>
		     	</div>
		     	<div class="part">
				<h3>GroupNo</h3>
			     <div class="input_box">
			      	<input required type="number"  placeholder="그룹번호" class="inputTag" name="groupNo" id="groupNo">
			    </div>
			    </div>
		     	<!-- <div class="part">
				<h3>관심 아티스트 (선택)</h3>
		        <div class="input_box">
		        	<input type="text" name="favorite" id="pickArtist" class="inputTag" style="color: #828282" readOnly placeholder="관심 아티스트를 골라주세요" value=''>
		        </div>
		        </div> -->
	     		<%-- <div class="flex_col2">
	     			<div>
			    		<input type='checkbox' name='ckAll' id="ckAll"><label for="ckAll"> (필수, 선택) 전체 동의</label>
			    	</div>
			    	<div>
			        	<input required type='checkbox' name='ck1' id="ck1"><label for="ck1"><a class="ck" target="_blank" rel="noopener noreferrer" href="<%=request.getContextPath()%>/policies/terms?no=1"> (필수) 개인회원 약관 동의</a></label>
			        </div>
			        <div>
			        	<input required type='checkbox' name='ck2' id='ck2'><label for="ck2"> <a class="ck" target="_blank" rel="noopener noreferrer" href="<%=request.getContextPath()%>/policies/terms?no=2"> (필수) 개인정보 수집 및 이용 약관에 동의</a></label>
			        </div>
			        <div>
			        <input type='checkbox' name='ck3' id='ck3'><label for="ck3"> <a class="ck" target="_blank" rel="noopener noreferrer" href="<%=request.getContextPath()%>/policies/terms?no=3"> (선택) 마케팅 정보 수신 동의 - 이메일, SMS/MMS</a></label>
			    	</div>
			</div> --%>
			<br>
	    	<div class="btn_container">
		        <button class="btn" id="submit">등록</button>
		       	<button class="btn" type="button" id="cancelBtn">취소</button>
	       </div>
	    </form>
        </div>
     </div>
     </div>
 </section>
<script>
	$("#name").keyup(e=>{
		let target = e.target.value;
		target = target.replace(/[^가-힣]/g, '');
		e.target.value = target;
		console.log(target);
	});
	$("#phone").keyup(e=>{
		let target = e.target.value;
		target = target.replace(/[^0-9]/g, '');
		e.target.value = target;
		console.log(target);
	});
	$("#pw").keyup(e=>{
		let target = e.target.value;
		console.log(target);
		target = target.replace(/[^a-zA-Z0-9ㄱ-ㅎ가-힣!@#$%^&*]/g, '');
		e.target.value = target;
		const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{4,20}$/;
		if(regex.test(e.target.value)){
			$(".pw_box .p").text('사용가능한 비밀번호입니다.').text('영문, 숫자, 특수문자가 포함된 4~20글자 사용가능').css("color",'black');
		}else{
			$(".pw_box .p").text('영문, 숫자, 특수문자가 포함된 4~20글자만 사용가능합니다.').css("color","#ff5e5e");
		}
	});
	$("#pwck").keyup(e=>{
		console.log(e.target.value);
		console.log($("#pw").val());
		let input = e.target.value;
		
		let target = input;
		target = target.replace(/[^a-zA-Z0-9ㄱ-ㅎ가-힣!@#$%^&*]/g, '');
		input = target;
		if($("#newpw").val() !== ""){
			if(input ===$("#pw").val()){
				$("#pwckResult").text('비밀번호가 일치합니다.').css("color","#c552ff");
				$("#newpwck").parent().removeClass('input_box_fail');
				if($("#newpwck").blur()){
					$("#pwckResult").text('');
				}
			}else{
				$("#pwckResult").text('비밀번호가 일치하지 않습니다.').css("color","#ff5e5e");
				$("#newpwck").parent().addClass('input_box_fail');
			}
		} else{
			$("#pwckResult").text('');
		}
	});
	$("#id").keyup(e=>{
		console.log(e.target.value);
		//정규식 활용한 문자열 필터링
		let target = e.target.value;
		target = target.replace(/[^a-zA-Z0-9ㄱ-ㅎ가-힣_]/g, '');
		e.target.value = target;
		//빈칸 입력 방지
		if(e.target.value !== ""){
		//중복검사
		$.ajax({
			url: "<%=request.getContextPath()%>/member/checkid.do",
			type: "post",
			data: {"id": target},
			success: data=>{
				console.dir(data);
				$("#idResult").text("");
				if(data==0){
					$("#idResult").text("* 사용가능한 아이디입니다.").css("color","#c552ff");
				} else if(data!=0){
					$("#idResult").text("* 이미 사용중인 아이디입니다.").css("color","#ff5e5e");
				} 
			}
		});
		}else{
			$("#nicknameResult").text("* 내용을 입력해주세요").css("color","gray");
		}
	});
	$("#nickname").keyup(e=>{
		console.log(e.target.value);
		//정규식 활용한 문자열 필터링
		let target = e.target.value;
		target = target.replace(/[^a-zA-Z0-9ㄱ-ㅎ가-힣]/g, '');
		e.target.value = target;
		//빈칸 입력 방지
		if(e.target.value !== ""){
		//중복검사
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/checknickname.do",
			type: "post",
			data: {"nickname": target},
			success: data=>{
				console.dir(data);
				$("#nicknameResult").text("");
				if(data==0){
					$("#nicknameResult").text("* 사용가능한 닉네임입니다.").css("color","#c552ff");
				} else if(data!=0){
					$("#nicknameResult").text("* 이미 사용중인 닉네임입니다.").css("color","#ff5e5e");
				} 
			}
		});
		}else{
			$("#nicknameResult").text("* 변경할 닉네임을 입력해주세요").css("color","gray");
		}
	});
	$('#ckAll').change(e=>{
		const div = $(e.target).parent().nextAll()
		if(e.target.checked){
			console.dir(div);
			$(div).each( (i,o)=>{
				o.children[0].checked=true;
			}); 
		}else{
			$(div).each( (i,o)=>{
				o.children[0].checked=false;
			}); 
		}
	});
	const emailValidCk=()=>{
		console.log('email');
		if($("#inputEmail").val()!==""){
			const target = $("#inputEmail").val();
			window.open(`<%=request.getContextPath()%>/member/signupemailCheck.do?email=\${target}`,'emailVerification','width=570px, height=320px');
		}else{
			alert('이메일 주소를 먼저 입력해주세요.')
		}
	};
	$("#cancelBtn").click(e=>{
		let result = confirm('실행을 취소할까요?');
		if(result==true) {
			location.assign('<%=request.getContextPath()%>/manager/artistlist.do');
		}
	});
	function setEmail(email){
		console.log(email);
		$(document).ready();
		$("#inputEmail").val(email);
		$("#inputEmail").attr('readOnly', true);
		$("#inputEmail").parent().addClass("readonly_box");
	};
	const fnSignupCk =(e)=>{
		const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{4,20}$/;
		//비밀번호 암호화 전까지 잠시 보류
		/* if(!regex.test($("#newpw").val())){
			alert('영문, 숫자, 특수문자가 포함된 4~20글자 비밀번호가 아닙니다.');
			e.preventDefault();
			$("#newpw").focus();
			return false;
		} */
		if($("#pwck").val()!== $("#pw").val()){
			alert('비밀번호 확인 값이 일치하지 않습니다.');
			$("#pwch").focus();
			e.preventDefault();
			return false;
		}
	    // 모든 input 필드가 비어있지 않은지 확인
 		let isValid=true;
	    $("form input").each(function() {
	        if ($(this).val().trim() === "") {
	            alert('내용을 입력해주세요.');
	            $(this).focus();
	            isValid = false;
	            e.preventDefault();
	            return false; // jQuery each의 루프 종료
	        }
	    }); 
	};
	<%-- #("#pickArtist").click(e=>{
		window.open('<%=request.getContextPath()%>/member/favoriteArtist.select','_blank','width=870px, height=930px');
	}); --%>
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	const addressSearch=()=>{
		let width= 500;
		let height= 772;
		new daum.Postcode({
			theme: themeObj,
			oncomplete: data=>{
				document.querySelector("#inputAddress").value= data.address;
				document.querySelector("#inputAddressDetail").value= "";
				document.querySelector("#inputAddressDetail").focus();
			}
		}).open({
			left: (window.screen.width/2)-(width/2),
			top: (window. screen.height/2)-(height/2),
			popupTitle: '우편번호 검색',
		});
	};
	var themeObj = {
			   bgColor: "#ececec", //바탕 배경색
			   searchBgColor: "#ffffff", //검색창 배경색
			   contentBgColor: "ffffff", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
			   pageBgColor: "fafafa", //페이지 배경색
			   textColor: "#333333", //기본 글자색
			   queryTextColor: "#222222", //검색창 글자색
			   postcodeTextColor: "#fa4256", //우편번호 글자색
			   emphTextColor: "#008bd3", //강조 글자색
			   outlineColor: "#e0e0e0" //테두리
			};
</script>