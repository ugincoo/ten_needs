console.log('list js 열림');

/* 현재 페이지, 검색, 전송타입 */
let pageObject = {
	page : 1,
	key : "",
	keyword : "",
	type : 1, //1. 전체 출력 2. 개별 출력
}
if(memberInfo == null){
	getBoard(1);
	
}else if(memberInfo.mid == 'admin'){
	document.querySelector('.boardtopetc').innerHTML = `
		<a href = "write.jsp">
			<button class = "blbtn writebtn" type = "button" >글쓰기</button>
		</a>`
} else{
		document.querySelector('.boardtopetc').innerHTML = `
		<a href = "/ten__needs/tenneeds/jsp/map.jsp">
			<button class = "blbtn writebtn" type = "button" >테니스장 지도보기</button>
		</a>`
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
			if(r.boardList != null){
				console.log(r);	
				r.boardList.forEach((o) => {
				
					html += `<div class = "boardContent">
								<div>
									<img class = "profile" src = "/ten__needs/tenneeds/jsp/board/bimg/admin.png"/>
									<span>관리자</span>
									<span class = "bwritedate">${o.bwritedate}</span>
								</div>
								
								<div class = "bTitle">
									<a href = "/ten__needs/tenneeds/jsp/board/view.jsp?bno=${o.bno}">${o.btitle}</a>
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
				`<button class = "pagebtn" onClick = "getBoard(${page})" type = "button"><i class="fas fa-angle-left"></i></button>`	
				:
				`<button class = "pagebtn" onClick = "getBoard(${page-1})" type = "button"><i class="fas fa-angle-left"></i></button>`	;
			
			for(let i = r.startBtn; i <= r.endBtn; i++){ //시작 버튼 번호부터 마지막 버튼 번호까지 버튼 생성
				html += `<button class = "pagebtn" onClick = "getBoard(${i})" type = "button">${i}</button>`	
			}
			
			html += page >= r.totalpage ?
			` <button class = "pagebtn" onClick = "getBoard(${page})" type = "button"><i class="fas fa-angle-right"></i></button>`	
			:	
			` <button class = "pagebtn" onClick = "getBoard(${page+1})" type = "button"><i class="fas fa-angle-right"></i></button>`	
			
			//맨뒤
			html += `<button class = "pagebtn" onClick = "getBoard(${r.totalpage})" type = "button"><i class="fas fa-angle-double-right"></i></button>`;
			
			document.querySelector('.pagebox').innerHTML = html;
			
			
		}
	})
}

//2. 키워드 검색
function boardSearch(){
	pageObject.key = document.querySelector('.key').value;
	pageObject.keyword = document.querySelector('.keyword').value;
	
	getBoard(1);
}

//3. 검색 초기화
function setSearch(){
	pageObject.key = "";
	pageObject.keyword = "";
	
	document.querySelector('.keyword').value = "";
	
	getBoard(1);
}

