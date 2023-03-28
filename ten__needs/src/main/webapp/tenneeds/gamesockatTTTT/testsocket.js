console.log('작동 확인');

// sockat server 연결
let gameSockat = new WebSocket('ws://localhost:8089/ten__needs//test/sockat/'+memberInfo.mid);

// open, message, close 작동 on
gameSockat.onopen = function(e){ inputGame(e); }
gameSockat.onmessage = function(e){ onGame(e); } // --- 게임에서도 message사용?
gameSockat.onclose = function(e){ onClose(e); }

function inputGame(e){
		console.log('연결 확인');	
}