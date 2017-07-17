package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LecturePane extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	// ������Ʈ �ʵ�
	private JPanel plTable;

	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;

	// LecturePane ������
	public LecturePane() {
		this.setLayout(null);
		this.setBackground(new Color(81,152,0));

		plTable = new JPanel();
		plTable.setBorder(BorderFactory.createRaisedBevelBorder());
		plTable.setBounds(10, 5, 500, 500);
		plTable.setLayout(new BorderLayout());

		String data[][] = new String[0][5];
		String title[] = { "���Ǹ�", "���ǽ�", "���ǽð�", "����" };
		model = new DefaultTableModel(data, title) { // �� ��ü ����
			public boolean isCellEditable(int r, int c) { // �� ���� �Ұ����ϰ� ó��
				return false;
			}
		};

		table = new JTable(model);
		table.setBackground(Color.lightGray);
		table.setBorder(BorderFactory.createEtchedBorder());
		table.addMouseListener(this);

		scroll = new JScrollPane(table);
		scroll.getViewport().add(table, null);
		plTable.add(scroll, BorderLayout.CENTER);

		this.add(plTable);

		// JTable ������Ʈ �Ӽ� �� �÷� ����
		table.getColumnModel().getColumn(0).setPreferredWidth(50); // ���Ǹ�
		table.getColumnModel().getColumn(1).setPreferredWidth(70); // ���ǽ�
		table.getColumnModel().getColumn(2).setPreferredWidth(50); // ���ǽð�
		table.getColumnModel().getColumn(3).setPreferredWidth(50); // ����

		table.setShowHorizontalLines(false); // ���� ���� �Ⱥ��̰� ó��
		table.setShowVerticalLines(false); // ���� ���� �Ⱥ��̰� ó��
		table.setSelectionBackground(new Color(171,242,0)); // ������ �� ����
		table.setSelectionForeground(Color.black); // ������ �� �����
		table.setRowMargin(0);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setRequestFocusEnabled(false); // Ư�� ���� ��Ŀ�� ���� �ȵǰ� ó��

		table.setSelectionMode(0); // ���� �� ���� ���

		table.getTableHeader().setReorderingAllowed(false); // �������
		table.getTableHeader().setMaximumSize(new Dimension(140, 0)); // ��� �ʺ� ���� ������ �ִ밪
		table.getTableHeader().setMinimumSize(new Dimension(10, 0)); // ��� �ʺ� ���� ������ �ּҰ�
		table.getTableHeader().setResizingAllowed(false); // ��� ���� �Ұ���

		table.setAlignmentX(JTable.CENTER_ALIGNMENT); // ����
		table.setAlignmentY(JTable.CENTER_ALIGNMENT);
		
		createTable();

	}
	
	public void createTable() {
		try {
			model.setNumRows(0);
			ArrayList<LectureVO> showList = new ArrayList<LectureVO>();
			
			LectureDAO showDAO = new LectureDAO();
			
			showList = showDAO.getLectureRoom();
			Object[] showObj = new Object[4];
			
			for (int i = 0; i < showList.size(); i++) {
				LectureVO lvo = new LectureVO();
				lvo = showList.get(i);
				showObj[0] = lvo.getL_class();
				showObj[1] = lvo.getL_classroom();
				showObj[2] = lvo.getL_time();
				showObj[3] = lvo.getL_teacher();
				
				model.addRow(showObj);
			}
		} catch(Exception e){
			System.out.println(e);
			JOptionPane.showMessageDialog(this, "�並 ����µ� �����Ͽ����ϴ�", "���� �߻�", JOptionPane.ERROR_MESSAGE);
		}
	}

	// ���̺��� ���ڵ带 ������ �� �̺�Ʈ ó��
	@Override
	public void mousePressed(MouseEvent me) {
		int row = table.getSelectedRow(); // ���õ� �� ���
		if (row <0) {
			return;
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}
