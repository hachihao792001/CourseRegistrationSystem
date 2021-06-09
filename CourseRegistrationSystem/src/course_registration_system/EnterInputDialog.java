package course_registration_system;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EnterInputDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	public String[] result;
	public JComponent[] elementInputs;

	public EnterInputDialog(String[] elementNames, JComponent[] elementInputs, String[] defaultValues, String title) {
		this.elementInputs = elementInputs;

		JPanel theContentPane = new JPanel(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		for (int i = 0; i < elementNames.length; i++) {
			JLabel label = new JLabel(elementNames[i]);
			elementInputs[i].setPreferredSize(new Dimension(100, 20));

			String currentDefaultValue;
			if (defaultValues == null || defaultValues[i] == null)
				currentDefaultValue = "";
			else
				currentDefaultValue = defaultValues[i];

			if (elementInputs[i] instanceof JTextField)
				((JTextField) elementInputs[i]).setText(currentDefaultValue);
			else if (elementInputs[i] instanceof JComboBox<?>)
				((JComboBox<?>) elementInputs[i]).setSelectedItem(currentDefaultValue);

			theContentPane.add(label,
					gbc.setGrid(1, i + 1).setWeight(1, 0).setInsets(5).setFill(GridBagConstraints.BOTH));
			theContentPane.add(elementInputs[i],
					gbc.setGrid(2, i + 1).setWeight(1, 0).setInsets(5).setFill(GridBagConstraints.BOTH));
		}

		JButton okButton = new JButton("OK");
		theContentPane.add(okButton,
				gbc.setGrid(1, elementNames.length + 1).setFill(GridBagConstraints.NONE).setSpan(2, 1));
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				result = new String[elementInputs.length];
				for (int i = 0; i < elementInputs.length; i++) {
					if (elementInputs[i] instanceof JTextField)
						result[i] = ((JTextField) elementInputs[i]).getText();
					else if (elementInputs[i] instanceof JComboBox<?>)
						result[i] = ((JComboBox<?>) elementInputs[i]).getSelectedItem().toString();
				}
				setVisible(false);
				dispose();
			}
		});

		getRootPane().setDefaultButton(okButton);
		this.setTitle(title);
		this.setContentPane(theContentPane);
		this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		this.setLocationRelativeTo(null);
		this.pack();
	}

	public String[] showDialog() {
		setVisible(true);
		return result;
	}
}
