package BUS;

import java.util.ArrayList;
import DAO.phieumuonDAO;
import DAO.phieuphatDAO;
import DTO.phieumuonDTO;
import DTO.phieuphatDTO;

public class phieuphatBUS {
	public static ArrayList<phieuphatDTO> dspp;
	phieuphatDAO data = new phieuphatDAO();

	public ArrayList<phieuphatDTO> getPPList() throws Exception{
		if(dspp==null) {
			dspp = new ArrayList<phieuphatDTO>();
		}
		
		dspp = data.docDSPP();
		return dspp;
	}
	
	public void Insert(phieuphatDTO pp) throws Exception {
		data.Insert(pp);
		dspp.add(pp);
	}
	
	public void Update(phieuphatDTO pp) throws Exception {
		data.Update(pp);
		int k = 0;
		for(int i  = 0;i<dspp.size();i++) {
			if(pp.getMapp().compareTo(dspp.get(i).getMapp())==0) {
				k = i;
			}
		}
		dspp.set(k,pp);
	}
	
	public String autoCreateID() throws Exception {
		String ID = null;
		dspp = data.docDSPP();
		if(dspp.size()<10) {
			ID = "PP00"+String.valueOf(dspp.size()+1);
		}else if(dspp.size() >= 10 && dspp.size() < 100) {
			ID = "PP0" + String.valueOf(dspp.size() + 1);
		}else if(dspp.size() >= 100) {
			ID = "PP" + String.valueOf(dspp.size() + 1);
		}
		return ID;
	}
}
