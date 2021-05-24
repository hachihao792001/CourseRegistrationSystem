package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class HocKiDAO {
	public static HocKi layThongTinHocKi(String maHK) {
		HocKi hk = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			hk = (HocKi) session.get(HocKi.class, maHK);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return hk;
	}

	public static List<HocKi> layDanhSachHocKi() {
		List<HocKi> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select hk from HocKi hk";
			@SuppressWarnings("unchecked")
			Query<HocKi> query = session.createQuery(hql);
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
