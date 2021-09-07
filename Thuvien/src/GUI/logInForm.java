package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.nguoidungBUS;
import BUS.nhanvienBUS;
import DTO.nguoidungDTO;
import TOOL.check;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class logInForm extends JFrame implements ActionListener, MouseListener, KeyListener {

    private JPanel mainPanel;
    private JTextField txUserName;
    private JPasswordField tpPassword;
    private JPanel pnLogin;
    private JLabel lbLogin;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private nguoidungBUS bus;
    private JLabel lbTitle;
    private JLabel lbClose;
    private JPanel closePanel;
    private JLabel lbForgetPass;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    logInForm frame = new logInForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     *
     * @throws Exception
     */
    public logInForm() throws Exception {
        bus = new nguoidungBUS();
        bus.getNDList();
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        setLocationRelativeTo(null);
        // setUndecorated(true);
        setTitle("Quản lí thư viện");
        ImageIcon icon = new ImageIcon("./icon/book.png");
        Image img = icon.getImage();
        this.setIconImage(img);
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(128, 203, 196));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        JLabel lbIcon = new JLabel("");
        lbIcon.setHorizontalAlignment(SwingConstants.CENTER);
        lbIcon.setIcon(new ImageIcon("./icon/book1.png"));
        lbIcon.setBackground(Color.WHITE);
        lbIcon.setBounds(238, 16, 200, 94);
        mainPanel.add(lbIcon);

        txUserName = new JTextField();
        txUserName.setBounds(238, 135, 200, 30);
        mainPanel.add(txUserName);
        txUserName.setColumns(10);
        txUserName.addKeyListener(this);

        tpPassword = new JPasswordField();
        tpPassword.setBounds(238, 191, 200, 30);
        mainPanel.add(tpPassword);
        tpPassword.addKeyListener(this);

        pnLogin = new JPanel();
        pnLogin.setBackground(new Color(25, 166, 210));
        pnLogin.setBounds(238, 237, 200, 30);
        mainPanel.add(pnLogin);
        pnLogin.setLayout(null);

        lbLogin = new JLabel("Đăng nhập");
        lbLogin.setBounds(0, 0, 200, 30);
        pnLogin.add(lbLogin);
        lbLogin.setFont(new Font("Calibri", Font.BOLD, 20));
        lbLogin.setHorizontalAlignment(SwingConstants.CENTER);
        pnLogin.addMouseListener(this);

        lbUsername = new JLabel("Tài khoản");
        lbUsername.setFont(new Font("Calibri", Font.BOLD, 18));
        lbUsername.setBounds(121, 131, 100, 40);
        mainPanel.add(lbUsername);

        lbPassword = new JLabel("Mật khẩu");
        lbPassword.setFont(new Font("Calibri", Font.BOLD, 18));
        lbPassword.setBounds(121, 187, 100, 40);
        mainPanel.add(lbPassword);

        lbForgetPass = new JLabel("Quên mật khẩu ?");
        lbForgetPass.setForeground(new Color(0, 0, 139));
        lbForgetPass.setFont(new Font("Calibri", Font.PLAIN, 16));
        lbForgetPass.setHorizontalAlignment(SwingConstants.LEFT);
        lbForgetPass.setBounds(252, 283, 137, 20);
        lbForgetPass.addMouseListener(this);
        mainPanel.add(lbForgetPass);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == pnLogin || e.getSource() == lbLogin) {
            if (check.checkNull(txUserName.getText())) {
                txUserName.setBorder(BorderFactory.createLineBorder(Color.red));
                // JOptionPane.showMessageDialog(null, "Hãy nhập vào tài khoản");
            } else if (check.checkNull(new String(tpPassword.getPassword()))) {
                tpPassword.setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Hãy nhập vào mật khẩu");
            } else {
                nguoidungDTO nguoidung = new nguoidungDTO();
                nguoidung.setManv(txUserName.getText());
                nguoidung.setMkhau(new String(tpPassword.getPassword()));
                try {
					if (bus.checkAccount(nguoidung.getManv(), nguoidung.getMkhau(), "MQ001",1) == true) {
					    nhanvienBUS.userID = txUserName.getText();
					    JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
					    try {
					        UserFrame frame = new AdminFrame();
					        frame.setVisible(true);
					        this.dispose();
					    } catch (Exception e1) {
					        // TODO Auto-generated catch block
					        e1.printStackTrace();
					    }
					} else if (bus.checkAccount(nguoidung.getManv(), nguoidung.getMkhau(), "MQ002",1) == true) {
					    nhanvienBUS.userID = txUserName.getText();
					    JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
					    try {
					        UserFrame frame = new UserFrame();
					        frame.setVisible(true);
					        setVisible(false);
					        this.dispose();
					    } catch (Exception e1) {
					        // TODO Auto-generated catch block
					        e1.printStackTrace();
					    }
					} else {
					    JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        } else if (e.getSource() == lbClose) {
            if (JOptionPane.showConfirmDialog(null, "Bạn muốn đóng ứng dụng ?", "Confirmation",
                    JOptionPane.YES_NO_OPTION) == 0) {
                try {
                    //bus.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                logInForm.this.dispose();

            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == pnLogin || e.getSource() == lbLogin) {
            pnLogin.setBackground(new Color(31, 117, 254));
        } else if (e.getSource() == lbClose) {
            closePanel.setBackground(new Color(253, 64, 83));
        } else if (e.getSource() == lbForgetPass) {
            lbForgetPass.setForeground(new Color(143, 80, 157));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == pnLogin || e.getSource() == lbLogin) {
            pnLogin.setBackground(new Color(25, 166, 210));
        } else if (e.getSource() == lbClose) {
            closePanel.setBackground(new Color(31, 117, 254));
        } else if (e.getSource() == lbForgetPass) {
            lbForgetPass.setForeground(new Color(0, 0, 139));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == txUserName) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                tpPassword.requestFocus(true);
            }
        } else if (e.getSource() == tpPassword) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                pnLogin.setBackground(new Color(31, 117, 254));
                lbLogin.requestFocus(true);

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }
}
