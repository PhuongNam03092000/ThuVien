package DTO;

public class nccDTO {
	private String mancc;
	private String tenncc;
	private String diachi;
	private String sdt;
	private int trangthai;

	public nccDTO() {
		mancc = null;
		tenncc = null;
		diachi = null;
		sdt = null;
		trangthai = 1;
	}

	public nccDTO(String mancc, String tenncc, String diachi, String sdt, int trangthai) {
		this.mancc = mancc;
		this.tenncc = tenncc;
		this.diachi = diachi;
		this.sdt = sdt;
		this.trangthai = trangthai;
	}

	public String getMancc() {
		return mancc;
	}

	public void setMancc(String mancc) {
		this.mancc = mancc;
	}

	public String getTenncc() {
		return tenncc;
	}

	public void setTenncc(String tenncc) {
		this.tenncc = tenncc;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

}
