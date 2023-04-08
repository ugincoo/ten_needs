let rNo = document.querySelector('.rNo').value;
console.log(rNo);

// 라켓 개별 출력
getRacket();
function getRacket(){
	$.ajax({
		url : "/ten__needs/admin/racket",
		method : "get",
		data : { "getType": "racket", "type" : 2, "rNo": rNo },
		success : (r) => {
			console.log(r);
			document.querySelector('.rName').value = r.rName;
			document.querySelector('.rLevel').value = r.rLevle;
			document.querySelector('.rSize_x').value = r.rSize_x;
			document.querySelector('.rSize_y').value = r.rSize_y;
		}
	})
}


// 2. 라켓명 유효성 검사 메소드
let checkconfirm = document.querySelector('.checkconfirm');
function midcheck(){
	let rName= document.querySelector('.rName').value;

	// 아이디 중복검사
	$.ajax({
		url : "/ten__needs/admin/racket",
		method : "get",
		data : { "getType": "check", "rName" : rName}, // type: check
		success : (r)=>{
			if( r == 'true'){
				checkconfirm[0].innerHTML = '사용중인 라켓명 입니다.'
			}else{
				checkconfirm[0].innerHTML = '✓'
			}
		}
	})
}

// 4. 라켓 수정
function updateRacket(){
	
	let updateForm = document.querySelectorAll('.updateForm')[0];
	let updateFormData = new FormData( updateForm );
	
	$.ajax({
		url : "/ten__needs/admin/racket",
		method : "put",
		data : updateFormData,
		contentType : false,			
		processData : false,
		success : (r)=>{
			if( r == 'true'){
				alert('[알림] 라켓정보 수정 완료');
				location.href="/ten__needs/tenneeds/jsp/main.jsp"
			}else{
				alert('[알림] 라켓정보 수정 실패')
			}
		}
	})
}

// 6. 라켓 삭제 ----------------------------------------------------------------- 기능 안됨................................
function onDelete() {
	let dName = document.querySelector('#dName').value;
	console.log(dName);
    $.ajax({
        url: "/ten__needs/admin/racket",
        method: "delete",
        data: { "dName": dName },
        success: (r) => {
			console.log(dName);
            console.log("onDelete 결과");
            console.log(r);
            if (r == 'true') {
                alert('[알림] 라켓 데이터 정상 삭제')
                location.href = "/ten__needs/tenneeds/jsp/main.jsp"
            } else {
                alert('[알림] 라켓 데이터 삭제 실패(라켓명 불일치)')
            }
        }
    })
}