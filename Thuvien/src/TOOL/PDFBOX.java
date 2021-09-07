/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TOOL;

import BUS.linhvucBUS;
import BUS.nhanvienBUS;
import BUS.sachBUS;
import BUS.tacgiaBUS;
import DTO.linhvucDTO;
import DTO.nhanvienDTO;
import DTO.sachDTO;
import DTO.tacgiaDTO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.fontbox.*;


public class PDFBOX {
    private Document document;
    public PDFBOX() throws FileNotFoundException, DocumentException
    {
        String duongdan="C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\Library\\In.pdf";
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(duongdan));
        
        document.open();
    }
    
    public void sach(String header,ArrayList temp,String dto) throws DocumentException, IOException {
       File font2=new File("C:\\Users\\\\ADMIN\\Documents\\NetBeansProjects\\Library\\\\JavaLib\\214-Font UVN\\UVNThayGiaoNhe_R.ttf");
       BaseFont baseFont3 = BaseFont.createFont(font2.toString(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
       Font font = new Font(baseFont3, 12);
       Font font3=new Font(baseFont3,15);
       Font big =new Font(baseFont3,20);
      try {
            Paragraph title1 = new Paragraph("                                      "+header+"",big);
            Chapter chapter1 = new Chapter(title1, 1);
            chapter1.setNumberDepth(0);
            document.add(chapter1);
            //Tạo table
            
            //taoj header
            if(dto.equals("sachDTO"))
            {
                sachBUS bus=new sachBUS();
                PdfPTable t = new PdfPTable(bus.getHeader());
                t.setSpacingBefore(25);
                t.setSpacingAfter(25);
                String[] head={"Mã sách","Tên sách","Giá sách","Mã thể loại","Mã tác giả","Mã NXB","Mã lĩnh vực","Số lượng"};
                for(int i=0;i<bus.getHeader();i++){
                    PdfPCell j = new PdfPCell(new Phrase(head[i],font3));
                    t.addCell(j);
                }
                //add du lieu
                for(int i=0;i<temp.size();i++)
                {
                    sachDTO a=(sachDTO)temp.get(i);
                    Paragraph title = new Paragraph(a.getMasach(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getTensach(),font);
                    t.addCell(title);
                    title = new Paragraph(String.valueOf(a.getGiasach()),font);
                    t.addCell(title);
                    title = new Paragraph(a.getMatheloai(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getMatg(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getManxb(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getMalinhvuc(),font);
                    t.addCell(title);
                    title = new Paragraph(String.valueOf(a.getSoluong()),font);
                    t.addCell(title);
                }
            document.add(t);
            }
            if(dto.equals("tacgiaDTO"))
            {
                tacgiaBUS bus=new tacgiaBUS();
                PdfPTable t = new PdfPTable(bus.getHeader());
                t.setSpacingBefore(25);
                t.setSpacingAfter(25);
                String[] head={"Mã tác giả","Họ tác giả","Tên tác giả","Email"};
                for(int i=0;i<bus.getHeader();i++){
                    PdfPCell j = new PdfPCell(new Phrase(head[i],font3));
                    t.addCell(j);
                }
                //add du lieu
                for(int i=0;i<temp.size();i++)
                {
                    tacgiaDTO a=(tacgiaDTO)temp.get(i);
                    Paragraph title = new Paragraph(a.getMatg(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getHotg(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getTentg(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getEmail(),font);
                    t.addCell(title);
                }
                document.add(t);
            }
            if(dto.equals("linhvucDTO"))
            {
                linhvucBUS bus=new linhvucBUS();
                PdfPTable t = new PdfPTable(bus.getHeader());
                t.setSpacingBefore(25);
                t.setSpacingAfter(25);
                String[] head={"Mã Lĩnh Vực","Tên lĩnh vực"};
                for(int i=0;i<bus.getHeader();i++){
                    PdfPCell j = new PdfPCell(new Phrase(head[i],font3));
                    t.addCell(j);
                }
                //add du lieu
                for(int i=0;i<temp.size();i++)
                {
                    linhvucDTO a=(linhvucDTO)temp.get(i);
                    Paragraph title = new Paragraph(a.getMalinhvuc(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getTenlinhvuc(),font);
                    t.addCell(title);
                }
                document.add(t);
            }
            if(dto.equals("nhanvienDTO"))
            {
                nhanvienBUS bus=new nhanvienBUS();
                PdfPTable t = new PdfPTable(bus.getHeader());
                t.setSpacingBefore(25);
                t.setSpacingAfter(25);
                String[] head={"Mã Nhân viên","Họ nhân viên","Tên nhân viên","Ngày sinh","Giới tính","Địa chỉ","Email","SDT","Lương"};
                for(int i=0;i<bus.getHeader();i++){
                    PdfPCell j = new PdfPCell(new Phrase(head[i],font3));
                    t.addCell(j);
                }
                //add du lieu
                for(int i=0;i<temp.size();i++)
                {
                    nhanvienDTO a=(nhanvienDTO)temp.get(i);
                    Paragraph title = new Paragraph(a.getManv(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getHo(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getTen(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getNgaysinh(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getGioitinh(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getDiachi(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getEmail(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getSdt(),font);
                    t.addCell(title);
                    title = new Paragraph(a.getLuong(),font);
                    t.addCell(title);
                }
                document.add(t);
            }
            // Đóng File
            document.close();
            JOptionPane.showMessageDialog(null,"In thành công: đường dẫn:");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args) throws FileNotFoundException, DocumentException, IOException{
        PDFBOX box=new PDFBOX();
        nhanvienBUS sach=new nhanvienBUS();
        sach.getNVList();
        box.sach("Danh sách Tác giả",nhanvienBUS.dsnv,"nhanvienDTO");
    }
}
