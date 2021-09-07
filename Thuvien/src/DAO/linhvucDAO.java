package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.linhvucDTO;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class linhvucDAO {

    MyConnectUnit conn = null;
    ResultSet rs = null;
    private ArrayList<linhvucDTO> dslv;

    public linhvucDAO() {
        conn = new MyConnectUnit("localhost", "root", "", "thuvien","linhvucDAO");
    }

    public void docDSLV() throws Exception {
        dslv = new ArrayList<linhvucDTO>();
        rs = conn.Select("linhvuc");

        while (rs.next()) {
            linhvucDTO linhvuc = new linhvucDTO();
            linhvuc.setMalinhvuc(rs.getString(1));
            linhvuc.setTenlinhvuc(rs.getString(2));
            linhvuc.setTrangthai(Integer.parseInt(rs.getString(3)));
            dslv.add(linhvuc);
        }
        conn.Close();
    }

    public ArrayList<linhvucDTO> filteredList() throws Exception {
        docDSLV();
        ArrayList temp = new ArrayList();
        for (linhvucDTO linhvuc : dslv) {
            if (linhvuc.getTrangthai() == 1) {
                temp.add(linhvuc);
            }
        }
        return temp;
    }

    public void Insert(linhvucDTO s) throws Exception {
        HashMap<String, Object> insertValue = new HashMap<String, Object>();
        insertValue.put("MALINHVUC", s.getMalinhvuc());
        insertValue.put("TENLINHVUC", s.getTenlinhvuc());
        insertValue.put("TRANGTHAI", s.getTrangthai());
        boolean kt = conn.Insert("linhvuc", insertValue);
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    }

    public void Update(linhvucDTO s) throws Exception {
        HashMap<String, Object> updateValue = new HashMap<String, Object>();
        updateValue.put("MALINHVUC", s.getMalinhvuc());
        updateValue.put("TENLINHVUC", s.getTenlinhvuc());
        updateValue.put("trangthai", "1");
        boolean kt = conn.Update("linhvuc", updateValue, "malinhvuc = '" + s.getMalinhvuc() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }

    public void Delete(linhvucDTO s) throws Exception {
        boolean kt = conn.Delete("linhvuc", "malinhvuc = '" + s.getMalinhvuc() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }
}
