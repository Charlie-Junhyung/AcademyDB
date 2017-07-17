package mvc;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

// DefaultTable을 상속받아 Lecture Table을 구성하는 클래스
public class LectureTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	// 필드
	Object[][] data;
	Object[] columnName;
	LectureDAO lDao = new LectureDAO();
	LectureVO lVo;
	ArrayList<String> title;
	ArrayList<LectureVO> list;

	// 생성자
	public LectureTableModel() {
		// 열의 개수와 행의 개수를 알아야 2차원 배열 선언
		// 테이블에서 컬럼 이름을 얻어와서 1차원 배열 선언
		title = lDao.getColumnName();
		columnName = title.toArray();
		int columnCount = title.size();
		
		list = lDao.getLectureRoom();
		int rowCount = list.size();
		
		data = new Object[rowCount][columnCount];
		
		for (int idx = 0; idx < rowCount; idx++) {
			lVo = list.get(idx);
			data[idx][0] = lVo.getL_class();
			data[idx][1] = lVo.getL_classroom();
			data[idx][2] = lVo.getL_time();
			data[idx][3] = lVo.getL_teacher();
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
