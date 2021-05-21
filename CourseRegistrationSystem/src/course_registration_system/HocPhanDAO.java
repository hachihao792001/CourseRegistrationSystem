package course_registration_system;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class HocPhanDAO {
	public static HocPhan layThongTinHocPhan(String maHP) {
		HocPhan hp = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			hp = (HocPhan) session.get(HocPhan.class, maHP);
			Hibernate.initialize(hp.getMonHoc());
			Hibernate.initialize(hp.getGvlt());
			Hibernate.initialize(hp.getKyDKHP());
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
			String hql = "select sv from HocPhan sv";
			Query query = session.createQuery(hql);
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
}
