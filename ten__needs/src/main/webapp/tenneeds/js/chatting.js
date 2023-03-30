console.log('작동 확인');

let gametitle_box = document.querySelector('.gametitle_box');
let geme_chat = document.querySelector('.geme_chat');
let gNo = document.querySelector('.gNo').value;
// sockat server 연결
let chattingSockat = new WebSocket('ws://localhost:8089/ten__needs/tenneeds/chatting/'+gNo+'/'+memberInfo.mid);

// open, message, close 작동 on
chattingSockat.onopen = function(e){ inputChat(e); }
chattingSockat.onmessage = function(e){ onMessage(e); }
chattingSockat.onclose = function(e){ onClose(e); }
// --------------------------------------------------------- 접속 확인
function inputChat(e){
		// console.log(e); // --- 작동 확인
	
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

function onMessage( e ){
	console.log(e); // --- 확인 완료
	// console.log(e.data); // --- 확인 완료
	// console.log( JSON.parse(e.data) ); // --- 확인 완료
	 
	let data = JSON.parse(e.data);

	if( Array.isArray( data ) ){ // ------------- 상단 프로필 상태 출력
		console.log(data);
		let html = '';
		data.forEach( (d) =>{
			console.log(d);
			if( d.mId == memberInfo.mid ){
				html +=`
						<div class="game_user1" id="game_user" name="game_user">
		                  <img src="/ten__needs/tenneeds/img/짱구.png" class="profileUser1" id="profile" name="profile">
		                  <div class="midUser1" id="mid" name="mid"> ${d.mId} </div>
		                  <div class="ready_stateUser1" id="ready_state" name="ready_state"> </div>
		               </div>
						`
			} else{
				html += `
						<div class="game_user2" id="game_user" name="game_user">
		                  <img src="/ten__needs/tenneeds/img/짱구.png" class="profileUser2" id="profile" name="profile">
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
					            		<div class="rImgBox"> <img class="rImg" alt="" src="/ten__needs/tenneeds/img/짱구.png"> </div>
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
			} else if( JSON.parse(data.msg).type == "game" ){
				if( JSON.parse(data.msg).data ){
					if( data.mId == memberInfo.mid ){
						document.querySelector('.ready_stateUser1').innerHTML='READY';
					} else{ document.querySelector('.ready_stateUser2').innerHTML='READY'; }
					
				}
				else{
					if( data.mId == memberInfo.mid ){
						document.querySelector('.ready_stateUser1').innerHTML='';
					} else{ document.querySelector('.ready_stateUser2').innerHTML=''; }
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
		// console.log(readyState);
		// 
	}else { // 짝수: 레디OFF
		readyState = false;
		// console.log(readyState);
		// 
	}
	
	connectServer("game", readyState);
	
	// let msgBox = {
	//	type: 'game',
	//	data: readyState
	// }
	// chattingSockat.send( JSON.stringify(msgBox) );

}

// --------------------------------------------------------- 연결 해제 메소드
function onClose( e ){
	// console.log(e);
	if(e.currentTarget.readyState == 3){
		// alert('[알림] 정원 초과')
		location.href="/ten__needs/tenneeds/jsp/game/gamelist.jsp"
		
	}
}


