
let pageObject = {
	page : 1,
	key : "",
	keyword : "",
	type : 1, 			// 1.전체 출력(기본값), 2.개별 출력
	getType: "racket"	// method get 분류: check-rName 중복체크, racket-racket정보출력
}

// 3. 첨부파일 미리보기 메소드
function premimg( object ){
	
	let file = new FileReader();
	file.readAsDataURL( object.files[0] )
	file.onload = (e)=>{
		document.querySelector('.prerimg').src = e.target.result;
	}
}

// 5. 라켓 출력
let raketInfo = [];
printRacket(1);
function printRacket(page){
	pageObject.page = page;
	$.ajax({
		url : "/ten__needs/admin/racket",
		method : "get",
		data : pageObject,
		success : (r) => {
			raketInfo.push(r);
			console.log(r);
			let html = `
						<tr>
							<td> 번호 </td>
							<td class="rImgT"> 라켓사진 </td>
							<td> 라켓명 </td>
							<td> 라켓레벨 </td>
							<td> 라켓크기(가로) </td>
							<td> 라켓크기(세로) </td>
						</tr>
						`;
			if(r.racketList != null){
				r.racketList.forEach((o) => {
					html += `
							<tr>
									<td class = "rNo">${o.rNo}</td>
									<td><img class="rImg" src="/ten__needs/tenneeds/jsp/game/img/rimg/${o.rImg == null ? 'default.png' : o.rImg }" width:"15%"></td>
									<td class = "rNo"><a href = "/ten__needs/tenneeds/jsp/admin/adminRacketView.jsp?rNo=${o.rNo}">${o.rName}</a></td>
									<td class = "rLevle">${o.rLevle}</td>
									<td class = "rSize_x">${o.rSize_x}</td>
									<td class = "rSize_y">${o.rSize_y}</td>
								
							</tr>`
				})
			
			}else{
				html = `<div class = "racketContent">
							등록된 라켓이 없습니다.
						</div>`
			}
			document.querySelector('.racketTable').innerHTML = html;	
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
function racketSearch(){
	pageObject.key = document.querySelector('.key').value;
	pageObject.keyword = document.querySelector('.keyword').value;
	console.log(pageObject.key);
	console.log(pageObject.keyword);
	printRacket(1);
}

//3. 검색 초기화
function setSearch(){
	pageObject.key = "";
	pageObject.keyword = "";
	
	document.querySelector('.keyword').value = "";
	
	printRacket(1);
}

