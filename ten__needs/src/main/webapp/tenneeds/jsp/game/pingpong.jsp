<%@page import="model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/ten__needs/tenneeds/css/game/pingpong.css" rel="stylesheet">

</head> 
<body>
	<!-- 헤더 정보 -->
	<%@ include file = "/tenneeds/header.jsp" %>
	
	<%
		String user1Mid = request.getParameter("user1");
		String user2Mid = request.getParameter("user2");
		
		
		int user1Mno = MemberDao.getInstance().getMno(request.getParameter("user1"));
		int user2Mno = MemberDao.getInstance().getMno(request.getParameter("user2"));
		
		int gNo = Integer.parseInt(request.getParameter("gNo"));
	%>
	
	<input type = "hidden" type = "text" class = "user1Mid" value="<%=user1Mid%>">
	<input type = "hidden" type = "text" class = "user2Mid" value="<%=user2Mid%>">
	<input type = "hidden" type = "text" class = "user1Mno" value="<%=user1Mno%>">
	<input type = "hidden" type = "text" class = "user2Mno" value="<%=user2Mno%>">
	<input type = "hidden" type = "text" class = "gNo" value="<%=gNo%>">
	
	<div class ="sticky-wrap">
	
		<div class = "ppro plyaer1profile">
			<div class = "pinfo player1Div">
				<img class = "profile player1racket">
				<div style = "margin-left: 20px">
					<span class = "prm player1racketnm"></span>
					<br/>
					<span class = "username player1Name"><%=user1Mid%></span>
				</div>
			</div>
		</div>
		
		<div class = "game_wrap">
			<canvas class = "gamereal" id="canvas" width="600" height="800"></canvas>
		</div>
		
		<div class = "ppro plyaer2sprofile">
			<div class = "pinfo player2Div">
				<img class = "profile player2racket"  src = "/ten__needs/tenneeds/jsp/game/img/rimg/tennisstick.png">
				<div style = "margin-left: 20px">
					<span class = "prm player2racketnm"></span>
					<br/>
					<span class = "username player2Name"><%=user2Mid%></span>
				</div>
			</div>
		</div>
		
	</div>
	 
	
	
	<script type="text/javascript" src="/ten__needs/tenneeds/js/game/pingpong.js"></script> 
</body>
</html>