package model.dto;

import javax.websocket.Session;

public class GameUserDto {
	
	private Session session; //소켓
	private int type; //1. 접속 2. 캐릭터 움직임 3. 공치
	private int gno;
	private int mno; //회원번호
	private int x; //x좌표
	private int y; //y좌표
	private int rno; //라켓 번호
	// 추가 유저 선택
	private int user; // 1.User1 2.User2
	
	
	public GameUserDto() {
		super();
	}

	
	
	public GameUserDto(Session session, int type, int gno, int mno, int rno) {
		super();
		this.session = session;
		this.type = type;
		this.gno = gno;
		this.mno = mno;
		this.rno = rno;
	}
	


	public GameUserDto(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}



	public GameUserDto(Session session, int type, int gno, int mno, int x, int y, int rno) {
		super();
		this.session = session;
		this.type = type;
		this.gno = gno;
		this.mno = mno;
		this.x = x;
		this.y = y;
		this.rno = rno;
	}


	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getGno() {
		return gno;
	}


	public void setGno(int gno) {
		this.gno = gno;
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

	public int getRno() {
		return rno;
	}


	public void setRno(int rno) {
		this.rno = rno;
	}


	public int getUser() {
		return user;
	}



	public void setUser(int user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "GameUserDto [session=" + session + ", type=" + type + ", gno=" + gno + ", mno=" + mno + ", x=" + x
				+ ", y=" + y + ", rno=" + rno + "]";
	}
	
	
	
}