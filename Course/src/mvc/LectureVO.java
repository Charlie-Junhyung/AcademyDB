package mvc;

public class LectureVO {

	// 필드
	private int l_no; // 수업 등록 번호 (시퀀스)
	private String l_class;
	private String l_classroom;
	private String l_time; // 수업 시간
	private String l_teacher; // 선생님 이름
	
	// 디폴트 생성자
	public LectureVO() {
	}
	
	// 표시될 테이블을 만들기 위한 생성자
	public LectureVO(String l_class, String l_classroom, String l_time, String l_teacher) {
		this.l_class = l_class;
		this.l_classroom = l_classroom;
		this.l_time = l_time;
		this.l_teacher = l_teacher;
	}

	// 접근자와 설정자
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
