package BUS;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import DAO.docgiaDAO;
import DTO.docgiaDTO;

public class docgiaBUS {

    public static ArrayList<docgiaDTO> dsdg;
    docgiaDAO data = new docgiaDAO();

    public ArrayList<docgiaDTO> getDGList() {
        if (dsdg == null) {
            dsdg = new ArrayList<docgiaDTO>();
        }
        //doc truyen du lieu vao array list do nha hjhj
        try {
            dsdg = data.filteredList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsdg;
    }

    public void Insert(docgiaDTO dg) throws Exception {
        data.Insert(dg);
        dsdg.add(dg);
    }

    public void Delete(docgiaDTO dg) throws Exception {
        data.Delete(dg);
        dsdg.remove(dg);
    }

    public void Update(docgiaDTO dg) throws Exception {
        data.Update(dg);
        int k = 0;
        for (int i = 0; i < dsdg.size(); i++) {
            if (dsdg.get(i).getMadg().equals(dg.getMadg()));
            k = i;

        }
        dsdg.set(k, dg);
    }

    public boolean sameid(String a) {
        for (int i = 0; i < dsdg.size(); i++) {
            docgiaDTO temp = ((docgiaDTO) dsdg.get(i));
            if (temp.getMadg().equals(a)) {
                return true; // true là trùng
            }
        }
        return false;
    }

    public String autoCreateID() {
        String ID = null;
        docgiaDAO data = new docgiaDAO();
        try {
            dsdg = data.docDG();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dsdg.size() < 10) {
            ID = "DG00" + String.valueOf(dsdg.size() + 1);
        } else if (dsdg.size() >= 10 && dsdg.size() < 100) {
            ID = "DG0" + String.valueOf(dsdg.size() + 1);
        } else if (dsdg.size() >= 100) {
            ID = "DG" + String.valueOf(dsdg.size() + 1);
        }
        return ID;
    }
}