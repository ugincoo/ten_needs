package model.dao;

import model.dto.MemberDto;

public class MemberDao extends Dao {

	private static MemberDao dao = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() { return dao; }
	
	// 회원가입
	public boolean signup( MemberDto dto ) {
		String sql = "insert into member ( mId , mPw , mImg , mEmail , mPhone) values ( ? , ? , ? , ? , ? )";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getMid() );
			ps.setString(2, dto.getMpw() );
			ps.setString(3, dto.getMimg() );
			ps.setString(4, dto.getMemail() );
			ps.setString(5, dto.getMphone() );
			
			ps.executeUpdate();
			return true;
		}catch (Exception e) { System.out.println(e); }
		return false;
	}
	
	// 아이디 중복 체크
	public boolean idcheck( String mid ) {
		String sql = "select * from member where mid = '"+mid+"'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() ) {
				return true;
			}
		}catch (Exception e) { System.out.println(e); }
		return false;
	}
	
	// 로그인
	public boolean login( String mid , String mpw ) {
		String sql = "select * from member where mId = ? and mPw = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, mpw);
			rs = ps.executeQuery();
			if( rs.next()) {
				return true;
			}
		}catch (Exception e) { System.out.println(e); }
		return false;
	}
	
	// 아이디 찾기
	public String findmid( String memail ) {
		String sql = "select mId from member where mEmail = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, memail);
			rs = ps.executeQuery();
			if( rs.next() ) {
				return rs.getString(1);
			}
		}catch (Exception e) { System.out.println(e); }
		return "false";
	}
	
	// 비밀번호 찾기
	public String findmpw( String mid , String memail , String updatempw ) {
		String sql = "select mNo from member where mId = ? and mEmail = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, memail);
			rs = ps.executeQuery();
			if( rs.next() ) {
				sql = "update member set mPw = ? where mNo = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, updatempw);
				ps.setInt(2, rs.getInt(1));
				int result = ps.executeUpdate();
				if( result == 1 ) {
					/* 실제 이메일 전송
					new MemberDto().sendEmail(memail, updatempw);
					return true;
					*/
					return updatempw;
				}
			}
		}catch (Exception e) { System.out.println(e); }
		return "false";
	}
	
	// 회원정보(아이디로)
	public MemberDto getMember( String mid ) {
		String sql = "select * from member where mId = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if( rs.next() ) {
				MemberDto dto = new MemberDto( rs.getInt(1), rs.getString(2), null, rs.getString(4), rs.getString(5), rs.getString(6));
				return dto;
			}
		}catch (Exception e) { System.out.println(e); }
		return null;
	}
	
	// 회원정보(멤버번호로)
		public MemberDto getMember( int mno ) {
			String sql = "select * from member where mno = ? ";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, mno);
				rs = ps.executeQuery();
				if( rs.next() ) {
					MemberDto dto = new MemberDto( rs.getInt(1), rs.getString(2), null, rs.getString(4), rs.getString(5), rs.getString(6));
					return dto;
				}
			}catch (Exception e) { System.out.println(e); }
			return null;
		}
	
	
	// 회원정보 수정
	public boolean update ( String mid , String mpw , String newmpw , String mphone , String memail , String mImg ) {
		String sql = "update member set mPw = ? , mEmail = ? , mPhone = ? , mImg = ? where mId = ? and mPw = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newmpw);
			ps.setString(2, memail);
			ps.setString(3, mphone);
			ps.setString(4, mImg);
			ps.setString(5, mid);
			ps.setString(6, mpw);
			int count = ps.executeUpdate();
			if( count == 1 ) {
				return true;
			}
		}catch (Exception e) { System.out.println(e); }
		return false;
	}
	
	//회원 id ---> 회원 mno로 반환
	public int getMno(String mid) {
		String sql = "select mno from member where mid = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();

			if(rs.next()) {return rs.getInt(1);}
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return -1;
	}
	
	
	
}