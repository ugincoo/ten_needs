<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항/이벤트</title>

<!-- 사용자 정의 css -->
<link href = "/ten__needs/tenneeds/css/board/list.css" rel = "stylesheet">

</head>
<link rel = "shortcut icon" href="/ten__needs/tenneeds/favicon.ico" type="image/x-icon">
<body>
	<!-- 헤더 정보 -->
	<%@ include file = "/tenneeds/header.jsp" %>
	 <a href="/ten__needs/tenneeds/jsp/main.jsp"><button onclick="go_main()" type="button" class="gomain"> 메인으로 </button></a>
	
	<!-- 이벤트/공지사항 커뮤니티 -->
	<div class = "container">
		<div class ="boardbox">
			<div class = "boardtoptitle"> <!-- 맨위 타이틀 부분 -->
				<h3>공지사항/이벤트</h3>
			</div>
			<div class = "boardtopetc"> <!-- 글쓰기 버튼 [관리자일 경우] -->
				
			</div>
			<div class ="boardTable"> <!-- 공지사항/이벤트 목록 출력 부분 -->
								
			</div>
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
					<option value = "bTitle">제목</option>
					<option value = "bContent">내용</option>
				</select>
				<div>
					<input class = "keyword" type = "text" placeholder="키워드 검색">
					<button class = "blbtn" onClick = "boardSearch()">검색</button>
					<button class = "blbtn" onClick = "setSearch()">검색초기화</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 사용자 정의 js -->
	<script src = "/ten__needs/tenneeds/js/board/list.js"></script>
</body>
</html>