package lm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
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
import java.awt.Font;
import javax.swing.JLabel;

public class UserList extends JFrame implements ActionListener, MouseListener{
	
	private static JButton btnIn,  btnRe , btnEx, btnfind;
	private static JTextField txtname;
	private static JTable  jtable;
	private static JScrollPane pane;
	ImageIcon icon;
	
	Proc pc = null;
	static UserList ulist = null;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	public UserList () {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/lmimage/alphabets-33744_640.png")));
		getContentPane().setBackground(new Color(231,231,231));
		init ();
	}
	
	
	private void init() {
		setTitle( "사용자관리");
		
		icon = new ImageIcon("./image/리스트들.png");
		
		JPanel panel = new JPanel() {
	         public void paintComponent(Graphics g) {
	        
	             g.drawImage(icon.getImage(), 0, 0, null);
	     
	             setOpaque(false);
	             super.paintComponent(g);
	            }
	      };
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
		);
		

		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		btnEx   = new JButton("엑셀로 저장");
		btnEx.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnEx.setIcon(new ImageIcon(UserList.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btnEx.setBounds(474, 100, 106, 32);
		btnEx .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		panel.add(btnEx);
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
				String fmt2 = "사용자_%4d %02d %02d %2d %2d.xlsx";
				String filepath = String.format(fmt+fmt2, year, mm, dd, hh, mi );
				excelWrite(filepath);
				JOptionPane.showMessageDialog(btnEx, fmt +"로 엑셀파일 저장되었습니다");
				
			}
		});
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
		pane.setBounds(5, 142, 590, 350);
		panel.add(pane);
		pane.setPreferredSize(new Dimension(100, 50));
		txtname = new JTextField (" ");
		txtname.setBounds(55, 101, 118, 32);
		panel.add(txtname);
		txtname.setColumns(15);
		btnRe   = new JButton("새로고침");
		btnRe.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnRe.setIcon(new ImageIcon(UserList.class.getResource("/lmimage/4\uC790\uB9AC\uBC84\uD2BC.png")));
		btnRe.setBounds(267, 100, 93, 32);
		btnRe .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		panel.add(btnRe);
		//새로고침
		btnRe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtableRe();
			}
		});
		btnIn   = new JButton("회원가입");
		btnIn.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnIn.setIcon(new ImageIcon(UserList.class.getResource("/lmimage/4\uC790\uB9AC\uBC84\uD2BC.png")));
		btnIn.setBounds(369, 100, 93, 32);
		btnIn .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		panel.add(btnIn);
		
		//회원가입
		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pc != null)
					pc.dispose();
					pc = new Proc(ulist);
			}
		});
		btnfind = new JButton("조회");
		btnfind.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnfind.setIcon(new ImageIcon(UserList.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnfind.setBounds(185, 100, 70, 32);
		btnfind .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		panel.add(btnfind);
		
		lblNewLabel = new JLabel("사용자 관리");
		lblNewLabel.setFont(new Font("새굴림", Font.BOLD, 40));
		lblNewLabel.setBounds(229, 10, 261, 79);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("  \uC774\uB984 : ");
		lblNewLabel_1.setFont(new Font("새굴림", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(5, 100, 44, 32);
		panel.add(lblNewLabel_1);
		
		//회원조회
		btnfind.addActionListener(this);
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
		getContentPane().setLayout(groupLayout);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this. setLocation(650,200);
		setSize(620,543);
		setVisible(true);
		setResizable(false);
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
		String search = txtname.getText();
		Vector<Vector> list = FindUser(search);
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
		String search = txtname.getText();
		
		Vector<String> cols = getColumnlist();
		row = sheet.createRow(0);
		for (int i = 0; i < cols.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(cols.get(i));
			
		}
		if(txtname.getText() == null) {
			Vector<Vector> dataList1 =  getDatalist();
			for (int i = 0; i < dataList1.size(); i++) {
				row = sheet.createRow(i + 1);
				for (int j = 0; j < dataList1.get(i).size(); j++) {
					Vector v = dataList1.get(i);
					    cell = row.createCell(j);
					    cell.setCellValue((String)v.get(j));
				}
			} 
		}else {
			Vector<Vector> dataList2 = FindUser(search);
			for (int i = 0; i < dataList2.size(); i++) {
				row = sheet.createRow(i + 1);
				for (int j = 0; j < dataList2.get(i).size(); j++) {
					Vector v = dataList2.get(i);
					cell = row.createCell(j);
					cell.setCellValue((String)v.get(j));
				}
			} 
		}
	}
	//조회

	private Vector<Vector> FindUser(String search ){
		UserDao ud = new UserDao();
		Vector<Vector> list2 = ud.getUserlist2(search);
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
	//마우스 이벤트 - 두번클릭으로 회원 상세보기
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
	

