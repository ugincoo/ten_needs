console.log('board view js 실행')

let bno = document.querySelector('.bno').value;

console.log(bno)

if(memberInfo.mid == null){
	document.querySelector('.rcontent').disabled = true;
	document.querySelector('.replyBtn').disabled = true;
	document.querySelector('.rcontent').innerHTML = '로그인 후에 댓글 작성 가능합니다.'
}

getBoardContent();

//1. 개별 board 출력
function getBoardContent(){
	$.ajax({
		url : '/ten__needs/board/info',
		method : "get",
		data : {"type" : 2, "bno" : bno}, //1. 전체 출력 2. 개별 출력
		success : (r) => {
			console.log(r);

			if(r.bwritedate != null){
				document.querySelector('.btitle').innerHTML = r.btitle;
				document.querySelector('.bcontent').innerHTML = r.bcontent;
				document.querySelector('.bwritedate').innerHTML = r.bwritedate;
				document.querySelector('.replycount').innerHTML = r.rcount + "개의 댓글";
				
			}else{
				alert('정보를 조회할 수 없습니다. 관리자에게 문의해주세요.')
			}
			
			if(memberInfo.mid == 'admin'){ //관리자면 해당 글을 삭제하거나 수정할 수 잇다.
				let html = `<button class = "blbtn" onClick = "updateBoard()" type = "button" >수정</button>
							<button class = "blbtn" onClick = "deleteBoard()" type = "button" >삭제</button>`
				document.querySelector('.btnBox').innerHTML = html;
			}
			
			/* 댓글 출력 */
			getReplyList();	
		}
	})
}

//2. 게시물 삭제
function deleteBoard(){
	$.ajax({
		url : '/ten__needs/board/info',
		method : "delete",
		data : {"bno" : bno},
		success : (r) => {
			if(r == 'true'){
				alert('삭제 되었습니다.')
				location.href = "/ten__needs/tenneeds/jsp/board/list.jsp";
			}else{
				alert('삭제할 수 없습니다. 관리자에게 문의 부탁드립니다.')
			}
		}
		
	})
}

//3. 글 수정
function updateBoard(){
	location.href = "/ten__needs/tenneeds/jsp/board/update.jsp?bno="+bno;
}

//4. 댓글 출력
function getReplyList(){
	$.ajax({
		url : '/ten__needs/board/reply',
		method : "get",
		data : {"bno" : bno},
		success : (r) => {
			console.log(r);
			let html = ``;
			if(r != null){
				r.forEach((o) => {
					console.log(o)
					html += `<div class = "replyOne">
								<div class = "replyTopContent">
									<img class = "replyProfile" src = "/ten__needs/tenneeds/jsp/member/mimg/${ o.mimg == null ? 'default.webp' : o.mimg }" />
									<span class = "replyName">${o.mId}</span>
									<span class = "replyDate">${o.reDate}</span>
									<div clsss = "replyBtns">${
											(o.mId == memberInfo.mid || memberInfo.mid == 'admin') ? `<button class = "blbtn" onClick = "updateReply(${o.reNo})" type = "button" >수정</button>
											<button class = "blbtn" onClick = "deleteReply(${o.reNo})" type = "button" >삭제</button>` : 
										' '}</div>
								</div>
								<div class = "replyContentDiv">
									<span class = "replyContent">${o.reContent}</span>
								</div>
								<div class = "replyBoxs replyBox${o.reNo}"></div>
							</div>`
					
						
				})
				
				document.querySelector('.replayListBox').innerHTML = html;
				
			}
		}
	})
}

//5. 댓글 작성
function writeReply(){
	let rcontent = document.querySelector('.rcontent').value;
	
	$.ajax({
		url : '/ten__needs/board/reply',
		method : "post",
		data : {"bno" : bno, "reContent" : rcontent},
		success : (r) => {
			if(r == 'true'){
				alert('댓글이 작성되었습니다.')
				/*$(".replayListBox").load(loaction.href+' .replayListBox'); //새로고침*/
				location.href = "/ten__needs/tenneeds/jsp/board/view.jsp?bno="+bno;
			}else{
				alert('댓글작성에 실패하엿씁니다. 관리자에게 문의해주세요')
			}
		}
	})
}

//댓글 수정
function updateReply(reNo){
	document.querySelector('.replyBox'+reNo).innerHTML = `<div class = "replyWindow">
															<textarea  class = "newrcontent" rows="" cols=""></textarea>
															<button class = "blbtn" onClick = "realupdateReply(${reNo})" type = "button" >댓글수정</button>
														  </div>`
	
}




//댓글 진짜 수정
function realupdateReply(reNo){
	let newRcontent = document.querySelector('.newrcontent').value;
	console.log(newRcontent)
	
	$.ajax({
		url : '/ten__needs/board/reply',
		method : "put",
		data : {"reNo" : reNo, "reContent" : newRcontent},
		success : (r) => {
			if(r == 'true'){
				alert('댓글이 수정되었습니다.')
				/*$(".replayListBox").load(loaction.href+' .replayListBox'); //새로고침*/
				location.href = "/ten__needs/tenneeds/jsp/board/view.jsp?bno="+bno;
			}else{
				alert('댓글수정에 실패하엿씁니다. 관리자에게 문의해주세요')
			}
		}
	})
}

//댓글 삭제
function deleteReply(reNo){
	
	$.ajax({
		url : '/ten__needs/board/reply',
		method : "delete",
		data : {"reNo" : reNo},
		success : (r) => {
			if(r == 'true'){
				alert('댓글이 삭제되었습니다.')
				/*$(".replayListBox").load(loaction.href+' .replayListBox'); //새로고침*/
				location.href = "/ten__needs/tenneeds/jsp/board/view.jsp?bno="+bno;
			}else{
				alert('댓글삭제에 실패하엿씁니다. 관리자에게 문의해주세요')
			}
		}
	})
}



