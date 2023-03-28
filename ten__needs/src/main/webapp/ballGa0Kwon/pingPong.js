console.log("pingPong js 실행!")

/* 경기장 canvas를 가져온다 */
const canvas = document.getElementById('pong');

const context = canvas.getContext("2d"); //2d로 설정

//1. 사각형 그리기!
function drawRect(x, y, w, h, color){
	
	context.fillStyle = color; //색상 채우기

	/* context.fillRect(x좌표, y좌표, 가로 길이, 세로 길이) */
	context.fillRect(x, y, w, h) //paddle[사각형]
}

//2. 원 그리기!
function drawCircle(x, y, r, color){
	
	context.fillStyle = color; //색상 채우기
	//곡선 혹은 선을 그릴때는 beginPath()로 선그리기 시작을 선언하는 것부터 시작
	context.beginPath();
	/* context.arc(x좌표, y좌표, 반지름, start angle, end angle) //원(공) */
	// start angle이 0이고 end angle이 360여야 원이 그려진다.
	// direction : false => 원이 시계방향으로 그려짐
	// 완전한 원을 만들기 위해서는 start angle : 0 / end angle : 2π
	context.arc(x, y, r, 0, Math.PI*2, false) //원(공)
	
	context.closePath(); //그리기 위해 경로를 닫고
	context.fill(); //그린다.
}

let rectX = 0;

function render(){
	drawRect(0, 0, 500, 800, "white"); //캔버스 지우기[기존 사각형 지우기]
	drawRect(rectX, 100, 100, 100,"red");
	rectX += 50; //rectX (사각형의 x좌표를 50씩 이동)
}

/*setInterval(render, 1000); //1초마다 render()함수 실행*/

/* 만들려는 게임이 2인용 게임이므로 player객체 2개 만든다*/
const player1 = {
	x : canvas.width/2 - 100/2, 
	y : canvas.height - 10,
	width : 100,
	heigth : 10,
	color : "orange",
	score : 0
}

const player2 = {
	x : canvas.width/2 - 100/2,
	y : 0, //패들이 10임으로 높이에서 10만큼 빼준다.
	width :100,
	heigth : 10,
	color : "green",
	score : 0
}

//player 객체로 그리기
drawRect(player1.x, player1.y, player1.width, player1.heigth, player1.color);
drawRect(player2.x, player2.y, player2.width, player2.heigth, player2.color);

//네트(테니스 그물)
const net = {
	x : 0,
	y : canvas.height/2 - 2/2, //높이 도트의 크기가 2여서 그런가?? 각각 1씩 마이너스
	width : 10, // 네트 도트의 넓이 값
	height : 2, // 네트 도트의 높이 값
	color : "black"
}
drawNet();

// 그물 네트 그리기
function drawNet(){
	for(let i = 0; i <= canvas.width; i+= 15){ //15픽셀씩 증가?
		drawRect(net.x + i, net.y, net.width, net.height, net.color);
	}
}

//공 그리기
const ball = {
	x : canvas.width/2,
	y : canvas.height/2,
	radius : 10,
	color : "yellow"
}

drawCircle(ball.x, ball.y, ball.radius, ball.color);

//점수 판
document.querySelector('.player1Score').innerHTML = player1.score
document.querySelector('.player2Score').innerHTML = player2.score

//14.32초까지 봄
