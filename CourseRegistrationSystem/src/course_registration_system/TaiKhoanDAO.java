package course_registration_system;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class TaiKhoanDAO {
	public static List<TaiKhoan> layDanhSachTaiKhoan() {
		List<TaiKhoan> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select tk from TaiKhoan tk";
			Query query = session.createQuery(hql);
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
