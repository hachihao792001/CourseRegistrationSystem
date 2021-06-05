package course_registration_system;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import daos.TaiKhoanDAO;
import pojo.*;

public class AccountInfoPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	TaiKhoan tk;

	public AccountInfoPanel(TaiKhoan tk) {
		this.setLayout(new GridBagLayout());
		this.tk = tk;
		this.setBorder(BorderFactory.createTitledBorder("Thông tin tài khoản"));

		GBCBuilder gbc;
		JButton doiMKButton = new JButton("Đổi mật khẩu");
		doiMKButton.setActionCommand("pass");
		doiMKButton.addActionListener(this);
		JButton dangXuatButton = new JButton("Đăng xuất");
		dangXuatButton.setActionCommand("sign out");
		dangXuatButton.addActionListener(this);

		if (tk.getLoai().equals("GV")) {

			GiaoVu gvu = TaiKhoanDAO.layGiaoVu(tk);
			JList<String> accountInfoElementList = new JList<String>(
					new String[] { "Tài khoản: " + gvu.getTaiKhoan().getTenTaiKhoan(), "Mã giáo vụ: " + gvu.getMaGVu(),
							"Tên giáo vụ: " + gvu.getTenGiaoVu(), "Giới tính: " + gvu.getGioiTinh(),
							"Ngày sinh: " + gvu.getNgSinh() });
			accountInfoElementList.setFont(new Font("Dialog", Font.BOLD, 14));
			accountInfoElementList.setBackground(null);
			accountInfoElementList.setFixedCellHeight(40);
			
			gbc = new GBCBuilder(1, 1).setFill(GridBagConstraints.BOTH);
			this.add(accountInfoElementList, gbc.setGrid(1, 1).setSpan(2, 1).setWeight(1, 0).setInsets(0, 0, 10, 0));
			this.add(doiMKButton, gbc.setGrid(1, 2).setSpan(1, 1));
			this.add(dangXuatButton, gbc.setGrid(2, 2));
		}

		if (tk.getLoai().equals("SV")) {

			SinhVien sv = TaiKhoanDAO.laySinhVien(tk);
			JLabel taiKhoanLabel = new JLabel("Tài khoản: " + sv.getTaiKhoan().getTenTaiKhoan());
			JLabel maSVLabel = new JLabel("Mã số sinh viên: " + sv.getMssv());
			JLabel hoTenLabel = new JLabel("Họ tên: " + sv.getHoTen());
			JLabel gioiTinhLabel = new JLabel("Giới tính: " + sv.getGioiTinh());
			JLabel ngaySinhLabel = new JLabel("Ngày sinh: " + sv.getNgSinh());
			JLabel khoaLabel = new JLabel("Khoa: " + sv.getKhoa());

			gbc = new GBCBuilder(1, 1).setAnchor(GridBagConstraints.LINE_START).setInsets(5);
			this.add(taiKhoanLabel, gbc);
			this.add(maSVLabel, gbc.setGrid(1, 2));
			this.add(hoTenLabel, gbc.setGrid(1, 3));
			this.add(gioiTinhLabel, gbc.setGrid(1, 4));
			this.add(ngaySinhLabel, gbc.setGrid(1, 5));
			this.add(khoaLabel, gbc.setGrid(1, 6));
			this.add(doiMKButton, gbc.setGrid(1, 7));
			this.add(dangXuatButton, gbc.setGrid(1, 8));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "pass":
			JDialog changePassDialog = new JDialog();
			changePassDialog.setTitle("Đổi mật khẩu");
			JPanel changePassDialogContent = new JPanel(new GridBagLayout());

			JLabel oldPassLabel = new JLabel("Nhập mật khẩu cũ");
			JTextField oldPassText = new JTextField();
			oldPassText.setPreferredSize(new Dimension(100, 20));
			JLabel newPassLabel = new JLabel("Nhập mật khẩu cũ");
			JTextField newPassText = new JTextField();
			newPassText.setPreferredSize(new Dimension(100, 20));
			JButton changeButton = new JButton("Đổi");
			changeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (oldPassText.getText().equals(tk.getMatKhau())) {
						if (newPassText.getText().equals(oldPassText.getText())) {
							JOptionPane.showMessageDialog(changePassDialog, "Mật khẩu mới không được giống mật khẩu cũ",
									"Lỗi đổi mật khẩu", JOptionPane.WARNING_MESSAGE, null);
						} else {
							tk.setMatKhau(newPassText.getText());
							TaiKhoanDAO.capNhatThongTinTaiKhoan(tk);
							JOptionPane.showMessageDialog(changePassDialog, "Đổi mật khẩu thành công!", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE, null);
							changePassDialog.setVisible(false);
							changePassDialog.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(changePassDialog, "Mật khẩu cũ sai", "Lỗi đổi mật khẩu",
								JOptionPane.WARNING_MESSAGE, null);
					}
				}
			});

			GBCBuilder gbc = new GBCBuilder(1, 1).setInsets(5);
			changePassDialogContent.add(oldPassLabel, gbc);
			changePassDialogContent.add(oldPassText, gbc.setGrid(2, 1));
			changePassDialogContent.add(newPassLabel, gbc.setGrid(1, 2));
			changePassDialogContent.add(newPassText, gbc.setGrid(2, 2));
			changePassDialogContent.add(changeButton, gbc.setGrid(1, 3).setSpan(2, 1));

			changePassDialog.setContentPane(changePassDialogContent);

			changePassDialog.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
			changePassDialog.pack();
			changePassDialog.setVisible(true);
			break;

		case "sign out": {
			JFrame theMainScreen = (JFrame) SwingUtilities.getRoot(this);
			int confirmDelete = JOptionPane.showConfirmDialog(theMainScreen, "Bạn có chắc muốn đăng xuất?", "Đăng xuất",
					JOptionPane.YES_NO_OPTION);
			if (confirmDelete == JOptionPane.YES_OPTION) {
				theMainScreen.setVisible(false);
				theMainScreen.dispose();
				new LoginScreen();
			}
			break;
		}
		}

	}

}
