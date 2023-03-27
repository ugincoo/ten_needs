

const canvas = document.getElementById('canvas');
  const ctx = canvas.getContext('2d');
	
	// 상수 선언
	const user1 = {
		x : 0,
		y : canvas.height/2 - 100/2,
		width : 10,
		height : 100,
		color : "white",
		score : 0
	}
	
	const user2 = {
		x : canvas.width - 10,
		y : canvas.height/2 - 100/2,
		width : 10,
		height : 100,
		color : "white",
		score : 0
	}
	
	const ball = {
		x : canvas.width/2,
		y : canvas.height/2,
		radius : 10,
		speed : 5,
		velocityX : 5,
		velocityY : 5,
		color : "white"
	}
	
	const net = {
		x : canvas.width/2,
		y : 0,
		width : 2,
		height : 10,
		color : "white",
	}
	
	// 함수 정의
	function drawRect(x, y, w, h, color){
		ctx.fillStyle = color;
		ctx.fillRect(x, y, w, h);
	}
	
	function drawNet(){
		for(let i = 0; i<= canvas.height; i+=15){
			drawRect(net.x, net.y+i, net.width, net.height, net.color);
		}
	}
	
	function drawCircle(x, y, r, color){
		ctx.fillStyle = color;
		ctx.beginPath();
		ctx.arc(x, y, r, 0, Math.PI*2, false);
		ctx.closePath();
		ctx.fill();
	}
	
	function drawText(text, x, y, color){
		ctx.fillStyle = color;
		ctx.font = "45px fantasy";
		ctx.fillText(text, x, y);
	}
	
	
	function render(){
		drawRect( 0, 0, canvas.width , canvas.height, "black");
		
		drawNet();
		
		drawText(user1.score, canvas.width/4, canvas.height/5, "white");
		drawText(user2.score, 3*canvas.width/4, canvas.height/5, "white");
		
		drawRect(user1.x, user1.y, user1.width, user1.height, user1.color);
		drawRect(user2.x, user2.y, user2.width, user2.height, user2.color);
		
		drawCircle(ball.x, ball.y, ball.radius, ball.color);
	}
	
	// 사용자 마우스 이벤트
	canvas.addEventListener("mousemove", movePaddle);
	
	function movePaddle(evt){
		let rect = canvas.getBoundingClientRect();
		
		user1.y = evt.clientY - rect.top - user1.height/2;
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
	
	function resetBall(){
		ball.x = canvas.width/2;
		ball.y = canvas.height/2;
		
		ball.speed = 5;
		ball.velocityX = -ball.velocityX;
	}
	
	function update(){
		ball.x += ball.velocityX;
		ball.y += ball.velocityY;
		
		// 상대 유저(=컴퓨터) 자동
		let computerLevel = 0.1;
		user2.y += (ball.y - (user2.y + user2.height/2)) * computerLevel;
		
		if(ball.y + ball.radius > canvas.height || ball.y - ball.radius < 0){
			ball.velocityY = -ball.velocityY;
		}
		
		let player = (ball.x < canvas.width/2) ? user1 : user2;
		
		if(collision(ball, player)){
			let collidePoint = ball.y - (player.y + player.height/2);
			
			collidePoint = collidePoint/(player.height/2);
			
			let angleRad = collidePoint * Math.PI/4;
			
			let direction = (ball.x < canvas.width/2)? 1 : -1;
			
			ball.velocityX = direction * ball.speed * Math.cos(angleRad);
			ball.velocityY = ball.speed * Math.sin(angleRad);
			
			ball.speed += 0.5;
		}
		
		if(ball.x -ball.radius < 0){
			user2.score++;
			resetBall();
		}else if(ball.x + ball.radius > canvas.width){
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
	



	game();








