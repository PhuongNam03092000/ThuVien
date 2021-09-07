package GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DTO.phieuphatDTO;

public class modelPP extends DefaultTableModel {

    public ArrayList<phieuphatDTO> phieuphatList;
    private static String[] colName = {"Mã phiếu phạt", "Mã phiếu mượn", "Tổng tiền̉", "Ngày lập"};

    public modelPP() {
        super(colName, 0);
    }

    public void setData(ArrayList<phieuphatDTO> list) {
        this.phieuphatList = list;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void loadData() {
        deleteAll();
        for (phieuphatDTO pp : phieuphatList) {
            Vector<String> row = new Vector<String>();
            row.add(pp.getMapp());
            row.add(pp.getMapm());
            row.add(String.valueOf(pp.getTongtien()));
            row.add(pp.getNgaylap());
            super.addRow(row);
        }
    }

    public void addRow(phieuphatDTO pp) {
        if (pp == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<Object> row = new Vector<Object>();
        row.add(pp.getMapp());
        row.add(pp.getMapm());
        row.add(pp.getTongtien());
        row.add(pp.getNgaylap());
        super.addRow(row);
    }

    public void deleteData(int i) {
        super.removeRow(i);
    }

    public void updateData(phieuphatDTO pp, int i) {
        String[] temp = new String[]{pp.getMapm(), String.valueOf(pp.getTongtien()), pp.getNgaylap()};
        for (int j = 0; j < colName.length - 1; j++) {
            super.setValueAt(temp[j], i, j + 1);
        }
    }

    public void deleteAll() {
        if (super.getRowCount() > 0) {
            for (int i = super.getRowCount() - 1; i > -1; i--) {
                super.removeRow(i);
            }
        }
    }

    public phieuphatDTO getPP(int index) {
        return phieuphatList.get(index);
    }
}
