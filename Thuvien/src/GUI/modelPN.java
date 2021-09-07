/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.phieunhapBUS;
import DTO.phieunhapDTO;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class modelPN extends DefaultTableModel {

    public ArrayList<phieunhapDTO> phieunhapList;
    public static String[] colName = {"Mã phiếu nhập", "Mã nhân viên", "Mã nhà cung cấp", "Ngày nhập", "Tổng tiền"};

    public modelPN() {
        super(colName, 0);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void setData(ArrayList<phieunhapDTO> list) {
        this.phieunhapList = list;
    }

    public void deleteAll() {
        if (super.getRowCount() > 0) {
            for (int i = super.getRowCount() - 1; i > -1; i--) {
                super.removeRow(i);
            }
        }
    }

    public void loadData() {
        deleteAll();
        for (phieunhapDTO pn : phieunhapList) {
            Vector<String> row = new Vector<>();
            row.add(pn.getMapn());
            row.add(pn.getManv());
            row.add(pn.getMancc());
            row.add(pn.getNgaynhap());
            row.add(String.valueOf(pn.getTongtien()));
            super.addRow(row);
        }
    }

    public void addRow(phieunhapDTO pn) {
        if (pn == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<Object> row = new Vector<>();
        row.add(pn.getMapn());
        row.add(pn.getManv());
        row.add(pn.getMancc());
        row.add(pn.getNgaynhap());
        row.add(pn.getTongtien());
        super.addRow(row);
    }

}
