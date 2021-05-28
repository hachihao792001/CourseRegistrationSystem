package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import daos.GiaoVuDAO;

public class GiaoVuTaiKhoanPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel listPanel, buttonPanel;
	InfoPanel infoPanel;

	public GiaoVuTaiKhoanPanel() {

		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		// ------------------------ LIST PANEL --------------------------------
		listPanel = new JPanel(new GridBagLayout());
		JScrollPane gvListScrollPane = new JScrollPane();
		JTable giaoVuTable = new JTable(GiaoVuDAO.getObjectMatrix(),
				new String[] { "Tài khoản", "Tên giáo vụ", "Giới tính", "Ngày sinh" });
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
		listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách giáo vụ"));
		this.add(listPanel, gbc.setFill(GridBagConstraints.BOTH).setWeight(1, 1));

		// ------------------------ INFO PANEL --------------------------------
		infoPanel = new InfoPanel(new GridBagLayout());

		Dimension nameDim = InfoPanelElement.defaultNameLabelDim, dataDim = InfoPanelElement.defaultDataLabelDim;
		InfoPanelElement taiKhoanPanel = new InfoPanelElement("Tài khoản: ", nameDim, dataDim);
		InfoPanelElement tenGVPanel = new InfoPanelElement("Tên giáo vụ: ", nameDim, dataDim);
		InfoPanelElement gioiTinhPanel = new InfoPanelElement("Giới tính: ", nameDim, dataDim);
		InfoPanelElement ngSinhPanel = new InfoPanelElement("Ngày sinh: ", nameDim, dataDim);

		gbc = new GBCBuilder(1, 1);
		infoPanel.add(taiKhoanPanel, gbc.setInsets(0, 0, 15, 0));
		infoPanel.add(tenGVPanel, gbc.setGrid(1, 2));
		infoPanel.add(gioiTinhPanel, gbc.setGrid(1, 3));
		infoPanel.add(ngSinhPanel, gbc.setGrid(1, 4));
		for (int i = 0; i < infoPanel.elementList.size(); i++) 
			infoPanel.elementList.get(i).elementDataLabel.setText(giaoVuTable.getValueAt(0, i).toString());

		JButton modifyPassBtn = new JButton("Đổi mật khẩu");
		infoPanel.add(modifyPassBtn, gbc.setGrid(1, 5).setInsets(10, 70, 0, 70));

		infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		this.add(infoPanel, new GBCBuilder(2, 1).setSpan(1, 2).setFill(GridBagConstraints.BOTH).setInsets(0, 10, 0, 0));

		// ------------------------ BUTTON PANEL --------------------------------
		buttonPanel = new JPanel();

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
