package DTO;

public class theTVDTO {
	private String mathe;
	private String madg;
	private String ngaycap;
	private String ngayhethan;
	private int trangthai;

	public theTVDTO() {
		mathe = null;
		madg = null;
		ngaycap = null;
		ngayhethan = null;
		trangthai = 1;
	}

	public theTVDTO(String mathe, String madg, String ngaycap, String ngayhethan, int trangthai) {
		this.mathe = mathe;
		this.madg = madg;
		this.ngaycap = ngaycap;
		this.ngayhethan = ngayhethan;
		this.trangthai = trangthai;
	}

	public String getMathe() {
		return mathe;
	}

	public void setMathe(String mathe) {
		this.mathe = mathe;
	}

	public String getMadg() {
		return madg;
	}

	public void setMadg(String madg) {
		this.madg = madg;
	}

	public String getNgaycap() {
		return ngaycap;
	}

	public void setNgaycap(String ngaycap) {
		this.ngaycap = ngaycap;
	}

	public String getNgayhethan() {
		return ngayhethan;
	}

	public void setNgayhethan(String ngayhethan) {
		this.ngayhethan = ngayhethan;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

}
