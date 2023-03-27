package model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardDto {
	private int bno; //게시물 번호
	private String btitle; // 게시물 제목
    private String bcontent; //게시물 내용
    private String bwritedate; //게시물 작성 날짜/시간
    
    //추가 필드 
    private int rcount; //댓글 개수
    
    //1. 생성자 [빈생성자] : 사용할 용도가 적음
   	public BoardDto() {
   		super();
   	}

	
  	//등록용 생성자
	public BoardDto(String btitle, String bcontent) {
		super();
		this.btitle = btitle;
		this.bcontent = bcontent;
	}

	//수정용 생성자
	
	public BoardDto(int bno, String btitle, String bcontent) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
	}
   	
	//출력용 생성자
	public BoardDto(int bno, String btitle, String bcontent, String bwritedate) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		
		Date date = new Date();
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String now = dformat.format(date);
		
		if(now.split(" ")[0].equals(bwritedate.split((" "))[0])){
			//2. 만약에 오늘 날짜와 작성일과 동일하면 시간 출력 아니면 날짜출력
			this.bwritedate = bwritedate.split(" ")[1]; //시간 출력
		}else {
			this.bwritedate = bwritedate.split(" ")[0]; //시간 출력
		}		
		
	}



	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBwritedate() {
		return bwritedate;
	}

	public void setBwritedate(String bwritedate) {
		this.bwritedate = bwritedate;
	}

	public int getRcount() {
		return rcount;
	}

	public void setRcount(int rcount) {
		this.rcount = rcount;
	}

	@Override
	public String toString() {
		return "BoardDto [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwritedate=" + bwritedate
				+ ", rcount=" + rcount + "]";
	}
	
	
}