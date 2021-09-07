package BUS;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;


import DTO.nxbDTO;
import DAO.nxbDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class nxbBUS {
	public static ArrayList<nxbDTO> dsnxb;

	public void getNXBList() {
		if (dsnxb == null) {
			dsnxb = new ArrayList<nxbDTO>();
		}
		//Ä‘á»c dá»¯ liá»‡u lĂªn vĂ  truyá»n vĂ o arraylist
		try {
                        nxbDAO dao=new nxbDAO();
			dsnxb = dao.filteredList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        public void Insert(nxbDTO a) throws Exception
        {
            if(sameid(a.getManxb())==false)
            {    
                nxbDAO dao=new nxbDAO();
                dao.Insert(a);
                dsnxb.add(a);
            }
            else 
                JOptionPane.showMessageDialog(null,"Thể loại  đã tồn tại-Không thể thêm");
        }
        
        public void Update(nxbDTO a)
        {
            try{
                nxbDAO dao=new nxbDAO();
                dao.Update(a);
                dsnxb.set(sameid(a), a);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Lỗi sửa");
            }
        }
        
        public void Delete(nxbDTO a)
        {
            try {
                a.setTrangthai(0);
                nxbDAO dao=new nxbDAO();
                dsnxb.remove(sameid(a));
                dao.Delete(a);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Lỗi xóa sách");
            }
            
        }
        
        public boolean sameid(String a)
        {
            for(int i=0;i<dsnxb.size();i++)
            {
                nxbDTO temp=((nxbDTO) dsnxb.get(i));
                if(temp.getManxb().equals(a))
                    return true;                                    //true là trùng 
            }
            return false;
        }
        
        public int sameid(nxbDTO a)
        {
            for(int i=0;i<dsnxb.size();i++)
            {
                nxbDTO temp=((nxbDTO) dsnxb.get(i));
//                JOptionPane.showMessageDialog(null, temp.getMasach());
                if(temp.getManxb().equals(a.getManxb()))
                    return i;
            }
            return -1;
        }
}
