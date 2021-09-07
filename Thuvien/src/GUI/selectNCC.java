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
import BUS.nccBUS;
import DAO.linhvucDAO;
import DTO.linhvucDTO;
import DTO.nccDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class selectNCC extends selectID {

    private JPanel contentPane;
    private DefaultTableModel model;
    private JTable tbTG;

    public selectNCC() throws Exception {
        super();
        Vector header = new Vector();
        header.add("Mã Nhà cung cấp");
        header.add("Tên Nhà cung cấp");

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
        nccBUS bus = new nccBUS();
        bus.getNCCList();
        for (nccDTO ncc : nccBUS.dsncc) {
            Vector a = new Vector();
            a.add(ncc.getMancc());
            a.add(ncc.getTenncc());
            a.add(ncc.getDiachi());
            a.add(ncc.getSdt());
            model.addRow(a);
        }
        tbTG.setModel(model);
    }

    @Override
    public int kiemtra(JTextField a) {
        super.select(a);
        int i = tbTG.getSelectedRow();
        if (i < 0) {
            a.setText("");
            return 0;
        } else {
            String temp = (String) tbTG.getValueAt(i, 0);
            a.setText(temp);
            return 1;
        }
    }

}
