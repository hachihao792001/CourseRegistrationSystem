package pojo;

public class MonHoc {
	private String maMH;
	private String tenMH;
	private int soTinChi;

	public MonHoc() {
	}

	public MonHoc(String maMH, String tenMH, int soTinChi) {
		super();
		this.maMH = maMH;
		this.tenMH = tenMH;
		this.soTinChi = soTinChi;
	}

	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getTenMH() {
		return tenMH;
	}

	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}

	public int getSoTinChi() {
		return soTinChi;
	}

	public void setSoTinChi(int soTinChi) {
		this.soTinChi = soTinChi;
	}

}
