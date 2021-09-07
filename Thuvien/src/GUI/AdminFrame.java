package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import TOOL.design;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

public class AdminFrame extends UserFrame implements ActionListener, MouseListener {

    private int j = 0;
    private JLabel lbNhanvien;
    private JLabel lbTaikhoan;
    private QLNVPanel nhanvien;
    private QLTKPanel taikhoan;
    private JPanel pnNhanvien;
    private JPanel pnTaikhoan;
    private thongkePanel tk;

    public AdminFrame() {
        super();
        pnPhieu.setLocation(0, 250);
        pnSach.setLocation(0, 200);
        pnPhieunhap.setLocation(0, 300);

        pnNhanvien = new JPanel();
        pnNhanvien.setBounds(0, 100, 180, 40);
        pnNhanvien.setBackground(new Color(45, 118, 232));
        pnNhanvien.addMouseListener(this);
        leftPanel.add(pnNhanvien);
        pnNhanvien.setLayout(null);

        lbNhanvien = new JLabel("        Nhân viên");
        lbNhanvien.setForeground(Color.WHITE);
        lbNhanvien.setBounds(0, 0, 180, 40);
        ImageIcon iconNV = design.resizeIcon("./icon/icons8_Resume_64.png", pnNhanvien.getWidth() / 3,
                (int) (pnNhanvien.getHeight() * 1.5));
        lbNhanvien.setIcon(iconNV);
        pnNhanvien.add(lbNhanvien);

        pnTaikhoan = new JPanel();
        pnTaikhoan.setBounds(0, 150, 180, 40);
        pnTaikhoan.setBackground(new Color(45, 118, 232));
        pnTaikhoan.addMouseListener(this);
        leftPanel.add(pnTaikhoan);
        pnTaikhoan.setLayout(null);

        lbTaikhoan = new JLabel("        Tài khoản");
        lbTaikhoan.setForeground(Color.WHITE);
        lbTaikhoan.setBounds(0, 0, 180, 40);
        ImageIcon iconTK = design.resizeIcon("./icon/icons8_user_64.png", pnTaikhoan.getWidth() / 3,
                (int) (pnTaikhoan.getHeight() * 1.5));
        lbTaikhoan.setIcon(iconTK);
        pnTaikhoan.add(lbTaikhoan);
        

        pnSach.setBounds(0, 200, 180, 40);
        pnSach.addMouseListener(this);

        pnPhieu.setBounds(0, 250, 180, 40);
        pnPhieu.addMouseListener(this);

        pnPhieunhap.setBounds(0, 290, 180, 40);
        pnPhieumuon.setBounds(0, 330, 180, 40);
        pnPhieuphat.setBounds(0, 370, 180, 40);

        try {
            //        try {
//            nhanvien = new QLNVPanel();
//        } catch (Exception ex) {
//            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        centerPanel.add(nhanvien, BorderLayout.CENTER);

            tk = new thongkePanel();
        } catch (Exception ex) {
            System.out.println("Lỗi adminframe-dòng 88");
        }
           centerPanel.add(tk,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        super.mouseClicked(evt);
        if (evt.getSource() == lbNhanvien||evt.getSource()==pnNhanvien) {
            centerPanel.removeAll();
            try {
                nhanvien = new QLNVPanel();
            } catch (Exception e1) {
                System.out.println(e1);
            }
            centerPanel.add(nhanvien, BorderLayout.CENTER);
            centerPanel.repaint();
            centerPanel.revalidate();
        }else if(evt.getSource()==lbTaikhoan||evt.getSource()==pnTaikhoan){
              centerPanel.removeAll();
            try {
                taikhoan = new QLTKPanel();
            } catch (Exception e1) {
                System.out.println(e1);
            }
            centerPanel.add(taikhoan, BorderLayout.CENTER);
            centerPanel.repaint();
            centerPanel.revalidate();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
}
