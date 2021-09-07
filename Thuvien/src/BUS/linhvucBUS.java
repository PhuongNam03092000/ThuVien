package BUS;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DTO.linhvucDTO;
import DAO.linhvucDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class linhvucBUS {

    public static ArrayList<linhvucDTO> dslv;
    

    public void getLinhvucList() {
        if (dslv == null) {
            dslv = new ArrayList<linhvucDTO>();
        }
        //Ä‘á»c dá»¯ liá»‡u lĂªn vĂ  truyá»n vĂ o arraylist
        try {
            linhvucDAO dao = new linhvucDAO();
            dslv = dao.filteredList();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Insert(linhvucDTO a) throws Exception {
        if (sameid(a.getMalinhvuc()) == false) {
            linhvucDAO dao = new linhvucDAO();
            dao.Insert(a);
            dslv.add(a);
        } else {
            JOptionPane.showMessageDialog(null, "Tác giả đã tồn tại-Không thể thêm");
        }
    }

    public void Update(linhvucDTO a) {
        try {
            linhvucDAO dao = new linhvucDAO();
            dao.Update(a);
            dslv.set(sameid(a), a);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi sửa");
        }
    }

    public void Delete(linhvucDTO a) {
        try {
            a.setTrangthai(0);
            linhvucDAO dao = new linhvucDAO();
            dslv.remove(sameid(a));
            dao.Delete(a);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa sách");
        }

    }

    public boolean sameid(String a) {
        for (int i = 0; i < dslv.size(); i++) {
            linhvucDTO temp = ((linhvucDTO) dslv.get(i));
            if (temp.getMalinhvuc().equals(a)) {
                return true;                                    //true là trùng 
            }
        }
        return false;
    }

    public int sameid(linhvucDTO a) {
        for (int i = 0; i < dslv.size(); i++) {
            linhvucDTO temp = ((linhvucDTO) dslv.get(i));
//                JOptionPane.showMessageDialog(null, temp.getMasach());
            if (temp.getMalinhvuc().equals(a.getMalinhvuc())) {
                return i;
            }
        }
        return -1;
    }
    
    public int getHeader(){
        return 2;
    }
}
