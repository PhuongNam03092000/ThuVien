package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import DTO.docgiaDTO;

public class TableDocGia extends JPanel {

	public JTable table;
	private modelDG dgModel;

	public TableDocGia() {

		dgModel = new modelDG();
		table = new JTable(dgModel);
		table.setEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(5);
		table.getColumnModel().getColumn(5).setPreferredWidth(15);
		table.getColumnModel().getColumn(6).setPreferredWidth(15);
		table.setRowHeight(30);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);

	}

	public void setData(ArrayList<docgiaDTO> list) {
		dgModel.setData(list);
	}

	public int getSelectedRow() {
		return table.getSelectedRow();
	}

	public void refresh() {
		dgModel.fireTableDataChanged();
	}

	public void loadData() {
		dgModel.loadData();
	}
	
	public void addData(docgiaDTO dg) {
		dgModel.addRow(dg);
	}

	public void deleteData(docgiaDTO dg, int i) {
		dgModel.deleteData(dg, i);
	}

	public void updateData(docgiaDTO dg, int i) {
		dgModel.updateData(dg, i);
	}

	public modelDG getModel() {
		return dgModel;
	}

	public void setRowSorter(TableRowSorter sorter) {
		table.setRowSorter(sorter);
	}

	public JTable getTable() {
		return table;
	}

}
