package lm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import lm.model.IpgoDao;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;
import java.awt.event.ActionEvent;


public class IpgoList extends JFrame implements  ActionListener, MouseListener{

	static IpgoList    iList = null;
	LMipgo  lmList = null;
	
	
	   JLabel        lblAcc, lblMonth, lblStart, lblEnd, lblTot; // 거래처명, 월, 일
	   TextField     txtId, txtTot; // 검색할 항목
	   JButton       btnFind, btnSet, btnToExcel; //검수확정, 인쇄(엑셀로 보내기)
	   JPanel        topPane, botPane;
	   JTable        jTable;
	   JScrollPane   pane;
	   
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
	   btnSet       =  new JButton("검수확정");    
	   btnToExcel   =  new JButton("인쇄");
	   lblStart     =  new JLabel("     기간 시작날짜: ");
	   lblEnd       =  new JLabel("     기간 종료날짜: ");
	   botPane      =  new JPanel();

	  

	   
	   topPane.add( lblAcc );
	   topPane.add( txtId );
	   topPane.add( lblStart );
	   
	   UtilDateModel model2 = new UtilDateModel();
	   JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
	   JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
	   datePicker2.setPreferredSize(new Dimension(120, 30));
	   topPane.add(datePicker2);
	   // 달력
	    UtilDateModel model1 = new UtilDateModel();
	   JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
	   JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
	   datePicker1.setPreferredSize(new Dimension(120, 30));
	   
	//   Date selectedDate = (Date) datePicker.getModel().getValue();
	//   UtilDateModel model1 = new UtilDateModel();
	//   model1.setDate(1990, 8, 24);

	   topPane.add( lblEnd );
	   topPane.add(datePicker1);
	   topPane.add( btnFind );
	   topPane.add( btnSet );
	   topPane.add( btnToExcel );
	   botPane.add(lblTot);
	   botPane.add(txtTot);

	   
	   // 버튼에 기능 부여
	   // 검색버튼 클릭
	   btnFind.addActionListener( this );
	   //검수확정버튼 클릭
	   btnSet.addActionListener( this );
	      // 이벤트핸들러(이벤트발생시 수행할 함수 - actionPreformed() )를 등록
	   // 엑셀로 저장 버튼 클릭
	   btnToExcel.addActionListener( this );
	   
	   
	   
	      // -----------------------      
	      jTable      =   new  JTable();      
	      // data 를 model 에 담아서 채움
	      jTable.setModel(
	            new DefaultTableModel( getDataList() , getColumnList() ) {            
	               // 기본 option 설정 - 각 cell 에 대한 편집가능여부 :isCellEditable
	               @Override
	               public boolean isCellEditable(int row, int column) {
	                     int  currLine = jTable.getSelectedRow();  // 선택한 줄만 수정가능
	                    if( row == currLine  )
	                        return true;         
	                return false;   // 모든 cell 편집불가능
	               }            
	            }   
	            );
	   
	   
	         
	         //jTable 의 Row 이 더블클릭(마우스 동작연결)되면
	         jTable.addMouseListener( this );
	         
	         
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
	         //  <? extends Vector> : Vector 를 상속받은 Type 만 가능하다
	         // Vector<? extends Vector> : Vector안에 Vector 타입만 저장가능 
	         //  - 2차원배열 : ResultSet을 받기위해 
	         //  Vector< Vector > : 안쪽   Vector : ResultSet 의 한 Row - Record
	         //                   : 바깥쪽 Vector : ResultSet 전체  Table - Record 배열
	         private Vector<Vector> getDataList() {
	        	 IpgoDao       dao   =  new IpgoDao();
	            Vector<Vector>  list  =  dao.geIpgoList();
	            return  list;
	         }

	         // jTable 에 제목줄 생성 - 크기변경가능한 배열 : ArrayList -> Vector(swing)
	         // ? : class Type 
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
	         
	         
	         
	         
	      /*   
	           
	         // data 조작
	         // 1. 행과 열 수
	         System.out.println( jt.getRowCount()    );  // 줄수 : row 
	         System.out.println( jt.getColumnCount() );  // 칸수 : column
	         
	         // 2. 칼럼이름 가져오기
	         System.out.println("3번째 칸의 제목:" + jt.getColumnName(2)); // 0~
	         
	         // 3. 데이터 줄 삭제      
	         //  jt.remove(0);  // 작동안함
	         dm.removeRow(0);
	         
	         // 4. data 가져오기 (2,3) 위치의 자료
	         System.out.println(  "(2,3) 위치의 자료:" + dm.getValueAt(2, 3) );
	               
	         // 5. data 수정하기 (2,0) 를 변경
	         dm.setValueAt("han", 2, 0);
	         
	         // 6. 선택한 row selection
	         jt.setRowSelectionInterval(1, 1);
	         
	   */


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
	          // 새로고침 클릭
	          System.out.println("검색 클릭");         
	          break;
	      case "검수확정":
	         // 새로고침 클릭
	         System.out.println("검수확정 클릭");
	         jTableRefresh();         
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
	      case "입고내역 조회":
	          // 새로고침 클릭
	          System.out.println("입고내역 조회 클릭");
	          jTableRefresh();         
	          break;
	      }
	         
	   }
	   
	   // jTable 검수확정 - data를 변경
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
	   
	   
			
	   @Override
	   public void mousePressed(MouseEvent e) {
	      // 마우스버튼 누르고 있는 동안
	      
	   }

	   @Override
	   public void mouseReleased(MouseEvent e) {
	      // 마우스버튼이 눌러졌다가 놓는 순간
	      
	   }

	   @Override
	   public void mouseEntered(MouseEvent e) {
	      // 마우스 커서가 특정공간안으로 들어갈때(진입)
	      
	   }

	   @Override
	   public void mouseExited(MouseEvent e) {
	      // 마우스 커서가 특정공간에서 밖으로 나갈때(이탈)
	      
	   }

	      //-----------------------------------------------------
	      // excel 로 저장
	      // Workbook -> .xlsx
	      // -> Sheet 
	      //  -> Row
	      //   -> Cell
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

	      @Override
	      public void mouseClicked(MouseEvent e) {
	         // TODO Auto-generated method stub
	         
	      }
	}
