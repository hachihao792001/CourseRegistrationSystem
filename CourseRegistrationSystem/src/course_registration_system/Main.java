package course_registration_system;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import javax.swing.UIManager;
import javax.xml.bind.DatatypeConverter;

public class Main {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public static String[] dsGioiTinh = new String[] { "Nam", "Nữ" };
	public static String[] dsTenHocKi = new String[] { "HK1", "HK2", "HK3" };
	public static String[] dsThu = new String[] { "Hai", "Ba", "Tư", "Năm", "Sáu", "Bảy" };
	public static String[] dsCa = new String[] { "7:30 – 9:30", "9:30 – 11:30", "13:30 – 15:30", "15:30 – 17:30" };

	public static String hash(char[] pass) {
		String myHash = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(String.valueOf(pass).getBytes());
			byte[] digest = md.digest();
			myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return myHash;
	}

	static void createAndShowUI() throws IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new LoginScreen();

	}

	public static void main(String args[]) throws IOException {
		createAndShowUI();
	}
}
