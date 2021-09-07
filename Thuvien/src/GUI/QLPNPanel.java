package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import BUS.ctpnBUS;
import BUS.nhanvienBUS;
import BUS.phieunhapBUS;
import BUS.sachBUS;
import DTO.phieunhapDTO;
import DTO.sachDTO;
import TOOL.check;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import DTO.ctpnDTO;
import java.time.Clock;
import javax.swing.ImageIcon;

public class QLPNPanel extends JPanel implements MouseListener, ActionListener {

    public static String mapn = null;
    public static boolean clickedToCreate = false;
    public JTextField txMapn;
    public JTextField txManv;
    public JTextField txMancc;
    public JDateChooser datechooser;
    public JTextField txMsach;
    public JTextField txNcc;
    public JTextField txTensach, txNXB, txLV, txTL, txTG, txSoluong, txGiatien;
    public tablePN table;
    public tableCTPN tableCTP;
    public JButton btnThemPN, btnXoaPN, btnSuaPN, btnThemCTPN, btnXoaCTPN, btnSuaCTPN, btnNV,btnNcc;
    public ctpnBUS busCTPN;
    public phieunhapBUS busPN;
    public sachBUS busSach;
    public nhanvienBUS busNhanvien;
    public JButton btnTL, btnTG, btnNXB, btnLV;
    public ArrayList<ctpnDTO> temp;

    public QLPNPanel() throws Exception {
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel lbMaPN = new JLabel("Mã phiếu nhập");
        lbMaPN.setBounds(650, 30, 100, 25);
        add(lbMaPN);

        txMapn = new JTextField();
        txMapn.setBounds(800, 29, 150, 25);
        txMapn.setEditable(false);
        add(txMapn);

        JLabel lbManv = new JLabel("Mã nhân viên");
        lbManv.setBounds(650, 70, 100, 25);
        add(lbManv);

        txManv = new JTextField();
        txManv.setText(nhanvienBUS.userID);
        txManv.setBounds(800, 70, 150, 26);
        txManv.setEditable(false);
        add(txManv);

        JLabel lbNgaynhap = new JLabel("Ngày nhập");
        lbNgaynhap.setBounds(650, 110, 100, 25);
        add(lbNgaynhap);

        datechooser = new JDateChooser();
        datechooser.setBounds(800, 110, 150, 25);
        datechooser.setDateFormatString("yyyy-MM-dd");
        add(datechooser);

        JLabel lbNcc = new JLabel("Mã nhà cung cấp");
        lbNcc.setBounds(650, 150, 100, 25);
        add(lbNcc);

        txNcc = new JTextField();
        txNcc.setBounds(800, 150, 125, 25);
        add(txNcc);

        btnNcc = new JButton("...");
        btnNcc.setBounds(925, 150, 25, 25);
        btnNcc.addActionListener(this);
        add(btnNcc);

        table = new tablePN();
        phieunhapBUS bus = new phieunhapBUS();
        table.setData(bus.getDSPN());
        table.loadData();
        table.setBounds(30, 30, 550, 200);
        add(table);

        busCTPN = new ctpnBUS();
        tableCTP = new tableCTPN();
        tableCTP.setBounds(10, 400, 1070, 200);
        tableCTP.setData(busCTPN.getDSCTPN());
        tableCTP.loadData();
        tableCTP.getTable().setEnabled(false);
        add(tableCTP);
        

        table.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int i = table.getTable().getSelectedRow();
                phieunhapDTO phieunhap = phieunhapBUS.dspn.get(i);
                if (i >= 0) {
                    try {
                        btnThemPN.setEnabled(false);
                        txMapn.setText(phieunhap.getMapn());
                        txManv.setText(phieunhap.getManv());
                        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
                        datechooser.setDate(fm.parse(phieunhap.getNgaynhap()));
                        txNcc.setText(phieunhap.getMancc());
                        tableCTP.setData(busCTPN.getDSCTPN(phieunhap.getMapn()));
                        tableCTP.loadData();
                          tableCTP.getTable().setEnabled(true);
                    } catch (Exception ex) {
                        Logger.getLogger(QLPNPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        tableCTP.getTable().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int i = table.getTable().getSelectedRow();
                int j = tableCTP.getTable().getSelectedRow();

                //xử lí xóa chi tiết phiếu của một phiếu nhập
                if (j >= 0) {
                    ctpnDTO ctpn = busCTPN.getDSCTPN((String) table.getTable().getValueAt(i, 0)).get(j);
                    sachBUS bus = new sachBUS();
                    bus.getSachListdaydu();
                    sachDTO sach = bus.getSach(ctpn.getMasach());
                    txTensach.setText(sach.getTensach());
                    txNXB.setText(sach.getManxb());
                    txTL.setText(sach.getMatheloai());
                    txLV.setText(sach.getMalinhvuc());
                    txTG.setText(sach.getMatg());
                    txSoluong.setText(String.valueOf(ctpn.getSoluong()));
                    txGiatien.setText(String.valueOf(ctpn.getDongia()));
                }
            }
        });

        btnThemPN = new JButton("Tạo phiếu mới");
        btnThemPN.setBounds(650, 190, 170, 30);
        btnThemPN.setIcon(new ImageIcon("./icon/icons8_add_32.png"));
        add(btnThemPN);
        btnThemPN.addActionListener(this);

        btnXoaPN = new JButton("Tải lại");
        btnXoaPN.setBounds(820, 190, 130, 30);
        btnXoaPN.setIcon(new ImageIcon("./icon/icons8_synchronize_32.png"));
        btnXoaPN.addActionListener(this);
        add(btnXoaPN);

        JLabel lbTensach = new JLabel("Tên sách :");
        lbTensach.setBounds(30, 270, 100, 25);
        add(lbTensach);

        txTensach = new JTextField();
        txTensach.setBounds(130, 270, 200, 25);
        add(txTensach);

        JLabel lbNXB = new JLabel("NXB :");
        lbNXB.setBounds(350, 270, 50, 25);
        add(lbNXB);

        txNXB = new JTextField();
        txNXB.setBounds(400, 270, 70, 25);
        txNXB.setEditable(false);
        add(txNXB);

        JLabel lbTL = new JLabel("TL :");
        lbTL.setBounds(490, 270, 50, 25);
        add(lbTL);

        txTL = new JTextField();
        txTL.setBounds(520, 270, 70, 25);
        txTL.setEditable(false);
        add(txTL);

        JLabel lbLV = new JLabel("LV :");
        lbLV.setBounds(610, 270, 50, 25);

        add(lbLV);

        txLV = new JTextField();
        txLV.setBounds(660, 270, 70, 25);
        txLV.setEditable(false);
        add(txLV);

        JLabel lbTG = new JLabel("TG :");
        lbTG.setBounds(750, 270, 50, 25);
        add(lbTG);

        txTG = new JTextField();
        txTG.setBounds(820, 270, 70, 25);
        txTG.setEditable(false);
        add(txTG);

        JLabel lbSoluong = new JLabel("Số lượng");
        lbSoluong.setBounds(350, 305, 70, 25);
        add(lbSoluong);

        txSoluong = new JTextField();
        txSoluong.setBounds(420, 305, 100, 25);
        add(txSoluong);

        JLabel lbGiatien = new JLabel("Giá tiền");
        lbGiatien.setBounds(540, 305, 70, 25);
        add(lbGiatien);

        txGiatien = new JTextField();
        txGiatien.setBounds(610, 305, 120, 25);
        add(txGiatien);

        btnThemCTPN = new JButton("Thêm chi tiết");
        btnThemCTPN.setBounds(30, 350, 150, 30);
        btnThemCTPN.addActionListener(this);
        btnThemCTPN.setIcon(new ImageIcon("./icon/icons8_add_32.png"));
        add(btnThemCTPN);

        btnXoaCTPN = new JButton("Xóa chi tiết");
        btnXoaCTPN.setBounds(200, 350, 150, 30);
        btnXoaCTPN.addActionListener(this);
        add(btnXoaCTPN);

        btnSuaCTPN = new JButton("Sửa chi tiết");
        btnSuaCTPN.setBounds(370, 350, 150, 30);
        btnSuaCTPN.addActionListener(this);
        add(btnSuaCTPN);
    }

    @Override

    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        phieunhapDTO pn = new phieunhapDTO();
        if (e.getSource() == btnThemPN) {
            busPN = new phieunhapBUS();
            txMapn.setText(busPN.autoCreateID());
            pn.setMapn(busPN.autoCreateID());
            mapn = pn.getMapn();
           // pn.setManv(txManv.getText());
            pn.setManv(nhanvienBUS.userID);
            pn.setMancc(txNcc.getText());
            Date ngay = new Date();
            if (datechooser.getDate() == null) {
                JOptionPane.showConfirmDialog(null, "Hãy chọn ngày nhập");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(datechooser.getDate());
                pn.setNgaynhap(date);
                try {
                    busPN.Insert(pn);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                table.addData(pn);
            }
        } else if (e.getSource() == btnThemCTPN) {
            int i = table.getTable().getSelectedRow();
            clickedToCreate = true;
            if (i >= 0) {
                themCTFrame ctFrame = new themCTFrame();
                mapn = phieunhapBUS.dspn.get(i).getMapn();
                ctFrame.SPanel.setCTPN(this);
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn phiếu nhập càn thêm chi tiết");
            }
        } else if (e.getSource() == btnNV) {
            try {
                selectNV nhanvien = new selectNV();
                nhanvien.setVisible(true);
                nhanvien.loaddata();
                nhanvien.select(txManv);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(QLPNPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnXoaCTPN) {

            int i = table.getTable().getSelectedRow();
            int j = tableCTP.getTable().getSelectedRow();
            //System.out.println(j);
            ctpnBUS bus = new ctpnBUS();
            busPN=new phieunhapBUS();
            busCTPN=new ctpnBUS();
            try {
                bus.getDSCTPN();
                busPN.getDSPN();
                busCTPN.getDSCTPN();
            } catch (Exception ex) {
                System.out.println("Lỗi dòng 327-QSPNPanel");
            }
            if (j >= 0) {
                //xử lí xóa chi tiết phiếu của một phiếu nhập
                ctpnDTO ctpn = bus.getDSCTPN((String) table.getTable().getValueAt(i, 0)).get(j);
                phieunhapDTO phieunhap = busPN.getPhieunhap(ctpn.getMapn());
                long price = ctpn.getThanhtien();
                tableCTP.deleteData(j);
//              chỉnh sửa và thay đổi tổng tiền của phiếu nhập
                try {
                    busCTPN.subPrice(phieunhap.getMapn(), price);
                    table.loadData();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
//              chỉnh sửa số lượng của sách
                busSach = new sachBUS();
                busSach.getSachListdaydu();
                sachDTO sach = busSach.getSach(ctpn.getMasach());
                sach.setSoluong(sach.getSoluong() - ctpn.getSoluong());
                busSach.Update(sach);
                busCTPN.Delete(ctpn);
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn chi tiết cần xóa");
            }

        } else if (e.getSource() == btnSuaCTPN) {
            int i = table.getTable().getSelectedRow();
            int j = tableCTP.getTable().getSelectedRow();
            busCTPN = new ctpnBUS();
            busSach = new sachBUS();
            busPN = new phieunhapBUS();
            try {
                busCTPN.getDSCTPN();
                busSach.getSachListdaydu();
                busPN.getDSPN();
            } catch (Exception ex) {
                Logger.getLogger(QLPNPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (j >= 0) {
                //xử lí xóa chi tiết phiếu của một phiếu nhập
                phieunhapDTO phieunhap = phieunhapBUS.dspn.get(i);
                System.out.println(phieunhap.toString());
                ctpnDTO ctpn = busCTPN.getDSCTPN((String) table.getTable().getValueAt(i, 0)).get(j);
                System.out.println(ctpn.toString());
                int soluongcu = ctpn.getSoluong();
                int soluongmoi = Integer.parseInt(txSoluong.getText());
                long giamoi = Long.parseLong(txGiatien.getText());
                long thanhtiencu = ctpn.getThanhtien();
                long thanhtienmoi = soluongmoi * giamoi;
                try {
                    if (thanhtiencu > thanhtienmoi) {
                        busCTPN.subPrice(phieunhap.getMapn(), (thanhtiencu - thanhtienmoi));

                    } else {
                        busCTPN.countPrice(phieunhap.getMapn(), (thanhtienmoi - thanhtiencu));
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                ctpn.setSoluong(soluongmoi);
                ctpn.setDongia(giamoi);
                ctpn.setThanhtien(thanhtienmoi);
                busCTPN=new ctpnBUS(); try {
                    busCTPN.getDSCTPN();
                } catch (Exception ex) {
                    Logger.getLogger(QLPNPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                busCTPN.Update(ctpn);
                table.loadData();
                tableCTP.loadData();
                
                sachDTO sach = busSach.getSach(ctpn.getMasach());
                if(soluongcu>soluongmoi){
                    sach.setSoluong(sach.getSoluong()-(soluongcu-soluongmoi));
                    busSach.Update(sach);
                }else{
                     sach.setSoluong(sach.getSoluong()+(soluongmoi-soluongcu));
                    busSach.Update(sach);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn phiếu nhập cần sửa");
            }
        } else if (e.getSource() == btnXoaPN) {
            txMapn.setText(null);
            txManv.setText(null);
            datechooser.setDate(null);
            txNcc.setText(null);
            btnThemPN.setEnabled(true);
        } else if (e.getSource() == btnNcc){
            try {
                selectNCC ncc=new selectNCC();
                ncc.loaddata();
                ncc.setVisible(true);
                ncc.select(txNcc);
            } catch (Exception ex) {
                Logger.getLogger(QLPNPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
