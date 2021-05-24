package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GiaoVuTaiKhoanPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public GiaoVuTaiKhoanPanel() {
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
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách giáo vụ"));
		this.add(listPanel, gbc.setFill(GridBagConstraints.BOTH));

		// ------------------------ INFO PANEL --------------------------------
		JPanel infoPanel = new JPanel(new GridBagLayout());

		Dimension nameDim = InfoPanelElement.defaultNameLabelDim, dataDim = InfoPanelElement.defaultDataLabelDim;
		JPanel maGVPanel = new InfoPanelElement("Mã giáo vụ: ", nameDim, dataDim);
		JPanel tenGVPanel = new InfoPanelElement("Tên giáo vụ: ", nameDim, dataDim);
		JPanel gioiTinhPanel = new InfoPanelElement("Giới tính: ", nameDim, dataDim);
		JPanel ngSinhPanel = new InfoPanelElement("Ngày sinh: ", nameDim, dataDim);

		infoPanel.add(maGVPanel, gbc.setInsets(0, 0, 15, 0));
		infoPanel.add(tenGVPanel, gbc.setGrid(1, 2));
		infoPanel.add(gioiTinhPanel, gbc.setGrid(1, 3));
		infoPanel.add(ngSinhPanel, gbc.setGrid(1, 4));

		JButton modifyPassBtn = new JButton("Đổi mật khẩu");
		infoPanel.add(modifyPassBtn, gbc.setGrid(1, 5).setInsets(10, 70, 0, 70));

		infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		this.add(infoPanel, new GBCBuilder(2, 1).setSpan(1, 2).setFill(GridBagConstraints.BOTH).setInsets(0, 10, 0, 0));

		// ------------------------ BUTTON PANEL --------------------------------
		JPanel buttonPanel = new JPanel();

		JButton findAccountBtn = new JButton();
		findAccountBtn.setText("Tìm tài khoản");
		findAccountBtn.addActionListener(this);

		JButton addAccountBtn = new JButton();
		addAccountBtn.setText("Thêm tài khoản");
		addAccountBtn.addActionListener(this);
		buttonPanel.add(addAccountBtn);

		JButton deleteAccountBtn = new JButton();
		deleteAccountBtn.setText("Xoá tài khoản");
		deleteAccountBtn.addActionListener(this);
		buttonPanel.add(deleteAccountBtn);

		buttonPanel.add(findAccountBtn);
		buttonPanel.add(addAccountBtn);
		buttonPanel.add(deleteAccountBtn);

		this.add(buttonPanel, new GBCBuilder(1, 2).setFill(GridBagConstraints.BOTH));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
