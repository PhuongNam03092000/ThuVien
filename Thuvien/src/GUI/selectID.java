package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class selectID extends JFrame {

    private JPanel mainPanel;
    private JTextField textField;
    public JButton btnChon, btnThoat;

    public selectID() throws UnsupportedLookAndFeelException {
        setBounds(100, 100, 450, 350);
        setLocationRelativeTo(null);
        mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        textField = new JTextField();
        textField.setBounds(15, 50, 201, 26);
        mainPanel.add(textField);
        textField.setColumns(10);

        btnChon = new JButton("Chọn");
        btnChon.setBounds(114, 249, 77, 29);

        mainPanel.add(btnChon);

        btnThoat = new JButton("Thoát");
        btnThoat.setBounds(219, 249, 84, 29);
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(btnThoat);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 450, 40);
        header.setBackground(new Color(45, 118, 232));
        mainPanel.add(header);
    }

    public void select(JTextField a) {
        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    int output;
                    if (kiemtra(a) == 0) {
                        output = JOptionPane.showConfirmDialog(null, "Bạn chưa chọn Tác giả-Muốn thoát hay tiếp tục chọn", "Cảnh báo", JOptionPane.YES_NO_OPTION);
                        if (output != JOptionPane.YES_OPTION) {
                            dispose();
                        }
                    } else {
                        dispose();
                    }
                }
            }
        });
    }

    public int kiemtra(JTextField a) {
        return 0;
    }

}
