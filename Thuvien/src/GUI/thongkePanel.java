package GUI;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import BUS.thongkeBUS;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class thongkePanel extends JPanel implements ActionListener {

    private JDateChooser dateBegin, dateEnd;
    private JButton btnThongke;
    private JPanel pnThongtin;
    public JTextField txThanhvienmoi;
    private JTable tk1, tk2, tk3, tk4;
    private thongkeBUS thongke;

    public thongkePanel() throws Exception {
        setBackground(Color.WHITE);
        setLayout(null);
        thongke = new thongkeBUS();

        tk1 = new JTable();
        try {
            tk1.setModel(thongke.thongKeThuchi());
        } catch (Exception ex) {
            Logger.getLogger(thongkePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        JScrollPane sc1 = new JScrollPane(tk1);
        sc1.setBounds(20, 20, 1000, 100);
        sc1.setBorder(new TitledBorder(null, "Th?ng kê thu chi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(sc1);

        JLabel lbMaxThu = new JLabel("Max t?ng thu :");
        lbMaxThu.setBounds(20, 135, 120, 25);
        add(lbMaxThu);

        JTextField txMaxThu;
        try {
            txMaxThu = new JTextField(String.valueOf(thongke.maxThu()));
            txMaxThu.setBounds(140, 135, 60, 25);
        txMaxThu.setEditable(false);
        add(txMaxThu);
        } catch (Exception ex) {
            Logger.getLogger(thongkePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        JLabel lbMaxChi = new JLabel("Max t?ng chi :");
        lbMaxChi.setBounds(220, 135, 120, 25);
        add(lbMaxChi);

        JTextField txMaxChi = new JTextField(String.valueOf(thongke.maxChi()));
        txMaxChi.setBounds(340, 135, 60, 25);
        txMaxChi.setEditable(false);
        add(txMaxChi);

        tk2 = new JTable();
        tk2.setModel(thongke.thongKeThanhVienMoi());
        JScrollPane sc2 = new JScrollPane(tk2);
        sc2.setBounds(20, 180, 250, 200);
        sc2.setBorder(new TitledBorder(null, "Th?ng kê thành viên m?i", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(sc2);

        JPanel pn_dtd = new JPanel();
        pn_dtd.setLayout(null);
        pn_dtd.setBounds(20, 400, 500, 100);
        pn_dtd.setBorder(new TitledBorder(null, "Th?ng kê t? ngày t?i ngày", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(pn_dtd);

        JLabel lbBegin = new JLabel("T? :");
        lbBegin.setBounds(50, 25, 30, 25);
        pn_dtd.add(lbBegin);

        tk3 = new JTable();
        tk3.setModel(thongke.thongKePhieuNhap());
        JScrollPane sc3 = new JScrollPane(tk3);
        sc3.setBounds(270, 180, 250, 200);
        sc3.setBorder(new TitledBorder(null, "Th?ng kê phi?u nh?p", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(sc3);
        dateBegin = new JDateChooser();
        dateBegin.setDateFormatString("yyyy-MM-dd");
        dateBegin.setBounds(100, 25, 100, 25);
        pn_dtd.add(dateBegin);

        tk4 = new JTable();
        tk4.setModel(thongke.topBook());
        JScrollPane sc4 = new JScrollPane(tk4);
        sc4.setBounds(530, 180, 550, 200);
        sc4.setBorder(new TitledBorder(null, "S?n ph?m du?c mu?n nhi?u nh?t", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(sc4);

        JLabel lbThinhhanh = new JLabel("Sách th?nh hành nh?t");
        lbThinhhanh.setBounds(550, 400, 200, 25);
        add(lbThinhhanh);

        JTextField txTopbook = new JTextField(thongke.thongKeTheoSanPham());
        txTopbook.setBounds(750, 400, 300, 25);
        add(txTopbook);
        
        JLabel lbNhanvien = new JLabel("S? lu?ng nhân viên");
        lbNhanvien.setBounds(550, 430, 200, 25);
        add(lbNhanvien);
        
        JTextField txNhanvien = new JTextField(thongke.countNhanvien());
        txNhanvien.setBounds(750, 430, 300, 25);
        add(txNhanvien);

        JLabel lbTo = new JLabel("Đ?n :");
        lbTo.setBounds(250, 25, 30, 25);
        pn_dtd.add(lbTo);

        dateEnd = new JDateChooser();
        dateEnd.setDateFormatString("yyyy-MM-dd");
        dateEnd.setBounds(300, 25, 100, 25);
        pn_dtd.add(dateEnd);

        btnThongke = new JButton("Th?ng kê");
        btnThongke.setBounds(180, 60, 100, 25);
        btnThongke.addActionListener(this);
        pn_dtd.add(btnThongke);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThongke) {
            if (dateBegin.getDate() != null && dateEnd.getDate() != null) {
                try {
                    thongke.deleteAll(tk2);

                    tk2.setModel(thongke.thongKeThanhVienMoi(dateBegin.getDate(), dateEnd.getDate()));
                    thongke.deleteAll(tk3);
                    tk3.setModel(thongke.thongKePhieuNhap(dateBegin.getDate(), dateEnd.getDate()));
                } catch (Exception ex) {
                    System.out.println("thongkePanel l?i 136"+ex);
                }
            } else if (dateBegin.getDate() != null && dateEnd.getDate() == null) {
                Date date = new Date();
                dateEnd.setDate(date);
                dateEnd.setDateFormatString("yyyy-MM-dd");
                thongke.deleteAll(tk2);
                try {
                    tk2.setModel(thongke.thongKeThanhVienMoi(dateBegin.getDate(), dateEnd.getDate()));
                    thongke.deleteAll(tk3);
                    tk3.setModel(thongke.thongKePhieuNhap(dateBegin.getDate(), dateEnd.getDate()));
                } catch (Exception ex) {
                    System.out.println("thongkePanel l?i 148"+ex);
                }
            } else if (dateEnd.getDate() == null && dateEnd.getDate() != null) {
                String date = "2020-01-01";
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dateBegin.setDate(format.parse(date));
                    thongke.deleteAll(tk2);
                    try {
                        tk2.setModel(thongke.thongKeThanhVienMoi(dateBegin.getDate(), dateEnd.getDate()));
                        thongke.deleteAll(tk3);
                        tk3.setModel(thongke.thongKePhieuNhap(dateBegin.getDate(), dateEnd.getDate()));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } catch (Exception ex) {
                    System.out.println("thongkePanel l?i 164"+ex);
                }

            } else {
                thongke.deleteAll(tk2);
                try {
                    tk2.setModel(thongke.thongKeThanhVienMoi());
                } catch (Exception ex) {
                    Logger.getLogger(thongkePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                thongke.deleteAll(tk3);
                try {
                    tk3.setModel(thongke.thongKePhieuNhap());
                } catch (Exception ex) {
                    Logger.getLogger(thongkePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
