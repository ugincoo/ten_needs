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
	

}
