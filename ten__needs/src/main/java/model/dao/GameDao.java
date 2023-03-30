package model.dao;

public class GameDao extends Dao{
	/*싱글톤*/
	private static GameDao gameDao = new GameDao();
	private GameDao(){};
	public static GameDao getInstance() {return gameDao;}
	
	//게임 승리시 게임 종료 -> 데이터 넣기(승리자)
	public boolean endGame(int winner) {
		String sql = "update into game set winner = ";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, winner);
			
			int count =  ps.executeUpdate();
			
			if(count == 1) {
				//게임 승리자의 gResult의 값을 1로 바꿔준다.
				sql = "update into gamestatus set gResult = 1 where mNo = " + winner;
				
				ps.executeUpdate();
				
				return true;
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}
