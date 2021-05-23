package daos;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class GiaoVienDAO {
	public static GiaoVien layThongTinGiaoVien(String maGV) {
		GiaoVien gv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			gv = (GiaoVien) session.get(GiaoVien.class, maGV);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return gv;
	}

	public static List<GiaoVien> layDanhSachGiaoVien() {
		List<GiaoVien> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select sv from GiaoVien sv";
			Query query = session.createQuery(hql);
			ds = query.list();

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
}
