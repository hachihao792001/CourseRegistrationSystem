package daos;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.*;

public class HocKiHienTaiDAO {

	public static HocKiHienTai layThongTinHocKiHienTai() {
		HocKiHienTai hk = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select hk from HocKiHienTai hk";
			@SuppressWarnings("unchecked")
			Query<HocKiHienTai> query = session.createQuery(hql);
			hk = query.list().get(0);
			Hibernate.initialize(hk.getHk());
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return hk;
	}

	public static boolean capNhatThongTinHocKiHienTai(HocKiHienTai hkht) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (layThongTinHocKiHienTai() == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(hkht);
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

}
