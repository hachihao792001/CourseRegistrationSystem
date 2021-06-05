package course_registration_system;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public List<InfoPanelElement> elementList = new ArrayList<InfoPanelElement>();
	public JButton actionButton;
	public int padding = 20, buttonPadding = 10;
	public Object[] firstRowTable;

	public InfoPanel(List<String> infoPanelElementNames) {
		super(new GridBagLayout());

		for (String name : infoPanelElementNames)
			elementList.add(new InfoPanelElement(name));

	}

	public void build() {
		GBCBuilder gbc = new GBCBuilder(1, 1);
		for (int i = 0; i < elementList.size(); i++)
			super.add(elementList.get(i), gbc.setGrid(1, i + 1).setInsets(0, 0, padding, 0));
		if (actionButton != null)
			super.add(actionButton, gbc.setGrid(1, elementList.size() + 1).setInsets(buttonPadding, 0, 0, 0));

		if (firstRowTable != null) {
			for (int i = 0; i < elementList.size(); i++)
				elementList.get(i).elementDataLabel.setText(firstRowTable[i].toString());
		}

		this.setBorder(BorderFactory.createTitledBorder("Thông tin"));
	}
}
