package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import daos.HocPhanDAO;

public class GiaoVuHocPhanPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel listPanel, buttonPanel;
	InfoPanel infoPanel;

	public GiaoVuHocPhanPanel() {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new JPanel(new GridBagLayout());
		JScrollPane gvListScrollPane = new JScrollPane();
		JTable giaoVuTable = new JTable();
		giaoVuTable.setModel(new DefaultTableModel(HocPhanDAO.getObjectMatrix(), new String[] { "Mã môn", "Tên môn",
				"Số tín chỉ", "Giáo viên lý thuyết", "Tên phòng", "Thứ", "Ca", "Slot tối đa" }));
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
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách học phần"));
		this.add(listPanel, gbc.setFill(GridBagConstraints.BOTH));

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new GridBagLayout());

		Dimension nameDim = new Dimension(140, 25), dataDim = InfoPanelElement.defaultDataLabelDim;
		InfoPanelElement maMonPanel = new InfoPanelElement("Mã môn: ", nameDim, dataDim);
		InfoPanelElement tenMonPanel = new InfoPanelElement("Tên môn: ", nameDim, dataDim);
		InfoPanelElement soTinChiPanel = new InfoPanelElement("Số tín chỉ: ", nameDim, dataDim);
		InfoPanelElement gvltPanel = new InfoPanelElement("Giáo viên lý thuyết: ", nameDim, dataDim);
		InfoPanelElement tenPhongHocPanel = new InfoPanelElement("Tên phòng học: ", nameDim, dataDim);
		InfoPanelElement thuPanel = new InfoPanelElement("Thứ: ", nameDim, dataDim);
		InfoPanelElement caPanel = new InfoPanelElement("Ca: ", nameDim, dataDim);
		InfoPanelElement maxSlotPanel = new InfoPanelElement("Số slot tối đa: ", nameDim, dataDim);

		gbc = new GBCBuilder(1, 1);
		infoPanel.add(maMonPanel, gbc.setInsets(0, 0, 5, 0));
		infoPanel.add(tenMonPanel, gbc.setGrid(1, 2));
		infoPanel.add(soTinChiPanel, gbc.setGrid(1, 3));
		infoPanel.add(gvltPanel, gbc.setGrid(1, 4));
		infoPanel.add(tenPhongHocPanel, gbc.setGrid(1, 5));
		infoPanel.add(thuPanel, gbc.setGrid(1, 6));
		infoPanel.add(caPanel, gbc.setGrid(1, 7));
		infoPanel.add(maxSlotPanel, gbc.setGrid(1, 8).setInsets(0));
		for (int i = 0; i < infoPanel.elementList.size(); i++)
			infoPanel.elementList.get(i).elementDataLabel.setText(giaoVuTable.getValueAt(0, i).toString());

		JButton xemDanhSachSVBtn = new JButton("Xem danh sách sinh viên đăng ký");
		infoPanel.add(xemDanhSachSVBtn, gbc.setGrid(1, 9).setInsets(15, 0, 0, 0));

		infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		this.add(infoPanel, new GBCBuilder(2, 1).setSpan(1, 2).setFill(GridBagConstraints.BOTH).setInsets(0, 10, 0, 0));

		// ------------------------ BUTTON PANEL --------------------------------
		JPanel buttonPanel = new JPanel(new GridBagLayout());

		JButton findHocPhanBtn = new JButton();
		findHocPhanBtn.setText("Tìm học phần");
		findHocPhanBtn.addActionListener(this);

		JButton addHocPhanBtn = new JButton();
		addHocPhanBtn.setText("Thêm học phần");
		addHocPhanBtn.addActionListener(this);
		buttonPanel.add(addHocPhanBtn);

		JButton deleteHocPhanBtn = new JButton();
		deleteHocPhanBtn.setText("Xoá học phần");
		deleteHocPhanBtn.addActionListener(this);
		buttonPanel.add(deleteHocPhanBtn);

		buttonPanel.add(findHocPhanBtn);
		buttonPanel.add(addHocPhanBtn);
		buttonPanel.add(deleteHocPhanBtn);

		this.add(buttonPanel, new GBCBuilder(1, 2).setFill(GridBagConstraints.BOTH));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
