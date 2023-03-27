window.onload = function () { // --- canvas onload

	const canvas = document.getElementById("canvas");
	const ctx = canvas.getContext("2d");
	
	// -------------------------------------------------------- 볼 전역 변수
	const ballRadius = 10;		// 공 지름 설정
	let x = canvas.width / 2;	// x 좌표
	let y = canvas.height - 30;	// y 좌표
	let dx = 2;					// x 속도 고정값 
	let dy = -2;				// y 속도 고정값 (밑으로 내려가야되니까 음수 처리)
	// -------------------------------------------------------- 패들 전역 변수
	const paddleHeight = 10;	// 패들 높이 설정
	const paddleWidth = 75;		// 패들 넓이 설정
	let paddleX = (canvas.width - paddleWidth) / 2;	// 패들 x 좌표
	let paddleY = canvas.height - paddleHeight;		// 패들 y 좌표
	// -------------------------------------------------------- 방향키 전역 변수
	let rightPressed = false;	// 우키 상태
	let leftPressed = false;	// 좌키 상태
	let upPressed = false;		// 상키 상태
	let downPressed = false;	// 하키 상태
	// 선언 이유: 아래 방향키 작동 메소드로 만들어 사용하고자 함
	
	// ------------------------------ 공 그리기(설정) ------------------------------
	function drawBall() {
	    ctx.beginPath(); // --- 메소드 경로 열기(기본 폼)
	    ctx.arc(x, y, ballRadius, 0, Math.PI * 2);
	    // arc메소드 인수: x좌표, y좌표, 반지름, 각도1, 각도2
	    ctx.fillStyle = "#0095DD"; // --- 색상 설정
	    ctx.fill();
	    ctx.closePath(); // --- 메소드 경로 닫기(기본 폼)
	}
	
	// ------------------------------ 패들 그리기(설정) ------------------------------
	function drawPaddle() {
		ctx.beginPath();
	    ctx.rect(paddleX, paddleY, paddleWidth, paddleHeight);
	    // rect메소드 인수: x좌표, y좌표, 가로길이, 세로길이
	    ctx.fillStyle = "#0095DD";
	    ctx.fill();
	    ctx.closePath();
	}
	
	// ------------------------------ 캔버스에 그리기(캔버스, 공, 패들 캔버스에 직접 적용) ------------------------------
	function draw() {
    	ctx.clearRect(0, 0, canvas.width, canvas.height);
    	drawBall();
    	drawPaddle();
		
		
		// ------------------------------ 경계면 처리 ------------------------------
    	if (x + dx > canvas.width - ballRadius || x + dx < ballRadius) { // 공이 캔버스 오른쪽 or 왼쪽 끝에 닿으면 공 이동방향 변경 
      		dx = -dx;
    	}
    	
	    if (y + dy < ballRadius) { // 공 캔버스 위쪽 끝에 닿을 때 이동방향 변경
	      dy = -dy;
	    } else if (y + dy > canvas.height - ballRadius) { // 공 캔버스 아래 끝에 닿을 때 이동방향 변경
	      	if (x > paddleX && x < paddleX + paddleWidth) { // 패들에 맞을 때만 변경
	        	dy = -dy;
	      	} else { // 안맞으면 게임 종료
	        	alert("GAME OVER");
	       		setTimeout(function() {
	        		document.location.reload();
	      		}, 1000); // 1초 후에 페이지 새로고침
	      		// 피드백: setTimeout 없으면 무한루프 걸림
	      	}
    	}
		
		// ------------------------------ 방향키에 따른 공 이동 ------------------------------
	    if (rightPressed && paddleX < canvas.width - paddleWidth) {
	      	paddleX += 7;
	    } else if (leftPressed && paddleX > 0) {
	      	paddleX -= 7;
	    }
	    
	    if ( upPressed && paddleY > 0 ){
		  	paddleY -= 7;
		} else if ( downPressed && paddleY < canvas.height - paddleHeight ) {
		  	paddleY += 7;
		}
	    x += dx;
	    y += dy;
  }

	// ------------------------------ 패들 방향키 조작 ------------------------------
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
	setInterval(draw, 10);

}