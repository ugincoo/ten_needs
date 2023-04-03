package model.dto;

import javax.websocket.Session;

public class GameUserDto {
	
	// 공 데이터에 필요한 필드
	Session session;
	int mno;
	int gno;
	
	// 유저 움직임에 필요한 필드 추가 정의?
	
	public GameUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GameUserDto(Session session, int mno, int gno) {
		super();
		this.session = session;
		this.mno = mno;
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

	public int getGno() {
		return gno;
	}

	public void setGno(int gno) {
		this.gno = gno;
	}
}
