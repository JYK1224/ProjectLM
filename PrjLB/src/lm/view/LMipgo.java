package lm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import lm.model.IpgoVo;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



public class LMipgo extends JFrame 
implements  ActionListener  {

	// 필드
   static LMipgo    mList = null;
   IpgoList         iProc = null;
   
   JLabel        lblAcc, lblMonth, lblDay; // 거래처명, 월, 일
   TextField     txtId; // 검색할 항목
   JButton       btnFind, btnSet, btnToExcel, btnList; //검수확정, 인쇄(엑셀로 보내기)
   JPanel        topPane, botPane;
   JTable        jTable;
   JScrollPane   pane;
   
   private static String selDate = "";
   public static IpgoVo lmvo = new IpgoVo();
   
   public static ArrayList<Object> inDate = new ArrayList<Object>();
   public static ArrayList<Object> inPname = new ArrayList<Object>();
   public static ArrayList<Object> inNum = new ArrayList<Object>();
   
   
   
   
   
   
   
   
   public LMipgo() {   
      init();
   }
   
private void init() {
   
   setTitle("상품입고");
   
   topPane      =  new JPanel();
   lblAcc       =  new JLabel("거래처명: ");
   txtId        =  new TextField(30);  
   btnFind      =  new JButton("검색");    
   btnSet       =  new JButton("입고확정");    
   btnToExcel   =  new JButton("엑셀로 저장");
   btnList      =  new JButton("입고내역 조회");
   lblDay       =  new JLabel("        입고 일자 선택: ");
   botPane      =  new JPanel();

  

   
   topPane.add( lblAcc );
   topPane.add( txtId );
   topPane.add( lblDay );
   
   // 달력
    UtilDateModel model = new UtilDateModel();
   JDatePanelImpl datePanel = new JDatePanelImpl(model);
   JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
   datePicker.setPreferredSize(new Dimension(250, 30));
   
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
			lmvo.setIndate(current);	// 날짜 갱신
			//				jTableRefresh(); 	// 테이블 새로고침 메소드
			
			System.out.println(current);


			cal.add(Calendar.DATE, +1);		// 입고예정일 = 주문일 + 1
			String after = df.format(cal.getTime());
			
			//어제 
			Calendar cal2 = Calendar.getInstance();
			cal2.add(Calendar.DATE, -1);	
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			String yesterday = df2.format(cal2.getTime());
			
			lmvo.setOrderdate(yesterday);
			//	        System.out.println("after: " + df.format(cal.getTime()));


			
			
		}
});
   
 

   topPane.add(datePicker);
   topPane.add( btnFind );
   topPane.add( btnSet );
   topPane.add( btnToExcel );
   botPane.add(btnList);

   
   // 버튼에 기능 부여
   // 검색버튼 클릭
   btnFind.addActionListener( this );
   //검수확정버튼 클릭
   btnSet.addActionListener( this );
      // 이벤트핸들러(이벤트발생시 수행할 함수 - actionPreformed() )를 등록
   // 엑셀로 저장 버튼 클릭
   btnToExcel.addActionListener( this );
   // 입고내역 조회 버튼 클릭
   btnList.addActionListener( this );
// 버튼에 기능 부여
		// 회원가입버튼 클릭
   btnList.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 입고내역 조회(ipgolist)을 연다
				System.out.println("입고내역 조회 클릭");
				if(  iProc != null )
					iProc.dispose();  // 강제로 닫는다
				System.out.println("this:" + this);
				System.out.println("mList:" + mList);
				iProc = new IpgoList( mList );				
			}
		});
   
   
   
      // -----------------------------------------------------------------------------      
      jTable      =   new  JTable() {
    	  @Override
			public boolean isCellEditable(int row, int column) {
				int  currColumn = jTable.getSelectedColumn();  // 선택한 열만 수정가능
				if( currColumn == 7  )
					return true;			
				return false;   // 모든 cell 편집불가능
			}
      };      
      
      
      
         pane  = new JScrollPane( jTable );
         getContentPane().add( pane );
         
            getContentPane().add(topPane, BorderLayout.NORTH);
            getContentPane().add(botPane, BorderLayout.SOUTH);
            setSize(1200, 700); // 창크기
            setLocation(200, 200);
            setVisible(true); // 화면에 보이게
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // x버튼 눌렀을때 메모리에서 제거 부탁하는 명령   
}


         // data 목록
         //  <? extends Vector> : Vector 를 상속받은 Type 만 가능하다
         // Vector<? extends Vector> : Vector안에 Vector 타입만 저장가능 
         //  - 2차원배열 : ResultSet을 받기위해 
         //  Vector< Vector > : 안쪽   Vector : ResultSet 의 한 Row - Record
         //                   : 바깥쪽 Vector : ResultSet 전체  Table - Record 배열
         private Vector<Vector> getDataList() {
        	 IpgoDao       dao   =  new IpgoDao();
            Vector<Vector>  list  =  dao.getLmList();
            return  list;
         }

         // jTable 에 제목줄 생성 - 크기변경가능한 배열 : ArrayList -> Vector(swing)
         // ? : class Type 
         private Vector<String> getColumnList() {
            Vector<String>  cols = new Vector<>();  // 문자배열 대신 사용
            cols.add("거래처명");
            cols.add("주문일자");
            cols.add("입고일");
            cols.add("상품코드");
            cols.add("상품명");
            cols.add("현재 재고");
            cols.add("주문 수량");
            cols.add("입고 수량");
            cols.add("사원 번호");
            return  cols;
         }
         
         
   public static void main(String[] args) {
   LMipgo mlist = new LMipgo();

   }

//-------------------------------------------------------------------

   // implements ActionListener 를 위한 구현
   // 버튼들의 이벤트 처리
   
   @Override
   public void actionPerformed(ActionEvent e) {
      
      switch( e.getActionCommand() ) {  // 눌러진 버튼의 글자
      case "검색":
    	  Vector<Vector> list = getDataList(this);
			jTableRefresh2(list);
			break;
      case "입고확정":
         // 새로고침 클릭
         System.out.println("입고확정 클릭");
         
         
         try {
				jTable.editCellAt(-1, -1);	// 마지막 cell의 입력을 완료되려면 셀선택을 테이블 밖으로 빼야함(입력 후 엔터치는 것과 같음)
			}catch(Exception ex) {}
			addList();
			
	//-------------------------------------------------
	    // STOCK테이블에 UPDATE해주기
		// 입고예정일을 주문일 +1로 바꿔주기
			
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
         
         String  fmt      = "D:\\ws\\excelSave\\";
         fmt             += "jTable_%4d%02d%02d%02d%02d%02d.xlsx";
         String  filepath = String.format(fmt, year, mm, dd, hh, mi, ss );
         
         System.out.println( filepath );
          excelWrite( filepath );
         
         break;
      case "입고내역 조회":
    	  System.out.println("입고내역 조회 클릭");
			if(  iProc != null )
				iProc.dispose();  // 강제로 닫는다
			iProc = new IpgoList( this );   // this : 현재 실행중인 MemberList        
          break;
      }
         
   }
  
   
   private Vector<Vector> getDataList(LMipgo lmList) {
	    String         search =  txtId.getText();
	    IpgoDao          dao    =  new IpgoDao();
		Vector<Vector> list   =  dao.getOrder(search);

		return list;
}

   
private void addList() {
	   getInArrayData();
	   IpgoDao dao = new IpgoDao();		
		
		dao.insertList(inDate,inPname,inNum);	
		System.out.println(inDate);
		System.out.println(inPname);
		System.out.println(inNum);
		
		dao.updateMember(inPname,inNum);
		
		
		inDate.clear();
		inPname.clear();
		inNum.clear();
}

   
private void getInArrayData() {
	int rowsCount = jTable.getRowCount();
	for (int i = 0; i < rowsCount; i++) {
		
		try {
			inDate.add(i, jTable.getValueAt(i, 2).toString() );  //  입고예정일
			inPname.add(i, jTable.getValueAt(i, 4).toString() ); //  상품명
			inNum.add(i, jTable.getValueAt(i, 7).toString() ); 	 //  입고수량
			
		} catch(NullPointerException e) {
            System.err.println("table.getValueAt 이 null 입니다.");
            JOptionPane.showMessageDialog(null, 
                  "입고일자를 지정해주세요.", "날짜지정오류", JOptionPane.ERROR_MESSAGE);
            return;
         }

	}
}


   public void jTableRefresh() {
      jTable.setModel(
            new DefaultTableModel( getDataList(),  getColumnList()  ) {

               @Override
               public boolean isCellEditable(int row, int column) {               
                  return false;
               }
               
            }
         );  // jtable 새로운 데이터를 지정
         
         jTable.repaint();  // jtable을 새로 그린다
      
   }
   
   // 검색이후에 
   public void jTableRefresh2(Vector<Vector> list) {
	      jTable.setModel(
	            new DefaultTableModel( list ,  getColumnList()  ) {

	               @Override
	               public boolean isCellEditable(int row, int column) {               
	                  return false;
	               }
	               
	            }
	         );  // jtable 새로운 데이터를 지정
	         
	         jTable.repaint();  // jtable을 새로 그린다
	      
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