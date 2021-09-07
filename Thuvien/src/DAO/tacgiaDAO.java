package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.linhvucDTO;
import DTO.tacgiaDTO;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class tacgiaDAO {

    MyConnectUnit conn = null;
    ResultSet rs = null;
    private ArrayList<tacgiaDTO> dstg;

    public tacgiaDAO() {
        conn = new MyConnectUnit("localhost", "root", "", "thuvien","tacgiaDAO");
    }

    public void docDSTG() throws Exception {
        dstg = new ArrayList<tacgiaDTO>();
        rs = conn.Select("tacgia");

        while (rs.next()) {
            tacgiaDTO tacgia = new tacgiaDTO();
            tacgia.setMatg(rs.getString(1));
            tacgia.setHotg(rs.getString(2));
            tacgia.setTentg(rs.getString(3));
            tacgia.setEmail(rs.getString(4));
            tacgia.setTrangthai(Integer.parseInt(rs.getString(5)));
            dstg.add(tacgia);
        }
        conn.Close();
    }

    public ArrayList<tacgiaDTO> filteredList() throws Exception {
        docDSTG();
        ArrayList<tacgiaDTO> temp = new ArrayList();
        for (tacgiaDTO sach : dstg) {
            if (sach.getTrangthai() == 1) {
                temp.add(sach);
            }
        }
        return temp;
    }

    public void Insert(tacgiaDTO s) throws Exception {
        HashMap<String, Object> insertValue = new HashMap<String, Object>();
        insertValue.put("MATG", s.getMatg());
        insertValue.put("HOTG", s.getHotg());
        insertValue.put("TENTG", s.getTentg());
        insertValue.put("EMAILTG", s.getEmail());
        insertValue.put("TRANGTHAI", s.getTrangthai());
        boolean kt = conn.Insert("tacgia", insertValue);
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    }

    public void Update(tacgiaDTO s) throws Exception {
        HashMap<String, Object> updateValue = new HashMap<String, Object>();
        updateValue.put("MATG", s.getMatg());
        updateValue.put("HOTG", s.getHotg());
        updateValue.put("TENTG", s.getTentg());
        updateValue.put("EMAILTG", s.getEmail());
        updateValue.put("trangthai", "1");
        boolean kt = conn.Update("tacgia", updateValue, "matg = '" + s.getMatg() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }

    public void Delete(tacgiaDTO s) throws Exception {
        boolean kt = conn.Delete("tacgia", "matg = '" + s.getMatg() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }
}
