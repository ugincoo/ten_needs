console.log('list js 열림');

// pageObject 객체 생성: 현재 페이지, 검색, 전송타입 설정
let pageObject = {
	page : 1,
	key : "",
	keyword : "",
	type : 1, 		//1. 전체 출력(기본값), 2. 개별 출력
}

if(memberInfo == null){
	alert('[알림] 로그인 회원만 이용 가능합니다.')
	location.href="/ten__needs/tenneeds/jsp/main.jsp"
}else {
	document.querySelector('.gametopetc').innerHTML = `
			<button onclick="openModal()" class = "glbtn writebtn" type = "button" >방만들기</button>
			`
}

let gameInfo = [];
getgame(1)

// 게임방 리스트 출력
function getgame(page){
	pageObject.page = page; //페이지 변경
	$.ajax({
		url : '/ten__needs/tenneeds/creategame',
		method : "get",
		data : pageObject,
		success : (r) => {
				gameInfo.push(r);
				console.log(gameInfo);
			
			let html = '';
			if(r.gameList != null){
					console.log(r.gameList);	
				r.gameList.forEach((o) => {
					html += `<div class = "gameContent${o.gNo}" name="gameContent" id="gameContent">
								<div>
									<span>${o.mId}</span>
									<span class = "bwritedate">${o.gDate}</span>
								</div>
								
								<div onclick="sendTitle(${o.gTitle})" class = "gTitle">
									<a href = "/ten__needs/tenneeds/jsp/game/gameroom.jsp?gNo=${o.gNo}">${o.gTitle}</a>
								</div>
							</div>`
				})
			
			}else{
				html = `<div class = "gameContent">
							생성된 게임방이 없습니다.
						</div>`
			}	
			document.querySelector('.gameTable').innerHTML = html;
			
			/* --------------------------------- 페이징 버튼 출력 --------------------------------- */
			
			html = ''; //출력 후 html 초기화
			
			//맨앞 
			html += `<button class = "pagebtn" onClick = "getgame(1)" type = "button"><i class="fas fa-angle-double-left"></i></button>`;
			
			html += page <=1 ? 
				`<button class = "pagebtn" onClick = "getgame(${page})" type = "button"><i class="fas fa-angle-left"></i></button>`	
				:
				`<button class = "pagebtn" onClick = "getgame(${page-1})" type = "button"><i class="fas fa-angle-left"></i></button>`	;
			
			for(let i = r.startBtn; i <= r.endBtn; i++){ //시작 버튼 번호부터 마지막 버튼 번호까지 버튼 생성
				html += `<button class = "pagebtn" onClick = "getgame(${i})" type = "button">${i}</button>`	
			}
			
			html += page >= r.totalpage ?
			` <button class = "pagebtn" onClick = "getgame(${page})" type = "button"><i class="fas fa-angle-right"></i></button>`	
			:	
			` <button class = "pagebtn" onClick = "getgame(${page+1})" type = "button"><i class="fas fa-angle-right"></i></button>`	
			
			//맨뒤
			html += `<button class = "pagebtn" onClick = "getgame(${r.totalpage})" type = "button"><i class="fas fa-angle-double-right"></i></button>`;
			
			document.querySelector('.pagebox').innerHTML = html;
			
		}
	})
}

//2. 키워드 검색
function gameSearch(){
	pageObject.key = document.querySelector('.key').value;
	pageObject.keyword = document.querySelector('.keyword').value;
	console.log(pageObject.key);
	console.log(pageObject.keyword);
	getgame(1);
}

//3. 검색 초기화
function setSearch(){
	pageObject.key = "";
	pageObject.keyword = "";
	
	document.querySelector('.keyword').value = "";
	
	getgame(1);
}

// 4, 게임 방만들기
function createGame(){
	let gTitle = document.querySelector('.gcTitle').value;
		
		console.log(gTitle);
		
		// console.log( memberInfo.mno ); // -- 확인완료
		
	$.ajax({
		url : '/ten__needs/tenneeds/creategame',
		method : "post",
		data : { "gTitle" : gTitle, "mno": memberInfo.mno },
		success : (r) =>{
			console.log(r);
			if(r == 'true'){
				alert('방생성 성공')
				location.href = "/ten__needs/tenneeds/jsp/game/gamelist.jsp";
			}else{
				alert('글쓰기 실패. 관리자에게 문의해주세요.')
			}
		}
	})
}
