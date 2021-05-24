package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GiaoVuHocPhanPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public GiaoVuHocPhanPanel() {
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
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách học phần"));
		this.add(listPanel, gbc.setFill(GridBagConstraints.BOTH));

		// ------------------------ INFO PANEL --------------------------------
		JPanel infoPanel = new JPanel(new GridBagLayout());

		Dimension nameDim = new Dimension(140, 25), dataDim = InfoPanelElement.defaultDataLabelDim;
		JPanel maMonPanel = new InfoPanelElement("Mã môn: ", nameDim, dataDim);
		JPanel tenMonPanel = new InfoPanelElement("Tên môn: ", nameDim, dataDim);
		JPanel soTinChiPanel = new InfoPanelElement("Số tín chỉ: ", nameDim, dataDim);
		JPanel gvltPanel = new InfoPanelElement("Giáo viên lý thuyết: ", nameDim, dataDim);
		JPanel tenPhongHocPanel = new InfoPanelElement("Tên phòng học: ", nameDim, dataDim);
		JPanel thuPanel = new InfoPanelElement("Thứ: ", nameDim, dataDim);
		JPanel caPanel = new InfoPanelElement("Ca: ", nameDim, dataDim);
		JPanel maxSlotPanel = new InfoPanelElement("Số slot tối đa: ", nameDim, dataDim);

		infoPanel.add(maMonPanel, gbc.setInsets(0, 0, 5, 0));
		infoPanel.add(tenMonPanel, gbc.setGrid(1, 2));
		infoPanel.add(soTinChiPanel, gbc.setGrid(1, 3));
		infoPanel.add(gvltPanel, gbc.setGrid(1, 4));
		infoPanel.add(tenPhongHocPanel, gbc.setGrid(1, 5));
		infoPanel.add(thuPanel, gbc.setGrid(1, 6));
		infoPanel.add(caPanel, gbc.setGrid(1, 7));
		infoPanel.add(maxSlotPanel, gbc.setGrid(1, 8).setInsets(0));
		
		JButton xemDanhSachSVBtn = new JButton("Xem danh sách sinh viên đăng ký");
		infoPanel.add(xemDanhSachSVBtn, gbc.setGrid(1, 9).setInsets(15, 70, 0, 70));

		infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		this.add(infoPanel, new GBCBuilder(2, 1).setSpan(1, 2).setFill(GridBagConstraints.BOTH).setInsets(0, 10, 0, 0));

		// ------------------------ BUTTON PANEL --------------------------------
		JPanel buttonPanel = new JPanel();

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
