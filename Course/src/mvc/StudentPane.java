package mvc;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class StudentPane extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private JPanel plSearch;
	private JPanel plTable;

	private TitledBorder searchBorder;

	private JLabel lblName2;
	private JTextField tfName;
	private JButton btnSearch;
	private JButton btnRef;

	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JViewport scrollVport;

	public StudentPane() {
		this.setLayout(null);
		this.setBackground(new Color(81, 152, 0)); // panel ���� ����

		searchBorder = new TitledBorder("�˻�");

		plSearch = new JPanel();
		plSearch.setBackground(new Color(171, 242, 0)); // panel ���� ����
		plSearch.setBorder(searchBorder); // TitledBorder ����
		plSearch.setBounds(10, 5, 500, 50);
		plSearch.setLayout(null);

		lblName2 = new JLabel("�̸�");
		lblName2.setBounds(20, 12, 50, 30);
		plSearch.add(lblName2);

		tfName = new JTextField();
		tfName.setBounds(60, 12, 200, 30);
		plSearch.add(tfName);

		btnSearch = new JButton("�˻�");
		btnSearch.setBounds(270, 12, 60, 30);
		btnSearch.addActionListener(this);
		plSearch.add(btnSearch);

		btnRef = new JButton("��ü �л�");
		btnRef.setBounds(345, 12, 90, 30);
		btnRef.addActionListener(this);
		plSearch.add(btnRef);

		plTable = new JPanel();
		plTable.setBorder(BorderFactory.createRaisedBevelBorder()); // �г��� ��踦
																	// �簢����
		plTable.setBounds(10, 60, 500, 445);
		plTable.setLayout(new BorderLayout());

		String data[][] = new String[0][5];
		String title[] = { "��Ϲ�ȣ", "�̸�", "�г�", "����ó", "��������" };
		model = new DefaultTableModel(data, title) { // �� ��ü ����
			public boolean isCellEditable(int r, int c) { // �� �����Ұ����ϰ� ó��
				return false;
			}
		};

		table = new JTable(model); // defaultTableModel�� JTable ����
		table.setBorder(BorderFactory.createEtchedBorder());
		table.addMouseListener(this); // ���̺��� ���ڵ� ���� �̺�Ʈ ó���� ���� listener �߰�

		scroll = new JScrollPane(table);
		scrollVport = scroll.getViewport(); // JScrollPane�� ��ũ�ѵ� �� ��ü��
		scrollVport.add(table, null); // �߰��ϴµ� ���ؼ� JViewport�� ����Ѵ�
		plTable.add(scroll, BorderLayout.CENTER);

		this.add(plSearch);
		this.add(plTable);

		// JTable ������Ʈ �Ӽ� �� �÷� ����
		table.getColumnModel().getColumn(0).setPreferredWidth(30); // ��Ϲ�ȣ
		table.getColumnModel().getColumn(1).setPreferredWidth(40); // �̸�
		table.getColumnModel().getColumn(2).setPreferredWidth(40); // �г�
		table.getColumnModel().getColumn(3).setPreferredWidth(70); // �޴���
		table.getColumnModel().getColumn(4).setPreferredWidth(70); // �޴���

		table.setShowHorizontalLines(false); // ���� ���� �Ⱥ��̰� ó��
		table.setShowVerticalLines(false); // ���� ���� �Ⱥ��̰� ó��
		table.setSelectionBackground(new Color(171, 242, 0)); // ������ �� ����
		table.setSelectionForeground(Color.black); // ������ �� �����
		table.setRowMargin(0); // �� �� ���� ���� (�ȼ�����)
		table.setIntercellSpacing(new Dimension(0, 0)); // �� �� �����̽��� ���̿� ���� ����
		table.setRequestFocusEnabled(false); // Ư�� ���� ��Ŀ�� ���� �ȵǰ� ó��

		table.setSelectionMode(0); // ���� �� ���� ���

		table.getTableHeader().setReorderingAllowed(false); // �������
		table.getTableHeader().setMaximumSize(new Dimension(140, 0)); 
		// ��� �ʺ� ���� ������ �ִ밪
		table.getTableHeader().setMinimumSize(new Dimension(10, 0)); 
		// ��� �ʺ� ���� ������ �ּҰ�
		table.getTableHeader().setResizingAllowed(false); // ��� ���� �Ұ���

		table.setAlignmentX(JTable.CENTER_ALIGNMENT); // ����
		table.setAlignmentY(JTable.CENTER_ALIGNMENT);

	}

	// ��ư �̺�Ʈ ó�� �޼ҵ�
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object obj = evt.getSource(); // �̺�Ʈ ��ü ����

		// �˻� ��ư�� ������ ��
		if (obj == btnSearch) {
			System.out.println("�˻� ��ư Ŭ��!");
			
			try {
				model.setNumRows(0);
				ArrayList<StudentVO> showList = new ArrayList<StudentVO>();
				
				StudentDAO showDAO = new StudentDAO();
				
				showList = showDAO.getStudentFromName(tfName.getText().toString());
				Object[] showObj = new Object[5];
				
				for (int i = 0; i < showList.size(); i++) {
					StudentVO stvo = new StudentVO();
					stvo = showList.get(i);
					showObj[0] = stvo.getS_no();
					showObj[1] = stvo.getS_name();
					showObj[2] = stvo.getS_year();
					showObj[3] = stvo.getS_phone();
					showObj[4] = stvo.getL_class();
					
					model.addRow(showObj);
				}
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(this, "�˻��� �����Ͽ����ϴ�", "�˻� ����", JOptionPane.ERROR_MESSAGE);
			}
		}

		// ��ü �л� ��ư�� ������ ��
		else if (obj == btnRef) {
			System.out.println("��ü �л� ��ư Ŭ��!");

			try {
				model.setNumRows(0); // ���̺��� ������ û��
				ArrayList<StudentVO> showList = new ArrayList<StudentVO>();

				// StudentDAO�� ������ ArrayList�� �������� ����
				StudentDAO showDAO = new StudentDAO(); 

				showList = showDAO.getStudentTotal();
				Object[] showObj = new Object[5];

				for (int i = 0; i < showList.size(); i++) {
					StudentVO stvo = new StudentVO();
					stvo = showList.get(i);
					showObj[0] = stvo.getS_no();
					showObj[1] = stvo.getS_name();
					showObj[2] = stvo.getS_year();
					showObj[3] = stvo.getS_phone();
					showObj[4] = stvo.getL_class();

					model.addRow(showObj);
				}
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(this, "�˻��� �����Ͽ����ϴ�", "�˻� ����", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// JTable���� ���� ������ �� ȣ��Ǵ� �ݹ�޼ҵ�
	@Override
	public void mousePressed(MouseEvent arg0) {

		int row = table.getSelectedRow(); // ���õ� �� ���
		if (row < 0) { // ���õ� ���� ���� ���
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
