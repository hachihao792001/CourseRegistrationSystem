package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;

public class GiaoVienDAO {
	public static GiaoVien layThongTinGiaoVien(int maGV) {
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
			@SuppressWarnings("unchecked")
			Query<GiaoVien> query = session.createQuery(hql);
			ds = query.list();

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	
	public static boolean themGiaoVien(GiaoVien gv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (GiaoVienDAO.layThongTinGiaoVien(gv.getMaGV()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(gv);
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

	public static boolean capNhatThongTinGiaoVien(GiaoVien gv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (GiaoVienDAO.layThongTinGiaoVien(gv.getMaGV()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(gv);
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

	public static boolean xoaGiaoVien(int maGiaoVien) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		GiaoVien gv = GiaoVienDAO.layThongTinGiaoVien(maGiaoVien);
		if (gv == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(gv);
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
