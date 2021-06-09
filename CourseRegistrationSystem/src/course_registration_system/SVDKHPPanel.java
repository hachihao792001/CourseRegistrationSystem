package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import daos.DKHPDAO;
import daos.HocKiHienTaiDAO;
import daos.HocPhanDAO;
import daos.SinhVienDAO;
import pojo.DKHP;
import pojo.HocKi;
import pojo.HocPhan;

public class SVDKHPPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public static ListPanel listPanel;
	InfoPanel infoPanel;
	HocKi hocKiHienTai;
	boolean trongKyDKHP;

	public SVDKHPPanel(boolean trongKyDKHP) {
		this.trongKyDKHP = trongKyDKHP;
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

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
			if (trongKyDKHP) {
				HocPhan selectedHocPhan = SinhVienDAO
						.layDanhSachHocPhanChuaDKTrongHocKy(MainScreen.loggedInSV.getMssv(), hocKiHienTai.getMaHK())
						.get(listPanel.theTable.getSelectedRow());
				if(selectedHocPhan == null) break;
				
				List<HocPhan> dshpDaDK = SinhVienDAO.layDanhSachHocPhanDaDKTrongHocKy(MainScreen.loggedInSV.getMssv(),
						hocKiHienTai.getMaHK());

				if (dshpDaDK.size() >= 8) {
					JOptionPane.showMessageDialog(this, "Sinh viên đã đăng ký đủ số môn cho phép (8 môn)", "Thông báo",
							JOptionPane.WARNING_MESSAGE, null);
				} else {
					boolean trungGio = false;
					for (HocPhan hocPhan : dshpDaDK) {
						if (hocPhan.getThu() == selectedHocPhan.getThu()
								&& hocPhan.getCa() == selectedHocPhan.getCa()) {
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
			} else {
				JOptionPane.showMessageDialog(this, "Đã hết hạn đăng ký học phần!", "Thông báo",
						JOptionPane.ERROR_MESSAGE, null);
			}
			break;
		}
		}
	}
}
