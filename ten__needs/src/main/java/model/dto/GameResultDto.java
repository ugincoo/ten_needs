package model.dto;

public class GameResultDto {
	private int winnerMno;
	private int loserMno;
	private double winnerAccute;
	private double loserAccute;
	
		
	public GameResultDto() {
		super();
	}

	public GameResultDto(int winnerMno, int loserMno, double winnerAccute, double loserAccute) {
		super();
		this.winnerMno = winnerMno;
		this.loserMno = loserMno;
		this.winnerAccute = winnerAccute;
		this.loserAccute = loserAccute;
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

	@Override
	public String toString() {
		return "GameResultDto [winnerMno=" + winnerMno + ", loserMno=" + loserMno + ", winnerAccute=" + winnerAccute
				+ ", loserAccute=" + loserAccute + "]";
	}
	
	
}
