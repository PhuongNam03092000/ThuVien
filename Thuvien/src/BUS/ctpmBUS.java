package BUS;

import java.util.ArrayList;

import DAO.ctpmDAO;
import DAO.nhanvienDAO;
import DTO.ctpmDTO;
import DTO.nhanvienDTO;

public class ctpmBUS {
	public static ArrayList<ctpmDTO> dsctpm;
	//public static String userID;
	//nhanvienDAO data = new nhanvienDAO();
	ctpmDAO data = new ctpmDAO();
	public ArrayList<ctpmDTO> getCTPMList(String ID) {
		if (dsctpm == null) {
			dsctpm = new ArrayList<ctpmDTO>();
		}

		// đọc dữ liệu lên và truyền vào arraylist
		try {
			dsctpm = data.filteredList(ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsctpm;
	}
        public ArrayList<ctpmDTO> getCTPMList() throws Exception{
            if(dsctpm==null){
                dsctpm = new ArrayList<>();
            }
            dsctpm = data.docCTPM();
            return dsctpm;
        }
	
	public void removeList() {
		for(int i = 0;i< dsctpm.size();i++) {
			dsctpm.remove(i);
		}
	}
	
	public void insert(ctpmDTO ctpm) {
		try {
			data.Insert(ctpm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dsctpm.add(ctpm);
	}
	
	public void delete(ctpmDTO ctpm) throws Exception {
		data.Delete(ctpm);
		for (int i = 0; i < dsctpm.size(); i++) {
			if ( dsctpm.get(i).getMapm().equals(ctpm.getMapm()) && dsctpm.get(i).getMasach().equals(ctpm.getMasach()) ) {
				dsctpm.remove(i);
			}
		}
	}
	
	public void update(ctpmDTO ctpm) throws Exception {
		data.Update(ctpm);
		// phần thêm
		int k = 0;
		for (int i = 0; i < dsctpm.size(); i++) {
			if ((dsctpm.get(i)).getMapm().equals(ctpm.getMapm())) {
				k = i;
			}
		}
		dsctpm.set(k, ctpm);
	}
	
}
