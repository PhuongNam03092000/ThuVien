package GUI;

import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import BUS.tacgiaBUS;
import DTO.tacgiaDTO;
import java.util.Vector;
import javax.swing.JTextField;

public class selectTG extends selectID {

    private JPanel contentPane;
    private DefaultTableModel model;
    private JTable tbTG;

    public selectTG() throws Exception {
        super();

        Vector header = new Vector();
        header.add("Mã TG");
        header.add("Họ TG");
        header.add("Tên TG");

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
        tacgiaBUS bus = new tacgiaBUS();
        bus.getTacgiaList();
        for (tacgiaDTO tacgia : tacgiaBUS.dstg) {
            Vector a = new Vector();
            a.add(tacgia.getMatg());
            a.add(tacgia.getHotg());
            a.add(tacgia.getTentg());
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
