package model.dto;

import model.dao.GameroomDao;
import model.dao.MemberDao;

public class GameroomDto {
	
	// DB 필드
	private int gNo;
	private String gTitle;
	private String gDate;
	private String mId;
	
	private int mNo;
	private int limitCount;
	
	// Empty
	public GameroomDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 등록용
	public GameroomDto(String gTitle, String mId) {
		super();
		this.gTitle = gTitle;
		this.mId = mId;
		this.mNo = MemberDao.getInstance().getMno(mId);
	}

	// 출력용
	public GameroomDto(int gNo, String gTitle, String gDate, int mNo) {
		super();
		this.gNo = gNo;
		this.gTitle = gTitle;
		this.gDate = gDate;
		this.mNo = mNo;
	}
	
	public GameroomDto(int gNo, String gTitle, String gDate, String mId, int mNo) {
		super();
		this.gNo = gNo;
		this.gTitle = gTitle;
		this.gDate = gDate;
		this.mId = mId;
		this.mNo = mNo;
	}

	@Override
	public String toString() {
		return "GameroomDto [gNo=" + gNo + ", gTitle=" + gTitle + ", gDate=" + gDate + ", mNo=" + mNo + ", mId=" + mId
				+ "]";
	}
	
	public int getgNo() {
		return gNo;
	}
	
	public void setgNo(int gNo) {
		this.gNo = gNo;
	}

	public String getgTitle() {
		return gTitle;
	}

	public void setgTitle(String gTitle) {
		this.gTitle = gTitle;
	}

	public String getgDate() {
		return gDate;
	}

	public void setgDate(String gDate) {
		this.gDate = gDate;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public int getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}
	
}
