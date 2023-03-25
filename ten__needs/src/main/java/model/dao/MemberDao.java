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
}