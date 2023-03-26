<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/ten__needs/tenneeds/css/member/signup.css" rel="stylesheet" >
</head>
<body>
	<!-- 헤더 정보 -->
	<%@ include file = "/tenneeds/header.jsp" %>
	
	<div class="container signupForm">
		<form class="signupForm">
			<h3>TenNeeds에 오신 것을<br> 환영합니다!</h3>
		
			<div class="maintitle"> 아이디[닉네임] </div>
			<input name="mid" class="mid" type="text">
			
			<div class="maintitle"> 비밀번호 </div>
			<input class="mpw" type="password">
			
			<div class="checkconfirm"></div>
			
			<div class="subbtnbox">
				<a href="/ten__needs/tenneeds/jsp/member/signup.jsp"> 회원가입 </a>|
				<a class="modalfind" onclick="onpenModalID()"> 아이디찾기 </a>|
				<a class="modalfind" onclick="onpenModalPW()"> 비밀번호찾기</a>
				<a href="/ten__needs/tenneeds/jsp/member/infoupdate.jsp"> 회원정보 수정 </a>
			</div>
			
			<button class="signupbtn" onclick="login()" type="button"> 로그인 </button>
		</form>
	</div>
	
	
	<!-- 모달 HTML -->
	<div class="modal_wrap">
		<div class="modal_box signupForm">
			
		</div>
	</div>
	
	
	<script src="/ten__needs/tenneeds/js/member/login.js" type="text/javascript"></script>
</body>
</html>