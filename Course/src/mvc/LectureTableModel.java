package mvc;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

// DefaultTable�� ��ӹ޾� Lecture Table�� �����ϴ� Ŭ����
public class LectureTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	// �ʵ�
	Object[][] data;
	Object[] columnName;
	LectureDAO lDao = new LectureDAO();
	LectureVO lVo;
	ArrayList<String> title;
	ArrayList<LectureVO> list;

	// ������
	public LectureTableModel() {
		// ���� ������ ���� ������ �˾ƾ� 2���� �迭 ����
		// ���̺��� �÷� �̸��� ���ͼ� 1���� �迭 ����
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
	
	// JTable�� �����Ϳ� �����ϱ� ���� ������ �޼ҵ�
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
