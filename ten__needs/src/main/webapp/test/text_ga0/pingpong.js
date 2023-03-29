const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');
	
	// 상수 선언
	const user1 = {
		x : canvas.width/2 - 100/2,
		y : 0,
		width : 100,
		height : 10,
		color : "white",
		score : 0
	}
	
	const user2 = {
		x : canvas.width/2 - 100/2,
		y : canvas.height - 10,
		width : 100,
		height : 10,
		color : "#DBC889",
		score : 0
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
		color : "white",
	}
	
	// -------------------------------------------------------- 방향키 전역 변수
	let rightPressed = false;	// 우키 상태
	let leftPressed = false;	// 좌키 상태
	let upPressed = false;		// 상키 상태
	let downPressed = false;	// 하키 상태
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
	      	user1.x += 7;
	    } else if (leftPressed && user1.x > 0) {
	      	user1.x -= 7;
	    }
	    
	    if ( upPressed && user1.y > 0 ){
		  	user1.y -= 7;
		} else if ( downPressed && user1.y < canvas.height - user1.height ) {
		  	user1.y += 7;
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
	
	// 업데이트 함수, 모든 계산을 수행하는 함수
	function update(){
		 // 공에는 속도가 있습니다.
		ball.x += ball.velocityX;
		ball.y += ball.velocityY;
		
		// 상대 유저(=컴퓨터) 자동
		let computerLevel = 0.015;
		user2.x += (ball.y - (user2.x + user2.width/2)) * computerLevel;
		
		// 공이 아래쪽 및 위쪽 벽과 충돌하면 y 속도를 반전시킵니다.
		if(ball.x + ball.radius > canvas.width || ball.x - ball.radius < 0){
			ball.velocityX = -ball.velocityX;
		}
		
		  // 패들이 사용자 또는 com 패들을 쳤는지 확인합니다.
		let player = (ball.y < canvas.height/2) ? user1 : user2;
		
		 // 공이 패들에 부딪힌 경우
		if(collision(ball, player)){
			
			// 공이 패들에 닿는 위치를 확인합니다.
			let collidePoint = ball.y - (player.y + player.height/2);
			
			// collidePoint의 값을 정규화합니다. -1과 1 사이의 숫자를 가져와야 합니다.
       		// -player.height/2 < 충돌 지점 < player.height/2
			collidePoint = collidePoint/(player.height/2);
			
			// 공이 패들의 상단에 닿을 때 공이 -18도 각도를 가지기를 원합니다.
    	    // 공이 패들의 중앙에 닿을 때 공이 0도 각도를 가지기를 원합니다.
   		    // 공이 패들 바닥에 닿을 때 공이 18도 기울기를 원합니다.
       		// Math.PI/10 = 18도
			let angleRad = collidePoint * Math.PI/10;
			
			// X 및 Y 속도 방향 변경
			let direction = (ball.x < canvas.width/2)? 1 : -1;
			
			ball.velocityX = direction * ball.speed * Math.cos(angleRad);
			ball.velocityY = ball.speed * Math.sin(angleRad);
			
		 // 패들이 공을 칠 때마다 공의 속도를 높입니다.
			ball.speed += 0.5;
		}
		
		
    // 플레이어의 점수 변경, 공이 왼쪽 "ball.y<0"으로 이동하면 컴퓨터 승리, 그렇지 않으면 "ball.y > canvas.width"인 경우 사용자 승리
		if(ball.y -ball.radius < 0){
			user2.score++;
			resetBall();
		}else if(ball.y + ball.radius > canvas.height){
			user1.score++;
			resetBall();
		}
	}
	
	
	
	function game(){
	    update();
	    render();
	}
	// number of frames per second
	let framePerSecond = 50;
	
	//call the game function 50 times every 1 Sec
	let loop = setInterval(game,1000/framePerSecond);
	








