package pojo;

import java.io.Serializable;
import java.util.*;

public class KyDKHP {

	public static class KyDKHPID implements Serializable {
		private static final long serialVersionUID = 1L;
		private HocKi hocKi;
		private int lan;

		public KyDKHPID(HocKi hocKi, int lan) {
			this.hocKi = hocKi;
			this.lan = lan;
		}

		public KyDKHPID() {
		}
		
		public HocKi getHocKi() {
			return hocKi;
		}

		public void setHocKi(HocKi hocKi) {
			this.hocKi = hocKi;
		}

		public int getLan() {
			return lan;
		}

		public void setLan(int lan) {
			this.lan = lan;
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

	private KyDKHPID kyDKHPID;
	private Date ngayBatDau;
	private Date ngayKetThuc;

	public KyDKHP() {
	}

	public KyDKHP(KyDKHPID kyDKHPID, Date ngayBatDau, Date ngayKetThuc) {
		this.kyDKHPID = kyDKHPID;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}

	public KyDKHPID getKyDKHPID() {
		return kyDKHPID;
	}

	public void setKyDKHPID(KyDKHPID kyDKHPID) {
		this.kyDKHPID = kyDKHPID;
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
