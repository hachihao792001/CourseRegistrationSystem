package daos;

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
			@SuppressWarnings("unchecked")
			Query<HocPhan> query = session.createQuery(hql);
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

	public static Object[][] getObjectMatrix() {
		List<HocPhan> ds = layDanhSachHocPhan();
		Object[][] data = new Object[ds.size()][6];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getMonHoc().getMaMH();
			data[i][1] = ds.get(i).getGvlt().getTenGV();
			data[i][2] = ds.get(i).getTenPhong();
			data[i][3] = ds.get(i).getThu();
			data[i][4] = ds.get(i).getCa();
			data[i][5] = ds.get(i).getSlotToiDa();
		}

		return data;
	}
}
