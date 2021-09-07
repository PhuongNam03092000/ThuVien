package DTO;

public class loaiDTO {
	private String maloai;
	private String tenloai;
	private int trangthai;
	
	public loaiDTO() {
		maloai = null;
		tenloai = null;
		trangthai = 1;
	}

	public loaiDTO(String maloai, String tenloai, int trangthai) {
		this.maloai = maloai;
		this.tenloai = tenloai;
		this.trangthai = trangthai;
	}

	public String getMaloai() {
		return maloai;
	}

	public void setMaloai(String maloai) {
		this.maloai = maloai;
	}

	public String getTenloai() {
		return tenloai;
	}

	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	
}
