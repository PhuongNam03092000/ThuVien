/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class NewClass extends JFrame {
    public NewClass() throws Exception{
        setSize(1100,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        QLPNPanel nhap = new QLPNPanel();
        add(nhap,BorderLayout.CENTER);
        setVisible(true);
    }
    public static void main(String[] args) throws Exception{
        NewClass a = new NewClass();
    }
    
}
