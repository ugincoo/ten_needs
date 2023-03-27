console.log('update.js 열림');

$(document).ready(function() { /*썸머노트 */
        $('#summernote').summernote({
			 minHeight: 300 
		});
});

let bno = document.querySelector('.bno').value;

getBoardViewInfo();

//해당 정보 가져오기 
function getBoardViewInfo(){
	$.ajax({
		url : '/ten__needs/board/info',
		method : "get",
		data : {"type" : 2, "bno" : bno}, //1. 전체 출력 2. 개별 출력
		success : (r) => {
			console.log(r)
			if(r.bwritedate != null){
				document.querySelector('.bTitle').value = r.btitle;
			
				//썸머노트에 값넣기
	  			$('#summernote').summernote('code', r.bcontent);
				
			}else{
				alert('정보를 조회할 수 없습니다. 관리자에게 문의해주새요.')
			}
		}
	})
}


//정보 수정
function updateBoard(){
	let writeForm = document.querySelectorAll('.writecontent')[0];

	let writeFormData = new FormData(writeForm);
	
	writeFormData.set('bno', bno);
	
	$.ajax({
		url : '/ten__needs/board/info',
		method : "put",
		data : writeFormData,
		contentType : false,
		processData : false,
		success : (r) =>{
			if(r == 'true'){
				alert('글수정 성공')
				location.href = "/ten__needs/tenneeds/jsp/board/view.jsp?bno="+bno;
			}else{
				alert('글수정 실패. 관리자에게 문의해주세요.')
			}
		}
		
	})
}

