package lm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lm.model.OrderDao;
import lm.model.OrderVo;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;

public class LMProdOrder extends JFrame implements ActionListener{

	// Fields
	private String userid;
	private static JFrame frame;
	private static JTextField textField, textField_1, textField_2;
	private static String selDate = "";
	private static JTable table;
	private static JButton btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_3;
	private JLabel lblNewLabel_1, lblNewLabel_2, lblNewLabel_3;

	private static ArrayList<Object> orderNum = new ArrayList<Object>();
	private static ArrayList<Object> orderDate = new ArrayList<Object>();
	private static ArrayList<Object> orderDname = new ArrayList<Object>();
	private static ArrayList<Object> orderPname = new ArrayList<Object>(); 
	ImageIcon icon;
	static LMProdOrder order3     = null;
	LMOrderList     odl       = null ;	
	public static OrderVo ot = new OrderVo();



	//               Order3 window = new Order3();
	//               window.frame.setVisible(true);
	//               window.frame.setTitle("?????? ?????? ??????");
	//               window.frame.setResizable(false);


	// this.setVisible(false) : ??? ?????? ?????? addActionListener ??? ??????

	public LMProdOrder(String userid) {

		this.userid = userid;
		initComponent();
	}

	private void initComponent() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LMProdOrder.class.getResource("/lmimage/alphabets-33744_640.png")));

		frame.setTitle("??????????????????");

		icon = new ImageIcon(LMProdOrder.class.getResource("/lmimage/??????1.png"));

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};


		frame.setBounds(100, 100, 1000, 600);

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);

		LocalDate now = LocalDate.now();
		model.setDate(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth() );	// ?????? ????????? ????????????
		model.setSelected(true);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
				);


		scrollPane.setViewportView(panel);
		panel.setLayout(null);




		// ??????
		lblNewLabel_3 = new JLabel("???????????? ?????? :");
		lblNewLabel_3.setFont(new Font("?????????", Font.PLAIN, 12));
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setBounds(193, 29, 84, 15);

		textField = new JTextField(20);
		textField.setBounds(289, 118, 171, 21);
		panel.add(textField);
		//      textField.setBounds(102, 17, 184, 20);
		textField.setPreferredSize(new Dimension(80, 20));


		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					btnNewButton_1.doClick(); 
				}
			}
		});

		textField_1 = new JTextField(15);
		panel.add(textField_1);
		textField_1.setBounds(289, 56, 171, 21);


		// ????????????
		lblNewLabel_1 = new JLabel("???????????? :");
		lblNewLabel_1.setFont(new Font("?????????", Font.PLAIN, 12));
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setBounds(193, 61, 56, 15);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setBackground(SystemColor.window);
		datePicker.setBackground(SystemColor.window);
		datePicker.setBounds(289, 23, 171, 30);
		panel.add(datePicker);
		datePicker.setPreferredSize(new Dimension(250, 30));

		// ????????????
		JLabel lblNewLabel = new JLabel("???????????? :");
		lblNewLabel.setFont(new Font("?????????", Font.PLAIN, 12));
		lblNewLabel.setBounds(193, 121, 60, 20);
		panel.add(lblNewLabel);
		//      lblNewLabel.setBounds(39, 20, 68, 15);
		lblNewLabel.setPreferredSize(new Dimension(60, 20));

		// ????????????
		lblNewLabel_2 = new JLabel("???????????? :");
		lblNewLabel_2.setFont(new Font("?????????", Font.PLAIN, 12));
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setBounds(193, 92, 56, 15);

		// ???????????????
		btnNewButton_2 = new JButton("????????? ??????");
		btnNewButton_2.setIcon(new ImageIcon(LMProdOrder.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_2.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton_2.setBounds(843, 94, 103, 32);
		btnNewButton_2 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_2);
		//	  btnNewButton_2.setSize(99, 30);
		btnNewButton_2.setPreferredSize(new Dimension(100, 30));	// FlowLayout??? ???????????? ???????????? ??????
		btnNewButton_2.setToolTipText("d:/ws/java/DBProject02/src/jTable_20230220142558.xlsx");

		textField_2 = new JTextField(15);
		panel.add(textField_2);
		textField_2.setBounds(289, 88, 171, 21);


		// ??????
		btnNewButton = new JButton("\uC8FC\uBB38");
		btnNewButton.setIcon(new ImageIcon(LMProdOrder.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnNewButton.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton.setBounds(643, 94, 70, 32);
		btnNewButton .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton);
		btnNewButton.setToolTipText("???????????? ?????? ??? ??????");
		//	  btnNewButton.setBounds(636, 12, 99, 30);
		btnNewButton.setPreferredSize(new Dimension(100, 30));


		// ????????????
		btnNewButton_1 = new JButton("\uAC80\uC0C9");
		btnNewButton_1.setIcon(new ImageIcon(LMProdOrder.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnNewButton_1.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton_1 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_1);
		btnNewButton_1.setToolTipText("???????????? ?????? ??? ??????");
		btnNewButton_1.setBounds(474, 94, 70, 32);
		btnNewButton_1.setPreferredSize(new Dimension(200, 30));


		// ???????????? ??????
		btnNewButton_3 = new JButton("\uC8FC\uBB38\uB0B4\uC5ED\uC870\uD68C");
		btnNewButton_3.setIcon(new ImageIcon(LMProdOrder.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_3.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton_3.setBounds(725, 94, 106, 32);
		btnNewButton_3 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_3);
		//	  btnNewButton_3.setBounds(636, 12, 99, 30);
		btnNewButton_3.setPreferredSize(new Dimension(120, 30));

		// ?????????
		table = new JTable() {

			@Override
			public boolean isCellEditable(int row, int column) {
				int  currColumn = table.getSelectedColumn();  // ????????? ?????? ????????????
				if( currColumn == 6  )
					return true;			
				return false;   // ?????? cell ???????????????
			}

		};

		table.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					btnNewButton.doClick(); 

				}
			}
		});

		JScrollPane sp = new JScrollPane(table); 
		sp.setBounds(5, 158, 970, 395);
		panel.add(sp);
		sp.setPreferredSize(new Dimension(970, 400));

		JLabel lblNewLabel_4 = new JLabel("\uC0C1\uD488\uC8FC\uBB38\uC5C5\uBB34");
		lblNewLabel_4.setFont(new Font("?????????", Font.BOLD, 40));
		lblNewLabel_4.setBounds(703, 10, 391, 79);
		panel.add(lblNewLabel_4);

		btnNewButton_3.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("???????????? ?????? ?????? ??????....");	
				if (odl != null)
					odl.setVisible(false);
				odl = new LMOrderList();
			}
		});

		btnNewButton_1.addActionListener(this);

		// ?????? ??????
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.editCellAt(-1, -1);	// ????????? cell??? ????????? ??????????????? ???????????? ????????? ????????? ?????????(?????? ??? ???????????? ?????? ??????)
				}catch(Exception ex) {}
				addList();
				JDialog jd = new JDialog(0);
				jd.Dlbl.setText( "?????????????????????");
				jd.setTitle("????????????");

			}
		});

		btnNewButton_2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("????????? ??????....");
				LocalDateTime  now   =  LocalDateTime.now(); 
				int            year  =  now.getYear();
				int            mm    =  now.getMonthValue();
				int            dd    =  now.getDayOfMonth();
				int            hh    =  now.getHour();
				int            mi    =  now.getMinute();
				int            ss    =  now.getSecond();

				String  fmt      = "D:\\excel\\";
				fmt             += "????????????_%4d%02d%02d%02d%02d%02d.xlsx";
				String  filepath = String.format(fmt, year, mm, dd, hh, mi, ss );

				System.out.println( filepath );
				excelWrite( filepath );

			}

		});

		datePicker.addActionListener(new ActionListener() {		// ????????? ?????? ????????????, ??????????????? ??????????????? ??????

			@Override
			public void actionPerformed(ActionEvent e) {
				selDate = datePicker.getJFormattedTextField().getText();

				// ????????? ????????? DATE ???????????? ??????
				Date selectedDate = (Date) datePicker.getModel().getValue();


				Calendar cal = Calendar.getInstance();
				cal.setTime(selectedDate);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String current = df.format(cal.getTime());
				//	        System.out.println("current: " + df.format(cal.getTime()));

				textField_1.setText(current);	

				cal.add(Calendar.DATE, +1);		// ??????????????? = ????????? + 1
				String after = df.format(cal.getTime());
				//	        System.out.println("after: " + df.format(cal.getTime()));

				textField_2.setText(after);	

				ot.setOrderDate(current);	// ?????? ??????
				//				jTableRefresh(); 	// ????????? ???????????? ?????????
			}

			private void jTableRefresh() {
				table.setModel(
						new DefaultTableModel( getDataList(),  getColumnList()  ) {

							@Override
							public boolean isCellEditable(int row, int column) {					
								return false;
							}

						}
						);  // table ????????? ???????????? ??????

				table.repaint();  // table??? ?????? ?????????

			}
		});
		frame.getContentPane().setLayout(groupLayout);

		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// ????????????????????? ??????
		frame.setLocation(650,200);
		frame.setVisible(true);
		frame.setResizable(false);
		setResizable(false);

	}

	// ????????? getDataList
	private Vector<Vector> getDataList() {
		OrderDao       dao   =  new OrderDao();
		Vector<Vector>  list  =  dao.getOrder();

		return list;
	}

	// ?????? ??? ????????? getDataList
	private Vector<Vector> getDataList(LMProdOrder order3) {
		String          search = textField.getText();
		OrderDao       dao   =  new OrderDao();
		Vector<Vector>  list  =  dao.getOrder(search, userid);

		return list;
	}

	// ????????? getColumnList
	private Vector<String> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("????????????");
		cols.add("?????? ??????");
		cols.add("?????? ??????");
		cols.add("?????????");
		cols.add("?????? ??????");
		cols.add("?????? ??????");
		cols.add("?????? ??????");
		cols.add("?????? ??????");

		return cols;
	}



	// ?????? ????????????
	private void excelWrite(String filepath) {
		XSSFWorkbook  workbook =  new XSSFWorkbook();
		XSSFSheet     sheet    =  workbook.createSheet("Data");

		// data ?????? : swing jTable -> Excel Sheet
		getWorkbook_Data( sheet );

		// ?????? ??????
		FileOutputStream  fos = null;
		try {
			fos = new FileOutputStream( filepath );
			workbook.write(fos);
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText("D:\\excel ??? ????????? ?????????????????????");
			jd.setTitle("????????????");
		} catch (IOException e) {
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText("<html><body><center>????????? ?????????????????????."
					+ "<br>??????????????? ????????? ??????????????????."
					+ "<br>????????????: D:\\excel</center></body></html>");
			jd.setTitle("????????????");	
			e.printStackTrace();
		} finally {
			try {
				if(fos != null)fos.close();
			} catch (IOException e) {
			}
		}

	}

	private void getWorkbook_Data(XSSFSheet sheet) {

		XSSFRow     row   =  null;
		XSSFCell    cell  =  null;

		int numcols = table.getColumnCount();
		int numrows = table.getRowCount();

		// ????????? ??????
		Vector<String>  cols =  getColumnList();
		row          =  sheet.createRow( 0 );
		for (int i = 0; i < cols.size(); i++) {
			cell     =  row.createCell(i);
			cell.setCellValue(  cols.get(i) );    
		}

		//????????? ??????
		for (int i = 0; i < numrows; i++) {
			row    =  sheet.createRow(i+1);
			for (int j = 0; j < numcols ; j++) {
				cell = row.createCell(j);
				cell.setCellValue((String) table.getValueAt(i, j));
			}
		}
	} // ?????? ???

	@Override
	public void actionPerformed(ActionEvent e) {

		switch( e.getActionCommand() ) {
		case "??????":
			Vector<Vector> list = getDataList(this);
			jTableRefresh2(list);
			break;

		}
	}

	private void addList() {
		getOrderArrayData();
		OrderDao dao = new OrderDao();			
		dao.insertList(orderNum,orderDate,orderDname,orderPname, userid);						
		orderNum.clear();
		orderDate.clear();
		orderDname.clear();
		orderPname.clear();
	}

	// ArrayList -> DAO INSERT ??? ?????? ?????? 
	static void getOrderArrayData() {
		int rowsCount = table.getRowCount();
		for (int i = 0; i < rowsCount; i++) {

			try {
				orderDname.add(i, table.getValueAt(i, 0).toString() );	// dname ????????????
				orderDate.add(i, table.getValueAt(i, 1).toString() ); 	// orderdate ????????????
				if( orderDate.get(i).equals("") ) {
					JDialog jd = new JDialog(0);
					jd.Dlbl.setText("??????????????? ??????????????????.");
					jd.setTitle("??????????????????");
					return;
				}
				orderPname.add(i, table.getValueAt(i, 3).toString() );	// pname ?????????
			}catch (NullPointerException e) {
				System.err.println("table.getValueAt ??? null ?????????.");
				JDialog jd = new JDialog(0);
				jd.Dlbl.setText("????????? ??????.");
				jd.setTitle("?????? ??????");
				return;
			}
			orderNum.add(i, table.getValueAt(i, 6).toString() ); 	// ordernum ????????????
		}
	}


	private void jTableRefresh2(Vector<Vector> list) {

		table.setModel(new DefaultTableModel(list, getColumnList()) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return super.isCellEditable(row, column);
			}

		});


		table.repaint();

	} public static void main(String[] args) {
		String dd = "";
		new LMProdOrder(dd);
	}
}