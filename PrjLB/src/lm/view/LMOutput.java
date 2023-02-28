package lm.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import lm.model.OutputDao;
import lm.model.OutputVo;

public class LMOutput extends JFrame implements ActionListener{

	// Fields
	private static JFrame frame;
	private static JTextField textField, textField_1, textField_2;
	private static JTable table;
	private static String selDate = "";
	private static JButton btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4;
	private static JLabel lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel_4, lblNewLabel_5;

	private static ArrayList<Object> outputDname = new ArrayList<Object>();
	private static ArrayList<Object> outDate = new ArrayList<Object>();
	private static ArrayList<Object> outputPname = new ArrayList<Object>();
	private static ArrayList<Object> outputNum = new ArrayList<Object>(); 
	private static ArrayList<Object> shopName = new ArrayList<Object>(); 
	private static Vector<String> shops;


	public static Vector<String> getShops() {
		return shops;
	}

	public static void setShops(Vector<String> shops) {
		LMOutput.shops = shops;
	}


	static LMOutput output     = null;
	LMOutputList     opl       = null ;
	private JComboBox comboBox;	
	public static OutputVo ot = new OutputVo();


	public LMOutput() {
		OutputDao odao = new OutputDao();
		odao.setShopVector();
		initComponent();
	}

	private void initComponent() {
		frame = new JFrame();

		frame.setTitle("상품 출고 화면");
		frame.setBounds(100, 100, 1200, 600);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		frame. setLocation(650,200);

		// 거래처명
		JLabel lblNewLabel = new JLabel("거래처명 :");
		lblNewLabel.setPreferredSize(new Dimension(60, 20));	// FlowLayout의 컴포넌트 리사이즈 방법
		frame.add(lblNewLabel);

		textField = new JTextField(20);
		textField.setPreferredSize(new Dimension(80, 20));	// FlowLayout의 컴포넌트 리사이즈 방법
		frame.add(textField);


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

		// 점포명 
		JLabel lblNewLabel_5 = new JLabel("점포명");
		lblNewLabel_5.setPreferredSize(new Dimension(60, 20));
		frame.add(lblNewLabel_5);


		// 콤보박스에 담을 점포명 배열을 가져와야 함

		OutputDao oDao = new OutputDao();
		oDao.setShopVector(); 

		setShops(oDao.setShopVector());  

		JComboBox comboBox = new JComboBox(getShops());
		comboBox.setPreferredSize(new Dimension(150, 30));
		frame.add(comboBox);

		comboBox.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ot.setShopId(comboBox.getSelectedItem().toString());

			}
		});

		// 조회
		JButton btnNewButton_1 = new JButton("점포지정 & 조회");
		btnNewButton_1.setToolTipText("거래처명 입력, 점포 지정 후 조회");
		btnNewButton_1.setPreferredSize(new Dimension(200, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		frame.getContentPane().add(btnNewButton_1);

		btnNewButton_1.addActionListener(this);

		// 테이블
		table = new JTable() {

			@Override
			public boolean isCellEditable(int row, int column) {
				int  currColumn = table.getSelectedColumn();  // 선택한 열만 수정가능
				if( currColumn == 7  )
					return true;			
				return false;   // 모든 cell 편집불가능
			}

		};

		table.addKeyListener(new KeyListener() {

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
					btnNewButton.doClick(); 

				}
			}
		});

		JScrollPane sp = new JScrollPane(table); 
		sp.setPreferredSize(new Dimension(1170, 480));		// 테이블이 담긴 스크롤페인의 사이즈
		frame.getContentPane().add( sp );

		// 출고 확정
		btnNewButton = new JButton("출고 확정");
		btnNewButton.setToolTipText("출고수량 입력 후 클릭");
		btnNewButton.setPreferredSize(new Dimension(100, 30));	// FlowLayout의 컴포넌트 리사이즈 방법

		frame.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.editCellAt(-1, -1);	// 마지막 cell의 입력을 완료되려면 셀선택을 테이블 밖으로 빼야함(입력 후 엔터치는 것과 같음)
				}catch(Exception ex) {}
				addList();

			}
		});


		// 출고내역 확인
		btnNewButton_3 = new JButton("출고내역 확인");
		btnNewButton_3.setPreferredSize(new Dimension(120, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
		frame.getContentPane().add(btnNewButton_3);

		btnNewButton_3.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("출고내역 확인 버튼 클릭....");	
				if (opl != null)
					opl.getFrame().setVisible(false);
				opl = new LMOutputList();
			}
		});

		// 엑셀로저장
		btnNewButton_2 = new JButton("엑셀로 저장");
		btnNewButton_2.setPreferredSize(new Dimension(100, 30));	// FlowLayout의 컴포넌트 리사이즈 방법
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

				String  fmt      = "D:\\excel\\";
				fmt             += "출고내역_%4d%02d%02d%02d%02d%02d.xlsx";
				String  filepath = String.format(fmt, year, mm, dd, hh, mi, ss );

				System.out.println( filepath );
				excelWrite( filepath );

			}

		});

		frame.setVisible(true);
		frame.setResizable(false);

	}



	// 테이블 getDataList
	private Vector<Vector> getDataList() {
		OutputDao       dao   =  new OutputDao();
		Vector<Vector>  list  =  dao.getOutput();

		return list;
	}


	// 검색 후 테이블 getDataList
	private Vector<Vector> getDataList(LMOutput output) {
		String          search = textField.getText();
		OutputDao       dao   =  new OutputDao();
		Vector<Vector>  list  =  dao.getOutput(search);

		return list;
	}

	// 테이블 getColumnList
	private Vector<String> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("거래처명");
		cols.add("출고 일자");
		cols.add("상품코드");
		cols.add("상품명");
		cols.add("입고 가격");
		cols.add("현재 재고");
		cols.add("점포명");
		cols.add("출고 수량");
		cols.add("출고 직원");

		return cols;
	}



	public static void main(String[] args) {

		output = new LMOutput();
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

		int numcols = table.getColumnCount();
		int numrows = table.getRowCount();

		// 제목줄 처리
		Vector<String>  cols =  getColumnList();
		row          =  sheet.createRow( 0 );
		for (int i = 0; i < cols.size(); i++) {
			cell     =  row.createCell(i);
			cell.setCellValue(  cols.get(i) );    
		}

		// 데이터 처리
		for (int i = 0; i < numrows; i++) {
			row    =  sheet.createRow(i+1);
			for (int j = 0; j < numcols ; j++) {
				cell = row.createCell(j);
				cell.setCellValue((String) table.getValueAt(i, j));
			}
		}

	} // 엑셀 끝

	@Override
	public void actionPerformed(ActionEvent e) {

		switch( e.getActionCommand() ) {
		case "점포지정 & 조회":
			Vector<Vector> list = getDataList(this);
			jTableRefresh2(list);
			break;

		}
	}

	private void addList() {

		getOutputArrayData();



		OutputDao dao = new OutputDao();

		dao.insertList(outDate,outputPname,shopName,outputNum);						
		outDate.clear();
		outputPname.clear();
		shopName.clear();
		outputNum.clear();
	}

	// ArrayList -> DAO INSERT 에 활용 예정 
	static void getOutputArrayData() {
		int rowsCount = table.getRowCount();
		for (int i = 0; i < rowsCount; i++) {
				try {
					outDate.add(i, table.getValueAt(i, 1).toString() ); 	// outdate 출고일자
					outputPname.add(i, table.getValueAt(i, 3).toString() );	// pname 상품명
					shopName.add(i, table.getValueAt(i, 6).toString() );	// shop 점포명
					if( shopName.get(i).equals("점포명 선택해주세요") || shopName.get(i).equals("") ) {
						JOptionPane.showMessageDialog(null, 
								"점포명 지정해주세요.", "점포명지정오류", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch(NullPointerException e){
					System.err.println("table.getValueAt 이 null 입니다.");
					JOptionPane.showMessageDialog(null, 
							"빈칸이 있음.", "빈칸 입력", JOptionPane.ERROR_MESSAGE);
					return;
				}
				outputNum.add(i, table.getValueAt(i, 7).toString() ); 	// outputnum 출고수량

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



}