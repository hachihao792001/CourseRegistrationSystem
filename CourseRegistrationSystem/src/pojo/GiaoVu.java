package pojo;

import java.util.Date;

public class GiaoVu {
	private int maGVu;
	private TaiKhoan taiKhoan;
	private String tenGiaoVu;
	private String gioiTinh;
	private Date ngSinh;

	public GiaoVu() {
	}

	public GiaoVu(int maGVu, TaiKhoan taiKhoan, String tenGiaoVu, String gioiTinh, Date ngSinh) {
		this.maGVu = maGVu;
		this.taiKhoan = taiKhoan;
		this.tenGiaoVu = tenGiaoVu;
		this.gioiTinh = gioiTinh;
		this.ngSinh = ngSinh;
	}

	public int getMaGVu() {
		return maGVu;
	}

	public void setMaGVu(int maGVu) {
		this.maGVu = maGVu;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getTenGiaoVu() {
		return tenGiaoVu;
	}

	public void setTenGiaoVu(String tenGiaoVu) {
		this.tenGiaoVu = tenGiaoVu;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgSinh() {
		return ngSinh;
	}

	public void setNgSinh(Date ngSinh) {
		this.ngSinh = ngSinh;
	}

}
