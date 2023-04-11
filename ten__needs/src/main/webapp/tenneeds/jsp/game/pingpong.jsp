<%@page import="model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐거운 테니스게임 !</title>

<link href="/ten__needs/tenneeds/css/game/pingpong.css" rel="stylesheet">

</head> 
<link rel = "shortcut icon" href="/ten__needs/tenneeds/favicon.ico" type="image/x-icon">
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
					<span class = "username player1Name"></span>
				</div>
			</div>
		</div>
		
		<div class = "game_wrap">
			<canvas class = "gamereal" id="canvas" width="600" height="800"></canvas>
		</div>
		
		<div class = "ppro plyaer2sprofile">
			<div class = "pinfo player2Div">
				<img class = "profile player2racket">
				<div style = "margin-left: 20px">
					<span class = "prm player2racketnm"></span>
					<br/>
					<span class = "username player2Name"></span>
				</div>
			</div>
		</div>
		
	</div>
	
	 <!-- 모달 HTMLs -->
	<div class="modal_wrap">
		<div class="modal_box">
			<h3 class="modal_title"> 라운드 :   </h3>
			<div class="modal_content">
				플레이어 : <span class = "userMid"></span>
				<h4 class = "roundContent"></h4>
			</div>
			<div class="modal_btns">
				<button onclick="closeModal3()" class="modla_cancle" type="button"> 확인 </button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src = "/ten__needs/tenneeds/js/modal.js"></script>
	<script type="text/javascript" src="/ten__needs/tenneeds/js/game/pingpong.js"></script> 
</body>
</html>