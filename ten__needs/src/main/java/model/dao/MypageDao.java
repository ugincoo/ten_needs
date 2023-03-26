package model.dao;

import java.sql.ResultSet;

import model.dto.MypageGameDto;

public class MypageDao extends Dao {
	
	private static MypageDao dao = new MypageDao();
	private MypageDao() {}
	public static MypageDao getInstance() { return dao; }
	
	// --------------------------------------------------- MemberDao랑 합치기 작업 필요
	// 회원 탈퇴
	public boolean onDelete( String mId, String mPw ) {
		String sql = "delete from member where mId = ? and mPw = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mId );		ps.setString(2, mPw );
			
			if( ps.executeUpdate() == 1 ) { return true; } // --- 확인 필요
		} catch (Exception e) { System.out.println(e); }
		return false;
	}
	
	// 게임 정보 출력
	public MypageGameDto printGameInfo( int mNo ) {
		
		String sql = "select count(*) as gCount, sum(gsResult) as gWin, (sum(gsResult)/count(*))*100 as gWinRate from gamestatus where mNo ="+mNo;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() ) {
				MypageGameDto dto = new MypageGameDto();
				
				dto.setgCount(rs.getInt(1));
				dto.setgWin(rs.getInt(2));
				dto.setgWinRate(rs.getDouble(3));
				
				sql = "select r.rNo, r.rName, r.rImg, sum(g.gsResult) as total from gamestatus g join racket r on g.rNo = r.rNo where mNo = ? group by r.rNo order by total desc";
				ps.setInt(1, mNo);
				ResultSet rs2 = ps.executeQuery();
				
				while( rs2.next() ) { // --- test해야함
					if( rs2.first() ) {
						dto.setgBestRa(rs2.getString(2));
						dto.setrImg(rs2.getString(3));
					}
					if( rs2.last() ) {
						dto.setgWorstRa(rs2.getString(2));
					}
				}
				return dto;
			}
		} catch (Exception e) { System.out.println(e); }
		return null;
	}
}
