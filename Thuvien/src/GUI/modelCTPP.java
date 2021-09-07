package GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DTO.ctppDTO;

public class modelCTPP extends DefaultTableModel {

    public ArrayList<ctppDTO> ctppList;
    private static String[] colName = {"Mã sách", "Số lượng", "Mã quy định", "Thành tiền̉"};

    public modelCTPP() {
        super(colName, 0);
    }

    public void setData(ArrayList<ctppDTO> list) {
        this.ctppList = list;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void loadData() {
        deleteAll();
        for (ctppDTO ctp : ctppList) {
            Vector<String> row = new Vector<String>();
            row.add(ctp.getMasach());
            row.add(String.valueOf(ctp.getSoluong()));
            row.add(ctp.getMaqd());
            row.add(String.valueOf(ctp.getThanhtien()));
            super.addRow(row);
        }
    }

    public void addRow(ctppDTO ctpp) {
        if (ctpp == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<String> rowVector = new Vector<>();
        rowVector.add(ctpp.getMasach());
        rowVector.add(String.valueOf(ctpp.getSoluong()));
        rowVector.add(ctpp.getMaqd());
        rowVector.add(String.valueOf(ctpp.getThanhtien()));
        super.addRow(rowVector);
    }

    public void updateRow(ctppDTO ctpp) {
        for (int i = 0; i < super.getRowCount(); i++) {
            if (super.getValueAt(i, 0).equals(ctpp.getMasach())) {
                super.setValueAt(ctpp.getMasach(), i, 0);
                super.setValueAt(String.valueOf(ctpp.getSoluong()), i, 1);
                super.setValueAt(ctpp.getMaqd(), i, 2);
                super.setValueAt(String.valueOf(ctpp.getThanhtien()), i, 3);
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
