package lm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lm.model.IpgoDao;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


public class IpgoList extends JFrame implements  ActionListener{

	static IpgoList    iList = null;
	LMipgo  lmList = null;


	JLabel        lblAcc, lblMonth, lblStart, lblEnd, lblTot; // 거래처명, 월, 일
	private static TextField     txtId, txtTot; // 검색할 항목, 총 금액
	JButton       btnFind, btnToExcel; // 인쇄(엑셀로 보내기)
	JPanel        topPane, botPane;
	private static JTable        jTable;
	private static JScrollPane   pane;
	
	private static String startDate;
	private static String endDate;
	private static Date selectedDate1, selectedDate2;
	private static String date1 = "";
	private static String date2 = "";
	
	LMipgo lml = null;

	public IpgoList() {   
		init();
	}

	//인자있는 생성자
	public IpgoList(LMipgo lmList) {
		this();  // 기본생성자 호출		
		//initComponent();
		this.lmList = lmList;
	}

	// 인자있는 생성자 : 입고수량을 전달받는다
	public IpgoList( String innum, LMipgo  lmList ) {
		this();
		this.lmList = lmList;

		// 넘어온 입고수량을 txtId 에 넣고 find 버튼 클릭하면
		txtId.setText( innum );
		btnFind.doClick();
	}

	private void init() {

		setTitle("상품 입고내역 조회");

		topPane      =  new JPanel();
		lblAcc       =  new JLabel("거래처명: ");
		lblTot       =  new JLabel("                                        "
				+ "    총 입고가격: ");
		txtId        =  new TextField(20);  
		txtTot       =  new TextField(20);  
		btnFind      =  new JButton("검색");      
		btnToExcel   =  new JButton("엑셀로 저장");
		lblStart     =  new JLabel("     기간 시작날짜: ");
		lblEnd       =  new JLabel("     기간 종료날짜: ");
		botPane      =  new JPanel();


		topPane.add( lblAcc );
		topPane.add( txtId );
		topPane.add( lblStart );

		// 시작일 달력
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.setPreferredSize(new Dimension(150, 30));
		topPane.add(datePicker1);
		
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
				
				System.out.println(date1);
				
			}
		});
		
		topPane.add( lblEnd );
		
		// 종료일 달력
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.setPreferredSize(new Dimension(150, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		topPane.add(datePicker2);


		datePicker2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				endDate = datePicker2.getJFormattedTextField().getText();

				// 선택된 종료날짜를 DATE 타입으로 저장
				selectedDate2 = (Date) datePicker2.getModel().getValue();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				date2 = simpleDateFormat.format(selectedDate2);
				
				if(selectedDate1.compareTo(selectedDate2) > 0) {
					JOptionPane.showMessageDialog(null, 
							"종료일이 시작일보다 빠릅니다.", "날짜지정오류", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		

		topPane.add( btnFind );
		topPane.add( btnToExcel );
		botPane.add(lblTot);
		botPane.add(txtTot);



		// 버튼에 기능 부여
		// 검색버튼 클릭
		btnFind.addActionListener( this );
		// 이벤트핸들러(이벤트발생시 수행할 함수 - actionPreformed() )를 등록
		// 엑셀로 저장 버튼 클릭
		btnToExcel.addActionListener( this );


		// -----------------------      
		jTable      =   new  JTable(){

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;   // 모든 cell 편집불가능
			}

		};
		
		txtTot.setText( String.valueOf(getSumPrice()) );	// 가격 * 수량


		pane  = new JScrollPane( jTable );
		this.add( pane );
		
		getContentPane().add(topPane, BorderLayout.NORTH);
		getContentPane().add(botPane, BorderLayout.SOUTH);
		setSize(1100, 700); // 창크기
		setLocation(200, 200);
		setVisible(true); // 화면에 보이게
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // x버튼 눌렀을때 메모리에서 제거 부탁하는 명령   
	}


	
	// data 목록

	private Vector<Vector> getDataList() {
		IpgoDao       dao   =  new IpgoDao();
		Vector<Vector>  list  =  dao.getIpgoList();
		
		return  list;
	}

	private Vector<String> getColumnList() {
		Vector<String>  cols = new Vector<>();  // 문자배열 대신 사용
		cols.add("입고일자");
		cols.add("거래처명");
		cols.add("상품코드");
		cols.add("상품명");
		cols.add("입고 가격");
		cols.add("현재 재고");
		cols.add("입고 수량");
		cols.add("입고 직원");
		return  cols;
	}



	public static void main(String[] args) {
		IpgoList    iList =  new IpgoList();

	}

	//---------------------------------------------------

	// implements ActionListener 를 위한 구현
	// 버튼들의 이벤트 처리

	@Override
	public void actionPerformed(ActionEvent e) {

		switch( e.getActionCommand() ) {  // 눌러진 버튼의 글자
		case "검색":
			
			if(date1.equals("") || date2.equals("")) {
				JOptionPane.showMessageDialog(null, 
						"시작일 또는 종료일이 선택되지 않았습니다.", "날짜지정오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Vector<Vector> list = getDataList(this);
			jTableRefresh2(list);
			txtTot.setText( String.valueOf(getSumPrice()) );	// 가격 * 수량
			
			break;
		case "엑셀로 저장":
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

			break;

		}       
	}





	private Vector<Vector> getDataList(IpgoList ipgoList) {
		String          search = txtId.getText();
		IpgoDao        dao   =  new IpgoDao();
		Vector<Vector>  list  =  dao.getInputList(search, date1, date2);

		return list;
	}

	private void jTableRefresh2(Vector<Vector> list) {

		jTable.setModel(new DefaultTableModel(list, getColumnList()) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return super.isCellEditable(row, column);
			}
		});

		jTable.repaint();   

	}
	
	private static int getSumPrice() {
		int rowsCount = jTable.getRowCount();
		int sum = 0;
		for(int i = 0; i < rowsCount; i++){
			int pri = 0;
			if (jTable.getValueAt(i, 6) != null) {
				pri = Integer.parseInt(jTable.getValueAt(i, 4).toString());
			}
			
			int su  = 0;
			if (jTable.getValueAt(i, 6) != null) {
				su = Integer.parseInt(jTable.getValueAt(i, 6).toString());
			}
			
			sum = sum + (pri * su);
		}
		System.out.println(sum);
		return sum;
	}
	

	//-----------------------------------------------------
	// excel 로 저장
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

		// 제목줄 처리
		Vector<String>  cols =  getColumnList();
		row          =  sheet.createRow( 0 );
		for (int i = 0; i < cols.size(); i++) {
			cell     =  row.createCell(i);
			cell.setCellValue(  cols.get(i) );    
		}

		// Data 줄 처리
		Vector< Vector >  dataList = getDataList();
		for (int i = 0; i < dataList.size(); i++) {
			row    =   sheet.createRow(i + 1);
			for (int j = 0; j < dataList.get(i).size() ; j++) {
				Vector  v  =  dataList.get(i);
				cell       =  row.createCell( j );
				cell.setCellValue( (String) v.get( j ) );
			}

		}
	}




}
