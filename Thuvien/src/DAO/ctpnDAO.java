package DAO;

import DTO.ctpnDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class ctpnDAO {

    MyConnectUnit conn = null;
    ResultSet rs = null;
    protected ArrayList<ctpnDTO> dsctpn;

    public ctpnDAO() {
        if (conn == null) {
            conn = new MyConnectUnit("localhost", "root", "", "thuvien","ctpnDAO");
        }
    }

    public ArrayList<ctpnDTO> docCTPN() throws Exception {
        dsctpn = new ArrayList<>();
        rs = conn.Select("ctphieunhap");
        while (rs.next()) {
            ctpnDTO ctpn = new ctpnDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getLong(4), rs.getLong(5));
            dsctpn.add(ctpn);
        }
        conn.Close();
        if (dsctpn == null) {
            return null;
        } else {
            return dsctpn;
        }
    }

    // khi thêm chi tiết phiếu nhập và khi lưu lại thì phải nhớ cập nhật lại số lượng của sách
    public void Insert(ctpnDTO ctpn) throws Exception {
        HashMap<String, Object> insertValue = new HashMap<>();
        insertValue.put("mapn", ctpn.getMapn());
        insertValue.put("masach", ctpn.getMasach());
        insertValue.put("soluong", String.valueOf(ctpn.getSoluong()));
        insertValue.put("dongia", String.valueOf(ctpn.getDongia()));
        insertValue.put("thanhtien", String.valueOf(ctpn.getThanhtien()));
        boolean kt = conn.Insert("ctphieunhap", insertValue);
    }
    
    public void Insert(ArrayList<ctpnDTO> temp) throws Exception{
        for(ctpnDTO ctpn : temp){
            Insert(ctpn);
        }
    }

    // chỉ dùng hàm xóa khi thực hiện quá trình thêm chi tiết phiếu nhập mới còn lại quá trình khác không được xóa ctpn
    public void Delete(ctpnDTO ctpn) throws Exception {
        boolean kt = conn.Delete("ctphieunhap", "mapn = '" + ctpn.getMapn() + "' and masach = '" + ctpn.getMasach() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }

    public void DeleteReal(ctpnDTO ctpn) throws Exception {
        
        
        boolean kt = conn.DeleteReal("ctphieunhap", "mapn = '" + ctpn.getMapn() + "' and masach = '" + ctpn.getMasach() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }

    public void Update(ctpnDTO ctpn) throws Exception {
        HashMap<String, Object> updateValue = new HashMap<>();
        updateValue.put("mapn", ctpn.getMapn());
        updateValue.put("masach", ctpn.getMasach());
        updateValue.put("soluong", String.valueOf(ctpn.getSoluong()));
        updateValue.put("dongia", String.valueOf(ctpn.getDongia()));
        updateValue.put("thanhtien", String.valueOf(ctpn.getThanhtien()));
        boolean kt = conn.Update("ctphieunhap", updateValue, "mapn = '" + ctpn.getMapn() + "' and masach = '" + ctpn.getMasach() + "'");
        if (kt == true) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }

    public ArrayList<ctpnDTO> filteredList(String ID) throws Exception {
        dsctpn = docCTPN();
        ArrayList<ctpnDTO> edited = new ArrayList<>();
        for (ctpnDTO ctp : dsctpn) {
            if (ctp.getMapn().equals(ID)) {
                edited.add(ctp);
            }
        }
        return edited;
    }
}
