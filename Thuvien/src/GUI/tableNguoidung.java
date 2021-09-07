package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import DTO.nguoidungDTO;
import DTO.nhanvienDTO;

public class tableNguoidung extends JPanel {

    public JTable table;
    private modelND model;

    public tableNguoidung() {
        model = new modelND();
        table = new JTable(model);
        table.setEnabled(true);
        table.setRowHeight(30);
        table.setFont(new Font("Calibri", Font.PLAIN, 18));
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(ArrayList<nguoidungDTO> list) {
        model.setData(list);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public void refresh() {
        model.fireTableDataChanged();
    }

    public void loadData() {
        model.loadData();
    }

    public void addData(nguoidungDTO nd) {
        model.addRow(nd);
    }

    public void deleteData(nguoidungDTO nd, int i) {
        model.deleteData(nd, i);
    }

    public void updateData(nguoidungDTO nd, int i) {
        model.updateData(nd, i);
    }

    public modelND getModel() {
        return model;
    }

    public void setRowSorter(TableRowSorter sorter) {
        table.setRowSorter(sorter);
    }

    public JTable getTable() {
        return table;
    }
}
