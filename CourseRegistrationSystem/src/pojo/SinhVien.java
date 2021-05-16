package pojo;

import java.util.Date;

public class SinhVien {
	private String mssv;
	private TaiKhoan taiKhoan;
	private String hoTen;
	private String gioiTinh;
	private Date ngSinh;

	public SinhVien(String mssv, TaiKhoan taiKhoan, String hoTen, String gioiTinh, Date ngSinh) {
		this.mssv = mssv;
		this.taiKhoan = taiKhoan;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngSinh = ngSinh;
	}

	public SinhVien() {
	}

	public SinhVien(String mssv) {
		this.mssv = mssv;
	}

	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
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
