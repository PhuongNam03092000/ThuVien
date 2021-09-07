package DTO;

public class linhvucDTO {
	private String malinhvuc;
	private String tenlinhvuc;
	private int trangthai;
	
	public linhvucDTO() {
		malinhvuc = null;
		tenlinhvuc = null;
		trangthai = 1;
		
	}
	
	public linhvucDTO(String malinhvuc, String tenlinhvuc, int trangthai) {
		this.malinhvuc = malinhvuc;
		this.tenlinhvuc = tenlinhvuc;
		this.trangthai = trangthai;
	}
	public String getMalinhvuc() {
		return malinhvuc;
	}
	public void setMalinhvuc(String malinhvuc) {
		this.malinhvuc = malinhvuc;
	}
	public String getTenlinhvuc() {
		return tenlinhvuc;
	}
	public void setTenlinhvuc(String tenlinhvuc) {
		this.tenlinhvuc = tenlinhvuc;
	}
	public int getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	
}
