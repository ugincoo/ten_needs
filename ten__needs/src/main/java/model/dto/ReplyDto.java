package model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyDto {
	private int reNo;
    private String reContent;
    private String reDate;
    private int bNo;
    private int mNo;

    //추가 필드
    private String mId;

    
    
	public ReplyDto() {
		super();
	}
	
	
	public ReplyDto(int reNo, String reContent, String reDate, int bNo, int mNo, String mId) {
		super();
		this.reNo = reNo;
		this.reContent = reContent;

		this.bNo = bNo;
		this.mNo = mNo;
		this.mId = mId;
		
		Date date = new Date();
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String now = dformat.format(date);
		
		if(now.split(" ")[0].equals(reDate.split((" "))[0])){
			//2. 만약에 오늘 날짜와 작성일과 동일하면 시간 출력 아니면 날짜출력
			this.reDate = reDate.split(" ")[1]; //시간 출력
		}else {
			this.reDate = reDate.split(" ")[0]; //시간 출력
		}	
		
		
	}
	
	
	
	public ReplyDto(int reNo, String reContent, String reDate, int bNo, int mNo) {
		super();
		this.reNo = reNo;
		this.reContent = reContent;
		this.reDate = reDate;
		this.bNo = bNo;
		this.mNo = mNo;
	}
	
	//등록용
	public ReplyDto(String reContent, int bNo, int mNo) {
		super();
		this.reContent = reContent;
		this.bNo = bNo;
		this.mNo = mNo;
	}

	
	//수정용
	public ReplyDto(String reContent, int reNo) {
		super();
		this.reContent = reContent;
		this.reNo = reNo;
	}

	public int getReNo() {
		return reNo;
	}





	public void setReNo(int reNo) {
		this.reNo = reNo;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public String getReDate() {
		return reDate;
	}

	public void setReDate(String reDate) {
		this.reDate = reDate;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
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

	@Override
	public String toString() {
		return "ReplyDto [reNo=" + reNo + ", reContent=" + reContent + ", reDate=" + reDate + ", bNo=" + bNo + ", mNo="
				+ mNo + ", mId=" + mId + "]";
	}
	
	
    
    
}
