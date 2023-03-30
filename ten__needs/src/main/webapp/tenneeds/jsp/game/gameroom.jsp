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
   
   <%	
		String gNo = request.getParameter("gNo");
	%>
	
   <input type = "hidden" type = "text" class = "gNo" value="<%=gNo%>">
   
   <div class="container gameback" >   
      	 <a href="/ten__needs/tenneeds/jsp/main.jsp"><button onclick="go_main()" type="button" class="gomain"> 메인으로 </button></a>

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
                  <div class="ready_state">  </div>
               </div>
               
               <!-- 유저1명 박스 -->
               <div class="game_user">
                  <img src="/ten__needs/tenneeds/img/짱구.png" class="profile">
                  <div class="mid"> 닉네임 </div>
                  <div class="ready_state">  </div>
               </div>
   
            </div>
            <!--  채팅  -->
            <!-- 대화내용 표시 구역 -->
            <div class="geme_chat">
            	

            </div>   
            <!-- 입력 / 전송버튼 -->
            <div class="geme_chatbox">
               <input type="text" class="chatContent">
               <button onclick="sendMessage()" type="button" class="gamebtn"> 전 송 </button>
            </div>
            
         </div>
         <!-- 테니스장 사진 /  레디,시작 버튼 -->
         <div  class="game_rightbox">
            <div class="right_title"> 테니스장 </div>
            <img class="ex_img "src="/ten__needs/tenneeds/img/테니스장.jpg">
            <button onclick="gamestart()" type="button" class="gamebtn startbtn" > 준비  </button>
         </div>
      </div>

   </div>
   
	<script src="/ten__needs/tenneeds/js/game/gameroom.js" type="text/javascript"> </script>
</body>
</html>