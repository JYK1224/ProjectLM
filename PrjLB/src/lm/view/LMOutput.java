package lm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lm.model.OutputDao;
import lm.model.OutputVo;

public class LMOutput extends JFrame implements ActionListener{

	// Fields
	String userid;
	private static JFrame frame;
	private static JTextField textField;
	private static JTable table;
	private static JButton btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4;
	private static JLabel lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel_4, lblNewLabel_5;

	private static ArrayList<Object> outDate = new ArrayList<Object>();
	private static ArrayList<Object> outputPname = new ArrayList<Object>();
	private static ArrayList<Object> outputNum = new ArrayList<Object>(); 
	private static ArrayList<Object> shopName = new ArrayList<Object>(); 
	private static Vector<String> shops;
	private static Vector<Vector> list;


	ImageIcon icon;


	public static Vector<Vector> getList() {
		return list;
	}

	public static void setList(Vector<Vector> list) {
		LMOutput.list = list;
	}

	public static Vector<String> getShops() {
		return shops;
	}

	public static void setShops(Vector<String> shops) {
		LMOutput.shops = shops;
	}


	static LMOutput output     = null;
	LMOutputList     opl       = null ;
	private JComboBox comboBox;	
	public static OutputVo ot = new OutputVo();


	public LMOutput(String userid) {
		this.userid = userid;
		OutputDao odao = new OutputDao();
		odao.setShopVector();
		initComponent();

	}

	public LMOutput() {
		OutputDao odao = new OutputDao();
		odao.setShopVector();
		initComponent();
	}

	private void initComponent() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LMProdOrder.class.getResource("/lmimage/alphabets-33744_640.png")));
		frame.setTitle("??????????????????");

		icon = new ImageIcon(LMOutput.class.getResource("/lmimage/??????1.png"));

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};


		frame.setBounds(100, 100, 1000, 600);
		frame.setLocation(650,200);

		// ??????????????? ?????? ????????? ????????? ???????????? ???

		OutputDao oDao = new OutputDao();
		oDao.setShopVector(); 

		setShops(oDao.setShopVector());  

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


		// ????????????
		JLabel lblNewLabel = new JLabel("???????????? :");
		lblNewLabel.setFont(new Font("?????????", Font.PLAIN, 15));
		lblNewLabel.setBounds(176, 54, 75, 30);
		panel.add(lblNewLabel);
		lblNewLabel.setPreferredSize(new Dimension(60, 20));

		// ?????????
		table = new JTable() {

			@Override
			public boolean isCellEditable(int row, int column) {
				int  currColumn = table.getSelectedColumn();  // ????????? ?????? ????????????
				if( currColumn == 7  )
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

		// ???????????????
		btnNewButton_2 = new JButton("????????? ??????");
		btnNewButton_2.setIcon(new ImageIcon(LMOutput.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_2.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton_2.setBounds(843, 94, 103, 32);
		btnNewButton_2 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_2);
		btnNewButton_2.setPreferredSize(new Dimension(100, 30));	// FlowLayout??? ???????????? ???????????? ??????

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

				String  fmt      = "d:\\excel\\";
				fmt             += "????????????_%4d %02d %02d %02d %02d %02d.xlsx";
				String  filepath = String.format(fmt, year, mm, dd, hh, mi, ss );

				System.out.println( filepath );
				excelWrite( filepath );

			}

		});

		// ?????? ??????
		btnNewButton = new JButton("\uCD9C\uACE0\uD655\uC815");
		btnNewButton.setIcon(new ImageIcon(LMOutput.class.getResource("/lmimage/4\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton.setBounds(599, 95, 92, 32);
		btnNewButton .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton);
		btnNewButton.setToolTipText("???????????? ?????? ??? ??????");
		btnNewButton.setPreferredSize(new Dimension(100, 30));

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.editCellAt(-1, -1);	// ????????? cell??? ????????? ??????????????? ???????????? ????????? ????????? ?????????(?????? ??? ???????????? ?????? ??????)
				}catch(Exception ex) {}
				addList();
				jTableRefresh2(getList());

			}

		});


		// ???????????? ??????
		btnNewButton_3 = new JButton("\uCD9C\uACE0\uB0B4\uC5ED\uD655\uC778");
		btnNewButton_3.setIcon(new ImageIcon(LMOutput.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_3.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton_3.setBounds(711, 95, 106, 32);
		btnNewButton_3 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_3);
		btnNewButton_3.setPreferredSize(new Dimension(120, 30));

		btnNewButton_3.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("???????????? ?????? ?????? ??????....");	
				if (opl != null)
					opl.dispose();
				opl = new LMOutputList();
			}
		});

		JScrollPane sp = new JScrollPane(table); 
		sp.setBounds(5, 158, 970, 395);
		panel.add(sp);
		sp.setPreferredSize(new Dimension(1170, 480));

		comboBox = new JComboBox(getShops());
		comboBox.setFont(new Font("?????????", Font.PLAIN, 12));
		comboBox.setForeground(Color.BLACK);
		comboBox.setBackground(SystemColor.window);
		comboBox.setBounds(263, 95, 150, 30);
		panel.add(comboBox);
		comboBox.setPreferredSize(new Dimension(150, 30));

		comboBox.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ot.setShopId(comboBox.getSelectedItem().toString());


			}
		});

		// ??????
		JButton btnNewButton_1_1 = new JButton("\uC870\uD68C");
		btnNewButton_1_1.setIcon(new ImageIcon(LMOutput.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnNewButton_1_1.setFont(new Font("?????????", Font.PLAIN, 12));
		btnNewButton_1_1.setBounds(425, 94, 70, 32);
		btnNewButton_1_1 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_1_1);
		btnNewButton_1_1.setToolTipText("???????????? ??????, ?????? ?????? ??? ??????");
		//btnNewButton_1_1.setPreferredSize(new Dimension(200, 30));

		btnNewButton_1_1.addActionListener(this);

		// ????????? 
		JLabel lblNewLabel_5_1 = new JLabel("\uC810\uD3EC\uBA85    :");
		lblNewLabel_5_1.setFont(new Font("?????????", Font.PLAIN, 15));
		lblNewLabel_5_1.setBounds(176, 95, 76, 30);
		panel.add(lblNewLabel_5_1);
		lblNewLabel_5_1.setPreferredSize(new Dimension(60, 20));

		textField = new JTextField(20);
		textField.setBounds(263, 55, 150, 30);
		panel.add(textField);
		textField.setPreferredSize(new Dimension(80, 20));

		JLabel lblNewLabel_4_1 = new JLabel("\uC0C1\uD488\uCD9C\uACE0\uC5C5\uBB34");
		lblNewLabel_4_1.setFont(new Font("?????????", Font.BOLD, 40));
		lblNewLabel_4_1.setBounds(700, 10, 391, 79);
		panel.add(lblNewLabel_4_1);


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
					btnNewButton_1_1.doClick(); 
				}
			}
		});
		frame.getContentPane().setLayout(groupLayout);

		frame.setVisible(true);
		frame.setResizable(false);

	}



	// ????????? getDataList
	private Vector<Vector> getDataList() {
		OutputDao       dao   =  new OutputDao();
		Vector<Vector>  list  =  dao.getOutput();
		setList(list);

		return list;
	}


	// ?????? ??? ????????? getDataList
	private Vector<Vector> getDataList(LMOutput output) {
		String          search = textField.getText();
		OutputDao       dao   =  new OutputDao();
		Vector<Vector>  list  =  dao.getOutput(search,userid);
		setList(list);

		return list;
	}

	// ????????? getColumnList
	private Vector<String> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("????????????");
		cols.add("?????? ??????");
		cols.add("????????????");
		cols.add("?????????");
		cols.add("?????? ??????");
		cols.add("?????? ??????");
		cols.add("?????????");
		cols.add("?????? ??????");
		cols.add("?????? ??????");

		return cols;
	}



	public static void main(String[] args) {

		output = new LMOutput();
	}


	// ?????? ????????????
	private void excelWrite(String filepath) {
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

		// ????????? ??????
		Vector<String>  cols =  getColumnList();
		row          =  sheet.createRow( 0 );
		for (int i = 0; i < cols.size(); i++) {
			cell     =  row.createCell(i);
			cell.setCellValue(  cols.get(i) );    
		}

		// ????????? ??????
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

	// ??????????????? ????????? ????????? 
	private void addList() {

		getOutputArrayData();


		OutputDao dao = new OutputDao();

		dao.insertList(outDate,outputPname,shopName,outputNum);	// (ordering ???????????? insert)

		dao.updateStock(outputPname, outputNum);				// (stock ???????????? update)


		outDate.clear();
		outputPname.clear();
		shopName.clear();
		outputNum.clear();
	}


	// ArrayList -> DAO INSERT ??? ?????? ?????? 
	static void getOutputArrayData() {
		int rowsCount = table.getRowCount();
		int sum = 0;
		for (int i = 0; i < rowsCount; i++) {

			if(table.getValueAt(i, 7).toString().equals("")) {
				outputNum.add("0");
			} else {
				outputNum.add(i, table.getValueAt(i, 7).toString() ); 	// outputnum ????????????
			}

			try {
				//???????????? ???????????? outDate??? ??????
				Calendar t = Calendar.getInstance();

				StringBuffer now = new StringBuffer();
				StringBuffer now1 = new StringBuffer();

				now.append(t.get(Calendar.YEAR));
				now.append("-");
				now.append(t.get(Calendar.MONTH)+1);
				now.append("-");
				now.append(t.get(Calendar.DATE));

				now1.append(t.get(Calendar.HOUR_OF_DAY));
				now1.append(":");
				now1.append(t.get(Calendar.MINUTE));
				now1.append(":");
				now1.append(t.get(Calendar.SECOND));

				outDate.add(i, now.toString() + " " + now1.toString() ); 	// outdate ????????????

				outputPname.add(i, table.getValueAt(i, 3).toString() );	// pname ?????????
				shopName.add(i, table.getValueAt(i, 6).toString() );	// shop ?????????
				if( shopName.get(i).equals("????????? ??????????????????") || shopName.get(i).equals("") ) {
					JDialog jd = new JDialog(0);
					jd.Dlbl.setText("????????? ??????????????????.");
					jd.setTitle("?????????????????????");
					return;
				}
			} catch(NullPointerException e){
				System.err.println("table.getValueAt ??? null ?????????.");
				JDialog jd = new JDialog(0);
				jd.Dlbl.setText("????????? ??????.");
				jd.setTitle("????????????");
				return;
			}

			sum += Integer.parseInt(outputNum.get(i).toString());

		}
		System.out.println(sum);
		JDialog jd = new JDialog(0);
		jd.Dlbl.setText(sum + " ??? ?????????????????????");
		jd.setTitle("??????");

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
}