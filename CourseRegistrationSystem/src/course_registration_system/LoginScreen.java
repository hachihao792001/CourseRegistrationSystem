package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import daos.TaiKhoanDAO;
import pojo.TaiKhoan;

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

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(337, 171));
		loginContent.setLayout(new GridBagLayout());

		Title.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setText("Phần mềm đăng ký học phần");
		Title.setAlignmentY(0.0F);
		Title.setHorizontalTextPosition(SwingConstants.CENTER);
		Title.setVerifyInputWhenFocusTarget(false);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(20, 0, 20, 0);
		loginContent.add(Title, gridBagConstraints);

		accLabel.setText("Tài khoản");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(0, 0, 10, 10);
		loginContent.add(accLabel, gridBagConstraints);

		accText.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(0, 0, 10, 0);
		loginContent.add(accText, gridBagConstraints);

		passLabel.setText("Mật khẩu");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(0, 0, 0, 10);
		loginContent.add(passLabel, gridBagConstraints);

		passText.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		loginContent.add(passText, gridBagConstraints);

		loginButton.setText("Đăng nhập");
		loginButton.setActionCommand("login");
		loginButton.addActionListener(this);
		getRootPane().setDefaultButton(loginButton);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(20, 0, 20, 0);
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
			List<TaiKhoan> taiKhoans = TaiKhoanDAO.layDanhSachTaiKhoan();
			TaiKhoan loginAcc = null;
			for (TaiKhoan taiKhoan : taiKhoans) {
				if (taiKhoan.getTenTaiKhoan().equals(accText.getText())) {
					if (taiKhoan.getMatKhau().equals(passText.getText()))
						loginAcc = taiKhoan;
					break;
				}
			}

			if (loginAcc != null) {
				setVisible(false);
				dispose();
				new MainScreen(loginAcc);
			} else
				JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng", "Lỗi đăng nhập",
						JOptionPane.WARNING_MESSAGE, null);
			break;

		default:
			break;
		}
	}
}
