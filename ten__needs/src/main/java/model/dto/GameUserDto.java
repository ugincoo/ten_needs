package model.dto;

import javax.websocket.Session;

public class GameUserDto {
	private Session session; //소켓
	
	private int mno; //회원번호
	private int x; //x좌표
	private int y; //y좌표
	private int score ; //점수 
	private boolean gameResult; //게임 결과(이기면 1 지면 2)
	private int smash; //스매시횟수 
	private int swing; //스윙 횟수
	private int rno; //라켓 번호
	
	
	
	public GameUserDto() {
		super();
	}


	public GameUserDto(Session session, int mno, int x, int y, int score, boolean gameResult, int smash, int swing, int rno) {
		super();
		this.session = session;
		this.mno = mno;
		this.x = x;
		this.y = y;
		this.score = score;
		this.gameResult = gameResult;
		this.smash = smash;
		this.swing = swing;
		this.rno = rno;
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


	@Override
	public String toString() {
		return "GameUserDto [mno=" + mno + ", x=" + x + ", y=" + y + ", score=" + score + ", gameResult=" + gameResult
				+ ", smash=" + smash + ", swing=" + swing + ", rno=" + rno + "]";
	}
	
	
	
}
