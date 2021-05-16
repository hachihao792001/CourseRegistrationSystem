package pojo;

public class TaiKhoan {
	private String tenTaiKhoan;
	private String matKhau;
	private String loai;


	public TaiKhoan() {
	}


	public TaiKhoan(String tenTaiKhoan, String matKhau, String loai) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.loai = loai;
	}
	
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}


	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}


	public String getMatKhau() {
		return matKhau;
	}


	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}


	public String getLoai() {
		return loai;
	}


	public void setLoai(String loai) {
		this.loai = loai;
	}
}
