package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DTO.ctppDTO;

public class tableCTPP extends JPanel{
	public JTable table;
	private modelCTPP model;
	
	public tableCTPP() {
		model = new modelCTPP();
		table = new JTable(model);
		table.setEnabled(true);
		table.setRowHeight(30);
		table.setFont(new Font("Calibri", Font.PLAIN, 18));
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(ArrayList<ctppDTO> list) {
		model.setData(list);
	}
	
	public void loadData() {
		model.loadData();
	}
	
	public void addData(ctppDTO ctpp) {
		model.addRow(ctpp);
	}

	public void deleteRow(int i) {
		model.deleteRow(i);
	}
	
	public void updateData(ctppDTO ctpp) {
		model.updateRow(ctpp);
	}
	
	public JTable getTable() {
		return table;
	}
	
}
