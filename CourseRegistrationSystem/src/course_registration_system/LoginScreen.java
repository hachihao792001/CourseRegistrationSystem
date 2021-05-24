package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public LoginScreen() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;
		JPanel loginContent = new JPanel(new GridBagLayout());

		Title = new JLabel();
		accLabel = new JLabel();
		accText = new JTextField();
		passLabel = new JLabel();
		passText = new JTextField();
		loginButton = new JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(337, 171));
		loginContent.setLayout(new java.awt.GridBagLayout());

		Title.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		Title.setText("Phần mềm đăng ký học phần");
		Title.setAlignmentY(0.0F);
		Title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		Title.setVerifyInputWhenFocusTarget(false);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
		loginContent.add(Title, gridBagConstraints);

		accLabel.setText("Tài khoản");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
		loginContent.add(accLabel, gridBagConstraints);

		accText.setText("jTextField1");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
		loginContent.add(accText, gridBagConstraints);

		passLabel.setText("Mật khẩu");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
		loginContent.add(passLabel, gridBagConstraints);

		passText.setText("jTextField1");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		loginContent.add(passText, gridBagConstraints);

		loginButton.setText("jButton1");
		loginButton.setActionCommand("login");
		loginButton.addActionListener(this);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
		loginContent.add(loginButton, gridBagConstraints);

		this.setTitle("Đăng nhập");
		this.setContentPane(loginContent);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private JLabel Title;
	private JLabel accLabel;
	private JTextField accText;
	private JButton loginButton;
	private JLabel passLabel;
	private JTextField passText;

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "login":
			setVisible(false);
			dispose();
			new MainScreen();
			break;

		default:
			break;
		}
	}
}
