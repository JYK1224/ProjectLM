package lm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.SystemColor;



public class LMipgo extends JFrame 
implements  ActionListener  {

   // 필드
   static LMipgo    mList = null;
   IpgoList         iProc = null;
   ButtonGroup group;
   JLabel        lblAcc, lblMonth, lblDay; // 거래처명, 월, 일
   TextField     txtId; // 검색할 항목
   JButton       btnFind, btnSet, btnToExcel, btnList; //검수확정, 인쇄(엑셀로 보내기)
   JTable        jTable;
   JScrollPane   pane;
   ImageIcon icon;
   
   private static String selDate = "";
   public static IpgoVo lmvo = new IpgoVo();
   
   public static ArrayList<Object> inDate = new ArrayList<Object>();
   public static ArrayList<Object> inPname = new ArrayList<Object>();
   public static ArrayList<Object> inNum = new ArrayList<Object>();
   private JScrollPane scrollPane;
   private JPanel panel;
   private JLabel lblNewLabel;
   
   
   
   
   
   
   
   
   public LMipgo() {   
      init();
   }
   
private void init() {
   
   setTitle("상품입고");
      
   

   icon = new ImageIcon("./image/큰거1.png");
   
   JPanel panel = new JPanel() {
        public void paintComponent(Graphics g) {
       
            g.drawImage(icon.getImage(), 0, 0, null);
    
            setOpaque(false);
            super.paintComponent(g);
           }
     };
   
   this.group        = new ButtonGroup();
   
   // 달력
    UtilDateModel model = new UtilDateModel();
   JDatePanelImpl datePanel = new JDatePanelImpl(model);
            
            scrollPane = new JScrollPane();
            GroupLayout groupLayout = new GroupLayout(getContentPane());
            groupLayout.setHorizontalGroup(
               groupLayout.createParallelGroup(Alignment.LEADING)
                  .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
            );
            groupLayout.setVerticalGroup(
               groupLayout.createParallelGroup(Alignment.LEADING)
                  .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
            );
            
           
            scrollPane.setViewportView(panel);
            panel.setLayout(null);
            lblAcc       =  new JLabel("거래처명: ");
            lblAcc.setFont(new Font("새굴림", Font.PLAIN, 15));
            lblAcc.setBounds(175, 55, 77, 20);
            panel.add(lblAcc);
            
            
            
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
                  pane.setBounds(5, 158, 970, 395);
                  panel.add(pane);
            lblDay       =  new JLabel("\uB0A0\uC9DC\uC120\uD0DD: ");
            lblDay.setFont(new Font("새굴림", Font.PLAIN, 15));
            lblDay.setBounds(175, 99, 77, 24);
            panel.add(lblDay);
            btnFind      =  new JButton("검색");    
            btnFind.setIcon(new ImageIcon(LMipgo.class.getResource("/lmimage/\uAE34\uBC84\uD2BC.png")));
            btnFind.setFont(new Font("새굴림", Font.PLAIN, 12));
            btnFind.setBounds(453, 94, 149, 32);
            btnFind .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
            panel.add(btnFind);
            
               
               // 버튼에 기능 부여
               // 검색버튼 클릭
               btnFind.addActionListener( this );
            btnList      =  new JButton("\uC785\uACE0\uB0B4\uC5ED\uC870\uD68C");
            btnList.setIcon(new ImageIcon(LMipgo.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
            btnList.setFont(new Font("새굴림", Font.PLAIN, 12));
            btnList.setBounds(725, 94, 106, 32);
            btnList .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
            panel.add(btnList);
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
            btnToExcel   =  new JButton("엑셀로 저장");
            btnToExcel.setIcon(new ImageIcon(LMipgo.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
            btnToExcel.setFont(new Font("새굴림", Font.PLAIN, 12));
            btnToExcel.setBounds(843, 94, 106, 32);
            btnToExcel .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
            panel.add(btnToExcel);
            // 이벤트핸들러(이벤트발생시 수행할 함수 - actionPreformed() )를 등록
   // 엑셀로 저장 버튼 클릭
   btnToExcel.addActionListener( this );
            btnSet       =  new JButton("입고확정");    
            btnSet.setIcon(new ImageIcon(LMipgo.class.getResource("/lmimage/4\uC790\uB9AC\uBC84\uD2BC.png")));
            btnSet.setFont(new Font("새굴림", Font.PLAIN, 12));
            btnSet.setBounds(620, 94, 93, 32);
            btnSet .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
            panel.add(btnSet);
            //검수확정버튼 클릭
            btnSet.addActionListener( this );
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
            datePicker.getJFormattedTextField().setBackground(SystemColor.window);
            datePicker.setBounds(264, 97, 177, 30);
            panel.add(datePicker);
            datePicker.setPreferredSize(new Dimension(250, 30));
            
            datePicker.addActionListener(new ActionListener() {      // 선택한 날짜 기준으로, 주문일자와 입고일자에 대입

      @Override
      public void actionPerformed(ActionEvent e) {
         selDate = datePicker.getJFormattedTextField().getText();

         // 선택된 날짜를 DATE 타입으로 저장
         Date selectedDate = (Date) datePicker.getModel().getValue();

         Calendar cal = Calendar.getInstance();
         cal.setTime(selectedDate);
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         String current = df.format(cal.getTime());
         //           System.out.println("current: " + df.format(cal.getTime()));
         lmvo.setIndate(current);
         System.out.println(current);

         cal.add(Calendar.DATE, +1);      // 입고예정일 = 주문일 + 1
         String after = df.format(cal.getTime());
         //           System.out.println("after: " + df.format(cal.getTime()));

       //어제 
			Calendar cal2 = Calendar.getInstance();
			cal2.add(Calendar.DATE, -1);	
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			String yesterday = df2.format(cal2.getTime());
			
			
			lmvo.setOrderdate(yesterday);
         //            jTableRefresh();    // 테이블 새로고침 메소드
         
       
         
      }
});
            txtId        =  new TextField(30);  
            txtId.setBounds(264, 53, 177, 23);
            panel.add(txtId);
            
            lblNewLabel = new JLabel("\uC0C1\uD488\uC785\uACE0\uC5C5\uBB34");
            lblNewLabel.setFont(new Font("새굴림", Font.BOLD, 40));
            lblNewLabel.setBounds(703, 10, 391, 79);
            panel.add(lblNewLabel);
            getContentPane().setLayout(groupLayout);
            setSize(1000, 600); // 창크기
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
            cols.add("입고 예정일");
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
            jTable.editCellAt(-1, -1);   // 마지막 cell의 입력을 완료되려면 셀선택을 테이블 밖으로 빼야함(입력 후 엔터치는 것과 같음)
         }catch(Exception ex) {}
         addList();
         
         
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
         inNum.add(i, jTable.getValueAt(i, 7).toString() );     //  입고수량
         
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