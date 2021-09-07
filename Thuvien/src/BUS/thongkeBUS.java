package BUS;

import DTO.ctpmDTO;
import DTO.phieumuonDTO;
import DTO.phieunhapDTO;
import DTO.sachDTO;
import DTO.theTVDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class thongkeBUS {

    private long maxthu=0;
    private long maxchi=0;
    
    public long tinhTongThu(int index) throws Exception {
        long tongthu = 0;
        phieumuonBUS busPM=new phieumuonBUS();
        busPM.getPMList();
        try {
            for (phieumuonDTO pm : phieumuonBUS.dspm) {
                String[] ngayMuon = pm.getNgaymuon().split("-");
                if (Integer.parseInt(ngayMuon[1]) == index) {
                    tongthu += pm.getTongtienmuon();
                }
            }
        } catch (Exception ex) {
            System.out.println("L?i ḍng 36 "+ex);
        }
        if(tongthu>maxthu) maxthu=tongthu;
        return tongthu;
    }

    public long tinhTongChi(int index) throws Exception {
        long tongchi = 0;
        phieunhapBUS busPN=new phieunhapBUS();
        busPN.getDSPN();
        try {
            for (phieunhapDTO pn : phieunhapBUS.dspn) {
                String[] ngayNhap = pn.getNgaynhap().split("-");
                if (Integer.parseInt(ngayNhap[1]) == index) {
                    tongchi += pn.getTongtien();
                }
            }
        } catch (Exception ex) {
            System.out.println("L?i ḍng 53 "+ex);
        }
        if(tongchi>maxchi) maxchi=tongchi;
        return tongchi;
    }

    public long maxChi() throws Exception {
        return maxchi;
    }

    public long maxThu() throws Exception {
        return maxthu;
    }

    public DefaultTableModel thongKeThuchi() throws Exception {
        DefaultTableModel thongKeThuchi = new DefaultTableModel();
        Vector header1 = new Vector();
        header1.add("");
        for (int i = 1; i <= 12; i++) {
            header1.add("Tháng " + String.valueOf(i));
        }
        thongKeThuchi = new DefaultTableModel(header1, 3){
                @Override
                public boolean isCellEditable(int row,int col){
                    return false;
                }
            };
        thongKeThuchi.setValueAt("T?ng thu", 0, 0);
        thongKeThuchi.setValueAt("T?ng chi", 1, 0);
        thongKeThuchi.setValueAt("T?ng thu chi", 2, 0);
        for (int i = 1; i <= 12; i++) {
            thongKeThuchi.setValueAt(tinhTongThu(i), 0, i);
            thongKeThuchi.setValueAt(tinhTongChi(i), 1, i);
            thongKeThuchi.setValueAt(tinhTongChi(i) + tinhTongThu(i), 2, i);
        }
        return thongKeThuchi;
    }

    public void deleteAll(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
    }

    public DefaultTableModel thongKeThanhVienMoi() throws Exception {
        DefaultTableModel thanhvienmoi = new DefaultTableModel();
        Vector head2 = new Vector();
        head2.add("Mă th?");
        head2.add("Mă d?c gi?");
        head2.add("Ngày l?p");
        if (thanhvienmoi.getRowCount() == 0) {
            thanhvienmoi = new DefaultTableModel(head2, 0){
                @Override
                public boolean isCellEditable(int row,int col){
                    return false;
                }
            };
        }
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String ngayHomNay = format.format(today);
        String[] mangNgayHomNay = ngayHomNay.split("-");
        theTVBUS bus = new theTVBUS();
        bus.getTTVList();
        try {
            for (theTVDTO the : theTVBUS.dsttv) {
                String[] mangNgayLapThe = the.getNgaycap().split("-");
                if (mangNgayHomNay[1].equals(mangNgayLapThe[1])) {
                    String[] theTV = {the.getMathe(), the.getMadg(), the.getNgaycap()};
                    thanhvienmoi.addRow(theTV);
                }
            }
        } catch (Exception ex) {
            System.out.println("L?i ḍng 132 "+ ex);
        }

        return thanhvienmoi;
    }

    public DefaultTableModel thongKeThanhVienMoi(Date dateBegin, Date dateEnd) throws Exception {
        DefaultTableModel thanhvienmoi = new DefaultTableModel();
        Vector head2 = new Vector();
        head2.add("Mă th?");
        head2.add("Mă d?c gi?");
        head2.add("Ngày l?p");
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (thanhvienmoi.getRowCount() == 0) {
            thanhvienmoi = new DefaultTableModel(head2, 0){
                @Override
                public boolean isCellEditable(int row,int col){
                    return false;
                }
            };
        }
        theTVBUS bus = new theTVBUS();
        bus.getTTVList();
            for (theTVDTO the : theTVBUS.dsttv) {
            Date ngaycap = format.parse(the.getNgaycap());
            if (ngaycap.getTime() >= dateBegin.getTime() && ngaycap.getTime() <= dateEnd.getTime()) {
                String[] theTV = {the.getMathe(), the.getMadg(), the.getNgaycap()};
                thanhvienmoi.addRow(theTV);
            }
        }
        return thanhvienmoi;
    }

    public DefaultTableModel thongKePhieuNhap() throws Exception {
        DefaultTableModel thongkephieunhap = new DefaultTableModel();
        Vector head2 = new Vector();
        head2.add("Mă phi?u");
        head2.add("Ngày nh?p ");
        head2.add("T?ng ti?n");
        if (thongkephieunhap.getRowCount() == 0) {
            thongkephieunhap = new DefaultTableModel(head2, 0){
                @Override
                public boolean isCellEditable(int row,int col){
                    return false;
                }
            };
        }
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String ngayHomNay = format.format(today);
        String[] mangNgayHomNay = ngayHomNay.split("-");
        phieunhapBUS bus = new phieunhapBUS();
        bus.getDSPN();
        try {
            for (phieunhapDTO phieunhap : phieunhapBUS.dspn) {
                String[] mangNhap = phieunhap.getNgaynhap().split("-");
                if (mangNgayHomNay[1].equals(mangNhap[1])) {
                    String[] PN = {phieunhap.getMapn(), phieunhap.getNgaynhap(), String.valueOf(phieunhap.getTongtien())};
                    thongkephieunhap.addRow(PN);
                }
            }
        } catch (Exception ex) {
            System.out.println("L?i ḍng 183 "+ex);
        }
        return thongkephieunhap;
    }

    public DefaultTableModel thongKePhieuNhap(Date dateBegin, Date dateEnd) throws Exception {
        DefaultTableModel thongkephieunhap = new DefaultTableModel();
        Vector head2 = new Vector();
        head2.add("Mă phi?u");
        head2.add("Ngày nh?p ");
        head2.add("T?ng ti?n");
        if (thongkephieunhap.getRowCount() == 0) {
            thongkephieunhap = new DefaultTableModel(head2, 0){
                @Override
                public boolean isCellEditable(int row,int col){
                    return false;
                }
            };
        }
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        phieunhapBUS bus = new phieunhapBUS();
        bus.getDSPN();
        for (phieunhapDTO phieunhap : phieunhapBUS.dspn) {
            Date ngaynhap = format.parse(phieunhap.getNgaynhap());
            if (ngaynhap.getTime() >= dateBegin.getTime() && ngaynhap.getTime() <= dateEnd.getTime()) {
                String[] PN = {phieunhap.getMapn(), phieunhap.getNgaynhap(), String.valueOf(phieunhap.getTongtien())};
                thongkephieunhap.addRow(PN);
            }
        }
        return thongkephieunhap;
    }

    public int countNhanvien() {
        nhanvienBUS bus = new nhanvienBUS();
        bus.getNVList();
        return nhanvienBUS.dsnv.size();
    }

    //ID ? dây là mă sách
    public int countSinCTPM(String ID) throws Exception {
        int dem = 0;
        ctpmBUS busCTPM = new ctpmBUS();
        busCTPM.getCTPMList();
        try {
            for (ctpmDTO ctpm : ctpmBUS.dsctpm) {
                if (ctpm.getMasach().compareTo(ID) == 0) {
                    dem++;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(thongkeBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dem;
    }

    public String thongKeTheoSanPham() throws Exception {
        int max = Integer.MIN_VALUE;
        sachDTO sach = new sachDTO();
        sachBUS busSach = new sachBUS();
        busSach.getSachList();
        for (sachDTO s : sachBUS.dss) {
            int temp=countSinCTPM(s.getMasach());
            if ( temp> max) {
                max = temp;
                sach = s;
            }
        }
        return sach.getMasach() + " " + sach.getTensach();
    }

//    public DefaultTableModel topBook (){
//       int max = Integer.MIN_VALUE;
//       ArrayList<sachDTO> temp = sachBUS.dssdaydu;
//       DefaultTableModel model = new DefaultTableModel();
//       for(sachDTO s : temp){
//           if(countSinCTPN(s.getMasach())>max){
//              Vector newVector = new Vector();
//              newVector.add(s.getMasach());
//              newVector.add(s.getTensach());
//              newVector.add(countSinCTPN(s.getMasach()));
//              
//              model.addRow(newVector);
//           }
//       }
//       return model
//    }
    public DefaultTableModel topBook() throws Exception {
        int max = Integer.MIN_VALUE;
        Vector header = new Vector();
        header.add("Mă sách");
        header.add("Tên sách");
        header.add("S? lu?ng mu?n");
        DefaultTableModel model = new DefaultTableModel();
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0){
                @Override
                public boolean isCellEditable(int row,int col){
                    return false;
                }
            };
        }
        sachBUS bus = new sachBUS();
        bus.getSachList();
        ArrayList<sachDTO> temp = new ArrayList<>();
        for (sachDTO s : sachBUS.dss) {
            int bientam=countSinCTPM(s.getMasach());
            if ( bientam == 0) {
            } else if (bientam > max) {
                temp.add(s);
                Collections.sort(temp, new Comparator<sachDTO>() {
                    @Override
                    public int compare(sachDTO o1, sachDTO o2) {
                        try {
                            int bientam2= countSinCTPM(o1.getMasach()) - countSinCTPM(o2.getMasach());
                            if (bientam>0) {
                                return -1;
                            } else if (bientam<0) {
                                return 1;
                            } else {
                                return 0;
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(thongkeBUS.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       return 0;
                    }

                });
            }
        }
        for (sachDTO s : temp) {
            Vector newVector = new Vector();
            newVector.add(s.getMasach());
            newVector.add(s.getTensach());
            newVector.add(countSinCTPM(s.getMasach()));
            model.addRow(newVector);
        }
        return model;

    }

}
