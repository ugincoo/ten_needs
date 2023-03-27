package model.dto;

import javax.websocket.Session;

public class ChatUserDto {
	
	Session session;
	String mId;
	
	public ChatUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatUserDto(Session session, String mId) {
		super();
		this.session = session;
		this.mId = mId;
	}

	@Override
	public String toString() {
		return "ChatDto [session=" + session + ", mId=" + mId + "]";
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
}
