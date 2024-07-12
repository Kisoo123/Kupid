<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ArtistPage</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: black;
            color: white;
        }
        .container {
            width: 80%; 
            margin: 0 auto;           
    		padding-top: 80px; /* navbar 높이만큼 패딩 추가 */
    	}
        
        .header {
            text-align: center;
            margin-bottom: 0px;
            pointer-events: none;
        }
        .header iframe {
	         position:relative;
	         /* left:-100; */
	         top: -60px;
	   		 bottom: -60px;
	         width: 1400px;
	         height: 600px;
	         margin-bottom: 10px; 
	         margin-left:70px;
        }
        .header::after {
		    content:'';
		    position: relative;
		    top: 0;
		    left: 0;
		    width: 100%;
		    height: 100%;
		    z-index: 1;
		}
        .member-photos {
            display: flex;
            justify-content: center;
            gap: 30px;
            margin-bottom: 70px;
        }
	    .member-photo {
	    text-align: center;
	    transition: transform 0.3s ease; 
		}
		
		.member-photo img {
		    width: 100px;
		    height: 130px;
		    border-radius: 10px;
		}
		
		.member-photo:hover {
		    transform: scale(1.2); 
		}

        .icons {
            text-align: center;
            margin-bottom: 100px;
        }
        .icons img {
		    width: 50px; 
		    height: auto; 
		    margin: 0 10px; 
		}
        .social-icons {
            display: flex;
            justify-content:center;
            gap: 10px;
        }
        .social-icons a {
            text-decoration: none;
        }
        .membership-button {
            text-align: center;
            position: fixed;
		    bottom: 0;
		    width: 100%;
		    left: 0;
		    padding: 0px 0px 30px;
		    z-index: 1000;
        }
        
        .membership-button button {
		    background-color: #fff;
		    border-radius: 100px;
		    box-shadow: 0 10px 60px 0 #000c;
		    color: #000;
		    display: inline-block;
		    font-size: 15px;
		    font-weight: 700;
		    line-height: 31px;
		    padding: 8px 15px;
		    vertical-align: top;
        }
        .pre {
        	margin-top: 0px;
   	      	text-align: center;
        }
        .membership-button button:hover {
            background-color: rgb(226, 196, 239);
        }
        .section-title {
            font-size: 24px;
            margin-bottom: 20px;
            color: white;
        }
        .media-videos, .membership-videos {
	        display: flex;
	        justify-content: space-between;
	        margin-bottom: 70Px;
	        pointer-events: none;
    	}
        
        .media-videos iframe, .membership-videos iframe {
            display:flex;
            justify-center:space-between;
            left:0px;
            width: 300px;
            height: 169px;
            flex-shrink: 0;
        }
        .blur {
            filter: blur(5px);
        }
        .shop-item {
            width: 200px;
            text-align: center;
            flex-shrink: 0;
            margin-botton: 70px;
        }
        .shop-item img {
            width: 80%;
            heigt: 40%;
            
        }
        .shop-item img:hover {
  		 transform: scale(1.1);
		}
        .shop-items{
            display: flex;
            justify-content: flex-start;
            gap: 30px;
            margin-bottom: 70px;
            flex-wrap: nowrap;
            overflow-x: auto;
        }
        .calendar {
            filter: blur(5px);
            margin-bottom: 40px;
            text-align:center;
            margin-bottom:70px
        }
        .social-icons img.youtube {
			 margin-top: 10px; 
		}

        .logo-container {
 	    flex: 0 0 auto; 
		}	

		.logo {
  	     height: 60px; 
  	     width:auto;
         position: absolute;
         top: 10px; 
         left: 10px; 
         width: 150px; 
         z-index: 1000; 
  	    
 		}     
		.navbar {
	    display: flex;
	    justify-content: space-between;
	    align-items: center;
	    background-color: black;
	    padding: 10px 20px;
	    position: absolute;
	    top: 0;
	    width: 100%;
	    z-index: 1000;
	    height:100px;;
		}
		.header img, .container > .header > pre {
		    margin: 0 auto;
		    display: block;
		}
    </style>
</head>

<body>	
		<nav class="navbar">
        <div class="logo-container">
		<a href="<%=request.getContextPath()%>/">
        <img src="<%=request.getContextPath()%>/image/artistpage/KUPID-removebg-preview.png" alt="Logo" class="logo" >
		</a>
		</div>



	</nav>
	
	    <div class="container">
	        <div class="header">
	            <!-- 대표 영상 -->
	            <iframe src="https://www.youtube.com/embed/Ey53EQhkLY8?autoplay=1&mute=1&controls=0&start=22" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
	            	<img src="data:image/png;base64,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">
	            	<pre class="pre">
RIIZE(라이즈)는 ‘Rise & Realize’를 더해 만든 이름으로, 함께 성장하고
                  꿈을 실현해 나아가는 팀이라는 의미다.

각자의 개성을 지닌 일곱 명의 멤버가 하나의 팀으로서 쌓아가는 리얼타임 오디세이(성장사)를
    바탕으로, RIIZE만의 감정을 표현하는 독자적 음악 스타일인 Emotional Pop을 선사한다.
   RIIZE는 일상의 모든 경험에서 영감을 얻는 Emotional Pop Rookie가 성장해, 
    세상 모두의 공감을 불러일으키는 Emotional Pop Artist로서의 꿈을 실현하겠다는 포부를 가지고 있다.
            	</pre>
        </div>
          <div class="media-section">
            <div class="section-title">Profile</div>
		        <div class="member-photos">
		            <!-- 멤버별 사진 -->
		            <div class="member-photo">
		                <img src="https://search.pstatic.net/common?type=b&size=3000&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2FprofileImg%2Fed820b8e-777c-4e7a-962d-2ef1917d7bc9.jpg" alt="WONBIN">
		                <p>WONBIN</p>
		            </div>
		            <div class="member-photo">
		                <img src="https://search.pstatic.net/common?type=b&size=3000&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2FprofileImg%2F35907455-0647-4976-9b7e-fb6f3aabd0a0.jpg" alt="ANTON">
		                <p>ANTON</p>
		            </div>
		            <div class="member-photo">
		                <img src="https://search.pstatic.net/common?type=b&size=3000&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2FprofileImg%2F8a0cd064-f916-42ce-9eb1-5732cbbc0608.jpg" alt="SOHEE">
		                <p>SOHEE</p>
		            </div>
		            <div class="member-photo">
		                <img src="https://search.pstatic.net/common?type=b&size=3000&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2FprofileImg%2F55982851-20de-4ab5-b19c-92c4908c5f72.jpg" alt="ENSEOK">
		                <p>ENSEOK</p>
		            </div>
		            <div class="member-photo">
		                <img src="https://search.pstatic.net/common?type=b&size=3000&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2FprofileImg%2F736edaee-5fb4-4444-891d-bcdfb32d6216.jpg" alt="SHOTARO">
		                <p>SHOTARO</p>
		            </div>
	            </div>
        </div>
        <div class="media-section">
            <div class="section-title">Media</div>
            <div class="media-videos">
                <!-- 유튜브 영상들 -->
                <iframe src="https://www.youtube.com/embed/0TAAUWHo4Ec?autoplay=1&mute=1&controls=0" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                <iframe src="https://www.youtube.com/embed/iUw3LPM7OBU?autoplay=1&mute=1&controls=0&start=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                <iframe src="https://www.youtube.com/embed/gJMheHHf4GQ?autoplay=1&mute=1&controls=0&start=3" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                <iframe src="https://www.youtube.com/embed/2H0duKgnYgE?autoplay=1&mute=1&controls=0&start=5" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
            </div>
        </div>
        <div class="membership-section">
            <div class="section-title">Membership</div>
            <div class="membership-videos">
                <!-- 블러 처리된 유튜브 영상들 -->
                <iframe class="blur" src="https://www.youtube.com/embed/xbEUaaRlZl4?autoplay=1&mute=1&controls=0&start=1150" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                <iframe class="blur" src="https://www.youtube.com/embed/lPKynzuWt58?autoplay=1&mute=1&controls=0&start=121" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                <iframe class="blur" src="https://www.youtube.com/embed/HLrNjCXNNa4?autoplay=1&mute=1&controls=0&start=1198" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                <iframe class="blur" src="https://www.youtube.com/embed/u25WgPC7ACU?autoplay=1&mute=1&controls=0&start=727" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
            </div>
        </div>
        <div class="shop-section">
            <div class="section-title">Shop</div>
            <div class="shop-items">
                <!-- 상품 카드들 -->
                <div class="shop-item">
                    <img src="https://cdn-contents.weverseshop.io/public/shop/636a3419bbbbf77462d67a9db5b07fe7.png?q=95&w=720" alt="Item 1">
                    <p>1st Mini Album [RIIZING] (SMini Ver.)</p>
                    <p>₩13,300</p>
                </div>
                <div class="shop-item">
                    <img src="https://cdn-contents.weverseshop.io/public/shop/7bde05dc407b56d33f6d17395de85237.png?q=95&w=720" alt="Item 2">
                    <p>1st Single Album [Get A Guitar] (Random)</p>
                    <p>₩14,800</p>
                </div>
                <!-- 추가 상품 카드들 필요 시 여기에 추가 -->
            </div>
        </div>
        <div class="calendar-section">
            <div class="section-title">Calendar</div>
            <!-- 블러 처리된 캘린더 -->
               <div class="calendar">
    		    <img src="https://blog.kakaocdn.net/dn/yBMRy/btrRvcufQI4/smYyR8LB9KZhNwyeKychxk/2023%EB%85%84%206%EC%9B%94%20%EB%8B%AC%EB%A0%A5.jpg?attach=1&knm=img.jpg" alt="Calendar" style="width: 40%; display: block; margin: 0 auto;">
  		    </div>

        </div>
        <div class="icons">
            <div class="social-icons">
                <!-- 페이스북, 유튜브, 인스타그램 아이콘 -->
                <a href="https://www.instagram.com/riize_official/" target="_blank"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZF4DTH7F_qryjx-dGzUr5Nk3HfaZ8DVd1qw&usqp=CAU" alt="Instagram"></a>
                <a href="https://twitter.com/riize_official" target="_blank"><img src="https://img.freepik.com/free-vector/new-2023-twitter-logo-x-icon-design_1017-45418.jpg?size=338&ext=jpg&ga=GA1.1.44546679.1716422400&semt=ais_user"></a>
                <a href="https://www.youtube.com/channel/UCdVD0MsYecQaIE5Ru-pOIQQ" target="_blank">
                <img class="youtube"  src="https://cdn.xsd.cz/resize/f0dc0d87befc36faa8bbcee4d1022615_resize=1454,1026_.jpg?hash=d0ce1554e3114442918b017dd230c7b2" alt="Twitter"></a>
            </div>
        </div>
        <div class="membership-button">
  	    <button onclick="location.assign('<%= request.getContextPath() %>/marketview.do')">멤버쉽 가입하기 ></button>
  	    <button onclick="location.assign('<%= request.getContextPath() %>/feed/feedView.do?groupno=3')">구독하기 ></button>
		</div>
    </div>   
    <script>
        
        document.querySelectorAll('.membership-section').forEach(function(video) {
            video.addEventListener('click', function() {
                showMembershipPopup();
            });
        });
 
        
        document.querySelector('.calendar').addEventListener('click', function() {
            showMembershipPopup();
        });
        
        document.querySelector('.shop-items').addEventListener('click', function() {
            showMembershipPopup();
        });


        
        document.querySelectorAll('.media-section').forEach(function(video) {
            video.addEventListener('click', function() {
            	showMediaPopup();
            });
        });


        function showMediaPopup() {
            alert("구독이 필요한 서비스입니다");
        }
   

       
        function showMembershipPopup() {
            alert("멤버쉽 가입이 필요한 서비스입니다");
        }
    
       </script>
</body>
</html>