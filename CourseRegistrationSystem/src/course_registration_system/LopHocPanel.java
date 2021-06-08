package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import daos.LopHocDAO;
import pojo.LopHoc;

public class LopHocPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	ListPanel listPanel;
	InfoPanel infoPanel;

	public LopHocPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new String[] { "Mã lớp: ", "Tổng số sinh viên: ", "Tổng số nam: ", "Tổng số nữ: " },
				new String[] { "Xem danh sách sinh viên", "Xoá lớp học" }, this);
		infoPanel.updateInfo();

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new ListPanel(infoPanel, LopHocDAO.getObjectMatrix(),
				new String[] { "Mã lớp", "Tổng số sinh viên", "Tổng số nam", "Tổng số nữ" },
				new String[] { "Thêm lớp học", }, this, "lớp học");

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, infoPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Thêm lớp học": {
			MultiTextFieldDialog themLopHocDialog = new MultiTextFieldDialog(
					new String[] { "Mã lớp: ", "Tổng số sinh viên: ", "Tổng số nam: ", "Tổng số nữ: " }, new String[4],
					"Thêm lớp học");
			String[] thongTinLopHocMoi = themLopHocDialog.showDialog();
			if (thongTinLopHocMoi == null)
				break;

			if (LopHocDAO.layThongTinLopHoc(thongTinLopHocMoi[0]) != null)
				JOptionPane.showMessageDialog(this, "Lớp học đã tồn tại", "Lỗi thêm lớp học", JOptionPane.ERROR_MESSAGE,
						null);
			else {

				try {

					LopHoc newLopHoc = new LopHoc(thongTinLopHocMoi[0], Integer.parseInt(thongTinLopHocMoi[1]),
							Integer.parseInt(thongTinLopHocMoi[2]), Integer.parseInt(thongTinLopHocMoi[3]));

					LopHocDAO.themLopHoc(newLopHoc);

					listPanel.updateTable(LopHocDAO.getObjectMatrix());
					listPanel.setTableSelectedRow(listPanel.theTable.getRowCount() - 1);

					JOptionPane.showMessageDialog(this, "Thêm lớp học thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE, null);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "Số sinh viên phải là 1 số nguyên", "Lỗi thêm lớp học",
							JOptionPane.WARNING_MESSAGE, null);
				}
			}
			break;
		}

		case "Xoá lớp học": {
			String lopHocCanXoa = infoPanel.elementDatas[0];

			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá lớp học " + lopHocCanXoa, "Xoá lớp học",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				LopHocDAO.xoaLopHoc(lopHocCanXoa);

				listPanel.theTable.setModel(new DefaultTableModel(LopHocDAO.getObjectMatrix(),
						new String[] { "Mã lớp: ", "Tổng số sinh viên: ", "Tổng số nam: ", "Tổng số nữ: " }));

				JOptionPane.showMessageDialog(this, "Xoá lớp học thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
			break;
		}

		case "Xem danh sách sinh viên": {

			new DSSVDialog(LopHocDAO.layThongTinLopHoc(infoPanel.elementDatas[0]));

			break;
		}
		}
	}
}
