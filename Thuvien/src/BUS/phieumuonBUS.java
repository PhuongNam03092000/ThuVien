package BUS;

import java.util.ArrayList;
import DAO.phieumuonDAO;
import DTO.phieumuonDTO;

public class phieumuonBUS {
	public static ArrayList<phieumuonDTO> dspm;
	phieumuonDAO data = new phieumuonDAO();

	public ArrayList<phieumuonDTO> getPMList() throws Exception{
		if(dspm==null) {
			dspm = new ArrayList<phieumuonDTO>();
		}
		
		dspm = data.docDSPM();
		return dspm;
	}
	
	public void Insert(phieumuonDTO pm) throws Exception {
		data.Insert(pm);
		dspm.add(pm);
	}
	
	public void Update(phieumuonDTO pm) throws Exception {
		data.Update(pm);
		int k = 0;
		for(int i  = 0;i<dspm.size();i++) {
			if(pm.getManv().compareTo(dspm.get(i).getMapm())==0) {
				k = i;
			}
		}
		dspm.set(k,pm);
	}
	
	public String autoCreateID() throws Exception {
		String ID = null;
		dspm = data.docDSPM();
		if(dspm.size()<10) {
			ID = "PM00"+String.valueOf(dspm.size()+1);
		}else if(dspm.size() >= 10 && dspm.size() < 100) {
			ID = "NV0" + String.valueOf(dspm.size() + 1);
		}else if(dspm.size() >= 100) {
			ID = "NV" + String.valueOf(dspm.size() + 1);
		}
		return ID;
	}
}
