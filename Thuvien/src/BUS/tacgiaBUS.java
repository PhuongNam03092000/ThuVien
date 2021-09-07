package BUS;

import DAO.sachDAO;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DAO.tacgiaDAO;
import DTO.tacgiaDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class tacgiaBUS {
	public static ArrayList<tacgiaDTO> dstg;
        
	public void getTacgiaList() {
		if (dstg == null) {
			dstg = new ArrayList<tacgiaDTO>();
		}
		//Ä‘á»c dá»¯ liá»‡u lĂªn vĂ  truyá»n vĂ o arraylist
		try {
                        tacgiaDAO dao=new tacgiaDAO();
			dstg = dao.filteredList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        public void Insert(tacgiaDTO a) throws Exception
        {
            if(sameid(a.getMatg())==false)
            {    
                tacgiaDAO dao=new tacgiaDAO();
                dao.Insert(a);
                dstg.add(a);
            }
            else 
                JOptionPane.showMessageDialog(null,"Tác giả đã tồn tại-Không thể thêm");
        }
        
        public void Update(tacgiaDTO a)
        {
            try{
                tacgiaDAO dao=new tacgiaDAO();
                dao.Update(a);
                dstg.set(sameid(a), a);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Lỗi sửa");
            }
        }
        
        public void Delete(tacgiaDTO a)
        {
            try {
                a.setTrangthai(0);
                tacgiaDAO dao=new tacgiaDAO();
                dstg.remove(sameid(a));
                dao.Delete(a);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Lỗi xóa sách");
            }
            
        }
        
        public boolean sameid(String a)
        {
            try {
                tacgiaDAO data=new tacgiaDAO();
                dstg = data.filteredList();
            } catch (Exception ex) {
                Logger.getLogger(tacgiaBUS.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(int i=0;i<dstg.size();i++)
            {
                tacgiaDTO temp=((tacgiaDTO) dstg.get(i));
                if(temp.getMatg().equals(a))
                    return true;                                    //true là trùng 
            }
            return false;
        }
        
        public int sameid(tacgiaDTO a)
        {
            for(int i=0;i<dstg.size();i++)
            {
                tacgiaDTO temp=((tacgiaDTO) dstg.get(i));
//                JOptionPane.showMessageDialog(null, temp.getMasach());
                if(temp.getMatg().equals(a.getMatg()))
                    return i;
            }
            return -1;
        }
        
        public int getHeader()
        {
            return 4;
        }
}
