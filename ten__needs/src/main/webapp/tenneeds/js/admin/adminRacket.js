// console.log('adminRacket.js 작동확인'); //--- 작동 확인

// 1. 라켓 등록 메소드
function addRacket(){
	
	let racketForm = document.querySelectorAll('.racketForm')[0];
	let racketFormData = new FormData( racketForm );
	

	let rimg = document.querySelector('.rimg');
    if (!rimg.files[0]) {
        alert('[알림]이미지를 선택해주세요.');
        return;
    }
    
	console.log(rimg)
	$.ajax({
		url : "/ten__needs/admin/racket",
		method : "post",
		data : racketFormData,
		contentType : false,			
		processData : false,
		success : (r)=>{
			if( r == 'true'){
				alert('[알림]라켓등록 성공')
				location.href="/ten__needs/tenneeds/jsp/main.jsp"
			}else{
				alert('[알림]라켓등록 실패(시스템 오류)')
			}
		}
	})
}

// 2. 라켓명 유효성 검사 메소드
let checkconfirm = document.querySelector('.checkconfirm');
function racketcheck(){
	let rName= document.querySelector('.rName').value;
	console.log(rName);
	// 아이디 중복검사
	$.ajax({
		url : "/ten__needs/admin/racket",
		method : "get",
		data : { "getType": "check", "rName" : rName}, // type: check
		success : (r)=>{
			if( r == 'true'){
				checkconfirm.innerHTML = '사용중인 라켓명 입니다.'
			}else{
				checkconfirm.innerHTML = '✓'
			}
		}
	})
}

// 3. 첨부파일 미리보기 메소드
function prerimg( object ){
	
	let file = new FileReader();
	file.readAsDataURL( object.files[0] )
	file.onload = (e)=>{
		document.querySelector('.prerimg').src = e.target.result;
	}
}
