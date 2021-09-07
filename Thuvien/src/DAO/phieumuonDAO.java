package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import DTO.phieumuonDTO;

public class phieumuonDAO {
	MyConnectUnit conn = null;
	ResultSet rs = null;
	ArrayList<phieumuonDTO> dspm = null;

	public phieumuonDAO() {
		conn = new MyConnectUnit("localhost", "root", "", "thuvien","phieumuonDAO");
	}

	public ArrayList<phieumuonDTO> docDSPM() throws Exception {
		dspm = new ArrayList<phieumuonDTO>();
		rs = conn.Select("phieumuon");
		while (rs.next()) {
			phieumuonDTO pm = new phieumuonDTO();
			pm.setMapm(rs.getString(1));
			pm.setManv(rs.getString(2));
			pm.setMathe(rs.getString(3));
			pm.setNgaymuon(rs.getString(4));
			pm.setNgayquidinhtra(rs.getString(5));
			pm.setTongtienmuon(rs.getLong(6));
			dspm.add(pm);
		}
                conn.Close();
		return dspm;
	}

	public void Insert(phieumuonDTO pm) throws Exception {
		HashMap<String, Object> insertValue = new HashMap<String, Object>();
		insertValue.put("mapm", pm.getMapm());
		insertValue.put("manv", pm.getManv());
		insertValue.put("mathe", pm.getMathe());
		insertValue.put("ngaymuon", pm.getNgaymuon());
		insertValue.put("ngayquidinhtra", pm.getNgayquidinhtra());
		insertValue.put("tongtienmuon",String.valueOf(pm.getTongtienmuon()));
		
		boolean kt = conn.Insert("phieumuon", insertValue);
		if (kt == true) {
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		} else {
			JOptionPane.showMessageDialog(null, "Thêm thất bại");
		}
		conn.Close();
	}

	public void Update(phieumuonDTO pm) throws Exception {
		HashMap<String,Object> updateValue = new HashMap<String,Object>();
		updateValue.put("mapm",pm.getMapm());
		updateValue.put("manv",pm.getManv());
		updateValue.put("mathe",pm.getMathe());
		updateValue.put("ngaymuon",pm.getNgaymuon());
		updateValue.put("ngayquidinhtra",pm.getNgayquidinhtra());
		//updateValue.put("tongtienmuon",String.valueOf(pm.getTongtienmuon()));
		boolean kt = conn.Update("phieumuon", updateValue, "mapm = '" +pm.getMapm() + "'");
		if(kt == true) {
			JOptionPane.showMessageDialog(null, "Sửa thành công");
		}else {
			JOptionPane.showMessageDialog(null, "Sửa thất bại");
		}
		conn.Close();
	}
	
}
