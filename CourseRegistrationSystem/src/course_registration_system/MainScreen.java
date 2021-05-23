package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public MainScreen() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;
		JPanel mainContent = new JPanel(new GridBagLayout());

		jTabbedPane1 = new javax.swing.JTabbedPane();
		taiKhoanTab = new javax.swing.JPanel();
		listPanel = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		infoPanel = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jPanel3 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jPanel4 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jTextField4 = new javax.swing.JTextField();
		buttonPanel = new javax.swing.JPanel();
		button1 = new java.awt.Button();
		monHocTab = new javax.swing.JPanel();
		hocKiTab = new javax.swing.JPanel();
		lopHocTab = new javax.swing.JPanel();
		kyDKHPTab = new javax.swing.JPanel();
		hocPhanTab = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(800, 600));
		mainContent.setLayout(new java.awt.CardLayout());

		taiKhoanTab.setPreferredSize(new java.awt.Dimension(277, 272));
		taiKhoanTab.setLayout(new java.awt.GridBagLayout());

		jTable1.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jTable1.setRowHeight(30);
		listPanel.setViewportView(jTable1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		taiKhoanTab.add(listPanel, gridBagConstraints);

		infoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		infoPanel.setLayout(new java.awt.GridBagLayout());

		jPanel1.setLayout(new java.awt.GridBagLayout());

		jLabel2.setText("Mã giáo vụ");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
		jPanel1.add(jLabel2, gridBagConstraints);

		jTextField1.setText("jTextField1");
		jTextField1.setPreferredSize(new java.awt.Dimension(100, 20));
		jPanel1.add(jTextField1, new java.awt.GridBagConstraints());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 1;
		infoPanel.add(jPanel1, gridBagConstraints);

		jPanel2.setLayout(new java.awt.GridBagLayout());

		jLabel3.setText("Tên giáo vụ");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
		jPanel2.add(jLabel3, gridBagConstraints);

		jTextField2.setText("jTextField1");
		jTextField2.setPreferredSize(new java.awt.Dimension(100, 20));
		jPanel2.add(jTextField2, new java.awt.GridBagConstraints());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 2;
		infoPanel.add(jPanel2, gridBagConstraints);

		jPanel3.setLayout(new java.awt.GridBagLayout());

		jLabel4.setText("Giới tính");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
		jPanel3.add(jLabel4, gridBagConstraints);

		jTextField3.setText("jTextField1");
		jTextField3.setPreferredSize(new java.awt.Dimension(100, 20));
		jPanel3.add(jTextField3, new java.awt.GridBagConstraints());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 3;
		infoPanel.add(jPanel3, gridBagConstraints);

		jPanel4.setLayout(new java.awt.GridBagLayout());

		jLabel5.setText("Ngày sinh");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
		jPanel4.add(jLabel5, gridBagConstraints);

		jTextField4.setText("jTextField1");
		jTextField4.setPreferredSize(new java.awt.Dimension(100, 20));
		jPanel4.add(jTextField4, new java.awt.GridBagConstraints());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 4;
		infoPanel.add(jPanel4, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		taiKhoanTab.add(infoPanel, gridBagConstraints);

		buttonPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		button1.setLabel("button1");
		button1.addActionListener(this);
		buttonPanel.add(button1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		taiKhoanTab.add(buttonPanel, gridBagConstraints);

		jTabbedPane1.addTab("Tài khoản", taiKhoanTab);

		javax.swing.GroupLayout monHocTabLayout = new javax.swing.GroupLayout(monHocTab);
		monHocTab.setLayout(monHocTabLayout);
		monHocTabLayout.setHorizontalGroup(monHocTabLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 760, Short.MAX_VALUE));
		monHocTabLayout.setVerticalGroup(monHocTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 407, Short.MAX_VALUE));

		jTabbedPane1.addTab("Môn học", monHocTab);

		javax.swing.GroupLayout hocKiTabLayout = new javax.swing.GroupLayout(hocKiTab);
		hocKiTab.setLayout(hocKiTabLayout);
		hocKiTabLayout.setHorizontalGroup(hocKiTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 760, Short.MAX_VALUE));
		hocKiTabLayout.setVerticalGroup(hocKiTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 407, Short.MAX_VALUE));

		jTabbedPane1.addTab("Học kì", hocKiTab);

		javax.swing.GroupLayout lopHocTabLayout = new javax.swing.GroupLayout(lopHocTab);
		lopHocTab.setLayout(lopHocTabLayout);
		lopHocTabLayout.setHorizontalGroup(lopHocTabLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 760, Short.MAX_VALUE));
		lopHocTabLayout.setVerticalGroup(lopHocTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 407, Short.MAX_VALUE));

		jTabbedPane1.addTab("Lớp học", lopHocTab);

		javax.swing.GroupLayout kyDKHPTabLayout = new javax.swing.GroupLayout(kyDKHPTab);
		kyDKHPTab.setLayout(kyDKHPTabLayout);
		kyDKHPTabLayout.setHorizontalGroup(kyDKHPTabLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 760, Short.MAX_VALUE));
		kyDKHPTabLayout.setVerticalGroup(kyDKHPTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 407, Short.MAX_VALUE));

		jTabbedPane1.addTab("Kỳ đăng ký học phần", kyDKHPTab);

		javax.swing.GroupLayout hocPhanTabLayout = new javax.swing.GroupLayout(hocPhanTab);
		hocPhanTab.setLayout(hocPhanTabLayout);
		hocPhanTabLayout.setHorizontalGroup(hocPhanTabLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 760, Short.MAX_VALUE));
		hocPhanTabLayout.setVerticalGroup(hocPhanTabLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 407, Short.MAX_VALUE));

		jTabbedPane1.addTab("Học phần", hocPhanTab);

		mainContent.add(jTabbedPane1, "giaoVuCard");

		this.setTitle("Phần mềm quản lí học sinh");
		this.setContentPane(mainContent);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private Button button1;
	private JPanel buttonPanel;
	private JPanel hocKiTab;
	private JPanel hocPhanTab;
	private JPanel infoPanel;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JTabbedPane jTabbedPane1;
	private JTable jTable1;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JTextField jTextField3;
	private JTextField jTextField4;
	private JPanel kyDKHPTab;
	private JScrollPane listPanel;
	private JPanel lopHocTab;
	private JPanel monHocTab;
	private JPanel taiKhoanTab;
	// End of variables declaration

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
