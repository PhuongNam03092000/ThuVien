package DTO;

public class sachDTO {
	private String masach;
	private String tensach;
	private long giasach;
	private String matheloai;
	private String matg;
	private String manxb;
	private String malinhvuc;
	private String hinhanh;
	private int soluong;
	private int trangthai;

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

	public sachDTO() {
		masach = null;
		tensach = null;
		giasach = 0;
		matheloai = null;
		matg = null;
		manxb = null;
		malinhvuc = null;
		hinhanh = null;
		soluong = 0;
		trangthai = 0;// trạng thái 0 là mặc định , 1 là còn ở bảng ,2 là ẩn khỏi bảng
	}

	public sachDTO(String masach, String tensach, long giasach, String matheloai, String matg, String manxb,
			String malinhvuc, String hinhanh, int soluong, int trangthai) {
		this.masach = masach;
		this.tensach = tensach;
		this.giasach = giasach;
		this.matheloai = matheloai;
		this.matg = matg;
		this.manxb = manxb;
		this.malinhvuc = malinhvuc;
		this.hinhanh = hinhanh;
		this.soluong = soluong;
		this.trangthai = trangthai;
	}

	public sachDTO(sachDTO a) {
		this.masach = a.getMasach();
		this.tensach = a.getTensach();
		this.giasach = a.getGiasach();
		this.matheloai = a.getMatheloai();
		this.matg = a.getMatg();
		this.manxb = a.getManxb();
		this.malinhvuc = a.getMalinhvuc();
		this.hinhanh = a.getHinhanh();
		this.soluong = a.getSoluong();
		this.trangthai = a.getTrangthai();
	}

	public String getMasach() {
		return masach;
	}

	public void setMasach(String masach) {
		this.masach = masach;
	}

	public String getTensach() {
		return tensach;
	}

	public void setTensach(String tensach) {
		this.tensach = tensach;
	}

	public long getGiasach() {
		return giasach;
	}

	public void setGiasach(long giasach) {
		this.giasach = giasach;
	}

	public String getMatheloai() {
		return matheloai;
	}

	public void setMatheloai(String matheloai) {
		this.matheloai = matheloai;
	}

	public String getMatg() {
		return matg;
	}

	public void setMatg(String matg) {
		this.matg = matg;
	}

	public String getManxb() {
		return manxb;
	}

	public void setManxb(String manxb) {
		this.manxb = manxb;
	}

	public String getMalinhvuc() {
		return malinhvuc;
	}

	public void setMalinhvuc(String malinhvuc) {
		this.malinhvuc = malinhvuc;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	// thêm 1 hàm khởi tạo dựa trên DTO cho trước

    @Override
    public String toString() {
        return "sachDTO{" + "masach=" + masach + ", tensach=" + tensach + ", giasach=" + giasach + ", matheloai=" + matheloai + ", matg=" + matg + ", manxb=" + manxb + ", malinhvuc=" + malinhvuc + ", hinhanh=" + hinhanh + ", soluong=" + soluong + ", trangthai=" + trangthai + '}';
    }

}
