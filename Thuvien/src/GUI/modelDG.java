package GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import DTO.docgiaDTO;

public class modelDG extends DefaultTableModel {

    private ArrayList<docgiaDTO> dgList;

    private static String[] colName = {"Mã DG", "Họ đọc giả", "Tên độc giả", "Ngày sinh", "Địa chỉ", "Nghề Nghiệp", "Trình độ"};

    public modelDG() {
        super(colName, 0);
    }

    public void setData(ArrayList<docgiaDTO> list) {
        this.dgList = list;
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

    public void loadData() {
        deleteAll();
        for (docgiaDTO dg : dgList) {
            Vector<String> row = new Vector<>();
            row.add(dg.getMadg());
            row.add(dg.getHodg());
            row.add(dg.getTendg());;
            row.add(dg.getNgaysinh());
            row.add(dg.getDiachi());
            row.add(dg.getNghenghiep());
            row.add(dg.getTrinhdo());
            super.addRow(row);
        }
    }

    public void addRow(docgiaDTO dg) {
        if (dg == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<Object> rowVector = new Vector<>();
        rowVector.add(dg.getMadg());
        rowVector.add(dg.getHodg());
        rowVector.add(dg.getTendg());
        rowVector.add(dg.getNgaysinh());
        rowVector.add(dg.getNghenghiep());
        rowVector.add(dg.getTrinhdo());
        super.addRow(rowVector);
    }

    public void deleteData(docgiaDTO dg, int i) {
        super.removeRow(i);
    }

    public void updateData(docgiaDTO dg, int i) {
        String[] temp = new String[]{dg.getHodg(), dg.getTendg(),
            dg.getNgaysinh(), dg.getDiachi(), dg.getNghenghiep(), dg.getTrinhdo()};
        for (int j = 0; j < colName.length - 2; j++) {
            super.setValueAt(temp[j], i, j + 1);
        }
    }

    public docgiaDTO getDG(int index) {
        return dgList.get(index);
    }

}
