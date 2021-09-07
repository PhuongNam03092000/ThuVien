package GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import BUS.phieumuonBUS;
import DTO.phieumuonDTO;

public class modelPM extends DefaultTableModel {

    public ArrayList<phieumuonDTO> phieumuonList;
    private static String[] colName = {"Mã phiếu mượn", "Mã nhân viên", "Mã thẻ", "Ngày mượn",
        "Ngày quy định trả", "Tổng tiền mượn"};

    public modelPM() {
        super(colName, 0);
    }

    public void setData(ArrayList<phieumuonDTO> list) {
        this.phieumuonList = list;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void loadData() {
        deleteAll();
        for (phieumuonDTO pm : phieumuonList) {
            Vector<String> row = new Vector<String>();
            row.add(pm.getMapm());
            row.add(pm.getManv());
            row.add(pm.getMathe());
            row.add(pm.getNgaymuon());
            row.add(pm.getNgayquidinhtra());
            row.add(String.valueOf(pm.getTongtienmuon()));
            super.addRow(row);
        }
    }

    public void addRow(phieumuonDTO pm) {
        if (pm == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<Object> row = new Vector<Object>();
        row.add(pm.getMapm());
        row.add(pm.getManv());
        row.add(pm.getMathe());
        row.add(pm.getNgaymuon());
        row.add(pm.getNgayquidinhtra());
        row.add(pm.getTongtienmuon());
        super.addRow(row);
    }

    public void deleteData(int i) {
        super.removeRow(i);
    }

    public void updateData(phieumuonDTO pm, int i) {
        String[] temp = new String[]{pm.getManv(), pm.getMathe(), pm.getNgaymuon(),
            pm.getNgayquidinhtra()};
        for (int j = 0; j < colName.length - 2; j++) {
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

    public phieumuonDTO getPM(int index) {
        return phieumuonList.get(index);
    }
}
