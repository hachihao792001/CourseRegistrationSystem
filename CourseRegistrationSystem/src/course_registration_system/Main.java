package course_registration_system;

import java.sql.*;
import java.util.List;

import pojo.GiaoVien;

public class Main {
	public static void main(String args[]) {
		List<GiaoVien> ds = GiaoVienDAO.layDanhSachSinhVien();
		for (int i = 0; i < ds.size(); i++) {
			GiaoVien sv = ds.get(i);
			System.out.print(sv.getMaGV() + " ");
			System.out.print(sv.getHoTen() + " ");
			System.out.print(sv.getNgSinh() + " ");
			System.out.println(sv.getDiaChi() + " ");
		}

	}
}
