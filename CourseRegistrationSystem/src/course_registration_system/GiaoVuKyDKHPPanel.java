package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import daos.KyDKHPDAO;

public class GiaoVuKyDKHPPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel listPanel, buttonPanel;
	InfoPanel infoPanel;

	public GiaoVuKyDKHPPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new JPanel(new GridBagLayout());
		JScrollPane gvListScrollPane = new JScrollPane();
		JTable giaoVuTable = new JTable();
		giaoVuTable.setModel(new DefaultTableModel(KyDKHPDAO.getObjectMatrix(),
				new String[] { "Năm học", "Học kì", "Lần", "Ngày bắt đầu", "Ngày kết thúc" }));
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
		InfoPanelElement namHocPanel = new InfoPanelElement("Năm học: ", nameDim, dataDim);
		InfoPanelElement tenKyDKHPPanel = new InfoPanelElement("Học kì: ", nameDim, dataDim);
		InfoPanelElement lanPanel = new InfoPanelElement("Lần: ", nameDim, dataDim);
		InfoPanelElement ngayBatDauPanel = new InfoPanelElement("Ngày bắt đầu: ", nameDim, dataDim);
		InfoPanelElement ngayKetThucPanel = new InfoPanelElement("Ngày kết thúc: ", nameDim, dataDim);

		gbc = new GBCBuilder(1, 1);
		infoPanel.add(namHocPanel, gbc.setInsets(0, 0, 20, 20));
		infoPanel.add(tenKyDKHPPanel, gbc.setGrid(1, 2).setInsets(0, 0, 20, 20));
		infoPanel.add(lanPanel, gbc.setGrid(1, 3));
		infoPanel.add(ngayBatDauPanel, gbc.setGrid(1, 4));
		infoPanel.add(ngayKetThucPanel, gbc.setGrid(1, 5).setInsets(0, 0, 0, 20));
		for (int i = 0; i < infoPanel.elementList.size(); i++)
			infoPanel.elementList.get(i).elementDataLabel.setText(giaoVuTable.getValueAt(0, i).toString());

		infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		this.add(infoPanel, new GBCBuilder(2, 1).setSpan(1, 2).setFill(GridBagConstraints.BOTH).setInsets(0, 10, 0, 0));

		// ------------------------ BUTTON PANEL --------------------------------
		buttonPanel = new JPanel(new GridBagLayout());

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
