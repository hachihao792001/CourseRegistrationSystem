package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import daos.HocKiDAO;

public class GiaoVuHocKiPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel listPanel, buttonPanel;
	InfoPanel infoPanel;

	public GiaoVuHocKiPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new JPanel(new GridBagLayout());
		JScrollPane gvListScrollPane = new JScrollPane();
		JTable giaoVuTable = new JTable();
		giaoVuTable.setModel(new DefaultTableModel(HocKiDAO.getObjectMatrix(),
				new String[] { "Tên học kì", "Năm học", "Ngày bắt đầu", "Ngày kết thúc" }));
		giaoVuTable.setRowHeight(30);
		giaoVuTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				for (int i = 0; i < infoPanel.elementList.size(); i++) {
					infoPanel.elementList.get(i).elementDataLabel
							.setText(giaoVuTable.getValueAt(giaoVuTable.getSelectedRow(), i).toString());
				}
			}
		});
		gvListScrollPane.setViewportView(giaoVuTable);

		listPanel.add(gvListScrollPane, gbc.setFill(GridBagConstraints.BOTH).setWeight(1, 1));
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách học kì"));
		this.add(listPanel, gbc.setFill(GridBagConstraints.BOTH).setWeight(1, 1));

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new GridBagLayout());

		Dimension nameDim = new Dimension(110, 25), dataDim = new Dimension(80, 25);

		InfoPanelElement tenHocKiPanel = new InfoPanelElement("Tên học kì: ", nameDim, dataDim);
		InfoPanelElement namHocPanel = new InfoPanelElement("Năm học: ", nameDim, dataDim);
		InfoPanelElement ngayBatDauPanel = new InfoPanelElement("Ngày bắt đầu: ", nameDim, dataDim);
		InfoPanelElement ngayKetThucPanel = new InfoPanelElement("Ngày kết thúc: ", nameDim, dataDim);

		gbc = new GBCBuilder(1, 1);
		infoPanel.add(tenHocKiPanel, gbc.setInsets(0, 0, 20, 0));
		infoPanel.add(namHocPanel, gbc.setGrid(1, 2));
		infoPanel.add(ngayBatDauPanel, gbc.setGrid(1, 3));
		infoPanel.add(ngayKetThucPanel, gbc.setGrid(1, 4).setInsets(0));
		for (int i = 0; i < infoPanel.elementList.size(); i++)
			infoPanel.elementList.get(i).elementDataLabel.setText(giaoVuTable.getValueAt(0, i).toString());

		JButton setCurrentHocKiBtn = new JButton("Để thành học kì hiện tại");
		infoPanel.add(setCurrentHocKiBtn, gbc.setGrid(1, 5).setInsets(25, 0, 0, 0));

		infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		this.add(infoPanel, new GBCBuilder(2, 1).setSpan(1, 2).setFill(GridBagConstraints.BOTH).setInsets(0, 10, 0, 0));

		// ------------------------ BUTTON PANEL --------------------------------
		buttonPanel = new JPanel(new GridBagLayout());

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
