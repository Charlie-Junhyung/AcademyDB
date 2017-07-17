package mvc;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

// DefaultTable을 상속받아 Students Table을 구성하는 클래스
public class StudentTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	Object[][] data;
	Object[] columnName;
	StudentDAO sDao = new StudentDAO();
	StudentVO sVo;
	ArrayList<String> title;
	ArrayList<StudentVO> list;

	// 생성자
	public StudentTableModel() {
		// 열의 개수와 행의 개수를 알아야 2차원 배열 선언
		// 테이블에서 컬럼이름을 얻어와서 1차원 배열 선언
		title = sDao.getColumnName();
		columnName = title.toArray();
		int columnCount = title.size();

		list = sDao.getStudentTotal();
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];

		for (int idx = 0; idx < rowCount; idx++) {
			sVo = list.get(idx);
			data[idx][0] = sVo.getS_no();
			data[idx][1] = sVo.getS_name();
			data[idx][2] = sVo.getS_year();
			data[idx][3] = sVo.getS_phone();
		}

	}
	
	// JTable의 데이터에 접근하기 위한 재정의 메소드
	@Override
	public int getColumnCount() {
		if (columnName == null)
			return 0;
		else
			return columnName.length;
	}

	@Override
	public String getColumnName(int column) {
		return (String) columnName[column];
	}

	@Override
	public int getRowCount() {
		if (data == null)
			return 0;
		else
			return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
}
