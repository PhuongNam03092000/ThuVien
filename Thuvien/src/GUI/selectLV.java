package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.linhvucBUS;
import DAO.linhvucDAO;
import DTO.linhvucDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class selectLV extends selectID {

    private JPanel contentPane;
    private DefaultTableModel model;
    private JTable tbTG;

    public selectLV() throws Exception {
        super();
        Vector header = new Vector();
        header.add("Mã Lĩnh Vực");
        header.add("Tên Lĩnh Vực");

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
        linhvucBUS bus = new linhvucBUS();
        bus.getLinhvucList();
        for (linhvucDTO linhvuc : linhvucBUS.dslv) {
            Vector a = new Vector();
            a.add(linhvuc.getMalinhvuc());
            a.add(linhvuc.getTenlinhvuc());
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
