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

	JTable theTable;
	String[] columnNames;

	public ListPanel(InfoPanel infoPanel, Object[][] objectMatrix, String[] columnNames, String[] actionButtonTexts,
			ActionListener actionListener, String tableTitle) {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);
		this.columnNames = columnNames;

		JScrollPane theScrollPane = new JScrollPane();
		theTable = new JTable();
		updateTable(objectMatrix);
		theTable.setRowHeight(30);
		if (infoPanel != null) {
			theTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					int selectedRow = theTable.getSelectedRow();
					if (selectedRow == -1)
						selectedRow = 0;
					for (int i = 0; i < infoPanel.elementDatas.length; i++)
						infoPanel.elementDatas[i] = theTable.getValueAt(selectedRow, i).toString();

					infoPanel.updateInfo();
				}
			});
		}
		setTableSelectedRow(0);
		theScrollPane.setViewportView(theTable);
		this.add(theScrollPane,
				gbc.setSpan(actionButtonTexts.length, 1).setFill(GridBagConstraints.BOTH).setWeight(1, 1));

		for (int i = 0; i < actionButtonTexts.length; i++) {
			JButton newButton = new JButton(actionButtonTexts[i]);
			newButton.setActionCommand(actionButtonTexts[i]);
			newButton.addActionListener(actionListener);
			this.add(newButton, gbc.setGrid(i + 1, 2).setSpan(1, 1).setWeight(1, 0));

		}

		this.setBorder(BorderFactory.createTitledBorder("Danh sách " + tableTitle));
	}

	public void updateTable(Object[][] objectMatrix) {
		theTable.setModel(new DefaultTableModel(objectMatrix, columnNames) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}

	public void setTableSelectedRow(int row) {
		theTable.setRowSelectionInterval(row, row);
	}
}
