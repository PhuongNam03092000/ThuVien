package BUS;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DAO.nccDAO;
import DTO.nccDTO;
import javax.swing.JOptionPane;

public class nccBUS {
	public static ArrayList<nccDTO> dsncc;

        public nccBUS()
        {
            getNCCList();
        }
	public void getNCCList() {
		if (dsncc == null) {
			dsncc = new ArrayList<nccDTO>();
		}
		//Ä‘á»c dá»¯ liá»‡u lĂªn vĂ  truyá»n vĂ o arraylist
		try {
                        nccDAO dao=new nccDAO();
			dsncc = dao.filteredList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        public void Insert(nccDTO a) throws Exception
        {
            if(sameid(a.getMancc())==false)
            {    
                nccDAO dao=new nccDAO();
                dao.Insert(a);
                dsncc.add(a);
            }
            else 
                JOptionPane.showMessageDialog(null,"Tác giả đã tồn tại-Không thể thêm");
        }
        
        public void Update(nccDTO a)
        {
            try{
                nccDAO dao=new nccDAO();
                dao.Update(a);
                dsncc.set(sameid(a), a);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Lỗi sửa");
            }
        }
        
        public void Delete(nccDTO a)
        {
            try {
                a.setTrangthai(0);
                nccDAO dao=new nccDAO();
                dsncc.remove(sameid(a));
                dao.Delete(a);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Lỗi xóa sách");
            }
            
        }
        
        public boolean sameid(String a)
        {
            for(int i=0;i<dsncc.size();i++)
            {
                nccDTO temp=((nccDTO) dsncc.get(i));
                if(temp.getMancc().equals(a))
                    return true;                                    //true là trùng 
            }
            return false;
        }
        
        public int sameid(nccDTO a)
        {
            for(int i=0;i<dsncc.size();i++)
            {
                nccDTO temp=((nccDTO) dsncc.get(i));
                if(temp.getMancc().equals(a.getMancc()))
                    return i;
            }
            return -1;
        }
        
        public int getHeader()
        {
            return 4;
        }
}
