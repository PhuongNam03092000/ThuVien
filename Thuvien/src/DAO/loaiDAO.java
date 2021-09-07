package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.loaiDTO;

import java.util.HashMap;
import javax.swing.JOptionPane;

public class loaiDAO {

    MyConnectUnit conn = null;
    ResultSet rs = null;
    private ArrayList<loaiDTO> dstl=null;

    public loaiDAO() {
        conn = new MyConnectUnit("localhost", "root", "", "thuvien","theloaiDAO");
    }

    public void docDSTL() throws Exception {
        if(dstl==null)
            dstl = new ArrayList<loaiDTO>();
        rs = conn.Select("theloai");

        while (rs.next()) {
            loaiDTO loai = new loaiDTO();
            loai.setMaloai(rs.getString(1));
            loai.setTenloai(rs.getString(2));
            loai.setTrangthai(Integer.parseInt(rs.getString(3)));
            dstl.add(loai);
        }
        conn.Close();
    }

    public ArrayList<loaiDTO> filteredList() throws Exception {
        docDSTL();
        ArrayList temp = new ArrayList();
        for (loaiDTO loai : dstl) {
            if (loai.getTrangthai() == 1) {
                temp.add(loai);
            }
        }
        return temp;
    }

    public void Insert(loaiDTO s) throws Exception {
        HashMap<String, Object> insertValue = new HashMap<String, Object>();
        insertValue.put("MATHELOAI", s.getMaloai());
        insertValue.put("TENTHELOAI", s.getTenloai());
        insertValue.put("TRANGTHAI", s.getTrangthai());
        boolean kt = conn.Insert("theloai", insertValue);
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    }

    public void Update(loaiDTO s) throws Exception {
        HashMap<String, Object> updateValue = new HashMap<String, Object>();
        updateValue.put("MATHELOAI", s.getMaloai());
        updateValue.put("TENTHELOAI", s.getTenloai());
        updateValue.put("trangthai", "1");
        boolean kt = conn.Update("theloai", updateValue, "matheloai = '" + s.getMaloai() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }

    public void Delete(loaiDTO s) throws Exception {
        boolean kt = conn.Delete("loai", "matheloai = '" + s.getMaloai() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }
}
