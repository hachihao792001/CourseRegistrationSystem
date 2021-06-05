package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import daos.HocKiDAO;

public class GiaoVuHocKiPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	ListPanel listPanel;
	InfoPanel infoPanel;

	public GiaoVuHocKiPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(
				new ArrayList<String>(Arrays.asList("Tên học kì", "Năm học: ", "Ngày bắt đầu: ", "Ngày kết thúc: ")));
		infoPanel.actionButton = new JButton("Để thành học kì hiện tại");
		infoPanel.buttonPadding = 25;
		infoPanel.firstRowTable = HocKiDAO.getObjectMatrix()[0];
		infoPanel.build();

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new ListPanel(infoPanel, HocKiDAO.getObjectMatrix(),
				new String[] { "Tên học kì", "Năm học", "Ngày bắt đầu", "Ngày kết thúc" },
				new String[] { "Thêm học kì", "Xoá học kì" }, this);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, infoPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
