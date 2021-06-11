package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.DateTimeException;

import javax.swing.*;

import daos.HocKiDAO;
import daos.HocKiHienTaiDAO;
import pojo.HocKi;

public class HocKiPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	ListPanel listPanel;
	InfoPanel infoPanel;
	JLabel hocKiHienTaiLabel;

	public HocKiPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new String[] { "Tên học kì: ", "Năm học: ", "Ngày bắt đầu: ", "Ngày kết thúc: " },
				new String[] { "Để thành học kì hiện tại", "Xoá học kì" }, this);
		infoPanel.updateInfo();

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new ListPanel(infoPanel, HocKiDAO.getObjectMatrix(),
				new String[] { "Tên học kì", "Năm học", "Ngày bắt đầu", "Ngày kết thúc" },
				new String[] { "Thêm học kì" }, this, "học kì");

		// ---------------- HOC KI HIEN TAI PANEL-----------------------------
		JPanel hocKiHienTaiPanel = new JPanel();
		hocKiHienTaiPanel.setBorder(BorderFactory.createTitledBorder("Học kì hiện tại"));
		HocKi hocKiHienTai = HocKiHienTaiDAO.layThongTinHocKiHienTai().getHk();
		hocKiHienTaiPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				HocKi hocKiHienTai = HocKiHienTaiDAO.layThongTinHocKiHienTai().getHk();
				Object[][] hocKiObjectMatrix = HocKiDAO.getObjectMatrix();
				for (int i = 0; i < hocKiObjectMatrix.length; i++) {
					if (hocKiObjectMatrix[i][0].equals(hocKiHienTai.getTenHocKi())
							&& hocKiObjectMatrix[i][1].equals(hocKiHienTai.getNamHoc())) {
						listPanel.setTableSelectedRow(i);
						break;
					}
				}
			}
		});
		hocKiHienTaiLabel = new JLabel();
		hocKiHienTaiLabel.setText("Học kì " + hocKiHienTai.getTenHocKi() + ", năm học " + hocKiHienTai.getNamHoc());
		hocKiHienTaiPanel.add(hocKiHienTaiLabel);

		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightPanel.add(hocKiHienTaiPanel, gbc.setGrid(1, 1).setWeight(1, 0).setFill(GridBagConstraints.BOTH));
		rightPanel.add(infoPanel, gbc.setGrid(1, 2).setFill(GridBagConstraints.BOTH).setWeight(1, 1));

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, rightPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setInsets(0).setWeight(1, 1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Thêm học kì": {
			EnterInputDialog themHocKiDialog = new EnterInputDialog(
					new String[] { "Tên học kì", "Năm học", "Ngày bắt đầu", "Ngày kết thúc" },
					new JComponent[] { new JComboBox<String>(new String[] { "HK1", "HK2", "HK3" }), new JTextField(),
							new JFormattedTextField(Main.dateFormat), new JFormattedTextField(Main.dateFormat) },
					new String[4], "Thêm học kì");
			String[] thongTinHocKiMoi = themHocKiDialog.showDialog();
			if (thongTinHocKiMoi == null)
				break;

			if (HocKiDAO.layThongTinHocKi(thongTinHocKiMoi[0], Integer.parseInt(thongTinHocKiMoi[1])) != null)
				JOptionPane.showMessageDialog(this, "Học kì đã tồn tại", "Lỗi thêm học kì", JOptionPane.ERROR_MESSAGE,
						null);
			else {

				try {

					HocKi newHocKi = new HocKi();
					newHocKi.setTenHocKi(thongTinHocKiMoi[0]);
					newHocKi.setNamHoc(Integer.parseInt(thongTinHocKiMoi[1]));
					newHocKi.setNgayBatDau(Main.dateFormat.parse(thongTinHocKiMoi[2]));
					newHocKi.setNgayKetThuc(Main.dateFormat.parse(thongTinHocKiMoi[3]));

					if (newHocKi.getNgayBatDau().after(newHocKi.getNgayKetThuc()))
						throw new DateTimeException("");

					HocKiDAO.themHocKi(newHocKi);

					listPanel.updateTable(HocKiDAO.getObjectMatrix());
					listPanel.setTableSelectedRow(listPanel.theTable.getRowCount() - 1);

					JOptionPane.showMessageDialog(this, "Thêm học kì thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE, null);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "Năm học phải là 1 số nguyên", "Lỗi thêm học kì",
							JOptionPane.WARNING_MESSAGE, null);
				} catch (ParseException e2) {
					JOptionPane.showMessageDialog(this, "Sai định dạng ngày", "Lỗi thêm học kì",
							JOptionPane.WARNING_MESSAGE, null);
				} catch (DateTimeException e3) {
					JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc", "Lỗi thêm học kì",
							JOptionPane.WARNING_MESSAGE, null);
				}
			}
			break;
		}

		case "Xoá học kì": {
			String tenHocKiCanXoa = infoPanel.elementDatas[0];
			int namHocCanXoa = Integer.parseInt(infoPanel.elementDatas[1]);
			HocKi hocKiCanXoa = HocKiDAO.layThongTinHocKi(tenHocKiCanXoa, namHocCanXoa);

			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá học kì " + tenHocKiCanXoa, "Xoá học kì",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				HocKiDAO.xoaHocKi(hocKiCanXoa.getMaHK());

				listPanel.updateTable(HocKiDAO.getObjectMatrix());

				JOptionPane.showMessageDialog(this, "Xoá học kì thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
			break;
		}

		case "Để thành học kì hiện tại": {

			String tenHocKi = infoPanel.elementDatas[0];
			int namHoc = Integer.parseInt(infoPanel.elementDatas[1]);

			HocKiDAO.capNhatHocKiHienTai(tenHocKi, namHoc);
			hocKiHienTaiLabel.setText("Học kì " + tenHocKi + ", năm học " + namHoc);
			KyDKHPPanel.hocKiHienTaiLabel.setText(hocKiHienTaiLabel.getText());
			HocPhanPanel.capNhatVoiHocKiHienTai(HocKiHienTaiDAO.layThongTinHocKiHienTai().getHk());

			JOptionPane.showMessageDialog(this, "Để học kì hiện tại thành công!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE, null);
			break;
		}
		}
	}
}
