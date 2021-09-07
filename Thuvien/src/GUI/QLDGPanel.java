package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;
import BUS.docgiaBUS;
import DTO.docgiaDTO;
import TOOL.check;

public class QLDGPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    private JTextField txMadg;
    private JTextField txHodg;
    private JTextField txTendg;
    private JTextField txNgaysinh;
    private JTextField txDiachi;
    private JTextField txNghenghiep;
    private JTextField txTrinhdo;
    private JButton btnThem, btnXoa, btnSua, btnTaiLai;
    private JDateChooser dateChooser;
    private JTextField txTimkiem;
    private JComboBox<String> comboBox;
    private suaTT suaTT;
    private TableDocGia table;

    public QLDGPanel() throws Exception {
        //set background cho nó 
        setBackground(Color.WHITE);
        //set lay out 
        setLayout(null);
        //gui lop bus lên 
        docgiaBUS bus = new docgiaBUS();

        JLabel lbMadg = new JLabel("Mã Độc Giả :");
        lbMadg.setHorizontalAlignment(SwingConstants.LEFT);
        lbMadg.setBounds(50, 30, 120, 35);
        add(lbMadg);

        txMadg = new JTextField();
        txMadg.setEditable(false);
        txMadg.setBounds(175, 30, 372, 25);
        add(txMadg);
        txMadg.setColumns(10);

        JLabel lbHo = new JLabel("Họ :");
        lbHo.setBounds(50, 70, 114, 25);
        add(lbHo);

        txHodg = new JTextField();
        txHodg.setBounds(175, 70, 185, 25);
        add(txHodg);
        txHodg.setColumns(10);

        JLabel lbTen = new JLabel("Tên :");
        lbTen.setHorizontalAlignment(SwingConstants.CENTER);
        lbTen.setBounds(368, 69, 50, 25);
        add(lbTen);

        txTendg = new JTextField();
        txTendg.setBounds(420, 71, 127, 26);
        add(txTendg);
        txTendg.setColumns(10);

        JLabel lbNgaysinh = new JLabel("Ngày sinh ");
        lbNgaysinh.setBounds(50, 110, 100, 25);
        add(lbNgaysinh);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(175, 110, 372, 25);
        add(dateChooser);

        JLabel lbdiachi = new JLabel("Địa chỉ :");
        lbdiachi.setBounds(50, 150, 100, 25);
        add(lbdiachi);

        txDiachi = new JTextField();
        txDiachi.setBounds(175, 150, 375, 25);
        add(txDiachi);
        txDiachi.setColumns(10);

        JLabel lbnghenghiep = new JLabel("Nghề nghiệp");
        lbnghenghiep.setBounds(624, 30, 100, 25);
        add(lbnghenghiep);

        txNghenghiep = new JTextField();
        txNghenghiep.setBounds(746, 28, 284, 25);
        add(txNghenghiep);
        txNghenghiep.setColumns(10);

        JLabel lbTrinhdo = new JLabel("Trình độ :");
        lbTrinhdo.setBounds(624, 70, 70, 25);
        add(lbTrinhdo);

        txTrinhdo = new JTextField();
        txTrinhdo.setBounds(746, 68, 284, 25);
        add(txTrinhdo);
        txTrinhdo.setColumns(10);

        btnThem = new JButton("Thêm");
        btnThem.setIcon(new ImageIcon("./icon/icons8_add_32.png"));
        btnThem.setBounds(180, 206, 150, 25);
        btnThem.addActionListener(this);
        add(btnThem);

        btnXoa = new JButton("Xóa");
        btnXoa.setIcon(new ImageIcon("./icon/icons8_delete_sign_32.png"));
        btnXoa.setBounds(360, 205, 150, 25);
        btnXoa.addActionListener(this);
        add(btnXoa);

        btnTaiLai = new JButton("Tải lại");
        btnTaiLai.setIcon(new ImageIcon("./icon/icons8_delete_sign_32.png"));
        btnTaiLai.setBounds(694, 205, 150, 25);
        btnTaiLai.addActionListener(this);
        add(btnTaiLai);

        btnSua = new JButton("Sửa");
        btnSua.setIcon(new ImageIcon("./icon/icons8_change_32.png"));
        btnSua.setBounds(529, 205, 150, 25);
        btnSua.addActionListener(this);
        add(btnSua);

        table = new TableDocGia();
        table.setData(bus.getDGList());
        Dimension dm = new Dimension();
        dm = Toolkit.getDefaultToolkit().getScreenSize();
        table.setBounds(5, 295, 1050, 300);
        add(table);
        table.loadData();

        txTimkiem = new JTextField();
        txTimkiem.setBounds(190, 253, 212, 25);
        txTimkiem.addKeyListener(this);
        add(txTimkiem);
        txTimkiem.setColumns(10);

        comboBox = new JComboBox();
        comboBox.setModel(
                new DefaultComboBoxModel(new String[]{"--Vui lòng chọn--", "Mã độc giả", "Tên độc giả"}));
        comboBox.setBounds(10, 253, 154, 26);
        add(comboBox);

        table.getTable().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int i = table.getSelectedRow();;
                btnThem.setEnabled(false);
                if (i >= 0) {
                    docgiaDTO dg = new docgiaDTO();
                    dg = table.getModel().getDG(i);
                    txMadg.setEnabled(true);
                    txMadg.setText(dg.getMadg());

                    txHodg.setText(dg.getHodg());
                    txTendg.setText(dg.getTendg());
                    try {
                        Date ngaysinh = new SimpleDateFormat("yyyy-MM-dd").parse(dg.getNgaysinh());
                        dateChooser.setDate(ngaysinh);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    txDiachi.setText(dg.getDiachi());
                    txNghenghiep.setText(dg.getNghenghiep());
                    txTrinhdo.setText(dg.getTrinhdo());

                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnThem) {
            docgiaBUS bus = new docgiaBUS();
            docgiaDTO dg = new docgiaDTO();
            dg.setMadg(bus.autoCreateID());
            if (!check.checkNull(txHodg.getText())) {
                dg.setHodg((txHodg.getText()));
                if (!check.checkNull(txTendg.getText())) {
                    dg.setTendg(txTendg.getText());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(dateChooser.getDate());
                    JOptionPane.showMessageDialog(null, date);
                    dg.setNgaysinh(date);
                    if (!check.checkNull(txDiachi.getText())) {
                        dg.setDiachi(txDiachi.getText());

                        if (!check.checkNull(txNghenghiep.getText())) {
                            dg.setNghenghiep(txNghenghiep.getText());

                            if (!check.checkNull(txTrinhdo.getText())) {
                                dg.setTrinhdo(txTrinhdo.getText());
                                JOptionPane.showMessageDialog(null, "Độc giả vừa thêm có mã là " + dg.getMadg());
                                table.addData(dg);
                                docgiaBUS.dsdg.add(dg);
                                try {
                                    bus.Insert(dg);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                txMadg.setText(null);
                                txHodg.setText(null);
                                txTendg.setText(null);
                                dateChooser.setDate(null);
                                txDiachi.setText(null);
                                txNghenghiep.setText(null);
                                txTrinhdo.setText(null);
                            } else {
                                JOptionPane.showMessageDialog(null, "Trình độ không được bỏ trống");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Nghề nghiệp không được bỏ trống");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Địa chỉ không được bỏ trống");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Tên không được để trống");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Họ không được để trống");

            }
        } else if (evt.getSource() == btnXoa) {
            // lấy giá trị hàng đang được ch�?n
            docgiaBUS bus = new docgiaBUS();
            int i = table.getSelectedRow();
            if (i >= 0) {
                docgiaDTO dg  = docgiaBUS.dsdg.get(i);
                if (JOptionPane.showConfirmDialog(null, "Bạn muốn xóa độc giả ?", "Confirmation",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    try {
                        bus.Delete(dg);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e);
                    }
                    table.deleteData(dg, i);
                    docgiaBUS.dsdg.remove(dg);
                    txMadg.setText(null);
                    txHodg.setText(null);
                    txTendg.setText(null);
                    dateChooser.setDate(null);
                  
                    txDiachi.setText(null);
                    txNghenghiep.setText(null);
                    txTrinhdo.setText(null);
                  
                }
            } else {
                // báo lỗi nếu chưa ch�?n nhân viên
                JOptionPane.showMessageDialog(null, "Bạn chưa ch�?n nhân viên");
            }
        } 
            else if (evt.getSource() == btnSua) {
            int i = table.getSelectedRow();
            docgiaBUS bus = new docgiaBUS();
            if (i >= 0) {
                docgiaDTO dg =docgiaBUS.dsdg.get(i);
                if (!check.checkNull(txHodg.getText())) {
                    // nếu đúng thực hiện gán giá tri
                    dg.setHodg(txHodg.getText());
                    // kiểm tra tên nhân viên
                    if (!check.checkNull(txTendg.getText())) {
                        // gán giá trị cho tên nhân viên
                        dg.setTendg(txTendg.getText());
                        // định dạng cấu trúc hiển thị co ngày
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String date = sdf.format(dateChooser.getDate());
                        JOptionPane.showMessageDialog(null, date);
                        // gán giá trị ngày sinh cho nhân viên
                        dg.setNgaysinh(date);
                     
                    
                        // gán giá trị địa chỉ cho nhân viên
                        dg.setDiachi(txDiachi.getText());
                        // thực hiện kiểm tra tính hợp lệ của email
                        if (!check.checkNull(txNghenghiep.getText())) {
                            dg.setNghenghiep(txNghenghiep.getText());
                            if (!check.checkNull(txTrinhdo.getText())) {
                                dg.setTrinhdo(txTrinhdo.getText());
                                 try {
                                        bus.Update(dg);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    table.updateData(dg, i);
                                    docgiaBUS.dsdg.set(i, dg);
                            } else {
                                JOptionPane.showMessageDialog(null, "Trình độ không để trống");
                            }
                        } else {
                            // báo lỗi nếu email không đúng định dạng
                            JOptionPane.showMessageDialog(null, "Nghề nghiệp không để trống");
                        }
                    } else {
                        // báo lỗi tên nhân viên
                        JOptionPane.showMessageDialog(null, "Tên nhân viên không được bỏ trống");
                    }
                } else {
                    // báo lỗi học nhân viên
                    JOptionPane.showMessageDialog(null, "Họ nhân viên không được bỏ trống");
                    txMadg.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn nhân viên muốn sửa");
            }
        
        } else if (evt.getSource() == btnTaiLai) {
                btnThem.setEnabled(true);
                txHodg.setText(null);
                txTendg.setText(null);
                dateChooser.setDate(null);
                txDiachi.setText(null);
                txNghenghiep.setText(null);
                txTrinhdo.setText(null);
            }

        }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }


