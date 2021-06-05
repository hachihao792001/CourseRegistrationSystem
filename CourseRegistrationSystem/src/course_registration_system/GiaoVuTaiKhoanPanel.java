package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import daos.GiaoVuDAO;

public class GiaoVuTaiKhoanPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel listPanel, buttonPanel;
	InfoPanel infoPanel;

	public GiaoVuTaiKhoanPanel() {

		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(
				new ArrayList<String>(Arrays.asList("Tài khoản: ", "Tên giáo vụ: ", "Giới tính: ", "Ngày sinh: ")));

		infoPanel.actionButton = new JButton("Reset mật khẩu");
		infoPanel.firstRowTable = GiaoVuDAO.getObjectMatrix()[0];
		infoPanel.build();

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new ListPanel(infoPanel, GiaoVuDAO.getObjectMatrix(),
				new String[] { "Tài khoản", "Tên giáo vụ", "Giới tính", "Ngày sinh" },
				new String[] { "Tìm tài khoản", "Thêm tài khoản", "Xoá tài khoản" }, this);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, infoPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
