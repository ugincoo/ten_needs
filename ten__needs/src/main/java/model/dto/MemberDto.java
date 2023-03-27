package model.dto;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MemberDto {

	private int mno;
    private String mid;
    private String mpw;
    private String mimg;
    private String memail;
    private String mphone;
    
    // 이메일 전송 메소드
    public boolean sendEmail( String toemail , String contenthtml ) {
    	
    	String fromemail = "네이버아이디@naver.com";
    	String emailpw = "네이버계정비밀번호";
    	
    	// 네이버 기준
    	Properties properties = new Properties();
    	properties.put("mail.smtp.host", "smtp.naver.com");		// gmail일 경우 : smtp.gmail.com
		properties.put("mail.smtp.port", 587 );					// 동일
		properties.put("mail.smtp.auth", true );				// 동일
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		Session session = Session.getDefaultInstance(properties , new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication( fromemail , emailpw );
			}
		});
		
		// 메일 보내기
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromemail));
			message.addRecipient( Message.RecipientType.TO, new InternetAddress(toemail));
			
			message.setSubject("TenNeeds 회원가입 인증코드");
			message.setText(contenthtml);
			
			Transport.send(message);
			
			return true;
		}catch (Exception e) { System.out.println(e); }
		return false;
    }
    
    
    
    public MemberDto() {}

	public MemberDto(int mno, String mid, String mpw, String mimg, String memail, String mphone) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mimg = mimg;
		this.memail = memail;
		this.mphone = mphone;
	}

	@Override
	public String toString() {
		return "MemberDto [mno=" + mno + ", mid=" + mid + ", mpw=" + mpw + ", mimg=" + mimg + ", memail=" + memail
				+ ", mphone=" + mphone + "]";
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMimg() {
		return mimg;
	}

	public void setMimg(String mimg) {
		this.mimg = mimg;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
    
    
    
}