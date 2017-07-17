package mvc;

import java.sql.*;
import java.util.ArrayList;

public class LectureDAO {

	public ArrayList<LectureVO> getLectureRoom() {
		ArrayList<LectureVO> list = new ArrayList<LectureVO>();
		
		String query_join = "SELECT L_CLASS \"����\""
				+ ", L_CLASSROOM \"���ǽ�\""
				+ ", L_TIME \"�����ð�\""
				+ ", L_TEACHER \"����\""
				+ " FROM LECTURE" 
				+ " ORDER BY L_TIME ASC";
		
		System.out.println("SELECT QUERY :: " + query_join);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LectureVO lVo = null;

		try {
			con = DBUtil.getConnection(); // DB ����
			pstmt = con.prepareStatement(query_join); // sql�� ����
			rs = pstmt.executeQuery(); // ������� �޾Ƽ� rs�� ����
			while (rs.next()) {
				lVo = new LectureVO(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4));
				list.add(lVo);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return list;
	}

	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		String query_meta = "SELECT L_CLASS \"����\""
				+ ", L_CLASSROOM \"���ǽ�\""
				+ ", L_TIME \"�����ð�\""
				+ ", L_TEACHER \"����\""
				+ ", STD.S_NAME \"�л�\""
				+ " FROM LECTURE, STUDENTS STD" 
				+ " WHERE LECTURE.S_NO = STD.S_NO";
		System.out.println("MetaData From Query :: " + query_meta);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null; // MetaData�� Į������ �����´�

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(query_meta);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();

			int columns = rsmd.getColumnCount();
			for (int i = 1; i <= columns; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // ���� ����
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return columnName;
	}

}
