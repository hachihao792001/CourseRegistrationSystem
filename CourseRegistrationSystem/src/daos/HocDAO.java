package daos;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class HocDAO {
	public static Hoc layThongTinHoc(String maHoc) {
		Hoc h = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			h = (Hoc) session.get(Hoc.class, maHoc);
			Hibernate.initialize(h.getHocID().getSinhVien());
			Hibernate.initialize(h.getHocID().getLopHoc());
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return h;
	}

	public static List<Hoc> layDanhSachHoc() {
		List<Hoc> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select sv from Hoc sv";
			@SuppressWarnings("unchecked")
			Query<Hoc> query = session.createQuery(hql);
			ds = query.list();
			for (Hoc h : ds) {
				Hibernate.initialize(h.getHocID().getSinhVien());
				Hibernate.initialize(h.getHocID().getLopHoc());
			}

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
}
