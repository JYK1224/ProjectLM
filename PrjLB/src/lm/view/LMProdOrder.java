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
	//               window.frame.setTitle("상품 주문 화면");
	//               window.frame.setResizable(false);


	// this.setVisible(false) : 창 꺼야 할때 addActionListener 에 추가

	public LMProdOrder(String userid) {

		this.userid = userid;
		initComponent();
	}

	private void initComponent() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LMProdOrder.class.getResource("/lmimage/alphabets-33744_640.png")));

		frame.setTitle("상품주문업무");

		icon = new ImageIcon(LMProdOrder.class.getResource("/lmimage/큰거1.png"));

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
		model.setDate(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth() );	// 오늘 날짜로 초기세팅
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




		// 달력
		lblNewLabel_3 = new JLabel("주문일자 지정 :");
		lblNewLabel_3.setFont(new Font("새굴림", Font.PLAIN, 12));
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


		// 주문일자
		lblNewLabel_1 = new JLabel("주문일자 :");
		lblNewLabel_1.setFont(new Font("새굴림", Font.PLAIN, 12));
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setBounds(193, 61, 56, 15);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setBackground(SystemColor.window);
		datePicker.setBackground(SystemColor.window);
		datePicker.setBounds(289, 23, 171, 30);
		panel.add(datePicker);
		datePicker.setPreferredSize(new Dimension(250, 30));

		// 거래처명
		JLabel lblNewLabel = new JLabel("거래처명 :");
		lblNewLabel.setFont(new Font("새굴림", Font.PLAIN, 12));
		lblNewLabel.setBounds(193, 121, 60, 20);
		panel.add(lblNewLabel);
		//      lblNewLabel.setBounds(39, 20, 68, 15);
		lblNewLabel.setPreferredSize(new Dimension(60, 20));

		// 입고일자
		lblNewLabel_2 = new JLabel("입고일자 :");
		lblNewLabel_2.setFont(new Font("새굴림", Font.PLAIN, 12));
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setBounds(193, 92, 56, 15);

		// 엑셀로저장
		btnNewButton_2 = new JButton("엑셀로 저장");
		btnNewButton_2.setIcon(new ImageIcon(LMProdOrder.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_2.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnNewButton_2.setBounds(843, 94, 103, 32);
		btnNewButton_2 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_2);
		//	  btnNewButton_2.setSize(99, 30);
		btnNewButton_2.setPreferredSize(new Dimension(100, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		btnNewButton_2.setToolTipText("d:/ws/java/DBProject02/src/jTable_20230220142558.xlsx");

		textField_2 = new JTextField(15);
		panel.add(textField_2);
		textField_2.setBounds(289, 88, 171, 21);


		// 주문
		btnNewButton = new JButton("\uC8FC\uBB38");
		btnNewButton.setIcon(new ImageIcon(LMProdOrder.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnNewButton.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnNewButton.setBounds(643, 94, 70, 32);
		btnNewButton .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton);
		btnNewButton.setToolTipText("주문수량 입력 후 클릭");
		//	  btnNewButton.setBounds(636, 12, 99, 30);
		btnNewButton.setPreferredSize(new Dimension(100, 30));


		// 검색하기
		btnNewButton_1 = new JButton("\uAC80\uC0C9");
		btnNewButton_1.setIcon(new ImageIcon(LMProdOrder.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnNewButton_1.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnNewButton_1 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_1);
		btnNewButton_1.setToolTipText("거래처명 입력 후 검색");
		btnNewButton_1.setBounds(474, 94, 70, 32);
		btnNewButton_1.setPreferredSize(new Dimension(200, 30));


		// 주문내역 조회
		btnNewButton_3 = new JButton("\uC8FC\uBB38\uB0B4\uC5ED\uC870\uD68C");
		btnNewButton_3.setIcon(new ImageIcon(LMProdOrder.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_3.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnNewButton_3.setBounds(725, 94, 106, 32);
		btnNewButton_3 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_3);
		//	  btnNewButton_3.setBounds(636, 12, 99, 30);
		btnNewButton_3.setPreferredSize(new Dimension(120, 30));

		// 테이블
		table = new JTable() {

			@Override
			public boolean isCellEditable(int row, int column) {
				int  currColumn = table.getSelectedColumn();  // 선택한 열만 수정가능
				if( currColumn == 6  )
					return true;			
				return false;   // 모든 cell 편집불가능
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
		lblNewLabel_4.setFont(new Font("새굴림", Font.BOLD, 40));
		lblNewLabel_4.setBounds(703, 10, 391, 79);
		panel.add(lblNewLabel_4);

		btnNewButton_3.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("주문내역 조회 버튼 클릭....");	
				if (odl != null)
					odl.setVisible(false);
				odl = new LMOrderList();
			}
		});

		btnNewButton_1.addActionListener(this);

		// 주문 액션
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.editCellAt(-1, -1);	// 마지막 cell의 입력을 완료되려면 셀선택을 테이블 밖으로 빼야함(입력 후 엔터치는 것과 같음)
				}catch(Exception ex) {}
				addList();
				JDialog jd = new JDialog(0);
				jd.Dlbl.setText( "주문되었습니다");
				jd.setTitle("주문완료");

			}
		});

		btnNewButton_2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("엑셀로 저장....");
				LocalDateTime  now   =  LocalDateTime.now(); 
				int            year  =  now.getYear();
				int            mm    =  now.getMonthValue();
				int            dd    =  now.getDayOfMonth();
				int            hh    =  now.getHour();
				int            mi    =  now.getMinute();
				int            ss    =  now.getSecond();

				String  fmt      = "D:\\excel\\";
				fmt             += "주문내역_%4d%02d%02d%02d%02d%02d.xlsx";
				String  filepath = String.format(fmt, year, mm, dd, hh, mi, ss );

				System.out.println( filepath );
				excelWrite( filepath );

			}

		});

		datePicker.addActionListener(new ActionListener() {		// 선택한 날짜 기준으로, 주문일자와 입고일자에 대입

			@Override
			public void actionPerformed(ActionEvent e) {
				selDate = datePicker.getJFormattedTextField().getText();

				// 선택된 날짜를 DATE 타입으로 저장
				Date selectedDate = (Date) datePicker.getModel().getValue();


				Calendar cal = Calendar.getInstance();
				cal.setTime(selectedDate);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String current = df.format(cal.getTime());
				//	        System.out.println("current: " + df.format(cal.getTime()));

				textField_1.setText(current);	

				cal.add(Calendar.DATE, +1);		// 입고예정일 = 주문일 + 1
				String after = df.format(cal.getTime());
				//	        System.out.println("after: " + df.format(cal.getTime()));

				textField_2.setText(after);	

				ot.setOrderDate(current);	// 날짜 갱신
				//				jTableRefresh(); 	// 테이블 새로고침 메소드
			}

			private void jTableRefresh() {
				table.setModel(
						new DefaultTableModel( getDataList(),  getColumnList()  ) {

							@Override
							public boolean isCellEditable(int row, int column) {					
								return false;
							}

						}
						);  // table 새로운 데이터를 지정

				table.repaint();  // table을 새로 그린다

			}
		});
		frame.getContentPane().setLayout(groupLayout);

		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 윈도우빌더라서 ㅅㅂ
		frame.setLocation(650,200);
		frame.setVisible(true);
		frame.setResizable(false);
		setResizable(false);

	}

	// 테이블 getDataList
	private Vector<Vector> getDataList() {
		OrderDao       dao   =  new OrderDao();
		Vector<Vector>  list  =  dao.getOrder();

		return list;
	}

	// 검색 후 테이블 getDataList
	private Vector<Vector> getDataList(LMProdOrder order3) {
		String          search = textField.getText();
		OrderDao       dao   =  new OrderDao();
		Vector<Vector>  list  =  dao.getOrder(search, userid);

		return list;
	}

	// 테이블 getColumnList
	private Vector<String> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("거래처명");
		cols.add("주문 일자");
		cols.add("상품 코드");
		cols.add("상품명");
		cols.add("입고 가격");
		cols.add("현재 재고");
		cols.add("주문 수량");
		cols.add("사원 번호");

		return cols;
	}



	// 엑셀 저장형식
	private void excelWrite(String filepath) {
		XSSFWorkbook  workbook =  new XSSFWorkbook();
		XSSFSheet     sheet    =  workbook.createSheet("Data");

		// data 저장 : swing jTable -> Excel Sheet
		getWorkbook_Data( sheet );

		// 파일 저장
		FileOutputStream  fos = null;
		try {
			fos = new FileOutputStream( filepath );
			workbook.write(fos);
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText("D:\\excel 로 저장에 성공하였습니다");
			jd.setTitle("저장성공");
		} catch (IOException e) {
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText("<html><body><center>저장을 실패하였습니다."
					+ "<br>저장공간의 위치를 확인해주세요."
					+ "<br>저장공간: D:\\excel</center></body></html>");
			jd.setTitle("저장실패");	
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

		// 제목줄 처리
		Vector<String>  cols =  getColumnList();
		row          =  sheet.createRow( 0 );
		for (int i = 0; i < cols.size(); i++) {
			cell     =  row.createCell(i);
			cell.setCellValue(  cols.get(i) );    
		}

		//데이터 처리
		for (int i = 0; i < numrows; i++) {
			row    =  sheet.createRow(i+1);
			for (int j = 0; j < numcols ; j++) {
				cell = row.createCell(j);
				cell.setCellValue((String) table.getValueAt(i, j));
			}
		}
	} // 엑셀 끝

	@Override
	public void actionPerformed(ActionEvent e) {

		switch( e.getActionCommand() ) {
		case "검색":
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

	// ArrayList -> DAO INSERT 에 활용 예정 
	static void getOrderArrayData() {
		int rowsCount = table.getRowCount();
		for (int i = 0; i < rowsCount; i++) {

			try {
				orderDname.add(i, table.getValueAt(i, 0).toString() );	// dname 거래처명
				orderDate.add(i, table.getValueAt(i, 1).toString() ); 	// orderdate 주문일자
				if( orderDate.get(i).equals("") ) {
					JDialog jd = new JDialog(0);
					jd.Dlbl.setText("주문일자를 지정해주세요.");
					jd.setTitle("날짜지정오류");
					return;
				}
				orderPname.add(i, table.getValueAt(i, 3).toString() );	// pname 상품명
			}catch (NullPointerException e) {
				System.err.println("table.getValueAt 이 null 입니다.");
				JDialog jd = new JDialog(0);
				jd.Dlbl.setText("빈칸이 있음.");
				jd.setTitle("빈칸 입력");
				return;
			}
			orderNum.add(i, table.getValueAt(i, 6).toString() ); 	// ordernum 주문수량
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