package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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
			String hql = "select sv from LopHoc sv";
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
}
