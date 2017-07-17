package mvc;

public class StudentVO {

	// 필드
	private int s_no;
	private String s_name;
	private String s_year;
	private String s_phone;
	private String l_class;

	// 디폴트 생성자
	public StudentVO() {
	}

	// 생성자
	public StudentVO(int s_no, String s_name, String s_year, String s_phone) {
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_year = s_year;
		this.s_phone = s_phone;
	}
	
	public StudentVO(int s_no, String s_name, String s_year, String s_phone, String l_class) {
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_year = s_year;
		this.s_phone = s_phone;
		this.l_class = l_class;
	}

	// 필드 접근자와 설정자
	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_year() {
		return s_year;
	}

	public void setS_year(String s_year) {
		this.s_year = s_year;
	}

	public String getS_phone() {
		return s_phone;
	}

	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}
	
	public String getL_class() {
		return l_class;
	}

	public void setL_class(String l_class) {
		this.l_class = l_class;
	}

}
