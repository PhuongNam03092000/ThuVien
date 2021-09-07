
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import DTO.docgiaDTO;


public class docgiaDAO {

    MyConnectUnit conn;
    ResultSet rs;
    private ArrayList<docgiaDTO> dsdg;

    public docgiaDAO() {
        conn = new MyConnectUnit("localhost", "root", "", "thuvien","docgiaDAO");
    }

    public ArrayList<docgiaDTO> docDG() throws Exception {
        dsdg = new ArrayList<>();
        rs = conn.Select("docgia");
        while (rs.next()) {
            docgiaDTO docgia = new docgiaDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), Integer.parseInt(rs.getString(8)));
            dsdg.add(docgia);
        }
        conn.Close();
        return dsdg;

    }

   public void Insert(docgiaDTO dg) throws Exception {
        HashMap<String, Object> insertValue = new HashMap<>();
        insertValue.put("madg", dg.getMadg());
        insertValue.put("hodg", dg.getHodg());
        insertValue.put("tendg", dg.getTendg());
        insertValue.put("ngaysinhdg", dg.getNgaysinh());
        insertValue.put("diachidg", dg.getDiachi());
        insertValue.put("nghenghiep", dg.getNghenghiep());
        insertValue.put("trinhdo", dg.getTrinhdo());
        insertValue.put("trangthai", 1);
        boolean kt = conn.Insert("docgia", insertValue);
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Them thanh cong");
        } else {
            JOptionPane.showMessageDialog(null, "Them that bai");
        }
        conn.Close();
    }

    public void Delete(docgiaDTO dg) throws Exception {
        boolean kt = conn.Delete("docgia", "madg = '" + dg.getMadg() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Xoa thanh cong");
        } else {
            JOptionPane.showMessageDialog(null, "Xoa that bai");
        }
        conn.Close();
    }

    public void Update(docgiaDTO dg) throws Exception {
        HashMap<String, Object> updateValue = new HashMap<>();
        updateValue.put("madg", dg.getMadg());
        updateValue.put("hodg", dg.getHodg());
        updateValue.put("tendg", dg.getTendg());
        updateValue.put("ngaysinhdg", dg.getNgaysinh());
        updateValue.put("diachidg", dg.getDiachi());
        updateValue.put("nghenghiep", dg.getNghenghiep());
        updateValue.put("trinhdo", dg.getTrinhdo());
        updateValue.put("trangthai", 1);
        boolean kt = conn.Update("docgia", updateValue, "madg = '" + dg.getMadg() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Sua thanh cong");
        } else {
            JOptionPane.showMessageDialog(null, "Sua that bai");
        }
        conn.Close();
    }

    public ArrayList<docgiaDTO> filteredList() {
        try {
            dsdg = docDG();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<docgiaDTO> edited = new ArrayList<docgiaDTO>();
        for (docgiaDTO dg : dsdg) {
            if (dg.getTrangthai() == 1) {
                edited.add(dg);
            }

        }
        return edited;
    }
}
