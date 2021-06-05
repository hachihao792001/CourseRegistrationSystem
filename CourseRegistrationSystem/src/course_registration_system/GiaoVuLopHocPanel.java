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

import daos.HocKiDAO;
import daos.LopHocDAO;

public class GiaoVuLopHocPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel listPanel, buttonPanel;
	InfoPanel infoPanel;

	public GiaoVuLopHocPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new ArrayList<String>(
				Arrays.asList("Mã lớp: ", "Tổng số sinh viên: ", "Tổng số nam: ", "Tổng số nữ: ")));
		infoPanel.actionButton = new JButton("Xem danh sách sinh viên");
		infoPanel.firstRowTable = LopHocDAO.getObjectMatrix()[0];
		infoPanel.build();

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new ListPanel(infoPanel, LopHocDAO.getObjectMatrix(),
				new String[] { "Mã lớp", "Tổng số sinh viên", "Tổng số nam", "Tổng số nữ" },
				new String[] { "Thêm lớp học", "Xoá lớp học" }, this);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, infoPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
