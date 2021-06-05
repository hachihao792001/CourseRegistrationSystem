package course_registration_system;

import java.awt.*;
import javax.swing.*;

public class InfoPanelElement extends JPanel {
	private static final long serialVersionUID = 1L;

	public JLabel elementNameLabel;
	public JLabel elementDataLabel;

	public InfoPanelElement(String labelText) {
		this.setLayout(new FlowLayout());
		elementNameLabel = new JLabel(labelText);
		elementNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		this.add(elementNameLabel, new GBCBuilder(1, 1).setInsets(0, 10, 0, 10));

		elementDataLabel = new JLabel();
		elementDataLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		this.add(elementDataLabel, new GBCBuilder(2, 1).setFill(GridBagConstraints.BOTH));
	}
}
