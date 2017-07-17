package mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	// DB 연결에 필요한 정보
	static final String driver = "oracle.jdbc.driver.OracleDriver";
	static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	static final String id = "madang";
	static final String pw = "madang";
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver); // Driver 적재
			con = DriverManager.getConnection(url, id, pw); // DB 연결 생성
		} catch(ClassNotFoundException cnfe){
			System.out.println("드라이버 적재 실패 : 드라이버가 유효한지 확인하세요"); 
		} catch (SQLException se) {
			System.out.println("연결 실패 : url 또는 id 또는 password가 잘못되었습니다");
		} 
		
		return con; // DB 연결을 반환
		
	}

}
