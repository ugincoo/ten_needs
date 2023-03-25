console.log('list js 열림');

/* 현재 페이지, 검색, 전송타입 */
let pageObject = {
	page : 1,
	key : "",
	keyword : "",
	type : 1, //1. 전체 출력 2. 개별 출력
}

getBoard(1)
// 게시물 목록 출력
function getBoard(page){
	pageObject.page = page; //페이지 변경
	$.ajax({
		url : "/ten__needs/board/info",
		method : "get",
		data : pageObject,
		success : (r) => {
			let html = '';
			if(r != null){
				console.log(r);
				r.boardList.forEach((o) => {
					html += `<div class = "boardContent">
								<div>
									<img class = "profile" src = "/tenNeeds/tenneeds/board/bImg/admin.png"/>
									<span>관리자</span>
									<span class = "bwritedate">${o.bwritedate}</span>
								</div>
								
								<div class = "bTitle">
									<a href = "/tenNeeds/tenneeds/board/view.jsp?bno = ${o.bno}">${o.btitle}</a>
								</div>
							</div>`
				})
			
			}else{
				html = `<div class = "boardContent">
							공지사항/이벤트 게시물이 없습니다.
						</div>`
			}	
			document.querySelector('.boardTable').innerHTML = html;
			
			/* --------------------------------- 페이징 버튼 출력 --------------------------------- */
			html = ''; //출력 후 html 초기화
			
			//맨앞 
			html += `<button class = "pagebtn" onClick = "getBoard(1)" type = "button"><i class="fas fa-angle-double-left"></i></button>`;
			
			html += page <=1 ? 
				`<button class = "pagebtn" onClick = "printBoard(${page})" type = "button"><i class="fas fa-angle-left"></i></button>`	
				:
				`<button class = "pagebtn" onClick = "printBoard(${page-1})" type = "button"><i class="fas fa-angle-left"></i></button>`	;
			
			for(let i = r.startBtn; i <= r.endBtn; i++){ //시작 버튼 번호부터 마지막 버튼 번호까지 버튼 생성
				html += `<button class = "pagebtn" onClick = "printBoard(${i})" type = "button">${i}</button>`	
			}
			
			html += page >= r.totalpage ?
			` <button class = "pagebtn" onClick = "printBoard(${page})" type = "button"><i class="fas fa-angle-right"></i></button>`	
			:	
			` <button class = "pagebtn" onClick = "printBoard(${page+1})" type = "button"><i class="fas fa-angle-right"></i></button>`	
			
			//맨뒤
			html += `<button class = "pagebtn" onClick = "printBoard(${r.totalpage})" type = "button"><i class="fas fa-angle-double-right"></i></button>`;
			
			document.querySelector('.pagebox').innerHTML = html;
			
			
		}
	})
}