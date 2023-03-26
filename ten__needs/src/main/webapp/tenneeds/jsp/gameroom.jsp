<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

   <link href="/ten__needs/tenneeds/css/gameroom.css" rel="stylesheet">
   

</head>
<body>
   <%@include file = "/tenneeds/header.jsp" %>
   
   <div class="container gameback" >   
      <!-- 방 번호 / 방제목  -->
      <div class="gametitle_box"> 
         <div class="gno"> 방번호 : 1 </div>
         <div class="gtitle"> 방 제목 :  테니스게임~~~~~하실분~~ </div>
      </div>
      <!-- 게임대기실 -->
      <div class="gamebox">
         <!-- 프로필 / 채팅-->
         <div class="game_leftbox">
            <div class="game_userbox">
               <!-- 유저1명 박스 -->
               <div class="game_user">
                  <img src="/ten__needs/tenneeds/img/짱구.png" class="profile">
                  <div class="mid"> 닉네임 </div>
               </div>
               
               <!-- 유저1명 박스 -->
               <div class="game_user">
                  <img src="/ten__needs/tenneeds/img/짱구.png" class="profile">
                  <div class="mid"> 닉네임 </div>
               </div>
   
            </div>
            <!--  채팅  -->
            <!-- 대화내용 표시 구역 -->
            <div class="geme_chat">
               <p> 안녕하세염 </p>
               <p> 안녕하세염 </p>
               <p> ---- 유저1님이 준비되었습니다 ----</p>
            </div>   
            <!-- 입력 / 전송버튼 -->
            <div class="geme_chatbox">
               <input type="text">
               <button type="button" class="gamebtn"> 전 송 </button>
            </div>
            
         </div>
         <!-- 테니스장 사진 /  레디,시작 버튼 -->
         <div  class="game_rightbox">
            <div class="right_title"> 테니스장 </div>
            <img class="ex_img "src="/ten__needs/tenneeds/img/테니스장.jpg">
            <button onclick="gamestart()" type="button" class="gamebtn startbtn"> 준비완료!  </button>
         </div>
      </div>

   </div>
   

</body>
</html>