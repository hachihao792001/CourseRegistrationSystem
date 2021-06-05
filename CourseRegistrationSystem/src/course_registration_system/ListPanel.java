package course_registration_system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ListPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ListPanel(InfoPanel infoPanel, Object[][] objectMatrix, String[] columnNames, String[] actionButtonTexts,
			ActionListener actionListener) {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);

		JScrollPane theScrollPane = new JScrollPane();
		JTable theTable = new JTable();
		theTable.setModel(new DefaultTableModel(objectMatrix, columnNames));
		theTable.setRowHeight(30);
		theTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				for (int i = 0; i < infoPanel.elementList.size(); i++) {
					infoPanel.elementList.get(i).elementDataLabel
							.setText(theTable.getValueAt(theTable.getSelectedRow(), i).toString());
				}
			}
		});
		theScrollPane.setViewportView(theTable);
		this.add(theScrollPane,
				gbc.setSpan(actionButtonTexts.length, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1));

		for (int i = 0; i < actionButtonTexts.length; i++) {
			JButton newButton = new JButton(actionButtonTexts[i]);
			newButton.setActionCommand(actionButtonTexts[i]);
			newButton.addActionListener(actionListener);
			this.add(newButton, gbc.setGrid(i + 1, 2).setSpan(1, 1).setWeight(1, 0));

		}

		this.setBorder(BorderFactory.createTitledBorder("Danh sách môn học"));
	}
}
