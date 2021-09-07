package GUI;

import BUS.ctpnBUS;
import BUS.phieunhapBUS;
import BUS.sachBUS;
import DTO.ctpnDTO;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class themCTFrame extends JFrame {

    public QLSPanel SPanel;
    public QLPNPanel panel;

    public themCTFrame() {
        initFrame();
    }

    public void initFrame() {
        this.setSize(1050, 650);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        initComponent();
        this.setVisible(true);

    }

    public void initComponent() {
        SPanel = new QLSPanel();
        add(SPanel, BorderLayout.CENTER);
    }
}
