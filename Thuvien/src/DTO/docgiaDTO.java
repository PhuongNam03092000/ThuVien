package DTO;

public class docgiaDTO {
	private String madg;
	private String hodg;
	private String tendg;
	private String ngaysinh;
	private String diachi;
	private String nghenghiep;
	private String trinhdo;
	private int trangthai;
	
	public docgiaDTO() {
		madg = null;
		hodg = null;
		tendg = null;
		ngaysinh = null;
		diachi = null;
		nghenghiep = null;
		trinhdo = null;
		trangthai = 1;
	}
	
	public docgiaDTO(String madg, String hodg, String tendg, String ngaysinh, String diachi, String nghenghiep,
			String trinhdo, int trangthai) {
		this.madg = madg;
		this.hodg = hodg;
		this.tendg = tendg;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.nghenghiep = nghenghiep;
		this.trinhdo = trinhdo;
		this.trangthai = trangthai;
	}

	public String getMadg() {
		return madg;
	}

	public void setMadg(String madg) {
		this.madg = madg;
	}

	public String getHodg() {
		return hodg;
	}

	public void setHodg(String hodg) {
		this.hodg = hodg;
	}

	public String getTendg() {
		return tendg;
	}

	public void setTendg(String tendg) {
		this.tendg = tendg;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getNghenghiep() {
		return nghenghiep;
	}

	public void setNghenghiep(String nghenghiep) {
		this.nghenghiep = nghenghiep;
	}

	public String getTrinhdo() {
		return trinhdo;
	}

	public void setTrinhdo(String trinhdo) {
		this.trinhdo = trinhdo;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	
	
}
