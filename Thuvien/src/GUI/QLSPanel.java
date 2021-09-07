package GUI;

import BUS.ctpnBUS;
import BUS.linhvucBUS;
import BUS.loaiBUS;
import BUS.nxbBUS;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import BUS.sachBUS;
import BUS.tacgiaBUS;
import DTO.ctpnDTO;
import DTO.sachDTO;
import TOOL.PDFBOX;
import TOOL.check;
import com.itextpdf.text.DocumentException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.RowFilter;

public class QLSPanel extends JPanel implements MouseListener, ActionListener, KeyListener {

    public JTextField txMasach;
    public JTextField txTensach;
    public JTextField txGia;
    public JTextField txTheloai;
    public JTextField txTacgia;
    public JTextField txMaNXB;
    public JTextField txSoluong;
    public DefaultTableModel model;
    public JButton btnThem, btnXoa, btnSua, btnTailai, btnNXB, btnIn, btnAnh;
    public JTextField txLinhvuc;
    public JButton btnTL, btnTG;
    public JButton btnLV;
    public tableSach table;
    public ImagePanel img;
    public JFileChooser fileChooser;
    public JLabel hinhanh;
    public String duongdan = "./imgsach/null";
    public JTextField txTen;
    public JTextField txTL;
    public JLabel lblTG;
    public JTextField txTG;
    public JLabel lbNXBID;
    public JTextField txNXBID;
    public JLabel lbLV;
    public JTextField txLV;
    private QLPNPanel panel;

    public QLSPanel() {
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel lbMasach = new JLabel("Mã sách:");
        lbMasach.setBounds(50, 30, 120, 25);
        add(lbMasach);

        txMasach = new JTextField();
        txMasach.setBounds(175, 28, 203, 25);
        txMasach.setEnabled(false);
        add(txMasach);
        txMasach.setColumns(10);

        JLabel lbTensach = new JLabel("Tên sách :");
        lbTensach.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbTensach.setBounds(50, 70, 120, 25);
        add(lbTensach);

        txTensach = new JTextField();
        txTensach.setColumns(10);
        txTensach.setBounds(175, 70, 203, 25);
        add(txTensach);

        JLabel lbGia = new JLabel("Giá sách :");
        lbGia.setBounds(50, 110, 94, 25);
        add(lbGia);

        txGia = new JTextField();
        txGia.setColumns(10);
        txGia.setBounds(175, 110, 203, 25);
        add(txGia);

        JLabel lbTheloai = new JLabel("Mã thể loại : ");
        lbTheloai.setBounds(420, 30, 120, 25);
        add(lbTheloai);

        txTheloai = new JTextField();
        txTheloai.setBounds(537, 28, 98, 25);
        add(txTheloai);
        txTheloai.setColumns(10);

        JLabel lbTacgia = new JLabel("Mã tác giả :");
        lbTacgia.setBounds(420, 70, 94, 25);
        add(lbTacgia);

        txTacgia = new JTextField();
        txTacgia.setBounds(537, 68, 98, 26);
        add(txTacgia);
        txTacgia.setColumns(10);

        JLabel txNXB = new JLabel("Mã NXB : ");
        txNXB.setBounds(420, 110, 94, 25);
        add(txNXB);

        txMaNXB = new JTextField();
        txMaNXB.setBounds(537, 110, 98, 25);
        add(txMaNXB);
        txMaNXB.setColumns(10);

        JLabel lbSoluong = new JLabel("Số lượng:");
        if(QLPNPanel.clickedToCreate==true){
            lbSoluong.setText("Số lượng thêm :");
          
        }
        lbSoluong.setBounds(50, 151,95, 25);
        add(lbSoluong);

        txSoluong = new JTextField();
        txSoluong.setBounds(175, 149, 203, 26);
        add(txSoluong);
        txSoluong.setColumns(10);

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
        btnTailai.setBounds(544, 205, 150, 25);
        btnTailai.addActionListener(this);
        add(btnTailai);

        btnSua = new JButton("Sửa");
        btnSua.setIcon(new ImageIcon("./icon/icons8_change_32.png"));
        btnSua.setBounds(379, 205, 150, 25);
        btnSua.addActionListener(this);
        add(btnSua);

        JButton btnNewButton = new JButton("Xuất Excel");
        btnNewButton.setIcon(new ImageIcon("./icon/icons8_Microsoft_Excel_2019_32.png"));
        btnNewButton.setBounds(715, 204, 150, 25);
        add(btnNewButton);

        btnIn = new JButton("In");
        btnIn.setIcon(new ImageIcon("./icon/icons8_print_32.png"));
        btnIn.setBounds(880, 204, 150, 25);
        btnIn.addActionListener(this);
        add(btnIn);

        table = new tableSach();
        table.loadData();
        table.setBounds(10, 295, 970, 270);
        add(table);

        JLabel lbMalinhvuc = new JLabel("Mã lĩnh vực :");
        lbMalinhvuc.setBounds(420, 152, 94, 20);
        add(lbMalinhvuc);

        txLinhvuc = new JTextField();
        txLinhvuc.setBounds(537, 149, 98, 25);
        add(txLinhvuc);
        txLinhvuc.setColumns(10);

        btnTL = new JButton("...");
        btnTL.addActionListener(this);
        btnTL.setBounds(636, 28, 25, 25);
        add(btnTL);

        btnTG = new JButton("...");
        btnTG.addActionListener(this);
        btnTG.setBounds(636, 68, 25, 25);
        add(btnTG);

        btnNXB = new JButton("...");
        btnNXB.addActionListener(this);
        btnNXB.setBounds(636, 110, 25, 25);
        add(btnNXB);

        btnLV = new JButton("...");
        btnLV.addActionListener(this);
        btnLV.setBounds(636, 149, 25, 25);
        add(btnLV);

        hinhanh = new JLabel();
        hinhanh.setBounds(730, 30, 170, 158);
        img = new ImagePanel();
        add(hinhanh);

        btnAnh = new JButton("Chọn ảnh");
        btnAnh.setIcon(new ImageIcon("./icon/icons8_print_32.png"));
        btnAnh.setBounds(900, 150, 130, 25);
        btnAnh.addActionListener(this);
        add(btnAnh);

        FileNameExtensionFilter jpg = new FileNameExtensionFilter("JPG File", "jpg");
        FileNameExtensionFilter jpeg = new FileNameExtensionFilter("JPEG File", "jpeg");
        FileNameExtensionFilter png = new FileNameExtensionFilter("PNG File", "PNG");

        txTen = new JTextField();
        txTen.setBounds(92, 253, 134, 25);
        add(txTen);
        txTen.setColumns(10);

        JLabel lbTen = new JLabel("Tên sách :");
        lbTen.setBounds(24, 257, 67, 16);
        add(lbTen);

        JLabel lbTL = new JLabel("Thể loại :");
        lbTL.setBounds(235, 257, 56, 16);
        add(lbTL);

        txTL = new JTextField();
        txTL.setBounds(296, 253, 90, 25);
        add(txTL);
        txTL.setColumns(10);

        lblTG = new JLabel("Tác giả :");
        lblTG.setBounds(395, 257, 56, 16);
        add(lblTG);

        txTG = new JTextField();
        txTG.setBounds(450, 253, 90, 25);
        add(txTG);
        txTG.setColumns(10);

        lbNXBID = new JLabel("Nhà xuất bản :");
        lbNXBID.setBounds(547, 257, 88, 16);
        add(lbNXBID);

        txNXBID = new JTextField();
        txNXBID.setBounds(638, 253, 90, 25);
        add(txNXBID);
        txNXBID.setColumns(10);

        lbLV = new JLabel("Lĩnh vực :");
        lbLV.setBounds(737, 257, 56, 16);
        add(lbLV);

        txLV = new JTextField();
        txLV.setBounds(797, 254, 90, 25);
        add(txLV);
        txLV.setColumns(10);

        txTen.addKeyListener(this);
        txTL.addKeyListener(this);
        txTG.addKeyListener(this);
        txNXBID.addKeyListener(this);
        txLV.addKeyListener(this);

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(jpg);
        fileChooser.setFileFilter(jpeg);
        fileChooser.setFileFilter(png);

        table.getTable().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int i = table.getSelectedRow();
                btnThem.setEnabled(false);
                if (i >= 0) {
                    sachDTO nv = sachBUS.dss.get(i);
                    txMasach.setText(nv.getMasach());
                    txTensach.setText(nv.getTensach());
                    txGia.setText(String.valueOf(nv.getGiasach()));
                    txTheloai.setText(nv.getMatheloai());
                    txTacgia.setText(nv.getMatg());
                    txMaNXB.setText(nv.getManxb());
                    txLinhvuc.setText(nv.getMalinhvuc());
                    if(QLPNPanel.clickedToCreate==false)
                        txSoluong.setText(String.valueOf(nv.getSoluong()));
                    else
                        txSoluong.setText("");
                    if (nv.getHinhanh().equals("./imgsach/null")) {
                        hinhanh.setIcon(null);
                    } else {
                        img.setImage(nv.getHinhanh(), hinhanh);
                        duongdan = nv.getHinhanh();
                    }
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == btnTL) {
            try {
                selectLoai loai = new selectLoai();
                loai.loaddata();
                loai.setVisible(true);
                loai.select(txTheloai);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == btnNXB) {
            try {
                selectNXB nxb = new selectNXB();
                nxb.loaddata();
                nxb.setVisible(true);
                nxb.select(txMaNXB);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == btnLV) {
            try {
                selectLV linhvuc = new selectLV();
                linhvuc.loaddata();
                linhvuc.setVisible(true);
                linhvuc.select(txLinhvuc);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == btnTG) {
            try {
                selectTG tacgia = new selectTG();
                tacgia.loaddata();
                tacgia.setVisible(true);
                tacgia.select(txTacgia);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == btnThem) {
            try {
                Insert(0, "Insert");
            } catch (Exception ex) {
                Logger.getLogger(QLSPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnXoa) {
            Delete();
        } else if (e.getSource() == btnSua) {
            try {
                Update();
            } catch (Exception ex) {
                Logger.getLogger(QLSPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnTailai) {
            tailai();
        } else if (e.getSource() == btnAnh) {
            try {
                duongdan = Image();
            } catch (IOException ex) {
                Logger.getLogger(QLSPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(e.getSource()==btnIn){
            try {
                PDFBOX in=new PDFBOX();
                sachBUS bus=new sachBUS();
                bus.getSachList();
                in.sach("Danh sách Sách",sachBUS.dss,"sachDTO");
            } catch(FileNotFoundException k){System.out.println(k);}
              catch(DocumentException e2){System.out.println(e2);}
            catch(IOException e3){System.out.println(e3);}
        }
    }

    public void Insert(int vitritable, String option) throws Exception {
        try {
            sachDTO sach = new sachDTO();
            sachBUS bus = new sachBUS();
            if (!check.checkNull(txTensach.getText()) == true) {
                sach.setTensach(txTensach.getText());
                if (check.isNumeric(txGia.getText()) == true && !check.checkNull(txGia.getText()) == true) {
                    txGia.setText(TOOL.check.returnNumber(txGia.getText()));
                    sach.setGiasach(Integer.parseInt(txGia.getText()));
                    if (check.isNumeric(txSoluong.getText()) == true && !check.checkNull(txSoluong.getText()) == true) {
                        txSoluong.setText(TOOL.check.returnNumber(txSoluong.getText()));
                        if(kiemtraid()==true){
                        sach.setSoluong(Integer.parseInt(txSoluong.getText()));
                            sach.setMatheloai(txTheloai.getText());
                                sach.setMatg(txTacgia.getText());
                                    sach.setManxb(txMaNXB.getText());
                                        sach.setMalinhvuc(txLinhvuc.getText());
                                        sach.setTrangthai(1);
                                        switch (option) {
                                            case "Insert": {
                                                try {
                                                    bus.getSachListdaydu();
                                                    if (bus.sameiddaydu(txMasach.getText()) == true) {
                                                       JOptionPane.showMessageDialog(null, "Bạn phải tải lại trước khi thêm");
                                                       return;
                                                   }

                                                    int k = bus.checkBookInFullList(sach.getTensach(), sach.getManxb(), sach.getMalinhvuc(), sach.getMatheloai(), sach.getMatg());
                                                    switch (k) {
                                                        case 1:
                                                        case 2: {
                                                            bus.updateSoluong(sach.getTensach(), sach.getManxb(), sach.getMalinhvuc(), sach.getMatheloai(), sach.getMatg(), Integer.parseInt(txSoluong.getText()), 1);
                                                            sach.setMasach(bus.getSach(sach.getTensach(), sach.getManxb(), sach.getMalinhvuc(), sach.getMatheloai(), sach.getMatg()).getMasach());
                                                            table.loadData();
                                                        }
                                                        break;
                                                        case 3: {
                                                            txMasach.setText(bus.sinhma());
                                                            sach.setMasach(txMasach.getText());
                                                            sach.setHinhanh(duongdan);
                                                            bus.Insert(sach);
                                                            table.addData(sach);
                                                        }
                                                        break;
                                                    }
                                                    if (QLPNPanel.clickedToCreate) {
                                                        String mapn = QLPNPanel.mapn;
                                                        String masach = sach.getMasach();
                                                        int soluong = sach.getSoluong();
                                                        long giatien = sach.getGiasach();
                                                        long thanhtien = soluong * giatien;
                                                        ctpnDTO ctpn;
                                                        ctpnBUS busCTPN = new ctpnBUS();
                                                        if (busCTPN.sameID(mapn, masach) == true) {
                                                            ctpn = new ctpnDTO(mapn, masach, soluong, giatien, thanhtien);
                                                            busCTPN.countPrice(mapn, thanhtien);
                                                            busCTPN.Insert(ctpn);
                                                            panel.tableCTP.addData(ctpn);
                                                            panel.table.loadData();
                                                            QLPNPanel.clickedToCreate = false;
                                                        } else {
                                                            ctpn = busCTPN.getCTPN(mapn, masach);
                                                            int soluongcu = ctpn.getSoluong();
                                                            ctpn.setSoluong(soluongcu + soluong);
                                                            busCTPN.Update(ctpn);
                                                            panel.tableCTP.loadData();
                                                            panel.table.loadData();
                                                            QLPNPanel.clickedToCreate = false;
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Logger.getLogger(QLSPanel.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                //break;
                                            }
                                            break;
                                            case "Update": {
                                                sach.setHinhanh(duongdan);
                                                sach.setMasach(txMasach.getText());
                                                if (QLPNPanel.clickedToCreate) {
                                                    String mapn = QLPNPanel.mapn;
                                                    String masach = sach.getMasach();
                                                    System.out.println(sach.toString());
                                                    int soluongcu =bus.getSoluong(masach);
                                                    System.out.println(soluongcu);
                                                    int soluongnhap = Integer.parseInt(txSoluong.getText());
                                                   // System.out.println(soluongmoi);
           //                                             System.out.println("Có vào đây không");
                                                        int soluongmoi = soluongnhap + soluongcu;
                                                        long giatien = sach.getGiasach();
                                                        long thanhtien = soluongnhap * giatien;
                                                        ctpnDTO ctpn;
                                                        ctpnBUS busCTPN = new ctpnBUS();
                                                         if (busCTPN.sameID(mapn, masach) == true) {
                                                            ctpn = new ctpnDTO(mapn, masach, soluongnhap, giatien, thanhtien);
                                                            busCTPN.countPrice(mapn, thanhtien);
                                                            busCTPN.Insert(ctpn);
                                                            panel.tableCTP.addData(ctpn);
                                                            panel.table.loadData();
                                                            QLPNPanel.clickedToCreate = false;
                                                        } else {
                                                            ctpn = busCTPN.getCTPN(mapn, masach);
                                                            ctpn.setSoluong(soluongnhap);
                                                            busCTPN.countPrice(mapn, thanhtien);
//                                                            busCTPN.Insert(ctpn);
                                                            panel.tableCTP.loadData();
                                                            panel.table.loadData();
                                                            QLPNPanel.clickedToCreate = false;
                                                        }
                                                        sach.setSoluong(soluongmoi);
                                                        bus.Update(sach);
                                                        table.Update(vitritable, sach);
                                                        table.loadData();
                                                } else{
                                                    bus.Update(sach);
                                                    table.Update(vitritable, sach);
                                                    table.loadData();
                                                }
                                                //                         break;
                                            }
                                            break;
                                        }}
                    } else {
                        JOptionPane.showMessageDialog(null, "Số lượng phải là số và không được để trống");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Gía sách phải là số và không để trống");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tên sách không được để trống");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public void Delete() {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn sách cần xóa");
            return;
        }
        String masach = txMasach.getText();
        int i = table.getSelectedRow();
        sachDTO temp = table.getModel().getSach(i);
        if (!masach.equals(temp.getMasach())) {
            JOptionPane.showMessageDialog(null, "Bạn không thể thay đổi mã sách");
            return;
        }
        sachBUS bus = new sachBUS();
        bus.Delete(temp);
        table.removeData(i);   //?? xóa dss thì sachList cũng bị thay đổi theo
    }

    public void Update() throws Exception {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn sách cần sửa");
            return;
        }

        String masach = txMasach.getText();
        int i = table.getSelectedRow();
        sachDTO temp = table.getModel().getSach(i);
        if (!masach.equals(temp.getMasach())) {
            JOptionPane.showMessageDialog(null, "Bạn không thể thay đổi mã sách");
            return;
        }
        Insert(i, "Update");
    }

    public String Image() throws IOException {
        int result = fileChooser.showOpenDialog(QLSPanel.this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File input = fileChooser.getSelectedFile();
            File output = new File("./imgsach/" + input.getName());
            copyFileUsingStream(input, output); // TODO Auto-generated catch block
            img.setImage(output.getAbsolutePath(), hinhanh);
            return "./imgsach/" + input.getName();
        }
        return "./imgsach/null";
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {

        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest);) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    public void tailai() {
        btnThem.setEnabled(true);
        txMasach.setText(null);
        txTensach.setText(null);
        txGia.setText(null);
        txSoluong.setText(null);
        txTacgia.setText(null);
        txTheloai.setText(null);
        txMaNXB.setText(null);
        txLinhvuc.setText(null);
        hinhanh.setIcon(null);
        table.refresh();
        duongdan = "./imgsach/null";
    }

    public void setCTPN(QLPNPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        RowFilter<Object, Object> bookName = new RowFilter<Object, Object>() {
            @Override
            public boolean include(Entry<? extends Object, ? extends Object> entry) {
                String find = txTen.getText().toLowerCase();
                String name = entry.getStringValue(1).toLowerCase();

                //System.out.println(name);
                //System.out.println(find);
                if (name.contains(find)) {
                    return true;
                }
                return false;
            }

        };

        ArrayList<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
        filters.add(bookName);
        filters.add(RowFilter.regexFilter("(?i)" + txTL.getText().toLowerCase(), 3));
        filters.add(RowFilter.regexFilter("(?i)" + txTG.getText().toLowerCase(), 4));
        filters.add(RowFilter.regexFilter("(?i)" + txNXBID.getText().toLowerCase(), 5));
        filters.add(RowFilter.regexFilter("(?i)" + txLV.getText().toLowerCase(), 6));
        RowFilter rf = RowFilter.andFilter(filters);

        if (txTen.getText().isEmpty() && txTL.getText().isEmpty() && txTG.getText().isEmpty()
                && txNXBID.getText().isEmpty() && txLV.getText().isEmpty()) {
            table.getTr().setRowFilter(null);
        } else {
            table.getTr().setRowFilter(rf);
        }
    }
    
      public boolean kiemtraid()  
    {
        tacgiaBUS tacgia=new tacgiaBUS();
        tacgia.getTacgiaList();
        linhvucBUS linhvuc=new linhvucBUS();
        linhvuc.getLinhvucList();
        loaiBUS theloai=new loaiBUS();
        theloai.getLoaiList();
        nxbBUS nxb=new nxbBUS();
        nxb.getNXBList();
        if(txTacgia.getSelectionStart()==0||txLinhvuc.getSelectionStart()==0||txTheloai.getSelectionStart()==0||txMaNXB.getSelectionStart()==0)
        {
            JOptionPane.showMessageDialog(null,"Vui lĂ²ng nháº­p Ä‘áº§y Ä‘á»§ thĂ´ng tin(TG-LV-NXB-TL)");
            return false;
        }
        if(!tacgia.sameid(txTacgia.getText()))
        {
            int kq=JOptionPane.showConfirmDialog(null,"TĂ¡c giáº£ nĂ y chÆ°a tá»“n táº¡i! Báº¡n muá»‘n táº¡o má»›i khĂ´ng?","Warning", JOptionPane.YES_NO_OPTION);
            if(kq==JOptionPane.YES_OPTION)
            {
                SupportPanel tacgia2=new SupportPanel();
                tacgia2.run("tacgia","TĂ¡c giáº£",txTacgia);
            }
             return false;
        }
        if(!linhvuc.sameid(txLinhvuc.getText()))
        {
            int kq=JOptionPane.showConfirmDialog(null,"LÄ©nh vá»±c nĂ y chÆ°a tá»“n táº¡i! Báº¡n muá»‘n táº¡o má»›i khĂ´ng?","Warning", JOptionPane.YES_NO_OPTION);
             if(kq==JOptionPane.YES_OPTION)
            {
                SupportPanel linhvuc2=new SupportPanel();
                linhvuc2.run("linhvuc","LÄ©nh Vá»±c",txLinhvuc);
            }
            return false;
        }
        if(!theloai.sameid(txTheloai.getText()))
        {
            int kq=JOptionPane.showConfirmDialog(null,"Thá»ƒ loáº¡i nĂ y chÆ°a tá»“n táº¡i! Báº¡n muá»‘n táº¡o má»›i khĂ´ng?","Warning", JOptionPane.YES_NO_OPTION);
             if(kq==JOptionPane.YES_OPTION)
            {
                SupportPanel theloai2=new SupportPanel();
                theloai2.run("theloai","Thá»ƒ loáº¡i",txTheloai);
            }
              return false;
        }
        if(!nxb.sameid(txMaNXB.getText()))
        {
            int kq=JOptionPane.showConfirmDialog(null,"NXB nĂ y chÆ°a tá»“n táº¡i! Báº¡n muá»‘n táº¡o má»›i khĂ´ng?","Warning", JOptionPane.YES_NO_OPTION);
             if(kq==JOptionPane.YES_OPTION)
            {
                SupportPanel nxb2=new SupportPanel();
                nxb2.run("nxb","NhĂ  xuáº¥t báº£n",txMaNXB);
            }
               return false;
        }
        return true;
    }
   
}


