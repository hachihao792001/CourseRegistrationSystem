package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GiaoVuMonHocPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public GiaoVuMonHocPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ LIST PANEL --------------------------------
		JPanel listPanel = new JPanel();
		JScrollPane gvListScrollPane = new JScrollPane();
		JTable giaoVuTable = new JTable();
		giaoVuTable
				.setModel(new DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		giaoVuTable.setRowHeight(30);
		gvListScrollPane.setPreferredSize(new Dimension(400, 250));
		gvListScrollPane.setViewportView(giaoVuTable);

		listPanel.add(gvListScrollPane);
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách môn học"));
		this.add(listPanel, gbc.setFill(GridBagConstraints.BOTH));

		// ------------------------ INFO PANEL --------------------------------
		JPanel infoPanel = new JPanel(new GridBagLayout());

		Dimension nameDim = new Dimension(100, 25), dataDim = new Dimension(50, 25);
		JPanel maMHPanel = new InfoPanelElement("Mã môn học: ", nameDim, dataDim);
		JPanel tenMHPanel = new InfoPanelElement("Tên môn học: ", nameDim, dataDim);
		JPanel soTinChiPanel = new InfoPanelElement("Số tín chỉ: ", nameDim, dataDim);

		infoPanel.add(maMHPanel, gbc.setInsets(0, 0, 30, 0));
		infoPanel.add(tenMHPanel, gbc.setGrid(1, 2));
		infoPanel.add(soTinChiPanel, gbc.setGrid(1, 3).setInsets(0));

		infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		this.add(infoPanel, new GBCBuilder(2, 1).setSpan(1, 2).setFill(GridBagConstraints.BOTH).setInsets(0, 10, 0, 0));

		// ------------------------ BUTTON PANEL --------------------------------
		JPanel buttonPanel = new JPanel();

		JButton findMonHocBtn = new JButton();
		findMonHocBtn.setText("Tìm môn học");
		findMonHocBtn.addActionListener(this);

		JButton addMonHocBtn = new JButton();
		addMonHocBtn.setText("Thêm môn học");
		addMonHocBtn.addActionListener(this);
		buttonPanel.add(addMonHocBtn);

		JButton deleteMonHocBtn = new JButton();
		deleteMonHocBtn.setText("Xoá môn học");
		deleteMonHocBtn.addActionListener(this);
		buttonPanel.add(deleteMonHocBtn);

		buttonPanel.add(findMonHocBtn);
		buttonPanel.add(addMonHocBtn);
		buttonPanel.add(deleteMonHocBtn);

		this.add(buttonPanel, new GBCBuilder(1, 2).setFill(GridBagConstraints.BOTH));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
