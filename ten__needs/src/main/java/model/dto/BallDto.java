package model.dto;

import javax.websocket.Session;

public class BallDto {
		
	    private int x;
	    private int y;
	    private int radius;
	    private int speed;
	    private int velocityX;
	    private int velocityY;
	    private String color;
	    
	    // 추가 필드
	    private int ballState; // 0: start, 1: user1 touch, 2: user2 touch, 3: reset
	    private String msg; // 메시지 전송
	    
	    // Empty 생성자
		public BallDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		// 볼 정보 갱신용 생성자
	    public BallDto( Session session, String msg ) {
	    	this.msg = msg;
		}
		
		// 처음 볼 생성 및 리셋용 생성자
		public BallDto(int x, int y, int radius, int speed, int velocityX, int velocityY, String color, int ballState) {
			super();
			this.x = x;
			this.y = y;
			this.radius = radius;
			this.speed = speed;
			this.velocityX = velocityX;
			this.velocityY = velocityY;
			this.color = color;
			this.ballState = ballState;
		}
		
		// FULL 생성자
		public BallDto(int x, int y, int radius, int speed, int velocityX, int velocityY, String color, int ballState,
				String msg) {
			super();
			this.x = x;
			this.y = y;
			this.radius = radius;
			this.speed = speed;
			this.velocityX = velocityX;
			this.velocityY = velocityY;
			this.color = color;
			this.ballState = ballState;
			this.msg = msg;
		}

		@Override
		public String toString() {
			return "BallDto [x=" + x + ", y=" + y + ", radius=" + radius + ", speed=" + speed + ", velocityX="
					+ velocityX + ", velocityY=" + velocityY + ", color=" + color + ", ballState=" + ballState
					+ ", msg=" + msg + "]";
		}

		public int getX() {
	        return x;
	    }

	    public void setX(int x) {
	        this.x = x;
	    }

	    public int getY() {
	        return y;
	    }

	    public void setY(int y) {
	        this.y = y;
	    }

	    public int getRadius() {
	        return radius;
	    }

	    public void setRadius(int radius) {
	        this.radius = radius;
	    }

	    public int getSpeed() {
	        return speed;
	    }

	    public void setSpeed(int speed) {
	        this.speed = speed;
	    }

	    public int getVelocityX() {
	        return velocityX;
	    }

	    public void setVelocityX(int velocityX) {
	        this.velocityX = velocityX;
	    }

	    public int getVelocityY() {
	        return velocityY;
	    }

	    public void setVelocityY(int velocityY) {
	        this.velocityY = velocityY;
	    }

	    public String getColor() {
	        return color;
	    }

	    public void setColor(String color) {
	        this.color = color;
	    }

		public int getBallState() {
			return ballState;
		}

		public void setBallState(int ballState) {
			this.ballState = ballState;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	    
}

