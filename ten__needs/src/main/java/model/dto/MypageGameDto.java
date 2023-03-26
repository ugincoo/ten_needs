package model.dto;

public class MypageGameDto {
	
	// DB SQL 데이터 필드화
	private int gCount;
	private int gWin;
	private double gWinRate;
	private String rImg;
	private String gBestRa;
	private String gWorstRa;
	
	// Empty 생성자
	public MypageGameDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Full 생성자
	public MypageGameDto(int gCount, int gWin, double gWinRate,  String rImg, String gBestRa, String gWorstRa) {
		super();
		this.gCount = gCount;
		this.gWin = gWin;
		this.gWinRate = gWinRate;
		this.rImg = rImg;
		this.gBestRa = gBestRa;
		this.gWorstRa = gWorstRa;
	}
	

	@Override
	public String toString() {
		return "MypageGameDto [gCount=" + gCount + ", gWin=" + gWin + ", gWinRate=" + gWinRate + ", rImg=" + rImg
				+ ", gBestRa=" + gBestRa + ", gWorstRa=" + gWorstRa + "]";
	}

	public int getgCount() {
		return gCount;
	}

	public void setgCount(int gCount) {
		this.gCount = gCount;
	}

	public int getgWin() {
		return gWin;
	}

	public void setgWin(int gWin) {
		this.gWin = gWin;
	}

	public double getgWinRate() {
		return gWinRate;
	}

	public void setgWinRate(double gWinRate) {
		this.gWinRate = gWinRate;
	}
	
	public String getrImg() {
		return rImg;
	}

	public void setrImg(String rImg) {
		this.rImg = rImg;
	}

	public String getgBestRa() {
		return gBestRa;
	}

	public void setgBestRa(String gBestRa) {
		this.gBestRa = gBestRa;
	}

	public String getgWorstRa() {
		return gWorstRa;
	}

	public void setgWorstRa(String gWorstRa) {
		this.gWorstRa = gWorstRa;
	}
	
}
