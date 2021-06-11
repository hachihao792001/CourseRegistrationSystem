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
			if (h != null) {
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
		return h;
	}

	public static DKHP layThongTinDKHP(HocPhan hp, SinhVien sv) {
		DKHP h = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = String.format(
					"select dk from DKHP dk where dk.dkhpID.hocPhan.maHP = %d and dk.dkhpID.sinhVien.mssv = %d",
					hp.getMaHP(), sv.getMssv());
			@SuppressWarnings("unchecked")
			Query<DKHP> query = session.createQuery(hql);
			h = query.list().get(0);
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

	public static List<DKHP> layDanhSachDKHP(HocPhan hp) {
		List<DKHP> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select dkhp from DKHP dkhp where dkhp.dkhpID.hocPhan.maHP = " + hp.getMaHP();
			@SuppressWarnings("unchecked")
			Query<DKHP> query = session.createQuery(hql);
			ds = query.list();
			for (DKHP h : ds) {
				Hibernate.initialize(h.getDkhpID().getSinhVien());
				Hibernate.initialize(h.getDkhpID().getHocPhan());
				Hibernate.initialize(h.getDkhpID().getHocPhan().getGvlt());
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

	public static Object[][] getObjectMatrix(List<DKHP> ds) {
		Object[][] data = new Object[ds.size()][8];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getDkhpID().getSinhVien().getMssv();
			data[i][1] = ds.get(i).getDkhpID().getSinhVien().getHoTen();
			data[i][2] = ds.get(i).getDkhpID().getHocPhan().getMonHoc().getMaMH();
			data[i][3] = ds.get(i).getDkhpID().getHocPhan().getMonHoc().getTenMH();
			data[i][4] = ds.get(i).getDkhpID().getHocPhan().getGvlt().getTenGV();
			data[i][5] = ds.get(i).getDkhpID().getHocPhan().getThu();
			data[i][6] = ds.get(i).getDkhpID().getHocPhan().getCa();
			data[i][7] = ds.get(i).getThoiGianDKHP();
		}

		return data;
	}
}
