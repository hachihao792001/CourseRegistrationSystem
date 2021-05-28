package course_registration_system;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public List<InfoPanelElement> elementList = new ArrayList<InfoPanelElement>();

	public InfoPanel(GridBagLayout gridBagLayout) {
		super(gridBagLayout);
	}

	public void add(InfoPanelElement comp, Object constraints) {
		super.add(comp, constraints);
		elementList.add(comp);
	}
}
