package pojo;

import java.util.Date;

public class SinhVien {
	private int mssv;
	private TaiKhoan taiKhoan;
	private String hoTen;
	private String gioiTinh;
	private Date ngSinh;
	private String khoa;

	public SinhVien(int mssv, TaiKhoan taiKhoan, String hoTen, String gioiTinh, Date ngSinh, String khoa) {
		this.mssv = mssv;
		this.taiKhoan = taiKhoan;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngSinh = ngSinh;
		this.khoa = khoa;
	}

	public SinhVien() {
	}

	public SinhVien(int mssv) {
		this.mssv = mssv;
	}

	public int getMssv() {
		return mssv;
	}

	public void setMssv(int mssv) {
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

	public String getKhoa() {
		return khoa;
	}

	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}
}
