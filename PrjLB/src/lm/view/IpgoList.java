package lm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
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

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;


public class IpgoList extends JFrame implements  ActionListener{

	static IpgoList    iList = null;
	LMipgo  lmList = null;


	JLabel        lblAcc, lblMonth, lblStart, lblEnd, lblTot; // 거래처명, 월, 일
	private static TextField     txtId, txtTot; // 검색할 항목, 총 금액
	JButton       btnFind, btnToExcel; // 인쇄(엑셀로 보내기)
	private static JTable        jTable;
	private static JScrollPane   pane;
	
	private static String startDate;
	private static String endDate;
	private static Date selectedDate1, selectedDate2;
	private static String date1 = "";
	private static String date2 = "";
	ButtonGroup group;
	LMipgo lml = null;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	ImageIcon icon;
	public IpgoList() { 
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/lmimage/alphabets-33744_640.png")));
		getContentPane().setBackground(new Color(231,231,231));
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
		
		icon = new ImageIcon("./image/큰거1.png");
		
		JPanel panel = new JPanel() {
	         public void paintComponent(Graphics g) {
	        
	             g.drawImage(icon.getImage(), 0, 0, null);
	     
	             setOpaque(false);
	             super.paintComponent(g);
	            }
	      };
		
		// 시작일 달력
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
		
		// 종료일 달력
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		
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
		lblAcc.setBounds(210, 18, 83, 23);
		panel.add(lblAcc);
		txtId        =  new TextField(20);  
		txtId.setBounds(302, 18, 164, 23);
		panel.add(txtId);
		lblStart     =  new JLabel("     기간 시작날짜: ");
		lblStart.setFont(new Font("새굴림", Font.PLAIN, 15));
		lblStart.setBounds(152, 63, 124, 22);
		panel.add(lblStart);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.getJFormattedTextField().setBackground(SystemColor.window);
		datePicker1.setBounds(302, 63, 164, 28);
		panel.add(datePicker1);
		datePicker1.setPreferredSize(new Dimension(150, 30));
		lblEnd       =  new JLabel("     기간 종료날짜: ");
		lblEnd.setFont(new Font("새굴림", Font.PLAIN, 15));
		lblEnd.setBounds(152, 108, 124, 25);
		panel.add(lblEnd);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.getJFormattedTextField().setBackground(SystemColor.window);
		datePicker2.setBounds(302, 108, 164, 28);
		panel.add(datePicker2);
		datePicker2.setPreferredSize(new Dimension(150, 30));
		btnFind      =  new JButton("검색");      
		btnFind.setIcon(new ImageIcon(IpgoList.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnFind.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnFind.setBounds(488, 103, 70, 32);
		btnFind .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		panel.add(btnFind);
		btnToExcel   =  new JButton("엑셀로 저장");
		btnToExcel.setIcon(new ImageIcon(IpgoList.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnToExcel.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnToExcel.setBounds(852, 103, 106, 32);
		btnToExcel .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		panel.add(btnToExcel);
		lblTot       =  new JLabel(" \uCD1D \uC785\uACE0\uAC00\uACA9: ");
		lblTot.setForeground(SystemColor.text);
		lblTot.setFont(new Font("굴림", Font.PLAIN, 17));
		lblTot.setBounds(617, 526, 100, 20);
		panel.add(lblTot);
		txtTot       =  new TextField(20);  
		txtTot.setFont(new Font("새굴림", Font.PLAIN, 17));
		txtTot.setBounds(730, 524, 153, 20);
		panel.add(txtTot);
		
		
		
				// -----------------------      
				jTable      =   new  JTable(){
		
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;   // 모든 cell 편집불가능
					}
		
				};
				txtTot.setText( String.valueOf(getSumPrice()) );	// 가격 * 수량
				
				
						pane  = new JScrollPane( jTable );
						pane.setBounds(5, 158, 970, 351);
						panel.add(pane);
						
						lblNewLabel = new JLabel("\uC785\uACE0\uB0B4\uC5ED\uC870\uD68C");
						lblNewLabel.setFont(new Font("새굴림", Font.BOLD, 40));
						lblNewLabel.setBounds(703, 10, 391, 79);
						panel.add(lblNewLabel);
		// 이벤트핸들러(이벤트발생시 수행할 함수 - actionPreformed() )를 등록
		// 엑셀로 저장 버튼 클릭
		btnToExcel.addActionListener( this );
		
		
		
				// 버튼에 기능 부여
				// 검색버튼 클릭
				btnFind.addActionListener( this );
		
		
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
		getContentPane().setLayout(groupLayout);
		setSize(1000, 600); // 창크기
		 setLocation(650,200);
		setVisible(true); // 화면에 보이게
		setResizable(false);
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
		new IpgoList();

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

			String  fmt      = "d:\\excel\\";
			fmt             += "입고내역_%4d %02d %02d %02d %02d %02d.xlsx";
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
		
		int numcols = jTable.getColumnCount();
		int numrows = jTable.getRowCount();
		
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
				cell       =  row.createCell( j );
				cell.setCellValue( (String) jTable.getValueAt(i, j));
			}

		}
	}




}
