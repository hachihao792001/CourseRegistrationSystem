package course_registration_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import daos.HibernateUtil;
import daos.TaiKhoanDAO;
import pojo.TaiKhoan;

public class LoginScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static JOptionPane loading;

	public LoginScreen() {
		initComponents();
	}

	private void initComponents() {
		GBCBuilder gbc;
		JPanel loginContent = new JPanel(new GridBagLayout());

		Title = new JLabel();
		accLabel = new JLabel();
		accText = new JTextField();
		passLabel = new JLabel();
		passText = new JPasswordField();
		loginButton = new JButton();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		loginContent.setLayout(new GridBagLayout());

		Title.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setText("Phần mềm đăng ký học phần");
		Title.setAlignmentY(0.0F);
		Title.setHorizontalTextPosition(SwingConstants.CENTER);
		Title.setVerifyInputWhenFocusTarget(false);
		gbc = new GBCBuilder(1, 1);
		loginContent.add(Title, gbc.setSpan(2, 1).setInsets(20).setFill(GridBagConstraints.BOTH).setWeight(1, 0));

		accLabel.setText("Tài khoản");
		loginContent.add(accLabel, gbc.setGrid(1, 2).setSpan(1, 1).setInsets(0, 20, 10, 0).setWeight(0, 0));

		accText.setText("");
		loginContent.add(accText, gbc.setGrid(2, 2).setInsets(0, 20, 10, 20).setWeight(1, 0));

		passLabel.setText("Mật khẩu");
		loginContent.add(passLabel, gbc.setGrid(1, 3).setInsets(0, 20, 20, 0).setWeight(0, 0));

		passText.setText("");
		loginContent.add(passText, gbc.setGrid(2, 3).setInsets(0, 20, 20, 20).setWeight(1, 0));

		loginButton.setText("Đăng nhập");
		loginButton.setActionCommand("login");
		loginButton.addActionListener(this);
		getRootPane().setDefaultButton(loginButton);
		loginContent.add(loginButton, gbc.setGrid(1, 4).setSpan(2, 1).setInsets(0, 50, 20, 50).setWeight(0, 0));

		this.setTitle("Đăng nhập");
		this.setContentPane(loginContent);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);

		JDialog loadingScreen = new JDialog();
		JPanel loadingContent = new JPanel(new GridBagLayout());
		loadingScreen.setUndecorated(false);
		JLabel loadingLabel = new JLabel("Đang tải dữ liệu...");
		loadingLabel.setFont(new Font("Tahoma", 0, 22));
		loadingContent.add(loadingLabel, new GBCBuilder(1, 1).setInsets(10));
		loadingScreen.setContentPane(loadingContent);
		loadingScreen.setEnabled(false);
		loadingScreen.setUndecorated(true);
		loadingScreen.pack();
		loadingScreen.setLocationRelativeTo(null);
		loadingScreen.setVisible(true);

		HibernateUtil.getSessionFactory().openSession();

		loadingScreen.setVisible(false);
		loadingScreen.dispose();
		this.setVisible(true);
	}

	private JLabel Title;
	private JLabel accLabel;
	private JTextField accText;
	private JButton loginButton;
	private JLabel passLabel;
	private JPasswordField passText;

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "login":
			List<TaiKhoan> taiKhoans = TaiKhoanDAO.layDanhSachTaiKhoan();
			TaiKhoan loginAcc = null;
			for (TaiKhoan taiKhoan : taiKhoans) {
				if (taiKhoan.getTenTaiKhoan().equals(accText.getText())) {
					if (taiKhoan.getMatKhau().equals(Main.hash(passText.getPassword())))
						loginAcc = taiKhoan;
					break;
				}
			}

			if (loginAcc != null) {
				setVisible(false);
				dispose();
				new MainScreen(loginAcc);
			} else {
				JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng", "Lỗi đăng nhập",
						JOptionPane.WARNING_MESSAGE, null);
			}
			break;

		default:
			break;
		}
	}
}
