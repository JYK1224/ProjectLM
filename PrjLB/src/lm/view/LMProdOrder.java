package lm.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
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

public class LMProdOrder extends JFrame implements ActionListener{

	// Fields
	private String userid;
	private static JFrame frame;
	private static JTextField textField, textField_1, textField_2;
	private static String selDate = "";
	private static JTable table;
	private static JButton btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_3;
	private JLabel lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel_4;

	private static ArrayList<Object> orderNum = new ArrayList<Object>();
	private static ArrayList<Object> orderDate = new ArrayList<Object>();
	private static ArrayList<Object> orderDname = new ArrayList<Object>();
	private static ArrayList<Object> orderPname = new ArrayList<Object>(); 

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

		frame.setTitle("상품 주문 화면");
		frame.setBounds(100, 100, 1000, 600);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));




		// 달력
		lblNewLabel_3 = new JLabel("주문일자 지정 :");
		lblNewLabel_3.setBounds(39, 45, 80, 20);
		frame.getContentPane().add(lblNewLabel_3);

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setPreferredSize(new Dimension(250, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		frame.add(datePicker);

		LocalDate now = LocalDate.now();
		model.setDate(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth() );	// 오늘 날짜로 초기세팅
		model.setSelected(true);

		datePicker.addActionListener(new ActionListener() {		// 선택한 날짜 기준으로, 주문일자와 입고일자에 대입

			@Override
			public void actionPerformed(ActionEvent e) {
				selDate = datePicker.getJFormattedTextField().getText();

				// 선택된 날짜를 DATE 타입으로 저장
				Date selectedDate = (Date) datePicker.getModel().getValue();


				Calendar cal = Calendar.getInstance();
				cal.setTime(selectedDate);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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


		// 주문일자
		lblNewLabel_1 = new JLabel("주문일자 :");
		lblNewLabel_1.setBounds(39, 45, 80, 20);
		frame.getContentPane().add(lblNewLabel_1);

		textField_1 = new JTextField(15);
		textField_1.setBounds(326, 45, 147, 20);
		frame.getContentPane().add(textField_1);

		// 입고일자
		lblNewLabel_2 = new JLabel("입고일자 :");
		lblNewLabel_2.setBounds(261, 45, 80, 20);
		frame.getContentPane().add(lblNewLabel_2);

		textField_2 = new JTextField(15);
		textField_2.setBounds(102, 45, 147, 20);
		frame.getContentPane().add(textField_2);

		// 여백 라벨
		lblNewLabel_4 = new JLabel("                             "
				+ "                                                       ");
		lblNewLabel_4.setBounds(39, 45, 80, 20);
		frame.getContentPane().add(lblNewLabel_4);

		// 거래처명
		JLabel lblNewLabel = new JLabel("거래처명 :");
		//      lblNewLabel.setBounds(39, 20, 68, 15);
		lblNewLabel.setPreferredSize(new Dimension(60, 20));	// FlowLayout의 컴포넌트 리사이즈 방법
		frame.add(lblNewLabel);

		textField = new JTextField(20);
		//      textField.setBounds(102, 17, 184, 20);
		textField.setPreferredSize(new Dimension(80, 20));	// FlowLayout의 컴포넌트 리사이즈 방법
		frame.add(textField);


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


		// 검색하기
		btnNewButton_1 = new JButton("주문일자지정 & 검색하기");
		btnNewButton_1.setToolTipText("거래처명 입력 후 검색");
		btnNewButton_1.setBounds(490, 40, 99, 30);
		btnNewButton_1.setPreferredSize(new Dimension(200, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		frame.getContentPane().add(btnNewButton_1);

		btnNewButton_1.addActionListener(this);

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
		sp.setPreferredSize(new Dimension(970, 400));		// 테이블이 담긴 스크롤페인의 사이즈
		frame.getContentPane().add( sp );


		// 주문
		btnNewButton = new JButton("주문하기");
		btnNewButton.setToolTipText("주문수량 입력 후 클릭");
		//	  btnNewButton.setBounds(636, 12, 99, 30);
		btnNewButton.setPreferredSize(new Dimension(100, 30));	// FlowLayout의 컴포넌트 리사이즈 방법

		frame.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.editCellAt(-1, -1);	// 마지막 cell의 입력을 완료되려면 셀선택을 테이블 밖으로 빼야함(입력 후 엔터치는 것과 같음)
				}catch(Exception ex) {}
				addList();

			}
		});


		// 주문내역 조회
		btnNewButton_3 = new JButton("주문내역 조회");
		//	  btnNewButton_3.setBounds(636, 12, 99, 30);
		btnNewButton_3.setPreferredSize(new Dimension(120, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		frame.getContentPane().add(btnNewButton_3);

		btnNewButton_3.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("주문내역 조회 버튼 클릭....");	
				if (odl != null)
					odl.getFrame().setVisible(false);
				odl = new LMOrderList();
			}
		});

		// 엑셀로저장
		btnNewButton_2 = new JButton("엑셀로 저장");
		//	  btnNewButton_2.setSize(99, 30);
		btnNewButton_2.setPreferredSize(new Dimension(100, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		btnNewButton_2.setToolTipText("d:/ws/java/DBProject02/src/jTable_20230220142558.xlsx");
		frame.getContentPane().add(btnNewButton_2);

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

		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 윈도우빌더라서 ㅅㅂ
		frame.setVisible(true);
		frame.setResizable(false);

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
		cols.add("주문 직원");

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
			JOptionPane.showMessageDialog(null, "저장에 성공하였습니다");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "저장을 실패하였습니다. 저장공간의 위치를 확인해주세요. \n"
			+"저장공간: "  +  "D:\\excel");		
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
		case "주문일자지정 & 검색하기":
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
				orderPname.add(i, table.getValueAt(i, 3).toString() );	// pname 상품명
				orderNum.add(i, table.getValueAt(i, 6).toString() ); 	// ordernum 주문수량
			}catch (NullPointerException e) {
				System.err.println("table.getValueAt 이 null 입니다.");
				JOptionPane.showMessageDialog(null, 
						"주문일자를 지정해주세요.", "날짜지정오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
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


}