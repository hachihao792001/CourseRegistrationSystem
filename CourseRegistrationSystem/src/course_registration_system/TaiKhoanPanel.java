package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import org.hibernate.exception.GenericJDBCException;

import daos.GiaoVuDAO;
import daos.TaiKhoanDAO;
import pojo.GiaoVu;
import pojo.TaiKhoan;

public class TaiKhoanPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public ListPanel listPanel;
	InfoPanel infoPanel;

	public TaiKhoanPanel() {

		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new String[] { "Tài khoản: ", "Tên giáo vụ: ", "Giới tính: ", "Ngày sinh: " },
				new String[] { "Cập nhật thông tin tài khoản", "Reset mật khẩu", "Xoá tài khoản" }, this);
		infoPanel.updateInfo();

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new ListPanel(infoPanel, GiaoVuDAO.getObjectMatrix(),
				new String[] { "Tài khoản", "Tên giáo vụ", "Giới tính", "Ngày sinh" },
				new String[] { "Tìm tài khoản", "Thêm tài khoản" }, this, "tài khoản");

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, infoPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Tìm tài khoản": {
			MultiTextFieldDialog timTaiKhoanDialog = new MultiTextFieldDialog(new String[] { "Tên tài khoản cần tìm" },
					new String[] { "" }, "Tìm tài khoản");
			String tenTaiKhoanCanTim = timTaiKhoanDialog.showDialog()[0];
			if (tenTaiKhoanCanTim == null)
				break;

			TaiKhoan tkTimDuoc = TaiKhoanDAO.layThongTinTaiKhoan(tenTaiKhoanCanTim);
			if (tkTimDuoc == null) {
				JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				Object[][] giaoVuObjectMatrix = GiaoVuDAO.getObjectMatrix();
				for (int i = 0; i < giaoVuObjectMatrix.length; i++) {
					if (giaoVuObjectMatrix[i][0].equals(tkTimDuoc.getTenTaiKhoan())) {
						listPanel.setTableSelectedRow(i);
						break;
					}
				}
			}
			break;
		}

		case "Thêm tài khoản": {
			MultiTextFieldDialog themTaiKhoanDialog = new MultiTextFieldDialog(
					new String[] { "Tài khoản", "Tên giáo vụ", "Giới tính", "Ngày sinh" }, new String[4],
					"Thêm tài khoản");
			String[] thongTinTaiKhoanMoi = themTaiKhoanDialog.showDialog();
			if (thongTinTaiKhoanMoi == null)
				break;

			if (TaiKhoanDAO.layThongTinTaiKhoan(thongTinTaiKhoanMoi[0]) != null)
				JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại", "Lỗi thêm tài khoản giáo vụ",
						JOptionPane.ERROR_MESSAGE, null);
			else {

				try {
					TaiKhoan taiKhoanMoi = new TaiKhoan();
					taiKhoanMoi.setTenTaiKhoan(thongTinTaiKhoanMoi[0]);
					taiKhoanMoi.setMatKhau("123");
					taiKhoanMoi.setLoai("GV");

					GiaoVu newGiaoVu = new GiaoVu();
					newGiaoVu.setTaiKhoan(taiKhoanMoi);
					newGiaoVu.setTenGiaoVu(thongTinTaiKhoanMoi[1]);
					newGiaoVu.setGioiTinh(thongTinTaiKhoanMoi[2]);
					newGiaoVu.setNgSinh(new SimpleDateFormat("dd/MM/yyyy").parse(thongTinTaiKhoanMoi[3]));

					TaiKhoanDAO.themTaiKhoan(taiKhoanMoi);
					GiaoVuDAO.themGiaoVu(newGiaoVu);

					listPanel.updateTable(GiaoVuDAO.getObjectMatrix());
					listPanel.setTableSelectedRow(listPanel.theTable.getRowCount() - 1);

					JOptionPane.showMessageDialog(this, "Thêm giáo vụ thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE, null);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng", "Lỗi thêm tài khoản giáo vụ",
							JOptionPane.WARNING_MESSAGE, null);
				} catch (GenericJDBCException e2) {
					JOptionPane.showMessageDialog(this, "Giới tính phải là nam hoặc nữ", "Lỗi thêm tài khoản giáo vụ",
							JOptionPane.WARNING_MESSAGE, null);
				}
			}
			break;
		}

		case "Cập nhật thông tin tài khoản": {

			String taiKhoanCanCapNhat = listPanel.theTable.getValueAt(listPanel.theTable.getSelectedRow(), 0)
					.toString();
			GiaoVu selectedGiaoVu = TaiKhoanDAO.layGiaoVu(taiKhoanCanCapNhat);

			MultiTextFieldDialog capNhatThongTinDialog;
			capNhatThongTinDialog = new MultiTextFieldDialog(
					new String[] { "Mã giáo vụ: ", "Tên giáo vụ: ", "Giới tính: ", "Ngày sinh: " },
					new String[] { "" + selectedGiaoVu.getMaGVu(), selectedGiaoVu.getTenGiaoVu(),
							selectedGiaoVu.getGioiTinh(),
							new SimpleDateFormat("dd/MM/yyyy").format(selectedGiaoVu.getNgSinh()) },
					"Cập nhật thông tin");

			String[] result = capNhatThongTinDialog.showDialog();
			if (result != null) {
				try {
					selectedGiaoVu.setMaGVu(Integer.parseInt(result[0]));
					selectedGiaoVu.setTenGiaoVu(result[1]);
					selectedGiaoVu.setGioiTinh(result[2]);
					selectedGiaoVu.setNgSinh(new SimpleDateFormat("dd/MM/yyyy").parse((result[3])));

					GiaoVuDAO.capNhatThongTinGiaoVu(selectedGiaoVu);
					listPanel.updateTable(GiaoVuDAO.getObjectMatrix());

					JOptionPane.showMessageDialog(capNhatThongTinDialog, "Cập nhật thông tin thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE, null);

				} catch (NumberFormatException | ParseException ex) {
					JOptionPane.showMessageDialog(capNhatThongTinDialog, "Sai định dạng", "Lỗi cập nhật thông tin",
							JOptionPane.WARNING_MESSAGE, null);
				}
			}

			break;
		}
		case "Reset mật khẩu": {
			String taiKhoanCanReset = listPanel.theTable.getValueAt(listPanel.theTable.getSelectedRow(), 0).toString();
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn reset mật khẩu của tài khoản " + taiKhoanCanReset,
					"Reset mật khẩu", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoan(taiKhoanCanReset);
				tk.setMatKhau("admin");
				TaiKhoanDAO.capNhatThongTinTaiKhoan(tk);

				JOptionPane.showMessageDialog(this, "Reset mật khẩu thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
			break;
		}

		case "Xoá tài khoản": {

			String taiKhoanCanXoa = listPanel.theTable.getValueAt(listPanel.theTable.getSelectedRow(), 0).toString();

			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá tài khoản " + taiKhoanCanXoa, "Xoá tài khoản",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				GiaoVuDAO.xoaGiaoVu(TaiKhoanDAO.layGiaoVu(taiKhoanCanXoa).getMaGVu());
				TaiKhoanDAO.xoaTaiKhoan(taiKhoanCanXoa);

				listPanel.updateTable(GiaoVuDAO.getObjectMatrix());

				JOptionPane.showMessageDialog(this, "Xoá tài khoản thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE, null);
			}

			break;
		}
		}
	}
}
