console.log('board view js 실행')

let memberInfo = {
	mid : 'admin',
	mpwd : '1',
	memail : 'admin123@kakao.com',
	mphone : '010-1111-1111'
}

let bno = document.querySelector('.bno').value;

if(memberInfo.mid == null){
	document.querySelector('.rcontent').disabled = true;
	document.querySelector('.replyBtn').disabled = true;
	document.querySelector('.rcontent').innerHTML = '로그인 후에 댓글 작성 가능합니다.'
}

function getBoardContent(){
	$.ajax({
		url : '/ten__needs/board/info',
		method : "get",
		data : {"type" : 2, "bno" : bno}, //1. 전체 출력 2. 개별 출력
		success : (r) => {
			if(r.mid != null){
				
			}
		}
	})
}
