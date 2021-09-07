package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import DTO.ctppDTO;

public class ctppDAO {
	MyConnectUnit conn = null;
	ResultSet rs = null;
	protected ArrayList<ctppDTO> dsctpp;

	public ctppDAO() {
		if (conn == null) {
			conn = new MyConnectUnit("localhost", "root", "", "thuvien","ctpPDAO");
		}
	}

	public ArrayList<ctppDTO> docCTPP() throws Exception {
		dsctpp = new ArrayList<>();
		rs = conn.Select("ctphieuphat");
		while (rs.next()) {
			ctppDTO ctpp = new ctppDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
			dsctpp.add(ctpp);
		}
                conn.Close();
		if (dsctpp == null) {
			return null;
		} else {
			return dsctpp;
		}
	}

	public void Insert(ctppDTO ctpp) throws Exception {
		HashMap<String, Object> insertValue = new HashMap<>();
		insertValue.put("mapp", ctpp.getMapp());
		insertValue.put("masach", ctpp.getMasach());
		insertValue.put("soluong", String.valueOf(ctpp.getSoluong()));
		insertValue.put("maqd", ctpp.getMaqd());
		insertValue.put("thanhtien", String.valueOf(ctpp.getThanhtien()));
		// boolean kt = conn.Insert("ctphieuphat", insertValue);

		HashMap<String, Object> phieuphatValue = new HashMap<>();
		phieuphatValue.put("tongtien", "tongtien + " + ctpp.getThanhtien());
		boolean check = conn.UpdateNoComma("phieuphat", phieuphatValue, "mapp ='" + ctpp.getMapp() + "'");

		if (check == true) {
			JOptionPane.showMessageDialog(null, "Thêm tiền phiếu phạt thành công");
			boolean kt = conn.Insert("ctphieuphat", insertValue);
			if (kt == true) {
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Thêm thất bại");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Thêm tiền phiếu phạt thất bại");
		}

	}

	public void Delete(ctppDTO ctpp) throws Exception {

		HashMap<String, Object> phieuphatValue = new HashMap<>();
		phieuphatValue.put("tongtien", "tongtien - " + ctpp.getThanhtien());

		boolean check = conn.UpdateNoComma("phieuphat", phieuphatValue,
				"mapp ='" + ctpp.getMapp() + "'" + " AND " + "(tongtien - " + ctpp.getThanhtien() + ") >= 0");

		if (check == true) {
			JOptionPane.showMessageDialog(null, "Trừ tiền phiếu phạt thành công");
			boolean kt = conn.DeleteReal("ctphieuphat",
					"mapp = '" + ctpp.getMapp() + "' and masach = '" + ctpp.getMasach() + "'");
			if (kt == true) {
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Xoá thất bại");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Trừ tiền phiếu phạt thất bại");
		}

	}

	public void Update(ctppDTO ctpp) throws Exception {

		for (ctppDTO ctp : filteredList(ctpp.getMapp())) {
			if (ctp.getMasach().equalsIgnoreCase(ctpp.getMasach())) {
				int priceChange = Math.abs(ctp.getThanhtien() - ctpp.getThanhtien());

				if (ctp.getThanhtien() < ctpp.getThanhtien()) {

					HashMap<String, Object> updateValue = new HashMap<>();
					updateValue.put("mapp", ctpp.getMapp());
					updateValue.put("masach", ctpp.getMasach());
					updateValue.put("soluong", String.valueOf(ctpp.getSoluong()));
					updateValue.put("maqd", ctpp.getMaqd());
					updateValue.put("thanhtien", String.valueOf(ctpp.getThanhtien()));

					HashMap<String, Object> phieuphatValue = new HashMap<>();
					phieuphatValue.put("tongtien", "tongtien + " + priceChange);
					boolean check = conn.UpdateNoComma("phieuphat", phieuphatValue, "mapp ='" + ctpp.getMapp() + "'");

					if (check == true) {
						JOptionPane.showMessageDialog(null, "Thêm tiền (Sửa) thành công");
						boolean kt = conn.Update("ctphieuphat", updateValue,
								"mapp = '" + ctpp.getMapp() + "' and masach = '" + ctpp.getMasach() + "'");
						if (kt == true) {
							JOptionPane.showMessageDialog(null, "Sửa thành công");
						} else {
							JOptionPane.showMessageDialog(null, "Sửa thất bại");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Thêm tiền (Sửa) thất bại");
					}
					break;
				}
				if (ctp.getThanhtien() > ctpp.getThanhtien()) {
					HashMap<String, Object> updateValue = new HashMap<>();
					updateValue.put("mapp", ctpp.getMapp());
					updateValue.put("masach", ctpp.getMasach());
					updateValue.put("soluong", String.valueOf(ctpp.getSoluong()));
					updateValue.put("maqd", ctpp.getMaqd());
					updateValue.put("thanhtien", String.valueOf(ctpp.getThanhtien()));

					HashMap<String, Object> phieuphatValue = new HashMap<>();
					phieuphatValue.put("tongtien", "tongtien - " + priceChange);
					boolean check = conn.UpdateNoComma("phieuphat", phieuphatValue,
							"mapp ='" + ctpp.getMapp() + "'" + " AND " + "(tongtien - " + priceChange + ") >= 0");

					if (check == true) {
						JOptionPane.showMessageDialog(null, "Trừ tiền (Sửa) thành công");
						boolean kt = conn.Update("ctphieuphat", updateValue,
								"mapp = '" + ctpp.getMapp() + "' and masach = '" + ctpp.getMasach() + "'");
						if (kt == true) {
							JOptionPane.showMessageDialog(null, "Sửa thành công");
						} else {
							JOptionPane.showMessageDialog(null, "Sửa thất bại");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Trừ tiền (Sửa) thất bại");
					}
					break;
				}
				if (priceChange == 0) {
					HashMap<String, Object> updateValue = new HashMap<>();
					updateValue.put("mapp", ctpp.getMapp());
					updateValue.put("masach", ctpp.getMasach());
					updateValue.put("soluong", String.valueOf(ctpp.getSoluong()));
					updateValue.put("maqd", ctpp.getMaqd());
					updateValue.put("thanhtien", String.valueOf(ctpp.getThanhtien()));
					boolean kt = conn.Update("ctphieuphat", updateValue,
							"mapp = '" + ctpp.getMapp() + "' and masach = '" + ctpp.getMasach() + "'");
					if (kt == true) {
						JOptionPane.showMessageDialog(null, "Sửa thành công");
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thất bại");
					}
					break;
				}
			}
		}

	}

	public ArrayList<ctppDTO> filteredList(String ID) throws Exception {
		dsctpp = docCTPP();
		ArrayList<ctppDTO> edited = new ArrayList<>();
		for (ctppDTO ctpp : dsctpp) {
			if (ctpp.getMapp().equals(ID)) {
				edited.add(ctpp);
			}
		}
		return edited;
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
