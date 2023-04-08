package model.dto;

public class GameResultDto {
	private int gsNo;
	private int winnerMno;
	private int loserMno;
	private double winnerAccute;
	private double loserAccute;
	private int gno;
	private int winnerRno;
	private int loserRno;
	
	//추가 필드 
	private String mimg;
	private String mid;
	private int count;
		
	public GameResultDto() {
		super();
	}
	
	//추가 Dto를 만들기 보단 의미는 안맞지만 필드재사용예정
	public GameResultDto(double winnerAccute, String mimg, String mid, int count) {
		super();
		this.winnerAccute = winnerAccute;
		this.mimg = mimg;
		this.mid = mid;
		this.count = count;
	}
	


	public GameResultDto(int winnerMno, int loserMno, double winnerAccute, double loserAccute, int gno, int winnerRno,
			int loserRno) {
		super();
		this.winnerMno = winnerMno;
		this.loserMno = loserMno;
		this.winnerAccute = winnerAccute;
		this.loserAccute = loserAccute;
		this.gno = gno;
		this.winnerRno = winnerRno;
		this.loserRno = loserRno;
	}


	public GameResultDto(int gsNo, int winnerMno, int loserMno, double winnerAccute, double loserAccute, int gno,
			int winnerRno, int loserRno, String mimg, String mid, int count) {
		super();
		this.gsNo = gsNo;
		this.winnerMno = winnerMno;
		this.loserMno = loserMno;
		this.winnerAccute = winnerAccute;
		this.loserAccute = loserAccute;
		this.gno = gno;
		this.winnerRno = winnerRno;
		this.loserRno = loserRno;
		this.mimg = mimg;
		this.mid = mid;
		this.count = count;
	}


	public int getGsNo() {
		return gsNo;
	}


	public void setGsNo(int gsNo) {
		this.gsNo = gsNo;
	}


	public int getWinnerMno() {
		return winnerMno;
	}


	public void setWinnerMno(int winnerMno) {
		this.winnerMno = winnerMno;
	}


	public int getLoserMno() {
		return loserMno;
	}


	public void setLoserMno(int loserMno) {
		this.loserMno = loserMno;
	}


	public double getWinnerAccute() {
		return winnerAccute;
	}


	public void setWinnerAccute(double winnerAccute) {
		this.winnerAccute = winnerAccute;
	}


	public double getLoserAccute() {
		return loserAccute;
	}


	public void setLoserAccute(double loserAccute) {
		this.loserAccute = loserAccute;
	}


	public int getGno() {
		return gno;
	}


	public void setGno(int gno) {
		this.gno = gno;
	}


	public int getWinnerRno() {
		return winnerRno;
	}


	public void setWinnerRno(int winnerRno) {
		this.winnerRno = winnerRno;
	}


	public int getLoserRno() {
		return loserRno;
	}


	public void setLoserRno(int loserRno) {
		this.loserRno = loserRno;
	}


	public String getMimg() {
		return mimg;
	}


	public void setMimg(String mimg) {
		this.mimg = mimg;
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public String toString() {
		return "GameResultDto [gsNo=" + gsNo + ", winnerMno=" + winnerMno + ", loserMno=" + loserMno + ", winnerAccute="
				+ winnerAccute + ", loserAccute=" + loserAccute + ", gno=" + gno + ", winnerRno=" + winnerRno
				+ ", loserRno=" + loserRno + ", mimg=" + mimg + ", mid=" + mid + ", count=" + count + "]";
	}




	
	
	
}
