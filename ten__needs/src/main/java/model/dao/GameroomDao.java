package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.dto.GameroomDto;

public class GameroomDao extends Dao {
	
	private static GameroomDao dao = new GameroomDao();
	private GameroomDao() {}
	public static GameroomDao getInstance() { return dao; }
	
	// 1. 게임방 생성
	public synchronized GameroomDto createGame( GameroomDto dto ) {
		String sql = "insert into gameroom( gTitle, mno ) values(?,?)";
		// --- 1. 방을 생성해라
		// --- 2. 가장 최근에 생성된 방 정보를 가져온다.
		// --- 3. 이거를 DTO넣고, 반환한다
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getgTitle()); 	ps.setInt(2, dto.getmNo());
			
			if( ps.executeUpdate() == 1 ) {
				sql = "select g.*, m.mid from gameroom g natural join member m where mno = "+ dto.getmNo() +" order by gdate desc limit 1";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if( rs.next() ) {
					return new GameroomDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getInt(4));
				}
			}
		} catch (Exception e) { System.out.println("예외발생:" + e); }
		return null;
	}
	
	// 2-1. 게시물 구하기
	public synchronized int getTotalSize(String key, String keyword) {
		String sql;
		
		if(key.equals("") && keyword.equals("")) {
			 sql = "select count(*) from gameroom";
		}else {
			 sql = "select count(*) from gameroom where "+key+" like " + "\"%"+ keyword + "%\"";
		}
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		}catch (Exception e) {	System.err.println(e.getMessage()); }
		return 0;
	}
	
	// 2-2. 게임방 출력
	public synchronized ArrayList<GameroomDto> gameList(int startRow, String key, String keyword){
		String sql;
		ArrayList<GameroomDto> gamelist = new ArrayList<>();
		
		if(key.equals("") && keyword.equals("")) {
			sql = "select * from gameroom order by gDate desc limit ?, 10"; //--- SQL 정상 작동
		}else {
			System.out.println("키워드 있음");
			sql = "select * from gameroom where "+key+" like \"%"+keyword+"%\" order by gDate desc limit ?, 10";
		}
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, startRow);		rs = ps.executeQuery();
			
			while( rs.next() ) { //--- 여기서 dto 한개만 출력됨
				GameroomDto dto = new GameroomDto(
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)
						);
					
				sql = "select mid from member where mno ="+rs.getInt(4);
				ps = con.prepareStatement(sql); ResultSet rs2 = ps.executeQuery();
				if( rs2.next() ) {
					dto.setmId(rs2.getString(1));
				}
					// System.out.println(dto.toString()); //--- 확인 완료
				gamelist.add(dto);
			}
			return gamelist;
		} catch(Exception e) { System.out.println("예외발생:" + e); }
		return null;
	}
	
	// 2-3. 게임방 개별 출력(입장)
	public synchronized GameroomDto getGame( int gno ) {
		String sql = "select * from gameroom where gno = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, gno);		rs = ps.executeQuery();
			
			if( rs.next() ) {
				GameroomDto dto = new GameroomDto(
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)
						);
				return dto;
			}
		} catch(Exception e) { System.err.println(e.getMessage()); }
		return null;
	}
	
	// 3. 게임방 삭제
	public synchronized  boolean onDelete( int gNo ) {
		String sql = "delete from gameroom where gNo ="+gNo;
		try {
			ps = con.prepareStatement(sql);	
			if( ps.executeUpdate() == 1 ) { return true; } // --- 확인 필요
		} catch (Exception e) { System.out.println(e); }
		return false;
	}
	
}
