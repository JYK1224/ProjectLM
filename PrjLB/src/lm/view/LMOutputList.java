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
import javax.swing.table.TableColumn;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class LMOutputList implements ActionListener {

	private static JFrame frame;
	private static JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel_4, lblNewLabel_5, lblNewLabel_6, lblNewLabel_7;
	private static JTextField textField, textField_1, textField_2, textField_3;
	private static JButton btnNewButton_1, btnNewButton_2, btnNewButton_3;
	private static JTable table;
	private static JScrollPane scrollPane;
	
	private static String startDate;
	private static String endDate;
	private static Date selectedDate1, selectedDate2;
	private static String date1 = "";
	private static String date2 = "";
	
	LMOutput lmoutput = null;
	
	// 기본생성자
	public LMOutputList(){
		initComponent();
	}

	//인자있는 생성자
	public LMOutputList(LMOutput lmoutput) {
		this();  // 기본생성자 호출		
		this.lmoutput = lmoutput;
	}

	private void initComponent() {
		setFrame(new JFrame());
		
		getFrame().setTitle("출고 내역 조회");
		getFrame().setBounds(700, 300, 1100, 600);
		getFrame().getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));	

		// 시작일 달력
		lblNewLabel_1 = new JLabel("시작일 선택 :");
		lblNewLabel_1.setBounds(39, 45, 80, 20);
		getFrame().getContentPane().add(lblNewLabel_1);

		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.setPreferredSize(new Dimension(150, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		getFrame().getContentPane().add(datePicker1);

		LocalDate now = LocalDate.now();
		model1.setDate(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth() );	// 오늘 날짜로 초기세팅
		model1.setSelected(true);

		datePicker1.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// datePicker 에서 선택한 날짜 추출
				startDate = datePicker1.getJFormattedTextField().getText();
				
				// 선택된 시작날짜를 DATE 타입으로 저장 (날짜 비교에 활용함)
				selectedDate1 = (Date) datePicker1.getModel().getValue();
				System.out.println(selectedDate1);
				
				
				// SQL 넣기위해 String 으로 타입변경
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
				if(date1.equals(today)) {	// 시작일이 오늘이라면 종료일도 오늘로 지정
					textField_2.setText(start);
					date2 = date1;
				}
			}
		});

		// 시작일자
		lblNewLabel_2 = new JLabel("시작일자 :");
		lblNewLabel_2.setBounds(39, 45, 80, 20);
		getFrame().getContentPane().add(lblNewLabel_2);

		textField_1 = new JTextField(13);
		textField_1.setBounds(326, 45, 147, 20);
		getFrame().getContentPane().add(textField_1);

		// 여백 라벨
		lblNewLabel_5 = new JLabel("                         ");
		lblNewLabel_5.setBounds(39, 45, 80, 20);
		getFrame().getContentPane().add(lblNewLabel_5);

		// 종료일 달력
		lblNewLabel_3 = new JLabel("종료일 선택 :");
		lblNewLabel_3.setBounds(39, 45, 80, 20);
		getFrame().getContentPane().add(lblNewLabel_3);

		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.setPreferredSize(new Dimension(150, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		getFrame().getContentPane().add(datePicker2);

		model2.setDate(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth() );	// 오늘 날짜로 초기세팅
		model2.setSelected(true);

		datePicker2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				endDate = datePicker2.getJFormattedTextField().getText();

				// 선택된 종료날짜를 DATE 타입으로 저장
				selectedDate2 = (Date) datePicker2.getModel().getValue();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println("ㅇㅇ"+date2);
				date2 = simpleDateFormat.format(selectedDate2);
				System.out.println(date2);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(selectedDate2);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String end = df.format(cal.getTime());

				textField_2.setText(end);

				if(selectedDate1.compareTo(selectedDate2) > 0) {
					JOptionPane.showMessageDialog(null, 
							"종료일이 시작일보다 빠릅니다.", "날짜지정오류", JOptionPane.ERROR_MESSAGE);
					textField_2.setText("");
				}

			}
		});


		// 종료일자
		lblNewLabel_4 = new JLabel("종료일자 :");
		lblNewLabel_4.setBounds(261, 45, 80, 20);
		getFrame().getContentPane().add(lblNewLabel_4);

		textField_2 = new JTextField(13);
		textField_2.setBounds(102, 45, 147, 20);
		getFrame().getContentPane().add(textField_2);

		// 날짜 초기화
		btnNewButton_3 = new JButton("날짜 초기화");
		getFrame().getContentPane().add(btnNewButton_3);
		
		btnNewButton_3.setPreferredSize(new Dimension(100, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		getFrame().getContentPane().add(btnNewButton_3);

		btnNewButton_3.addActionListener(this);
		
		
		
		// 여백 라벨
		lblNewLabel_6 = new JLabel("                         ");
		lblNewLabel_6.setBounds(39, 45, 80, 20);
		getFrame().getContentPane().add(lblNewLabel_6);

		
		// 거래처명
		lblNewLabel = new JLabel("거래처명 :");
		lblNewLabel.setPreferredSize(new Dimension(60, 20));	// FlowLayout의 컴포넌트 리사이즈 방법
		getFrame().add(lblNewLabel);

		textField = new JTextField(20);
		textField.setPreferredSize(new Dimension(80, 20));	// FlowLayout의 컴포넌트 리사이즈 방법
		getFrame().add(textField);
		
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
		btnNewButton_1 = new JButton("검색하기");
		btnNewButton_1.setToolTipText("거래처명 입력 후 검색");
//		btnNewButton_1.setBounds(490, 40, 99, 30);
		btnNewButton_1.setPreferredSize(new Dimension(100, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		getFrame().getContentPane().add(btnNewButton_1);

		btnNewButton_1.addActionListener(this);


		// 엑셀로저장
		btnNewButton_2 = new JButton("엑셀로 저장");
		btnNewButton_2.setPreferredSize(new Dimension(100, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		btnNewButton_2.setToolTipText("d:/ws/java/DBProject02/src/jTable_20230220142558.xlsx");
		getFrame().getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			
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

				String  fmt      = "d:\\ws\\java\\DBProject02\\src\\";
				fmt             += "jTable_%4d%02d%02d%02d%02d%02d.xlsx";
				String  filepath = String.format(fmt, year, mm, dd, hh, mi, ss );

				System.out.println( filepath );
				excelWrite( filepath );
				
			}
		});

		getFrame().setVisible(true);
		getFrame().setResizable(false);

		// 테이블
		scrollPane = new JScrollPane();
		getFrame().getContentPane().add(scrollPane);

		table = new JTable(){

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;   // 모든 cell 편집불가능
			}

		};
		scrollPane.setPreferredSize(new Dimension(1070, 400));	
		scrollPane.setViewportView(table);
		 
		
		

		// 총 출고가격
		lblNewLabel_7 = new JLabel("총 출고가격 :");
		lblNewLabel_7.setPreferredSize(new Dimension(80, 20));	// FlowLayout의 컴포넌트 리사이즈 방법
		getFrame().add(lblNewLabel_7);

		textField_3 = new JTextField(15);
		textField_3.setPreferredSize(new Dimension(80, 20));	// FlowLayout의 컴포넌트 리사이즈 방법
		getFrame().add(textField_3);
		textField_3.setText( String.valueOf(getSumPrice()) );	// 가격 * 수량
		
		getFrame().setVisible(true);
		getFrame().setResizable(false);

	}
	
	// 엑셀 저장
	protected void excelWrite(String filepath) {
		XSSFWorkbook  workbook =  new XSSFWorkbook();
		XSSFSheet     sheet    =  workbook.createSheet("Data");

		// data 저장 : swing jTable -> Excel Sheet
		getWorkbook_Data( sheet );

		// 파일 저장
		FileOutputStream  fos = null;
		try {
			fos = new FileOutputStream( filepath );
			workbook.write(fos);
			System.out.println("저장완료");
		} catch (IOException e) {
			System.out.println("저장Fail");			
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
		
		//제목줄 처리
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
		
	}

	// 테이블 getDateList
//	private Vector<Vector> getDataList() {
//		OutputDao       dao   =  new OutputDao();
//		Vector<Vector>  list  =  dao.getOutputList();
//
//		return list;
//	}

	// 검색 후 테이블 getList
	private Vector<Vector> getDataList(OutputList OrderList) {
		String          search = textField.getText();
		OutputDao        dao   =  new OutputDao();
		Vector<Vector>  list  =  dao.getOutputList(search, date1, date2);

		return list;
	}

	// 테이블 getColumnList
	private Vector<String> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("출고 일자");
		cols.add("거래처명");
		cols.add("상품 코드");
		cols.add("상품명");
		cols.add("출고 가격");
		cols.add("현재 재고");
		cols.add("점포명");
		cols.add("출고 수량");
		cols.add("사원 번호");

		return cols;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
		case "검색하기":
			
			if(date1.equals("") || date2.equals("")) {
				JOptionPane.showMessageDialog(null, 
						"시작일 또는 종료일이 선택되지 않았습니다.", "날짜지정오류", JOptionPane.ERROR_MESSAGE);
				textField_1.setText("");
				textField_2.setText("");
				return;
			}
			
			Vector<Vector> list = getDataList(this);
			jTableRefresh2(list);
			textField_3.setText( String.valueOf(getSumPrice()) );	// 가격 * 수량
			break;
			
		case "날짜 초기화":
			textField_1.setText("");
			textField_2.setText("");
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
	
	public static int getSumPrice(){	// 테이블의 상품가격합계
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

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		OutputList.frame = frame;
	}

	//	public static void main(String[] args) {
	//	OrderList window = new OrderList();
	//}



}
