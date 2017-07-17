package mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	// DB ���ῡ �ʿ��� ����
	static final String driver = "oracle.jdbc.driver.OracleDriver";
	static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	static final String id = "madang";
	static final String pw = "madang";
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver); // Driver ����
			con = DriverManager.getConnection(url, id, pw); // DB ���� ����
		} catch(ClassNotFoundException cnfe){
			System.out.println("����̹� ���� ���� : ����̹��� ��ȿ���� Ȯ���ϼ���"); 
		} catch (SQLException se) {
			System.out.println("���� ���� : url �Ǵ� id �Ǵ� password�� �߸��Ǿ����ϴ�");
		} 
		
		return con; // DB ������ ��ȯ
		
	}

}
