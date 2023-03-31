package model.dao;

import model.dto.GameResultDto;

public class GameDao extends Dao{
	/*싱글톤*/
	private static GameDao gameDao = new GameDao();
	private GameDao(){};
	public static GameDao getInstance() {return gameDao;}
	
	//게임 승리시 게임 종료 -> 데이터 넣기(승리자)
	public boolean endGame(GameResultDto dto) {
		String sql = "update into gamestatus set gsResult = true, gsAccute = ? where mNo = " + dto.getWinnerMno();
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setDouble(1, dto.getWinnerAccute());
			
			int count =  ps.executeUpdate();
			
			if(count == 1) {
				//게임 승리자의 gResult의 값을 1로 바꿔준다.
				sql = "update into gamestatus set gsAccute = ? where mNo = " + dto.getLoserMno();
				
				ps.setDouble(1, dto.getLoserAccute());
				
				ps.executeUpdate();
				
				return true;
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}
