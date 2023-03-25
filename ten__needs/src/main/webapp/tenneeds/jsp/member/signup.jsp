<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="/ten_needs/tenneeds/css/member/signup.css" rel="stylesheet" >
</head>
<body>
	<div class="container">
		<form class="signupForm">
		
			<h3> TenNeeds에 오신 것을 환영합니다! </h3>
			
			<div class="title"> 아이디[닉네임] </div>
			<input name="mid" type="text">
			<div></div>
			
			<div class="title"> 비밀번호 </div>
			<input name="mpw" type="password">
			<div></div>
			
			<div class="title"> 비밀번호 확인 </div>
			<input name="mpwconfirm" type="password">
			<div></div>
			
			<div class="title"> 전화번호 </div>
			<input name="mphone" type="text">
			
			<div class="title"> 이메일 </div>
			<input name="memail" type="text">
			<div></div>
			
			<div class="title"> 프로필 </div>
			<div>
				<input name="mimg" type="file">
				<img alt="" src="">
			</div>
			
			<button onclick="signup()" type="button"> 회원가입 완료 </button>
		</form>
	</div>

	<!-- 1. JQUERY (ajax 사용하기 위해) -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script src="/ten_needs/tenneeds/js/member/signup.js" type="text/javascript"></script>
</body>
</html>