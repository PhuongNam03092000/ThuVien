package BUS;

import java.util.ArrayList;
import java.util.HashMap;

import DAO.ctppDAO;
import DTO.ctppDTO;

public class ctppBUS {
	public static ArrayList<ctppDTO> dsctpp;
	//public static String userID;
	//nhanvienDAO data = new nhanvienDAO();
	ctppDAO data = new ctppDAO();
	public ArrayList<ctppDTO> getCTPPList(String ID) {
		if (dsctpp == null) {
			dsctpp = new ArrayList<ctppDTO>();
		}

		// đọc dữ liệu lên và truyền vào arraylist
		try {
			dsctpp = data.filteredList(ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsctpp;
	}
	
	public void removeList() {
		for(int i = 0;i< dsctpp.size();i++) {
			dsctpp.remove(i);
		}
	}
	
	public void insert(ctppDTO ctpp) {
		try {
			data.Insert(ctpp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dsctpp.add(ctpp);
	}
	
	public void delete(ctppDTO ctpp) throws Exception {
		data.Delete(ctpp);
		for (int i = 0; i < dsctpp.size(); i++) {
			if ( dsctpp.get(i).getMapp().equals(ctpp.getMapp()) && dsctpp.get(i).getMasach().equals(ctpp.getMasach()) ) {
				dsctpp.remove(i);
			}
		}
	}
	
	public void update(ctppDTO ctpp) throws Exception {
		data.Update(ctpp);
		// phần thêm
		int k = 0;
		for (int i = 0; i < dsctpp.size(); i++) {
			if ((dsctpp.get(i)).getMapp().equals(ctpp.getMapp())) {
				k = i;
			}
		}
		dsctpp.set(k, ctpp);
	}

	public HashMap<String,Integer> getQuyDinhMap() throws Exception {
		return data.getQuyDinh();
	}
	
}
