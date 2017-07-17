package mvc;

import java.sql.*;
import java.util.ArrayList;

public class LectureDAO {

	public ArrayList<LectureVO> getLectureRoom() {
		ArrayList<LectureVO> list = new ArrayList<LectureVO>();
		
		String query_join = "SELECT L_CLASS \"강의\""
				+ ", L_CLASSROOM \"강의실\""
				+ ", L_TIME \"수업시간\""
				+ ", L_TEACHER \"강사\""
				+ " FROM LECTURE" 
				+ " ORDER BY L_TIME ASC";
		
		System.out.println("SELECT QUERY :: " + query_join);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LectureVO lVo = null;

		try {
			con = DBUtil.getConnection(); // DB 연결
			pstmt = con.prepareStatement(query_join); // sql문 전송
			rs = pstmt.executeQuery(); // 결과값을 받아서 rs에 저장
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
		String query_meta = "SELECT L_CLASS \"강의\""
				+ ", L_CLASSROOM \"강의실\""
				+ ", L_TIME \"수업시간\""
				+ ", L_TEACHER \"강사\""
				+ ", STD.S_NAME \"학생\""
				+ " FROM LECTURE, STUDENTS STD" 
				+ " WHERE LECTURE.S_NO = STD.S_NO";
		System.out.println("MetaData From Query :: " + query_meta);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null; // MetaData로 칼럼명을 가져온다

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
		} finally { // 연결 종료
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
