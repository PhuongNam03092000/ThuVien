package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.nccDTO;

import java.util.HashMap;
import javax.swing.JOptionPane;

public class nccDAO {

    MyConnectUnit conn = null;
    ResultSet rs = null;
    private ArrayList<nccDTO> dsncc;

    public nccDAO() {
        conn = new MyConnectUnit("localhost", "root", "", "thuvien","nccDAO");
    }

    public void docDSNXB() throws Exception {
        dsncc = new ArrayList<nccDTO>();
        rs = conn.Select("nxb");

        while (rs.next()) {
            nccDTO loai = new nccDTO();
            loai.setMancc(rs.getString(1));
            loai.setTenncc(rs.getString(2));
            loai.setDiachi(rs.getString(3));
            loai.setSdt(rs.getString(4));
            loai.setTrangthai(Integer.parseInt(rs.getString(5)));
            dsncc.add(loai);
        }
        conn.Close();
    }

    public ArrayList<nccDTO> filteredList() throws Exception {
        docDSNXB();
        ArrayList temp = new ArrayList();
        for (nccDTO nxb : dsncc) {
            if (nxb.getTrangthai() == 1) {
                temp.add(nxb);
            }
        }
        return temp;
    }

    public void Insert(nccDTO s) throws Exception {
        HashMap<String, Object> insertValue = new HashMap<String, Object>();
        insertValue.put("MANCC", s.getMancc());
        insertValue.put("TENNCC", s.getTenncc());
        insertValue.put("DIACHINCC", s.getDiachi());
        insertValue.put("SDTNCC", s.getSdt());
        insertValue.put("TRANGTHAI", s.getTrangthai());
        boolean kt = conn.Insert("ncc", insertValue);
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    }

    public void Update(nccDTO s) throws Exception {
        HashMap<String, Object> updateValue = new HashMap<String, Object>();
        updateValue.put("MANCC", s.getMancc());
        updateValue.put("TENNCC", s.getTenncc());
        updateValue.put("DIACHINCC", s.getDiachi());
        updateValue.put("SDTNCC", s.getSdt());
        updateValue.put("trangthai", "1");
        boolean kt = conn.Update("ncc", updateValue, "mancc = '" + s.getMancc()+ "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }

    public void Delete(nccDTO s) throws Exception {
        boolean kt = conn.Delete("ncc", "mancc = '" + s.getMancc()+ "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }
}
