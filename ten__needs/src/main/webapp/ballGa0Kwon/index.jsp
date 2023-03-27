<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href = "/ten__needs/ballGa0Kwon/pingPong.css" rel = "stylesheet">
</head>
<body>
	<%@include file = "/tenneeds/header.jsp" %>
	<!-- 점수 판 -->
	<div class = "container scoreBar" id ="scoreBar" style = "width : 500px; height : 50px; border : 1px solid red">
		<div class = "player1">
			<span class ="player1Name">Player1 : </span>
			<span class ="player1Score"></span>
		</div>
		<div class = "player2">
			<span class = "player2Name">Player2 : </span>
			<span class ="player2Score"></span>
		</div>
	</div>
		
	<div class = "container contentDiv">
		<!-- 경기장 전체를 의미 -->
		<canvas id = "pong" width = "500" height="800"></canvas>
	</div>
	
	
	<!-- 사용자 정의 JS -->
	<script src = "/ten__needs/ballGa0Kwon/pingPong.js" type="text/javascript"></script>
</body>
</html>