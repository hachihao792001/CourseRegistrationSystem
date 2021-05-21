package pojo;

public class HocPhan {
	private int maHP;
	private MonHoc monHoc;
	private GiaoVien gvlt;
	private String tenPhong;
	private int thu;
	private int ca;
	private int slotToiDa;
	private KyDKHP kyDKHP;

	public HocPhan() {
	}

	public HocPhan(int maHP, MonHoc monHoc, GiaoVien gvlt, String tenPhong, int thu, int ca, int slotToiDa, KyDKHP kyDKHP) {
		this.maHP = maHP;
		this.monHoc = monHoc;
		this.gvlt = gvlt;
		this.tenPhong = tenPhong;
		this.thu = thu;
		this.ca = ca;
		this.slotToiDa = slotToiDa;
		this.kyDKHP = kyDKHP;
	}

	public MonHoc getMonHoc() {
		return monHoc;
	}

	public int getMaHP() {
		return maHP;
	}

	public void setMaHP(int maHP) {
		this.maHP = maHP;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	public GiaoVien getGvlt() {
		return gvlt;
	}

	public void setGvlt(GiaoVien gvlt) {
		this.gvlt = gvlt;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public int getThu() {
		return thu;
	}

	public void setThu(int thu) {
		this.thu = thu;
	}

	public int getCa() {
		return ca;
	}

	public void setCa(int ca) {
		this.ca = ca;
	}

	public int getSlotToiDa() {
		return slotToiDa;
	}

	public void setSlotToiDa(int slotToiDa) {
		this.slotToiDa = slotToiDa;
	}

	public KyDKHP getKyDKHP() {
		return kyDKHP;
	}

	public void setKyDKHP(KyDKHP kyDKHP) {
		this.kyDKHP = kyDKHP;
	}

}
