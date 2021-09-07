package GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import DTO.sachDTO;
import BUS.sachBUS;
import javax.swing.JOptionPane;

public class modelSach extends DefaultTableModel {

    public ArrayList<sachDTO> sachList;
    private static String[] colName = {"Mã sách", "Tên sách", "Gía sách", "Mã thể loại", "Mã tác giả", "Mã NXB", "Mã lĩnh vực", "Số lượng"};

    public modelSach() {
        super(colName, 0);
    }

    @Override
    public boolean isCellEditable(int col, int row) {
        return false;
    }

    public void setData(ArrayList<sachDTO> list) {
        this.sachList = list;
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
        sachBUS bus = new sachBUS();
        setData(bus.getSachList());
        for (sachDTO nv : sachList) {
            Vector<String> row = new Vector<String>();
            row.add(nv.getMasach());
            row.add(nv.getTensach());
            row.add(String.valueOf(nv.getGiasach()));
            row.add(nv.getMatheloai());
            row.add(nv.getMatg());
            row.add(nv.getManxb());
            row.add(nv.getMalinhvuc());
            row.add(String.valueOf(nv.getSoluong()));
            row.add(nv.getHinhanh());
            row.add(String.valueOf(nv.getTrangthai()));
            super.addRow(row);
        }
    }

    public void addRow(sachDTO nv) {
        if (nv == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<String> row = new Vector<String>();
        row.add(nv.getMasach());
        row.add(nv.getTensach());
        row.add(String.valueOf(nv.getGiasach()));
        row.add(nv.getMatheloai());
        row.add(nv.getMatg());
        row.add(nv.getManxb());
        row.add(nv.getMalinhvuc());
        row.add(String.valueOf(nv.getSoluong()));
        row.add(nv.getHinhanh());
        row.add(String.valueOf(nv.getTrangthai()));
        super.addRow(row);
    }

    /*@Override
	public String getColumnName(int col) {
		return colName[col];
	}

	@Override
	public int getColumnCount() {
		if(null != colName) {
		    return colName.length;
		} else {
		    return 0;
		}
	}*/

    public sachDTO getSach(int index) {
        return sachList.get(index);
    }

    public void set(int row, sachDTO nv) {
        super.setValueAt(nv.getTensach(), row, 1);
        super.setValueAt(nv.getGiasach(), row, 2);
        super.setValueAt(nv.getMatheloai(), row, 3);
        super.setValueAt(nv.getMatg(), row, 4);
        super.setValueAt(nv.getManxb(), row, 5);
        super.setValueAt(nv.getMalinhvuc(), row, 6);
        super.setValueAt(nv.getSoluong(), row, 7);
    }

    public void removeRow(int index) {
        super.removeRow(index);
    }
    /*public nhanvienDTO getNV(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}*/

 /*@Override
	public Object getValueAt(int row, int col) {
		nhanvienDTO nv = nvList.get(row);
		switch(col) {
		case 0:
			return nv.getManv();
		case 1:
			return nv.getHo();
		case 2:
			return nv.getTen();
		case 3:
			return nv.getNgaysinh();
		case 4:
			return nv.getGioitinh();
		case 5:
			return nv.getDiachi();
		case 6:
			return nv.getEmail();
		case 7:
			return nv.getSdt();
		case 8:
			return nv.getLuong();
		case 9:
			return nv.getTrangthai();
		}
		return null;
	}*/
}
