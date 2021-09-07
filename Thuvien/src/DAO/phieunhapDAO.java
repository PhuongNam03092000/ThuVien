/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.phieunhapDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class phieunhapDAO {

    MyConnectUnit conn = null;
    ResultSet rs = null;
    private ArrayList<phieunhapDTO> dspn;

    public phieunhapDAO() {
        if (conn == null) {
            conn = new MyConnectUnit("localhost", "root", "", "thuvien","phieunhapDAO");
        }
    }

    public ArrayList<phieunhapDTO> docDSPN() throws Exception {
        dspn = new ArrayList<>();
        rs = conn.Select("phieunhap");
        while (rs.next()) {
            phieunhapDTO pn = new phieunhapDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5));
            dspn.add(pn);
        }
        conn.Close();
        return dspn;
    }
    
    public void Insert(phieunhapDTO pn) throws Exception{
        HashMap<String,Object> insertValue = new HashMap<>();
        insertValue.put("mapn", pn.getMapn());
        insertValue.put("manv", pn.getManv());
        insertValue.put("mancc",pn.getMancc());
        insertValue.put("ngaynhap", pn.getNgaynhap());
        insertValue.put("tongtien",String.valueOf(pn.getTongtien()));
        boolean kt =  conn.Insert("phieunhap", insertValue);
        if(kt == true){
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }else{
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    }
    
 
    
    public void Delete(phieunhapDTO pn) throws Exception{
        boolean kt = conn.Delete("phieunhap","mapn = '"+pn.getMapn()+"'");
        if(kt == true){
            JOptionPane.showConfirmDialog(null, "Xóa thành công");
        }else{
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }
    
    public void Update(phieunhapDTO pn) throws Exception{
        HashMap<String,Object> updateValue = new HashMap<>();
        updateValue.put("mapn", pn.getMapn());
        updateValue.put("manv", pn.getManv());
        updateValue.put("mancc",pn.getMancc());
        updateValue.put("ngaynhap", pn.getNgaynhap());
        updateValue.put("tongtien",String.valueOf(pn.getTongtien()));
        boolean kt = conn.Update("phieunhap", updateValue,"mapn = '"+pn.getMapn()+"'");
    }
}
