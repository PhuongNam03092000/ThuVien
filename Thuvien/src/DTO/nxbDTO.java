package DTO;

public class nxbDTO {
	private String manxb;
	private String tenxb;
	private String sdt;
	private String email;
	private int trangthai;
	
	public nxbDTO() {
		manxb = null;
		tenxb = null;
		sdt = null;
		email = null;
	}
	public int getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	public nxbDTO(String manxb, String tenxb, String sdt, String email,int trangthai) {
		this.manxb = manxb;
		this.tenxb = tenxb;
		this.sdt = sdt;
		this.email = email;
                this.trangthai=trangthai;
	}
	public String getManxb() {
		return manxb;
	}
	public void setManxb(String manxb) {
		this.manxb = manxb;
	}
	public String getTenxb() {
		return tenxb;
	}
	public void setTenxb(String tenxb) {
		this.tenxb = tenxb;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
