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
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.UIManager;


public class LMDispose extends JFrame implements ActionListener{
	String userid;
	private static JTable table;
	private static JComboBox comboBox;

	private static LMDispose    dList = null;
	private static LMDisposeList  dProc = null;

	public static DisposeVo Divo = new DisposeVo();
	public static ArrayList<Object> disDate = new ArrayList<Object>();
	public static ArrayList<Object> disPname = new ArrayList<Object>();
	public static ArrayList<Object> disNum = new ArrayList<Object>();
	public static ArrayList<Object> disStock = new ArrayList<Object>();
	ImageIcon icon;

	public LMDispose() {
		init();
	}

	public LMDispose(String userid) {
		this.userid = userid;
		init();
	}

	private void init() {
		setTitle("상품폐기업무");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/lmimage/alphabets-33744_640.png")));
		getContentPane().setBackground(new Color(231,231,231));
		
		icon = new ImageIcon(LMDispose.class.getResource("/lmimage/큰거1.png"));
		
		JPanel panel = new JPanel() {
	        public void paintComponent(Graphics g) {
	       
	            g.drawImage(icon.getImage(), 0, 0, null);
	    
	            setOpaque(false);
	            super.paintComponent(g);
	           }
	     };
		panel.setForeground(UIManager.getColor("ComboBox.background"));
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,600);
		setLocation(650, 200);

		String [] aid = {"전체보기","주류","가공식품","기호식품","냉동냉장"}; 
		
		JScrollPane scrollPane = new JScrollPane();

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
			.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
				);

		//JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC0C1\uD488 \uBD84\uB958 :");
		lblNewLabel.setFont(new Font("새굴림", Font.PLAIN, 17));
		lblNewLabel.setBounds(170, 95, 92, 30);
		panel.add(lblNewLabel);
		comboBox = new JComboBox(aid);
		comboBox.setFont(new Font("새굴림", Font.PLAIN, 12));
		comboBox.setForeground(Color.BLACK);
		comboBox.setBackground(SystemColor.window);
		comboBox.setBounds(263, 95, 150, 30);
		panel.add(comboBox);


		JButton btnNewButton = new JButton("검색");
		btnNewButton.setIcon(new ImageIcon(LMDispose.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnNewButton.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnNewButton.setBounds(425, 94, 70, 32);
		btnNewButton .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("폐기확정");
		btnNewButton_1.setIcon(new ImageIcon(LMDispose.class.getResource("/lmimage/4\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_1.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnNewButton_1.setBounds(599, 95, 92, 32);
		btnNewButton_1 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_1);

		JButton btnNewButton_3 = new JButton("엑셀로 저장");
		btnNewButton_3.setIcon(new ImageIcon(LMDispose.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_3.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnNewButton_3.setBounds(843, 95, 103, 32);
		btnNewButton_3 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("\uD3D0\uAE30\uB0B4\uC5ED\uC870\uD68C");
		btnNewButton_2.setIcon(new ImageIcon(LMDispose.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnNewButton_2.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnNewButton_2.setBounds(716, 95, 108, 32);
		btnNewButton_2 .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnNewButton_2);

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
		pane.setBounds(5, 158, 970, 395);
		panel.add(pane);
		//getContentPane().add( pane );

		JLabel lblNewLabel_4_1 = new JLabel("\uC0C1\uD488\uD3D0\uAE30\uC5C5\uBB34");
		lblNewLabel_4_1.setFont(new Font("새굴림", Font.BOLD, 40));
		lblNewLabel_4_1.setBounds(700, 10, 391, 79);
		panel.add(lblNewLabel_4_1);
		btnNewButton_2.addActionListener(this);
		btnNewButton_3.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		btnNewButton.addActionListener( this );
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
			int rowsCount = table.getRowCount();
	        JDialog jd = new JDialog(0);
	        jd.Dlbl.setText(rowsCount + "건 검색되었습니다");
	        jd.setTitle("검색 결과");
	        jd.setAlwaysOnTop(true);
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
		case "폐기내역조회":
			System.out.println("폐기내역 조회 클릭");
			if(  dProc != null )
				dProc.dispose();  // 강제로 닫는다
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
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText("D:\\excel 저장에 성공하였습니다");
					       
			jd.setTitle("저장성공");
			jd.setAlwaysOnTop(true);
		} catch (IOException e) {
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText("<html><body><center>저장을 실패하였습니다."
					+ "<br>저장공간의 위치를 확인해주세요."
					+ "<br>저장공간: D:\\excel</center></body></html>");
			jd.setTitle("저장실패");
			jd.setAlwaysOnTop(true);
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
		cols.add("사원번호");
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
		int sum = 0;
		for (int i = 0; i < rowsCount; i++) {

			if(table.getValueAt(i, 6).toString().equals("")) {
				disNum.add("0");
			} else {
			disNum.add(i, table.getValueAt(i, 6).toString() );   //  폐기수량
			}

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
				
				sum += Integer.parseInt(disNum.get(i).toString());
			
		}
		System.out.println(sum);
		JDialog jd = new JDialog(0);
		jd.Dlbl.setText(sum + " 개 폐기되었습니다");
		jd.setTitle("폐기");
		jd.setAlwaysOnTop(true);
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
		Vector<Vector> list   =  dao.getDispose(search,userid);
		System.out.println(list);
		
		
		return list;
	}
}