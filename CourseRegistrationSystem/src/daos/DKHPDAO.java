package daos;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;
import pojo.DKHP.DKHPID;

public class DKHPDAO {
	public static DKHP layThongTinDKHP(DKHPID maDKHP) {
		DKHP h = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			h = (DKHP) session.get(DKHP.class, maDKHP);
			Hibernate.initialize(h.getDkhpID().getSinhVien());
			Hibernate.initialize(h.getDkhpID().getHocPhan());
			Hibernate.initialize(h.getDkhpID().getHocPhan().getMonHoc());
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
			}

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}

	public static boolean themDKHP(DKHP dkhp) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (DKHPDAO.layThongTinDKHP(dkhp.getDkhpID()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(dkhp);
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

	public static boolean capNhatThongTinDKHP(DKHP dkhp) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (DKHPDAO.layThongTinDKHP(dkhp.getDkhpID()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(dkhp);
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

	public static boolean xoaDKHP(DKHPID maDKHP) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		DKHP dkhp = DKHPDAO.layThongTinDKHP(maDKHP);
		if (dkhp == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(dkhp);
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
