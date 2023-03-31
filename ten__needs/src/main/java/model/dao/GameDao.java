package model.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		
}
