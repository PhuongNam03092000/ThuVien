package GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import BUS.nguoidungBUS;
import BUS.phieumuonBUS;
import DTO.nguoidungDTO;
import DTO.phieumuonDTO;

public class modelND extends DefaultTableModel {

    public ArrayList<nguoidungDTO> nguoidungList;
    private static String[] colName = {"Mã nhân viên", "Mật khẩu", "Mã quyền ", "Trạng thái"};

    public modelND() {
        super(colName, 0);
    }

    public void setData(ArrayList<nguoidungDTO> list) {
        this.nguoidungList = list;
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
        nguoidungBUS bus = new nguoidungBUS();
        for (nguoidungDTO nd : nguoidungList) {
            Vector<String> row = new Vector<String>();
            row.add(nd.getManv());
            //row.add(nd.getMkhau());
            String pass = "";
            for (int i = 0; i < nd.getMkhau().length(); i++) {
                pass += "*";
            }
            row.add(pass);
            row.add(nd.getQuyen());
            switch (nd.getTrangthai()) {
                case 0: {
                    row.add("Không hoạt động");
                    break;
                }

                case 1: {
                    row.add("Còn hoạt động");
                    break;
                }
                case 2: {
                    row.add("Bị chặn");
                    break;
                }

            }
            super.addRow(row);
        }
    }

    public void addRow(nguoidungDTO nd) {
        if (nd == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<Object> row = new Vector<Object>();
        row.add(nd.getManv());
        row.add(nd.getMkhau());
        row.add(nd.getQuyen());

        switch (nd.getTrangthai()) {
            case 0: {
                row.add("Không hoạt động");
            }
            break;
            case 1: {
                row.add("Còn hoạt động");
            }
            break;
            case 2: {
                row.add("Bị chặn");
            }
            break;
        }
        super.addRow(row);
    }

    public void deleteData(nguoidungDTO nd, int i) {
        super.removeRow(i);
    }

    public void updateData(nguoidungDTO nd, int i) {
        String[] temp = new String[]{nd.getManv(), nd.getMkhau(), nd.getQuyen()};
        for (int j = 0; j < colName.length - 1; j++) {
            super.setValueAt(temp[j], i, j + 1);
        }
    }

    public nguoidungDTO getND(int index) {
        return nguoidungList.get(index);
    }
}
