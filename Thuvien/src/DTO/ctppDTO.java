package DTO;

public class ctppDTO {
	private String mapp;
	private String masach;
	private int soluong;
	private String maqd;
	private int thanhtien;
	
	public ctppDTO(String mapp, String masach, int soluong, String maqd, int thanhtien) {
		this.mapp = mapp;
		this.masach = masach;
		this.soluong = soluong;
		this.maqd = maqd;
		this.thanhtien = thanhtien;
	}
	
	public ctppDTO(String mapp, String masach, int soluong, String maqd) {
		super();
		this.mapp = mapp;
		this.masach = masach;
		this.soluong = soluong;
		this.maqd = maqd;
	}

	public String getMapp() {
		return mapp;
	}
	public void setMapp(String mapp) {
		this.mapp = mapp;
	}
	public String getMasach() {
		return masach;
	}
	public void setMasach(String masach) {
		this.masach = masach;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public String getMaqd() {
		return maqd;
	}
	public void setMaqd(String maqd) {
		this.maqd = maqd;
	}
	public int getThanhtien() {
		return thanhtien;
	}
	public void setThanhtien(int thanhtien) {
		this.thanhtien = thanhtien;
	}
	@Override
	public String toString() {
		return "ctppDTO [mapp=" + mapp + ", masach=" + masach + ", soluong=" + soluong + ", maqd=" + maqd
				+ ", thanhtien=" + thanhtien + "]";
	}
}
