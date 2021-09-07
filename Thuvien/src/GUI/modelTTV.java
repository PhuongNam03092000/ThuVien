package GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import DTO.theTVDTO;

public class modelTTV extends DefaultTableModel {

    private ArrayList<theTVDTO> ttvList;

    private static String[] colName = {"Mã thẻ", "Mã độc giả", "Ngày tạo", "Ngày hết hạn"};

    public modelTTV() {
        super(colName, 0);
    }

    public void deleteAll() {
        if (super.getRowCount() > 0) {
            for (int i = super.getRowCount() - 1; i > -1; i--) {
                super.removeRow(i);
            }
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void setData(ArrayList<theTVDTO> list) {
        this.ttvList = list;
    }

    public void loadData() {
        deleteAll();
        for (theTVDTO the : ttvList) {
            Vector<String> row = new Vector<String>();
            row.add(the.getMathe());
            row.add(the.getMadg());
            row.add(the.getNgaycap());
            row.add(the.getNgayhethan());
            super.addRow(row);
        }
    }

    public void addRow(theTVDTO the) {
        if (the == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<Object> rowVector = new Vector<Object>();
        rowVector.add(the.getMathe());
        rowVector.add(the.getMadg());
        rowVector.add(the.getNgaycap());
        rowVector.add(the.getNgayhethan());
        super.addRow(rowVector);
    }

    public void deleteRow(theTVDTO the, int i) {
        super.removeRow(i);
    }

    public void updateData(theTVDTO the, int i) {
        String[] temp = new String[]{the.getMathe(), the.getMadg(), the.getNgaycap(), the.getNgayhethan()};
        for (int j = 0; j < colName.length; j++) {
            super.setValueAt(temp[j], i, j + 1);
        }
    }
}
