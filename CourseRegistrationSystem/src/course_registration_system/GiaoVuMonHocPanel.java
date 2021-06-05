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

import daos.KyDKHPDAO;
import daos.MonHocDAO;

public class GiaoVuMonHocPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel listPanel, buttonPanel;
	InfoPanel infoPanel;

	public GiaoVuMonHocPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(
				new ArrayList<String>(Arrays.asList("Mã môn học: ", "Tên môn học: ", "Số tín chỉ: ")));
		infoPanel.firstRowTable = MonHocDAO.getObjectMatrix()[0];
		infoPanel.build();

		// ------------------------ LIST PANEL --------------------------------

		listPanel = new ListPanel(infoPanel, MonHocDAO.getObjectMatrix(),
				new String[] { "Mã môn học", "Tên môn học", "Số tín chỉ" },
				new String[] { "Tìm môn học", "Thêm môn học", "Xoá môn học" }, this);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, infoPanel);
		this.add(splitPane, gbc.setGrid(1, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
