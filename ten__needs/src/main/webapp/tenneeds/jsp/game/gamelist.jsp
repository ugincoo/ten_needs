<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href = "/ten__needs/tenneeds/css/game/gamelist.css" rel = "stylesheet">
</head>
<body>
	<!-- 헤더 정보 -->
	<%@ include file = "/tenneeds/header.jsp" %>
	
	<!-- 이벤트/공지사항 커뮤니티 -->
	<div class = "container">
		<a href="/ten__needs/tenneeds/jsp/main.jsp"><button onclick="go_main()" type="button" class="gomain"> 메인으로 </button></a>
		
		<div class ="gamebox">
			<div class = "gametoptitle"> <!-- 맨위 타이틀 부분 -->
				<h3>게임 대기실</h3>
			</div>
			<div class = "gametopetc"> <!-- 글쓰기 버튼 [관리자일 경우] -->
				
			</div>
			<div class ="gameTable"> <!-- 공지사항/이벤트 목록 출력 부분 -->
								
			</div>
		</div>
	</div>
	<div class = "container">
		<div class = "container page_box" style ="text-align: center">
			<div class ="pagebox">
				<!-- 페이징 버튼 들어가는 부분 -->
			</div>
			<!-- 검색하는 창 -->
			<div class = "searchbox" style ="display: flex; justify-content: center">
				<select class = "key"> <!-- key입력 -->
					<option value = "gTitle">제목</option>
					<option value = "mid">아이디</option>
				</select>
				<div>
					<input class = "keyword" type = "text" placeholder="키워드 검색">
					<button class = "glbtn" onClick = "gameSearch()">검색</button>
					<button class = "glbtn" onClick = "setSearch()">검색초기화</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 모달 HTMLs -->
	<div class="modal_wrap">
		<div class="modal_box">
			<h3 class="modal_title"> 게임방 생성  </h3>
			<div class="modal_content">
				제목: <input type="text" class="gcTitle">
			</div>
			<div class="modal_btns">
				<button onclick="createGame()" class="modla_cancle" type="button"> 확인 </button>
				<button onclick="closeModal()" class="modla_cancle" type="button"> 취소 </button>
			</div>
		</div>
	</div>
	
	<!-- 사용자 정의 js -->
	<script src = "/ten__needs/tenneeds/js/game/gamelist.js"></script>
	<script src = "/ten__needs/tenneeds/js/modal.js" type="text/javascript"> </script>
	
</body>
</html>