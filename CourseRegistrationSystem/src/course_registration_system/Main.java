package course_registration_system;

import java.io.IOException;
import java.util.*;

import javax.swing.UIManager;

import daos.DKHPDAO;
import pojo.*;

public class Main {
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
