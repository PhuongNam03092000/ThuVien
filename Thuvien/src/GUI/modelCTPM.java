package GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DTO.ctpmDTO;

public class modelCTPM extends DefaultTableModel {

    public ArrayList<ctpmDTO> ctpmList;
    private static String[] colName = {"Mã sách", "Số lượng", "Tình trạng", "Tiền thế chân"};

    public modelCTPM() {
        super(colName, 0);
    }

    public void setData(ArrayList<ctpmDTO> list) {
        this.ctpmList = list;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void loadData() {
        deleteAll();
        for (ctpmDTO ctp : ctpmList) {
            Vector<String> row = new Vector<String>();
            row.add(ctp.getMasach());
            row.add(String.valueOf(ctp.getSoluong()));
            row.add(ctp.getTinhtrang());
            row.add(String.valueOf(ctp.getTienthechan()));
            super.addRow(row);
        }
    }

    public void addRow(ctpmDTO ctpm) {
        if (ctpm == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<String> rowVector = new Vector<>();
        rowVector.add(ctpm.getMasach());
        rowVector.add(String.valueOf(ctpm.getSoluong()));
        rowVector.add(ctpm.getTinhtrang());
        rowVector.add(String.valueOf(ctpm.getTienthechan()));
        super.addRow(rowVector);
    }

    public void updateRow(ctpmDTO ctpm) {
        for (int i = 0; i < super.getRowCount(); i++) {
            if (super.getValueAt(i, 0).equals(ctpm.getMasach())) {
                super.setValueAt(ctpm.getMasach(), i, 0);
                super.setValueAt(String.valueOf(ctpm.getSoluong()), i, 1);
                super.setValueAt(ctpm.getTinhtrang(), i, 2);
                super.setValueAt(String.valueOf(ctpm.getTienthechan()), i, 3);
            }
        }
    }

    public void deleteRow(int i) {
        super.removeRow(i);
    }

    public void deleteAll() {
        if (super.getRowCount() > 0) {
            for (int i = super.getRowCount() - 1; i > -1; i--) {
                super.removeRow(i);
            }
        }
    }
}
