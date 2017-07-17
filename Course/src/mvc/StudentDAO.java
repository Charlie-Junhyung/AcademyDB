package mvc;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {
	
	// �л� �̸����� ��ȸ
	public ArrayList<StudentVO> getStudentFromName(String name) {

		ArrayList<StudentVO> list = new ArrayList<StudentVO>();

		String dml_select = "SELECT S_NO \"��Ϲ�ȣ\""
				+ ", S_NAME \"�̸�\""
				+ ", S_YEAR \"�г�\""
				+ ", S_PHONE \"����ó\""
				+ ", LCT.L_CLASS \"��������\" "
				+ "FROM STUDENTS STD, LECTURE LCT "
				+ "WHERE STD.L_NO = LCT.L_NO "
				+ "	AND STD.S_NAME LIKE '%" + name + "%'";
		
		System.out.println("JOIN QUERY :: " + dml_select);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO sVo = null;

		try {
			con = DBUtil.getConnection(); // DB����
			pstmt = con.prepareStatement(dml_select); // sql�� ����
			rs = pstmt.executeQuery(); // ������� �޾Ƽ� rs�� ����

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

	// ��ü �л� ��ȸ
	public ArrayList<StudentVO> getStudentTotal() {

		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		String dml_select = "SELECT S_NO \"��Ϲ�ȣ\""
				+ ", S_NAME \"�̸�\""
				+ ", S_YEAR \"�г�\""
				+ ", S_PHONE \"����ó\""
				+ ", LCT.L_CLASS \"��������\" "
				+ "FROM STUDENTS STD, LECTURE LCT "
				+ "WHERE STD.L_NO = LCT.L_NO";
		System.out.println("JOIN QUERY : " + dml_select);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO sVo = null;

		try {
			con = DBUtil.getConnection(); // DB����
			pstmt = con.prepareStatement(dml_select); // sql�� ����
			rs = pstmt.executeQuery(); // ������� �޾Ƽ� rs�� ����
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
	
	// Į�� �� ������ (�Ӽ���)
	public ArrayList<String> getColumnName() {

		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "SELECT S_NO \"��Ϲ�ȣ\", S_NAME \"�̸�\", S_YEAR \"�г�\", S_PHONE \"����ó\" FROM STUDENTS";
		System.out.println("MetaData From SQL : " + sql);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData(); // MetaData�� Į������ �����´�

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
