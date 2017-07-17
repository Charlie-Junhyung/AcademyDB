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
	
	// 컴포넌트 필드
	private JPanel plTable;

	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;

	// LecturePane 생성자
	public LecturePane() {
		this.setLayout(null);
		this.setBackground(new Color(81,152,0));

		plTable = new JPanel();
		plTable.setBorder(BorderFactory.createRaisedBevelBorder());
		plTable.setBounds(10, 5, 500, 500);
		plTable.setLayout(new BorderLayout());

		String data[][] = new String[0][5];
		String title[] = { "강의명", "강의실", "강의시간", "강사" };
		model = new DefaultTableModel(data, title) { // 모델 객체 설정
			public boolean isCellEditable(int r, int c) { // 셀 편집 불가능하게 처리
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

		// JTable 컴포넌트 속성 및 컬럼 설정
		table.getColumnModel().getColumn(0).setPreferredWidth(50); // 강의명
		table.getColumnModel().getColumn(1).setPreferredWidth(70); // 강의실
		table.getColumnModel().getColumn(2).setPreferredWidth(50); // 강의시간
		table.getColumnModel().getColumn(3).setPreferredWidth(50); // 강사

		table.setShowHorizontalLines(false); // 수평 라인 안보이게 처리
		table.setShowVerticalLines(false); // 수직 라인 안보이게 처리
		table.setSelectionBackground(new Color(171,242,0)); // 선택한 셀 배경색
		table.setSelectionForeground(Color.black); // 선택한 셀 전경색
		table.setRowMargin(0);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setRequestFocusEnabled(false); // 특정 셀에 포커스 설정 안되게 처리

		table.setSelectionMode(0); // 단일 셀 선택 모드

		table.getTableHeader().setReorderingAllowed(false); // 헤더고정
		table.getTableHeader().setMaximumSize(new Dimension(140, 0)); // 헤더 너비 조절 가능한 최대값
		table.getTableHeader().setMinimumSize(new Dimension(10, 0)); // 헤더 너비 조절 가능한 최소값
		table.getTableHeader().setResizingAllowed(false); // 헤더 조절 불가능

		table.setAlignmentX(JTable.CENTER_ALIGNMENT); // 정렬
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
			JOptionPane.showMessageDialog(this, "뷰를 만드는데 실패하였습니다", "에러 발생", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 테이블에서 레코드를 선택할 때 이벤트 처리
	@Override
	public void mousePressed(MouseEvent me) {
		int row = table.getSelectedRow(); // 선택된 행 얻기
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
