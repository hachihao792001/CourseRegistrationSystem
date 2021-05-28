package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import daos.LopHocDAO;

public class GiaoVuLopHocPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel listPanel, buttonPanel;
	InfoPanel infoPanel;

	public GiaoVuLopHocPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new JPanel(new GridBagLayout());
		JScrollPane gvListScrollPane = new JScrollPane();

		JTable lopHocTable = new JTable();
		lopHocTable.setModel(new DefaultTableModel(LopHocDAO.getObjectMatrix(),
				new String[] { "Mã lớp", "Tổng số sinh viên", "Tổng số nam", "Tổng số nữ" }));
		lopHocTable.setRowHeight(30);
		lopHocTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				for (int i = 0; i < infoPanel.elementList.size(); i++) {
					infoPanel.elementList.get(i).elementDataLabel
							.setText(lopHocTable.getValueAt(lopHocTable.getSelectedRow(), i).toString());
				}
			}
		});
		gvListScrollPane.setViewportView(lopHocTable);

		listPanel.add(gvListScrollPane, gbc.setFill(GridBagConstraints.BOTH).setWeight(1, 1));
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách lớp học"));
		this.add(listPanel, gbc.setFill(GridBagConstraints.BOTH));

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new GridBagLayout());

		Dimension nameDim = new Dimension(135, 25), dataDim = new Dimension(70, 25);
		InfoPanelElement maLopPanel = new InfoPanelElement("Mã lớp: ", nameDim, dataDim);
		InfoPanelElement tongSoSVPanel = new InfoPanelElement("Tổng số sinh viên: ", nameDim, dataDim);
		InfoPanelElement tongSoNamPanel = new InfoPanelElement("Tổng số nam: ", nameDim, dataDim);
		InfoPanelElement tongSoNuPanel = new InfoPanelElement("Tổng số nữ: ", nameDim, dataDim);

		gbc = new GBCBuilder(1, 1);
		infoPanel.add(maLopPanel, gbc.setInsets(0, 0, 20, 0));
		infoPanel.add(tongSoSVPanel, gbc.setGrid(1, 2));
		infoPanel.add(tongSoNamPanel, gbc.setGrid(1, 3));
		infoPanel.add(tongSoNuPanel, gbc.setGrid(1, 4).setInsets(0));
		for (int i = 0; i < infoPanel.elementList.size(); i++)
			infoPanel.elementList.get(i).elementDataLabel.setText(lopHocTable.getValueAt(0, i).toString());

		JButton danhSachSVBtn = new JButton("Xem danh sách sinh viên");
		infoPanel.add(danhSachSVBtn, gbc.setGrid(1, 5).setInsets(25, 0, 0, 0));

		infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		this.add(infoPanel, new GBCBuilder(2, 1).setSpan(1, 2).setFill(GridBagConstraints.BOTH).setInsets(0, 10, 0, 0));

		// ------------------------ BUTTON PANEL --------------------------------
		buttonPanel = new JPanel(new GridBagLayout());

		JButton addLopHocBtn = new JButton();
		addLopHocBtn.setText("Thêm lớp học");
		addLopHocBtn.addActionListener(this);
		buttonPanel.add(addLopHocBtn);

		JButton deleteLopHocBtn = new JButton();
		deleteLopHocBtn.setText("Xoá lớp học");
		deleteLopHocBtn.addActionListener(this);
		buttonPanel.add(deleteLopHocBtn);

		buttonPanel.add(addLopHocBtn, new GBCBuilder(1, 1).setInsets(0, 0, 0, 10));
		buttonPanel.add(deleteLopHocBtn, new GBCBuilder(2, 1).setInsets(0));

		this.add(buttonPanel, new GBCBuilder(1, 2).setFill(GridBagConstraints.BOTH));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
