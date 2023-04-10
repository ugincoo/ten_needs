<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지-라켓 수정</title>
	<link href="/ten__needs/tenneeds/css/admin/adminRacket.css" rel="stylesheet" >
</head>
<link rel = "shortcut icon" href="/ten__needs/tenneeds/favicon.ico" type="image/x-icon">
<body>

	<%@ include file = "/tenneeds/header.jsp" %>
	<%	
		String rNo = request.getParameter("rNo");
	%>
	
  
	
	<div class="container">
		<form class="updateForm">
		
			<input type = "hidden" type = "text" name="rNo" class = "rNo" value="<%=rNo%>">
			<h3> TENNEEDS admin page </h3>
			<p> 라켓 정보 수정 </p>
			
			<div class="title"> 라켓명 </div>
			<input maxlength="30" name="rName" class="rName" type="text">
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
				<label for="rimg">
 					 	파일 업로드
				</label>
				<input onchange="prerimg(this)" name="rimg" id="rimg" class="rimg img" type="file"> <br/>
				<input onchange="prerimg(this)" name="rimg" id="rimg" class="rimg img" type="file">
			</div>
			
			<button class="signupbtn" onclick="updateRacket()" type="button"> 라켓수정 완료 </button>
			<button class="signupbtn" onclick="openModal()" type="button"> 라켓삭제 </button>
		</form>
	</div>
	
		<!-- 모달 HTMLs -->
	<div class="modal_wrap">
		<div class="modal_box">
			<h3 class="modal_title"> 라켓 정보를 삭제하시겠습니까?  </h3>
			<div class="modal_content">
				라켓명: <input type="text" class="dName" name="dName" id="dName">
			</div>
			<div class="modal_btns">
				<button onclick="onDelete()"  class="modla_cancle" type="button"> 확인 </button>
				<button onclick="closeModal()" class="modla_cancle" type="button"> 취소 </button>
			</div>
		</div>
	</div>
	
	<script src="/ten__needs/tenneeds/js/admin/adminRacktView.js" type="text/javascript"></script>
	<script src="/ten__needs/tenneeds/js/modal.js" type="text/javascript"> </script>
</body>
</html>