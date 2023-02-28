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

import lm.model.ShopDao;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

	public class ShopList extends JFrame implements ActionListener, MouseListener{
		
		private static JButton btnIn,  btnRe , btnEx, btnfind;
		private static JTextField txtname;
		private static JTable  jtable;
		private static JScrollPane pane;
		String sid ;
		ShopProc spc = null;
		static ShopList slist = null;
		UserTitle utl;
		private JScrollPane scrollPane;
		private JPanel panel;
		private JLabel lblNewLabel;
		private JLabel lblNewLabel_1;
		ImageIcon icon;
		
		public ShopList () {
			init ();
		}
		
		public ShopList(UserTitle utl) {
			this();
			this.utl = utl;
			btnIn.setVisible(false);
		}

		private void init() {
			setTitle( " 점포 목록 ");
			
			icon = new ImageIcon("./image/리스트들.png");
			
			JPanel panel = new JPanel() {
		         public void paintComponent(Graphics g) {
		        
		             g.drawImage(icon.getImage(), 0, 0, null);
		     
		             setOpaque(false);
		             super.paintComponent(g);
		            }
		      };
			
			scrollPane = new JScrollPane();
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
			);
			
			
			scrollPane.setViewportView(panel);
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
			panel.setLayout(null);
			
			pane = new JScrollPane(jtable);
			pane.setBounds(5, 142, 590, 350);
			panel.add(pane);
			btnfind = new JButton("조회");
			btnfind.setIcon(new ImageIcon(ShopList.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
			btnfind.setFont(new Font("새굴림", Font.PLAIN, 12));
			btnfind.setBounds(185, 100, 70, 32);
			btnfind .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
			panel.add(btnfind);
			
			//점포조회
			btnfind.addActionListener(this);
			btnEx   = new JButton("\uC5D1\uC140\uB85C \uC800\uC7A5");
			btnEx.setIcon(new ImageIcon(ShopList.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
			btnEx.setFont(new Font("새굴림", Font.PLAIN, 12));
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
					String fmt2 = "점포_%4d %02d %02d %2d %2d.xlsx";
					String filepath = String.format(fmt+fmt2, year, mm, dd, hh, mi );
					excelWrite(filepath);
					JOptionPane.showMessageDialog(btnEx, fmt +"로 엑셀파일 저장되었습니다");
					
				}
			});
			btnRe   = new JButton("새로고침");
			btnRe.setIcon(new ImageIcon(ShopList.class.getResource("/lmimage/4\uC790\uB9AC\uBC84\uD2BC.png")));
			btnRe.setFont(new Font("새굴림", Font.PLAIN, 12));
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
			btnIn   = new JButton("\uC810\uD3EC\uB4F1\uB85D");
			btnIn.setIcon(new ImageIcon(ShopList.class.getResource("/lmimage/4\uC790\uB9AC\uBC84\uD2BC.png")));
			btnIn.setFont(new Font("새굴림", Font.PLAIN, 12));
			btnIn.setBounds(369, 100, 93, 32);
			btnIn .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
			panel.add(btnIn);
			
			//점포가입
			btnIn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (spc != null)
						spc.dispose();
						spc = new ShopProc(slist);
				}
			});
			txtname = new JTextField (" "); 
			txtname.setBounds(61, 101, 118, 32);
			panel.add(txtname);
			txtname.setColumns(15);
			
			lblNewLabel = new JLabel("\uC810\uD3EC \uBAA9\uB85D");
			lblNewLabel.setFont(new Font("새굴림", Font.BOLD, 40));
			lblNewLabel.setBounds(229, 10, 261, 79);
			panel.add(lblNewLabel);
			
			lblNewLabel_1 = new JLabel("\uC810\uD3EC\uBA85 : ");
			lblNewLabel_1.setFont(new Font("새굴림", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(12, 100, 54, 32);
			panel.add(lblNewLabel_1);
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
			this.setLocation(500,150);
			setSize(620,543);
			setVisible(true);
			setResizable(false);
		}

		//전체 조회
		private Vector< Vector> getDatalist() {
			
			ShopDao sdao = new ShopDao(); 
			Vector<Vector> list = sdao.getshoplist1();
			
			return list;
		}

		//테이블 칼럼
		private Vector<String> getColumnlist() {
			Vector<String> cols = new Vector<String>();
			cols.add("점포코드");
			cols.add("점포명");
			cols.add("담당자");
			cols.add("담당자번호");
			
			return cols;
		}
		
		
		//-----------------------------------------------------------------------
		//메인
		public static void main (String[] args) {
			slist = new ShopList ();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String search = txtname.getText();
			Vector<Vector> list = FindShop(search);
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

			int numcols = jtable.getColumnCount();
			int numrows = jtable.getRowCount();

			//제목줄 처리
			Vector<String>  cols =  getColumnlist();
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
					cell.setCellValue((String) jtable.getValueAt(i, j));
				}
			}
		}
		
		//조회

		private Vector<Vector> FindShop(String search ){
			ShopDao sdao = new ShopDao();
			Vector<Vector> list2 = sdao.getShoplist2(search);
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
				String sid = (String) jtable.getValueAt(row, 0);
				if (spc != null)
					spc.dispose();
				spc = new ShopProc(sid, this);
				
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
