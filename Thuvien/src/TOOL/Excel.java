package TOOL;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    public void ExportExcel(String name, JTable table) {
        TableModel tblData = table.getModel();
        FileOutputStream excelFOS = null;
        BufferedOutputStream excelBOS = null;
        XSSFWorkbook wb = null;
        JFileChooser excelFileChooser = new JFileChooser();
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "ods");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet(name);
                XSSFRow row = sheet.createRow(0);
                for (int c = 0; c < tblData.getColumnCount(); c++) {
                    XSSFCell cell = row.createCell(c);
                    cell.setCellValue(tblData.getColumnName(c));
                }
                for (int i = 0; i < tblData.getRowCount(); i++) {
                    XSSFRow excelRow = sheet.createRow(i + 1);
                    for (int j = 0; j < tblData.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(tblData.getValueAt(i, j).toString());
                    }
                }
                excelFOS = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBOS = new BufferedOutputStream(excelFOS);
                wb.write(excelBOS);
                JOptionPane.showMessageDialog(null, "Successfully saved.");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (excelBOS != null) {
                        excelBOS.close();
                    }
                    if (excelFOS != null) {
                        excelFOS.close();
                    }
                    if (wb != null) {
                        wb.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void InportExcel(JTable table) {
        File excelFile;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        JFileChooser excelFileChooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "ods");
        excelFileChooser.setFileFilter(fnef);
        excelFileChooser.setDialogTitle("Select Excel File");
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                XSSFWorkbook excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet sheet = excelJTableImport.getSheetAt(0);
                for (int row = 0; row < sheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = sheet.getRow(row + 1);
                    ArrayList<Object> temp = new ArrayList<>();
                    Vector rowTemp = new Vector();
                    for (int cell = 0; cell < excelRow.getLastCellNum(); cell++) {
                        rowTemp.add(excelRow.getCell(cell));
                    }
                    model.addRow(rowTemp);
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

}
