console.log('작동 확인');

let geme_chat = document.querySelector('.geme_chat');

// sockat server 연결
let chattingSockat = new WebSocket('ws://localhost:8089/ten__needs//tenneeds/chatting/'+memberInfo.mid);

// open, message, close 작동 on
chattingSockat.onopen = function(e){ inputChat(e); }
chattingSockat.onmessage = function(e){ onMessage(e); }
chattingSockat.onclose = function(e){ onClose(e); }

function inputChat(e){
		console.log(e); // --- 작동 확인
	geme_chat.innerHTML += `<div class="alarmBox"> ${memberInfo.mid} 님이 접속하셨습니다. </div>`
	
}

function sendMessage(){
	
	let chatContent = document.querySelector('.chatContent').value;
	
	// chattingSockat.send( chatContent );
	// 타입 오류 발생
	chattingSockat.send( JSON.stringify(chatContent) );
	// game_chatbox로 전송 해보기
	
	document.querySelector('.chatContent').value = '';
}

function onMessage( e ){
	console.log(e);
	console.log(e.data);
	console.log( JSON.parse(e.data) );
	
}

function onClose(){
	
}