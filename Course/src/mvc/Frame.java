package mvc;

import java.sql.Connection;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tab;
	private StudentPane studentPane;
	private LecturePane lecturePane;
	
	public Frame() throws ParseException {
		
		JFrame frame = new JFrame();
		
		// 프레임에 TabbedPane을 생성하고 각 tab을 붙임
		tab = new JTabbedPane();
		studentPane = new StudentPane();
		tab.addTab("학생 정보", studentPane);
		lecturePane = new LecturePane();
		tab.addTab("수업 정보", lecturePane);
		getContentPane().add(tab);
		
		// 프레임 설정
		setTitle("수강 정보");
		pack();
		setSize(538, 580); 
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 우측 상단의 X 버튼으로 종료
		setLocation(100, 100); // 창이 열리는 위치 설정		

	}

	public static void main(String[] args) throws ParseException {

		try {
			// 실행되고 있는 시스템의 스타일로 설정(Window와 X-window는 윈도우가 보여지는 스타일이 다르다)
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Frame frame = new Frame(); // 프레임 생성
		
		Connection con = DBUtil.getConnection(); // DB 연결
		if(con == null) {
			System.out.println("DB 연결 실패!");
			JOptionPane.showMessageDialog(frame, "DB 연결에 실패했습니다", "DB 연결 오류", JOptionPane.ERROR_MESSAGE);
		} else  {
			System.out.println("DB 연결 성공!");
			JOptionPane.showMessageDialog(frame, "DB 연결에 성공했습니다", "DB 연결 성공", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
