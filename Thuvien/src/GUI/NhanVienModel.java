package GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import DTO.nhanvienDTO;
import TOOL.ThongTinEvent;

public class NhanVienModel extends DefaultTableModel {

    private ArrayList<nhanvienDTO> nvList;
    private static String[] colName = {"MNV", "Họ", "Tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Email", "Số điện thoại", "Lương"}; 
   // private String[] colName2 = {"MNV","Họ","Tên"};
    public NhanVienModel() {
        super(colName, 0);

    }
    
    
   
    

    public void setData(ArrayList<nhanvienDTO> list) {
        this.nvList = list;
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
        for (nhanvienDTO nv : nvList) {
            Vector<String> row = new Vector<String>();
            row.add(nv.getManv());
            row.add(nv.getHo());
            row.add(nv.getTen());
            row.add(nv.getNgaysinh());
            row.add(nv.getGioitinh());
            row.add(nv.getDiachi());
            row.add(nv.getEmail());
            row.add(nv.getSdt());
            row.add(nv.getLuong());
            super.addRow(row);
        }
    }

    public void addRow(nhanvienDTO nv) {
        if (nv == null) {
            throw new IllegalArgumentException("NULL");
        }
        Vector<Object> rowVector = new Vector<>();
        rowVector.add(nv.getManv());
        rowVector.add(nv.getHo());
        rowVector.add(nv.getTen());
        rowVector.add(nv.getNgaysinh());
        rowVector.add(nv.getGioitinh());
        rowVector.add(nv.getDiachi());
        rowVector.add(nv.getEmail());
        rowVector.add(nv.getSdt());
        rowVector.add(nv.getLuong());
        super.addRow(rowVector);
    }

    public void deleteData(nhanvienDTO nv, int i) {
        super.removeRow(i);
    }

    public void updateRow(String maNV, ThongTinEvent evt) {
        for (int i = 0; i < super.getRowCount(); i++) {
            if (super.getValueAt(i, 0).equals(maNV)) {
                super.setValueAt((String) evt.getHo(), i, 1);
                super.setValueAt((String) evt.getTen(), i, 2);
                super.setValueAt((String) evt.getNgaysinh(), i, 3);
                super.setValueAt((String) evt.getGioitinh(), i, 4);
                super.setValueAt((String) evt.getDiachi(), i, 5);
                super.setValueAt((String) evt.getEmail(), i, 6);
                super.setValueAt((String) evt.getSdt(), i, 7);
                super.setValueAt((String) evt.getLuong(), i, 8);
            }
        }

    }

    public void updateData(nhanvienDTO nv, int i) {
        String[] temp = new String[]{nv.getHo(), nv.getTen(), nv.getNgaysinh(),
            nv.getGioitinh(), nv.getDiachi(), nv.getEmail(), nv.getSdt(),
            nv.getLuong()};
        for (int j = 0; j < colName.length - 2; j++) {
            super.setValueAt(temp[j], i, j + 1);
        }
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
    public nhanvienDTO getNV(int index) {
        return nvList.get(index);
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
