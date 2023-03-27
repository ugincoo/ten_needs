package model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.Session;

import controller.chatting.Chatting;
import model.dao.MemberDao;

public class ChatMessageDto {

	private String mId;
	private String mImg;
	private String msg;
	private String date;
	private String time;
	
	// Empty 생성자
	public ChatMessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 클라이언트에게 메시지 전송할 때 사용하는 생성자
	public ChatMessageDto( Session session, String msg ) {
		
		this.msg = msg;
		
		for( ChatUserDto dto : Chatting.connectList ) {
			// ChatUserDto type의 인스턴스를 Chatting.connectList 인덱스 전체 회전
			
			if( dto.getSession() == session ) {
				// 현재 dto와 들어온 session이 일치하다면
				this.mId = dto.getmId(); // --- 아이디 입력
				this.mImg = MemberDao.getInstance().getMember(this.mId).getMimg(); // --- 이미지 이름 입력
				this.date = new SimpleDateFormat("yyyy-MM-dd").format( new Date() );
				this.time = new SimpleDateFormat("aa hh:mm").format( new Date() );
			}
		}
	}
	
	// Full 생성자
	public ChatMessageDto(String mId, String mImg, String msg, String date, String time) {
		super();
		this.mId = mId;
		this.mImg = mImg;
		this.msg = msg;
		this.date = date;
		this.time = time;
	}

	@Override
	public String toString() {
		return "ChatMessageDto [mId=" + mId + ", mImg=" + mImg + ", msg=" + msg + ", date=" + date + ", time=" + time
				+ "]";
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmImg() {
		return mImg;
	}

	public void setmImg(String mImg) {
		this.mImg = mImg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
