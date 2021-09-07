package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import DTO.phieumuonDTO;
import DTO.phieunhapDTO;
import GUI.modelPN;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class tablePN extends JPanel {

    public JTable table;
    private modelPN model;

    public tablePN() {
        model = new modelPN();
        table = new JTable(model);
        table.setEnabled(true);
        setLayout(new BorderLayout());
        table.setRowHeight(30);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(ArrayList<phieunhapDTO> list) {
        model.setData(list);
    }

    public void refresh() {
        model.fireTableDataChanged();
    }

    public void loadData() {
        model.loadData();
    }

    public void addData(phieunhapDTO pn) {
        model.addRow(pn);
    }
//	
//	public void  deleteData(phieumuonDTO pm,int i) {
//		model.deleteData(pm, i);
//	}
//	
//	public void updateData(phieumuonDTO pm,int i ) {
//		model.updateData(pm, i);
//	}

    public modelPN getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }

}
