package mvc;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {
	
	// 학생 이름으로 조회
	public ArrayList<StudentVO> getStudentFromName(String name) {

		ArrayList<StudentVO> list = new ArrayList<StudentVO>();

		String dml_select = "SELECT S_NO \"등록번호\""
				+ ", S_NAME \"이름\""
				+ ", S_YEAR \"학년\""
				+ ", S_PHONE \"연락처\""
				+ ", LCT.L_CLASS \"수강과목\" "
				+ "FROM STUDENTS STD, LECTURE LCT "
				+ "WHERE STD.L_NO = LCT.L_NO "
				+ "	AND STD.S_NAME LIKE '%" + name + "%'";
		
		System.out.println("JOIN QUERY :: " + dml_select);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO sVo = null;

		try {
			con = DBUtil.getConnection(); // DB연결
			pstmt = con.prepareStatement(dml_select); // sql문 전송
			rs = pstmt.executeQuery(); // 결과값을 받아서 rs에 저장

			while (rs.next()) {
				sVo = new StudentVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				list.add(sVo);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
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

	// 전체 학생 조회
	public ArrayList<StudentVO> getStudentTotal() {

		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		String dml_select = "SELECT S_NO \"등록번호\""
				+ ", S_NAME \"이름\""
				+ ", S_YEAR \"학년\""
				+ ", S_PHONE \"연락처\""
				+ ", LCT.L_CLASS \"수강과목\" "
				+ "FROM STUDENTS STD, LECTURE LCT "
				+ "WHERE STD.L_NO = LCT.L_NO";
		System.out.println("JOIN QUERY : " + dml_select);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO sVo = null;

		try {
			con = DBUtil.getConnection(); // DB연결
			pstmt = con.prepareStatement(dml_select); // sql문 전송
			rs = pstmt.executeQuery(); // 결과값을 받아서 rs에 저장
			while (rs.next()) {
				sVo = new StudentVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				list.add(sVo);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
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
	
	// 칼럼 명 얻어오기 (속성명)
	public ArrayList<String> getColumnName() {

		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "SELECT S_NO \"등록번호\", S_NAME \"이름\", S_YEAR \"학년\", S_PHONE \"연락처\" FROM STUDENTS";
		System.out.println("MetaData From SQL : " + sql);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData(); // MetaData로 칼럼명을 가져온다

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
