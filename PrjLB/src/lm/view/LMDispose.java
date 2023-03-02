package lm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lm.model.DisposeDao;
import lm.model.DisposeVo;


public class LMDispose extends JFrame implements ActionListener{
	private static JTable table;
	private static JComboBox comboBox;

	private static LMDispose    dList = null;
	private static LMDisposeList  dProc = null;

	public static DisposeVo Divo = new DisposeVo();
	public static ArrayList<Object> disDate = new ArrayList<Object>();
	public static ArrayList<Object> disPname = new ArrayList<Object>();
	public static ArrayList<Object> disNum = new ArrayList<Object>();
	public static ArrayList<Object> disStock = new ArrayList<Object>();


	public LMDispose() {
		init();
	}

	private void init() {
		setTitle("상품 폐기 업무");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200,700);
		setLocation(200, 200);

		JLabel lblNewLabel = new JLabel("상품 분류");

		String [] aid = {"전체보기","주류","가공식품","기호식품","냉동냉장"}; 
		comboBox = new JComboBox(aid);

		JButton btnNewButton = new JButton("검색");
		btnNewButton.addActionListener( this );

		JButton btnNewButton_1 = new JButton("폐기확정");
		btnNewButton_1.addActionListener(this);

		JButton btnNewButton_2 = new JButton("폐기내역 조회");
		btnNewButton_2.addActionListener(this);

		JButton btnNewButton_3 = new JButton("엑셀로 저장");
		btnNewButton_3.addActionListener(this);

		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				int  currColumn = table.getSelectedColumn();  // 선택한 열만 수정가능
				if( currColumn == 6  )
					return true;         
				return false;   // 모든 cell 편집불가능 ㅋ
			}
		};

		JScrollPane pane  = new JScrollPane( table );
		getContentPane().add( pane );

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(35)
						.addComponent(lblNewLabel)
						.addGap(32)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
						.addGap(30)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addGap(28))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(pane, GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(21)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(btnNewButton_2)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_3)
								.addComponent(btnNewButton)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(pane, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
						.addContainerGap())
				);
		getContentPane().setLayout(groupLayout);
		setVisible(true);



	}

	public static void main(String[] args) {

		new LMDispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch( e.getActionCommand() ) {  // 눌러진 버튼의 글자
		case "검색":
			System.out.println("검색 클릭");
			Vector<Vector> list = getDataList(this);
			jTableRefresh2(list);
			break;
			
		case "폐기확정":
			System.out.println("폐기확정 클릭");
			try {
				table.editCellAt(-1, -1);   // 마지막 cell의 입력을 완료되려면 셀선택을 테이블 밖으로 빼야함(입력 후 엔터치는 것과 같음)
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

			String  fmt      = "D:\\excel\\";
			fmt             += "폐기업무_%4d%02d%02d%02d%02d%02d.xlsx";
			String  filepath = String.format(fmt, year, mm, dd, hh, mi, ss );

			System.out.println( filepath );
			excelWrite( filepath );

			break;
		case "폐기내역 조회":
			System.out.println("폐기내역 조회 클릭");
			if(  dProc != null )
				dProc.getFrame().setVisible(false);  // 강제로 닫는다
			dProc = new LMDisposeList( this );   // this : 현재 실행중인 MemberList        
			break;

		}
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

		// Data 줄 처리
		for (int i = 0; i < numrows; i++) {
			row    =   sheet.createRow(i + 1);
			for (int j = 0; j < numcols ; j++) {
				cell = row.createCell(j);
				cell.setCellValue((String) table.getValueAt(i, j));
			}

		}

	}// 엑셀 끝

	private Vector<Vector> getDataList() {
		DisposeDao       dao   =  new DisposeDao();
		Vector<Vector>  list  =  dao.getDispose();
		return  list;
	}

	private Vector<String> getColumnList() {
		Vector<String>  cols = new Vector<>();  // 문자배열 대신 사용
		cols.add("거래처명");
		cols.add("폐기일자");
		cols.add("상품코드");
		cols.add("상품명");
		cols.add("입고가격");
		cols.add("현재 재고");
		cols.add("폐기 수량");
		cols.add("폐기 직원");
		return  cols;
	}

	private void addList() {
		getInArrayData();
		DisposeDao dao = new DisposeDao();      

		dao.insertList(disDate,disPname,disNum);   // 폐기 INSERT DAO
		System.out.println(disDate);
		System.out.println(disPname);
		System.out.println(disNum);

		dao.updateMember(disPname,disNum);		// STOCK UPDATE DAO

		disDate.clear();
		disPname.clear();
		disNum.clear();
	}

	private void getInArrayData() {
		int rowsCount = table.getRowCount();
		for (int i = 0; i < rowsCount; i++) {

			try {

				// 현재시간 불러와서 disDate에 넣자
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
				disDate.add(i, now.toString() + " " + now1.toString() ); 	// disdate 폐기일자

				disPname.add(i, table.getValueAt(i, 3).toString() ); //  상품명

			} catch(NullPointerException e) {
				System.err.println("table.getValueAt 이 null 입니다.");
				JOptionPane.showMessageDialog(null, 
						"table.getValueAt 이 null 입니다.", "지정오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			if(table.getValueAt(i, 6).toString().equals("")) {
				disNum.add(i, "0");
			} else {
				disNum.add(i, table.getValueAt(i, 6).toString() );   //  폐기수량
			}
			System.out.print(disNum);
			// 폐기수량이 현재수량보다 크다면, 경고메세지
			disStock.add(i, table.getValueAt(i, 5).toString() );   //  현재수량
			
			// 배열이름 넣자
			if( Integer.parseInt((String) disNum.get(i))  >  Integer.parseInt(table.getValueAt(i, 5).toString())) {
				JOptionPane.showMessageDialog(null, 
						"폐기할 수량이 현재재고보다 많습니다.", "지정오류", JOptionPane.ERROR_MESSAGE);				
			}
		}
	}


	private void jTableRefresh2(Vector<Vector> list) {
		table.setModel(
				new DefaultTableModel( list ,  getColumnList()  ) {

					@Override
					public boolean isCellEditable(int row, int column) {               
						return false;
					}

				}
				);  // jtable 새로운 데이터를 지정

		table.repaint();  // jtable을 새로 그린다

	}

	private Vector<Vector> getDataList(LMDispose lmDispose) {
		String         search = null;
		if (comboBox.getSelectedItem().toString().equals("전체보기")) {
			search = "";
			
		} else {
			search = comboBox.getSelectedItem().toString();
		}
		DisposeDao     dao    =  new DisposeDao();
		Vector<Vector> list   =  dao.getDispose(search);
		System.out.println(list);
		
		
		return list;
	}
}
