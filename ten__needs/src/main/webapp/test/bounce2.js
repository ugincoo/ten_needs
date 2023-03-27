window.onload = function () { // --- canvas onload
	
	// ------------- Canvas 컨텍스트 가져오기 ------------- 
	const canvas = document.getElementById('canvas');
	const ctx = canvas.getContext('2d');
	// 기능: JavaScript에서 Canvas 컨텍스트를 가져와서 애니메이션으로 만들어줌
	
	// ------------- 공 객체 만들기(정의) 필요 인자: 위치, 크기, 속도, 방향 ------------- 
	class Ball {
		constructor(x, y, radius, speed, angle) {
		    this.x = x; 			// x 좌표
		    this.y = y; 			// y 좌표
		    this.radius = radius; 	// 공 반지름
		    this.speed = speed;		// 공 속도
		    this.angle = angle;		// 공 방향
		    this.vx = Math.cos(angle) * speed; // x축 방향 속도 (cos사용)
		    this.vy = Math.sin(angle) * speed; // y축 방향 속도 (sing사용) 
	  	}
		
	    update() {
		    this.x += this.vx;		// x 좌표 변경 (x축 방향의 속도만큼 이동)
		    this.y += this.vy;		// y 좌표 변경 (y축 방향의 속도만큼 이동)
		    this.vy += gravity; 	// 중력 적용
		    this.angle = Math.atan2(this.vy, this.vx);
		    this.speed = Math.sqrt(this.vx ** 2 + this.vy ** 2); // ** 거듭제곱
		    this.vx = Math.cos(this.angle) * this.speed;
		    this.vy = Math.sin(this.angle) * this.speed;
		  }
		  
		  // Math 클래스 신규 메소드
		  // 1. Math.atan2(y, x)
		  // 반환: 주어진 y축 방향과 x축 방향의 좌표를 이용하여, 해당 좌표의 극각을 반환
		  // 사용이유: x와 y의 부호를 고려하여 적절한 사분면의 각도를 반환하기 때문에, x와 y 값이 모두 양수인 경우와 양수와 음수가 섞인 경우 모두 처리 가능
		  // 2. Math.sqrt()
		  // 반환: 인수로 전달된 숫자의 제곱근을 반환
		  // 사용 이유: x푹 방향 속도와 y축 방향 속도로 공 초기 속도 계산하기 위함 공 이동속도 = (x속도 제곱 + y속도 제곱)의 제곱
	
	    draw() {
		    ctx.beginPath();
		    ctx.arc(this.x, this.y, this.radius, 0, 2 * Math.PI);
		    ctx.fill();
		  }
	  
	  // 지면에 닿았는지 확인하는 메소드
	  isOnGround() { return this.y + this.radius >= canvas.height; }
	 
	  /* 아래랑 같은 코드
	  isOnGround(){
			if( this.y + this.radius >= canvas.height){return true;
			} else{ return false; }
	  }
	  */
	  
	  // 바운스 메소드
	  bounce() { 
	    this.vy *= -bounceFactor; 	// 바닥에 닿으면 y방향 전환 + 감속 처리
	    this.vx *= friction;		// 바닥에 닿으면 x속도 감속 처리
	  }
	}
	
	// 객체 생성
	const ball = new Ball(100, 100, 20, 5, Math.PI / 4);
	const gravity = 0.1; 		// 중력 임의 설정
	const bounceFactor = 0.7;	// 감속 임의 설정 (바닥에 닿았을 때 튀기는 정도 감속)
	const friction = 0.99;		// 마찰 임의 설정 (바닥에 닿았을 때 마찰 적용)
	
	// ------------- 애니메이션 루프 적용 -------------
	// 사용 방법: requestAnimationFrame을 사용하여 애니메이션 루프 생성
	// 용도: 1)공 업데이트, 2)공이 땅에 닿으면 튕기는 효과 적용
	animate();
	function animate() {
		ctx.clearRect(0, 0, canvas.width, canvas.height);
	 	// clearReact 메소드
	 	// 인자: canvas x좌표, canvas y좌표, canvas 가로길이, canvas 세로길이
	 	// 반환: true, false
	 	// 동작: 캔버스 직사각형 계속 지움 => 지우고 다시 그리고 지우고 다시 그리고 반복
	 	ball.draw();
	  	ball.update();
		// 최종: 지우고 볼 다시 그리고 지우고 볼 다시 그리고 반복~~~~~~~~~~~~~~~
		
	  	if (ball.isOnGround()) { ball.bounce(); }
		
		requestAnimationFrame(animate);
	}
}