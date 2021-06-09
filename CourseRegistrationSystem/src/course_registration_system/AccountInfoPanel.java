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

	JList<String> theJList;
	TaiKhoanPanel taiKhoanPanel;

	public AccountInfoPanel(TaiKhoan tk, TaiKhoanPanel taiKhoanPanel) {
		this(tk);
		this.taiKhoanPanel = taiKhoanPanel;
	}

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

		if (tk.getLoai().equals("GV")) {
			gvu = TaiKhoanDAO.layGiaoVu(tk.getTenTaiKhoan());
			theJList = new JList<String>(
					new String[] { "Tài khoản: " + gvu.getTaiKhoan().getTenTaiKhoan(), "Mã giáo vụ: " + gvu.getMaGVu(),
							"Tên giáo vụ: " + gvu.getTenGiaoVu(), "Giới tính: " + gvu.getGioiTinh(),
							"Ngày sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(gvu.getNgSinh()) });

		} else {
			sv = TaiKhoanDAO.laySinhVien(tk);
			theJList = new JList<String>(new String[] { "Tài khoản: " + sv.getTaiKhoan().getTenTaiKhoan(),
					"Mã số sinh viên: " + sv.getMssv(), "Họ tên: " + sv.getHoTen(), "Giới tính: " + sv.getGioiTinh(),
					"Ngày sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(sv.getNgSinh()),
					"Khoa: " + sv.getKhoa() });

		}

		theJList.setFont(new Font("Dialog", Font.PLAIN, 14));
		theJList.setBackground(null);
		theJList.setFixedCellHeight(40);

		gbc = new GBCBuilder(1, 1).setFill(GridBagConstraints.BOTH);
		this.add(theJList, gbc.setGrid(1, 1).setSpan(2, 1).setWeight(1, 0).setInsets(0, 0, 10, 0));
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
			JPasswordField oldPassText = new JPasswordField();
			oldPassText.setPreferredSize(new Dimension(100, 20));
			JLabel newPassLabel = new JLabel("Nhập mật khẩu cũ");
			JPasswordField newPassText = new JPasswordField();
			newPassText.setPreferredSize(new Dimension(100, 20));
			JButton changeButton = new JButton("Đổi");
			changePassDialog.getRootPane().setDefaultButton(changeButton);
			changeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (Main.hash(oldPassText.getPassword()).equals(tk.getMatKhau())) {
						if (String.valueOf(newPassText.getPassword())
								.equals(String.valueOf(oldPassText.getPassword()))) {
							JOptionPane.showMessageDialog(changePassDialog, "Mật khẩu mới không được giống mật khẩu cũ",
									"Lỗi đổi mật khẩu", JOptionPane.WARNING_MESSAGE, null);
						} else {
							tk.setMatKhau(Main.hash(newPassText.getPassword()));
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
			changePassDialog.setLocationRelativeTo(null);
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
			EnterInputDialog updateInfoDialog;
			if (tk.getLoai().equals("GV")) {
				updateInfoDialog = new EnterInputDialog(
						new String[] { "Mã giáo vụ: ", "Tên giáo vụ: ", "Giới tính: ", "Ngày sinh: " },
						new JComponent[] { new JTextField(), new JTextField(),
								new JComboBox<String>(new String[] { "Nam", "Nữ" }),
								new JFormattedTextField(Main.dateFormat) },
						new String[] { "" + gvu.getMaGVu(), gvu.getTenGiaoVu(), gvu.getGioiTinh(),
								Main.dateFormat.format(gvu.getNgSinh()) },
						"Cập nhật thông tin");
			} else {
				updateInfoDialog = new EnterInputDialog(
						new String[] { "Họ tên: ", "Giới tính: ", "Ngày sinh: ", "Khoa: " },
						new JComponent[] { new JTextField(), new JComboBox<String>(new String[] { "Nam", "Nữ" }),
								new JFormattedTextField(Main.dateFormat), new JTextField() },
						new String[] { sv.getHoTen(), sv.getGioiTinh(), Main.dateFormat.format(sv.getNgSinh()),
								sv.getKhoa() },
						"Cập nhật thông tin");
			}

			String[] result = updateInfoDialog.showDialog();
			if (result != null) {
				try {
					if (tk.getLoai().equals("GV")) {
						gvu.setMaGVu(Integer.parseInt(result[0]));
						gvu.setTenGiaoVu(result[1]);
						gvu.setGioiTinh(result[2]);
						gvu.setNgSinh(new SimpleDateFormat("dd/MM/yyyy").parse((result[3])));

						GiaoVuDAO.capNhatThongTinGiaoVu(gvu);
						theJList.setListData(new String[] { "Tài khoản: " + gvu.getTaiKhoan().getTenTaiKhoan(),
								"Mã giáo vụ: " + gvu.getMaGVu(), "Tên giáo vụ: " + gvu.getTenGiaoVu(),
								"Giới tính: " + gvu.getGioiTinh(),
								"Ngày sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(gvu.getNgSinh()) });
						taiKhoanPanel.listPanel.updateTable(GiaoVuDAO.getObjectMatrix());

					} else {
						sv.setHoTen(result[0]);
						sv.setGioiTinh(result[1]);
						sv.setNgSinh(Main.dateFormat.parse((result[2])));
						sv.setKhoa(result[3]);

						SinhVienDAO.capNhatThongTinSinhVien(sv);
						theJList.setListData(new String[] { "Tài khoản: " + sv.getTaiKhoan().getTenTaiKhoan(),
								"Mã số sinh viên: " + sv.getMssv(), "Họ tên: " + sv.getHoTen(),
								"Giới tính: " + sv.getGioiTinh(),
								"Ngày sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(sv.getNgSinh()),
								"Khoa: " + sv.getKhoa() });
					}

					JOptionPane.showMessageDialog(updateInfoDialog, "Cập nhật thông tin thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE, null);

				} catch (ParseException ex) {
					JOptionPane.showMessageDialog(updateInfoDialog, "Sai định dạng ngày", "Lỗi cập nhật thông tin",
							JOptionPane.WARNING_MESSAGE, null);
				}
			}

			break;
		}
		}

	}

}
