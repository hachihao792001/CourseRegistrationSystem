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
	
	public static Object[][] getObjectMatrix() {
		List<GiaoVu> ds = layDanhSachGiaoVu();
		Object[][] data = new Object[ds.size()][4];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getTaiKhoan().getTenTaiKhoan();
			data[i][1] = ds.get(i).getTenGiaoVu();
			data[i][2] = ds.get(i).getGioiTinh();
			data[i][3] = ds.get(i).getNgSinh();
		}

		return data;
	}
}
