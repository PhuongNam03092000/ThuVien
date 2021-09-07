package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import BUS.nhanvienBUS;
import TOOL.design;
import java.awt.event.MouseAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserFrame extends JFrame implements MouseListener {
    
    public int i = 0;
    public JPanel mainPanel;
    public JPanel leftPanel, headLeft, bodyLeft;
    public JScrollPane scrollpane;
    public JPanel header, centerPanel, namePanel;
    public JPanel pnSach;
    public JPanel pnPhieu, pnPhieumuon, pnPhieuphat, pnPhieunhap, pnThongke;
    public JLabel lbSach, lbPhieu, lbpn, lbpm, lbpp,lbthongke;
    public QLSPanel sach;
    public QLPNPanel pn;
    public QLPMPanel pm;
    public QLPPPanel pp;
    public thongkePanel tk;
    public JLabel lbName, lbLogOut;
    private JPanel logOutPanel;
    private ImageIcon imgLogout;
    private JLabel lbLogo;
    public JPanel panelPhat;
    
    public UserFrame() {
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1280, 750);
        
        setLocationRelativeTo(null);
        setTitle("Qu?n lí nhân viên");
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(mainPanel);
        
        leftPanel = new JPanel();
        leftPanel.setBorder(new LineBorder(Color.WHITE, 0, true));
        leftPanel.setPreferredSize(new Dimension(180, 650));
        leftPanel.setBackground(new Color(122, 172, 240));
        leftPanel.setLayout(null);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        
        pnSach = new JPanel();
        pnSach.setBounds(0, 100, 200, 40);
        pnSach.setBackground(new Color(45, 118, 232));
        pnSach.addMouseListener(this);
        leftPanel.add(pnSach);
        
        lbSach = new JLabel("        Sách");
        lbSach.setForeground(Color.WHITE);
        lbSach.setBounds(0, 0, 200, 40);
        ImageIcon iconS = design.resizeIcon("./icon/icons8_book_64.png", pnSach.getWidth() / 3,
                (int) (pnSach.getHeight() * 1.4));
        pnSach.setLayout(null);
        lbSach.setIcon(iconS);
        pnSach.add(lbSach);
        
        pnPhieu = new JPanel();
        pnPhieu.setBounds(0, 145, 200, 40);
        pnPhieu.setBackground(new Color(45, 118, 232));
        pnPhieu.addMouseListener(this);
        leftPanel.add(pnPhieu);
        
        lbPhieu = new JLabel("        Phiếu");
        lbPhieu.setForeground(Color.WHITE);
        lbPhieu.setBounds(0, 0, 200, 40);
        lbPhieu.addMouseListener(this);
        ImageIcon iconP = design.resizeIcon("./icon/icons8_Bill_64.png", pnPhieu.getWidth() / 3,
                (int) (pnPhieu.getHeight() * 1.5));
        pnPhieu.setLayout(null);
        lbPhieu.setIcon(iconP);
        pnPhieu.add(lbPhieu);
        
        lbLogo = new JLabel("");
        lbLogo.setBounds(54, 14, 70, 70);
        leftPanel.add(lbLogo);
        
        ImageIcon logo = design.resizeIcon("./icon/book1.png", lbLogo.getWidth(), lbLogo.getHeight());
        lbLogo.setIcon(logo);
        
        header = new JPanel();
        header.setPreferredSize(new Dimension(1200, 40));
        header.setBackground(new Color(45, 118, 232));
        mainPanel.add(header, BorderLayout.NORTH);
        header.setLayout(null);
        
        nhanvienBUS bus = new nhanvienBUS();
        JLabel lbXinchao = new JLabel("Xin chào : ");
        
        lbXinchao.setHorizontalAlignment(SwingConstants.CENTER);
        lbXinchao.setForeground(Color.WHITE);
        lbXinchao.setBounds(740, 0, 60, 36);
        header.add(lbXinchao);
        
        namePanel = new JPanel();
        namePanel.setBounds(815, 0, 200, 40);
        namePanel.setBackground(new Color(45, 118, 232));
        namePanel.setLayout(null);
        namePanel.addMouseListener(this);
        header.add(namePanel);
        
        lbName = new JLabel(bus.findName());
        lbName.setHorizontalAlignment(SwingConstants.CENTER);
        lbName.setBounds(0, 0, 200, 40);
        lbName.addMouseListener(this);
        namePanel.add(lbName);
        
        logOutPanel = new JPanel();
        logOutPanel.setBounds(0, 0, 41, 40);
        logOutPanel.setBackground(new Color(45, 118, 232));
        header.add(logOutPanel);
        logOutPanel.setLayout(null);
        
        lbLogOut = new JLabel("");
        lbLogOut.setBounds(0, 0, 40, 40);
        logOutPanel.add(lbLogOut);
        
        imgLogout = design.resizeIcon("./icon/icons8_logout_rounded_left_64.png", logOutPanel.getWidth(),
                logOutPanel.getHeight());
        lbLogOut.setIcon(imgLogout);
        lbLogOut.addMouseListener(this);
        
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        pnPhieunhap = new JPanel();
        pnPhieunhap.setBounds(0, 185, 200, 40);
        leftPanel.add(pnPhieunhap);
        pnPhieunhap.setBackground(new Color(122, 172, 240));
        pnPhieunhap.setVisible(false);
        
        lbpn = new JLabel("Phi?u nh?p");
        lbpn.setForeground(Color.WHITE);
        lbpn.setBounds(0, 0, 200, 40);
        lbpn.addMouseListener(this);
        ImageIcon iconPN = design.resizeIcon("./icon/icons8_Bill_64.png", pnPhieu.getWidth() / 3,
                (int) (pnPhieu.getHeight() * 1.5));
        pnPhieunhap.setLayout(null);
        lbpn.setIcon(iconPN);
        pnPhieunhap.add(lbpn);
        lbpn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Hello");
                centerPanel.removeAll();
                try {
                    pn = new QLPNPanel();
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                centerPanel.add(pn, BorderLayout.CENTER);
                centerPanel.repaint();
                centerPanel.revalidate();
            }
        });
        
        pnPhieumuon = new JPanel();
        pnPhieumuon.setBounds(0, 225, 200, 40);
        leftPanel.add(pnPhieumuon);
        pnPhieumuon.setBackground(new Color(122, 172, 240));
        pnPhieumuon.setVisible(false);
        
        lbpm = new JLabel("Phi?u mu?n");
        lbpm.setForeground(Color.WHITE);
        lbpm.setBounds(0, 0, 200, 40);
        lbpm.addMouseListener(this);
        ImageIcon iconPM = design.resizeIcon("./icon/icons8_Bill_64.png", pnPhieu.getWidth() / 3,
                (int) (pnPhieu.getHeight() * 1.5));
        pnPhieumuon.setLayout(null);
        lbpm.setIcon(iconPM);
        pnPhieumuon.add(lbpm);
        lbpm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("PHIEU MUON");
                centerPanel.removeAll();
                try {
                    pm = new QLPMPanel();
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                centerPanel.add(pm, BorderLayout.CENTER);
                centerPanel.repaint();
                centerPanel.revalidate();
            }
        });
        //310

        //
        pnPhieuphat = new JPanel();
        pnPhieuphat.setBounds(0, 265, 200, 40);
        pnPhieuphat.setBackground(new Color(122, 172, 240));
        leftPanel.add(pnPhieuphat);
        pnPhieuphat.setVisible(false);
        
        lbpp = new JLabel("Phi?u Ph?t");
        // lbpm = new JLabel("Phi?u mu?n");
        lbpp.setForeground(Color.WHITE);
        lbpp.setBounds(0, 0, 200, 40);
        lbpp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("PHIEU PHAT");
                centerPanel.removeAll();
                try {
                    pp = new QLPPPanel();
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                centerPanel.add(pp, BorderLayout.CENTER);
                centerPanel.repaint();
                centerPanel.revalidate();       
            }
        });
        ImageIcon iconPP = design.resizeIcon("./icon/icons8_Bill_64.png", pnPhieu.getWidth() / 3,
                (int) (pnPhieu.getHeight() * 1.5));
        System.out.println(iconPP.getIconWidth()+" "+iconPP.getIconHeight());
        pnPhieuphat.setLayout(null);
        lbpp.setIcon(iconPM);
        pnPhieuphat.add(lbpp);
        
       
     
        
        
        pnThongke = new JPanel();
        pnThongke.setBounds(0, 190, 200, 40);
          pnThongke.setBackground(new Color(45, 118, 232));
       // leftPanel.add(pnPhieuphat);
        //pnPhieuphat.setVisible(false);
        pnThongke.setLayout(null);
        leftPanel.add(pnThongke);
        
        lbthongke = new JLabel("        Thống kê");
        lbthongke.setBounds(0, 0, 200, 40);
        lbthongke.setForeground(Color.WHITE);
        lbthongke.addMouseListener(this);
        ImageIcon iconTKe = design.resizeIcon("./icon/icons8_calculator_32.png", pnThongke.getWidth()/3,
                (int) (pnThongke.getHeight()*1.4));
        System.out.println(iconTKe.getIconHeight()+" " +iconTKe.getIconWidth());
        lbthongke.setIcon(iconTKe);
        pnThongke.add(lbthongke);
        
        sach = new QLSPanel();
        centerPanel.add(sach, BorderLayout.CENTER);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == pnSach || e.getSource() == lbSach) {
            centerPanel.removeAll();
          
            try {
                sach = new QLSPanel();
            } catch (Exception e1) {
                System.out.println(e1);
            }
            centerPanel.add(sach, BorderLayout.CENTER);
            centerPanel.repaint();
            centerPanel.revalidate();
        } else if (e.getSource() == namePanel || e.getSource() == lbName) {
            lbName.setForeground(Color.black);
            suaTT sua;
            try {
                sua = new suaTT();
                sua.setVisible(true);
                sua.loadData();
            } catch (UnsupportedLookAndFeelException e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == lbLogOut || e.getSource() == logOutPanel) {
            try {
                logInForm login = new logInForm();
                login.setVisible(true);
                dispose();
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == pnPhieu || e.getSource() == lbPhieu) {
            i++;
            System.out.println(i);
            if (i % 2 == 0) {
                pnPhieunhap.setVisible(false);
                pnPhieumuon.setVisible(false);
                pnPhieuphat.setVisible(false);
                pnThongke.setBounds(0, 190, 200, 40);
            } else {
                pnPhieunhap.setVisible(true);
                pnPhieumuon.setVisible(true);
                pnPhieuphat.setVisible(true);
                pnThongke.setBounds(0, 310, 200, 40);
            }
        } else if(e.getSource()==lbthongke){
            centerPanel.removeAll();
            try {
                tk = new thongkePanel();
            } catch (Exception ex) {
                System.out.println("lỗi userframe-dòng 318");
            }
            centerPanel.add(tk,BorderLayout.CENTER);
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
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
