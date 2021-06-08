package daos;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;
import pojo.Hoc.HocID;

public class HocDAO {
	public static Hoc layThongTinHoc(HocID maHoc) {
		Hoc h = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			h = (Hoc) session.get(Hoc.class, maHoc);
			if (h != null) {
				Hibernate.initialize(h.getHocID().getSinhVien());
				Hibernate.initialize(h.getHocID().getLopHoc());
			}
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

	public static boolean themHoc(Hoc hoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (HocDAO.layThongTinHoc(hoc.getHocID()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(hoc);
			transaction.commit();

		} catch (HibernateException ex) {
			try {
				transaction.rollback();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}

	public static boolean capNhatThongTinHoc(Hoc hoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (HocDAO.layThongTinHoc(hoc.getHocID()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(hoc);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			try {
				transaction.rollback();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}

	public static boolean xoaHoc(HocID maHoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Hoc hoc = HocDAO.layThongTinHoc(maHoc);
		if (hoc == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(hoc);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}
}
