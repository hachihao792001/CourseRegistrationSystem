package daos;

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
			@SuppressWarnings("unchecked")
			Query<TaiKhoan> query = session.createQuery(hql);
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
		List<TaiKhoan> ds = layDanhSachTaiKhoan();
		Object[][] data = new Object[ds.size()][3];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = ds.get(i).getTenTaiKhoan();
			data[i][1] = ds.get(i).getMatKhau();
			data[i][2] = ds.get(i).getLoai();
		}

		return data;
	}
}
