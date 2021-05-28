package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import daos.MonHocDAO;

public class GiaoVuMonHocPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public GiaoVuMonHocPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ LIST PANEL --------------------------------
		JPanel listPanel = new JPanel(new GridBagLayout());
		JScrollPane gvListScrollPane = new JScrollPane();
		JTable giaoVuTable = new JTable();
		giaoVuTable.setModel(new DefaultTableModel(MonHocDAO.getObjectMatrix(),
				new String[] { "Mã môn học", "Tên môn học", "Số tín chỉ" }));
		giaoVuTable.setRowHeight(30);
		gvListScrollPane.setViewportView(giaoVuTable);

		listPanel.add(gvListScrollPane, gbc.setFill(GridBagConstraints.BOTH).setWeight(1, 1));
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách môn học"));
		this.add(listPanel, gbc.setFill(GridBagConstraints.BOTH).setWeight(1, 1));

		// ------------------------ INFO PANEL --------------------------------
		JPanel infoPanel = new JPanel(new GridBagLayout());

		Dimension nameDim = new Dimension(100, 25), dataDim = new Dimension(50, 25);
		JPanel maMHPanel = new InfoPanelElement("Mã môn học: ", nameDim, dataDim);
		JPanel tenMHPanel = new InfoPanelElement("Tên môn học: ", nameDim, dataDim);
		JPanel soTinChiPanel = new InfoPanelElement("Số tín chỉ: ", nameDim, dataDim);

		gbc = new GBCBuilder(1, 1);
		infoPanel.add(maMHPanel, gbc.setInsets(0, 0, 30, 0));
		infoPanel.add(tenMHPanel, gbc.setGrid(1, 2));
		infoPanel.add(soTinChiPanel, gbc.setGrid(1, 3).setInsets(0));

		infoPanel.setPreferredSize(new Dimension(200, 200));
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
