package BUS;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DAO.sachDAO;
import DTO.sachDTO;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class sachBUS {

    public static ArrayList<sachDTO> dss;
    public static ArrayList<sachDTO> dssdaydu;

    public ArrayList<sachDTO> getSachList() {
        if (dss == null) {
            dss = new ArrayList<>();
        }
        //Ä‘á»c dá»¯ liá»‡u lĂªn vĂ  truyá»n vĂ o arraylist
        try {
            sachDAO dao = new sachDAO();
            dss = dao.filteredList();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dss;
    }

    public void getSachListdaydu() {
        if (dssdaydu == null) {
            dssdaydu = new ArrayList<sachDTO>();
        }
        //Ä‘á»c dá»¯ liá»‡u lĂªn vĂ  truyá»n vĂ o arraylist
        try {
            sachDAO dao = new sachDAO();
            dao.docDSS();
            dssdaydu = dao.getdss();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Insert(sachDTO a) throws Exception {
        if (sameid(a.getMasach()) == false) {
            sachDAO dao = new sachDAO();
            dao.Insert(a);
            dss.add(a);
        } else {
            JOptionPane.showMessageDialog(null, "Sách đã tồn tại-Không thể thêm");
        }
    }

    public void Delete(sachDTO a) {
        try {
            a.setTrangthai(0);
            a.setHinhanh("");
            sachDAO dao = new sachDAO();
            dss.remove(sameid(a));
            dao.Delete(a);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa sách");
        }

    }
    

    public void Update(sachDTO a) {
        try {
            sachDAO dao = new sachDAO();
            dao.Update(a);
            dss.set(sameid(a), a);

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Lỗi sửa");
            System.out.println(e);
        }
    }

    public boolean sameid(String a) {
        for (int i = 0; i < dss.size(); i++) {
            sachDTO temp = ((sachDTO) dss.get(i));
            if (temp.getMasach().equals(a)) {
                return true;                                    //true là trùng 
            }
        }
        return false;
    }

    public int sameid(sachDTO a) {
        for (int i = 0; i < dss.size(); i++) {
            sachDTO temp = dss.get(i);
//                JOptionPane.showMessageDialog(null, temp.getMasach());
            if (temp.getMasach().equals(a.getMasach())) {
                System.out.println(i);
                return i;
            }
        }
        
        
        return -1;
    }

    public boolean sameiddaydu(String a) {
        for (int i = 0; i < dssdaydu.size(); i++) {
            sachDTO temp = ((sachDTO) dssdaydu.get(i));
            if (temp.getMasach().equals(a)) {
                return true;                                    //true là trùng 
            }
        }
        return false;
    }

    public int checkBookInFullList(String tensach, String nxb, String lv, String theloai, String tacgia) {
        int i = 0;
        for (sachDTO sach : dssdaydu) {
            boolean ktTen = tensach.trim().replaceAll("\\s+", " ").toLowerCase().compareToIgnoreCase(sach.getTensach().trim().replaceAll("\\s+", " ").toLowerCase()) == 0;
            boolean ktNXB = nxb.compareToIgnoreCase(sach.getManxb()) == 0;
            boolean ktMalinhvuc = lv.compareToIgnoreCase(sach.getMalinhvuc()) == 0;
            boolean ktMatheloai = theloai.compareToIgnoreCase(sach.getMatheloai()) == 0;
            boolean ktTacgia = tacgia.compareToIgnoreCase(sach.getMatg()) == 0;
            //System.out.println(ktTen+" "+ktNXB+" "+ktMalinhvuc+" "+ktMatheloai+" "+ktTacgia);
            //System.out.println(sach.getMatheloai()+" "+sach.getMatg());
            if (ktTen && ktNXB && ktMalinhvuc && ktMatheloai && ktTacgia) {
                if (sach.getTrangthai() == 0) {
                    i = 1;
                    break;
                } else if (sach.getTrangthai() == 1) {
                    i = 2;
                    break;
                }
            } else {
                i = 3;
                break;
            }
        }
        System.out.println(i);
        return i;
    }

    public String sinhma() {
        String temp = null;
        if (dssdaydu.size() < 10) {
            temp = "S00" + String.valueOf(dssdaydu.size() + 1);
        } else if (dssdaydu.size() >= 10 && dssdaydu.size() < 100) {
            temp = "S0" + String.valueOf(dssdaydu.size() + 1);
        } else {
            temp = "S" + String.valueOf(dssdaydu.size() + 1);
        }
        return temp;
    }

    public int getSoluong(String masach) {
        for (sachDTO sach : dss) {
            if (masach.equals(sach.getMasach())) {
                return sach.getSoluong();
            }
        }
        return 0;
    }
    
    public sachDTO getSach(String tensach, String nxb, String lv, String theloai, String tacgia){
        for (sachDTO sach : dssdaydu) {
            boolean ktTen = tensach.trim().replaceAll("\\s+", " ").toLowerCase().compareToIgnoreCase(sach.getTensach().trim().replaceAll("\\s+", " ").toLowerCase()) == 0;
            boolean ktNXB = nxb.compareToIgnoreCase(sach.getManxb()) == 0;
            boolean ktMalinhvuc = lv.compareToIgnoreCase(sach.getMalinhvuc()) == 0;
            boolean ktMatheloai = theloai.compareToIgnoreCase(sach.getMatheloai()) == 0;
            boolean ktTacgia = tacgia.compareToIgnoreCase(sach.getMatg()) == 0;
            if (ktTen && ktNXB && ktMalinhvuc && ktMatheloai && ktTacgia) {
               return sach;
            }
        }
        return null;
    }

    public void updateSoluong(String tensach, String nxb, String lv, String theloai, String tacgia, int soluong, int trangthai) throws Exception {
         int i = 0;
        for (sachDTO sach : dssdaydu) {
            boolean ktTen = tensach.trim().replaceAll("\\s+", " ").toLowerCase().compareToIgnoreCase(sach.getTensach().trim().replaceAll("\\s+", " ").toLowerCase()) == 0;
            boolean ktNXB = nxb.compareToIgnoreCase(sach.getManxb()) == 0;
            boolean ktMalinhvuc = lv.compareToIgnoreCase(sach.getMalinhvuc()) == 0;
            boolean ktMatheloai = theloai.compareToIgnoreCase(sach.getMatheloai()) == 0;
            boolean ktTacgia = tacgia.compareToIgnoreCase(sach.getMatg()) == 0;
            if (ktTen && ktNXB && ktMalinhvuc && ktMatheloai && ktTacgia) {
                sach.setSoluong(sach.getSoluong()+soluong);
                Update(sach);
            }
        }
    }
    
    public sachDTO getSach(String ID){
        for(sachDTO sach:dssdaydu){
            if(sach.getMasach().equals(ID)){
                return sach;
            }
        }
        return null;
    }
    
    public int getHeader()
    {
        return 8;
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String tensach = JOptionPane.showInputDialog("");
        String nxb = input.nextLine();
        String lv = input.nextLine();
        String theloai = input.nextLine();
        String tacgia = input.nextLine();

        sachDAO data = new sachDAO();
        data.docDSS();
        dssdaydu = data.getdss();
//        int i = checkBookInFullList(tensach, nxb, lv, theloai, tacgia);
//        if (i == 1) {
//            System.out.println("Hello");
//        }
    }


    
    
}
