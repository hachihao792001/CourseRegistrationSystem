package pojo;

import java.io.Serializable;

public class Hoc {

	public static class HocID implements Serializable {
		private static final long serialVersionUID = 1L;

		private SinhVien sinhVien;
		private LopHoc lopHoc;

		public HocID() {
		}

		public HocID(SinhVien sinhVien, LopHoc lopHoc) {
			this.sinhVien = sinhVien;
			this.lopHoc = lopHoc;
		}

		public SinhVien getSinhVien() {
			return sinhVien;
		}

		public void setSinhVien(SinhVien sinhVien) {
			this.sinhVien = sinhVien;
		}

		public LopHoc getLopHoc() {
			return lopHoc;
		}

		public void setLopHoc(LopHoc lopHoc) {
			this.lopHoc = lopHoc;
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

	private HocID hocID;

	public Hoc() {
	}

	public Hoc(HocID hocID) {
		this.hocID = hocID;
	}

	public HocID getHocID() {
		return hocID;
	}

	public void setHocID(HocID hocID) {
		this.hocID = hocID;
	}
}
