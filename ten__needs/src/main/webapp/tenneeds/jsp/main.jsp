<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/ten__needs/tenneeds/css/main.css" rel="stylesheet">
</head>
<body>
	<%--    <%@include file = "/tenneeds/header.jsp" %> --%>
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
               <li><a href ="/ten__needs/tenneeds/jsp/member/login.jsp"> 로그인 </a></li>
               <li><a href ="/ten__needs/tenneeds/jsp/member/mypage.jsp"> 마이페이지 </a></li>
               <li><a href ="/ten__needs/tenneeds/jsp/board/list.jsp"> 공지사항 </a></li>
            </ul>
      </div>
   </div>
	
</body>
</html>