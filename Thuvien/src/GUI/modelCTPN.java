/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.phieunhapBUS;
import DTO.ctpnDTO;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class modelCTPN extends DefaultTableModel {

    public ArrayList<ctpnDTO> ctpnList;
    public static String[] colName = {"Mã phiếu nhập", "Mãsách", "Số lượng ", "Đơn giá", "Thành tiền"};

    public modelCTPN() {
        super(colName, 0);
    }

    public void setData(ArrayList<ctpnDTO> list) {
        this.ctpnList = list;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
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
        for (ctpnDTO ctpn : ctpnList) {
            Vector<String> row = new Vector<>();
            row.add(ctpn.getMapn());
            row.add(ctpn.getMasach());
            row.add(String.valueOf(ctpn.getSoluong()));
            row.add(String.valueOf(ctpn.getDongia()));
            row.add(String.valueOf(ctpn.getThanhtien()));
            super.addRow(row);
        }
    }

    public void addRow(ctpnDTO ctpn) {
        if (ctpnList == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<Object> row = new Vector<>();
        row.add(ctpn.getMapn());
        row.add(ctpn.getMasach());
        row.add(String.valueOf(ctpn.getSoluong()));
        row.add(String.valueOf(ctpn.getDongia()));
        row.add(String.valueOf(ctpn.getThanhtien()));
        super.addRow(row);
    }

    public void delete(int i) {
        super.removeRow(i);
    }
}
