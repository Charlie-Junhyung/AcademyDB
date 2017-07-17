package mvc;

public class LectureVO {

	// �ʵ�
	private int l_no; // ���� ��� ��ȣ (������)
	private String l_class;
	private String l_classroom;
	private String l_time; // ���� �ð�
	private String l_teacher; // ������ �̸�
	
	// ����Ʈ ������
	public LectureVO() {
	}
	
	// ǥ�õ� ���̺��� ����� ���� ������
	public LectureVO(String l_class, String l_classroom, String l_time, String l_teacher) {
		this.l_class = l_class;
		this.l_classroom = l_classroom;
		this.l_time = l_time;
		this.l_teacher = l_teacher;
	}

	// �����ڿ� ������
	public int getL_no() {
		return l_no;
	}

	public void setL_no(int l_no) {
		this.l_no = l_no;
	}

	public String getL_class() {
		return l_class;
	}

	public void setL_class(String l_class) {
		this.l_class = l_class;
	}

	public String getL_classroom() {
		return l_classroom;
	}

	public void setL_classroom(String l_classroom) {
		this.l_classroom = l_classroom;
	}

	public String getL_time() {
		return l_time;
	}

	public void setL_time(String l_time) {
		this.l_time = l_time;
	}

	public String getL_teacher() {
		return l_teacher;
	}

	public void setL_teacher(String l_teacher) {
		this.l_teacher = l_teacher;
	}
	
}
