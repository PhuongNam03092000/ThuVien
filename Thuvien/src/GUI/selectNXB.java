package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import BUS.nxbBUS;
import DTO.nxbDTO;
import java.util.Vector;
import javax.swing.JTextField;

public class selectNXB extends selectID {

    private JPanel contentPane;
    private DefaultTableModel model;
    private JTable tbTG;

    public selectNXB() throws Exception {
        super();

        Vector header = new Vector();
        header.add("Mã NXB");
        header.add("Tên NXB");
        header.add("SDT NXB");
        header.add("Email NXB");

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
        nxbBUS bus = new nxbBUS();
        bus.getNXBList();
        for (nxbDTO nxb : nxbBUS.dsnxb) {
            Vector a = new Vector();
            a.add(nxb.getManxb());
            a.add(nxb.getTenxb());
            a.add(nxb.getSdt());
            a.add(nxb.getEmail());
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
