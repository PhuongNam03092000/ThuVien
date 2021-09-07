package DAO;

import DTO.nguoidungDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import DTO.nhanvienDTO;

public class nhanvienDAO {

    MyConnectUnit conn = null;
    ResultSet rs = null;
    ArrayList<nhanvienDTO> dsnv = null;

    public nhanvienDAO() {

        conn = new MyConnectUnit("localhost", "root", "", "thuvien","nhanvienDAO");
    }

    public ArrayList<nhanvienDTO> docDSNV() throws Exception {
        dsnv = new ArrayList<nhanvienDTO>();
        rs = conn.Select("nhanvien");
        while (rs.next()) {
            nhanvienDTO nhanvien = new nhanvienDTO();
            nhanvien.setManv(rs.getString(1));
            nhanvien.setHo(rs.getString(2));
            nhanvien.setTen(rs.getString(3));
            nhanvien.setNgaysinh(rs.getString(4));
            nhanvien.setGioitinh(rs.getString(5));
            nhanvien.setDiachi(rs.getString(6));
            nhanvien.setEmail(rs.getString(7));
            nhanvien.setSdt(rs.getString(8));
            nhanvien.setLuong(rs.getString(9));
            nhanvien.setTrangthai(Integer.parseInt(rs.getString(10)));
            dsnv.add(nhanvien);
        }
        conn.Close();
        return dsnv;
    }

    public void Insert(nhanvienDTO nv) throws Exception {
        HashMap<String, Object> insertValue = new HashMap<String, Object>();
        insertValue.put("manv", nv.getManv());
        insertValue.put("honv", nv.getHo());
        insertValue.put("tennv", nv.getTen());
        insertValue.put("ngaysinh", nv.getNgaysinh());
        insertValue.put("gioitinh", nv.getGioitinh());
        insertValue.put("diachinv", nv.getDiachi());
        insertValue.put("emailnv", nv.getEmail());
        insertValue.put("sdtnv", nv.getSdt());
        insertValue.put("luong", nv.getLuong());
        insertValue.put("trangthai", 1);
        boolean kt = conn.Insert("nhanvien", insertValue);
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
        conn.Close();
    }

    public void Delete(nhanvienDTO nv) throws Exception {
        boolean kt = conn.Delete("nhanvien", "manv = '" + nv.getManv() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
        conn.Close();
    }

    public void Update(nhanvienDTO nv) throws Exception {
        HashMap<String, Object> updateValue = new HashMap<String, Object>();
        updateValue.put("honv", nv.getHo());
        updateValue.put("tennv", nv.getTen());
        updateValue.put("ngaysinh", nv.getNgaysinh());
        updateValue.put("gioitinh", nv.getGioitinh());
        updateValue.put("diachinv", nv.getDiachi());
        updateValue.put("emailnv", nv.getEmail());
        updateValue.put("sdtnv", nv.getSdt());
        updateValue.put("luong", nv.getLuong());
        updateValue.put("trangthai", 1);
        boolean kt = conn.Update("nhanvien", updateValue, "manv = '" + nv.getManv() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
        conn.Close();
    }

    public ArrayList<nhanvienDTO> filteredList() {
        try {
            dsnv = docDSNV();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<nhanvienDTO> edited = new ArrayList<nhanvienDTO>();
        for (nhanvienDTO nv : dsnv) {
            if (nv.getTrangthai() == 1) {
                edited.add(nv);
            }
        }

        return edited;
    }
    
    public ArrayList<nhanvienDTO> filteredListForSelecting() throws Exception {
            rs = conn.Select("nhanvien","manv,honv,tennv", "exists (select manv,honv,tennv from nhanvien,nguoidung where nhanvien.manv = nguoidung.manv)");
            ArrayList<nhanvienDTO> temp = new ArrayList<>();
            while(rs.next()){
               nhanvienDTO nv = new nhanvienDTO();
               nv.setManv(rs.getString(1));
               nv.setHo(rs.getString(2));
               nv.setTen(rs.getString(3));
               temp.add(nv);
            }
            return temp;
	}

}
