package pojo;

import java.io.Serializable;
import java.util.*;

public class HocKi {

	private int maHK;
	private String tenHocKi;
	private int namHoc;
	private Date ngayBatDau;
	private Date ngayKetThuc;

	public HocKi() {
	}

	public HocKi(int maHK, String tenHocKi, int namHoc, Date ngayBatDau, Date ngayKetThuc) {
		this.maHK = maHK;
		this.tenHocKi = tenHocKi;
		this.namHoc = namHoc;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}

	public int getMaHK() {
		return maHK;
	}

	public void setMaHK(int maHK) {
		this.maHK = maHK;
	}

	public String getTenHocKi() {
		return tenHocKi;
	}

	public void setTenHocKi(String tenHocKi) {
		this.tenHocKi = tenHocKi;
	}

	public int getNamHoc() {
		return namHoc;
	}

	public void setNamHoc(int namHoc) {
		this.namHoc = namHoc;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
}
