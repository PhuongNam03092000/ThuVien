package DTO;

public class quydinhDTO {
	private String maqd;
	private String tenqd;
	private long mucphat;
	private int trangthai;

	public quydinhDTO() {
		maqd = null;
		tenqd = null;
		mucphat = 0;
		trangthai = 1;
	}

	public quydinhDTO(String maqd, String tenqd, long mucphat, int trangthai) {
		this.maqd = maqd;
		this.tenqd = tenqd;
		this.mucphat = mucphat;
		this.trangthai = trangthai;
	}

	public String getMaqd() {
		return maqd;
	}

	public void setMaqd(String maqd) {
		this.maqd = maqd;
	}

	public String getTenqd() {
		return tenqd;
	}

	public void setTenqd(String tenqd) {
		this.tenqd = tenqd;
	}

	public long getMucphat() {
		return mucphat;
	}

	public void setMucphat(long mucphat) {
		this.mucphat = mucphat;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

}
