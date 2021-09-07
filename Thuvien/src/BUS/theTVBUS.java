package BUS;

import java.util.ArrayList;
import DAO.theTVDAO;
import DTO.theTVDTO;
import GUI.modelTTV;
import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class theTVBUS {

    public static ArrayList<theTVDTO> dsttv;
    public static ArrayList<theTVDTO> newTTV;

    theTVDAO data = new theTVDAO();

    public ArrayList<theTVDTO> getTTVList() throws Exception {
        if (dsttv == null) {
            dsttv = new ArrayList<theTVDTO>();
        }
        dsttv = data.filteredList();
        return dsttv;
    }

    public void Insert(theTVDTO the) throws Exception {
        data.Insert(the);
        dsttv.add(the);
        // thêm thẻ thư viện này vào một arraylist khác dùng để thực hiện công việc thống kê
        //	newTTV.add(the);
    }

    public void Delete(theTVDTO the) throws Exception {
        data.Delete(the);
        dsttv.remove(the);
    }

    public void Update(theTVDTO the) throws Exception {
        data.Update(the);
        int k = 0;
        for (int i = 0; i < dsttv.size(); i++) {
            if (dsttv.get(i).getMathe().equals(the.getMathe())) {
                k = i;
            }
        }
        dsttv.set(k, the);
    }
    
    public void loadThongKeTTV(JDateChooser dateStart, JDateChooser dateEnd)throws ParseException, Exception{
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date Start = formatDate.parse(formatDate.format(dateStart.getDate()));
        Date End = formatDate.parse(formatDate.format(dateEnd.getDate()));
        newTTV = getNewTTVList(Start,End);
        //System.out.println(strStart);
    }
    
    
    
  

    public ArrayList<theTVDTO> getNewTTVList(Date start,Date end) throws Exception {
      
        if (newTTV == null) {
            newTTV = new ArrayList<>();
        }
        newTTV = data.newCardList(start,end);
        return newTTV;
    }
}
