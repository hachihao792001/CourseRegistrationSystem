package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;

public class LopHocDAO {
	public static LopHoc layThongTinLopHoc(String maLop) {
		LopHoc sv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			sv = (LopHoc) session.get(LopHoc.class, maLop);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return sv;
	}

	public static List<LopHoc> layDanhSachLopHoc() {
		List<LopHoc> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select lh from LopHoc lh";
			@SuppressWarnings("unchecked")
			Query<LopHoc> query = session.createQuery(hql);
			ds = query.list();

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	
	public static boolean themLopHoc(LopHoc lh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (LopHocDAO.layThongTinLopHoc(lh.getMaLop()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(lh);
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

	public static boolean capNhatThongTinLopHoc(LopHoc lh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (LopHocDAO.layThongTinLopHoc(lh.getMaLop()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(lh);
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

	public static boolean xoaLopHoc(String maLopHoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		LopHoc lh = LopHocDAO.layThongTinLopHoc(maLopHoc);
		if (lh == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(lh);
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
		List<LopHoc> ds = layDanhSachLopHoc();
		Object[][] data = new Object[ds.size()][4];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getMaLop();
			data[i][1] = ds.get(i).getTongSoSV();
			data[i][2] = ds.get(i).getTongSoNam();
			data[i][3] = ds.get(i).getTongSoNu();
		}

		return data;
	}
}
