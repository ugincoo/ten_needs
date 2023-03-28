package model.testgamesockat;

import javax.websocket.Session;

public class TestDto {
	Session session;
	String mId;
	
	public TestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestDto(Session session, String mId) {
		super();
		this.session = session;
		this.mId = mId;
	}

	@Override
	public String toString() {
		return "TestDto [session=" + session + ", mId=" + mId + "]";
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
