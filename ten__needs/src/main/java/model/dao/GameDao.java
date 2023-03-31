package model.dao;

import java.util.ArrayList;

import model.dto.RacketDto;

public class GameDao extends Dao {
	
	private static GameDao dao = new GameDao();
	private GameDao() {};
	public static GameDao getInstans() { return dao; }
	
	// 라켓 정보 가져오기
	public ArrayList<RacketDto> getRacket( int rNo ){
		ArrayList<RacketDto> racket = new ArrayList<>();
		String sql = "select * from racket where rNo = "+rNo;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() ) {
				racket.add(new RacketDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
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
	
	//라켓의 정보를 가지고 온다[모든 라켓을 가지고온다.]
	public ArrayList<RacketDto> getRacketList(){
		ArrayList<RacketDto> racketList = new ArrayList<>();
		
		String sql = "select * from racket";
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				racketList.add(new RacketDto(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4), 
						rs.getInt(5), 
						rs.getInt(6)));
			}
			return racketList;
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
}
