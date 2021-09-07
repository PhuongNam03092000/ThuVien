package GUI;

import BUS.linhvucBUS;
import BUS.loaiBUS;
import BUS.nxbBUS;
import BUS.tacgiaBUS;
import DTO.linhvucDTO;
import DTO.loaiDTO;
import DTO.nxbDTO;
import DTO.tacgiaDTO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class SupportPanel extends JPanel{

    private JTextField[] text;
    private JLabel[] label;
    private JButton them,sua,xoa,tailai;
    private JTable table;
    private DefaultTableModel model;
    private int click;
    public SupportPanel a;
    private JFrame temp;
    
    
    public void SupportPanel(){
       a=new SupportPanel();
    }
    
    public void phanloai(String loai,JTextField input)
    {
        switch(loai)
        {
            case "tacgia":{
                text=new JTextField[4];
                label=new JLabel[4];
                JLabel header=new JLabel("TÁC GIẢ");
                header.setFont(new Font("Calibri", Font.BOLD, 20));
                header.setBounds(200, 0, 100, 30);
                add(header);
                String[] textoflabel={"Mã T/gỉa","Họ T/gỉa","Tên T/gỉa","Email T/giả"};
                create(textoflabel,input.getText());
                button(loai,textoflabel,input);
                table(loai,textoflabel);
                break;
            }
            case "theloai":{
                text=new JTextField[2];
                label=new JLabel[2];
                JLabel header=new JLabel("THỂ LOẠI");
                header.setFont(new Font("Calibri", Font.BOLD, 20));
                header.setBounds(200, 0, 100, 30);
                add(header);
                String[] textoflabel={"Mã T/loại","Tên T/loại"};
                create(textoflabel,input.getText());
                button(loai,textoflabel,input);
                table(loai,textoflabel);
                break;
            }
            case "linhvuc":{
                text=new JTextField[2];
                label=new JLabel[2];
                JLabel header=new JLabel("LĨNH VỰC");
                header.setFont(new Font("Calibri", Font.BOLD, 20));
                header.setBounds(200, 0, 100, 30);
                add(header);
                String[] textoflabel={"Mã L/vực","Tên L/vực"};
                create(textoflabel,input.getText());
                button(loai,textoflabel,input);
                table(loai,textoflabel);
                break;
            }
            case "nxb":{
                text=new JTextField[4];
                label=new JLabel[4];
                JLabel header=new JLabel("NHÀ XUẤT BẢN");
                header.setFont(new Font("Calibri", Font.BOLD, 20));
                header.setBounds(200, 0, 150, 30);
                add(header);
                String[] textoflabel={"Mã NXB","Tên NXB","SDT","EMAIL NXB"};
                create(textoflabel,input.getText());
                button(loai,textoflabel,input);
                table(loai,textoflabel);
                break;
            }
            default:{
                JOptionPane.showConfirmDialog(null,"Khong ton tai");
                break;
            }  
        }
        
    }
    public void create(String[] textoflabel,String input)
    {
        setBackground(Color.WHITE);
        setLayout(null);
        //create label
        int x=0;
        int maxx=500;
        int maxy=300;
        int y=0;
        int left=textoflabel.length/2;
        if(textoflabel.length%2!=0)
            left=textoflabel.length/2+1;
        int temp=50;
        //left
        for(int i=0;i<left;i++)
        {
            label[i]=new JLabel();
            label[i].setText(textoflabel[i]);
            label[i].setFont(new Font("Calibri", Font.PLAIN, 18));
            label[i].setBounds(x+10, y+temp,100,30);
            temp+=50;
            add(label[i]);
        }
        //rigth
        temp=50;
        for(int i=left;i<textoflabel.length;i++)
        {
            label[i]=new JLabel();
            label[i].setText(textoflabel[i]);
            label[i].setFont(new Font("Calibri", Font.PLAIN, 18));
            label[i].setBounds(x+230+10, y+temp,110,30);
            temp+=50;
            add(label[i]);;
        }
        //left-text
        temp=50;
        for(int i=0;i<left;i++)
        {
            text[i]=new JTextField();
            text[i].setBounds(x+10+80, y+temp,110,30);
            temp+=50;
            text[0].setText(input);
            text[0].setEditable(false);
            add(text[i]);
        }
        //rigth-text
        temp=50;
        for(int i=left;i<textoflabel.length;i++)
        {
            text[i]=new JTextField();
            text[i].setBounds(x+10+80+240, y+temp,120,30);
            temp+=50;
            add(text[i]);
        }
    }
    public int kiemtra(String[] textoflabel)
    {
        for(int i=0;i<textoflabel.length;i++)
        {
            if(text[i].getText().length()==0)
            {
                JOptionPane.showMessageDialog(null,"Không được để trống bất kì ô nào!!");
                return 1;
            }
        }
        return 0;
    }
    
    public void table(String loai,String[] textoflabel)
    {
        model=new DefaultTableModel(textoflabel,0){
            @Override
            public boolean isCellEditable(int row,int col){
                return false;
            }
        };
        if(loai.equals("tacgia"))
        {
            tacgiaBUS bus=new tacgiaBUS();
            bus.getTacgiaList();
            for(int i=0;i<tacgiaBUS.dstg.size();i++)
            {
                tacgiaDTO temp=(tacgiaDTO)tacgiaBUS.dstg.get(i);
                Vector row=new Vector();
                row.add(temp.getMatg());
                row.add(temp.getHotg());
                row.add(temp.getTentg());
                row.add(temp.getEmail());
                model.addRow(row);
            }
        }
        if(loai.equals("linhvuc"))
        {
            linhvucBUS bus=new linhvucBUS();
            bus.getLinhvucList();
            for(int i=0;i<linhvucBUS.dslv.size();i++)
            {
                linhvucDTO temp=(linhvucDTO)linhvucBUS.dslv.get(i);
                Vector row=new Vector();
                row.add(temp.getMalinhvuc());
                row.add(temp.getTenlinhvuc());
                model.addRow(row);
            }
        }
        if(loai.equals("theloai"))
        {
            loaiBUS bus=new loaiBUS();
            bus.getLoaiList();
            for(int i=0;i<loaiBUS.dstl.size();i++)
            {
                loaiDTO temp=(loaiDTO)loaiBUS.dstl.get(i);
                Vector row=new Vector();
                row.add(temp.getMaloai());
                row.add(temp.getTenloai());
                model.addRow(row);
            }
        }
        if(loai.equals("nxb"))
        {
            nxbBUS bus=new nxbBUS();
            bus.getNXBList();
            for(int i=0;i<nxbBUS.dsnxb.size();i++)
            {
                nxbDTO temp=(nxbDTO)nxbBUS.dsnxb.get(i);
                Vector row=new Vector();
                row.add(temp.getManxb());
                row.add(temp.getTenxb());
                row.add(temp.getSdt());
                row.add(temp.getEmail());
                model.addRow(row);
            }
        }
        table=new JTable(model);
        JScrollPane scroll=new JScrollPane(table);
        scroll.setBounds(5,200,480,265);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        add(scroll);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                click=mouseclick(textoflabel);
            }
        });
    }
    
    public void button(String loai,String[] textoflabel,JTextField input)
    {
        them=new JButton();
        them.setText("Thêm");
        them.setBounds(50, 150, 80, 30);
        add(them);
        them.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kiemtra(textoflabel)==1)
                    return;
                switch(loai)
                {
                    case "tacgia":{
                        tacgiaBUS tacgia=new tacgiaBUS();
                        tacgia.getTacgiaList();
                        if(tacgia.sameid(text[0].getText())==true)
                        {
                            JOptionPane.showMessageDialog(null,"Mã tác giả đã tồn tại");
                            return;
                        }
                        if(!TOOL.check.checkEmail(text[3].getText()))
                        {
                            JOptionPane.showMessageDialog(null,"Email phai co dinh dang (xxx@xxx)");
                            return;
                        }
                        Vector row=new Vector();
                        adddata(row,text);
                        model.addRow(row);
                        tacgiaDTO newtg=new tacgiaDTO(text[0].getText(),text[1].getText(),text[2].getText(),text[3].getText(),1);
                        try {
                            tacgia.Insert(newtg);
                        } catch (Exception ex) {
                        }
                    }
                        break;
                    case "linhvuc":{
                        linhvucBUS linhvuc=new linhvucBUS();
                        linhvuc.getLinhvucList();
                        if(linhvuc.sameid(text[0].getText())==true)
                        {
                            JOptionPane.showMessageDialog(null,"Mã lĩnh vực đã tồn tại");
                            return;
                        }
                        Vector row=new Vector();
                        adddata(row,text);
                        model.addRow(row);
                        linhvucDTO newlv=new linhvucDTO(text[0].getText(),text[1].getText(),1);
                        try {
                            linhvuc.Insert(newlv);
                        } catch (Exception ex) {
                        }
                    }
                        break;    
                    case "theloai":{
                        loaiBUS theloai=new loaiBUS();
                        theloai.getLoaiList();
                        if(theloai.sameid(text[0].getText())==true)
                        {
                            JOptionPane.showMessageDialog(null,"Mã thể loại đã tồn tại");
                            return;
                        }
                        Vector row=new Vector();
                        adddata(row,text);
                        model.addRow(row);
                        loaiDTO newlv=new loaiDTO(text[0].getText(),text[1].getText(),1);
                        try {
                            theloai.Insert(newlv);
                        } catch (Exception ex) {
                        }
                    }
                        break;
                    case "nxb":{
                        nxbBUS nxb=new nxbBUS();
                        nxb.getNXBList();
                        if(nxb.sameid(text[0].getText())==true)
                        {
                            JOptionPane.showMessageDialog(null,"Mã thể loại đã tồn tại");
                            return;
                        }
                        if(!TOOL.check.checkEmail(text[3].getText()))
                        {
                            JOptionPane.showMessageDialog(null,"Email phai co dinh dang (xxx@xxx)");
                            return;
                        }
                        if(!TOOL.check.checkPhone(text[2].getText()))
                        {
                            JOptionPane.showMessageDialog(null,"SDT phai la so va chua 10 chu so");
                            return;
                        }
                        Vector row=new Vector();
                        adddata(row,text);
                        model.addRow(row);
                        nxbDTO newlv=new nxbDTO(text[0].getText(),text[1].getText(),text[2].getText(),text[3].getText(),1);
                        try {
                            nxb.Insert(newlv);
                        } catch (Exception ex) {
                        }
                    }
                        break;
                }
                table.setModel(model);
                input.setText(text[0].getText());
            }
        });
        
        sua=new JButton();
        sua.setText("Sửa");
        sua.setBounds(150, 150, 80, 30);
        add(sua);
        sua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kiemtra(textoflabel)==1)
                    return;
                switch(loai)
                {
                    case "tacgia":{
                        tacgiaBUS tacgia=new tacgiaBUS();
                        if(tacgia.sameid(text[0].getText())==false)
                        {
                            JOptionPane.showMessageDialog(null,"Mã này chưa có trong danh sách-Vui lòng thêm mới");
                            return;
                        }
                        tacgiaDTO newtg=new tacgiaDTO(text[0].getText(),text[1].getText(),text[2].getText(),text[3].getText(),1);
                        try {
                            tacgia.Update(newtg);
                        } catch (Exception ex) {
                        }
                    }
                        break;
                    case "linhvuc":{
                        linhvucBUS linhvuc=new linhvucBUS();
                        if(linhvuc.sameid(text[0].getText())==false)
                        {
                            JOptionPane.showMessageDialog(null,"Mã này chưa có trong danh sách-Vui lòng thêm mới");
                            return;
                        }
                        linhvucDTO newlv=new linhvucDTO(text[0].getText(),text[1].getText(),1);
                        try {
                            linhvuc.Update(newlv);
                        } catch (Exception ex) {
                        }
                    }
                        break;    
                    case "theloai":{
                        loaiBUS theloai=new loaiBUS();
                        if(theloai.sameid(text[0].getText())==false)
                        {
                            JOptionPane.showMessageDialog(null,"Mã này chưa có trong danh sách-Vui lòng thêm mới");
                            return;
                        }
                        loaiDTO newtl=new loaiDTO(text[0].getText(),text[1].getText(),1);
                        try {
                            theloai.Update(newtl);
                        } catch (Exception ex) {
                        }
                    }
                        break;
                    case "nxb":{
                        nxbBUS nxb=new nxbBUS();
                        if(nxb.sameid(text[0].getText())==false)
                        {
                            JOptionPane.showMessageDialog(null,"Mã này chưa có trong danh sách-Vui lòng thêm mới");
                            return;
                        }
                        nxbDTO newlv=new nxbDTO(text[0].getText(),text[1].getText(),text[2].getText(),text[3].getText(),1);
                        try {
                            nxb.Update(newlv);
                        } catch (Exception ex) {
                        }
                    }
                        break;
                }
                refresh(textoflabel);
                input.setText(text[0].getText());
            }
            
        });
        
        xoa=new JButton();
        xoa.setText("Xóa");
        xoa.setBounds(250, 150, 80, 30);
        add(xoa);
        xoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(click==-1)
                {
                    JOptionPane.showMessageDialog(null,"Bạn phải chọn 1 đóoi tượng cần xóa");
                    return;
                }
                switch(loai)
                {
                    case "tacgia":{
                        tacgiaBUS bus=new tacgiaBUS();
                        tacgiaDTO newtg=(tacgiaDTO)tacgiaBUS.dstg.get(click);
                        bus.Delete(newtg);
                        break;
                    }
                    case "linhvuc":{
                        linhvucBUS bus=new linhvucBUS();
                        linhvucDTO newtg=(linhvucDTO)linhvucBUS.dslv.get(click);
                        bus.Delete(newtg);
                        break;
                    }
                    case "theloai":{
                        loaiBUS bus=new loaiBUS();
                        loaiDTO newtg=(loaiDTO)loaiBUS.dstl.get(click);
                        bus.Delete(newtg);
                        break;
                    }
                    case "nxb":{
                        nxbBUS bus=new nxbBUS();
                        nxbDTO newtg=(nxbDTO)nxbBUS.dsnxb.get(click);
                        bus.Delete(newtg);
                        break;
                    }
                }
                model.removeRow(click);
                table.setModel(model);
                for(int i=0;i<textoflabel.length;i++)
                    text[i].setText("");
            }
            
        });
        
        tailai=new JButton();
        tailai.setText("Tải lại");
        tailai.setBounds(350, 150, 80, 30);
        add(tailai);
        tailai.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<textoflabel.length;i++)
                    text[i].setText("");
                text[0].setEditable(true);
            }
        });
    }
    
    
    public int mouseclick(String[] textoflabel)
    {
        int i=table.getSelectedRow();
        if(i==-1)
            return -1;
        for(int j=0;j<textoflabel.length;j++)
        {
            text[j].setText((String)table.getModel().getValueAt(i,j));
        }
        text[0].setEditable(false);
        return i;
    }
    
    public Vector adddata(Vector temp,JTextField[] text)
    {
        for(int i=0;i<text.length;i++)
            temp.add(text[i].getText());
        return temp;
    }
    
    public void refresh(String[] textoflabel)
    {
        for(int i=0;i<textoflabel.length;i++)
            model.setValueAt(text[i].getText(),click,i);
        table.setModel(model);
    }
    
    public void run(String loai,String title,JTextField input){
        temp=new JFrame();
        a=new SupportPanel();
        temp.setLayout(null);
        temp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        temp.setSize(500,500 );
        temp.setTitle(title);
        temp.setResizable(false);
        temp.setLocationRelativeTo(null);
        temp.setVisible(true);
        a.setBounds(0,0,500,500);
        temp.add(a);
        a.phanloai(loai,input);

    }
    public static void main(String[] args){
        JTextField txTacgia=new JTextField();
        SupportPanel tacgia2=new SupportPanel();
                tacgia2.run("tacgia","Tác giả",txTacgia);

    }

}
