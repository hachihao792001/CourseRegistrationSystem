package pojo;

import java.io.Serializable;
import java.util.Date;

public class DKHP {

	public static class DKHPID implements Serializable {
		private static final long serialVersionUID = 1L;
		private SinhVien sinhVien;
		private HocPhan hocPhan;

		public DKHPID(SinhVien sinhVien, HocPhan hocPhan) {
			this.sinhVien = sinhVien;
			this.hocPhan = hocPhan;
		}

		public DKHPID() {
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

		@Override
		public int hashCode() {
			return super.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}
	}

	private DKHPID dkhpID;
	private GiaoVien gvlt;
	private Date thoiGianHoc;
	private Date thoiGianDKHP;

	public DKHP() {
	}

	public DKHP(DKHPID dkhpID, GiaoVien gvlt, Date thoiGianHoc, Date thoiGianDKHP) {
		this.dkhpID = dkhpID;
		this.gvlt = gvlt;
		this.thoiGianHoc = thoiGianHoc;
		this.thoiGianDKHP = thoiGianDKHP;
	}

	public DKHPID getDkhpID() {
		return dkhpID;
	}

	public void setDkhpID(DKHPID dkhpID) {
		this.dkhpID = dkhpID;
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
