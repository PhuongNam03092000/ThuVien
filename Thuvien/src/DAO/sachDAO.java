package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import DTO.sachDTO;

public class sachDAO {

    MyConnectUnit conn;
    ResultSet rs;
    private static ArrayList<sachDTO> dss;

    public sachDAO() {
        conn = new MyConnectUnit("localhost", "root", "", "thuvien","sachDAO");
    }

    public ArrayList<sachDTO> getdss() {
        return dss;
    }

    public void docDSS() throws Exception {
        dss = new ArrayList<sachDTO>();
        rs = conn.Select("sach");
        while (rs.next()) {
            sachDTO s = new sachDTO();
            s.setMasach(rs.getString(1));
            s.setTensach(rs.getString(2));
            s.setGiasach(Long.parseLong(rs.getString(3)));
            s.setMatheloai(rs.getString(4));
            s.setMatg(rs.getString(5));
            s.setManxb(rs.getString(6));
            s.setMalinhvuc(rs.getString(7));
            s.setHinhanh(rs.getString(8));
            s.setSoluong(Integer.parseInt(rs.getString(9)));
            s.setTrangthai(Integer.parseInt(rs.getString(10)));
            dss.add(s);
        }
        conn.Close();
    }

    public void Insert(sachDTO s) throws Exception {
        HashMap<String, Object> insertValue = new HashMap<String, Object>();
        insertValue.put("MASACH", s.getMasach());
        insertValue.put("TENSACH", s.getTensach());
        insertValue.put("GIASACH", s.getGiasach());
        insertValue.put("MATHELOAI", s.getMatheloai());
        insertValue.put("MATG", s.getMatg());
        insertValue.put("MANXB", s.getManxb());
        insertValue.put("MALINHVUC", s.getMalinhvuc());
        insertValue.put("HINHANH", s.getHinhanh());
        insertValue.put("SOLUONG", s.getSoluong());
        insertValue.put("TRANGTHAI", s.getTrangthai());
        boolean kt = conn.Insert("sach", insertValue);
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    }

    public void Delete(sachDTO s) throws Exception {
        boolean kt = conn.Delete("sach", "masach = '" + s.getMasach() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }

    public void Update(sachDTO s) throws Exception {
        HashMap<String, Object> updateValue = new HashMap<String, Object>();
        //updateValue.put("masach", s.getMasach());
        updateValue.put("tensach", s.getTensach());
        updateValue.put("giasach", s.getGiasach());
        updateValue.put("matheloai", s.getMatheloai());
        updateValue.put("matg", s.getMatg());
        updateValue.put("manxb", s.getManxb());
        updateValue.put("malinhvuc", s.getMalinhvuc());
        updateValue.put("hinhanh", s.getHinhanh());
        updateValue.put("soluong", s.getSoluong());
        updateValue.put("trangthai", "1");
        boolean kt = conn.Update("sach", updateValue, "masach = '" + s.getMasach() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }

    public ArrayList filteredList() throws Exception {
        docDSS();
        ArrayList temp = new ArrayList();
        for (sachDTO sach : dss) {
            if (sach.getTrangthai() == 1) {
                temp.add(sach);
            }
        }
        return temp;
    }

}
