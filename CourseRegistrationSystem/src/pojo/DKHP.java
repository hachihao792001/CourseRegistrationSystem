package pojo;

import java.util.Date;

public class DKHP {

	private int maDKHP;
	private SinhVien sinhVien;
	private HocPhan hocPhan;
	private GiaoVien gvlt;
	private Date thoiGianHoc;
	private Date thoiGianDKHP;

	public DKHP() {
	}

	public DKHP(int maDKHP, SinhVien sinhVien, HocPhan hocPhan, GiaoVien gvlt, Date thoiGianHoc, Date thoiGianDKHP) {
		this.maDKHP = maDKHP;
		this.sinhVien = sinhVien;
		this.hocPhan = hocPhan;
		this.gvlt = gvlt;
		this.thoiGianHoc = thoiGianHoc;
		this.thoiGianDKHP = thoiGianDKHP;
	}

	public int getMaDKHP() {
		return maDKHP;
	}

	public void setMaDKHP(int maDKHP) {
		this.maDKHP = maDKHP;
	}

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	public HocPhan getHocPhan() {
		return hocPhan;
	}

	public void setHocPhan(HocPhan hocPhan) {
		this.hocPhan = hocPhan;
	}

	public GiaoVien getGvlt() {
		return gvlt;
	}

	public void setGvlt(GiaoVien gvlt) {
		this.gvlt = gvlt;
	}

	public Date getThoiGianHoc() {
		return thoiGianHoc;
	}

	public void setThoiGianHoc(Date thoiGianHoc) {
		this.thoiGianHoc = thoiGianHoc;
	}

	public Date getThoiGianDKHP() {
		return thoiGianDKHP;
	}

	public void setThoiGianDKHP(Date thoiGianDKHP) {
		this.thoiGianDKHP = thoiGianDKHP;
	}

}
