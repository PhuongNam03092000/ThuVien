package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import BUS.nguoidungBUS;
import DTO.nguoidungDTO;
import TOOL.check;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class QLTKPanel extends JPanel implements ActionListener {
    /**
     * Create the panel.
     */
    private JButton btnThem, btnXoa, btnSua, btnTailai,btnChan,button;
    private tableNguoidung table;
    private JTextField txManv;
    private JPasswordField passwordField;
    private JComboBox comboBox;

    public QLTKPanel() {
        setBackground(Color.WHITE);
        setForeground(Color.WHITE);
        setLayout(null);

        btnThem = new JButton("Thêm");
        btnThem.setIcon(new ImageIcon("./icon/icons8_add_32.png"));
        btnThem.setBounds(30, 206, 150, 25);
        btnThem.addActionListener(this);
        add(btnThem);

        btnXoa = new JButton("Xóa");
        btnXoa.setIcon(new ImageIcon("./icon/icons8_delete_sign_32.png"));
        btnXoa.setBounds(210, 205, 150, 25);
        btnXoa.addActionListener(this);
        add(btnXoa);

        btnTailai = new JButton("Tải lại");
        btnTailai.setIcon(new ImageIcon("./icon/icons8_synchronize_32.png"));
        btnTailai.setBounds(210, 257, 150, 25);
        btnTailai.addActionListener(this);
        add(btnTailai);

        btnSua = new JButton("Sửa");
        btnSua.setIcon(new ImageIcon("./icon/icons8_change_32.png"));
        btnSua.setBounds(30, 257, 150, 25);
        btnSua.addActionListener(this);
        add(btnSua);
        
        btnChan  = new JButton("Chặn");
        btnChan.setBounds(30, 300, 150, 25);
        btnChan.addActionListener(this);
        add(btnChan);

        table = new tableNguoidung();
        nguoidungBUS bus = new nguoidungBUS();
        table.setData(bus.getNDList());
        table.loadData();
        table.setBounds(392, 16, 596, 573);
        add(table);
        
        table.getTable().addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                int i = table.getTable().getSelectedRow();
                if(i>=0){
                    nguoidungDTO nd = nguoidungBUS.dsnd.get(i);
                    txManv.setText(nd.getManv());
                    passwordField.setText(nd.getMkhau());
                    if(nd.getQuyen().equals("MQ001")){
                        comboBox.setSelectedIndex(1);
                    }else if(nd.getQuyen().equals("MQ002")){
                        comboBox.setSelectedIndex(2);
                    }
                }
            }
        });

        txManv = new JTextField();
        txManv.setBounds(190, 55, 120, 25);
        add(txManv);
        txManv.setColumns(10);

        button = new JButton("...");
        button.setBounds(310, 55, 25, 25);
        add(button);
        button.addActionListener(this);

        JLabel lbManv = new JLabel("Mã  nhân viên :");
        lbManv.setBounds(51, 62, 110, 20);
        add(lbManv);

        JLabel kbMatkhau = new JLabel("Mật khẩu :");
        kbMatkhau.setBounds(51, 98, 110, 20);
        add(kbMatkhau);

        passwordField = new JPasswordField();
        passwordField.setBounds(192, 95, 146, 26);
        add(passwordField);

        JLabel lbMaquye = new JLabel("Mã quyền :");
        lbMaquye.setBounds(51, 134, 110, 20);
        add(lbMaquye);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"", "MQ001", "MQ002"}));
        comboBox.setBounds(192, 137, 146, 26);
        add(comboBox);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            try {
                Insert();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }else if(e.getSource()==btnXoa){
            try {
                Delete();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }else if(e.getSource()==btnSua){
            try {
                Update();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if(e.getSource()==btnChan){
            try {
                Block();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if(e.getSource()==button){
            try {
                selectNV nv = new selectNV();
                nv.setVisible(true);
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println(ex);
            }
            
        }
    }

    public void Insert() throws Exception {
        nguoidungDTO nd = new nguoidungDTO();
        if (!check.checkNull(txManv.getText())) {
            nd.setManv(txManv.getText());
            nd.setMkhau("123456");
            if (comboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Hãy chọn phân quyền cho tài khoản này");
            } else {
                if (comboBox.getSelectedIndex() == 1) {
                    nd.setQuyen("MQ001");
                } else {
                    nd.setQuyen("MQ002");
                }
                nguoidungBUS busNguoidung = new nguoidungBUS();
                busNguoidung.Insert(nd);
                table.addData(nd);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập không được bỏ trống");
        }
    }

    public void Delete() throws Exception {
        int i = table.getTable().getSelectedRow();
        if (i >= 0) {
            nguoidungDTO nd = nguoidungBUS.dsnd.get(i);
            nguoidungBUS bus = new nguoidungBUS();
            bus.Delete(nd);
            table.deleteData(nd, i);
            table.loadData();
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn tại khoản cần xóa");
        }
    }

    public void Update() throws Exception {
        int i = table.getTable().getSelectedRow();
        if (i >= 0) {
            nguoidungDTO nd = nguoidungBUS.dsnd.get(i);
            nguoidungBUS bus = new nguoidungBUS();
            if (!check.checkNull(txManv.getText())) {
                nd.setManv(txManv.getText());
                nd.setMkhau(passwordField.getPassword().toString());
                if (comboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn phân quyền cho tài khoản này");
                } else {
                    if (comboBox.getSelectedIndex() == 1) {
                        nd.setQuyen("MQ001");
                    } else {
                        nd.setQuyen("MQ002");
                    }
                    nguoidungBUS busNguoidung = new nguoidungBUS();
                    busNguoidung.Update(nd);
                    table.updateData(nd, i);
                    table.loadData();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tên đăng nhập không được bỏ trống");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn tại khoản cần sửa");
        }
    }
    
    public void Block() throws Exception{
        nguoidungBUS bus = new nguoidungBUS();
        int i = table.getTable().getSelectedRow();
        if(i>=0){
            nguoidungDTO nd = nguoidungBUS.dsnd.get(i);
            bus.Block(nd);
        }else{
            JOptionPane.showMessageDialog(null, "Hãy chọn tài khoản bạn muốn chặn");
        }
    }
}
