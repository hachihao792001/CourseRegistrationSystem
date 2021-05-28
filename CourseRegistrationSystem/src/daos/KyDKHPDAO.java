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
			Hibernate.initialize(kyDKHP.getKyDKHPID().getHocKi());
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
				Hibernate.initialize(kyDKHP.getKyDKHPID().getHocKi());

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}

	public static Object[][] getObjectMatrix() {
		List<KyDKHP> ds = layDanhSachKyDKHP();
		Object[][] data = new Object[ds.size()][5];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getKyDKHPID().getHocKi().getNamHoc();
			data[i][1] = ds.get(i).getKyDKHPID().getHocKi().getTenHocKi();
			data[i][2] = ds.get(i).getKyDKHPID().getLan();
			data[i][3] = ds.get(i).getNgayBatDau();
			data[i][4] = ds.get(i).getNgayKetThuc();
		}

		return data;
	}
}
