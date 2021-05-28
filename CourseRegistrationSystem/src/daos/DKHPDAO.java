package daos;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class DKHPDAO {
	public static DKHP layThongTinDKHP(String maDKHP) {
		DKHP h = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			h = (DKHP) session.get(DKHP.class, maDKHP);
			Hibernate.initialize(h.getDkhpID().getSinhVien());
			Hibernate.initialize(h.getDkhpID().getHocPhan());
			Hibernate.initialize(h.getDkhpID().getHocPhan().getMonHoc());
			Hibernate.initialize(h.getGvlt());
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return h;
	}

	public static List<DKHP> layDanhSachDKHP() {
		List<DKHP> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select sv from DKHP sv";
			@SuppressWarnings("unchecked")
			Query<DKHP> query = session.createQuery(hql);
			ds = query.list();
			for (DKHP h : ds) {
				Hibernate.initialize(h.getDkhpID().getSinhVien());
				Hibernate.initialize(h.getDkhpID().getHocPhan());
				Hibernate.initialize(h.getDkhpID().getHocPhan().getMonHoc());
				Hibernate.initialize(h.getGvlt());
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
