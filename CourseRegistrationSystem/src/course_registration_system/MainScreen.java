package course_registration_system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import daos.HocKiDAO;
import daos.HocKiHienTaiDAO;
import daos.TaiKhoanDAO;
import pojo.HocKi;
import pojo.KyDKHP;
import pojo.SinhVien;
import pojo.TaiKhoan;

public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	public static AccountInfoPanel accountInfoPanel;
	private JTabbedPane mainJTabbedPane;
	public static SinhVien loggedInSV;
	public static TaiKhoan loggedInTK;

	public MainScreen(TaiKhoan taiKhoan) {
		loggedInTK = taiKhoan;
		JPanel mainContent = new JPanel(new GridBagLayout());
		mainJTabbedPane = new JTabbedPane();

		if (taiKhoan.getLoai().equals("GV")) {

			TaiKhoanPanel tkPanel = new TaiKhoanPanel();
			mainJTabbedPane.addTab("Tài khoản", tkPanel);
			mainJTabbedPane.addTab("Môn học", new MonHocPanel());
			mainJTabbedPane.addTab("Học kì", new HocKiPanel());
			mainJTabbedPane.addTab("Lớp học", new LopHocPanel());
			mainJTabbedPane.addTab("Kỳ đăng ký học phần", new KyDKHPPanel());
			mainJTabbedPane.addTab("Học phần", new HocPhanPanel());

			accountInfoPanel = new AccountInfoPanel(taiKhoan, tkPanel);
		} else {
			loggedInSV = TaiKhoanDAO.laySinhVien(taiKhoan);

			boolean trongKyDKHP = false;
			HocKi hocKiHienTai = HocKiHienTaiDAO.layThongTinHocKiHienTai().getHk();
			List<KyDKHP> dsKyDKHP = HocKiDAO.layDanhSachKyDKHPTrongHocKi(hocKiHienTai);
			Date currentDate = new Date();
			for (KyDKHP kyDKHP : dsKyDKHP) {
				if (kyDKHP.getNgayBatDau().before(currentDate) && kyDKHP.getNgayKetThuc().after(currentDate)) {
					trongKyDKHP = true;
					break;
				}
			}

			mainJTabbedPane.addTab("Đăng ký học phần", new SVDKHPPanel(trongKyDKHP));
			mainJTabbedPane.addTab("Kết quả đăng ký học phần", new SVKetQuaDKHPPanel(trongKyDKHP));

			accountInfoPanel = new AccountInfoPanel(taiKhoan);
		}

		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, accountInfoPanel, mainJTabbedPane);
		mainContent.add(mainSplitPane, new GBCBuilder(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1));

		this.setTitle("Phần mềm đăng ký học phần");
		this.setContentPane(mainContent);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
