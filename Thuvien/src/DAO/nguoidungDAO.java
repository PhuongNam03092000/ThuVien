package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import DTO.nguoidungDTO;
import TOOL.MD5Hash;

public class nguoidungDAO {
	MyConnectUnit conn = null;
	ResultSet rs = null;
	ArrayList<nguoidungDTO> dsnd = null;

	public nguoidungDAO() {
		if (conn == null) {
			conn = new MyConnectUnit("localhost", "root", "", "thuvien","nguoidungDAO");
		}
	}

	public ArrayList<nguoidungDTO> docDSND() throws Exception {
		dsnd = new ArrayList<nguoidungDTO>();
		rs = conn.Select("nguoidung");
		while (rs.next()) {
			nguoidungDTO nguoidung = new nguoidungDTO();
			nguoidung.setManv(rs.getString(1));
			nguoidung.setMkhau(rs.getString(2));
			nguoidung.setQuyen(rs.getString(3));
                        nguoidung.setTrangthai(rs.getInt(4));
			dsnd.add(nguoidung);
		}
		conn.Close();
		return dsnd;
	}

	public void Insert(nguoidungDTO nd) throws Exception {
		HashMap<String, Object> insertValue = new HashMap<String, Object>();
		insertValue.put("manv", nd.getManv());
		insertValue.put("matkhau", nd.getMkhau());
		insertValue.put("maquyen", nd.getQuyen());
		insertValue.put("trangthai", 1);
		boolean kt = conn.Insert("nguoidung", insertValue);
		if (kt == true) {
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		} else {
			JOptionPane.showMessageDialog(null, "Thêm thất bại");
		}
		conn.Close();
	}

	public void Delete(nguoidungDTO nd) throws Exception {
		boolean kt = conn.Delete("nguoidung", "manv = '" + nd.getManv() + "'");
		if (kt == true) {
			JOptionPane.showMessageDialog(null, "Xóa thành công");
		} else {
			JOptionPane.showMessageDialog(null, "Xóa thất bại");
		}
	}

	public void Update(nguoidungDTO nd) throws Exception {
		HashMap<String, Object> updateValue = new HashMap<String, Object>();
		updateValue.put("manv",nd.getManv());
		updateValue.put("matkhau", nd.getMkhau());
		updateValue.put("maquyen", nd.getQuyen());
		updateValue.put("trangthai", nd.getTrangthai());
		boolean kt = conn.Update("nguoidung", updateValue,"manv = '" + nd.getManv() + "'");
		if (kt == true) {
			JOptionPane.showMessageDialog(null, "Thay đổi thành công");
		} else {
			JOptionPane.showMessageDialog(null, "Thay đổi thất bại thất bại");
		}
		
		conn.Close();
	}
	
	public boolean updateHash(String manv, String pass) throws Exception {
		String hashedPassword = null;
		rs = conn.Select("nguoidung");
		while(rs.next()) {
			if(rs.getString(1).equalsIgnoreCase(manv)) {
				hashedPassword = rs.getString(2);
			}
		}
		hashedPassword = MD5Hash.getMd5(hashedPassword);
		
		HashMap<String, Object> hashValue = new HashMap<>();
		hashValue.put("matkhau", hashedPassword);
		boolean hashUpdate = conn.Update("nguoidung", hashValue, "manv= '" + manv + "'");
		
		if(hashUpdate == true) {
			System.out.println("Đã Hash");
			return true;
		} else {
			System.out.println("Hash thất bại");
			return false;
		}
	}
	
	public String hashPassword(String manv, String pass) throws Exception {
		String hashedPassword = null;
		rs = conn.Select("nguoidung");
		while(rs.next()) {
			if(rs.getString(1).equalsIgnoreCase(manv)) {
				hashedPassword = rs.getString(2);
			}
		}
		hashedPassword = MD5Hash.getMd5(hashedPassword);
		
		HashMap<String, Object> hashValue = new HashMap<>();
		hashValue.put("matkhau", hashedPassword);
		boolean hashUpdate = conn.UpdateNoComma("nguoidung", hashValue, "manv= '" + manv + "'");
		
		if(hashUpdate == true) {
			System.out.println("Đã Hash");
		} else {
			System.out.println("Hash thất bại");
		}
		
		return hashedPassword;
	}
	
}
