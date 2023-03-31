package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	
	public Dao() {
		try {
			// * [웹 서버] 해당 MYSQL 드라이버[라이브러리] 찾기 => 안넣으면 실행이 X
			Class.forName("com.mysql.cj.jdbc.Driver"); //console 프로젝트 필요X
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tenneeds",
					"root", 
					"1234");
			
			System.out.println("연동 성공");
			
		}catch (Exception e) {
			System.err.println("연동 실패 : " + e.getMessage());
		}
	}
}