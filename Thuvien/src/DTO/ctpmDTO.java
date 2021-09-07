package DTO;

public class ctpmDTO {
	private String mapm;
	private String masach;
	private int soluong;
	private String tinhtrang;
	private int tienthechan;
	
	public ctpmDTO() {
		mapm = null;
		masach = null;
		soluong = 0;
		tinhtrang = null;
		tienthechan = 0;
	}

	public ctpmDTO(String mapm, String masach, int soluong, String tinhtrang, int tienthechan) {
		this.mapm = mapm;
		this.masach = masach;
		this.soluong = soluong;
		this.tinhtrang = tinhtrang;
		this.tienthechan = tienthechan;
	}

	public String getMapm() {
		return mapm;
	}

	public void setMapm(String mapm) {
		this.mapm = mapm;
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

	public String getTinhtrang() {
		return tinhtrang;
	}

	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	public int getTienthechan() {
		return tienthechan;
	}

	public void setTienthechan(int tienthechan) {
		this.tienthechan = tienthechan;
	}

	@Override
	public String toString() {
		return "ctpmDTO [mapm=" + mapm + ", masach=" + masach + ", soluong=" + soluong + ", tinhtrang=" + tinhtrang
				+ ", tienthechan=" + tienthechan + "]";
	}

	@Override
	public boolean equals(Object other) {
	    if (!(other instanceof phieuphatDTO)) {
	        return false;
	    }

	    ctpmDTO pm = (ctpmDTO) other;

	    return this.mapm.equals(pm.getMapm())
	        && this.masach.equals(pm.getMasach()) && ( this.soluong == pm.getSoluong())
	        && (this.tienthechan == pm.getTienthechan()) && this.tinhtrang.equals(pm.getTinhtrang());
	}

	
}
