package daos;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class KyDKHPDAO {
	public static KyDKHP layThongTinKyDKHP(String maKyDKHP) {
		KyDKHP kyDKHP = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			kyDKHP = (KyDKHP) session.get(KyDKHP.class, maKyDKHP);
			Hibernate.initialize(kyDKHP.getHocKi());
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
				Hibernate.initialize(kyDKHP.getHocKi());

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
}
