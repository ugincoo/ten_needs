	// console.log('mypage.js 연결확인');

if( memberInfo == null ){ // --- memberInfo 빈 상태일 때, 객체로 받아지면 .mId 추가
	alert( '[알림] 로그인된 회원만 이용 가능합니다.' )
	location.href="/ten__needs/tenneeds/jsp/index.jsp"
}

// ------------------------ 회원 탈퇴 ------------------------
function onDelete(){
	$.ajax({
		url: "/tenneeds/member/mypage", // servelt MemberInfo랑 합치기 작업
		method: "delete",
		data: { "mId" : memberInfo.mId ,"mPw" : document.querySelector('.mPw').value },
		success: (r)=>{
				console.log("onDelete 결과");
				console.log( r);
			if( r == 'true' ){
				alert('[회원탈퇴 성공] 이용해주셔 감사합니다.')
				location.href="/ten__needs/tenneeds/jsp/index.jsp"
			} else{ alert('[회원탈퇴 실패]비밀번호 불일치') }
		}
	})
}
//
// ------------------------ 프로필 출력: memberInfo 이용 ------------------------
// printInfo(); // --------------- 완료되면 주석 해제 처리
function printInfo(){
	document.querySelector('.mImg').src = "";
	document.querySelector('.mName').innerHTML = memberInfo.mName;
	document.querySelector('.mId').innerHTML = memberInfo.mId;
	document.querySelector('.mPhone').innerHTML = memberInfo.mPhone;
	document.querySelector('.mEmail').innerHTML = memberInfo.mEmail;
}

// ------------------------ 게임 전적 출력: ajax 이용 ------------------------
// printGameInfo(); // --------------- 완료되면 주석 해제 처리
function printGameInfo(){
	
	document.querySelector('.gId').innerHTML = memberInfo.mId; // 닉네임
	
	$.ajax({
		url: "/tenneeds/member/mypage", // servelt MemberInfo랑 합치기 작업
		method: "get",
		data: { "mNo": memberInfo.mNo }, // data로 전달하거나, mNo전환 메소드 생성하면 servlet에서 바로 진행
		success: (r)=>{ // SQL 이용해서 데이터 조회한 값 가져오기
				console.log("printGameInfo 결과");
				console.log(r);
			document.querySelector('.gCount').innerHTML = r.gCount; //o
			document.querySelector('.gWin').innerHTML = r.gWin; // o
			document.querySelector('.gWinRate').innerHTML = r.gWinRate;
			// SQL: select count(*) as gCount, sum(gsResult) as gWin, (sum(gsResult)/count(*))*100 as gWinRate from gamestatus where mNo = ?;
			document.querySelector('.rImg').src = "";
			document.querySelector('.racketContent').innerHTML = r.gBestRa;
			document.querySelector('.gBestRa').innerHTML = r.gBestRa; 	// 마지막 인덱스 라켓
			document.querySelector('.gWorstRa').innerHTML = r.gWorstRa; // 첫번째 인덱스 라켓
			// SQL: select r.rNo, r.rName, r.rImg, sum(g.gsResult) as total from gamestatus g join racket r on g.rNo = r.rNo where mNo = ? group by r.rNo order by total desc;
			// => Dao에서 SQL 2번 작성하는 식으로 고고 (type 나누면, Dto 관리 복잡해짐)
		}
	})
}