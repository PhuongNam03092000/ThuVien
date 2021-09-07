package GUI;

import BUS.nhanvienBUS;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import BUS.nxbBUS;
import DTO.nhanvienDTO;
import DTO.nxbDTO;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

public class selectNV extends selectID {

    private JPanel contentPane;
    private DefaultTableModel model;
    private JTable tbNV;

    public selectNV() throws UnsupportedLookAndFeelException {
        super();

        Vector header = new Vector();
        header.add("Mã NV");
        header.add("Họ NV");
        header.add("Tên NV");

        model = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbNV = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tbNV);
        scrollPane.setBounds(12, 98, 404, 126);
        getContentPane().add(scrollPane);

    }

    public void loaddata() {
        nhanvienBUS bus = new nhanvienBUS();
        try {
            bus.getNVList();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        for (nhanvienDTO nv : nhanvienBUS.dsnv) {
            Vector a = new Vector();
            a.add(nv.getManv());
            a.add(nv.getHo());
            a.add(nv.getTen());
            model.addRow(a);
        }
        tbNV.setModel(model);
    }

    @Override
    public int kiemtra(JTextField a) {
        super.select(a);
        int i = tbNV.getSelectedRow();
        if (i < 0) {
            a.setText(null);
            return 0;
        } else {
            String temp = (String) tbNV.getValueAt(i, 0);
            a.setText(temp);
            return 1;
        }
    }

}
