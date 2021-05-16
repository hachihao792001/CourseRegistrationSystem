package course_registration_system;

import java.util.*;
import pojo.*;

public class Main {
	public static void main(String args[]) {

		List<SinhVien> ds = SinhVienDAO.layDanhSachSinhVien();
		for (int i = 0; i < ds.size(); i++) {
			SinhVien sv = ds.get(i);
			System.out.print(sv.getMssv() + " ");
			System.out.print(sv.getTaiKhoan().getTenTaiKhoan() + " ");
			System.out.print(sv.getHoTen() + " ");
			System.out.print(sv.getGioiTinh() + " ");
			System.out.println(sv.getNgSinh() + " ");

		}

		SinhVien sv = SinhVienDAO.layThongTinSinhVien("19120002");
		if (sv != null) {
			TaiKhoan tk = sv.getTaiKhoan();
			System.out.println(tk.getTenTaiKhoan() + " " + tk.getMatKhau() + " " + tk.getLoai());
		}
	}
}
