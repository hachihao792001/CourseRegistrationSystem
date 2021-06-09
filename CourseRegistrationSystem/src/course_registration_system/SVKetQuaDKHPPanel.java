package course_registration_system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import daos.DKHPDAO;
import daos.HocKiHienTaiDAO;
import daos.HocPhanDAO;
import daos.SinhVienDAO;
import pojo.DKHP;
import pojo.HocKi;
import pojo.HocPhan;

public class SVKetQuaDKHPPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public static ListPanel listPanel;
	InfoPanel infoPanel;
	HocKi hocKiHienTai;
	boolean trongKyDKHP;

	public SVKetQuaDKHPPanel(boolean trongKyDKHP) {
		this.trongKyDKHP = trongKyDKHP;
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new String[] { "Mã môn: ", "Tên môn: ", "Số tín chỉ: ", "Giáo viên lý thuyết: ",
				"Tên phòng học: ", "Thứ: ", "Ca: ", "Số slot tối đa: " }, new String[] { "Huỷ đăng ký" }, this);
		infoPanel.updateInfo();

		// ------------------------ LIST PANEL --------------------------------
		hocKiHienTai = HocKiHienTaiDAO.layThongTinHocKiHienTai().getHk();
		listPanel = new ListPanel(infoPanel,
				HocPhanDAO.getObjectMatrix(SinhVienDAO.layDanhSachHocPhanDaDKTrongHocKy(MainScreen.loggedInSV.getMssv(),
						hocKiHienTai.getMaHK())),
				new String[] { "Mã môn", "Tên môn", "Số tín chỉ", "Giáo viên lý thuyết", "Tên phòng", "Thứ", "Ca",
						"Slot tối đa" },
				new String[] {}, this,
				"học phần đã đăng ký trong học kỳ " + hocKiHienTai.getTenHocKi() + ", năm học " + hocKiHienTai.getNamHoc());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, infoPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Huỷ đăng ký": {
			if (trongKyDKHP) {
				HocPhan selectedHocPhan = SinhVienDAO
						.layDanhSachHocPhanDaDKTrongHocKy(MainScreen.loggedInSV.getMssv(), hocKiHienTai.getMaHK())
						.get(listPanel.theTable.getSelectedRow());
				if(selectedHocPhan == null) break;
				if (JOptionPane.showConfirmDialog(this,
						"Bạn có chắc muốn huỷ đăng ký học phần " + selectedHocPhan.getMonHoc().getTenMH() + "?",
						"Huỷ đăng ký học phần", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					DKHP selectedDKHP = DKHPDAO.layThongTinDKHP(selectedHocPhan, MainScreen.loggedInSV);
					DKHPDAO.xoaDKHP(selectedDKHP.getDkhpID());

					listPanel.updateTable(HocPhanDAO.getObjectMatrix(SinhVienDAO.layDanhSachHocPhanDaDKTrongHocKy(
							MainScreen.loggedInSV.getMssv(), hocKiHienTai.getMaHK())));
					SVDKHPPanel.listPanel
							.updateTable(HocPhanDAO.getObjectMatrix(SinhVienDAO.layDanhSachHocPhanChuaDKTrongHocKy(
									MainScreen.loggedInSV.getMssv(), hocKiHienTai.getMaHK())));

					JOptionPane.showMessageDialog(this, "Huỷ đăng ký học phần thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE, null);
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
