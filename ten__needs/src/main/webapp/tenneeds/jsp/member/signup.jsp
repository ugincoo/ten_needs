<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tenneeds 회원가입</title>

	<link href="/ten__needs/tenneeds/css/member/signup.css" rel="stylesheet" >
</head>
<link rel = "shortcut icon" href="/ten__needs/tenneeds/favicon.ico" type="image/x-icon">
<body>
	<!-- 헤더 정보 -->
	<%@ include file = "/tenneeds/header.jsp" %>
	
	<div class="container">
		<form class="signupForm">
		
			<h3> TENNEEDS </h3>
			<p> 회원가입 </p>
			
			<div class="title"> 아이디[닉네임] </div>
			<input onkeyup="midcheck()" maxlength="30" name="mid" class="mid" type="text">
			<div class="checkconfirm"></div>
			
			<div class="title"> 비밀번호 </div>
			<input name="mpw" onkeyup="mpwcheck()" maxlength="16" class="mpw" type="password">
			<div class="checkconfirm"></div>
		
			<div class="title"> 전화번호 </div>
			<input name="mphone" placeholder="010-0000-0000" onkeyup="mphonecheck()" maxlength="13" class="mphone" type="text">
			<div class="checkconfirm"></div>
			
			<div class="title"> 이메일 </div>
			<div class="memailbox">
				<input onkeyup="memailcheck()" type="text" name="memail" class="memail" >
				<button onclick="getauth()" class="authbtn" disabled="disabled" type="button">인증하기</button>
			</div>
			
			<div class="authbox">
			
			</div>
			
			<div class="checkconfirm"></div>
			
			<div class="title"> 프로필 </div>
			<div class="pimgbox">
				<img class="premimg" alt="" src="/ten__needs/tenneeds/jsp/member/mimg/default.png">
				<label for="mimg">
 					 	파일 업로드
				</label>
				<input onchange="premimg(this)" name="mimg" id="mimg" class="mimg img" type="file"> <br/>
			</div>
			
			<button class="signupbtn" onclick="signup()" type="button"> 회원가입 완료 </button>
		</form>
	</div>

	
	<script src="/ten__needs/tenneeds/js/member/signup.js" type="text/javascript"></script>
</body>
</html>