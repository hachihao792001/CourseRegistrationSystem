package course_registration_system;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ModifyDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	public class ModifyInfoElement extends JPanel {

		private static final long serialVersionUID = 1L;

		public JLabel label;
		public JTextField textField;

		public ModifyInfoElement(String labelText, String textFieldText) {
			this.setLayout(new GridBagLayout());
			GBCBuilder gbc = new GBCBuilder(1, 1).setWeight(1, 0);

			label = new JLabel(labelText);
			textField = new JTextField(textFieldText);
			textField.setPreferredSize(new Dimension(100, 20));

			this.add(label, gbc.setAnchor(GridBagConstraints.LINE_START));
			this.add(textField, gbc.setGrid(2, 1).setAnchor(GridBagConstraints.LINE_END));
		}
	}

	public List<ModifyInfoElement> elementList;
	public String[] result;

	public ModifyDialog(String[] elementNames, String[] elementDatas) {
		JPanel theContentPane = new JPanel(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		elementList = new ArrayList<ModifyInfoElement>();
		for (int i = 0; i < elementNames.length; i++) {
			elementList.add(new ModifyInfoElement(elementNames[i], elementDatas[i]));
			theContentPane.add(elementList.get(i),
					gbc.setGrid(1, i + 1).setWeight(1, 0).setInsets(5).setFill(GridBagConstraints.BOTH));
		}

		JButton okButton = new JButton("OK");
		theContentPane.add(okButton, gbc.setGrid(1, elementNames.length + 1).setFill(GridBagConstraints.NONE));
		okButton.addActionListener(this);

		this.setTitle("Cập nhật thông tin");
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
		this.result = new String[elementList.size()];
		for (int i = 0; i < elementList.size(); i++)
			this.result[i] = elementList.get(i).textField.getText();
		this.setVisible(false);
		this.dispose();
	}
}
