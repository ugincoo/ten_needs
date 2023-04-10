<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지-라켓 리스트</title>
<link href = "/ten__needs/tenneeds/css/admin/adminRacketList.css" rel = "stylesheet">

</head>
<link rel = "shortcut icon" href="/ten__needs/tenneeds/favicon.ico" type="image/x-icon">
<body>
	<!-- 헤더 정보 -->
	<%@ include file = "/tenneeds/header.jsp" %>
	<div class="admin_topbox">
		 <a href="/ten__needs/tenneeds/jsp/main.jsp"><button onclick="go_main()" type="button" class="gomain"> 메인으로 </button></a>
	 </div>
	<!-- 이벤트/공지사항 커뮤니티 -->
	<div class = "container">
		<div class ="racketbox">
			<div class = "rackettoptitle"> <!-- 맨위 타이틀 부분 -->
				<h3>라켓 리스트</h3>
			</div>
			<div class = "rackettopetc"> <!-- 글쓰기 버튼 [관리자일 경우] -->
				<a href="/ten__needs/tenneeds/jsp/admin/adminRacket.jsp"><button onclick="go_main()" type="button" class="racketbtn"> 라켓등록 </button></a>
			</div>
			
			<table class="racketTable table table-hover">
			</table>
		</div>
	</div>
	<div class = "container">
		<div class = "container" style ="text-align: center">
			<div class ="pagebox">
				<!-- 페이징 버튼 들어가는 부분 -->
			</div>
			<!-- 검색하는 창 -->
			<div class = "searchbox" style ="display: flex; justify-content: center">
				<select class = "key"> <!-- key입력 -->
					<option value = "rName">라켓명</option>
				</select>
				<div>
					<input class = "keyword" type = "text" placeholder="키워드 검색">
					<button class = "blbtn" onClick = "racketSearch()">검색</button>
					<button class = "blbtn" onClick = "setSearch()">검색초기화</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 사용자 정의 js -->
	<script src = "/ten__needs/tenneeds/js/admin/adminRacketList.js"></script>
</body>
</html>