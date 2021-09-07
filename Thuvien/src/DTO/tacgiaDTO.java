package DTO;

public class tacgiaDTO {
	private String matg;
	private String hotg;
	private String tentg;
	private String email;
	private int trangthai;

	public tacgiaDTO() {
		matg = null;
		hotg = null;
		tentg = null;
		email = null;
		trangthai = 1;
	}

	public tacgiaDTO(String matg, String hotg, String tentg, String email, int trangthai) {
		this.matg = matg;
		this.hotg = hotg;
		this.tentg = tentg;
		this.email = email;
		this.trangthai = trangthai;
	}

	public String getMatg() {
		return matg;
	}

	public void setMatg(String matg) {
		this.matg = matg;
	}

	public String getHotg() {
		return hotg;
	}

	public void setHotg(String hotg) {
		this.hotg = hotg;
	}

	public String getTentg() {
		return tentg;
	}

	public void setTentg(String tentg) {
		this.tentg = tentg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

}
