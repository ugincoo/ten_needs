
//게임방 번호
let gNo = document.querySelector('.gNo').value;
console.log(gNo)

//플레이어들의 mid
let user1Mid = document.querySelector('.user1Mid').value;
let user2Mid = document.querySelector('.user2Mid').value;
console.log(user1Mid)
console.log(user2Mid)
//플레이어들의 mno
let user1Mno = document.querySelector('.user1Mno').value;
let user2Mno = document.querySelector('.user2Mno').value;
console.log(user1Mno)
console.log(user2Mno)

// 소켓 연결
let ballSocket = null;
let gameSocket = null;

if( memberInfo == null ){}

else{
	// ballSocket 
	ballSocket = new WebSocket('ws://localhost:8080/ten__needs/ball/'+gNo+'/'+memberInfo.mno);
	ballSocket.onopen = (e)=>{ console.log('서버소켓 들어'); ballOpen(e);}
	ballSocket.onclose = (e)=>{ console.log('서버소켓 나감');}
	ballSocket.onerror = (e)=>{ console.log('서버소켓 오류');}
	ballSocket.onmessage = (e)=>{ballMessage(e);}
	
	// gameSocket
	gameSocket = new WebSocket('ws://localhost:8080/ten__needs/game/'+gNo+'/'+memberInfo.mno);

	gameSocket.onopen = (e)=>{ console.log('서버소켓 들어');}

	gameSocket.onclose = (e)=>{ console.log('서버소켓 나감')}

	gameSocket.onerror = (e)=>{ console.log('서버소켓 오류')}

	gameSocket.onmessage = (e)=>{onMessage(e);}
}


let ball = {
	x: 0,
	y: 0,
	radius: 0,
	speed: 0,
	velocityX: 0,
	velocityY: 0,
	color: null
}

function ballOpen(e){
	console.log( 'ballOpen 확인');
}

// 메시지 받는 창구
function ballMessage( e ){
	console.log(e); //--- 확인 완료
	
	let data = JSON.parse(e.data);
	console.log(data);
	
	if( data.ballState === 0 ){
		console.log('작동확인');
		ball = {
			x : data.x/2,
			y : data.y/2,
			radius : data.radius,
			speed : data.speed,
			velocityX : data.velocityX,
			velocityY : data.velocityY,
			color : data.color
		}
		drawCircle( ball.x, ball.y, ball.radius, ball.color )
	}
	
}

function drawCircle(x, y, r, color){
	ctx.fillStyle = color;
	ctx.beginPath();
	ctx.arc(x, y, r, 0, Math.PI*2, false);
	ctx.closePath();
	ctx.fill();
}

// 메시지 보내는 창구
function connectServer( type, data ){
	let msgBox = {
		type: type,
		data: data
	}
	ballSocket.send( JSON.stringify(msgBox) );
}	

function onOpne(e){

}

//움직였을때의 서버에게 메시지(움직임정보) 보내 & 처음 접속했을때
function sendMessage(type, info){
	
}

//서버로부터 상대방의 움직임 정보를 받기
function onMessage(e){
	console.log(e)
	console.log(e.data);
	
	let userData = JSON.parse(e.data);
	console.log(userData)
	
	if(userData.mno == memberInfo.mno){
		user1 = {
				mno : userData.mno,
				x : userData.x,
				y : userData.y,
				width : 80,
				height : 80,
				score : 0,
				win : false,
				smash : 0,
				swing : 0,
				rno : userData.rno,
				draw(){
					let no = imageno;
					ctx.drawImage(user1Image[no], this.x , this.y, this.width, this.height);	
				}
			}
			// 라켓정보 설정 및 출력
			$.ajax({
				url : "/ten__needs/game/result",
				method : "get",
				data : {"type" : 1, "rno" : userData.rno},
				async : false,
				success : (r) => {
					console.log(r); 
					
					console.log(r + " : " + r.rImg)
					
					if(r != null){
						document.querySelector('.player1racket').src = `/ten__needs/tenneeds/jsp/game/img/rimg/${r.rImg}`;
						
						document.querySelector('.player1racketnm').innerHTML = r.rName
					
						document.querySelector('.player1Name').innerHTML = memberInfo.mid
					}
				}
			})
	
			console.log("user1" + user1)
	}else{
			
		user2 = {
				mno : userData.mno,
				x : userData.x,
				y : userData.y,
				width : 80,
				height : 80,
				score : userData.score,
				win : false,
				smash : 0,
				swing : 0,
				rno : userData.rno,
				draw(){
					let no = imageno;
					ctx.drawImage(user2Image[no], this.x , this.y, this.width, this.height);
				}			
			}
			// 라켓정보 설정 및 출력
			$.ajax({
				url : "/ten__needs/game/result",
				method : "get",
				data : {"type" : 1, "rno" : userData.rno},
				async : false,
				success : (r) => {
					console.log(r); 
					
					console.log(r + " : " + r.rImg)
					
					if(r != null){
						document.querySelector('.player2racket').src = `/ten__needs/tenneeds/jsp/game/img/rimg/${r.rImg}`;
						
						document.querySelector('.player2racketnm').innerHTML = r.rName
					
						document.querySelector('.player2Name').innerHTML = user1Mid != memberInfo.mid ? user1Mid : user2Mid;
						
					}
				}
			})
	
			console.log("user2" + user2)
	}
	
}

const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');
	
	
// 방향키 전역 변수
let rightPressed = false;	// 우키 상태
let leftPressed = false;	// 좌키 상태
let upPressed = false;		// 상키 상태
let downPressed = false;	// 하키 상태
let spacePressed = false; //스페이스여부
// 선언 이유: 아래 방향키 작동 메소드로 만들어 사용하고자 함
let player = null;


// 게임 사용자 정의	
// user1 이미지
let user1Image = new Array(3);
user1Image[0] = new Image();
user1Image[0].src = "wUser1.png"
user1Image[1] = new Image();
user1Image[1].src = "wUser2.png"
user1Image[2] = new Image();
user1Image[2].src = "wUser3.png"
let imageno = 0;
let user1 = {
	mno : user1Mno,
	x : canvas.width/2 - 100/2,
	y : 0,
	width : 80,
	height : 80,
	score : 0,
	win : 0,
	smash : 0,
	swing : 0,
	rno : 0,
	draw(){
		let no = imageno;
		ctx.drawImage(user1Image[no], this.x , this.y, this.width, this.height);
	}
}

// user2 이미지
let user2Image = new Image();
user2Image.src = "mUser1.png"
let user2 = {
	mno : user2Mno,
	x : canvas.width/2 - 100/2,
	y : canvas.height - 80,
	width : 80,
	height : 80,
	score : 0 ,
	win : 0,
	smash : 0,
	swing : 0,
	rno : 0,
	draw(){
		ctx.drawImage(user2Image, this.x , this.y, this.width, this.height);
	}
}
// 경기장 
// 경기장 이미지
const stadiumImage = new Image();
stadiumImage.src = "background.jpg"
const stadium = {
	x : 0,
	y : 0, 
	width : 1179,
	height : 800,
	draw(){
		ctx.fillRect(this.x , this.y, this.width, this.height);
		ctx.drawImage(stadiumImage, this.x , this.y, canvas.width, canvas.height);
	}
}

// 글쓰기
function drawText(text, x, y, color){
	ctx.fillStyle = color;
	ctx.font = "45px fantasy";
	ctx.fillText(text, x, y);
}

//패들 방향키 조
document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);

function keyDownHandler(event) {
	if (event.key === "Right" || event.key === "ArrowRight") { // --- 아스키코드 적용해도 무방
		rightPressed = true;
	} else if (event.key === "Left" || event.key === "ArrowLeft") {
	    leftPressed = true;
	} else if (event.key === "Up" || event.key === "ArrowUp") {
	    upPressed = true;
	} else if (event.key === "Down" || event.key === "ArrowDown") {
	    downPressed = true;
	} else if (event.key === "" || event.keyCode === 32) {
	    spacePressed = true;
	    if(user1.x > canvas.width/2 - 100/2 ){
			imageno = 1;
		}else{
			imageno = 2;
		}
	} 
}

function keyUpHandler(event) {
	if (event.key === "Right" || event.key === "ArrowRight") {
		rightPressed = false;
	} else if (event.key === "Left" || event.key === "ArrowLeft") {
	    leftPressed = false;
	} else if (event.key === "Up" || event.key === "ArrowUp") {
	    upPressed = false;
	} else if (event.key === "Down" || event.key === "ArrowDown") {
	    downPressed = false;
	} else if (event.key === "" || event.keyCode === 32) {
		//3초 후에 spacePressed = false로 적용
	    setTimeout(() => spacePressed = false, imageno = 0, 3000); //정확함도를 낮추기 위해서
		if(player == user1){
				user1.swing++;
				console.log(user1.swing)
		}else{
				user2.swing++;	
		}
	} 
}
// 공과 플레이어 충돌
function collision(b, p){
	b.top = b.y - b.radius;
	b.bottom = b.y + b.radius;
	b.left = b.x - b.radius;
	b.right = b.x + b.radius;
	
	p.top = p.y;
	p.bottom = p.y + p.height;
	p.left = p.x;
	p.right = p.x + p.width;
	
	return b.right > p.left && b.bottom > p.top && b.left < p.right && b.top < p.bottom;
}
// COM 또는 USER가 득점하면 공을 재설정합니다.
function resetBall(){
	ball.x = canvas.width/2;
	ball.y = canvas.height/2;
	
	ball.speed = 5;
	ball.velocityY = -ball.velocityY;
}

let round = 1; //게임 라운드 수를 선정하는 변수(3라운드까)
 //최종 라운드 체크
function checkRound(){
	if(user1.win >= 2 || user2.win >= 2){
		let playerWin = "";
		let playerLose = "";
		
		let winnergsAccute = 0;
		let losergsAccute = 0;
		cancelAnimationFrame(game)
		if(user1.win > user2.win){
			playerWin = user1.mno;
			playerLose = user2.mno;
			
			winnergsAccute = user1.smash/user1.swing;
			losergsAccute = user2.smash/user2.swing;
			alert('user1이 최종 승리')
			
		}else{
			playerWin = user2.mno;
			playerLose = user1.mno;
			
			winnergsAccute = user2.smash/user2.swing;
			losergsAccute = user1.smash/user1.swing;
			alert('user2이 최종 승리')
		}
		
		let gameresult = {
			winner : playerWin,
			loser : playerLose,
			winnergsAccute : winnergsAccute, //정확도(이긴사람)
			losergsAccute : losergsAccute //정확도(진사람)
		}
		
		console.log(user1.swing)
		console.log(user1.smash)
		console.log(gameresult)

		$.ajax({
			url : "/ten__needs/game/result",
			method : "post",
			data : gameresult,
			success : (r) => {
				if(r == 'true'){
					location.href = "/ten__needs/tenneeds/jsp/game/gamelist.jsp";
				}
			}
			
		})
	}
}
// 1초 반복 실행
function game(){
    requestAnimationFrame(game);
   
    ctx.clearRect(0, 0, canvas.width, canvas.height);	 // 캔버스 지워주기
    stadium.draw(); // 경기장 
    drawCircle(ball.x, ball.y, ball.radius, ball.color);	// 공 그려주기
    drawText(user1.score, 3*canvas.width/4, canvas.height/5, "white");	// 유저1 스코어
	drawText(user2.score, 3*canvas.width/4, 4.2*canvas.height/5, "white");	// 유저2 스코어
    
    // 플레어이 움직임
    if (rightPressed && user1.x < canvas.width - user1.width) { 
		user1.x += 8;
	} 
    else if (leftPressed && user1.x > 0) { user1.x -= 8; }
    
    if (upPressed && user1.y > 0){ user1.y -= 8;} 
    else if (downPressed && user1.y < canvas.height - user1.height) {
		 //상대편 네트보다 더 아래로 갈 수 없도록
	  	if(user1.y < canvas.height/2 - 20){ user1.y += 8;}
	}
    
    // 공 속도
    ball.x += ball.velocityX;
	ball.y += ball.velocityY;
    
    // 상대 유저(=컴퓨터) 자동
	let computerLevel = 0.015;
	user2.x += (ball.y - (user2.x + user2.width/2)) * computerLevel; // 추후 라켓에 따른 레벨 변경
	
	// 패들이 user1 또는 user2 쳤는지 확인합니다.
	player = (ball.y < canvas.height/2) ? user1 : user2;
	
	if(spacePressed){
		 // 공이 패들에 부딪힌 경우
		if(collision(ball, player)){
			if(player == user1){
					user1.result = 1;
					user2.result = 0;
					user1.smash++;	
				}else{
					user1.result = 0; //지면 0
					user2.result = 1; //이기면 1
					user2.smash++;	
				}
				
			
			// 공이 패들에 닿는 위치를 확인합니다.
			let collidePoint = ball.y - (player.y + player.height/2);
			
			// collidePoint의 값을 정규화합니다. -1과 1 사이의 숫자를 가져와야 합니다.
       		// -player.height/2 < 충돌 지점 < player.height/2
			collidePoint = collidePoint/(player.height/2);
			
			// 공이 패들의 상단에 닿을 때 공이 -18도 각도를 가지기를 원합니다.
    	    // 공이 패들의 중앙에 닿을 때 공이 0도 각도를 가지기를 원합니다.
   		    // 공이 패들 바닥에 닿을 때 공이 18도 기울기를 원합니다.
       		// Math.PI/10 = 18도
			let angleRad = collidePoint * Math.PI/7;
			
			// X 및 Y 속도 방향 변경
			let direction = (ball.x < canvas.width/2)? 1 : -1;
			
			ball.velocityX = direction * ball.speed * Math.cos(angleRad);
			ball.velocityY = ball.speed * Math.sin(angleRad);
			
		 // 패들이 공을 칠 때마다 공의 속도를 높입니다.
			ball.speed += 0.5;
		}
	}
	
	// 플레이어의 점수 변경, 공이 왼쪽 "ball.y<0"으로 이동하면 컴퓨터 승리, 그렇지 않으면 "ball.y > canvas.width"인 경우 사용자 승리
	if(ball.y -ball.radius < 0){
		user2.score += 15;
		if(user2.score >= 45){
			alert('user2 ' + round +  "라운드 승!");
			round++;
			user2.score = 0;
			user1.score = 0;
			user2.win += 1;
		}
		checkRound();
		resetBall();
		
	}else if(ball.y + ball.radius > canvas.height){
		user1.score += 15;
		if(user1.score >= 45){
			alert('user1 ' + round +  "라운드 승!");
			round++;
			user1.score = 0;
			user2.score = 0;
			user1.win++;
		}
		checkRound();
		resetBall();
	}
	
    if(user1.mno != 0 && user2.mno != 0){
		//user1.draw();	// 유저 1 그리기
   		 //user2.draw();	// 유저 2 그리기
	}
    
}

game();
