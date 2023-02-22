package lm.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lm.model.OrderDao;
import lm.model.OrderTable;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class LMProdOrder extends JFrame{

   private JFrame frame;
   private JTextField textField, textField_1, textField_2;
   private JTable table;
   private String selDate = "";
   private String inDate = "";
   
   public static OrderTable ot = new OrderTable();


   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               LMProdOrder window = new LMProdOrder();
               window.frame.setVisible(true);
               window.frame.setTitle("상품 주문 화면");
//               window.frame.setResizable(false);

            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   // this.setVisible(false) : 창 꺼야 할때 addActionListener 에 추가
   
   public LMProdOrder() {
	   initComponent();
   }

   private void initComponent() {
      frame = new JFrame();
      frame.setBounds(100, 100, 1000, 600);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
      
      // 거래처명
      JLabel lblNewLabel = new JLabel("거래처명 :");
//      lblNewLabel.setBounds(39, 20, 68, 15);
      lblNewLabel.setPreferredSize(new Dimension(80, 20));	// FlowLayout의 컴포넌트 리사이즈 방법
      frame.add(lblNewLabel);
      
      textField = new JTextField(500);
//      textField.setBounds(102, 17, 184, 20);
      textField.setPreferredSize(new Dimension(80, 20));	// FlowLayout의 컴포넌트 리사이즈 방법
      frame.add(textField);
      textField.setColumns(10);
      
      // 달력
      UtilDateModel model = new UtilDateModel();
      JDatePanelImpl datePanel = new JDatePanelImpl(model);
      JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
      datePicker.setPreferredSize(new Dimension(250, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
      frame.add(datePicker);
      
      LocalDate now = LocalDate.now();
      model.setDate(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth() );	// 오늘 날짜로 초기세팅
      model.setSelected(true);
      selDate = datePicker.getJFormattedTextField().getText();
      
      
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
	        jTableRefresh(); 	// 테이블 새로고침 메소드
		}

		private void jTableRefresh() {
			table.setModel(
					new DefaultTableModel( getDataList(),  getColumnList()  ) {

						@Override
						public boolean isCellEditable(int row, int column) {					
							return false;
						}
						
					}
				);  // jtable 새로운 데이터를 지정
				
				table.repaint();  // jtable을 새로 그린다
			
		}
	});
      
//      // 년
//            
//      LocalDateTime now = LocalDateTime.now();
//      
//      Vector years  = new Vector();
//      for (int i = now.getYear() - 10; i <= now.getYear(); i++) {	// 10년전부터 올해까지
//    	  years.add(i);
//      };
//      JComboBox comboBox = new JComboBox(years);
//      comboBox.setBounds(10, 40, 60, 20);
//      frame.getContentPane().add(comboBox);
//      
//      JLabel lblNewLabel_4 = new JLabel("년");
//      lblNewLabel_4.setBounds(73, 40, 20, 20);
//      frame.getContentPane().add(lblNewLabel_4);
//      
//      // 월
//      Vector months = new Vector();
//      for (int i = 1; i <= 12; i++) {	// 1월 ~ 12월
//    	  months.add(i);
//      };
//      JComboBox comboBox_1 = new JComboBox(months);
//      comboBox_1.setBounds(100, 40, 45, 20);
//      frame.getContentPane().add(comboBox_1);
//      
//      JLabel lblNewLabel_3 = new JLabel("월");
//      lblNewLabel_3.setBounds(147, 40, 20, 20);
//      frame.getContentPane().add(lblNewLabel_3);
//      
//      // 일
//      Calendar cal = Calendar.getInstance();
//      cal.set((int)comboBox.getSelectedItem(), (int)comboBox_1.getSelectedItem()-1, 1);
//      
//      Vector days = new Vector();
//      for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH) ; i++) {	// 1일 ~ 해당월 마지막일(안되고 있음)
//    	  days.add(i);
//      };
//      JComboBox comboBox_2 = new JComboBox(days);
//      comboBox_2.setBounds(175, 40, 45, 20);
//      frame.getContentPane().add(comboBox_2);
//      
//      JLabel lblNewLabel_3_1 = new JLabel("일");
//      lblNewLabel_3_1.setBounds(225, 40, 20, 20);
//      frame.getContentPane().add(lblNewLabel_3_1);
      
      // 검색
      JButton btnNewButton_1 = new JButton("검색하기");
      btnNewButton_1.setToolTipText("거래처명 입력, 주문일자 선택 후 검색");
      btnNewButton_1.setBounds(490, 40, 99, 30);
      btnNewButton_1.setPreferredSize(new Dimension(100, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
      frame.getContentPane().add(btnNewButton_1);
      
      // 주문일자
      JLabel lblNewLabel_1 = new JLabel("주문일자 :");
      lblNewLabel_1.setBounds(39, 45, 80, 20);
      frame.getContentPane().add(lblNewLabel_1);
      
      textField_1 = new JTextField();
      textField_1.setBounds(326, 45, 147, 20);
      frame.getContentPane().add(textField_1);
      textField_1.setColumns(10);
      
      // 입고일자
      JLabel lblNewLabel_2 = new JLabel("입고일자 :");
      lblNewLabel_2.setBounds(261, 45, 80, 20);
      frame.getContentPane().add(lblNewLabel_2);
      
      textField_2 = new JTextField();
      textField_2.setColumns(10);
      textField_2.setBounds(102, 45, 147, 20);
      frame.getContentPane().add(textField_2);
      
      // 테이블
	  table = new JTable(getDataList() , getColumnList() ) {

		@Override
		public boolean isCellEditable(int row, int column) {
			int  currColumn = table.getSelectedColumn();  // 선택한 열만 수정가능
				if( currColumn == 4  )
					return true;			
				return false;   // 모든 cell 편집불가능
		}
		  
	  };
//	  table.setBounds(42, 103, 496, 427);
	  JScrollPane sp = new JScrollPane(table); 
	  sp.setPreferredSize(new Dimension(930, 400));		// 테이블이 담긴 스크롤페인의 사이즈
	  frame.getContentPane().add( sp );
	  
	  
	  
	  // 주문
	  JButton btnNewButton = new JButton("주문하기");
	  btnNewButton.setToolTipText("주문수량 입력 후 클릭");
//	  btnNewButton.setBounds(636, 12, 99, 30);
	  btnNewButton.setPreferredSize(new Dimension(100, 30));	// FlowLayout의 컴포넌트 리사이즈 방법

	  frame.getContentPane().add(btnNewButton);
	  
	  btnNewButton.addActionListener( new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("주문버튼 클릭....");	
			
		}
	});
	  
	  // 주문내역 조회
	  JButton btnNewButton_3 = new JButton("주문내역 조회");
//	  btnNewButton_3.setBounds(636, 12, 99, 30);
	  btnNewButton_3.setPreferredSize(new Dimension(120, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
	  frame.getContentPane().add(btnNewButton_3);
	  
	  btnNewButton_3.addActionListener( new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("주문내역 조회 버튼 클릭....");	
			
		}
	});
	  
	  // 엑셀로저장
	  JButton btnNewButton_2 = new JButton("엑셀로 저장");
//	  btnNewButton_2.setSize(99, 30);
	  btnNewButton_2.setPreferredSize(new Dimension(120, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
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
			
			String  fmt      = "d:\\ws\\java\\DBProject02\\src\\";
			fmt             += "jTable_%4d%02d%02d%02d%02d%02d.xlsx";
			String  filepath = String.format(fmt, year, mm, dd, hh, mi, ss );
			
			System.out.println( filepath );
			excelWrite( filepath );
			
		}

	});
	  
   }
   
   // 테이블 getList
   private Vector<Vector> getDataList() {
	   OrderDao       dao   =  new OrderDao();
	   Vector<Vector>  list  =  dao.getOrderList();
	   
	   return list;
   }
   
   private Vector<String> getColumnList() {
	   Vector<String> cols = new Vector<String>();
	   cols.add("주문 일자");
	   cols.add("상품 코드");
	   cols.add("상품명");
	   cols.add("입고 가격");
	   cols.add("현재 재고");
	   cols.add("주문량");
	   cols.add("주문자");
	   
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