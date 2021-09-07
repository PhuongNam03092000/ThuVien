package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import DTO.phieuphatDTO;

public class tablePP extends JPanel{
	public JTable table ;
	private modelPP model;
	TableRowSorter<DefaultTableModel> tr;
	
	public tablePP() {
		model = new modelPP();
		table = new JTable(model);
		tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		
		table.setEnabled(true);
		setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		add(new JScrollPane(table),BorderLayout.CENTER);
	}
	
	public void setData(ArrayList<phieuphatDTO> list) {
		model.setData(list);
	}
	
	public void refresh() {
		model.fireTableDataChanged();
	}
	
	public void loadData() {
		model.loadData();
	}
	
	public void addData(phieuphatDTO pp) {
		model.addRow(pp);
	}
	
	public void  deleteData(int i) {
		model.deleteData(i);
	}
	
	public void updateData(phieuphatDTO pp,int i ) {
		model.updateData(pp,i);
	}
	
	public modelPP getModel() {
		return model;
	}
	
	public JTable getTable() {
		return table;
	}

	public TableRowSorter<DefaultTableModel> getTr() {
		return tr;
	}

	public void setTr(TableRowSorter<DefaultTableModel> tr) {
		this.tr = tr;
	}
	
	public phieuphatDTO getPP(int i) {
		phieuphatDTO pp = new phieuphatDTO();
		pp.setMapp((String)table.getValueAt(i, 0));
		pp.setMapm((String)table.getValueAt(i, 1));
		pp.setTongtien(Long.parseLong((String)table.getValueAt(i, 2)));
		pp.setNgaylap((String)table.getValueAt(i, 3));
		return pp;
	}
	
}
