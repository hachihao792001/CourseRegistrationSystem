package daos;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;

public class SinhVienDAO {
	public static SinhVien layThongTinSinhVien(int mssv) {
		SinhVien sv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			sv = (SinhVien) session.get(SinhVien.class, mssv);
			if (sv != null)
				Hibernate.initialize(sv.getTaiKhoan());
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return sv;
	}

	public static List<SinhVien> layDanhSachSinhVien() {
		List<SinhVien> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select sv from SinhVien sv";
			@SuppressWarnings("unchecked")
			Query<SinhVien> query = session.createQuery(hql);
			ds = query.list();
			for (SinhVien sv : ds)
				Hibernate.initialize(sv.getTaiKhoan());

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}

	public static boolean themSinhVien(SinhVien sv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (SinhVienDAO.layThongTinSinhVien(sv.getMssv()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(sv);
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

	public static boolean capNhatThongTinSinhVien(SinhVien sv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (SinhVienDAO.layThongTinSinhVien(sv.getMssv()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(sv);
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

	public static boolean xoaSinhVien(int maSinhVien) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		SinhVien sv = SinhVienDAO.layThongTinSinhVien(maSinhVien);
		if (sv == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(sv);
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

	public static List<MonHoc> layDanhSachMonHocCoDK(int mssv) {
		List<MonHoc> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select mh from MonHoc as mh, DKHP as dkhp "
					+ "where mh.maMH = dkhp.dkhpID.hocPhan.monHoc.maMH" + " and dkhp.dkhpID.sinhVien.mssv = '" + mssv
					+ "'";
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

	public static Object[][] getObjectMatrix(List<SinhVien> ds) {
		Object[][] data = new Object[ds.size()][5];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getMssv();
			data[i][1] = ds.get(i).getHoTen();
			data[i][2] = ds.get(i).getGioiTinh();
			data[i][3] = new SimpleDateFormat("dd/MM/yyyy").format(ds.get(i).getNgSinh());
			data[i][4] = ds.get(i).getKhoa();
		}

		return data;
	}
}
