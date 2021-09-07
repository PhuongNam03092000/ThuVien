package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.nxbDTO;

import java.util.HashMap;
import javax.swing.JOptionPane;

public class nxbDAO {

    MyConnectUnit conn = null;
    ResultSet rs = null;
    private ArrayList<nxbDTO> dsnxb;

    public nxbDAO() {
        conn = new MyConnectUnit("localhost", "root", "", "thuvien","nxbDAO");
    }

    public void docDSNXB() throws Exception {
        dsnxb = new ArrayList<nxbDTO>();
        rs = conn.Select("nxb");

        while (rs.next()) {
            nxbDTO loai = new nxbDTO();
            loai.setManxb(rs.getString(1));
            loai.setTenxb(rs.getString(2));
            loai.setSdt(rs.getString(3));
            loai.setEmail(rs.getString(4));
            loai.setTrangthai(Integer.parseInt(rs.getString(5)));
            dsnxb.add(loai);
        }
        conn.Close();
    }

    public ArrayList<nxbDTO> filteredList() throws Exception {
        docDSNXB();
        ArrayList temp = new ArrayList();
        for (nxbDTO nxb : dsnxb) {
            if (nxb.getTrangthai() == 1) {
                temp.add(nxb);
            }
        }
        return temp;
    }

    public void Insert(nxbDTO s) throws Exception {
        HashMap<String, Object> insertValue = new HashMap<String, Object>();
        insertValue.put("MANXB", s.getManxb());
        insertValue.put("TENNXB", s.getTenxb());
        insertValue.put("SDTNXB", s.getSdt());
        insertValue.put("EMAILNXB", s.getEmail());
        insertValue.put("TRANGTHAI", s.getTrangthai());
        boolean kt = conn.Insert("nxb", insertValue);
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    }

    public void Update(nxbDTO s) throws Exception {
        HashMap<String, Object> updateValue = new HashMap<String, Object>();
        updateValue.put("MANXB", s.getManxb());
        updateValue.put("TENNXB", s.getTenxb());
        updateValue.put("SDTNXB", s.getSdt());
        updateValue.put("EMAILNXB", s.getEmail());
        updateValue.put("trangthai", "1");
        boolean kt = conn.Update("nxb", updateValue, "manxb = '" + s.getManxb() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }

    public void Delete(nxbDTO s) throws Exception {
        boolean kt = conn.Delete("nxb", "manxb = '" + s.getManxb() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }
}
