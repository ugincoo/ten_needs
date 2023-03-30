package model.dto;

import javax.websocket.Session;

public class ChatUserDto {
	
	Session session;
	String mId;
	int gNo;
	
	public ChatUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatUserDto(Session session, String mId, int gNo) {
		super();
		this.session = session;
		this.mId = mId;
		this.gNo = gNo;
	}

	@Override
	public String toString() {
		return "ChatUserDto [session=" + session + ", mId=" + mId + ", gNo=" + gNo + "]";
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public int getgNo() {
		return gNo;
	}

	public void setgNo(int gNo) {
		this.gNo = gNo;
	}
	
	
	
}

