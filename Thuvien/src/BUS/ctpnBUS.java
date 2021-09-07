package BUS;

import DAO.ctpnDAO;
import DTO.ctpnDTO;
import DTO.phieunhapDTO;
import DTO.sachDTO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ctpnBUS {

    public static ArrayList<ctpnDTO> dsctpn;
    ctpnDAO data = new ctpnDAO();

    public ArrayList<ctpnDTO> getDSCTPN() throws Exception {
        if (dsctpn == null) {
            dsctpn = new ArrayList<>();
        }
        dsctpn = data.docCTPN();
        return dsctpn;
    }

    //xuất theo mã hóa đơngetDSCTPN
    public ArrayList<ctpnDTO> getDSCTPN(String ID) {
        ArrayList<ctpnDTO> temp = new ArrayList<>();
        if (dsctpn == null) {
            dsctpn = new ArrayList<>();
        }
        for (ctpnDTO ctpn : dsctpn) {
            if (ID.equals(ctpn.getMapn())) {
                temp.add(ctpn);
            }
        }
        return temp;
    }

    public void Insert(ctpnDTO ctpn) {
        dsctpn.add(ctpn);
        try {
            data.Insert(ctpn);
        } catch (Exception ex) {
            Logger.getLogger(ctpnBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Delete(ctpnDTO ctpn) {
        dsctpn.remove(ctpn);
        try {
            data.DeleteReal(ctpn);
        } catch (Exception ex) {
            Logger.getLogger(ctpnBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Update(ctpnDTO ctpn) {
        int k = -1;
        for (int i = 0; i < dsctpn.size(); i++) {
            if (ctpn.getMapn().equals(dsctpn.get(i).getMapn()) && ctpn.getMasach().equals(dsctpn.get(i).getMasach())) {
                k = i;
            }
        }
        if (k > -1) {
            dsctpn.set(k, ctpn);
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn chi tiết muốn sửa");
        }
        try {
            data.Update(ctpn);
        } catch (Exception ex) {
            Logger.getLogger(ctpnBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long Sum(String ID) {
        long sum = 0;
        for (int i = 0; i < dsctpn.size(); i++) {
            if (dsctpn.get(i).getMapn().equals(ID)) {
                dsctpn.get(i).setThanhtien(dsctpn.get(i).getSoluong() * dsctpn.get(i).getDongia());
                sum += dsctpn.get(i).getThanhtien();
            }
        }
        return sum;
    }

    public void countPrice(String Mapn, long price) throws Exception {
        for (phieunhapDTO phieunhap : phieunhapBUS.dspn) {
            if (Mapn.equals(phieunhap.getMapn())) {
                phieunhap.setTongtien(phieunhap.getTongtien()+price);
                phieunhapBUS bus = new phieunhapBUS();
                bus.getDSPN();
                bus.Update(phieunhap);
            }
        }
    }
    
    public void subPrice(String Mapn,long price) throws Exception {
        for(phieunhapDTO phieunhap : phieunhapBUS.dspn){
            if(Mapn.equals(phieunhap.getMapn())){
                phieunhap.setTongtien(phieunhap.getTongtien()-price);
                phieunhapBUS bus = new phieunhapBUS();
                bus.getDSPN();
                bus.Update(phieunhap);
            }
        }
    }
    
    public sachDTO displayInfoBook(ctpnDTO ctpn){
        sachDTO infoBook = null ;
        for(sachDTO sach : sachBUS.dss){
           if(sach.getMasach().equals(ctpn.getMasach())){
                infoBook = sach;
           }
        }
        return infoBook;
    }
        
    public boolean sameID(String pn,String masach){
        for(ctpnDTO ctpn : ctpnBUS.dsctpn){
            if(ctpn.getMapn().equals(pn)&&ctpn.getMasach().equals(masach)){
                return false;
            
                //false là trùng
            }
        }
        return true;
    }
    
    public ctpnDTO getCTPN(String pn,String masach){
        ctpnDTO ctpn = new ctpnDTO();
        for(ctpnDTO temp : dsctpn){
            if(pn.equals(temp.getMapn())&&masach.equals(temp.getMasach())){
                ctpn = temp;
                return ctpn;
            }
        }
        return null;
    }
}
