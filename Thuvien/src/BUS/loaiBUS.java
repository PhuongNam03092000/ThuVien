package BUS;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;


import DTO.loaiDTO;
import DAO.loaiDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class loaiBUS {
	public static ArrayList<loaiDTO> dstl;

	public void getLoaiList() {
		if (dstl == null) {
			dstl = new ArrayList<loaiDTO>();
		}
		//Ä‘á»c dá»¯ liá»‡u lĂªn vĂ  truyá»n vĂ o arraylist
		try {
                        loaiDAO dao=new loaiDAO();
			dstl = dao.filteredList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        public void Insert(loaiDTO a) throws Exception
        {
            if(sameid(a.getMaloai())==false)
            {    
                loaiDAO dao=new loaiDAO();
                dao.Insert(a);
                dstl.add(a);
            }
            else 
                JOptionPane.showMessageDialog(null,"Thể loại  đã tồn tại-Không thể thêm");
        }
        
        public void Update(loaiDTO a)
        {
            try{
                loaiDAO dao=new loaiDAO();
                dao.Update(a);
                dstl.set(sameid(a), a);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Lỗi sửa");
            }
        }
        
        public void Delete(loaiDTO a)
        {
            try {
                a.setTrangthai(0);
                loaiDAO dao=new loaiDAO();
                dstl.remove(sameid(a));
                dao.Delete(a);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Lỗi xóa sách");
            }
            
        }
        
        public boolean sameid(String a)
        {
            try {
                loaiDAO data=new loaiDAO();
                dstl = data.filteredList();
            } catch (Exception ex) {
                Logger.getLogger(loaiBUS.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(int i=0;i<dstl.size();i++)
            {
                loaiDTO temp=((loaiDTO) dstl.get(i));
                if(temp.getMaloai().equals(a))
                    return true;                                    //true là trùng 
            }
            return false;
        }
        
        public int sameid(loaiDTO a)
        {
            for(int i=0;i<dstl.size();i++)
            {
                loaiDTO temp=((loaiDTO) dstl.get(i));
//                JOptionPane.showMessageDialog(null, temp.getMasach());
                if(temp.getMaloai().equals(a.getMaloai()))
                    return i;
            }
            return -1;
        }
        

}
