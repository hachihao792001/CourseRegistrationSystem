package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;

public class TaiKhoanDAO {
	public static List<TaiKhoan> layDanhSachTaiKhoan() {
		List<TaiKhoan> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select tk from TaiKhoan tk";
			@SuppressWarnings("unchecked")
			Query<TaiKhoan> query = session.createQuery(hql);
			ds = query.list();
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}

	public static TaiKhoan layThongTinTaiKhoan(String tenTK) {
		TaiKhoan tk = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			tk = (TaiKhoan) session.get(TaiKhoan.class, tenTK);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return tk;
	}

	public static boolean themTaiKhoan(TaiKhoan tk) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (TaiKhoanDAO.layThongTinTaiKhoan(tk.getTenTaiKhoan()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(tk);
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

	public static boolean capNhatThongTinTaiKhoan(TaiKhoan tk) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (TaiKhoanDAO.layThongTinTaiKhoan(tk.getTenTaiKhoan()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(tk);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			try {
				transaction.rollback();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}

	public static boolean xoaTaiKhoan(String maTaiKhoan) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoan(maTaiKhoan);
		if (tk == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(tk);
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

	public static GiaoVu layGiaoVu(String tenTaiKhoan) {
		GiaoVu gv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select gv " + "from GiaoVu gv, TaiKhoan tk " + "where tk.tenTaiKhoan = '"
					+ tenTaiKhoan + "' and" + " gv.taiKhoan.tenTaiKhoan = tk.tenTaiKhoan";
			@SuppressWarnings("unchecked")
			Query<GiaoVu> query = session.createQuery(hql);
			gv = query.getResultList().get(0);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return gv;
	}

	public static SinhVien laySinhVien(TaiKhoan taiKhoan) {
		SinhVien sv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select sv " + "from SinhVien sv, TaiKhoan tk " + "where tk.tenTaiKhoan = '"
					+ taiKhoan.getTenTaiKhoan() + "' and " + "sv.taiKhoan.tenTaiKhoan = tk.tenTaiKhoan";
			@SuppressWarnings("unchecked")
			Query<SinhVien> query = session.createQuery(hql);
			sv = query.getResultList().get(0);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return sv;
	}
}
