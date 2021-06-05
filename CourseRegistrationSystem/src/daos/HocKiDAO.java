package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;

public class HocKiDAO {
	public static HocKi layThongTinHocKi(int maHK) {
		HocKi hk = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			hk = (HocKi) session.get(HocKi.class, maHK);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return hk;
	}

	public static List<HocKi> layDanhSachHocKi() {
		List<HocKi> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select hk from HocKi hk";
			@SuppressWarnings("unchecked")
			Query<HocKi> query = session.createQuery(hql);
			ds = query.list();

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	
	public static boolean themHocKi(HocKi hk) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (HocKiDAO.layThongTinHocKi(hk.getMaHK()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(hk);
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

	public static boolean capNhatThongTinHocKi(HocKi hk) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (HocKiDAO.layThongTinHocKi(hk.getMaHK()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(hk);
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

	public static boolean xoaHocKi(int maHocKi) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HocKi hk = HocKiDAO.layThongTinHocKi(maHocKi);
		if (hk == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(hk);
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

	public static Object[][] getObjectMatrix() {
		List<HocKi> ds = layDanhSachHocKi();
		Object[][] data = new Object[ds.size()][4];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getTenHocKi();
			data[i][1] = ds.get(i).getNamHoc();
			data[i][2] = ds.get(i).getNgayBatDau();
			data[i][3] = ds.get(i).getNgayKetThuc();
		}

		return data;
	}
}
