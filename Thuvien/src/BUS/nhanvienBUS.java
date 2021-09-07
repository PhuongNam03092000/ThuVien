package BUS;

import java.util.ArrayList;
import DAO.nhanvienDAO;
import DTO.nhanvienDTO;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class nhanvienBUS {

    public static ArrayList<nhanvienDTO> dsnv;
    public static String userID;
    nhanvienDAO data = new nhanvienDAO();

    public ArrayList<nhanvienDTO> getNVList() {
        if (dsnv == null) {
            dsnv = new ArrayList<>();
        }

        // đọc dữ liệu lên và truyền vào arraylist
        try {
            dsnv = data.filteredList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dsnv;
    }
    
    public ArrayList<nhanvienDTO> getNVListForSelecting() throws Exception{
        if(dsnv == null){
            dsnv = new ArrayList<>();
        }
        
        ArrayList<nhanvienDTO> dsnvtemp = data.filteredListForSelecting();
        return dsnvtemp;
    }

    public void Insert(nhanvienDTO nv) throws Exception {
        data.Insert(nv);
        dsnv.add(nv);
    }

    public void Delete(nhanvienDTO nv) throws Exception {
        data.Delete(nv);
        dsnv.remove(nv);
    }

    public void Update(nhanvienDTO nv) throws Exception {
        data.Update(nv);
        // phần thêm
        int k = 0;
        for (int i = 0; i < dsnv.size(); i++) {
            if ((dsnv.get(i)).getManv().equals(nv.getManv())) {
                k = i;
            }
        }
        dsnv.set(k, nv);
    }

    public String findName() {
        String name = "";
        try {
            dsnv = data.docDSNV();
        } catch (Exception e) {
            System.out.println(e);
        }
        for (nhanvienDTO nv : dsnv) {
            if (nv.getManv().compareTo(userID) == 0) {
                name = nv.getHo() + " " + nv.getTen();
            }
        }
        return name;
    }

    public boolean checkID(String manv) {
        int k = 0;
        for (nhanvienDTO nv : dsnv) {
            if (manv.compareToIgnoreCase(nv.getManv()) == 0) {
                k++;
            }
        }
        if (k == 0) {
            return false;
        } else {
            return true;
        }
    }

    public String autoCreateID() {
        String ID = null;
        try {
            dsnv = data.docDSNV();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (dsnv.size() < 10) {
            ID = "NV00" + String.valueOf(dsnv.size() + 1);
        } else if (dsnv.size() >= 10 && dsnv.size() < 100) {
            ID = "NV0" + String.valueOf(dsnv.size() + 1);
        } else if (dsnv.size() >= 100) {
            ID = "NV" + String.valueOf(dsnv.size() + 1);
        }
        return ID;
    }

    public void Import() throws Exception {

        File excelFile;

        //khái báo phần GUI hiển thị nhưng thành phần trùng trong excel
        //khai báo table
        FileInputStream excelFIS;
        BufferedInputStream excelBIS;
        JFileChooser excelFileChooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel File", "xlsx");
        excelFileChooser.setFileFilter(fnef);
        excelFileChooser.setDialogTitle("Chọn file excel");
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            excelFile = excelFileChooser.getSelectedFile();
            excelFIS = new FileInputStream(excelFile);
            excelBIS = new BufferedInputStream(excelFIS);
            XSSFWorkbook excelJTableImport = new XSSFWorkbook(excelBIS);
            XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
            for (int i = 1; i <= excelSheet.getLastRowNum(); i++) {
                XSSFRow excelRow = excelSheet.getRow(i);
                int k = 0;
                String manvInExcel = excelRow.getCell(0).getStringCellValue();
                for (int j = 0; j < dsnv.size(); j++) {
                    if (dsnv.get(j).getManv().equals(manvInExcel)) {
                        k++;
                    }
                }
                if (k == 0) {
                    nhanvienDTO nhanvien = new nhanvienDTO();
                    nhanvien.setManv(excelRow.getCell(0).getStringCellValue());
                    nhanvien.setHo(excelRow.getCell(1).getStringCellValue());
                    nhanvien.setTen(excelRow.getCell(2).getStringCellValue());
                    nhanvien.setNgaysinh(excelRow.getCell(3).getStringCellValue());
                    nhanvien.setGioitinh(excelRow.getCell(4).getStringCellValue());
                    nhanvien.setDiachi(excelRow.getCell(5).getStringCellValue());
                    nhanvien.setEmail(excelRow.getCell(6).getStringCellValue());
                    nhanvien.setSdt(String.valueOf(excelRow.getCell(7).getStringCellValue()));
                    nhanvien.setLuong(String.valueOf(excelRow.getCell(8).getStringCellValue()));
                    nhanvien.setTrangthai(1);
                    nhanvienBUS bus = new nhanvienBUS();
                    bus.Insert(nhanvien);
                } else {

                }
            }

        }
    }

    public int getHeader() {
        return 9;
    }

}
