
// 소켓 연결
let gameSocket = null;

if( memberInfo == null ){}

else{
	gameSocket = new WebSocket('ws://localhost:8080/ten__needs/game/'+1+'/'+memberInfo.mno+'/'+2);

	gameSocket.onopen = (e)=>{ console.log('서버소켓 들어')}

	gameSocket.onclose = (e)=>{ console.log('서버소켓 나감')}

	gameSocket.onerror = (e)=>{ console.log('서버소켓 오류')}

	gameSocket.onmessage = (e)=>{ console.log()}
}	


let round = 1; //게임 라운드 수를 선정하는 변수(3라운드까)

const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

	
	// 상수 선언
	const user1 = {
		mno : 1,
		rano : 1,
		x : canvas.width/2 - 100/2,
		y : 0,
		width : 100,
		height : 10,
		color : "white",
		score : 0,
		win : 0,
		smash : 0,
		swing : 0,
	}
	
	const user2 = {
		mno : 2,
		x : canvas.width/2 - 100/2,
		y : canvas.height - 10,
		width : 100,
		height : 10,
		color : "#DBC889",
		score : 0,
		win : 0,
		smash : 0,
		swing : 0,
	}
	
	const ball = {
		x : canvas.width/2,
		y : canvas.height/2,
		radius : 10,
		speed : 5,
		velocityX : 5,
		velocityY : 5,
		color : "yellow"
	}
	
	const net = {
		x : 0,
		y : canvas.height/2 -2/2,
		width : 10,
		height : 2,
		color : "white"
	}
	

	
	
	// 방향키 전역 변수
	let rightPressed = false;	// 우키 상태
	let leftPressed = false;	// 좌키 상태
	let upPressed = false;		// 상키 상태
	let downPressed = false;	// 하키 상태
	let spacePressed = false; //스페이스여부
	// 선언 이유: 아래 방향키 작동 메소드로 만들어 사용하고자 함
	
	// 함수 정의
	
	// 네모(경기장) 그리기
	function drawRect(x, y, w, h, color){
		ctx.fillStyle = color;
		ctx.fillRect(x, y, w, h);
		
	}
	// 네트 그리기
	function drawNet(){
		for(let i = 0; i<= canvas.width; i+=15){
			drawRect(net.x+i, net.y, net.width, net.height, net.color);
		}
	}
	// 공 그리기
	function drawCircle(x, y, r, color){
		ctx.fillStyle = color;
		ctx.beginPath();
		ctx.arc(x, y, r, 0, Math.PI*2, false);
		ctx.closePath();
		ctx.fill();
	}
	// 글쓰기
	function drawText(text, x, y, color){
		ctx.fillStyle = color;
		ctx.font = "45px fantasy";
		ctx.fillText(text, x, y);
	}
	
	
	// 반복으로 움직이게 보이는 함수
	function render(){
		if (rightPressed && user1.x < canvas.width - user1.width) {
	      	user1.x += 8;
	    } else if (leftPressed && user1.x > 0) {
	      	user1.x -= 8;
	    }
	    
	    if ( upPressed && user1.y > 0 ){
		  	user1.y -= 8;
		} else if ( downPressed && user1.y < canvas.height - user1.height ) {
		  	if(user1.y < canvas.height/2 - 20){ //상대편 네트보다 더 아래로 갈 수 없도록
				user1.y += 8;
			}
			
		}
	    
	    
		drawRect( 0, 0, canvas.width , canvas.height, "#203624");
		
		drawNet();
		
		drawText(user1.score, 3*canvas.width/4, canvas.height/5, "white");
		drawText(user2.score, 3*canvas.width/4, 4.2*canvas.height/5, "white");
		
		drawRect(user1.x, user1.y, user1.width, user1.height, user1.color);
		drawRect(user2.x, user2.y, user2.width, user2.height, user2.color);
		
		drawCircle(ball.x, ball.y, ball.radius, ball.color);
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
		user1.color = "red";
	    spacePressed = true;
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
	    setTimeout(() => spacePressed = false, user1.color = "white", 3000); //정확함도를 낮추기 위해서
	  	if(player == user1){
				user1.swing++;
				console.log(user1.swing)
				
		}else{
				user2.swing++;	
		}
	  } 
  }

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
	
	let player = null;
	
	// 업데이트 함수, 모든 계산을 수행하는 함수
	function update(){
		 // 공에는 속도가 있습니다.
		ball.x += ball.velocityX;
		ball.y += ball.velocityY;
		
		// 상대 유저(=컴퓨터) 자동
		let computerLevel = 0.015;
		user2.x += (ball.y - (user2.x + user2.width/2)) * computerLevel;
		
		
		  // 패들이 사용자 또는 com 패들을 쳤는지 확인합니다.
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
				
				// 공이 패들의 상단에 닿을 때 공이 -50도 각도를 가지기를 원합니다.
	    	    // 공이 패들의 중앙에 닿을 때 공이 0도 각도를 가지기를 원합니다.
	   		    // 공이 패들 바닥에 닿을 때 공이 50도 기울기를 원합니다.
	       		// Math.PI/7 = 약 50도
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
	}
	
	//최종 라운드 체크
	function checkRound(){
		
		if(user1.win >= 2 || user2.win >= 2){
			let winner = "";
			let loser = "";
			
			let winnergsAccute = 0;
			let losergsAccute = 0;
			cancelAnimationFrame(game)
			if(user1.win > user2.win){
				winner = user1.mno;
				loser = user2.mno;
				
				winnergsAccute = user1.smash/user1.swing;
				losergsAccute = user2.smash/user2.swing;
				alert('user1이 최종 승리')
				
			}else{
				winner = user2.mno;
				loser = user1.mno;
				
				winnergsAccute = user2.smash/user2.swing;
				losergsAccute = user1.smash/user1.swing;
				alert('user2이 최종 승리')
			}
			
			let gameresult = {
				winner : winner,
				loser : loser,
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
	
	let count = 0;
	
	function game(){
		if(count%1 == 0){
			update();
	    	render();	
		}
	    count++;
	    
	    // 애니메이션 효과를 효율적이적으로 만드는 함수 = setInterval 같은 역할
	    requestAnimationFrame(game);
	}
	game();
	// number of frames per second
	//let framePerSecond = 50;
	
	//call the game function 50 times every 1 Sec
	//let loop = setInterval(game,1000/framePerSecond);
	








