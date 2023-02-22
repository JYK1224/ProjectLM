package lm.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lm.model.UserDao;
import lm.model.Uservo;

public class UserList extends JFrame implements ActionListener, MouseListener{
	
	JButton btnIn,  btnRe , btnEx, btnfind;
	public JTextField txtname;
	JPanel  topPane;
	JTable  jtable;
	JScrollPane pane;
	
	Proc pc = null;
	static UserList ulist = null;
	
	public UserList () {
		init ();
	}
	
	private void init() {
		setTitle( "회원관리 ");
		topPane = new JPanel();
		txtname = new JTextField (" "); 
		txtname.setColumns(15);
		btnIn   = new JButton("회원가입");
		btnRe   = new JButton("새로고침");
		btnEx   = new JButton("엑셀");
		btnfind = new JButton("조회");
		
		topPane.add(txtname);
		topPane.add(btnfind);
		topPane.add(btnIn);
		topPane.add(btnRe);
		topPane.add(btnEx);
		this.txtname.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnfind.doClick();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		
		//회원조회
		btnfind.addActionListener(this);
		
		//회원가입
		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pc != null)
					pc.dispose();
					pc = new Proc(ulist);
			}
		});
		//새로고침
		btnRe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtableRe();
			}
		});
		//엑셀
		btnEx.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LocalDateTime now = LocalDateTime.now();
				int year = now.getYear();
				int mm = now.getMonthValue();
				int dd = now.getDayOfMonth();
				int hh = now.getHour();
				int mi = now.getMinute();
				
				String fmt = "D:\\excel\\ ";
				String fmt2 = "jtable_%4d %02d %02d %2d %2d.xlsx";
				String filepath = String.format(fmt+fmt2, year, mm, dd, hh, mi );
				excelWrite(filepath);
				JOptionPane.showMessageDialog(btnEx, fmt +"로 엑셀파일 저장되었습니다");
				
			}
		});
		//테이블
		this.add(topPane, BorderLayout.NORTH);
		jtable = new JTable();
		jtable.setModel( 
				new DefaultTableModel(getDatalist(), getColumnlist() ) {
					@Override
					public boolean isCellEditable(int row, int column) {
						if (column == 1)
							return false;
						return false;
					}
				});
		jtable.addMouseListener(this);
		
		pane = new JScrollPane(jtable);
		this.add(pane);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500,150);
		setSize(600,500);
		setVisible(true);
	}


	private Vector< Vector> getDatalist() {
		
		UserDao ud = new UserDao();
		Vector<Vector> list = ud.getUserlist1();
		
		return list;
	}

	
	private Vector<String> getColumnlist() {
		Vector<String> cols = new Vector<String>();
		cols.add("아이디");
		cols.add("이름");
		cols.add("유형");
		cols.add("가입일");
		
		return cols;
	}
	
	
	//-----------------------------------------------------------------------
	//메인
	public static void main(String[] args) {
		ulist = new UserList ();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Vector<Vector> list = FindUser(this);
		jtableRe2(list);
		
	}

	//엑셀
	private void excelWrite(String filepath) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet       sheet = workbook.createSheet("data");
		
		getWorkbook_Data(sheet);
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( filepath );
			workbook.write(fos);
			System.out.println("저장완료");
		} catch (FileNotFoundException e) {
			System.out.println("저장fail");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fos != null)fos.close();
			} catch (IOException e) {
			}
		}
	}
	//엑셀
	private void getWorkbook_Data(XSSFSheet sheet) {
		XSSFRow   row = null;
		XSSFCell cell = null;
		
		Vector<String> cols = getColumnlist();
		row = sheet.createRow(0);
		for (int i = 0; i < cols.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(cols.get(i));
			
		}
		Vector<Vector> dataList =  getDatalist();
		for (int i = 0; i < dataList.size(); i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < dataList.get(i).size(); j++) {
				Vector v = dataList.get(i);
				    cell = row.createCell(j);
				    cell.setCellValue((String)v.get(j));
			}
		} 
	}
	//조회

	private Vector<Vector> FindUser(UserList userList ){
		UserDao ud = new UserDao();
		Vector<Vector> list2 = ud.getUserlist2(userList);
		return list2;
	}
	//새로고침
	public void jtableRe() {
		jtable.setModel(
				new DefaultTableModel(getDatalist(), getColumnlist()) {

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}});
		
					jtable.repaint();
		}
	//개별조회 새로고침
	private void jtableRe2(Vector<Vector> list) {
		jtable.setModel(
				new DefaultTableModel(list, getColumnlist()) {

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}});
		
					jtable.repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			int row = jtable.getSelectedRow();
			String id = (String) jtable.getValueAt(row, 0);
			if (pc != null)
				pc.dispose();
			pc = new Proc(id, this);
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	
}
	

