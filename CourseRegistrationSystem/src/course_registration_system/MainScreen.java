package course_registration_system;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import daos.TaiKhoanDAO;
import pojo.TaiKhoan;

public class MainScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTabbedPane giaoVuTabbedPane;

	public MainScreen(TaiKhoan taiKhoan) {
		JPanel mainContent = new JPanel(new CardLayout());

		JPanel accountInfoPanel = new AccountInfoPanel(taiKhoan);

		giaoVuTabbedPane = new JTabbedPane();
		giaoVuTabbedPane.addTab("Tài khoản", new GiaoVuTaiKhoanPanel());
		giaoVuTabbedPane.addTab("Môn học", new GiaoVuMonHocPanel());
		giaoVuTabbedPane.addTab("Học kì", new GiaoVuHocKiPanel());
		giaoVuTabbedPane.addTab("Lớp học", new GiaoVuLopHocPanel());
		giaoVuTabbedPane.addTab("Kỳ đăng ký học phần", new GiaoVuKyDKHPPanel());
		giaoVuTabbedPane.addTab("Học phần", new GiaoVuHocPhanPanel());

		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, accountInfoPanel, giaoVuTabbedPane);
		mainContent.add(mainSplitPane, "giaoVuCard");

		this.setTitle("Phần mềm quản lí học sinh");
		this.setContentPane(mainContent);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
