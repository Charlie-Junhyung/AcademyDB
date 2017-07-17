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
		
		// �����ӿ� TabbedPane�� �����ϰ� �� tab�� ����
		tab = new JTabbedPane();
		studentPane = new StudentPane();
		tab.addTab("�л� ����", studentPane);
		lecturePane = new LecturePane();
		tab.addTab("���� ����", lecturePane);
		getContentPane().add(tab);
		
		// ������ ����
		setTitle("���� ����");
		pack();
		setSize(538, 580); 
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� ����� X ��ư���� ����
		setLocation(100, 100); // â�� ������ ��ġ ����		

	}

	public static void main(String[] args) throws ParseException {

		try {
			// ����ǰ� �ִ� �ý����� ��Ÿ�Ϸ� ����(Window�� X-window�� �����찡 �������� ��Ÿ���� �ٸ���)
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Frame frame = new Frame(); // ������ ����
		
		Connection con = DBUtil.getConnection(); // DB ����
		if(con == null) {
			System.out.println("DB ���� ����!");
			JOptionPane.showMessageDialog(frame, "DB ���ῡ �����߽��ϴ�", "DB ���� ����", JOptionPane.ERROR_MESSAGE);
		} else  {
			System.out.println("DB ���� ����!");
			JOptionPane.showMessageDialog(frame, "DB ���ῡ �����߽��ϴ�", "DB ���� ����", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
