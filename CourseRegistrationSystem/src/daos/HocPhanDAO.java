package daos;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;

public class HocPhanDAO {
	public static HocPhan layThongTinHocPhan(int maHP) {
		HocPhan hp = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			hp = (HocPhan) session.get(HocPhan.class, maHP);
			if (hp != null) {
				Hibernate.initialize(hp.getMonHoc());
				Hibernate.initialize(hp.getGvlt());
				Hibernate.initialize(hp.getKyDKHP());
			}
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return hp;
	}

	public static List<HocPhan> layDanhSachHocPhan() {
		List<HocPhan> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select hp from HocPhan hp";
			@SuppressWarnings("unchecked")
			Query<HocPhan> query = session.createQuery(hql);
			ds = query.list();
			for (HocPhan hp : ds) {
				Hibernate.initialize(hp.getMonHoc());
				Hibernate.initialize(hp.getGvlt());
				Hibernate.initialize(hp.getKyDKHP());
			}

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}

	public static boolean themHocPhan(HocPhan hp) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (HocPhanDAO.layThongTinHocPhan(hp.getMaHP()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(hp);
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

	public static boolean capNhatThongTinHocPhan(HocPhan hp) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (HocPhanDAO.layThongTinHocPhan(hp.getMaHP()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(hp);
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

	public static boolean xoaHocPhan(int maHocPhan) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HocPhan hp = HocPhanDAO.layThongTinHocPhan(maHocPhan);
		if (hp == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(hp);
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
		List<HocPhan> ds = layDanhSachHocPhan();
		Object[][] data = new Object[ds.size()][8];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getMonHoc().getMaMH();
			data[i][1] = ds.get(i).getMonHoc().getTenMH();
			data[i][2] = ds.get(i).getMonHoc().getSoTinChi();
			data[i][3] = ds.get(i).getGvlt().getTenGV();
			data[i][4] = ds.get(i).getTenPhong();
			data[i][5] = ds.get(i).getThu();
			data[i][6] = ds.get(i).getCa();
			data[i][7] = ds.get(i).getSlotToiDa();
		}

		return data;
	}

	public static Object[][] getObjectMatrix(List<HocPhan> ds) {
		Object[][] data = new Object[ds.size()][8];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getMonHoc().getMaMH();
			data[i][1] = ds.get(i).getMonHoc().getTenMH();
			data[i][2] = ds.get(i).getMonHoc().getSoTinChi();
			data[i][3] = ds.get(i).getGvlt().getTenGV();
			data[i][4] = ds.get(i).getTenPhong();
			data[i][5] = ds.get(i).getThu();
			data[i][6] = ds.get(i).getCa();
			data[i][7] = ds.get(i).getSlotToiDa();
		}

		return data;
	}
}
