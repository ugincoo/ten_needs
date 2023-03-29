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
	<div class ="sticky-wrap">
		<div class = "game_wrap">
			<canvas class = "gamereal" id="canvas" width="600" height="800"></canvas>
		</div>
	</div>
	
	
	
	<script type="text/javascript" src="/ten__needs/tenneeds/js/game/pingpong.js"></script>
</body>
</html>