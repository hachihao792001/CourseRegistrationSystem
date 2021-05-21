package pojo;

import java.util.*;

public class KyDKHP {

	private int maKyDKHP;
	private HocKi hocKi;
	private Date ngayBatDau;
	private Date ngayKetThuc;

	public KyDKHP() {
	}

	public KyDKHP(int maKyDKHP, HocKi hocKi, Date ngayBatDau, Date ngayKetThuc) {
		this.maKyDKHP = maKyDKHP;
		this.hocKi = hocKi;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}

	public int getMaKyDKHP() {
		return maKyDKHP;
	}

	public void setMaKyDKHP(int maKyDKHP) {
		this.maKyDKHP = maKyDKHP;
	}

	public HocKi getHocKi() {
		return hocKi;
	}

	public void setHocKi(HocKi hocKi) {
		this.hocKi = hocKi;
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
