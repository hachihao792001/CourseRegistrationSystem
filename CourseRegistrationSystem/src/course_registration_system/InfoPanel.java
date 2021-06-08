package course_registration_system;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public int padding = 20, buttonPadding = 10;

	JList<String> theJList;
	public String[] elementNames;
	public String[] elementDatas;

	public InfoPanel(String[] elementNames, String[] buttonTexts, ActionListener buttonListener) {
		this.setLayout(new GridBagLayout());
		this.elementNames = elementNames;
		this.elementDatas = new String[elementNames.length];

		GBCBuilder gbc = new GBCBuilder(1, 1);
		String[] jListStrings = new String[elementNames.length];
		for (int i = 0; i < elementNames.length; i++)
			jListStrings[i] = elementNames[i] + elementDatas[i];

		theJList = new JList<String>(jListStrings);
		theJList.setFont(new Font("Dialog", Font.PLAIN, 14));
		theJList.setBackground(null);
		theJList.setFixedCellHeight(40);

		this.add(theJList, gbc.setGrid(1, 1));

		if (buttonTexts != null) {
			for (int i = 0; i < buttonTexts.length; i++) {
				JButton newButton = new JButton(buttonTexts[i]);
				newButton.addActionListener(buttonListener);
				newButton.setActionCommand(buttonTexts[i]);
				this.add(newButton, gbc.setGrid(1, i + 2).setInsets(buttonPadding, 0, 0, 0).setSpan(1, 1));
			}
		}

		this.setBorder(BorderFactory.createTitledBorder("Thông tin"));
	}

	public void updateInfo() {

		String[] jListStrings = new String[elementNames.length];
		for (int i = 0; i < elementNames.length; i++)
			jListStrings[i] = elementNames[i] + elementDatas[i];

		theJList.setListData(jListStrings);
	}
}
