package mvc;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

// DefaultTable�� ��ӹ޾� Students Table�� �����ϴ� Ŭ����
public class StudentTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	Object[][] data;
	Object[] columnName;
	StudentDAO sDao = new StudentDAO();
	StudentVO sVo;
	ArrayList<String> title;
	ArrayList<StudentVO> list;

	// ������
	public StudentTableModel() {
		// ���� ������ ���� ������ �˾ƾ� 2���� �迭 ����
		// ���̺��� �÷��̸��� ���ͼ� 1���� �迭 ����
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
