package model.dto;

public class GameAlarmDto {
	
	private int player1Score; 	// player1score 관리 (공통으로 사용 불가)
	private int player2Score; 	// palyer2score 관리 (공통으로 사용 불가)
	private int round;			// round 관리 (공통으로 사용 가능)
	private String msg; 		// 시작 / 종료 알림 (공통으로 사용 가능)
	
	public GameAlarmDto(int player1Score, int player2Score, int round, String msg) {
		super();
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.round = round;
		this.msg = msg;
	}

	public GameAlarmDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GameAlarmDto [player1Score=" + player1Score + ", player2Score=" + player2Score + ", round=" + round
				+ ", msg=" + msg + "]";
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}

	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
