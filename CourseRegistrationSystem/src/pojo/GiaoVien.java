package pojo;

import java.util.Date;

public class GiaoVien {
	private String maGV;
	private String hoTen;
	private float luong;
	private String phai;
	private Date ngSinh;
	private String diaChi;
	private String gvqlcm;
	private String maBM;


	public GiaoVien() {
	}

	public GiaoVien(String maGV) {
		this.maGV = maGV;
	}

	public GiaoVien(String maGV, String hoTen, float luong, String phai, Date ngSinh, String diaChi, String gvqlcm, String maBM) {
		this.maGV = maGV;
		this.hoTen = hoTen;
		this.luong = luong;
		this.phai = phai;
		this.ngSinh = ngSinh;
		this.diaChi = diaChi;
		this.gvqlcm = gvqlcm;
		this.maBM = maBM;
	}

	public String getMaGV() {
		return maGV;
	}

	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public float getLuong() {
		return luong;
	}

	public void setLuong(float luong) {
		this.luong = luong;
	}

	public String getPhai() {
		return phai;
	}

	public void setPhai(String phai) {
		this.phai = phai;
	}

	public Date getNgSinh() {
		return ngSinh;
	}

	public void setNgSinh(Date ngSinh) {
		this.ngSinh = ngSinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getGvqlcm() {
		return gvqlcm;
	}

	public void setGvqlcm(String gvqlcm) {
		this.gvqlcm = gvqlcm;
	}

	public String getMaBM() {
		return maBM;
	}

	public void setMaBM(String maBM) {
		this.maBM = maBM;
	}
}
