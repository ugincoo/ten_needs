console.log('작동 확인');

let gametitle_box = document.querySelector('.gametitle_box');
let geme_chat = document.querySelector('.geme_chat');
let gNo = document.querySelector('.gNo').value;
// sockat server 연결
let chattingSockat = new WebSocket('ws://192.168.17.134:8080/ten__needs/tenneeds/chatting/'+gNo+'/'+memberInfo.mid);

// open, message, close 작동 on
chattingSockat.onopen = function(e){ inputChat(e); }
chattingSockat.onmessage = function(e){ onMessage(e); }
chattingSockat.onclose = function(e){ onClose(e); }
// --------------------------------------------------------- 접속 확인
function inputChat(e){
		console.log(e); // --- 작동 확인
	geme_chat.innerHTML += `<div class="alarmBox"> ${memberInfo.mid} 님이 접속하셨습니다. </div>`
	
}

// --------------------------------------------------------- 채팅 관련 메소드
function sendMessage(){
	
	let chatContent = document.querySelector('.chatContent').value;
	
	// chattingSockat.send( chatContent );
	// 타입 오류 발생
		console.log( JSON.stringify(chatContent) );
	chattingSockat.send( JSON.stringify(chatContent) );
	// game_chatbox로 전송 해보기
	
	document.querySelector('.chatContent').value = '';
}

function onMessage( e ){
	console.log(e);
	console.log(e.data);
	console.log( JSON.parse(e.data) );

	let data = JSON.parse(e.data);
	// ----------------------------------------------------------- type1, type2, tpy3
	if(  data.mId == memberInfo.mid ){
		geme_chat.innerHTML += `
								<div class="sendWrap">
				            		<div class="sDate"> ${ data.time } </div>
				            		<div class="sContent"> ${ JSON.parse(data.msg) } </div>
				            	</div>
								`
	} else{
		geme_chat.innerHTML += `
								<div class="receiveWrap">
				            		<div class="rImgBox"> <img class="rImg" alt="" src="/ten__needs/tenneeds/img/짱구.png"> </div>
				            		<div class="rBox">
				            			<div class="rName"> ${data.mId} </div>
				            			<div class="rContentDateBox">
				            				<div class="rContent"> ${ JSON.parse(data.msg) } </div>
				            				<div class="rDate"> ${ data.time } </div>
				            			</div>
				            		</div>
				            	</div>
								`
	}
	let top = geme_chat.scrollTop;
		console.log(top);
	
	let height = geme_chat.scrollHeight;
		console.log(height);
	
	geme_chat.scrollTop = geme_chat.scrollHeight;
}

// --------------------------------------------------------- 게임 관련 메소드
let clickCount = 0;
function gamestart(){
	let readyState = false;
	clickCount++;
	if( clickCount%2 != 0 ){ // 홀수: 레디ON
		readyState = true;
		chattingSockat.send( JSON.stringify(readyState) );
		document.querySelector('.ready_state').innerHTML='READY';
	}else if( clickCount%2 == 0 ){ // 짝수: 레디OFF
		readyState = false;
		document.querySelector('.ready_state').innerHTML='';
	}

	let startbtn = document.querySelector('startbtn');
	console.log(startbtn)

}



// --------------------------------------------------------- 연결 해제 메소드
function onClose( e ){
	// console.log(e);
	if(e.currentTarget.readyState == 3){
		// alert('[알림] 정원 초과')
		location.href="/ten__needs/tenneeds/jsp/game/gamelist.jsp"
		
	}
}