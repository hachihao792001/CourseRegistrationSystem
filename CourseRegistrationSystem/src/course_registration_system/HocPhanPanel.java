package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;

import daos.DKHPDAO;
import daos.GiaoVienDAO;
import daos.HocKiDAO;
import daos.HocKiHienTaiDAO;
import daos.HocPhanDAO;
import daos.KyDKHPDAO;
import daos.MonHocDAO;
import pojo.DKHP;
import pojo.GiaoVien;
import pojo.HocKi;
import pojo.HocPhan;
import pojo.KyDKHP;
import pojo.MonHoc;

public class HocPhanPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	static ListPanel listPanel;
	InfoPanel infoPanel;
	static HocKi hocKiHienTai;

	public HocPhanPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(
				new String[] { "Mã môn: ", "Tên môn: ", "Số tín chỉ: ", "Giáo viên lý thuyết: ", "Tên phòng học: ",
						"Thứ: ", "Ca: ", "Số slot tối đa: " },
				new String[] { "Xem danh sách sinh viên đăng ký", "Xoá học phần" }, this);
		infoPanel.updateInfo();

		// ------------------------ LIST PANEL --------------------------------
		hocKiHienTai = HocKiHienTaiDAO.layThongTinHocKiHienTai().getHk();
		listPanel = new ListPanel(infoPanel,
				HocPhanDAO.getObjectMatrix(HocKiDAO.layDanhSachHocPhanTrongHocKi(hocKiHienTai)),
				new String[] { "Mã môn", "Tên môn", "Số tín chỉ", "Giáo viên lý thuyết", "Tên phòng", "Thứ", "Ca",
						"Slot tối đa" },
				new String[] { "Thêm học phần" }, this,
				"học phần trong học kỳ " + hocKiHienTai.getTenHocKi() + ", năm học " + hocKiHienTai.getNamHoc());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, infoPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0));
	}

	public static void capNhatVoiHocKiHienTai(HocKi hocKiHienTai) {
		HocPhanPanel.hocKiHienTai = hocKiHienTai;
		listPanel.updateTable(HocPhanDAO.getObjectMatrix(HocKiDAO.layDanhSachHocPhanTrongHocKi(hocKiHienTai)));
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách học phần trong học kỳ "
				+ hocKiHienTai.getTenHocKi() + ", năm học " + hocKiHienTai.getNamHoc()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Thêm học phần": {

			EnterInputDialog themHocPhanDialog = new EnterInputDialog(
					new String[] { "Mã môn: ", "Tên giáo viên lý thuyết: ", "Tên phòng học: ", "Thứ: ", "Ca: ",
							"Số slot tối đa: " },
					new JComponent[] { new JComboBox<String>(MonHocDAO.layDanhSachTenMonHoc()),
							new JComboBox<String>(GiaoVienDAO.layDanhSachTenGV()), new JTextField(),
							new JComboBox<String>(Main.dsThu), new JComboBox<String>(Main.dsCa), new JTextField() },
					new String[8], "Thêm học phần");
			String[] thongTinHocPhanMoi = themHocPhanDialog.showDialog();
			if (thongTinHocPhanMoi == null)
				break;

			MonHoc mh = MonHocDAO.layThongTinMonHocVoiTen(thongTinHocPhanMoi[0]);
			GiaoVien gvlt = GiaoVienDAO.layThongTinGiaoVien(thongTinHocPhanMoi[1]);
			if (mh == null) {
				JOptionPane.showMessageDialog(this, "Môn học với mã " + thongTinHocPhanMoi[0] + " không tồn tại",
						"Lỗi thêm học phần", JOptionPane.WARNING_MESSAGE, null);
			} else if (gvlt == null) {
				JOptionPane.showMessageDialog(this, "Giáo viên với tên " + thongTinHocPhanMoi[1] + " không tồn tại",
						"Lỗi thêm học phần", JOptionPane.WARNING_MESSAGE, null);
			} else {
				try {
					HocPhan newHocPhan = new HocPhan();
					newHocPhan.setMonHoc(mh);
					newHocPhan.setGvlt(gvlt);
					newHocPhan.setTenPhong(thongTinHocPhanMoi[2]);
					newHocPhan.setThu(thongTinHocPhanMoi[3]);
					newHocPhan.setCa(thongTinHocPhanMoi[4]);
					newHocPhan.setSlotToiDa(Integer.parseInt(thongTinHocPhanMoi[5]));
					List<KyDKHP> cacKyDKHPTrongHKHienTai = KyDKHPDAO.layDanhSachKyDKHP(hocKiHienTai);
					newHocPhan.setKyDKHP(cacKyDKHPTrongHKHienTai.get(cacKyDKHPTrongHKHienTai.size() - 1));

					HocPhanDAO.themHocPhan(newHocPhan);

					capNhatVoiHocKiHienTai(hocKiHienTai);

					JOptionPane.showMessageDialog(this, "Thêm học phần thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE, null);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "Thứ, ca, số slot phải là 1 số nguyên", "Lỗi thêm học phần",
							JOptionPane.WARNING_MESSAGE, null);
				}
			}

			break;
		}

		case "Xoá học phần": {
			HocPhan hocPhanCanXoa = HocKiDAO.layDanhSachHocPhanTrongHocKi(hocKiHienTai)
					.get(listPanel.theTable.getSelectedRow());
			if (JOptionPane.showConfirmDialog(this,
					"Bạn có chắc muốn xoá học phần " + hocPhanCanXoa.getMonHoc().getTenMH(), "Xoá học phần",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				List<DKHP> dsDKHP = DKHPDAO.layDanhSachDKHP();
				for (DKHP dkhp : dsDKHP) {
					if (dkhp.getDkhpID().getHocPhan().getMaHP() == hocPhanCanXoa.getMaHP())
						DKHPDAO.xoaDKHP(dkhp.getDkhpID());
				}

				HocPhanDAO.xoaHocPhan(hocPhanCanXoa.getMaHP());

				capNhatVoiHocKiHienTai(hocKiHienTai);

				JOptionPane.showMessageDialog(this, "Xoá học phần thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
			break;
		}

		case "Xem danh sách sinh viên đăng ký": {
			if (listPanel.theTable.getSelectedRow() == -1)
				break;

			HocPhan selectedHocPhan = HocKiDAO.layDanhSachHocPhanTrongHocKi(hocKiHienTai)
					.get(listPanel.theTable.getSelectedRow());

			JDialog dssvDialog = new JDialog();
			JPanel dssvContent = new JPanel(new GridBagLayout());

			ListPanel dssvListPanel = new ListPanel(null,
					DKHPDAO.getObjectMatrix(DKHPDAO.layDanhSachDKHP(selectedHocPhan)), new String[] { "MSSV", "Họ tên",
							"Mã môn học", "Tên môn học", "Tên gvlt", "Thứ", "Thời gian", "Thời gian đăng kí học phần" },
					new String[] {}, null, "Danh sách sinh viên đăng ký");

			GBCBuilder gbc = new GBCBuilder(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1);
			dssvContent.add(dssvListPanel, gbc);

			dssvDialog.setContentPane(dssvContent);
			dssvDialog.setTitle("Danh sách sinh viên đăng ký học phần " + selectedHocPhan.getMonHoc().getTenMH());
			dssvDialog.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
			dssvDialog.pack();
			dssvDialog.setVisible(true);
			break;
		}
		}
	}
}
