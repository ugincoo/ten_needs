package model.dao;

import java.util.ArrayList;

import model.dto.RacketDto;

public class RacketDao extends Dao {
	
	private static RacketDao dao = new RacketDao();
	private RacketDao() {};
	public static RacketDao getInstance() { return dao; }
	
	// 1-1. 라켓 등록
	public boolean addRacket( RacketDto dto ) {
		
		String sql = "insert into racket ( rName , rImg , rLevle , rSize_x , rSize_y) values ( ? , ? , ? , ? , ? )";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getrName());	ps.setString(2, dto.getrImg());
			ps.setInt(3, dto.getrLevle());		ps.setInt(4, dto.getrSize_x());
			ps.setInt(5, dto.getrSize_y()); 	
			if( ps.executeUpdate() == 1 ) { return true; }
		} catch(Exception e) {	System.err.println(e.getMessage());} return false; 
	}
	
	// 1-2. 라켓명 중복체크
	public boolean racketCheck( String rName ) {
			// System.out.println(rName); //---확인완료
		String sql = "select * from racket where rName ='"+rName+"'";
		try {
			ps = con.prepareStatement(sql);		rs = ps.executeQuery();
			if( rs.next() ) { return true; }
		}catch (Exception e) {	System.err.println(e.getMessage()); }
		return false;
	}
	
	// 2. 라켓 삭제
	public boolean onDelete( String rName ) {
		String sql = "delete from racket where rName ="+rName;
		try {
			ps = con.prepareStatement(sql);
			if( ps.executeUpdate() == 1 ) { return true; }
		} catch(Exception e) {	System.err.println(e.getMessage());} return false; 
	}
	
	// 3-1. 라켓수 구하기
	public int getTotalSize(String key, String keyword) {
		
		String sql;
		
		if(key.equals("") && keyword.equals("")) {
			sql = "select count(*) from racket";
		}else {
			sql = "select count(*) from racket where "+key+" like " + "\"%"+ keyword + "%\"";
		}
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) { return rs.getInt(1);}
		} catch (Exception e) {	System.err.println(e.getMessage()); }
		return 0;
	}
	
	// 3-2. 라켓 출력
	public ArrayList<RacketDto> racketList(int startRow, String key, String keyword){
		
		String sql;
		ArrayList<RacketDto> list = new ArrayList<>();
		if(key.equals("") && keyword.equals("")) {
			sql = "select * from racket order by rNo limit ?, 10";
		} else {
			sql = "select * from racket where "+key+" like \"%"+keyword+"%\" order by rNo limit ?, 10";
		}
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, startRow);		rs = ps.executeQuery();
			
			while( rs.next() ) {
				RacketDto dto = new RacketDto(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getInt(5), rs.getInt(6));
				list.add(dto);
			}
			return list;
		} catch(Exception e) {	System.err.println(e.getMessage()); } return null;
	}
	
	// 3-3. 라켓 개별 출력
	public RacketDto getRacket( int rNo ) {
		String sql = "select * from racket where rno ="+rNo;
		try {
			ps = con.prepareStatement(sql);		rs = ps.executeQuery();
			if( rs.next() ) {
				RacketDto dto = new RacketDto(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getInt(5), rs.getInt(6));
				return dto;
			}
			
		} catch(Exception e) {	System.err.println(e.getMessage()); } return null;
	}
	
	// 3-4. 라켓 수정
	public boolean updateRacket( RacketDto dto ) {
		String sql = "update racket set rName = ? , rImg = ? , rLevle = ? , rSize_x = ? , rSize_y = ? where rNo =";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getrName());	ps.setString(2, dto.getrImg());
			ps.setInt(3, dto.getrLevle());		ps.setInt(4, dto.getrSize_x());
			ps.setInt(5, dto.getrSize_y()); 	
			if( ps.executeUpdate() == 1 ) { return true; }
		} catch(Exception e) {	System.err.println(e.getMessage()); } return false;
	}
}
