<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/ten__needs/tenneeds/css/member/mypage.css" rel="stylesheet">
</head>
<body>
	
	<%@ include file = "/tenneeds/header.jsp" %>
	
	<div class="container">
		
		<h3 class="mypageTitle"> 마이페이지 </h3>
		
		<div class="mypageContent">
		
			<!-- 프로필 정보 -->
			<div class="infobox">
				
				<h4 class="infoTitle"> 프로필 정보 </h4>
				<div class="infoContent">
					<form class="updateForm">
						<img class="mImg" alt="" src="/ten__needs/tenneeds/jsp/member/mimg/default.png">	<!-- 프로필: member 폴더에서 들어옴 -->
						<div class="mName"> <i class="fas fa-user-circle"></i> 이름: 김태호 </div>
						<div class="mId"> <i class="fas fa-regular fa-id-badge"></i> 아이디: hoky </div>	
						<div class="mPhone"> <i class="fas fa-mobile"></i> 연락처: 010-0000-0000 </div>
						<div class="mEmail"> <i class="fas fa-envelope"></i> 이메일: hohohohoho@naver.com </div>
					</form>
				</div>
				<a href="/ten__needs/tenneeds/member/updateinfo.jsp"><button class="updateBtn" type="button"> 정보수정 </button> </a>
				<button class="deleteBtn" onclick="openModal()" type="button"> 회원탈퇴 </button>	
			</div>
			
			<!-- main -->
			<div class="gamewrap">
				
				<!-- 게임 전적 정보 --><!--  -->
				<div class="gamebox">
				
					<h4 class="gameTitle"> 게임 전적 정보 </h4>
					<!-- <div class="gRank"> <i class="fab fa-ravelry"></i> 랭크 3위 </div> -->
					<div class="racketwrap">
						<img class="rImg" alt="" src="/ten__needs/tenneeds/jsp/member/mimg/파리채.png"> <!-- 라켓 이미지: game 폴더에서 들어옴 -->
						<div class="racketTitle"> 가장 잘하는 라켓 </div>
						<div class="racketContent"> 파리채 </div>
					</div>
					<div class="gameContent">
						<div class="gId"> <i class="fas fa-regular fa-id-badge"></i> 닉네임 호키 </div>
						<div class="gCount"> <i class="fas fa-solid fa-gamepad"></i> 경기수 100 </div>
						<div class="gWin"> <i class="fas fa-laugh-squint"></i> 승리 50  </div>
						<div class="gWinRate"> <i class="fas fa-table-tennis"></i> 승률 50.00%  </div>
						<div class="gBestRa"> <i class="fas fa-solid fa-trophy"></i> 베스트 라켓 파리채 </div>
						<div class="gWorstRa"> <i class="fas fa-solid fa-skull"></i> 워스트 라켓 모기채 </div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
	<!-- 모달 HTMLs -->
	<div class="modal_wrap">
		<div class="modal_box">
			<h3 class="modal_title"> 회원 탈퇴 하시겠습니까?  </h3>
			<div class="modal_content">
				비밀번호: <input type="password" class="mPw">
			</div>
			<div class="modal_btns">
				<button onclick="onDelete()" class="modla_cancle" type="button"> 확인 </button>
				<button onclick="closeModal()" class="modla_cancle" type="button"> 취소 </button>
			</div>
		</div>
	</div>

	<script src="/ten__needs/tenneeds/js/member/mypage.js" type="text/javascript"></script>
</body>
</html>