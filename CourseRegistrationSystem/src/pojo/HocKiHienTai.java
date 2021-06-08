package pojo;

public class HocKiHienTai {
	private int id;
	private HocKi hk;

	public HocKiHienTai() {
	}

	public HocKiHienTai(int id, HocKi hk) {
		super();
		this.id = id;
		this.hk = hk;
	}

	public HocKi getHk() {
		return hk;
	}

	public void setHk(HocKi hk) {
		this.hk = hk;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
