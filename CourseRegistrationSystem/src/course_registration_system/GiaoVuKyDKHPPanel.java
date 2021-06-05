package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import daos.HocPhanDAO;
import daos.KyDKHPDAO;

public class GiaoVuKyDKHPPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel listPanel, buttonPanel;
	InfoPanel infoPanel;

	public GiaoVuKyDKHPPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new ArrayList<String>(
				Arrays.asList("Năm học: ", "Học kì: ", "Lần: ", "Ngày bắt đầu: ", "Ngày kết thúc: ")));
		infoPanel.firstRowTable = KyDKHPDAO.getObjectMatrix()[0];
		infoPanel.build();

		// ------------------------ LIST PANEL --------------------------------

		listPanel = new ListPanel(infoPanel, KyDKHPDAO.getObjectMatrix(),
				new String[] { "Năm học", "Học kì", "Lần", "Ngày bắt đầu", "Ngày kết thúc" },
				new String[] { "Tạo kỳ đăng ký học phần", "Xoá kỳ đăng ký học phần" }, this);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, infoPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
