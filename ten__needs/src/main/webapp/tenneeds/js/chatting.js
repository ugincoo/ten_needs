	// console.log('chatting.js 작동 확인'); //--- 확인 완료
//console.log(gameInfo);

let gametitle_box = document.querySelector('.gametitle_box');
let geme_chat = document.querySelector('.geme_chat');
let gNo = document.querySelector('.gNo').value;
// sockat server 연결
let chattingSockat = null;

if( memberInfo.mid == null ){
	alert('로그인필요');
}else{
	chattingSockat = new WebSocket('ws://192.168.17.40:8080/ten__needs/tenneeds/chatting/'+gNo+'/'+memberInfo.mid);	

	// open, message, close 작동 on
	chattingSockat.onopen = function(e){ inputChat(e); }
	chattingSockat.onmessage = function(e){ onMessage(e); }
	chattingSockat.onclose = function(e){ onClose(e);}
}
// --------------------------------------------------------- 접속 확인
function inputChat(e){
		console.log(e); // --- 작동 확인
	
	connectServer( "alarm", memberInfo.mid );
	
}

// --------------------------------------------------------- 서버 소캣으로 메시지 보내는 메소드
function connectServer( type, data ){
	let msgBox = {
		type: type,
		data: data
	}
	// console.log(msgBox.type);	console.log(msgBox.data);
	chattingSockat.send( JSON.stringify(msgBox) );
}

// --------------------------------------------------------- 채팅 관련 메소드
function sendMessage(){
	
	let chatContent = document.querySelector('.chatContent').value;
	
	connectServer("chat", chatContent);
	
	document.querySelector('.chatContent').value = '';	
}

let countStart = 0;
let info = null;

function onMessage( e ){
	console.log(e); // --- 확인 완료
	//console.log(e.data); // --- 확인 완료
	// console.log( JSON.parse(e.data) ); // --- 확인 완료
	 
	let data = JSON.parse(e.data);
	
	if(data.length >= 2){
		info = data;
	}
	
	
	if( Array.isArray( data ) ){ // ------------- 상단 프로필 상태 출력
		console.log(data);
		let html = '';
		data.forEach( (d) =>{
			console.log(d);
			if( d.mId == memberInfo.mid ){
				html +=`
						<div class="game_user1" id="game_user" name="game_user">
		                  <img src="/ten__needs/tenneeds/jsp/member/mimg/${d.mImg==null ? 'default.png' : d.mImg}" class="profileUser1" id="profile" name="profile">
		                  <div class="midUser1" id="mid" name="mid"> ${d.mId} </div>
		                  <div class="ready_stateUser1" id="ready_state" name="ready_state"> </div>
		               </div>
						`
			} else{
				html += `
						<div class="game_user2" id="game_user" name="game_user">
		                  <img src="/ten__needs/tenneeds/jsp/member/mimg/${d.mImg==null ? 'default.png' : d.mImg}" class="profileUser2" id="profile" name="profile">
		                  <div class="midUser2" id="mid" name="mid"> ${d.mId} </div>
		                  <div class="ready_stateUser2" id="ready_state" name="ready_state">  </div>
		               </div>
						`
			}
			document.querySelector('.game_userbox').innerHTML = html;		
		})
	} else if( JSON.parse(data.msg).type == "chat" ){ // ------------- 메시지 출력
		if( data.mId == memberInfo.mid ){
			geme_chat.innerHTML += `
									<div class="sendWrap">
				            			<div class="sDate"> ${ data.time } </div>
				            			<div class="sContent"> ${ JSON.parse(data.msg).data } </div>
				            		</div>
								`
		} else{
			geme_chat.innerHTML += `
									<div class="receiveWrap">
					            		<div class="rImgBox"> <img class="rImg" alt="" src="/ten__needs/tenneeds/jsp/member/mimg/${data.mImg==null ? 'default.png' : data.mImg}"> </div>
					            		<div class="rBox">
					            			<div class="rName"> ${data.mId} </div>
					            			<div class="rContentDateBox">
					            				<div class="rContent"> ${ JSON.parse(data.msg).data } </div>
					            				<div class="rDate"> ${ data.time } </div>
					            			</div>
					            		</div>
					            	</div>
									`
		}} else if( JSON.parse(data.msg).type == "alarm" ){ // ------------- 알람 출력
				geme_chat.innerHTML += `
									<div class="alarm">
										<span> ${data.mId}님이 입장하셨습니다. </span>
									</div>
									`
			} else if( JSON.parse(data.msg).type == 'out' ){ // ------------- 사용자 나감 알람 출력
				geme_chat.innerHTML += `
									<div class="alarm">
										<span> ${data.mId}님이 퇴장하셨습니다. </span>
									</div>
									`
			}  else if( JSON.parse(data.msg).type == "game" ){
				
				if( JSON.parse(data.msg).data ){
					countStart++;
					if( data.mId == memberInfo.mid ){
						document.querySelector('.ready_stateUser1').innerHTML='READY'; 
					} else{ document.querySelector('.ready_stateUser2').innerHTML='READY'; }
					
				}
				else{
					 countStart--;
					if( data.mId == memberInfo.mid ){
						document.querySelector('.ready_stateUser1').innerHTML='';
					} else{ document.querySelector('.ready_stateUser2').innerHTML='';}
				}
				
				console.log(countStart);
				console.log(gNo);
				
				console.log(info[0])
				console.log(info[1])
				
				if( countStart == 2 ){
					setTimeout( ()=>{
					   location.href = `/ten__needs/tenneeds/jsp/game/pingpong.jsp?gNo=${gNo}&user1=${info[0].mId}&user2=${info[1].mId}`;
					   }, 5000 )
				}
			}

	let top = geme_chat.scrollTop;
		// console.log(top); // --- 확인완료
	
	let height = geme_chat.scrollHeight;
		
	geme_chat.scrollTop = geme_chat.scrollHeight;
	
}

// --------------------------------------------------------- 게임 관련 메소드
let clickCount = 0;
function gamestart(){
	let readyState = false;
	clickCount++;
	
	if( clickCount%2 != 0 ){ // 홀수: 레디ON
		readyState = true;
	}else { // 짝수: 레디OFF
		readyState = false;
	}
	
	connectServer("game", readyState);
}

// --------------------------------------------------------- 연결 해제 메소드
function onClose( e ){
	console.log(e);
	if(e.currentTarget.readyState == 3){
		// alert('[알림] 정원 초과')
		location.href="/ten__needs/tenneeds/jsp/game/gamelist.jsp"
		
	}
}

// ---------------------------------------------------------- 게임 정보 호출 메소드
getGame();
function getGame( ){
	$.ajax({
		url : '/ten__needs/tenneeds/creategame',
		method : "get",
		data : { "type": 2, "gno": gNo },
		success : (r) => {
				console.log(r.gNo);
				console.log(r.gTitle);
				gametitle_box.innerHTML += `
											<div class="gno"> ${ r.gNo } </div>
			        						 <div class="gtitle"> ${ r.gTitle } </div>
											`
				}
		})
}

// 엔터키를 눌렀을때 전송
document.addEventListener('keyup', (e)=>{
	// 만약에 입력한 키 코드가 13[엔터] 이면 메시지전송
	if( window.event.keyCode == 13 ){
		sendMessage();
	}
});
