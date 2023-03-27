console.log('write.js 실행')

$(document).ready(function() { /*썸머노트 */
        $('#summernote').summernote({
			 minHeight: 300 
		});
});
     
    
/* 게시글 쓰기 */
function writeBoard(){
	let writeForm = document.querySelectorAll('.writecontent')[0];

	let writeFormData = new FormData(writeForm);

	$.ajax({
		url : '/ten__needs/board/info',
		method : "post",
		data : writeFormData,
		contentType : false,
		processData : false,
		success : (r) =>{
			if(r == 'true'){
				alert('글쓰기 성공')
				location.href = "/ten__needs/tenneeds/jsp/board/list.jsp";
			}else{
				alert('글쓰기 실패. 관리자에게 문의해주세요.')
			}
		}
		
	})
}

