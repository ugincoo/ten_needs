package model.dto;

public class GameroomDto {
	
	// DB 필드
	private int gNo;
	private String gTitle;
	private String gDate;
	private int mNo;
	
	private String mId;
	
	// Empty
	public GameroomDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 등록용
	public GameroomDto(String gTitle, int mNo) {
		super();
		this.gTitle = gTitle;
		this.mNo = mNo;
	}

	// FULL (출력용)
	public GameroomDto(int gNo, String gTitle, String gDate, int mNo) {
		super();
		this.gNo = gNo;
		this.gTitle = gTitle;
		this.gDate = gDate;
		this.mNo = mNo;
	}

	@Override
	public String toString() {
		return "GameroomDto [gNo=" + gNo + ", gTitle=" + gTitle + ", gDate=" + gDate + ", mNo=" + mNo + "]";
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
	
	
	
}
