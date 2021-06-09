package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;

public class MonHocDAO {
	public static MonHoc layThongTinMonHoc(String maMH) {
		MonHoc sv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			sv = (MonHoc) session.get(MonHoc.class, maMH);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return sv;
	}

	public static List<MonHoc> layDanhSachMonHoc() {
		List<MonHoc> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select tk from MonHoc tk";
			@SuppressWarnings("unchecked")
			Query<MonHoc> query = session.createQuery(hql);
			ds = query.list();
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}

	public static String[] layDanhSachTenMonHoc() {
		List<MonHoc> dsMonHoc = layDanhSachMonHoc();
		String[] dsTenMonHoc = new String[dsMonHoc.size()];
		for (int i = 0; i < dsMonHoc.size(); i++)
			dsTenMonHoc[i] = dsMonHoc.get(i).getTenMH();

		return dsTenMonHoc;
	}

	public static boolean themMonHoc(MonHoc mh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (MonHocDAO.layThongTinMonHoc(mh.getMaMH()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(mh);
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

	public static boolean capNhatThongTinMonHoc(MonHoc mh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (MonHocDAO.layThongTinMonHoc(mh.getMaMH()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(mh);
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

	public static boolean xoaMonHoc(String maMonHoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		MonHoc mh = MonHocDAO.layThongTinMonHoc(maMonHoc);
		if (mh == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(mh);
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
		List<MonHoc> ds = layDanhSachMonHoc();
		Object[][] data = new Object[ds.size()][3];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getMaMH();
			data[i][1] = ds.get(i).getTenMH();
			data[i][2] = ds.get(i).getSoTinChi();
		}

		return data;
	}
}
