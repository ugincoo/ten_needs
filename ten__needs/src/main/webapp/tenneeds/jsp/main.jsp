<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>어서오세요 Tenneeds입니다 :) </title>

<link href="/ten__needs/tenneeds/css/main.css" rel="stylesheet">
</head>
<link rel = "shortcut icon" href="/ten__needs/tenneeds/favicon.ico" type="image/x-icon">
<body>
	 <%@include file = "/tenneeds/header.jsp" %>
   <!--  메인 화면 -->
   <div class="container main">
      <div class="mainback">
          <div class="mainbox"> 
            
            <div>
               <a href ="/ten__needs/tenneeds/jsp/game/gamelist.jsp"> <img class="mainball" src="/ten__needs/tenneeds/img/공.gif"></a>
            </div>
            
            <div class="maintitle">
              <h3> tenNeeds </h3>
              <p> 테니스 게임 tenNeeds입니다. </P> 
              <p>공을 누르면 게임대기방으로 이동합니다.</p> 
            </div>
          </div>
            <!-- 버튼 구역 -->
            <ul class="mainbtnbox">
               
            </ul>
      </div>
   </div>
	
	<script src="/ten__needs/tenneeds/js/main.js" type="text/javascript"></script>
</body>
</html>