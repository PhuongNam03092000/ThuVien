package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import com.toedter.calendar.JDateChooser;

import BUS.ctppBUS;
import BUS.phieuphatBUS;
import DTO.ctppDTO;
import DTO.phieuphatDTO;
import TOOL.check;

public class QLPPPanel extends JPanel implements ActionListener, KeyListener, PropertyChangeListener {
	private JTextField txMaPP;
	private JTextField txMaPM;
	private tablePP table;
	private JButton btnThemPP, btnSuaPP, btnTailaiPP;
	private JButton btnThemCTPP, btnXoaCTPP, btnTailaiCTPP, btnSuaCTPP;
	private JDateChooser dateNgayLap, findStartDate, findEndDate;
	private check check;
	private JTextField txFindPP;
	private JTextField txFindPM;
	private tableCTPP tableCTPP;
	private JTextField txMsach, txSoluong;
	private JTextField txMaqd;
	private JLabel lblT;
	private JLabel lbln;
	private HashMap<String, Integer> map;

	public QLPPPanel() {
		setBackground(Color.WHITE);
		setLayout(null);

		check = new check();

		JLabel lbMaPP = new JLabel("Mã phiếu phạt");
		lbMaPP.setBounds(554, 17, 99, 25);
		add(lbMaPP);

		txMaPP = new JTextField();
		txMaPP.setEditable(false);
		txMaPP.setBounds(688, 16, 191, 25);
		add(txMaPP);
		txMaPP.setColumns(10);

		JLabel lbMaPM = new JLabel("Mã phiếu mượn");
		lbMaPM.setBounds(554, 58, 99, 25);
		add(lbMaPM);

		txMaPM = new JTextField();
		txMaPM.setBounds(688, 57, 163, 26);
		add(txMaPM);
		txMaPM.setColumns(10);

		JButton btnNewButton = new JButton("...");
		btnNewButton.setBounds(854, 57, 25, 25);
		add(btnNewButton);

		JLabel lbNgayLap = new JLabel("Ngày lập");
		lbNgayLap.setBounds(554, 98, 99, 25);
		add(lbNgayLap);

		Date today = new Date();
		dateNgayLap = new JDateChooser();
		dateNgayLap.setDateFormatString("yyyy-MM-dd");
		dateNgayLap.setDate(today);
		dateNgayLap.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateNgayLap.setBounds(688, 98, 191, 26);
		add(dateNgayLap);

		phieuphatBUS bus = new phieuphatBUS();

		table = new tablePP();
		try {
			table.setData(bus.getPPList());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table.loadData();
		table.setFont(new Font("Calibri", Font.PLAIN, 18));
		table.setBounds(15, 16, 511, 258);
		add(table);

		btnThemPP = new JButton("Thêm");
		btnThemPP.setBounds(899, 17, 97, 25);
		add(btnThemPP);

		btnSuaPP = new JButton("Sửa");
		btnSuaPP.setBounds(899, 59, 97, 25);
		add(btnSuaPP);

		btnTailaiPP = new JButton("Tải lại");
		btnTailaiPP.setBounds(899, 98, 97, 25);
		add(btnTailaiPP);

		btnThemPP.addActionListener(this);
		btnSuaPP.addActionListener(this);
		btnTailaiPP.addActionListener(this);

		JLabel lbFindPP = new JLabel("Mã phiếu phạt :");
		lbFindPP.setBounds(554, 137, 99, 25);
		add(lbFindPP);

		txFindPP = new JTextField();
		txFindPP.setBounds(665, 136, 69, 26);
		add(txFindPP);
		txFindPP.setColumns(10);
		txFindPP.addKeyListener(this);

		JLabel lbFindPM = new JLabel("Mã phiếu mượn :");
		lbFindPM.setBounds(746, 137, 133, 25);
		add(lbFindPM);

		txFindPM = new JTextField();
		txFindPM.setBounds(865, 136, 67, 26);
		add(txFindPM);
		txFindPM.setColumns(10);
		txFindPM.addKeyListener(this);

		findStartDate = new JDateChooser();
		findStartDate.setBounds(593, 234, 183, 29);
		add(findStartDate);
		findStartDate.addPropertyChangeListener(this);

		findEndDate = new JDateChooser();
		findEndDate.setBounds(832, 234, 192, 29);
		add(findEndDate);
		findEndDate.addPropertyChangeListener(this);

		tableCTPP = new tableCTPP();
		tableCTPP.setBounds(477, 290, 555, 302);
		add(tableCTPP);

		btnThemCTPP = new JButton("Thêm");
		btnThemCTPP.setBounds(40, 435, 183, 29);
		btnThemCTPP.addActionListener(this);
		add(btnThemCTPP);

		btnXoaCTPP = new JButton("Xóa");
		btnXoaCTPP.addActionListener(this);
		btnXoaCTPP.setBounds(238, 435, 192, 29);
		add(btnXoaCTPP);

		btnTailaiCTPP = new JButton("Tải lại");
		btnTailaiCTPP.setBounds(40, 480, 183, 29);
		btnTailaiCTPP.addActionListener(this);
		add(btnTailaiCTPP);

		btnSuaCTPP = new JButton("Sửa");
		btnSuaCTPP.addActionListener(this);
		btnSuaCTPP.setBounds(238, 480, 192, 29);
		add(btnSuaCTPP);

		JLabel lbMasach = new JLabel("Mã sách :");
		lbMasach.setBounds(40, 305, 99, 25);
		add(lbMasach);

		txMsach = new JTextField(10);
		txMsach.setBounds(180, 305, 250, 26);
		add(txMsach);

		JLabel lbSoluong = new JLabel("Số lượng :");
		lbSoluong.setBounds(40, 345, 69, 20);
		add(lbSoluong);

		txSoluong = new JTextField(10);
		txSoluong.setBounds(180, 345, 250, 26);
		add(txSoluong);

		JLabel lbMaqd = new JLabel("Mã quy định :");
		lbMaqd.setBounds(40, 385, 90, 25);
		add(lbMaqd);

		txMaqd = new JTextField();
		txMaqd.setBounds(180, 385, 222, 25);
		add(txMaqd);
		txMaqd.setColumns(10);

		JButton btnNewButton_2 = new JButton("...");
		btnNewButton_2.setBounds(406, 385, 25, 25);
		add(btnNewButton_2);

		lblT = new JLabel("Từ :");
		lblT.setBounds(554, 234, 56, 25);
		add(lblT);

		lbln = new JLabel("Đến :");
		lbln.setBounds(795, 234, 56, 25);
		add(lbln);

		ctppBUS busCTPP = new ctppBUS();

		try {
			map = busCTPP.getQuyDinhMap();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		table.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = table.getTable().getSelectedRow();
				phieuphatDTO pp = table.getPP(i);

				txMaPP.setText(pp.getMapp());
				txMaPM.setText(pp.getMapm());
				try {
					dateNgayLap.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(pp.getNgaylap()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ctppBUS bus = new ctppBUS();
				if (i >= 0) {
					tableCTPP.setData(bus.getCTPPList(pp.getMapp()));
					tableCTPP.loadData();
				}
			}
		});

		tableCTPP.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ctppBUS bus = new ctppBUS();
				int i = tableCTPP.getTable().getSelectedRow();
				ctppDTO ctpp = bus.getCTPPList(txMaPP.getText()).get(i);

				txMsach.setText(ctpp.getMasach());
				txSoluong.setText(String.valueOf(ctpp.getSoluong()));
				txMaqd.setText(ctpp.getMaqd());

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnThemPP) {
			System.out.println("Thêm PP");
			System.out.println("Check maPM");
			if (check.checkDate((String) new SimpleDateFormat("dd-MM-yyyy").format(dateNgayLap.getDate()))) {
				System.out.println("check date");
				String dateLap = (String) new SimpleDateFormat("yyyy-MM-dd").format(dateNgayLap.getDate());

				phieuphatBUS busPP = new phieuphatBUS();

				phieuphatDTO pp = null;
				try {
					pp = new phieuphatDTO(busPP.autoCreateID(), txMaPM.getText(), dateLap);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					if (pp == null) {
						System.out.println("Lỗi quá trình thêm");
					}
					busPP.Insert(pp);
					table.setData(busPP.getPPList());
                                        table.loadData();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		if (evt.getSource() == btnSuaPP) {
			int i = table.getTable().getSelectedRow();
			if (i >= 0) {
				phieuphatBUS busPP = new phieuphatBUS();
				if (check.checkDate((String) new SimpleDateFormat("dd-MM-yyyy").format(dateNgayLap.getDate()))) {
					String dateLap = (String) new SimpleDateFormat("yyyy-MM-dd").format(dateNgayLap.getDate());
					phieuphatDTO pp = new phieuphatDTO(txMaPP.getText(), txMaPM.getText(), dateLap);
					try {
						busPP.Update(pp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						table.setData(busPP.getPPList());
						table.loadData();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}

		if (evt.getSource() == btnTailaiPP) {
			phieuphatBUS bus = new phieuphatBUS();
			try {
				table.setData(bus.getPPList());
				table.loadData();
				JOptionPane.showMessageDialog(this, "Đã tải lại");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (evt.getSource() == btnThemCTPP) {
			ctppBUS bus = new ctppBUS();
			phieuphatBUS busPP = new phieuphatBUS();
			ctppDTO ctpp = new ctppDTO(txMaPP.getText(), txMsach.getText(), Integer.parseInt(txSoluong.getText()),
					txMaqd.getText(), map.get(txMaqd.getText()) * Integer.parseInt(txSoluong.getText()));
			
			if (ctpp == null) {
				System.out.println("Lỗi quá trình thêm");
			} else {
				bus.insert(ctpp);

				tableCTPP.setData(bus.getCTPPList(txMaPP.getText()));
                                tableCTPP.loadData();
				try {
					table.setData(busPP.getPPList());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				table.loadData();
			}

		}

		if (evt.getSource() == btnXoaCTPP) {
			ctppBUS bus = new ctppBUS();
			phieuphatBUS busPP = new phieuphatBUS();
			ctppDTO ctpp = new ctppDTO(txMaPP.getText(), txMsach.getText(), Integer.parseInt(txSoluong.getText()),
					txMaqd.getText(), map.get(txMaqd.getText()) * Integer.parseInt(txSoluong.getText()));
			try {
				bus.delete(ctpp);
				tableCTPP.setData(bus.getCTPPList(txMaPP.getText()));
				tableCTPP.loadData();
				table.setData(busPP.getPPList());
				table.loadData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (evt.getSource() == btnSuaCTPP) {
			ctppBUS bus = new ctppBUS();
			ctppDTO ctpp = new ctppDTO(txMaPP.getText(), txMsach.getText(), Integer.parseInt(txSoluong.getText()),
					txMaqd.getText(), map.get(txMaqd.getText()) * Integer.parseInt(txSoluong.getText()));
			phieuphatBUS busPP = new phieuphatBUS();
			try {
				bus.update(ctpp);
				tableCTPP.setData(bus.getCTPPList(txMaPP.getText()));
                                tableCTPP.loadData();
				table.setData(busPP.getPPList());
				table.loadData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (evt.getSource() == btnTailaiCTPP) {
			ctppBUS bus = new ctppBUS();
			
			tableCTPP.setData(bus.getCTPPList(txMaPP.getText()));
			tableCTPP.loadData();
			JOptionPane.showMessageDialog(this, "Đã tải lại");
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		RowFilter<Object, Object> dateFilter = new RowFilter<Object, Object>() {
			@Override
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
					LocalDate d1 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(findStartDate.getDate()));
					LocalDate d2 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(findEndDate.getDate()));
					LocalDate d3 = LocalDate.parse(entry.getStringValue(3));
					long diff = ChronoUnit.DAYS.between(d1, d2);
					
					long diffActual = ChronoUnit.DAYS.between(d3, d2);
					if (diffActual >= 0) {
						if (diffActual <= diff) {
							return true;
						}
					}
				return false;
			}

		};

		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
		filters.add(RowFilter.regexFilter("(?i)" + txFindPP.getText().toLowerCase(), 0));
		filters.add(RowFilter.regexFilter("(?i)" + txFindPM.getText().toLowerCase(), 1));
		if( findStartDate.getDate() != null && findEndDate.getDate() != null ) {
			filters.add(dateFilter);
		}
		RowFilter rf = RowFilter.andFilter(filters);

		if (txMaPP.getText().isEmpty() && txMaPM.getText().isEmpty() && findStartDate.getDate() == null && findEndDate.getDate() == null ) {
			table.getTr().setRowFilter(null);
		} else {
			table.getTr().setRowFilter(rf);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		RowFilter<Object, Object> dateFilter = new RowFilter<Object, Object>() {
			@Override
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
					LocalDate d1 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(findStartDate.getDate()));
					LocalDate d2 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(findEndDate.getDate()));
					LocalDate d3 = LocalDate.parse(entry.getStringValue(3));
					long diff = ChronoUnit.DAYS.between(d1, d2);
					
					
					long diffActual = ChronoUnit.DAYS.between(d3, d2);

					if (diffActual >= 0) {
						if (diffActual <= diff) {
							return true;
						}
					}
				return false;
			}

		};

		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
		filters.add(RowFilter.regexFilter("(?i)" + txFindPP.getText().toLowerCase(), 0));
		filters.add(RowFilter.regexFilter("(?i)" + txFindPM.getText().toLowerCase(), 1));
		if( findStartDate.getDate() != null && findEndDate.getDate() != null ) {
			filters.add(dateFilter);
		}
		RowFilter rf = RowFilter.andFilter(filters);

		if (txMaPP.getText().isEmpty() && txMaPM.getText().isEmpty() && findStartDate.getDate() == null && findEndDate.getDate() == null) {
			table.getTr().setRowFilter(null);
		} else {
			table.getTr().setRowFilter(rf);
		}
	}
}
