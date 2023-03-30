package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.dto.BoardDto;
import model.dto.ReplyDto;

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
			System.out.println("키워드 있음");
			sql = "select * from board where "+key+" like \"%"+keyword+"%\" order by bDate desc limit ?, 10";
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
	
	//3. 게시물 개별 출력
	public BoardDto getBoard(int bno) {
		String sql = "select * from board where bno = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
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
				
				return dto;
				
			}
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	//4. 게시물 등록
	public boolean writeBoard(BoardDto dto) {
		String sql = "insert into  board (bTitle, bContent) values ( ?, ?)";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getBtitle());
			ps.setString(2, dto.getBcontent());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return false;
	}
	
	//5. 게시물 삭제
	public boolean deleteBoard(int bno) {
		String sql  = "delete from board where bno = "+bno;
		
		try {
			ps = con.prepareStatement(sql);
			
			int count = ps.executeUpdate();
			
			if(count  == 1) {
				return true;
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	//6. 게시물 수정
	public boolean updateBoard(BoardDto dto) {
		String sql  = "update board set bContent = ? , bTitle = ? where bno = "+dto.getBno();
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getBcontent());
			ps.setString(2, dto.getBtitle());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	//7. 댓글 출력
	public ArrayList<ReplyDto> getReplyList(int bno){
		String sql = "select reply.*, member.mid, member.mimg from reply natural join member where bno ="+bno;
		ArrayList<ReplyDto> replyList = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReplyDto dto = new ReplyDto(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4), 
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7));
				
				replyList.add(dto);
			}
			
			return replyList;
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	//8. 댓글 작성
	public boolean writeReply(ReplyDto dto){
		String sql = "insert into reply (reContent, bNo, mNo) values (?, ?, ?)";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getReContent());
			ps.setInt(2, dto.getbNo());
			ps.setInt(3, dto.getmNo());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	//9. 댓글 삭제
	public boolean deleteReply(int reNo) {
		String sql = "delete from reply where reNo = "+reNo;
		
		try {
			ps = con.prepareStatement(sql);
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	//10. 댓글 수정
	public boolean updateReply(ReplyDto dto) {
		String sql = "update reply set reContent = ? where reNo = "+dto.getReNo();
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getReContent());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
}