	// console.log('mypage.js 연결확인');
console.log(memberInfo)
if( memberInfo == null ){ // --- memberInfo 빈 상태일 때, 객체로 받아지면 .mId 추가
	alert( '[알림] 로그인된 회원만 이용 가능합니다.' )
	location.href="/ten__needs/tenneeds/jsp/main.jsp"
}

// ------------------------ 회원 탈퇴 ------------------------
function onDelete(){
	$.ajax({
		url: "/ten__needs/tenneeds/member/mypage", // servelt MemberInfo랑 합치기 작업
		method: "delete",
		data: { "mPw" : document.querySelector('.mPw').value },
		success: (r)=>{
				console.log("onDelete 결과");
				console.log( r);
			if( r == 'true' ){
				alert('[회원탈퇴 성공] 이용해주셔 감사합니다.')
				location.href="/ten__needs/tenneeds/jsp/main.jsp"
			} else{ alert('[회원탈퇴 실패]비밀번호 불일치') }
		}
	})
}
//
// ------------------------ 프로필 출력: memberInfo 이용 ------------------------
printInfo(); // --------------- 완료되면 주석 해제 처리
function printInfo(){
	document.querySelector('.mImg').src = `/ten__needs/tenneeds/jsp/member/mimg/${memberInfo.mimg == null ? 'default.png' : memberInfo.mimg }`;
	//document.querySelector('.mName').innerHTML = memberInfo.mName;
	document.querySelector('.mId').innerHTML = memberInfo.mid;
	document.querySelector('.mPhone').innerHTML = memberInfo.mphone;
	document.querySelector('.mEmail').innerHTML = memberInfo.memail;
}

// ------------------------ 게임 전적 출력: ajax 이용 ------------------------
 printGameInfo(); // --------------- 완료되면 주석 해제 처리
function printGameInfo(){
	let html = `<h4 class="gameTitle"> 게임 전적 정보 </h4>
						<button class ="wholeranking updateBtn" onClick = "getTotal()">
							전체 랭킹보기
						</button>`;
						
	document.querySelector('.memberGame_info').innerHTML = html;
	document.querySelector('.memberGame_info2').innerHTML = "";				
	html = `<div class="racketwrap">
							<img class="rImg" alt="" src=""> <!-- 라켓 이미지: game 폴더에서 들어옴 -->
							<div class="racketTitle"> </div>
							<div class="racketContent"> </div>
						</div>
						<div class="gameContent">
							<div> <i class="fas fa-regular fa-id-badge"></i> <span  class="gId"> </span> </div>
							<div> <i class="fas fa-solid fa-gamepad"></i> <span class="gCount"> </span> </div>
							<div> <i class="fas fa-laugh-squint"></i> <span class="gWin"></span>  </div>
							<div> <i class="fas fa-table-tennis"></i> <span class="gWinRate"></span>  </div>
							<div> <i class="fas fa-solid fa-trophy"></i> <span class="gBestRa"></span> </div>
							<div> <i class="fas fa-solid fa-skull"></i> <span class="gWorstRa"></span>  </div>
						</div>`
	document.querySelector('.realGame_info').innerHTML = html;
		
	document.querySelector('.gId').innerHTML = '닉네임: '+memberInfo.mid; // 닉네임
	
	$.ajax({
		url: "/ten__needs/tenneeds/member/mypage", // servelt MemberInfo랑 합치기 작업
		method: "get",
		data: { "mNo": memberInfo.mno }, // data로 전달하거나, mNo전환 메소드 생성하면 servlet에서 바로 진행
		success: (r)=>{ // SQL 이용해서 데이터 조회한 값 가져오기
				console.log("printGameInfo 결과");
				console.log(r);
			document.querySelector('.gCount').innerHTML = '경기수: '+r.gCount; //o
			document.querySelector('.gWin').innerHTML = '승리수: ' +r.gWin; // o
			document.querySelector('.gWinRate').innerHTML = '승률: '+r.gWinRate+' %';
			// SQL: select count(*) as gCount, sum(gsResult) as gWin, (sum(gsResult)/count(*))*100 as gWinRate from gamestatus where mNo = ?;
			document.querySelector('.rImg').src = `/ten__needs/tenneeds/jsp/game/img/rimg/${r.rImg == null ? 'default.png' : r.rImg }`;
			document.querySelector('.racketContent').innerHTML = r.gBestRa;
			document.querySelector('.gBestRa').innerHTML = 'Best: '+r.gBestRa; 	// 마지막 인덱스 라켓
			document.querySelector('.gWorstRa').innerHTML = 'Worst:'+r.gWorstRa; // 첫번째 인덱스 라켓
			// SQL: select r.rNo, r.rName, r.rImg, sum(g.gsResult) as total from gamestatus g join racket r on g.rNo = r.rNo where mNo = ? group by r.rNo order by total desc;
			// => Dao에서 SQL 2번 작성하는 식으로 고고 (type 나누면, Dto 관리 복잡해짐)
		}
	})
}