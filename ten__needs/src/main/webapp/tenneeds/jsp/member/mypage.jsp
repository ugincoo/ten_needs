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
		<a href="/ten__needs/tenneeds/jsp/main.jsp"><button onclick="go_main()" type="button" class="gomain"> 메인으로 </button></a>
		
		<h3 class="mypageTitle"> 마이페이지 </h3>
		
		<div class="mypageContent">
		
			<!-- 프로필 정보 -->
			<div class="infobox">
				
				<h4 class="infoTitle"> 프로필 정보 </h4>
				<div class="infoContent">
					<form class="updateForm">
						<img class="mImg" alt="" src="/ten__needs/tenneeds/jsp/member/mimg/default.png">	<!-- 프로필: member 폴더에서 들어옴 -->
						<!-- <i class="fas fa-user-circle"></i><div class="mName"> 이름: 김태호 </div> -->
						<div> <i class="fas fa-regular fa-id-badge"></i> <span class="mId"></span></div>	
						<div> <i class="fas fa-mobile"></i> <span class="mPhone"></span>  </div>
						<div> <i class="fas fa-envelope"></i> <span class="mEmail"></span>   </div>
					</form>
				</div>
				<a href="/ten__needs/tenneeds/jsp/member/infoupdate.jsp"><button class="updateBtn" type="button"> 정보수정 </button> </a>
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
						<div> <i class="fas fa-regular fa-id-badge"></i> <span  class="gId"> </span> </div>
						<div> <i class="fas fa-solid fa-gamepad"></i> <span class="gCount"> </span> </div>
						<div> <i class="fas fa-laugh-squint"></i> <span class="gWin"></span>  </div>
						<div> <i class="fas fa-table-tennis"></i> <span class="gWinRate"></span>  </div>
						<div> <i class="fas fa-solid fa-trophy"></i> <span class="gBestRa"></span> </div>
						<div> <i class="fas fa-solid fa-skull"></i> <span class="gWorstRa"></span>  </div>
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
	<script src="/ten__needs/tenneeds/js/modal.js" type="text/javascript"> </script>
	
</body>
</html>