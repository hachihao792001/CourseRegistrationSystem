package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.*;

public class LopHocDAO {
	public static LopHoc layThongTinLopHoc(String maLop) {
		LopHoc sv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			sv = (LopHoc) session.get(LopHoc.class, maLop);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return sv;
	}

	public static List<LopHoc> layDanhSachLopHoc() {
		List<LopHoc> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select sv from LopHoc sv";
			@SuppressWarnings("unchecked")
			Query<LopHoc> query = session.createQuery(hql);
			ds = query.list();

		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	
	public static Object[][] getObjectMatrix() {
		List<LopHoc> ds = layDanhSachLopHoc();
		Object[][] data = new Object[ds.size()][4];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getMaLop();
			data[i][1] = ds.get(i).getTongSoSV();
			data[i][2] = ds.get(i).getTongSoNam();
			data[i][3] = ds.get(i).getTongSoNu();
		}

		return data;
	}
}
