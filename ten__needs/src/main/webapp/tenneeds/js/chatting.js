console.log('작동 확인');

let geme_chat = document.querySelector('.geme_chat');

// sockat server 연결
let chattingSockat = new WebSocket('ws://192.168.17.134:8080/ten__needs/tenneeds/chatting/'+memberInfo.mid);

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

function onClose( e ){
	// 서버에서 메시지 작성해야함
}