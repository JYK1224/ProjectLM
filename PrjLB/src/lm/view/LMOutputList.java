package lm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import lm.model.OutputDao;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class LMOutputList extends  JFrame    implements ActionListener {


	private static JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel_4, lblNewLabel_5, lblNewLabel_6, lblNewLabel_7;
	private static JTextField textField, textField_1, textField_2;
	private static JButton btnNewButton_1, btnNewButton_2, btnNewButton_3;
	private static JTable table;
	private static JScrollPane scrollPane;

	private static String startDate;
	private static String endDate;
	private static Date selectedDate1, selectedDate2;
	private static String date1 = "";
	private static String date2 = "";
	ImageIcon icon;
	LMOutput lmoutput = null;
	private JScrollPane scrollPane_1;
	private JPanel panel;
	private JLabel lblNewLabel_9;

	// ???????????????
	public LMOutputList(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/lmimage/alphabets-33744_640.png")));
		getContentPane().setBackground(new Color(231,231,231));
		initComponent();
	}

	//???????????? ?????????
	public LMOutputList(LMOutput lmoutput) {
		this();  // ??????????????? ??????		
		this.lmoutput = lmoutput;
	}

	private void initComponent() {


		setTitle("??????????????????");
		icon = new ImageIcon(LMOutputList.class.getResource("/lmimage/??????1.png"));

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		setSize(1000,600);

		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);

		LocalDate now = LocalDate.now();
		model1.setDate(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth() );	// ?????? ????????? ????????????
		model1.setSelected(true);

		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);

		model2.setDate(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth() );	// ?????? ????????? ????????????
		model2.setSelected(true);

		setVisible(true);
		setResizable(false);

		scrollPane_1 = new JScrollPane(panel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
				);




		
		scrollPane_1.setViewportView(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 158, 970, 351);
		panel.add(scrollPane);
		
		table = new JTable(){
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;   // ?????? cell ???????????????
			}
			
		};
		scrollPane.setPreferredSize(new Dimension(1070, 400));	
		scrollPane.setViewportView(table);
		// ??? ????????????
		lblNewLabel_7 = new JLabel("\uCD1D \uCD9C\uACE0\uAE08\uC561 :");
		lblNewLabel_7.setForeground(SystemColor.text);
		lblNewLabel_7.setFont(new Font("?????????", Font.PLAIN, 17));
		lblNewLabel_7.setBounds(617, 526, 100, 20);
		panel.add(lblNewLabel_7);
		lblNewLabel_7.setPreferredSize(new Dimension(80, 20));

		// ????????? ??????
		lblNewLabel_1 = new JLabel("????????? ?????? :");
		lblNewLabel_1.setFont(new Font("?????????", Font.PLAIN, 12));
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setBounds(190, 45, 74, 23);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.getJFormattedTextField().setBackground(SystemColor.window);
		datePicker1.setBounds(276, 45, 150, 26);
		panel.add(datePicker1);
		datePicker1.setPreferredSize(new Dimension(150, 30));

		// ?????? ?????????
		btnNewButton_3 = new JButton("?????? ?????????");
		btnNewButton_3.setIcon(new ImageIcon(LMOutputList.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_3.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton_3.setBounds(320, 103, 106, 32);
		btnNewButton_3 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_3);

		btnNewButton_3.setPreferredSize(new Dimension(100, 30));

		// ????????????
		lblNewLabel_2 = new JLabel("???????????? :");
		lblNewLabel_2.setFont(new Font("?????????", Font.PLAIN, 12));
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setBounds(438, 45, 64, 23);

		textField_1 = new JTextField(13);
		panel.add(textField_1);
		textField_1.setBounds(514, 45, 116, 23);


		// ?????????
		lblNewLabel = new JLabel("?????????    :");
		lblNewLabel.setFont(new Font("?????????", Font.PLAIN, 12));
		lblNewLabel.setBounds(438, 110, 64, 23);
		panel.add(lblNewLabel);
		lblNewLabel.setPreferredSize(new Dimension(60, 20));

		textField = new JTextField(20);
		textField.setBounds(514, 110, 116, 23);
		panel.add(textField);
		textField.setPreferredSize(new Dimension(80, 20));

		// ????????? ??????
		lblNewLabel_3 = new JLabel("????????? ?????? :");
		lblNewLabel_3.setFont(new Font("?????????", Font.PLAIN, 12));
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setBounds(190, 76, 79, 23);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.getJFormattedTextField().setBackground(SystemColor.window);
		datePicker2.setBounds(276, 73, 150, 26);
		panel.add(datePicker2);
		datePicker2.setPreferredSize(new Dimension(150, 30));


		// ????????????
		lblNewLabel_4 = new JLabel("???????????? :");
		lblNewLabel_4.setFont(new Font("?????????", Font.PLAIN, 12));
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setBounds(438, 78, 64, 23);


		// ???????????????
		btnNewButton_2 = new JButton("????????? ??????");
		btnNewButton_2.setIcon(new ImageIcon(LMOutputList.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_2.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton_2.setBounds(852, 103, 106, 32);
		btnNewButton_2 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_2);
		btnNewButton_2.setPreferredSize(new Dimension(100, 30));	// FlowLayout??? ???????????? ???????????? ??????


		textField_2 = new JTextField(13);
		textField_2.setBounds(514, 77, 116, 23);
		panel.add(textField_2);

		// ?????????

		// ????????????
		btnNewButton_1 = new JButton("????????????");
		btnNewButton_1.setIcon(new ImageIcon(LMOutputList.class.getResource("/lmimage/4\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_1.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton_1.setBounds(653, 104, 90, 32);
		btnNewButton_1 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_1);
		btnNewButton_1.setToolTipText("???????????? ?????? ??? ??????");
		//		btnNewButton_1.setBounds(490, 40, 99, 30);
		btnNewButton_1.setPreferredSize(new Dimension(100, 30));
		
		JLabel lblNewLabel_8 = new JLabel("\uCD9C\uACE0\uB0B4\uC5ED\uC870\uD68C");
		lblNewLabel_8.setFont(new Font("?????????", Font.BOLD, 40));
		lblNewLabel_8.setBounds(703, 10, 391, 79);
		panel.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setForeground(SystemColor.text);
		lblNewLabel_9.setFont(new Font("?????????", Font.PLAIN, 17));
		lblNewLabel_9.setBounds(730, 525, 153, 20);
		panel.add(lblNewLabel_9);

		btnNewButton_1.addActionListener(this);

		

		btnNewButton_2.addActionListener(new ActionListener() {

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

				String  fmt      = "d:\\excel\\";
				fmt             += "????????????_%4d %02d %02d %02d %02d %02d.xlsx";
				String  filepath = String.format(fmt, year, mm, dd, hh, mi, ss );

				System.out.println( filepath );
				excelWrite( filepath );

			}
		});

		datePicker2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				endDate = datePicker2.getJFormattedTextField().getText();

				// ????????? ??????????????? DATE ???????????? ??????
				selectedDate2 = (Date) datePicker2.getModel().getValue();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println("??????"+date2);
				date2 = simpleDateFormat.format(selectedDate2);
				System.out.println(date2);

				Calendar cal = Calendar.getInstance();
				cal.setTime(selectedDate2);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String end = df.format(cal.getTime());

				textField_2.setText(end);

				if(selectedDate1.compareTo(selectedDate2) > 0) {
					JDialog jd = new JDialog(0);
					jd.Dlbl.setText("???????????? ??????????????? ????????????.");
					jd.setTitle("??????????????????");
					textField_2.setText("");
				}

			}
		});

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

		btnNewButton_3.addActionListener(this);

		datePicker1.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// datePicker ?????? ????????? ?????? ??????
				startDate = datePicker1.getJFormattedTextField().getText();

				// ????????? ??????????????? DATE ???????????? ?????? (?????? ????????? ?????????)
				selectedDate1 = (Date) datePicker1.getModel().getValue();
				System.out.println(selectedDate1);


				// SQL ???????????? String ?????? ????????????
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				date1 = simpleDateFormat.format(selectedDate1);

				Date now = Calendar.getInstance().getTime();
				String today = simpleDateFormat.format(now);

				System.out.println(date1);
				System.out.println(today);

				Calendar cal = Calendar.getInstance();
				cal.setTime(selectedDate1);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String start = df.format(cal.getTime());

				textField_1.setText(start);
				if(date1.equals(today)) {	// ???????????? ??????????????? ???????????? ????????? ??????
					textField_2.setText(start);
					date2 = date1;
				}
			}
		});
		getContentPane().setLayout(groupLayout);
		setLocation(700,250);
		setVisible(true);
		setResizable(false);

	}

	// ?????? ??????
	protected void excelWrite(String filepath) {
		XSSFWorkbook  workbook =  new XSSFWorkbook();
		XSSFSheet     sheet    =  workbook.createSheet("Data");
		String fmt = "D:\\excel";
		// data ?????? : swing jTable -> Excel Sheet
		getWorkbook_Data( sheet );

		// ?????? ??????
		FileOutputStream  fos = null;
		try {
			fos = new FileOutputStream( filepath );
			workbook.write(fos);
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText(fmt + " ??? ????????? ?????????????????????");
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

		//????????? ??????
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

	}

	// ????????? getDateList
	//	private Vector<Vector> getDataList() {
	//		OutputDao       dao   =  new OutputDao();
	//		Vector<Vector>  list  =  dao.getOutputList();
	//
	//		return list;
	//	}

	// ?????? ??? ????????? getList
	private Vector<Vector> getDataList(LMOutputList LMOrderList) {
		String          search = textField.getText();
		OutputDao        dao   =  new OutputDao();
		Vector<Vector>  list  =  dao.getOutputList(search, date1, date2);

		return list;
	}

	// ????????? getColumnList
	private Vector<String> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("?????? ??????");
		cols.add("????????????");
		cols.add("?????? ??????");
		cols.add("?????????");
		cols.add("?????? ??????");
		cols.add("?????? ??????");
		cols.add("?????????");
		cols.add("?????? ??????");
		cols.add("?????? ??????");

		return cols;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch( e.getActionCommand() ) {
		case "????????????":

			if(date1.equals("") || date2.equals("")) {
				JDialog jd = new JDialog(0);
				jd.Dlbl.setText("<html><body><center>????????? ?????? ????????????<br>???????????? ???????????????.</center></body></html>");
				jd.setTitle("??????????????????");
				textField_1.setText("");
				textField_2.setText("");
				return;
			}

			Vector<Vector> list = getDataList(this);
			jTableRefresh2(list);
			
			 int rowsCount = table.getRowCount();
			 JDialog jd = new JDialog(0);
			 jd.Dlbl.setText(rowsCount + "??? ?????????????????????");
			 jd.setTitle("??????");
			 DecimalFormat dc = new DecimalFormat("###,###,###,###"+"???");
				String ch = dc.format(Double.parseDouble(String.valueOf(getSumPrice())));
				lblNewLabel_9.setText( String.valueOf(ch) );	
			
			break;

		case "?????? ?????????":
			textField_1.setText("");
			textField_2.setText("");
			date1 = "";
			date2 = "";
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
	}

	public static int getSumPrice(){	// ???????????? ??????????????????
		int rowsCount = table.getRowCount();
		int sum = 0;
		for(int i = 0; i < rowsCount; i++){
			int pri = 0;
			if (table.getValueAt(i, 7) != null) {
				pri = Integer.parseInt(table.getValueAt(i, 4).toString());
			}

			int su  = 0;
			if (table.getValueAt(i, 7) != null) {
				su = Integer.parseInt(table.getValueAt(i, 7).toString());
			}

			sum = sum + (pri * su);
		}
		System.out.println(sum);
		return sum;
	}



	public static void main(String[] args) {
		LMOutputList window = new LMOutputList();
	}
}