package model.dao;

<<<<<<< HEAD
import java.util.ArrayList;

import model.dto.RacketDto;

public class GameDao extends Dao {
=======
import model.dto.GameResultDto;

public class GameDao extends Dao{
	/*싱글톤*/
	private static GameDao gameDao = new GameDao();
	private GameDao(){};
	public static GameDao getInstance() {return gameDao;}
>>>>>>> branch 'Ga0Kwon' of https://github.com/Tea-ho/ten__needs
	
<<<<<<< HEAD
	private static GameDao dao = new GameDao();
	private GameDao() {};
	public static GameDao getInstans() { return dao; }
	
	// 라켓 정보 가져오기
	public ArrayList<RacketDto> getRacket( int rNo ){
		ArrayList<RacketDto> racket = new ArrayList<>();
		String sql = "select * from racket where rNo = "+rNo;
=======
	//게임 승리시 게임 종료 -> 데이터 넣기(승리자)
	public boolean endGame(GameResultDto dto) {
		String sql = "update into gamestatus set gsResult = true, gsAccute = ? where mNo = " + dto.getWinnerMno();
		
>>>>>>> branch 'Ga0Kwon' of https://github.com/Tea-ho/ten__needs
		try {
			ps = con.prepareStatement(sql);
<<<<<<< HEAD
			rs = ps.executeQuery();
			if( rs.next() ) {
				racket.add(new RacketDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
=======
			
			ps.setDouble(1, dto.getWinnerAccute());
			
			int count =  ps.executeUpdate();
			
			if(count == 1) {
				//게임 승리자의 gResult의 값을 1로 바꿔준다.
				sql = "update into gamestatus set gsAccute = ? where mNo = " + dto.getLoserMno();
				
				ps.setDouble(1, dto.getLoserAccute());
				
				ps.executeUpdate();
				
				return true;
>>>>>>> branch 'Ga0Kwon' of https://github.com/Tea-ho/ten__needs
			}
		}catch (Exception e) { System.out.println(e); }
		return racket;
	}
	
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
