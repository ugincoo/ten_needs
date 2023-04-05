package model.dto;

import javax.websocket.Session;

public class GameUserDto {
	
	private Session session; //소켓
	private int gno;
	private int mno; //회원번호
	private int x; //x좌표
	private int y; //y좌표
	private int score ; //점수 
	private int width ;
	private int heigth;
	private boolean gameResult; //게임 결과(이기면 1 지면 2)
	private int smash; //스매시횟수 
	private int swing; //스윙 횟수
	private int rno; //라켓 번호
	
	
	
	public GameUserDto() {
		super();
	}
	
	

	public GameUserDto(Session session, int gno, int mno) {
		super();
		this.session = session;
		this.gno = gno;
		this.mno = mno;
	}

	


	public GameUserDto(int x, int y, int score, int width, int heigth, boolean gameResult, int smash, int swing,
			int rno) {
		super();
		this.x = x;
		this.y = y;
		this.score = score;
		this.width = width;
		this.heigth = heigth;
		this.gameResult = gameResult;
		this.smash = smash;
		this.swing = swing;
		this.rno = rno;
	}



	public GameUserDto(Session session, int gno, int mno, int x, int y, int score, int width, int heigth,
			boolean gameResult, int smash, int swing, int rno) {
		super();
		this.session = session;
		this.gno = gno;
		this.mno = mno;
		this.x = x;
		this.y = y;
		this.score = score;
		this.width = width;
		this.heigth = heigth;
		this.gameResult = gameResult;
		this.smash = smash;
		this.swing = swing;
		this.rno = rno;
	}



	public int getGno() {
		return gno;
	}



	public void setGno(int gno) {
		this.gno = gno;
	}



	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}


	public int getMno() {
		return mno;
	}


	public void setMno(int mno) {
		this.mno = mno;
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


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public boolean isGameResult() {
		return gameResult;
	}


	public void setGameResult(boolean gameResult) {
		this.gameResult = gameResult;
	}


	public int getSmash() {
		return smash;
	}


	public void setSmash(int smash) {
		this.smash = smash;
	}


	public int getSwing() {
		return swing;
	}


	public void setSwing(int swing) {
		this.swing = swing;
	}


	public int getRno() {
		return rno;
	}


	public void setRno(int rno) {
		this.rno = rno;
	}



	public int getWidth() {
		return width;
	}



	public void setWidth(int width) {
		this.width = width;
	}



	public int getHeigth() {
		return heigth;
	}



	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}



	@Override
	public String toString() {
		return "GameUserDto [session=" + session + ", gno=" + gno + ", mno=" + mno + ", x=" + x + ", y=" + y
				+ ", score=" + score + ", width=" + width + ", heigth=" + heigth + ", gameResult=" + gameResult
				+ ", smash=" + smash + ", swing=" + swing + ", rno=" + rno + "]";
	}



	
	
}
