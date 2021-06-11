package course_registration_system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.RowFilter;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ListPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	JTable theTable;
	String[] columnNames;
	JTextField findTextField;
	TableRowSorter<TableModel> sorter;

	public ListPanel(InfoPanel infoPanel, Object[][] objectMatrix, String[] columnNames, String[] actionButtonTexts,
			ActionListener actionListener, String tableTitle) {
		this.setLayout(new GridBagLayout());
		GBCBuilder gbc = new GBCBuilder(1, 1);
		this.columnNames = columnNames;

		findTextField = new JTextField();
		findTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				RowFilter<TableModel, Object> rf = null;

				try {
					int columnCount = theTable.getColumnCount();
					int[] columns = new int[columnCount];
					for (int i = 0; i < columnCount; i++)
						columns[i] = i;

					rf = RowFilter.regexFilter(findTextField.getText(), columns);
				} catch (java.util.regex.PatternSyntaxException ex) {
					return;
				}

				sorter.setRowFilter(rf);
				sorter.setSortsOnUpdates(true);
				sorter.addRowSorterListener(new RowSorterListener() {
					@Override
					public void sorterChanged(RowSorterEvent e) {
						if (e.getType() == RowSorterEvent.Type.SORTED) {
							theTable.revalidate();
							theTable.repaint();
						}
					}
				});
			}
		});

		JPanel findPanel = new JPanel(new GridBagLayout());
		JLabel findLabel = new JLabel("Tìm kiếm");

		findPanel.add(findLabel, gbc.setInsets(0, 0, 0, 10));
		findPanel.add(findTextField,
				gbc.setGrid(2, 1).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0).setInsets(0));
		this.add(findPanel, gbc.setSpan(actionButtonTexts.length, 1).setGrid(1, 1)
				.setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0));

		JScrollPane theScrollPane = new JScrollPane();
		theTable = new JTable();
		updateTable(objectMatrix);
		theTable.setRowHeight(30);
		if (infoPanel != null) {
			theTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					if (theTable.getRowCount() > 0) {
						int selectedRow = theTable.getSelectedRow();
						if (selectedRow == -1)
							selectedRow = 0;
						for (int i = 0; i < infoPanel.elementDatas.length; i++)
							infoPanel.elementDatas[i] = theTable.getValueAt(selectedRow, i).toString();
					} else {
						for (int i = 0; i < infoPanel.elementDatas.length; i++)
							infoPanel.elementDatas[i] = "";
					}
					infoPanel.updateInfo();

				}
			});
		}
		setTableSelectedRow(0);
		theScrollPane.setViewportView(theTable);
		this.add(theScrollPane, gbc.setSpan(actionButtonTexts.length, 1).setGrid(1, 2).setFill(GridBagConstraints.BOTH)
				.setWeight(1, 1));

		for (int i = 0; i < actionButtonTexts.length; i++) {
			JButton newButton = new JButton(actionButtonTexts[i]);
			newButton.setActionCommand(actionButtonTexts[i]);
			newButton.addActionListener(actionListener);
			this.add(newButton, gbc.setGrid(i + 1, 3).setSpan(1, 1).setWeight(1, 0));

		}
		sorter = new TableRowSorter<TableModel>(theTable.getModel());
		theTable.setRowSorter(sorter);

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
		sorter = new TableRowSorter<TableModel>(theTable.getModel());
		theTable.setRowSorter(sorter);
	}

	public void setTableSelectedRow(int row) {
		if (theTable.getRowCount() > 0)
			theTable.setRowSelectionInterval(row, row);
	}
}
