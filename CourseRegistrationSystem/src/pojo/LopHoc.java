package pojo;

public class LopHoc {
	private String maLop;
	private int tongSoSV, tongSoNam, tongSoNu;
	
	public LopHoc() {
	}

	public LopHoc(String maLop, int tongSoSV, int tongSoNam, int tongSoNu) {
		this.maLop = maLop;
		this.tongSoSV = tongSoSV;
		this.tongSoNam = tongSoNam;
		this.tongSoNu = tongSoNu;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public int getTongSoSV() {
		return tongSoSV;
	}

	public void setTongSoSV(int tongSoSV) {
		this.tongSoSV = tongSoSV;
	}

	public int getTongSoNam() {
		return tongSoNam;
	}

	public void setTongSoNam(int tongSoNam) {
		this.tongSoNam = tongSoNam;
	}

	public int getTongSoNu() {
		return tongSoNu;
	}

	public void setTongSoNu(int tongSoNu) {
		this.tongSoNu = tongSoNu;
	}

	
}
