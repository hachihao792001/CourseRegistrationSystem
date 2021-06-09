package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import daos.HocDAO;
import daos.LopHocDAO;
import daos.SinhVienDAO;
import daos.TaiKhoanDAO;
import pojo.Hoc;
import pojo.LopHoc;
import pojo.MonHoc;
import pojo.SinhVien;
import pojo.TaiKhoan;

public class DSSVDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	ListPanel listPanel;
	InfoPanel infoPanel;
	LopHoc lopHoc;

	public DSSVDialog(LopHoc lopHoc) {
		this.lopHoc = lopHoc;
		JPanel dssvContent = new JPanel(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(
				new String[] { "Mã số sinh viên: ", "Họ tên: ", "Giới tính: ", "Ngày sinh: ", "Khoa: " },
				new String[] { "Cập nhật thông tin", "Reset mật khẩu" }, this);
		infoPanel.updateInfo();

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new ListPanel(infoPanel,
				SinhVienDAO.getObjectMatrix(LopHocDAO.layDanhSachSinhVien(lopHoc.getMaLop())),
				new String[] { "Mã số sinh viên", "Họ tên", "Giới tính", "Ngày sinh", "Khoa" },
				new String[] { "Tìm sinh viên", "Thêm sinh viên vào lớp" }, this,
				"sinh viên trong lớp " + lopHoc.getMaLop());

		// ------------------------ DANH SACH MON HOC PANEL-----------------------------
		JPanel dsmhPanel = new JPanel(new GridBagLayout());
		dsmhPanel.setBorder(BorderFactory.createTitledBorder("Những môn học có đăng ký"));
		List<MonHoc> cacMonHocCoDK = SinhVienDAO
				.layDanhSachMonHocCoDK(LopHocDAO.layDanhSachSinhVien(lopHoc.getMaLop()).get(0).getMssv());
		String[] tenCacMonHocCoDK = new String[cacMonHocCoDK.size()];
		for (int i = 0; i < cacMonHocCoDK.size(); i++)
			tenCacMonHocCoDK[i] = cacMonHocCoDK.get(i).getTenMH();
		JList<String> monHocJList = new JList<String>(tenCacMonHocCoDK);
		JScrollPane monHocScrollPane = new JScrollPane(monHocJList);
		dsmhPanel.add(monHocScrollPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1));

		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightPanel.add(infoPanel, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1));
		rightPanel.add(dsmhPanel, gbc.setGrid(1, 2).setWeight(1, 0).setFill(GridBagConstraints.BOTH));

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, rightPanel);
		dssvContent.add(splitPane, gbc.setGrid(1, 1).setInsets(0).setWeight(1, 1));

		this.setContentPane(dssvContent);
		this.setTitle("Danh sách sinh viên trong lớp " + lopHoc.getMaLop());
		this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Tìm sinh viên": {
			EnterInputDialog timSinhVienDialog = new EnterInputDialog(new String[] { "MSSV cần tìm" },
					new JComponent[] { new JTextField() }, new String[] { "" }, "Tìm sinh viên");
			String[] result = timSinhVienDialog.showDialog();
			if (result == null)
				break;
			int mssvCanTim = Integer.parseInt(result[0]);

			SinhVien svTimDuoc = SinhVienDAO.layThongTinSinhVien(mssvCanTim);
			if (svTimDuoc == null) {
				JOptionPane.showMessageDialog(this, "Sinh viên không tồn tại", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				Object[][] monHocObjectMatrix = SinhVienDAO
						.getObjectMatrix(LopHocDAO.layDanhSachSinhVien(lopHoc.getMaLop()));
				for (int i = 0; i < monHocObjectMatrix.length; i++) {
					if (monHocObjectMatrix[i][0].equals(svTimDuoc.getMssv())) {
						listPanel.setTableSelectedRow(i);
						break;
					}
				}
			}
			break;
		}

		case "Thêm sinh viên vào lớp": {
			JDialog themSVDialog = new JDialog();
			themSVDialog.setTitle("Chọn những học sinh để thêm vào lớp");
			JPanel themSVContent = new JPanel();

			JScrollPane theScrollPane = new JScrollPane();
			JTable theTable = new JTable();
			Object[][] dssvKhongTrongLop = SinhVienDAO
					.getObjectMatrix(LopHocDAO.layDanhSachSinhVienKhongTrongLop(this.lopHoc.getMaLop()));
			theTable.setModel(new DefaultTableModel(dssvKhongTrongLop,
					new String[] { "Mã số sinh viên", "Họ tên", "Giới tính", "Ngày sinh", "Khoa" }));
			theTable.setRowHeight(30);
			theScrollPane.setViewportView(theTable);

			JButton themButton = new JButton("Thêm");
			themButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int[] selectedRows = theTable.getSelectedRows();
					for (int i = 0; i < selectedRows.length; i++) {
						SinhVien selectedSV = SinhVienDAO.layThongTinSinhVien(
								Integer.parseInt(dssvKhongTrongLop[selectedRows[i]][0].toString()));
						Hoc newHoc = new Hoc(new Hoc.HocID(selectedSV, lopHoc));
						HocDAO.themHoc(newHoc);
					}

					listPanel.theTable.setModel(new DefaultTableModel(
							SinhVienDAO.getObjectMatrix(LopHocDAO.layDanhSachSinhVien(lopHoc.getMaLop())),
							new String[] { "Mã số sinh viên", "Họ tên", "Giới tính", "Ngày sinh", "Khoa" }));

					themSVDialog.setVisible(false);
					themSVDialog.dispose();
				}
			});

			themSVContent.add(theScrollPane);
			themSVContent.add(themButton);
			themSVDialog.setContentPane(themSVContent);
			getRootPane().setDefaultButton(themButton);
			themSVDialog.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
			themSVDialog.pack();
			themSVDialog.setVisible(true);
			break;
		}

		case "Cập nhật thông tin": {
			int mssvCanCapNhat = Integer.parseInt(infoPanel.elementDatas[0]);
			SinhVien selectedSV = SinhVienDAO.layThongTinSinhVien(mssvCanCapNhat);

			EnterInputDialog capNhatThongTinDialog;
			capNhatThongTinDialog = new EnterInputDialog(
					new String[] { "Mã số sinh viên: ", "Họ tên: ", "Giới tính: ", "Ngày sinh: ", "Khoa: " },
					new JComponent[] { new JTextField(), new JTextField(),
							new JComboBox<String>(new String[] { "Nam", "Nữ" }),
							new JFormattedTextField(Main.dateFormat), new JTextField() },
					new String[] { "" + selectedSV.getMssv(), selectedSV.getHoTen(), selectedSV.getGioiTinh(),
							new SimpleDateFormat("dd/mm/yyyy").format(selectedSV.getNgSinh()), selectedSV.getKhoa() },
					"Cập nhật thông tin");

			String[] result = capNhatThongTinDialog.showDialog();
			if (result != null) {
				try {
					selectedSV.setMssv(Integer.parseInt(result[0]));
					selectedSV.setHoTen(result[1]);
					selectedSV.setGioiTinh(result[2]);
					selectedSV.setNgSinh(new SimpleDateFormat("dd/mm/yyyy").parse(result[3]));
					selectedSV.setKhoa(result[4]);

					SinhVienDAO.capNhatThongTinSinhVien(selectedSV);
					listPanel
							.updateTable(SinhVienDAO.getObjectMatrix(LopHocDAO.layDanhSachSinhVien(lopHoc.getMaLop())));

					JOptionPane.showMessageDialog(capNhatThongTinDialog, "Cập nhật thông tin thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE, null);

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(capNhatThongTinDialog, "MSSV phải là 1 số", "Lỗi cập nhật thông tin",
							JOptionPane.WARNING_MESSAGE, null);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(capNhatThongTinDialog, "Ngày sinh sai định dạng",
							"Lỗi cập nhật thông tin", JOptionPane.WARNING_MESSAGE, null);
				}
			}
			break;
		}
		case "Reset mật khẩu": {
			List<SinhVien> dssvTrongLop = LopHocDAO.layDanhSachSinhVien(lopHoc.getMaLop());
			SinhVien selectedSinhVien = dssvTrongLop.get(listPanel.theTable.getSelectedRow());
			String taiKhoanCanReset = selectedSinhVien.getTaiKhoan().getTenTaiKhoan();
			if (JOptionPane.showConfirmDialog(this,
					"Bạn có chắc muốn reset mật khẩu cho sinh viên " + selectedSinhVien.getHoTen(), "Reset mật khẩu",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoan(taiKhoanCanReset);
				tk.setMatKhau("" + selectedSinhVien.getMssv());
				TaiKhoanDAO.capNhatThongTinTaiKhoan(tk);

				JOptionPane.showMessageDialog(this, "Reset mật khẩu thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
			break;
		}

		}
	}
}
