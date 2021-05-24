package course_registration_system;

import java.awt.*;
import javax.swing.*;

public class InfoPanelElement extends JPanel {
	private static final long serialVersionUID = 1L;

	public JLabel elementNameLabel;
	public JLabel elementDataLabel;

	public static Dimension defaultNameLabelDim = new Dimension(90, 25);
	public static Dimension defaultDataLabelDim = new Dimension(150, 25);

	public InfoPanelElement(String labelText, Dimension nameLabelDim, Dimension dataLabelDim) {
		this.setLayout(new GridBagLayout());
		elementNameLabel = new JLabel(labelText);
		elementNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		elementNameLabel.setPreferredSize(nameLabelDim);
		this.add(elementNameLabel, new GBCBuilder(1, 1).setInsets(0, 10, 0, 10));

		elementDataLabel = new JLabel("stuff");
		elementDataLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		elementDataLabel.setPreferredSize(new Dimension(dataLabelDim));
		this.add(elementDataLabel, new GBCBuilder(2, 1).setFill(GridBagConstraints.BOTH));
	}
}
