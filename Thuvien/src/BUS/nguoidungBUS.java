package BUS;

import java.util.ArrayList;
import DAO.nguoidungDAO;
import DTO.nguoidungDTO;
import TOOL.MD5Hash;

import java.util.logging.Level;
import java.util.logging.Logger;

public class nguoidungBUS {

    public static ArrayList<nguoidungDTO> dsnd;
    nguoidungDAO data = new nguoidungDAO();

    public ArrayList<nguoidungDTO> getNDList() {
        if (dsnd == null) {
            dsnd = new ArrayList<>();
        }
        try {
            dsnd = data.docDSND();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dsnd;
    }

    public void Insert(nguoidungDTO nd) throws Exception {
        data.Insert(nd);
        dsnd.add(nd);
    }

    public void Delete(nguoidungDTO nd) throws Exception {
        data.Delete(nd);
        dsnd.remove(nd);
    }

    public void Update(nguoidungDTO nd) throws Exception {
        data.Update(nd);
        int k = 0;
        for (int i = 0; i < dsnd.size(); i++) {
            if (dsnd.get(i).getManv().compareTo(nd.getManv()) == 0) {
                k = i;
            }
        }
        dsnd.set(k, nd);
    }

    public void Block(nguoidungDTO nd) throws Exception {
        nd.setTrangthai(2);
        data.Update(nd);
        int k = 0;
        for (int i = 0; i < dsnd.size(); i++) {
            if (dsnd.get(i).getManv().compareTo(nd.getManv()) == 0) {
                k = i;
            }
        }
        dsnd.set(k, nd);
    }

    /* public boolean checkAccount(String username, String pass, String role,int trangthai) {
        for (int i = 0; i < dsnd.size(); i++) {
            if (username.equals(dsnd.get(i).getManv()) && pass.equals(dsnd.get(i).getMkhau())
                    && role.equals(dsnd.get(i).getQuyen())&&trangthai==1) {
                return true;
            }
        }
        return false;
    } */

    public boolean checkAccount(String username, String pass, String role,int trangthai) throws Exception {
        for (int i = 0; i < dsnd.size(); i++) {
            if (username.equals(dsnd.get(i).getManv()) && role.equals(dsnd.get(i).getQuyen())&&trangthai==1) {
            	System.out.println(MD5Hash.getMd5(pass));
            	System.out.println(pass);
                if(pass.equals(dsnd.get(i).getMkhau())) {
                	if(data.updateHash(username, pass)) {
                		return true;
                	}
                } else if (MD5Hash.getMd5(pass).equals(dsnd.get(i).getMkhau())) {
                	return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkPass(String username, String pass) {
        for (nguoidungDTO nd : dsnd) {
            if (username.compareTo(nd.getManv()) == 0 && pass.compareTo(nd.getMkhau()) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNewPass(String pass) {
        int ky_tu_in_hoa = 0;
        int ky_tu_thuong = 0;
        int so = 0;
        for (int i = 0; i < pass.length(); i++) {
            if (pass.charAt(i) >= 65 && pass.charAt(i) <= 90) {
                ky_tu_in_hoa++;
            } else if (pass.charAt(i) >= 97 && pass.charAt(i) <= 122) {
                ky_tu_thuong++;
            } else if (pass.charAt(i) >= 49 && pass.charAt(i) <= 57) {
                so++;
            }
        }
        System.out.print(ky_tu_in_hoa + " " + ky_tu_thuong + " " + so);
        if (ky_tu_in_hoa != 0 && ky_tu_thuong != 0 && so != 0 && pass.length() >= 6) {
            return true;
        } else {
            return false;
        }
    }

    public nguoidungDTO getNDbyID(String manv) {
        nguoidungDTO nd = new nguoidungDTO();
        for (nguoidungDTO temp : dsnd) {
            if (temp.getManv().compareTo(manv) == 0) {
                nd.setManv(temp.getManv());
                nd.setMkhau(temp.getMkhau());
                nd.setQuyen(temp.getQuyen());
                nd.setTrangthai(temp.getTrangthai());
            }
        }
        return nd;
    }
}
