<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
 .slider-container {
     width: 600px;
     height: 300px;
     overflow: hidden;
     margin-left: 450px;
     margin-top: 100px;
     border: 1px solid #ccc;
 }
 
 .slider {
     width: 1800px; /* (600px * 3) */
     height: 300px;
     display: flex;
     transition: transform 0.5s ease;
 }
 
 .slide {
     width: 600px;
     height: 300px;
 }

 .slide img {
     width: 100%;
     height: 100%;
     object-fit: cover;
 }

 .prev, .next {
     position: absolute;
     top: 38%;
     transform: translateY(-50%);
     background-color: rgba(255, 255, 255, 0.5);
     color: #333;
     font-size: 24px;
     padding: 10px;
     cursor: pointer;
     transition: background-color 0.3s ease;
 }

 .prev:hover, .next:hover {
     background-color: rgba(0, 0, 0, 0.5);
     color: #fff;
 }

 .prev {
     left: 10px;
 }

 .next {
     right: 10px;
 }
 #logo{
     width: 400px;
     height: 160px;
     position: absolute;
     left: 555px;
     top: -40px;
 }
 
 h3{
     text-align: center;
     position: relative;
    top: 20%;
 }
 #icon1{
     position: absolute;
     top: 5%;
     right: 2%;
     border: 1px solid red;
     width: 30px;
     height: 30px
     
 }
 .Icon1{
     width: 30px;
     position: absolute;
     right: 0px;
 }
 #icon2{
     position: absolute;
     top: 5%;
     right: 5%;
     border: 1px solid red;
     width: 30px;
     height: 30px
 }
 .Icon2{
     position: absolute;
     right: 0px;
     width: 30px;
     
 }
 #icon3{
     position: absolute;
     top: 5%;
     right: 8%;
     border: 1px solid red;
     width: 30px;
     height: 30px
 }
 .Icon3{
     position: absolute;
     right: 0px;
     width: 30px;

 }
 #icon4{
     position: absolute;
     top: 5%;
     right: 11%;
     border: 1px solid red;
     width: 30px;
     height: 30px
 }
 .Icon4{
     position: absolute;
     right: 0px;
     width: 30px;

 }
 #icon5{
     position: absolute;
     top: 5%;
     right: 14%;
     border: 1px solid red;
     width: 30px;
     height: 30px
 }
 .Icon5{
     position: absolute;
     right: 0px;
     width: 30px;

 }
 #search{
     position: absolute;
     top: 15%;
     right: 2%;
     border: 1px solid red;
     width: 30px;
     height: 30px
 }
 #searchArtist{
     position: absolute;
     top: 15.7%;
     right: 5%;
 }
 #banner1{
     position: absolute;
     top: 22%;
     left: 33%;
     border: 1px solid red;
     width: 500px;
     height: 330px;
     border-radius: 25%;


 }
 #like{
     position: absolute;
     top: 70%;
     left: 45%;
 }
 #artist1{
     display: inline-block;
     position: absolute;
     top: 80%;
     left: 15%;
     border: 1px solid red;
     width: 200px;
     height: 200px
     
 
 }
 #artist2{
     position: absolute;
     border: 1px solid red;
     top: 80%;
     left: 43%;
     width: 200px;
     height: 200px
 
 }
 #artist3{
     position: absolute;
     border: 1px solid red;
     top: 80%;
     left: 73%;
     width: 200px;
     height: 200px
 
 }
 #newlike{
 position: absolute;
 top: 130%;
 left: 44%;
 }
 #newArtist1{
     border: 1px solid red;
     position: absolute;
     top: 140%;
     left: 15%;
     width: 200px;
     height: 200px
 }
 #newArtist2{
     border: 1px solid red;
     position: absolute;
     top: 140%;
     left: 43%;
     width: 200px;
     height: 200px
 }
 #newArtist3{
     border: 1px solid red;
     position: absolute;
     top: 140%;
     left: 73%;
     width: 200px;
     height: 200px
 }
 footer{
     position: absolute;
     top: 300%;
 }
 .IU{
     width: 400px;
     border-radius: 20%;
     height: 250px;
 }
 .liked{
     border-radius: 50%;
 }
 .newartist1{
     width: 200px;
     border-radius: 50%;
     
 }
 .newartist2{
     width: 200px;
     border-radius: 50%
 }
 .newartist3{
     width: 200px;
     height: 200px;
     border-radius: 50%
 }
 #ranking-container {
    position: fixed;
    top: 20%;
    right: 5%;
    width: 200px;
    padding: 10px;
    border: 1px solid #ccc;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

#ranking-container h3 {
    text-align: center;
    margin-bottom: 10px;
}

#ranking-list {
    list-style-type: none;
    padding: 0;
}

#ranking-list li {
    padding: 5px 0;
    border-bottom: 1px solid #eee;
}

#ranking-list li:last-child {
    border-bottom: none;
}

</style>
</head>
<body>
    <div class="slider-container">
        <div class="slider" style="transform: translateX(0px);">
            <div class="slide"><img src="/semi/picture/btsconcert.jpg" alt="Slide 1"></div>
            <div class="slide"><img src="" alt="Slide 2"></div>
            <div class="slide"><img src="" alt="Slide 3"></div>
        </div>
        <div class="prev">❮</div>
        <div class="next">❯</div>
    </div>
    <div id="like"><h3>구독한 아티스트</h3></div>
        <div id="artist1"><a href=""><img src="/semi/picture/아이유.jpg" class="liked" alt="아이유"></a></div>
        <div id="artist2"><a href=""><img src="/semi/picture/아이린.jpg" class="liked" alt="아이린"></a></div>
        <div id="artist3"><a href=""><img src="/semi/picture/아이묭.jpg" class="liked" alt="아이묭"></a></div>
        
        <div id="newlike"><h3>인기있는 아티스트</h3></div>
        <div id="newArtist1"><a href=""><img src="/semi/picture/bts.jpg" class="newartist1" alt=""></a></div>
        <div id="newArtist2"><a href=""><img src="/semi/picture/ITZY.jpg" class="newartist2" alt=""></a></div>
        <div id="newArtist3"><a href=""><img src="/semi/picture/프로미스나인.jpg" class="newartist3" alt=""></a></div>
        <div id="ranking-container">
            <h3>실시간 순위</h3>
            <ol id="ranking-list">
                <li>Artist 1</li>
                <li>Artist 2</li>
                <li>Artist 3</li>
                <li>Artist 4</li>
                <li>Artist 5</li>
            </ol>
   </div>
</body>
<footer>
    푸터임
</footer>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const slider = document.querySelector('.slider');
        const prevButton = document.querySelector('.prev');
        const nextButton = document.querySelector('.next');

        let slideIndex = 0;

        function showSlides() {
            if (slideIndex === -1) {
                slideIndex = 2;
            } else if (slideIndex === 3) {
                slideIndex = 0;
            }

            slider.style.transform = `translateX(-${slideIndex * 600}px)`;
        }

        prevButton.addEventListener('click', function() {
            slideIndex--;
            showSlides();
        });

        nextButton.addEventListener('click', function() {
            slideIndex++;
            showSlides();
        });
    });
</script>

</html>