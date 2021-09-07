package DTO;

public class nguoidungDTO {

	private String manv;
	private String mkhau;
	private String quyen;
	private int trangthai;

	public nguoidungDTO(String manv, String mkhau, String quyen, int trangthai) {

		this.manv = manv;
		this.mkhau = mkhau;
		this.quyen = quyen;
		this.trangthai = 1;
	}

	public nguoidungDTO() {
		manv = null;
		mkhau = null;
		quyen = null;
		trangthai = 1;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getMkhau() {
		return mkhau;
	}

	public void setMkhau(String mkhau) {
		this.mkhau = mkhau;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

}
