package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BUS.nguoidungBUS;
import BUS.nhanvienBUS;
import DTO.nguoidungDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class suaMK extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JPasswordField tplastPass;
    private JPasswordField tpNewPass;
    private JPasswordField tpCfNewPass;
    private JButton btnDoi;
    private JButton btnThoat;

    public suaMK() {
        setBounds(100, 100, 500, 350);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        setTitle("Sửa mật khẩu");

        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 500, 40);
        header.setBackground(new Color(45, 118, 232));
        mainPanel.add(header);
        header.setLayout(null);

        JLabel lblNewLabel = new JLabel("Đổi mật khẩu");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(124, 0, 245, 40);
        header.add(lblNewLabel);

        JLabel lbLastPass = new JLabel("Mật khẩu hiện tại");
        lbLastPass.setBounds(30, 72, 126, 25);
        mainPanel.add(lbLastPass);

        tplastPass = new JPasswordField();
        tplastPass.setBounds(220, 71, 178, 26);
        mainPanel.add(tplastPass);

        JLabel lbNewPass = new JLabel("Mật khẩu mới ");
        lbNewPass.setBounds(30, 113, 126, 20);
        mainPanel.add(lbNewPass);

        tpNewPass = new JPasswordField();
        tpNewPass.setBounds(220, 113, 178, 26);
        mainPanel.add(tpNewPass);

        JLabel lbCfNewPass = new JLabel("Nhập lại mật khẩu mới");
        lbCfNewPass.setBounds(30, 153, 161, 20);
        mainPanel.add(lbCfNewPass);

        tpCfNewPass = new JPasswordField();
        tpCfNewPass.setBounds(220, 155, 178, 26);
        mainPanel.add(tpCfNewPass);

        btnDoi = new JButton("Đổi mật khẩu");
        btnDoi.setBounds(94, 234, 139, 29);
        btnDoi.addActionListener(this);
        mainPanel.add(btnDoi);

        btnThoat = new JButton("Thoát");
        btnThoat.setBounds(248, 234, 132, 29);
        btnThoat.addActionListener(this);
        mainPanel.add(btnThoat);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == btnDoi) {
            nguoidungBUS bus = new nguoidungBUS();
            String lastPass = new String(tplastPass.getPassword());
            String newPass = new String(tpNewPass.getPassword());
            String cfNewPass = new String(tpCfNewPass.getPassword());
            nguoidungDTO nd = bus.getNDbyID(nhanvienBUS.userID);
            if (bus.checkPass(nhanvienBUS.userID, lastPass)) {
                if (cfNewPass.equals(newPass)) {
                    if (bus.checkNewPass(newPass)) {
                        nd.setManv(nhanvienBUS.userID);
                        nd.setMkhau(newPass);
                        nd.setQuyen(nd.getQuyen());
                        nd.setTrangthai(nd.getTrangthai());
                        try {
                            bus.Update(nd);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    } else {
                        // vào đây là đúng và được thay đổi mật khẩu rồi nè
                        JOptionPane.showMessageDialog(null,
                                "Mật khẩu phải có ít nhất 6 ký tự , có chữ viết hoa, viết thường và chữ số");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mật khẩu và xác nhận mật khẩu không khớp");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác");
            }
        }else if(e.getSource()==btnThoat){
            dispose();
        }
    }
}
