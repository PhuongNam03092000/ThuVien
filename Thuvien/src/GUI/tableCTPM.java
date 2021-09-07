package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DTO.ctpmDTO;

public class tableCTPM extends JPanel{
	public JTable table;
	private modelCTPM model;
	
	public tableCTPM() {
		model = new modelCTPM();
		table = new JTable(model);
		table.setEnabled(true);
		table.setRowHeight(30);
		table.setFont(new Font("Calibri", Font.PLAIN, 18));
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(ArrayList<ctpmDTO> list) {
		model.setData(list);
	}
	
	public void loadData() {
		model.loadData();
	}
	
	public void addData(ctpmDTO ctpm) {
		model.addRow(ctpm);
	}

	public void deleteRow(int i) {
		model.deleteRow(i);
	}
	
	public void updateData(ctpmDTO ctpm) {
		model.updateRow(ctpm);
	}
	
	public JTable getTable() {
		return table;
	}
	
}
