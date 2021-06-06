package daos;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;
import pojo.KyDKHP.KyDKHPID;

public class KyDKHPDAO {
	public static KyDKHP layThongTinKyDKHP(KyDKHPID maKyDKHP) {
		KyDKHP kyDKHP = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			kyDKHP = (KyDKHP) session.get(KyDKHP.class, maKyDKHP);
			Hibernate.initialize(kyDKHP.getKyDKHPID().getHocKi());
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return kyDKHP;
	}

	public static List<KyDKHP> layDanhSachKyDKHP() {
		List<KyDKHP> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select hk from KyDKHP hk";
			@SuppressWarnings("unchecked")
			Query<KyDKHP> query = session.createQuery(hql);
			ds = query.list();
			for (KyDKHP kyDKHP : ds)
				Hibernate.initialize(kyDKHP.getKyDKHPID().getHocKi());

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}

	public static boolean themKyDKHP(KyDKHP kyDKHP) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (KyDKHPDAO.layThongTinKyDKHP(kyDKHP.getKyDKHPID()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(kyDKHP);
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

	public static boolean capNhatThongTinKyDKHP(KyDKHP kyDKHP) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (KyDKHPDAO.layThongTinKyDKHP(kyDKHP.getKyDKHPID()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(kyDKHP);
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

	public static boolean xoaKyDKHP(KyDKHPID maKyDKHP) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		KyDKHP kyDKHP = KyDKHPDAO.layThongTinKyDKHP(maKyDKHP);
		if (kyDKHP == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(kyDKHP);
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
		List<KyDKHP> ds = layDanhSachKyDKHP();
		Object[][] data = new Object[ds.size()][5];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getKyDKHPID().getHocKi().getNamHoc();
			data[i][1] = ds.get(i).getKyDKHPID().getHocKi().getTenHocKi();
			data[i][2] = ds.get(i).getKyDKHPID().getLan();
			data[i][3] = new SimpleDateFormat("dd/MM/yyyy").format(ds.get(i).getNgayBatDau());
			data[i][4] = new SimpleDateFormat("dd/MM/yyyy").format(ds.get(i).getNgayKetThuc());
		}

		return data;
	}
}
