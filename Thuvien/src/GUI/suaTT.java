package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import BUS.nhanvienBUS;
import DTO.nhanvienDTO;
import TOOL.ThongTinEvent;
import TOOL.ThongTinListener;
import TOOL.check;
import java.awt.Font;

public class suaTT extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JLabel lbTen;
    private JTextField txTen;
    private JTextField txHo;
    private JLabel lbManv;
    private JTextField txManv;
    private JLabel lbNgaysinh;
    private JTextField txEmail;
    private JTextField txDiachi;
    private JDateChooser dateChooser;
    private JComboBox comboBox;
    private JButton btnSuatt;
    private JLabel lbSDT;
    private JTextField txSDT;
    private JButton btnSuamk, btnThoat;
    private ThongTinListener tl;

    public suaTT() throws UnsupportedLookAndFeelException {
        setTitle("Sửa thông tin tài khoản");
        setBounds(100, 100, 500, 350);
        setLocationRelativeTo(null);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
        lbTen = new JLabel("Tên");
        lbTen.setHorizontalAlignment(SwingConstants.LEFT);
        lbTen.setBounds(289, 86, 45, 25);
        mainPanel.add(lbTen);

        JLabel lbHo = new JLabel("Họ");
        lbHo.setHorizontalAlignment(SwingConstants.LEFT);
        lbHo.setBounds(12, 86, 31, 25);
        mainPanel.add(lbHo);

        txTen = new JTextField();
        txTen.setBounds(352, 85, 114, 25);
        mainPanel.add(txTen);
        txTen.setColumns(10);

        txHo = new JTextField();
        txHo.setBounds(108, 85, 146, 26);
        mainPanel.add(txHo);
        txHo.setColumns(10);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 490, 40);
        header.setBackground(new Color(45, 118, 232));
        mainPanel.add(header);

        JLabel lblNewLabel = new JLabel("Sửa thông tin");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(124, 0, 245, 40);
        header.add(lblNewLabel);

        lbManv = new JLabel("Mã nhân viên");
        lbManv.setHorizontalAlignment(SwingConstants.LEFT);
        lbManv.setBounds(12, 52, 94, 25);
        mainPanel.add(lbManv);

        txManv = new JTextField();
        txManv.setBounds(110, 52, 146, 26);
        mainPanel.add(txManv);
        txManv.setColumns(10);

        lbNgaysinh = new JLabel("Ngày sinh");
        lbNgaysinh.setHorizontalAlignment(SwingConstants.LEFT);
        lbNgaysinh.setBounds(10, 125, 94, 20);
        mainPanel.add(lbNgaysinh);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(108, 123, 146, 26);
        mainPanel.add(dateChooser);

        JLabel lbGioitinh = new JLabel("Giới tính");
        lbGioitinh.setBounds(289, 123, 69, 25);
        mainPanel.add(lbGioitinh);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Nam", "Nữ"}));
        comboBox.setBounds(352, 122, 114, 26);
        mainPanel.add(comboBox);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setBounds(12, 157, 45, 25);
        mainPanel.add(lbEmail);

        txEmail = new JTextField();
        txEmail.setBounds(108, 157, 146, 26);
        mainPanel.add(txEmail);
        txEmail.setColumns(10);

        JLabel lbDiachi = new JLabel("Địa chỉ");
        lbDiachi.setBounds(289, 160, 58, 25);
        mainPanel.add(lbDiachi);

        txDiachi = new JTextField();
        txDiachi.setBounds(352, 156, 114, 26);
        mainPanel.add(txDiachi);
        txDiachi.setColumns(10);

        btnSuatt = new JButton("Sửa thông tin");
        btnSuatt.setBounds(46, 238, 135, 29);
        btnSuatt.addActionListener(this);
        mainPanel.add(btnSuatt);

        btnSuamk = new JButton("Sửa mật khẩu");
        btnSuamk.setBounds(184, 238, 131, 29);
        btnSuamk.addActionListener(this);
        mainPanel.add(btnSuamk);

        btnThoat = new JButton("Thoát");
        btnThoat.setBounds(318, 238, 115, 29);
        btnThoat.addActionListener(this);
        mainPanel.add(btnThoat);

        lbSDT = new JLabel("Số điện thoại");
        lbSDT.setBounds(12, 192, 107, 20);
        mainPanel.add(lbSDT);

        txSDT = new JTextField();
        txSDT.setBounds(108, 189, 148, 26);
        mainPanel.add(txSDT);
        txSDT.setColumns(10);
    }

    public void setThongTinLisnter(ThongTinListener fl) {
        this.tl = fl;
    }

    public void loadData() {
        for (nhanvienDTO nv : nhanvienBUS.dsnv) {
            if (nv.getManv().compareTo(nhanvienBUS.userID) == 0) {
                txManv.setText(nhanvienBUS.userID);
                txManv.setEditable(false);
                txHo.setText(nv.getHo());
                txTen.setText(nv.getTen());
                try {
                    Date ngaysinh = new SimpleDateFormat("yyyy-MM-dd").parse(nv.getNgaysinh());
                    dateChooser.setDate(ngaysinh);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                if (nv.getGioitinh().compareTo("Nam") == 0) {
                    comboBox.setSelectedIndex(0);
                } else {
                    comboBox.setSelectedIndex(1);
                }
                txSDT.setText(nv.getSdt());
                txEmail.setText(nv.getEmail());
                txDiachi.setText(nv.getDiachi());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nhanvienBUS bus = new nhanvienBUS();
        if (e.getSource() == btnSuatt) {
            int i = 0;
            for (nhanvienDTO nv : nhanvienBUS.dsnv) {
                if (nhanvienBUS.userID.compareTo(nv.getManv()) == 0) {
                    if (!check.checkNull(txHo.getText())) {
                        // nếu đúng thực hiện gán giá tri
                        nv.setHo(txHo.getText());
                        // kiểm tra tên nhân viên
                        if (!check.checkNull(txTen.getText())) {
                            // gán giá trị cho tên nhân viên
                            nv.setTen(txTen.getText());
                            // định dạng cấu trúc hiển thị co ngày
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String date = sdf.format(dateChooser.getDate());
                            JOptionPane.showMessageDialog(null, date);
                            // gán giá trị ngày sinh cho nhân viên
                            nv.setNgaysinh(date);
                            // gán giá trị giới tính cho nhân viên
                            if (comboBox.getSelectedIndex() == 0) {
                                nv.setGioitinh("Nam");
                            } else {
                                nv.setGioitinh("Nữ");
                            }
                            // gán giá trị địa chỉ cho nhân viên
                            nv.setDiachi(txDiachi.getText());
                            // thực hiện kiểm tra tính hợp lệ của email
                            if (check.checkEmail(txEmail.getText())) {
                                nv.setEmail(txEmail.getText());
                                if (check.checkPhone(txSDT.getText())) {
                                    nv.setSdt(txSDT.getText());
                                    ThongTinEvent evt = new ThongTinEvent(nv.getManv(), nv.getHo(),
                                            nv.getTen(), nv.getNgaysinh(), nv.getGioitinh(),
                                            nv.getDiachi(), nv.getEmail(), nv.getSdt(), nv.getLuong());
                                    if (tl != null) {
                                        tl.thongTin(evt);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Số điệnt hoại này không hợp lệ");
                                }
                            } else {
                                // báo lỗi nếu email không đúng định dạng
                                JOptionPane.showMessageDialog(null, "Email này không hợp lệ");
                            }
                        } else {
                            // báo lỗi tên nhân viên
                            JOptionPane.showMessageDialog(null, "Tên nhân viên không được bỏ trống");
                        }
                    } else {
                        // báo lỗi học nhân viên
                        JOptionPane.showMessageDialog(null, "Họ nhân viên không được bỏ trống");
                        txManv.requestFocus();
                    }
                }
            }
            i++;
        } else if (e.getSource() == btnSuamk) {
            suaMK mk = new suaMK();
            mk.setVisible(true);
            dispose();
        } else if (e.getSource() == btnThoat) {
            dispose();
        }

    }
}
