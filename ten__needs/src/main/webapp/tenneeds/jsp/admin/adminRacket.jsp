<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지-라켓 등록</title>
	<link href="/ten__needs/tenneeds/css/admin/adminRacket.css" rel="stylesheet" >
</head>
<link rel = "shortcut icon" href="/ten__needs/tenneeds/favicon.ico" type="image/x-icon">
<body>

	<%@ include file = "/tenneeds/header.jsp" %>
	
	<div class="container">
		<form class="racketForm">
		
			<h3> TENNEEDS admin page </h3>
			<p> 라켓 등록 </p>
			
			<div class="title"> 라켓명 </div>
			<input onkeyup="racketcheck()" maxlength="30" name="rName" class="rName" type="text">
			<div class="checkconfirm"></div>
			
			<div class="title"> 레벨설정 </div>
			<input name="rLevel" maxlength="16" class="rLevel" type="text">
			<div class="checkconfirm"></div>
		
			<div class="title"> 사이즈(가로) </div>
			<input name="rSize_x"  maxlength="13" class="rSize_x" type="text">
			<div class="checkconfirm"></div>
			
			<div class="title"> 사이즈(가로) </div>
				<input name="rSize_y"  maxlength="13" class="rSize_y" type="text">
			<div class="checkconfirm"></div>

			<div class="title"> 사진등록 </div>
			<div class="rimgbox">
				<img class="prerimg" alt="" src="">
				<div class="file">
					<label for="rimg">
	 					 	파일 업로드
					</label>
					<input onchange="prerimg(this)" name="rimg" id="rimg" class="rimg img" type="file">
				</div>
			</div>
			
			<button class="signupbtn" onclick="addRacket()" type="button"> 라켓등록 완료 </button>
		</form>
	</div>
	
	<script src="/ten__needs/tenneeds/js/admin/adminRacket.js" type="text/javascript"></script>
</body>
</html>