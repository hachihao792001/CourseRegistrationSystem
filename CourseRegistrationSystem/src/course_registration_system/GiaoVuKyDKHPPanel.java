package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import daos.KyDKHPDAO;

public class GiaoVuKyDKHPPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public GiaoVuKyDKHPPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ LIST PANEL --------------------------------
		JPanel listPanel = new JPanel(new GridBagLayout());
		JScrollPane gvListScrollPane = new JScrollPane();
		JTable giaoVuTable = new JTable();
		giaoVuTable.setModel(new DefaultTableModel(KyDKHPDAO.getObjectMatrix(),
				new String[] { "Học kì", "Ngày bắt đầu", "Ngày kết thúc" }));
		giaoVuTable.setRowHeight(30);
		gvListScrollPane.setViewportView(giaoVuTable);

		listPanel.add(gvListScrollPane, gbc.setFill(GridBagConstraints.BOTH).setWeight(1, 1));
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách học kì"));
		this.add(listPanel, gbc.setFill(GridBagConstraints.BOTH).setWeight(1, 1));

		// ------------------------ INFO PANEL --------------------------------
		JPanel infoPanel = new JPanel(new GridBagLayout());

		Dimension nameDim = new Dimension(110, 25), dataDim = new Dimension(40, 25);
		JPanel tenKyDKHPPanel = new InfoPanelElement("Tên học kì: ", nameDim, dataDim);
		JPanel namHocPanel = new InfoPanelElement("Năm học: ", nameDim, dataDim);
		JPanel ngayBatDauPanel = new InfoPanelElement("Ngày bắt đầu: ", nameDim, dataDim);
		JPanel ngayKetThucPanel = new InfoPanelElement("Ngày kết thúc: ", nameDim, dataDim);

		gbc = new GBCBuilder(1, 1);
		infoPanel.add(tenKyDKHPPanel, gbc.setInsets(0, 0, 20, 20));
		infoPanel.add(namHocPanel, gbc.setGrid(1, 2));
		infoPanel.add(ngayBatDauPanel, gbc.setGrid(1, 3));
		infoPanel.add(ngayKetThucPanel, gbc.setGrid(1, 4).setInsets(0, 0, 0, 20));

		infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		this.add(infoPanel, new GBCBuilder(2, 1).setSpan(1, 2).setFill(GridBagConstraints.BOTH).setInsets(0, 10, 0, 0));

		// ------------------------ BUTTON PANEL --------------------------------
		JPanel buttonPanel = new JPanel(new GridBagLayout());

		JButton addKyDKHPBtn = new JButton();
		addKyDKHPBtn.setText("Tạo kỳ đăng ký học phần");
		addKyDKHPBtn.addActionListener(this);
		buttonPanel.add(addKyDKHPBtn);

		JButton deleteKyDKHPBtn = new JButton();
		deleteKyDKHPBtn.setText("Xoá kỳ đăng ký học phần");
		deleteKyDKHPBtn.addActionListener(this);
		buttonPanel.add(deleteKyDKHPBtn);

		buttonPanel.add(addKyDKHPBtn, new GBCBuilder(1, 1).setInsets(0, 0, 0, 10));
		buttonPanel.add(deleteKyDKHPBtn, new GBCBuilder(2, 1).setInsets(0));

		this.add(buttonPanel, new GBCBuilder(1, 2).setFill(GridBagConstraints.BOTH));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
