package lm.view;

import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lm.model.OutputDao;
import lm.model.OutputVo;

public class LMOutput extends JFrame implements ActionListener{
	private static JTextField textField;
	private static JTable table;

	private static ArrayList<Object> outDate = new ArrayList<Object>();
	private static ArrayList<Object> outputPname = new ArrayList<Object>();
	private static ArrayList<Object> outputNum = new ArrayList<Object>(); 
	private static ArrayList<Object> shopName = new ArrayList<Object>();
	private static Vector<String> shops;
	private static Vector<Vector> list;

	private static LMOutput lmoutput     = null;
	LMOutputList     opl       = null ;
	private JComboBox comboBox;	
	public static OutputVo ot = new OutputVo();
	private static LMOutput lmOutput;


	public static Vector<Vector> getList() {
		return list;
	}

	public static void setList(Vector<Vector> list) {
		LMOutput.list = list;
	}

	public static Vector<String> getShops() {
		return shops;
	}

	public static void setShops(Vector<String> shops) {
		LMOutput.shops = shops;
	}


	public LMOutput() {
		initComponent();
	}


	private void initComponent() {

		setTitle("상품 출고 화면");
		setSize(1100, 600);
		setLocation(200, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("거래처명");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("점포명");


		// 콤보박스에 담을 점포명 배열을 가져와야 함

		OutputDao oDao = new OutputDao();
		oDao.setShopVector(); 

		setShops(oDao.setShopVector());  

		comboBox = new JComboBox(getShops());
		comboBox.setPreferredSize(new Dimension(150, 30));

		comboBox.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ot.setShopId(comboBox.getSelectedItem().toString());

			}
		});


		JButton btnNewButton = new JButton("점포지정 & 조회");

		btnNewButton.addActionListener( this );



		table = new JTable() {

			@Override
			public boolean isCellEditable(int row, int column) {
				int  currColumn = table.getSelectedColumn();  // 선택한 열만 수정가능
				if( currColumn == 7  )
					return true;			
				return false;   // 모든 cell 편집불가능
			}

		};;

		JButton btnNewButton_1 = new JButton("출고 확정");
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.editCellAt(-1, -1);	// 마지막 cell의 입력을 완료되려면 셀선택을 테이블 밖으로 빼야함(입력 후 엔터치는 것과 같음)
				}catch(Exception ex) {}
				addList();
				jTableRefresh2(getList());

			}

			// 테이블 새로고침
			private void jTableRefresh2(Vector<Vector> list) {
				table.setModel(new DefaultTableModel(list, getColumnList()) {

					@Override
					public boolean isCellEditable(int row, int column) {
						return super.isCellEditable(row, column);
					}

				});

				table.repaint();

			}

			// 출고확정시 실행할 메소드 
			private void addList() {
				getOutputArrayData();

				OutputDao dao = new OutputDao();

				dao.insertList(outDate,outputPname,shopName,outputNum);	// (ordering 테이블에 insert)
				dao.updateStock(outputPname, outputNum);				// (stock 테이블을 update)

				outDate.clear();
				outputPname.clear();
				shopName.clear();
				outputNum.clear();
			}

			private void getOutputArrayData() {
				int rowsCount = table.getRowCount();
				for (int i = 0; i < rowsCount; i++) {
					try {
						// 현재시간 불러와서 outDate에 넣자
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

						outDate.add(i, now.toString() + " " + now1.toString() ); 	// outdate 출고일자

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
		});

		JButton btnNewButton_1_1 = new JButton("출고내역 확인");
		btnNewButton_1_1.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				if (opl != null)
//					opl.dispose();
//				opl = new OutputList();
				
			}
		});
		

		JButton btnNewButton_1_2 = new JButton("엑셀로 저장");
		btnNewButton_1_2.addActionListener( new ActionListener() {
			
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
		});

		JScrollPane sp = new JScrollPane(table); 
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(63)
						.addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
						.addGap(41)
						.addComponent(lblNewLabel_1)
						.addGap(18)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addGap(89))
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(247)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(427, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(20)
						.addComponent(sp, GroupLayout.PREFERRED_SIZE, 954, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(210, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(19)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(sp, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton_1_1)
								.addComponent(btnNewButton_1_2))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		getContentPane().setLayout(groupLayout);

	}









	public static void main(String[] args) {
		lmOutput = new LMOutput();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Vector<Vector> list = getDataList(this);
		jTableRefresh2(list);

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
		cols.add("사원 번호");

		return cols;
	}

	// 검색 후 테이블 getDataList
	private Vector<Vector> getDataList(LMOutput lmoutput) {
		String          search = textField.getText();
		OutputDao       dao   =  new OutputDao();
		Vector<Vector>  list  =  dao.getOutput(search);
		setList(list);

		return list;
	}
}
