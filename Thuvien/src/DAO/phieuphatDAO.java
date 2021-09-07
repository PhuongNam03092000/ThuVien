/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ctpmDTO;
import DTO.ctppDTO;
import DTO.phieumuonDTO;
import DTO.phieuphatDTO;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class phieuphatDAO {

	MyConnectUnit conn = null;
	ResultSet rs = null;
	ctppDAO dataCTPP;
	private ArrayList<phieuphatDTO> dspp;

	public phieuphatDAO() {
		if (conn == null) {
			conn = new MyConnectUnit("localhost", "root", "", "thuvien", "phieuphatDAO");
		}
		dataCTPP = new ctppDAO();
	}

	public ArrayList<phieuphatDTO> docDSPP() throws Exception {
		dspp = new ArrayList<>();
		rs = conn.Select("phieuphat");
		while (rs.next()) {
			phieuphatDTO phieuphat = new phieuphatDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getLong(4));
			dspp.add(phieuphat);
		}
		
		if (dspp.isEmpty()) {
			for (phieumuonDTO pm : docDSPM()) {
				LocalDate d1 = LocalDate.parse(pm.getNgayquidinhtra());
				LocalDate d2 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				long diff = ChronoUnit.DAYS.between(d1, d2);
				if (diff > 0) {
					phieuphatDTO pp = null;
					if (dspp.isEmpty()) {
						pp = new phieuphatDTO("PP001", pm.getMapm(), "1970-01-01");
					} else {
						pp = new phieuphatDTO(autoCreateID(), pm.getMapm(), "1970-01-01");
					}
					Insert(pp);
					int tongTien = 0;
					for (ctpmDTO ctpm : docCTPM(pm.getMapm())) {
						if (ctpm.getTinhtrang().equals("Chưa trả")) {
							ctppDTO ctpp = new ctppDTO(pp.getMapp(), ctpm.getMasach(), ctpm.getSoluong(), "QD002",
									getQuyDinh().get("QD002") * ctpm.getSoluong());
							tongTien += getQuyDinh().get("QD002") * ctpm.getSoluong();
							dataCTPP.Insert(ctpp);
						}
					}
					pp.setTongtien(tongTien);
					dspp.add(pp);
				}
			}
		} else {
			for (phieumuonDTO pm : docDSPM()) {
				LocalDate d1 = LocalDate.parse(pm.getNgayquidinhtra());
				LocalDate d2 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				long diff = ChronoUnit.DAYS.between(d1, d2);
				phieuphatDTO pp = null;
				int flag =0;
				for (phieuphatDTO pp2 : dspp) {
					if (pp2.getMapm().equals(pm.getMapm())) {
						flag=1;
						continue;
					} else {
						pp = new phieuphatDTO(autoCreateID(), pm.getMapm(), "1970-01-01");
					}
				}
				
				if(flag == 1) {
					continue;
				}
				
				if (diff > 0) {
					
					if (pp != null) {
						Insert(pp);
						int tongTien = 0;
						ctppDTO ctpp = null;
						for (ctpmDTO ctpm : docCTPM(pm.getMapm())) {
							if (ctpm.getTinhtrang().equals("Chưa trả")) {
								ctpp = new ctppDTO(pp.getMapp(), ctpm.getMasach(), ctpm.getSoluong(), "QD002",
										getQuyDinh().get("QD002") * ctpm.getSoluong());
								tongTien += getQuyDinh().get("QD002") * ctpm.getSoluong();
								dataCTPP.Insert(ctpp);
							}
						}
						if (ctpp != null) {
							pp.setTongtien(tongTien);
							dspp.add(pp);
						} else {
							Delete(pp);
						}
					}
				}
			}
		}
		conn.Close();
		return dspp;
	}

	public String autoCreateID() throws Exception {
		String ID = null;
		ArrayList<phieuphatDTO> ds = new ArrayList<>();
		rs = conn.Select("phieuphat");
		while (rs.next()) {
			phieuphatDTO phieuphat = new phieuphatDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getLong(4));
			ds.add(phieuphat);
		}
		if (ds.size() < 10) {
			ID = "PP00" + String.valueOf(ds.size() + 1);
		} else if (ds.size() >= 10 && ds.size() < 100) {
			ID = "PP0" + String.valueOf(ds.size() + 1);
		} else if (ds.size() >= 100) {
			ID = "PP" + String.valueOf(ds.size() + 1);
		}
		return ID;
	}

	public void Insert(phieuphatDTO pp) throws Exception {
		HashMap<String, Object> insertValue = new HashMap<>();
		insertValue.put("mapp", pp.getMapp());
		insertValue.put("mapm", pp.getMapm());
		insertValue.put("ngaylap", pp.getNgaylap());
		insertValue.put("tongtien", String.valueOf(pp.getTongtien()));
		boolean kt = conn.Insert("phieuphat", insertValue);
		if (kt == true) {
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		} else {
			JOptionPane.showMessageDialog(null, "Thêm thất bại");
		}
	}

	public void Delete(phieuphatDTO pp) throws Exception {
		boolean kt = conn.Delete("phieuphat", "mapp = '" + pp.getMapp() + "'");
		if (kt == true) {
			JOptionPane.showConfirmDialog(null, "Xóa thành công");
		} else {
			JOptionPane.showMessageDialog(null, "Xóa thất bại");
		}
	}

	public void Update(phieuphatDTO pp) throws Exception {
		HashMap<String, Object> updateValue = new HashMap<>();
		updateValue.put("mapp", pp.getMapp());
		updateValue.put("mapm", pp.getMapm());
		updateValue.put("ngaylap", pp.getNgaylap());
		// updateValue.put("tongtien", String.valueOf(pp.getTongtien()));

		boolean kt = conn.Update("phieuphat", updateValue, "mapp = '" + pp.getMapp() + "'");
		if (kt == true) {
			JOptionPane.showMessageDialog(null, "Sửa thành công");
		} else {
			JOptionPane.showMessageDialog(null, "Sủa thất bại");
		}
	}

	public ArrayList<phieumuonDTO> docDSPM() throws Exception {
		ArrayList<phieumuonDTO> dspm = new ArrayList<>();
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

	public ArrayList<ctpmDTO> docCTPM(String ID) throws Exception {
		ArrayList<ctpmDTO> dsctm = new ArrayList<ctpmDTO>();
		rs = conn.Select("ctphieumuon");
		while (rs.next()) {
			if (rs.getString(1).equalsIgnoreCase(ID)) {
				ctpmDTO ctpm = new ctpmDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getInt(5));
				dsctm.add(ctpm);
			}

		}
		conn.Close();
		return dsctm;
	}

	public HashMap<String, Integer> getQuyDinh() throws Exception {
		HashMap<String, Integer> newMap = new HashMap<String, Integer>();
		rs = conn.Select("quydinh");
		while (rs.next()) {
			newMap.put(rs.getString(1), rs.getInt(3));
		}
		conn.Close();
		return newMap;
	}

}
