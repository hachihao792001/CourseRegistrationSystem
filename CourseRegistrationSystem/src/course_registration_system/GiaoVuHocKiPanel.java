package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GiaoVuHocKiPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public GiaoVuHocKiPanel() {
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
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách học kì"));
		this.add(listPanel, gbc.setFill(GridBagConstraints.BOTH));

		// ------------------------ INFO PANEL --------------------------------
		JPanel infoPanel = new JPanel(new GridBagLayout());

		Dimension nameDim = new Dimension(110, 25), dataDim = new Dimension(50, 25);;
		JPanel tenHocKiPanel = new InfoPanelElement("Tên học kì: ", nameDim, dataDim);
		JPanel namHocPanel = new InfoPanelElement("Năm học: ", nameDim, dataDim);
		JPanel ngayBatDauPanel = new InfoPanelElement("Ngày bắt đầu: ", nameDim, dataDim);
		JPanel ngayKetThucPanel = new InfoPanelElement("Ngày kết thúc: ", nameDim, dataDim);

		infoPanel.add(tenHocKiPanel, gbc.setInsets(0, 0, 20, 0));
		infoPanel.add(namHocPanel, gbc.setGrid(1, 2));
		infoPanel.add(ngayBatDauPanel, gbc.setGrid(1, 3));
		infoPanel.add(ngayKetThucPanel, gbc.setGrid(1, 4).setInsets(0));

		JButton setCurrentHocKiBtn = new JButton("Để thành học kì hiện tại");
		infoPanel.add(setCurrentHocKiBtn, gbc.setGrid(1, 5).setInsets(25, 70, 0, 70));

		infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		this.add(infoPanel, new GBCBuilder(2, 1).setSpan(1, 2).setFill(GridBagConstraints.BOTH).setInsets(0, 10, 0, 0));

		// ------------------------ BUTTON PANEL --------------------------------
		JPanel buttonPanel = new JPanel(new GridBagLayout());

		JButton addHocKiBtn = new JButton();
		addHocKiBtn.setText("Thêm học kì");
		addHocKiBtn.addActionListener(this);
		buttonPanel.add(addHocKiBtn);

		JButton deleteHocKiBtn = new JButton();
		deleteHocKiBtn.setText("Xoá học kì");
		deleteHocKiBtn.addActionListener(this);
		buttonPanel.add(deleteHocKiBtn);

		buttonPanel.add(addHocKiBtn, new GBCBuilder(1, 1).setInsets(0, 0, 0, 10));
		buttonPanel.add(deleteHocKiBtn, new GBCBuilder(2, 1).setInsets(0));

		this.add(buttonPanel, new GBCBuilder(1, 2).setFill(GridBagConstraints.BOTH));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
