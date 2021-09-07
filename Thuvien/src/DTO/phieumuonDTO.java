package DTO;

public class phieumuonDTO {
	private String mapm;
	private String manv;
	private String mathe;
	private String ngaymuon;
	private String ngayquidinhtra;
	private long tongtienmuon;

	public phieumuonDTO() {
		mapm = null;
		manv = null;
		mathe = null;
		ngaymuon = null;
		ngayquidinhtra = null;
		tongtienmuon = 0;
	}

	public phieumuonDTO(String mapm, String manv, String mathe, String ngaymuon, String ngayquidinhtra) {
		this.mapm = mapm;
		this.manv = manv;
		this.mathe = mathe;
		this.ngaymuon = ngaymuon;
		this.ngayquidinhtra = ngayquidinhtra;
	}

	public phieumuonDTO(String mapm, String manv, String mathe, String ngaymuon, String ngayquidinhtra, long tongtienmuon) {

		this.mapm = mapm;
		this.manv = manv;
		this.mathe = mathe;
		this.ngaymuon = ngaymuon;
		this.ngayquidinhtra = ngayquidinhtra;
		this.tongtienmuon = tongtienmuon;
	}

	public String getMapm() {
		return mapm;
	}

	public void setMapm(String mapm) {
		this.mapm = mapm;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getMathe() {
		return mathe;
	}

	public void setMathe(String mathe) {
		this.mathe = mathe;
	}

	public String getNgaymuon() {
		return ngaymuon;
	}

	public void setNgaymuon(String ngaymuon) {
		this.ngaymuon = ngaymuon;
	}

	public String getNgayquidinhtra() {
		return ngayquidinhtra;
	}

	public void setNgayquidinhtra(String ngayquidinhtra) {
		this.ngayquidinhtra = ngayquidinhtra;
	}

	public long getTongtienmuon() {
		return tongtienmuon;
	}

	public void setTongtienmuon(long tongtienmuon) {
		this.tongtienmuon = tongtienmuon;
	}

	@Override
	public String toString() {
		return "phieumuonDTO [mapm=" + mapm + ", manv=" + manv + ", mathe=" + mathe + ", ngaymuon=" + ngaymuon
				+ ", ngayquidinhtra=" + ngayquidinhtra + ", tongtienmuon=" + tongtienmuon + "]";
	}

}
