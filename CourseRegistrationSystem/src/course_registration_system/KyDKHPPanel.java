package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.DateTimeException;
import java.util.List;

import javax.swing.*;

import daos.HocKiHienTaiDAO;
import daos.KyDKHPDAO;
import pojo.HocKi;
import pojo.KyDKHP;

public class KyDKHPPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	ListPanel listPanel;
	InfoPanel infoPanel;
	public static JLabel hocKiHienTaiLabel;

	public KyDKHPPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(
				new String[] { "Năm học: ", "Học kì: ", "Lần: ", "Ngày bắt đầu: ", "Ngày kết thúc: " }, null, null);
		infoPanel.updateInfo();

		// ------------------------ LIST PANEL --------------------------------

		listPanel = new ListPanel(infoPanel, KyDKHPDAO.getObjectMatrix(),
				new String[] { "Năm học", "Học kì", "Lần", "Ngày bắt đầu", "Ngày kết thúc" }, new String[] {}, null,
				" kỳ đăng ký học phần");

		// ---------------- HOC KI HIEN TAI PANEL-----------------------------
		JPanel hocKiHienTaiPanel = new JPanel(new GridBagLayout());
		hocKiHienTaiPanel.setBorder(BorderFactory.createTitledBorder("Học kì hiện tại"));

		HocKi hocKiHienTai = HocKiHienTaiDAO.layThongTinHocKiHienTai().getHk();
		hocKiHienTaiLabel = new JLabel();
		hocKiHienTaiLabel.setText("Học kì " + hocKiHienTai.getTenHocKi() + ", năm học " + hocKiHienTai.getNamHoc());
		hocKiHienTaiLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		JButton taoKyDKHPButton = new JButton("Tạo 1 kỳ đăng ký học phần mới");
		taoKyDKHPButton.setActionCommand("Tao ky DKHP");
		taoKyDKHPButton.addActionListener(this);

		hocKiHienTaiPanel.add(hocKiHienTaiLabel, gbc.setGrid(1, 1).setInsets(0, 0, 10, 0));
		hocKiHienTaiPanel.add(taoKyDKHPButton, gbc.setGrid(1, 2));

		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightPanel.add(hocKiHienTaiPanel, gbc.setGrid(1, 1).setWeight(1, 0).setFill(GridBagConstraints.BOTH));
		rightPanel.add(infoPanel, gbc.setGrid(1, 2).setFill(GridBagConstraints.BOTH).setWeight(1, 1));

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, rightPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setInsets(0).setWeight(1, 1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Tao ky DKHP": {
			HocKi hocKiHienTai = HocKiHienTaiDAO.layThongTinHocKiHienTai().getHk();

			EnterInputDialog themKyDKHPDialog = new EnterInputDialog(
					new String[] { "Năm học: ", "Học kì: ", "Ngày bắt đầu: ", "Ngày kết thúc: " },
					new JComponent[] { new JTextField(), new JComboBox<String>(Main.dsTenHocKi),
							new JFormattedTextField(Main.dateFormat), new JFormattedTextField(Main.dateFormat) },
					new String[] { "" + hocKiHienTai.getNamHoc(), hocKiHienTai.getTenHocKi(), "", "", "" },
					"Tạo kỳ đăng ký học phần mới ở học kỳ hiện tại");
			themKyDKHPDialog.elementInputs[0].setEnabled(false);
			themKyDKHPDialog.elementInputs[1].setEnabled(false);

			String[] thongTinKyDKHPMoi = themKyDKHPDialog.showDialog();
			if (thongTinKyDKHPMoi == null)
				break;

			try {

				List<KyDKHP> cacKyDKHPTrongKyHienTai = KyDKHPDAO.layDanhSachKyDKHP(hocKiHienTai);
				KyDKHP newKyDKHP = new KyDKHP(new KyDKHP.KyDKHPID(hocKiHienTai, cacKyDKHPTrongKyHienTai.size() + 1),
						Main.dateFormat.parse(thongTinKyDKHPMoi[2]), Main.dateFormat.parse(thongTinKyDKHPMoi[3]));
				if (newKyDKHP.getNgayBatDau().after(newKyDKHP.getNgayKetThuc()))
					throw new DateTimeException("");

				KyDKHPDAO.themKyDKHP(newKyDKHP);

				listPanel.updateTable(KyDKHPDAO.getObjectMatrix());

				JOptionPane.showMessageDialog(this, "Thêm kỳ đăng ký học phần thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE, null);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(this, "Ngày bị lỗi định dạng", "Lỗi thêm kỳ đăng ký học phần",
						JOptionPane.WARNING_MESSAGE, null);
			} catch (DateTimeException e2) {
				JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc",
						"Lỗi thêm kỳ đăng ký học phần", JOptionPane.WARNING_MESSAGE, null);
			}

			break;
		}
		}
	}
}
