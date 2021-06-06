package course_registration_system;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MultiTextFieldDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	public String[] result;
	List<JTextField> textFields;

	public MultiTextFieldDialog(String[] elementNames, String[] elementDatas, String title) {
		JPanel theContentPane = new JPanel(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		textFields = new ArrayList<JTextField>();
		for (int i = 0; i < elementNames.length; i++) {
			JLabel label = new JLabel(elementNames[i]);
			JTextField textField = new JTextField(elementDatas[i]);
			if (elementDatas[i] == null || elementDatas[i].isEmpty())
				textField.setPreferredSize(new Dimension(100, 20));

			textFields.add(textField);

			theContentPane.add(label,
					gbc.setGrid(1, i + 1).setWeight(1, 0).setInsets(5).setFill(GridBagConstraints.BOTH));
			theContentPane.add(textField,
					gbc.setGrid(2, i + 1).setWeight(1, 0).setInsets(5).setFill(GridBagConstraints.BOTH));
		}

		JButton okButton = new JButton("OK");
		theContentPane.add(okButton,
				gbc.setGrid(1, elementNames.length + 1).setFill(GridBagConstraints.NONE).setSpan(2, 1));
		okButton.addActionListener(this);

		getRootPane().setDefaultButton(okButton);
		this.setTitle(title);
		this.setContentPane(theContentPane);
		this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		this.pack();
	}

	public String[] showDialog() {
		setVisible(true);
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.result = new String[textFields.size()];
		for (int i = 0; i < textFields.size(); i++)
			this.result[i] = textFields.get(i).getText();
		this.setVisible(false);
		this.dispose();
	}
}
