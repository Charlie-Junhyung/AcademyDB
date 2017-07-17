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
		this.setBackground(new Color(81, 152, 0)); // panel 배경색 설정

		searchBorder = new TitledBorder("검색");

		plSearch = new JPanel();
		plSearch.setBackground(new Color(171, 242, 0)); // panel 배경색 설정
		plSearch.setBorder(searchBorder); // TitledBorder 설정
		plSearch.setBounds(10, 5, 500, 50);
		plSearch.setLayout(null);

		lblName2 = new JLabel("이름");
		lblName2.setBounds(20, 12, 50, 30);
		plSearch.add(lblName2);

		tfName = new JTextField();
		tfName.setBounds(60, 12, 200, 30);
		plSearch.add(tfName);

		btnSearch = new JButton("검색");
		btnSearch.setBounds(270, 12, 60, 30);
		btnSearch.addActionListener(this);
		plSearch.add(btnSearch);

		btnRef = new JButton("전체 학생");
		btnRef.setBounds(345, 12, 90, 30);
		btnRef.addActionListener(this);
		plSearch.add(btnRef);

		plTable = new JPanel();
		plTable.setBorder(BorderFactory.createRaisedBevelBorder()); // 패널의 경계를
																	// 양각으로
		plTable.setBounds(10, 60, 500, 445);
		plTable.setLayout(new BorderLayout());

		String data[][] = new String[0][5];
		String title[] = { "등록번호", "이름", "학년", "연락처", "수강과목" };
		model = new DefaultTableModel(data, title) { // 모델 객체 설정
			public boolean isCellEditable(int r, int c) { // 셀 편집불가능하게 처리
				return false;
			}
		};

		table = new JTable(model); // defaultTableModel로 JTable 생성
		table.setBorder(BorderFactory.createEtchedBorder());
		table.addMouseListener(this); // 테이블의 레코드 선택 이벤트 처리를 위해 listener 추가

		scroll = new JScrollPane(table);
		scrollVport = scroll.getViewport(); // JScrollPane이 스크롤될 때 객체를
		scrollVport.add(table, null); // 추가하는데 대해서 JViewport를 사용한다
		plTable.add(scroll, BorderLayout.CENTER);

		this.add(plSearch);
		this.add(plTable);

		// JTable 컴포넌트 속성 및 컬럼 설정
		table.getColumnModel().getColumn(0).setPreferredWidth(30); // 등록번호
		table.getColumnModel().getColumn(1).setPreferredWidth(40); // 이름
		table.getColumnModel().getColumn(2).setPreferredWidth(40); // 학년
		table.getColumnModel().getColumn(3).setPreferredWidth(70); // 휴대폰
		table.getColumnModel().getColumn(4).setPreferredWidth(70); // 휴대폰

		table.setShowHorizontalLines(false); // 수평 라인 안보이게 처리
		table.setShowVerticalLines(false); // 수직 라인 안보이게 처리
		table.setSelectionBackground(new Color(171, 242, 0)); // 선택한 셀 배경색
		table.setSelectionForeground(Color.black); // 선택한 셀 전경색
		table.setRowMargin(0); // 행 간 간격 설정 (픽셀단위)
		table.setIntercellSpacing(new Dimension(0, 0)); // 셀 간 스페이스의 높이와 폭을 설정
		table.setRequestFocusEnabled(false); // 특정 셀에 포커스 설정 안되게 처리

		table.setSelectionMode(0); // 단일 셀 선택 모드

		table.getTableHeader().setReorderingAllowed(false); // 헤더고정
		table.getTableHeader().setMaximumSize(new Dimension(140, 0)); 
		// 헤더 너비 조절 가능한 최대값
		table.getTableHeader().setMinimumSize(new Dimension(10, 0)); 
		// 헤더 너비 조절 가능한 최소값
		table.getTableHeader().setResizingAllowed(false); // 헤더 조절 불가능

		table.setAlignmentX(JTable.CENTER_ALIGNMENT); // 정렬
		table.setAlignmentY(JTable.CENTER_ALIGNMENT);

	}

	// 버튼 이벤트 처리 메소드
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object obj = evt.getSource(); // 이벤트 객체 선언

		// 검색 버튼을 눌렀을 때
		if (obj == btnSearch) {
			System.out.println("검색 버튼 클릭!");
			
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
				JOptionPane.showMessageDialog(this, "검색에 실패하였습니다", "검색 실패", JOptionPane.ERROR_MESSAGE);
			}
		}

		// 전체 학생 버튼을 눌렀을 때
		else if (obj == btnRef) {
			System.out.println("전체 학생 버튼 클릭!");

			try {
				model.setNumRows(0); // 테이블을 깨끗이 청소
				ArrayList<StudentVO> showList = new ArrayList<StudentVO>();

				// StudentDAO에 정의한 ArrayList를 가져오기 위해
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
				JOptionPane.showMessageDialog(this, "검색에 실패하였습니다", "검색 실패", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// JTable에서 행을 선택할 때 호출되는 콜백메소드
	@Override
	public void mousePressed(MouseEvent arg0) {

		int row = table.getSelectedRow(); // 선택된 행 얻기
		if (row < 0) { // 선택된 행이 없을 경우
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
