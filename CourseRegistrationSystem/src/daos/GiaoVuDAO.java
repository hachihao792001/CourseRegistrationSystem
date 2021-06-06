package daos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;

public class GiaoVuDAO {
	public static GiaoVu layThongTinGiaoVu(int maGVu) {
		GiaoVu gv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			gv = (GiaoVu) session.get(GiaoVu.class, maGVu);
			Hibernate.initialize(gv.getTaiKhoan());
		} catch (HibernateException | NullPointerException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return gv;
	}

	public static List<GiaoVu> layDanhSachGiaoVu() {
		List<GiaoVu> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select sv from GiaoVu sv";
			@SuppressWarnings("unchecked")
			Query<GiaoVu> query = session.createQuery(hql);
			ds = query.list();
			for (GiaoVu gv : ds)
				Hibernate.initialize(gv.getTaiKhoan());

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}

	public static boolean themGiaoVu(GiaoVu gvu) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (GiaoVuDAO.layThongTinGiaoVu(gvu.getMaGVu()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(gvu);
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

	public static boolean capNhatThongTinGiaoVu(GiaoVu gvu) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (GiaoVuDAO.layThongTinGiaoVu(gvu.getMaGVu()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(gvu);
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

	public static boolean xoaGiaoVu(int maGiaoVu) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		GiaoVu gvu = GiaoVuDAO.layThongTinGiaoVu(maGiaoVu);
		if (gvu == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(gvu);
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
		List<GiaoVu> ds = layDanhSachGiaoVu();
		Object[][] data = new Object[ds.size()][4];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getTaiKhoan().getTenTaiKhoan();
			data[i][1] = ds.get(i).getTenGiaoVu();
			data[i][2] = ds.get(i).getGioiTinh();
			data[i][3] = new SimpleDateFormat("dd/MM/yyyy").format(ds.get(i).getNgSinh());
		}

		return data;
	}
}
