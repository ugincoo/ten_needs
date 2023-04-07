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
	
	
	<div class="container">
		<form class="updateForm">
		
			<h3> TENNEEDS </h3>
			<p> 회원정보 수정 </p>
			
			<div class="title"> 아이디[닉네임] </div>
			<div class="mid"></div>
			
			<div class="title"> 비밀번호 </div>
			<input name="mpw" maxlength="16" class="mpw" type="password">
			
			<div class="title"> new 비밀번호 </div>
			<input name="newmpw" onkeyup="mpwcheck()" maxlength="16" class="newmpw" type="password">
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
				<div>
					<input onchange="premimg(this)" name="mimg" class="mimg img" type="file">
					<input onclick="onchecked()" class="defaultimg" type="checkbox"> 기본프로필 사용
				</div>
			</div>
			
			<button class="signupbtn" onclick="infoupdate()" type="button"> 정보수정 완료 </button>
		</form>
	</div>
	
	
	<script src="/ten__needs/tenneeds/js/member/infoupdate.js" type="text/javascript"></script>
</body>
</html>