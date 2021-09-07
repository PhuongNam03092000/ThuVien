package GUI;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import BUS.loaiBUS;
import DTO.loaiDTO;
import java.util.Vector;
import javax.swing.JTextField;

public class selectLoai extends selectID {

    private JPanel contentPane;
    private DefaultTableModel model;
    private JTable tbTG;

    public selectLoai() throws Exception {
        super();

        Vector header = new Vector();
        header.add("Mã Thể Loại");
        header.add("Tên Thể Loại");

        model = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbTG = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tbTG);
        scrollPane.setBounds(12, 98, 404, 126);
        getContentPane().add(scrollPane);

    }

    public void loaddata() {
        loaiBUS bus = new loaiBUS();
        bus.getLoaiList();
        for (loaiDTO loai : loaiBUS.dstl) {
            Vector a = new Vector();
            a.add(loai.getMaloai());
            a.add(loai.getTenloai());
            model.addRow(a);
        }
        tbTG.setModel(model);
    }

    @Override
    public int kiemtra(JTextField a) {
        super.select(a);
        int i = tbTG.getSelectedRow();
        if (i < 0) {
            a.setText(null);
            return 0;
        } else {
            String temp = (String) tbTG.getValueAt(i, 0);
            a.setText(temp);
            return 1;
        }
    }

}
