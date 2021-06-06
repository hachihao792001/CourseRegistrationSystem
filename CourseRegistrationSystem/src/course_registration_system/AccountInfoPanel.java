package course_registration_system;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;

import daos.GiaoVuDAO;
import daos.SinhVienDAO;
import daos.TaiKhoanDAO;
import pojo.*;

public class AccountInfoPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	TaiKhoan tk;
	GiaoVu gvu;
	SinhVien sv;

	public AccountInfoPanel(TaiKhoan tk) {
		this.setLayout(new GridBagLayout());
		this.tk = tk;
		this.setBorder(BorderFactory.createTitledBorder("Thông tin tài khoản"));

		GBCBuilder gbc;
		JButton doiMKButton = new JButton("Đổi mật khẩu");
		doiMKButton.setActionCommand("pass");
		doiMKButton.addActionListener(this);
		JButton capNhatThongTinButton = new JButton("Cập nhật thông tin");
		capNhatThongTinButton.setActionCommand("update");
		capNhatThongTinButton.addActionListener(this);
		JButton dangXuatButton = new JButton("Đăng xuất");
		dangXuatButton.setActionCommand("sign out");
		dangXuatButton.addActionListener(this);

		JList<String> accountInfoElementList;

		if (tk.getLoai().equals("GV")) {
			gvu = TaiKhoanDAO.layGiaoVu(tk);
			accountInfoElementList = new JList<String>(
					new String[] { "Tài khoản: " + gvu.getTaiKhoan().getTenTaiKhoan(), "Mã giáo vụ: " + gvu.getMaGVu(),
							"Tên giáo vụ: " + gvu.getTenGiaoVu(), "Giới tính: " + gvu.getGioiTinh(),
							"Ngày sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(gvu.getNgSinh()) });

		} else {
			sv = TaiKhoanDAO.laySinhVien(tk);
			accountInfoElementList = new JList<String>(new String[] { "Tài khoản: " + sv.getTaiKhoan().getTenTaiKhoan(),
					"Mã số sinh viên: " + sv.getMssv(), "Họ tên: " + sv.getHoTen(), "Giới tính: " + sv.getGioiTinh(),
					"Ngày sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(sv.getNgSinh()),
					"Khoa: " + sv.getKhoa() });

		}

		accountInfoElementList.setFont(new Font("Dialog", Font.PLAIN, 14));
		accountInfoElementList.setBackground(null);
		accountInfoElementList.setFixedCellHeight(40);

		gbc = new GBCBuilder(1, 1).setFill(GridBagConstraints.BOTH);
		this.add(accountInfoElementList, gbc.setGrid(1, 1).setSpan(2, 1).setWeight(1, 0).setInsets(0, 0, 10, 0));
		this.add(doiMKButton, gbc.setGrid(1, 2).setSpan(1, 1));
		this.add(dangXuatButton, gbc.setGrid(2, 2));
		this.add(capNhatThongTinButton, gbc.setGrid(1, 3).setSpan(2, 1));
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

		case "update": {
			ModifyDialog modifyDialog;
			if (tk.getLoai().equals("GV")) {
				modifyDialog = new ModifyDialog(
						new String[] { "Mã giáo vụ: ", "Tên giáo vụ: ", "Giới tính: ", "Ngày sinh: " },
						new String[] { "" + gvu.getMaGVu(), gvu.getTenGiaoVu(), gvu.getGioiTinh(),
								new SimpleDateFormat("dd/MM/yyyy").format(gvu.getNgSinh()) });
			} else {
				modifyDialog = new ModifyDialog(
						new String[] { "Mã số sinh viên: ", "Họ tên: ", "Giới tính: ", "Ngày sinh: ", "Khoa: " },
						new String[] { "" + sv.getMssv(), sv.getHoTen(), sv.getGioiTinh(),
								new SimpleDateFormat("dd/MM/yyyy").format(sv.getNgSinh()), sv.getKhoa() });
			}

			String[] result = modifyDialog.showDialog();
			if (result != null) {
				try {
					if (tk.getLoai().equals("GV")) {
						gvu.setMaGVu(Integer.parseInt(result[0]));
						gvu.setTenGiaoVu(result[1]);
						gvu.setGioiTinh(result[2]);
						gvu.setNgSinh(new SimpleDateFormat("dd/MM/yyyy").parse((result[3])));

						GiaoVuDAO.capNhatThongTinGiaoVu(gvu);
					} else {
						sv.setMssv(Integer.parseInt(result[0]));
						sv.setHoTen(result[1]);
						sv.setGioiTinh(result[2]);
						sv.setNgSinh(new SimpleDateFormat("dd/MM/yyyy").parse((result[3])));
						sv.setKhoa(result[4]);

						SinhVienDAO.capNhatThongTinSinhVien(sv);
					}

					
					JOptionPane.showMessageDialog(modifyDialog, "Cập nhật thông tin thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE, null);
				} catch (NumberFormatException | ParseException ex) {
					JOptionPane.showMessageDialog(modifyDialog, "Sai định dạng", "Lỗi cập nhật thông tin",
							JOptionPane.WARNING_MESSAGE, null);
				}
			}

			break;
		}
		}

	}

}
