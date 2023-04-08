package model.dao;

import java.util.ArrayList;
import java.util.Vector;

import model.dto.GameResultDto;
import model.dto.GameUserDto;
import model.dto.RacketDto;

public class GameDao extends Dao {
	
	private static GameDao dao = new GameDao();
	private GameDao() {};
	public static GameDao getInstans() { return dao; }
	
	// 라켓 정보 가져오기
	public RacketDto getRacket( int rNo ){
		
		String sql = "select * from racket where rNo = "+rNo;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() ) {
				return new RacketDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
			}
		}catch (Exception e) { System.out.println(e); }
		return null;
	}
	
	//게임 종료시 데이터 넣기
	public boolean endGame(GameResultDto dto) {
		//승리자부터
		String sql = "insert into gamestatus(gsAccute, gsResult, mNo, gNo, rNo) vlaues (?, true, ?, ?, ?) ";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setDouble(1, dto.getWinnerAccute());
			ps.setInt(2, dto.getWinnerMno());
			ps.setInt(3, dto.getGno());
			ps.setInt(4, dto.getWinnerRno());
			
			
			if(ps.executeUpdate() == 1) {
				//게임 승리자의 gResult의 값을 1로 바꿔준다.
				sql = "insert into gamestatus(gsAccute, mNo, gNo, rNo) vlaues (?, ?, ?, ?) ";
				
				ps.setDouble(1, dto.getLoserAccute());
				ps.setInt(2, dto.getLoserMno());
				ps.setInt(3, dto.getGno());
				ps.setInt(4, dto.getLoserRno());
				
				ps.executeUpdate();
				
				return true;
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	//라켓의 정보를 가지고 온다[모든 라켓을 가지고온다.]
	public Vector<RacketDto> getRacketList(){
		Vector<RacketDto> racketList = new Vector<>();
		
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
	
	//정확도 승리수 출력
	public ArrayList<GameResultDto> getRanking(){
		ArrayList<GameResultDto> memberRankingList = new ArrayList<>();
		
		String sql = "select m.mid, sum(gs.gsResult) as '승리횟수', avg(gs.gsAccute) as '정확도 평균' ,m.mImg from gamestatus gs natural join member m group by m.mno order by '정확도 평균' desc";
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				memberRankingList.add(new GameResultDto(rs.getDouble(3), rs.getString(4), rs.getString(1), rs.getInt(2)));
				
			}
			return memberRankingList;
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
}
