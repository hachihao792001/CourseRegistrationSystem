package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class MonHocDAO {
	public static MonHoc layThongTinMonHoc(String mssv) {
		MonHoc sv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			sv = (MonHoc) session.get(MonHoc.class, mssv);
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
}
