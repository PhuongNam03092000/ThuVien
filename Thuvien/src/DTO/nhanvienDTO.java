package DTO;

public class nhanvienDTO {
	private String manv;
	private String ho;
	private String ten;
	private String ngaysinh;
	private String gioitinh;
	private String diachi;
	private String email;
	private String sdt;
	private String luong;
	private int trangthai;

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public nhanvienDTO() {
		manv = null;
		ho = null;
		ten = null;
		ngaysinh = null;
		gioitinh = null;
		diachi = null;
		email = null;
		sdt = null;
		luong = null;
		trangthai = 1;
	}

	public nhanvienDTO(String manv, String ho, String ten, String ngaysinh, String gioitinh, String diachi,
			String email, String sdt, String luong) {
		this.manv = manv;
		this.ho = ho;
		this.ten = ten;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.email = email;
		this.sdt = sdt;
		this.luong = luong;
		this.trangthai = 1;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getLuong() {
		return luong;
	}

	public void setLuong(String luong) {
		this.luong = luong;
	}
}
