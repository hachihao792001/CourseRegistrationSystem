package course_registration_system;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class SinhVienDAO {
	public static SinhVien layThongTinSinhVien(String mssv) {
		SinhVien sv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			sv = (SinhVien) session.get(SinhVien.class, mssv);
			Hibernate.initialize(sv.getTaiKhoan());
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return sv;
	}

	public static List<SinhVien> layDanhSachSinhVien() {
		List<SinhVien> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select sv from SinhVien sv";
			Query query = session.createQuery(hql);
			ds = query.list();
			for (SinhVien sv : ds)
				Hibernate.initialize(sv.getTaiKhoan());

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
}
