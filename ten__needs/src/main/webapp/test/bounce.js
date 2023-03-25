console.log( '작동확인' );

window.onload = function () { // 스크립트 로딩이 완료된 후부터 내용을 시작
 
  // ------------------------------------------------ canvas 기본 setting
  const canvas = document.getElementById('canvas');
  const ctx = canvas.getContext('2d');
  
  class Ball{
    constructor(x,y){ // ball의 기본 속성들을 정의
      this.x = x; // x 좌표
      this.y = y; // y 좌표
      this.c = 'rgba('+Math.random()*255+','+Math.random()*255+','+Math.random()*255+')'; // 색상(랜덤)
      this.size = 5; // 공의 반지름
      this.angle = (Math.random()*(Math.PI*2)); // 출발 각도
      this.power = 5; // 공의 세기
      this.directionX = this.power * Math.cos(this.angle); // 공이 좌우로 움직이는 값
      this.weight = this.power * Math.sin(this.angle); // 공이 상하로 움직이는 값
    }
    
    update(){ // 프레임마다 속성들을 변화시키는 함수
      this.y += this.weight; 		// y좌표 변경(증감)
      this.x += this.directionX; 	// x좌표 변경(증감)
     
      if(this.y+this.size>canvas.height || this.y-this.size<0){ // 상하 바운드 처리
        this.weight *= -1; // 상하에 닿으면 방향 전환 
      }
      if(this.x>canvas.width-this.size || this.x-this.size < 0) { // 좌우 바운드 처리
        this.directionX *= -1; // 좌우에 닿으면 방향 전환
      }
    }
    
    draw(){ // constructor에 설정한 속성값으로 원 생성
       ctx.fillStyle= this.c; // 색상 적용
       ctx.beginPath();
       ctx.arc(this.x, this.y, this.size, 0, Math.PI*2, true);
       ctx.closePath();
       ctx.fill();
    }
  }
 
  init = () => { // 그려질 공의 개체를 설정하는 함수
      ball = new Ball(canvas.width*0.5, canvas.height*0.5) // 가운데로 좌표 설정
  }
  function animate(){ // 매 프레임마다 그림을 새로 그려주는 함수
    ctx.fillStyle='rgba(255,255,255,0.5)'; // 매 프레임마다 캔버스를 통째로 칠하는 색깔. 맨 마지막의 alpha값에 따라 공의 잔상이 남는 정도가 달라진다.
    ctx.fillRect(0,0,canvas.width,canvas.height); // 캔버스 전체를 색칠해서 내용을 지워준다
    ball.update(); 	// ball 좌표 업데이트
    ball.draw(); 	// 업데이트된 좌표로 ball draw
    requestAnimationFrame(animate); // 인터벌 효과 메소드
  }
  init(); 	// 공의 초기 좌표를 설정하고,
  animate(); // 프레임마다 공을 그려준다.
 
  }
