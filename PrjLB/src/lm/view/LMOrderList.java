package lm.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
import javax.swing.JComponent;
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
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;

public class LMOrderList extends JFrame implements ActionListener {
	
	ImageIcon icon;
	JScrollPane scrollpane;
	

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

	LMProdOrder order3 = null;
	private JTextField textField_4;
	private JTextField textField_5;
	


	// 기본생성자
	public LMOrderList(){
		initComponent();
	}

	//인자있는 생성자
	public LMOrderList(LMProdOrder order3) {
		this();  // 기본생성자 호출		
		this.order3 = order3;
	}

	private void initComponent() {
		this.setTitle("주문내역 조회");
		
		icon = new ImageIcon("./image/큰거1.png");
		
		JPanel panel = new JPanel() {
	         public void paintComponent(Graphics g) {
	             // Approach 1: Dispaly image at at full size
	             g.drawImage(icon.getImage(), 0, 0, null);
	             // Approach 2: Scale image to size of component
	             // Dimension d = getSize();
	             // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
	             // Approach 3: Fix the image position in the scroll pane
	             // Point p = scrollPane.getViewport().getViewPosition();
	             // g.drawImage(icon.getImage(), p.x, p.y, null);
	             setOpaque(false);
	             super.paintComponent(g);
	            }
	      };
			
		
		
		scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setLayout(null);
		
		
		
		
		
		

		// 시작일 달력
		lblNewLabel_1 = new JLabel("시작일 선택");
		lblNewLabel_1.setBounds(12, 24, 74, 21);
		panel.add(lblNewLabel_1);
		
		
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.setBounds(98, 24 , 150, 26);
		panel.add(datePicker1);
		
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
		lblNewLabel_2 = new JLabel("시작일자:");
		lblNewLabel_2.setBounds(260, 24, 114, 21);
		panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(329, 24, 116, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		
		
		
		// 종료일 달력
		
		lblNewLabel_3 = new JLabel("종료일 선택 :");
		lblNewLabel_3.setBounds(457, 24, 114, 21);
		panel.add(lblNewLabel_3);
		
		
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
			// FlowLayout의 컴포넌트 리사이즈 방법
		panel.add(datePicker2);
		datePicker2.setBounds(540, 24 , 150, 26);
		
		model2.setDate(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth() );	// 오늘 날짜로 초기세팅
		model2.setSelected(true);

		datePicker2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				endDate = datePicker2.getJFormattedTextField().getText();

				// 선택된 종료날짜를 DATE 타입으로 저장
				selectedDate2 = (Date) datePicker2.getModel().getValue();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				date2 = simpleDateFormat.format(selectedDate2);

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
		
		//종료일자
		 lblNewLabel_4 = new JLabel("종료일자 :");
		lblNewLabel_4.setBounds(728, 24, 114, 21);
		panel.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(802, 24, 116, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
	
		
		//날짜 초기화
		btnNewButton_3 = new JButton("날짜 초기화");
		 btnNewButton_3.addActionListener(this);
		 btnNewButton_3.setBounds(100, 105, 97, 23);
		panel.add(btnNewButton_3);
		
		//거래처명
		lblNewLabel = new JLabel("거래처명 :");
		lblNewLabel.setBounds(295, 106, 114, 21);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(399, 106, 116, 21);
		panel.add(textField);
		
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
		
		//검색하기
		btnNewButton_1 = new JButton("검색하기");
		btnNewButton_1.setBounds(545, 105, 97, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		//액셀로 저장
		btnNewButton_2 = new JButton("엑셀로 저장");
		btnNewButton_2.setBounds(670, 106, 116, 21);
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

				String  fmt      = "D:\\excel\\";
				fmt             += "주문리스트_%4d%02d%02d%02d%02d%02d.xlsx";
				String  filepath = String.format(fmt, year, mm, dd, hh, mi, ss );

				System.out.println( filepath );
				excelWrite( filepath );

			}

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
		});
		
		panel.add(btnNewButton_2);
	
		
		table = new JTable(){
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;   // 모든 cell 편집불가능
			}
			
		};
		
				
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(40, 161, 907, 331);
		panel.add(scrollPane_1);
		
		
		
		
		
		 
		// 총 주문가격
		lblNewLabel_7 = new JLabel("총 주문가격 :");
		lblNewLabel_7.setBounds(361, 511, 114, 21);
		panel.add(lblNewLabel_7);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(442, 511, 116, 21);
		panel.add(textField_3);
		textField_3.setText( String.valueOf(getSumPrice()) );
		
		
		
		
		

		
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		this.setSize(1000,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	// 테이블 getDateList
	private Vector<Vector> getDataList() {
		OrderDao       dao   =  new OrderDao();
		Vector<Vector>  list  =  dao.getOrderList();

		return list;
	}

	// 검색 후 테이블 getList
	private Vector<Vector> getDataList(LMOrderList OrderList) {
		String          search = textField.getText();
		OrderDao        dao   =  new OrderDao();
		Vector<Vector>  list  =  dao.getOrderList(search, date1, date2);

		return list;
	}

	// 테이블 getColumnList
	private Vector<String> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("주문 일자");
		cols.add("거래처명");
		cols.add("상품 코드");
		cols.add("상품명");
		cols.add("입고 가격");
		cols.add("현재 재고");
		cols.add("주문 수량");
		cols.add("주문 직원");

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
			if (table.getValueAt(i, 6) != null) {
				pri = Integer.parseInt(table.getValueAt(i, 4).toString());
			}

			int su  = 0;
			if (table.getValueAt(i, 6) != null) {
				su = Integer.parseInt(table.getValueAt(i, 6).toString());
			}

			sum = sum + (pri * su);
		}
		System.out.println(sum);
		return sum;
	}


		public static void main(String[] args) {
		LMOrderList window = new LMOrderList();
	}
}
