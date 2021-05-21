package pojo;

public class Hoc {

	private int maHoc;
	private SinhVien sinhVien;
	private LopHoc lopHoc;

	public Hoc(int maHoc, SinhVien sinhVien, LopHoc lopHoc) {
		this.maHoc = maHoc;
		this.sinhVien = sinhVien;
		this.lopHoc = lopHoc;
	}

	public Hoc() {
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

	public int getMaHoc() {
		return maHoc;
	}

	public void setMaHoc(int maHoc) {
		this.maHoc = maHoc;
	}

}
