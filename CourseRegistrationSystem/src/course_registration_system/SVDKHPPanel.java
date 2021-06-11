package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import daos.DKHPDAO;
import daos.HocKiDAO;
import daos.HocKiHienTaiDAO;
import daos.HocPhanDAO;
import daos.SinhVienDAO;
import pojo.DKHP;
import pojo.HocKi;
import pojo.HocPhan;
import pojo.KyDKHP;

public class SVDKHPPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public static ListPanel listPanel;
	InfoPanel infoPanel;
	HocKi hocKiHienTai;

	public SVDKHPPanel(boolean trongKyDKHP) {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		if (!trongKyDKHP) {
			HocKi hocKiHienTai = HocKiHienTaiDAO.layThongTinHocKiHienTai().getHk();
			List<KyDKHP> dsKyDKHP = HocKiDAO.layDanhSachKyDKHPTrongHocKi(hocKiHienTai);
			Date currentDate = new Date();

			String hetHanTuNgay = "";
			for (int i = dsKyDKHP.size() - 1; i >= 0; i--) {
				if (currentDate.after(dsKyDKHP.get(i).getNgayKetThuc())) {
					hetHanTuNgay = Main.dateFormat.format(dsKyDKHP.get(i).getNgayKetThuc());
					break;
				}
			}

			JLabel hetHanLabel = new JLabel("Đã hết hạn đăng ký học phần từ ngày " + hetHanTuNgay);
			hetHanLabel.setFont(new Font("Tahoma", 0, 25));
			this.add(hetHanLabel);
			return;
		}

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new String[] { "Mã môn: ", "Tên môn: ", "Số tín chỉ: ", "Giáo viên lý thuyết: ",
				"Tên phòng học: ", "Thứ: ", "Ca: ", "Số slot tối đa: " }, new String[] { "Đăng ký" }, this);
		infoPanel.updateInfo();

		// ------------------------ LIST PANEL --------------------------------
		hocKiHienTai = HocKiHienTaiDAO.layThongTinHocKiHienTai().getHk();
		listPanel = new ListPanel(infoPanel,
				HocPhanDAO.getObjectMatrix(SinhVienDAO
						.layDanhSachHocPhanChuaDKTrongHocKy(MainScreen.loggedInSV.getMssv(), hocKiHienTai.getMaHK())),
				new String[] { "Mã môn", "Tên môn", "Số tín chỉ", "Giáo viên lý thuyết", "Tên phòng", "Thứ", "Ca",
						"Slot tối đa" },
				new String[] {}, this,
				"học phần trong học kỳ " + hocKiHienTai.getTenHocKi() + ", năm học " + hocKiHienTai.getNamHoc());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, infoPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Đăng ký": {
			HocPhan selectedHocPhan = SinhVienDAO
					.layDanhSachHocPhanChuaDKTrongHocKy(MainScreen.loggedInSV.getMssv(), hocKiHienTai.getMaHK())
					.get(listPanel.theTable.getSelectedRow());
			if (selectedHocPhan == null)
				break;

			List<HocPhan> dshpDaDK = SinhVienDAO.layDanhSachHocPhanDaDKTrongHocKy(MainScreen.loggedInSV.getMssv(),
					hocKiHienTai.getMaHK());

			if (dshpDaDK.size() >= 8) {
				JOptionPane.showMessageDialog(this, "Sinh viên đã đăng ký đủ số môn cho phép (8 môn)", "Thông báo",
						JOptionPane.WARNING_MESSAGE, null);
			} else {
				boolean trungGio = false;
				for (HocPhan hocPhan : dshpDaDK) {
					if (hocPhan.getThu().equals(selectedHocPhan.getThu())
							&& hocPhan.getCa().equals(selectedHocPhan.getCa())) {
						trungGio = true;
						JOptionPane.showMessageDialog(this,
								"Học phần đang chọn bị trùng giờ với môn " + hocPhan.getMonHoc().getTenMH(),
								"Thông báo", JOptionPane.WARNING_MESSAGE, null);
						break;
					}
				}

				if (!trungGio) {

					DKHP newDKHP = new DKHP(new DKHP.DKHPID(MainScreen.loggedInSV, selectedHocPhan), new Date());
					DKHPDAO.themDKHP(newDKHP);

					listPanel.updateTable(HocPhanDAO.getObjectMatrix(SinhVienDAO.layDanhSachHocPhanChuaDKTrongHocKy(
							MainScreen.loggedInSV.getMssv(), hocKiHienTai.getMaHK())));
					SVKetQuaDKHPPanel.listPanel
							.updateTable(HocPhanDAO.getObjectMatrix(SinhVienDAO.layDanhSachHocPhanDaDKTrongHocKy(
									MainScreen.loggedInSV.getMssv(), hocKiHienTai.getMaHK())));

					JOptionPane.showMessageDialog(this, "Đăng ký học phần thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE, null);
				}
			}

			break;
		}
		}
	}
}
