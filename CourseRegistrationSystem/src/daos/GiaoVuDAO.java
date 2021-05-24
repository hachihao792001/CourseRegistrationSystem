package daos;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class GiaoVuDAO {
	public static GiaoVu layThongTinGiaoVu(String maGVu) {
		GiaoVu gv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			gv = (GiaoVu) session.get(GiaoVu.class, maGVu);
			Hibernate.initialize(gv.getTaiKhoan());
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return gv;
	}

	public static List<GiaoVu> layDanhSachGiaoVu() {
		List<GiaoVu> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select sv from GiaoVu sv";
			@SuppressWarnings("unchecked")
			Query<GiaoVu> query = session.createQuery(hql);
			ds = query.list();
			for (GiaoVu gv : ds)
				Hibernate.initialize(gv.getTaiKhoan());

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
}
