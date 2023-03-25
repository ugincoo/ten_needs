package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.dto.BoardDto;

public class BoardDao extends Dao{
	/*싱글톤*/
	private static BoardDao boardDao = new BoardDao();
	private BoardDao(){};
	public static BoardDao getInstance() {return boardDao;}
	
	
	//1. 게시물 수 구하기
	public int getTotalSize(String key, String keyword) {
		String sql;
		
		if(key.equals("") && keyword.equals("")) {
			 sql = "select count(*) from board";
		}else {
			 sql = "select count(*) from board where "+key+" like " + "\"%"+ keyword + "%\"";
		}
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return 0;
	}
	
	//2. 글 출력
	public ArrayList<BoardDto> getBoardList(int startRow, String key, String keyword){
		String sql;
		ArrayList<BoardDto> boardList = new ArrayList<>();
		
		if(key.equals("") && keyword.equals("")) {
			sql = "select b.* from board b order by bDate desc limit ?, 10";
		}else {
			sql = "select b.* from board b where "+key+" like \"%"+keyword+"%\" order by bDate desc limit ?, 10";
		}
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, startRow);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDto dto = new BoardDto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4));
				
				sql = "select count(*) from reply where bno = " + rs.getInt(1);
				
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();
				
				if(rs2.next()) {
					dto.setRcount(rs2.getInt(1));
				}
				
				boardList.add(dto);	
			}
			
			return boardList;
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
}