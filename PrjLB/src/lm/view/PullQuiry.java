package lm.view;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections4.Get;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import lm.model.ProductInquiryDAO;
import lm.model.PullquiryDAO;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class PullQuiry extends JFrame implements ActionListener{
	JTextField txt;
	JScrollPane sp1;
	ButtonGroup group;
	JTable jtb1;
	JLabel lalb2, lalb4;
	JComboBox combo;
	JRadioButton ra1,ra2,ra3;
	JButton btn1 , btn2, btn3;
	PullQuiry ev;
	private JScrollPane scrollPane;
	private JPanel panel;
	ImageIcon icon;
	private JLabel lblNewLabel;

	public JRadioButton getRa1() {
		return ra1;
	}
	public void setRa1(JRadioButton ra1) {
		this.ra1 = ra1;
	}
	public JRadioButton getRa2() {
		return ra2;
	}
	public void setRa2(JRadioButton ra2) {
		this.ra2 = ra2;
	}
	public JRadioButton getRa3() {
		return ra3;
	}
	public void setRa3(JRadioButton ra3) {
		this.ra3 = ra3;
	}
	public JComboBox getCombo() {

		return combo;
	}
	public void setCombo(JComboBox combo) {
		this.combo = combo;
	}
	public JTextField getTxt() {
		return txt;
	}
	public void setTxt(JTextField txt) {
		this.txt = txt;
	}
	public JButton getBtn1() {
		return btn1;
	}
	public void setBtn1(JButton btn1) {
		this.btn1 = btn1;
	}



	public PullQuiry() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/lmimage/alphabets-33744_640.png")));
		getContentPane().setBackground(new Color(231,231,231));
		init();


	}
	public int getSum(){
		int rowsCount = jtb1.getRowCount();
		int sum = 0;
		for(int i = 0; i < rowsCount; i++){
			sum = sum+Integer.parseInt(jtb1.getValueAt(i, 6).toString());
		}
		return sum;
	}

	public double getAverage(){
		int rowsCount1 = jtb1.getRowCount();
		double average = 0;
		double sum = 0;
		for(int i = 0; i < rowsCount1; i++){
			sum = sum + Double.parseDouble(jtb1.getValueAt(i, 4).toString());

		}
		average = sum/rowsCount1;

		return average;
	}


	private void init() {
		setTitle("상품전체조회");


		icon = new ImageIcon("./image/큰거1.png");

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};

		this.group        = new ButtonGroup();

		scrollPane = new JScrollPane();






		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
				);


		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		lalb2 = new JLabel();
		lalb2.setForeground(SystemColor.text);
		lalb2.setBounds(486, 522, 83, 27);
		panel.add(lalb2);
		lalb2.setFont(new Font("굴림", Font.PLAIN, 17));

		lalb4 = new JLabel();
		lalb4.setForeground(SystemColor.text);
		lalb4.setBounds(730, 526, 153, 20);
		panel.add(lalb4);
		lalb4.setFont(new Font("굴림", Font.PLAIN, 17));


		JLabel lalb1 = new JLabel("총 이익률 : ");
		lalb1.setForeground(SystemColor.text);
		lalb1.setBounds(348, 526, 84, 20);
		panel.add(lalb1);
		lalb1.setFont(new Font("굴림", Font.PLAIN, 17));

		JLabel lalb3 = new JLabel("총 재고금액 : ");
		lalb3.setForeground(SystemColor.text);
		lalb3.setBounds(617, 526, 100, 20);
		panel.add(lalb3);
		lalb3.setFont(new Font("굴림", Font.PLAIN, 17));

		btn3 = new JButton("상품개별조회");
		btn3.setIcon(new ImageIcon(PullQuiry.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btn3.setBounds(725, 94, 106, 32);
		btn3 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		panel.add(btn3);
		btn3.setFont(new Font("새굴림", Font.PLAIN, 12));



		btn2 = new JButton("\uC5D1\uC140\uB85C \uC800\uC7A5");
		btn2.setIcon(new ImageIcon(PullQuiry.class.getResource("/lmimage/5\uC790\uB9AC\uBC84\uD2BC.png")));
		btn2.setBounds(843, 94, 106, 32);
		btn2 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		panel.add(btn2);
		btn2.setFont(new Font("새굴림", Font.PLAIN, 12));


		btn1 = new JButton("조회");
		btn1.setIcon(new ImageIcon(PullQuiry.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btn1.setBounds(511, 94, 70, 32);
		btn1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		panel.add(btn1);
		btn1.setFont(new Font("새굴림", Font.PLAIN, 12));

		ra3 = new JRadioButton("전체조회");
		ra3.setBackground(SystemColor.window);
		ra3.setBounds(201, 26, 92, 27);
		panel.add(ra3);
		ra3.setFont(new Font("새굴림", Font.PLAIN, 15));
		ra3.setSelected(true);
		this.group.add(ra3);
		ra1 = new JRadioButton("상품분류");
		ra1.setBackground(SystemColor.window);
		ra1.setBounds(201, 62, 92, 27);
		panel.add(ra1);
		ra1.setFont(new Font("새굴림", Font.PLAIN, 15));
		this.group.add(ra1);
		ra2 = new JRadioButton("거래처명");
		ra2.setBackground(SystemColor.window);
		ra2.setBounds(201, 99, 92, 27);
		panel.add(ra2);
		ra2.setFont(new Font("새굴림", Font.PLAIN, 15));
		this.group.add(ra2);

		combo = new JComboBox();
		combo.setBounds(302, 63, 119, 24);
		panel.add(combo);
		combo.setFont(new Font("굴림", Font.PLAIN, 15));
		combo.setModel(new DefaultComboBoxModel(new String[] {"가공식품","기호식품","냉동냉장","주류"}));
		combo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btn1.doClick();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		combo.setEnabled(false);


		txt = new JTextField();
		txt.setBounds(301, 95, 201, 32);
		panel.add(txt);
		txt.setColumns(10);
		txt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btn1.doClick();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});



		txt.setEnabled(false);

		jtb1 = new JTable();

		sp1 = new JScrollPane(jtb1);
		sp1.setBounds(5, 158, 970, 351);
		panel.add(sp1);

		lblNewLabel = new JLabel("\uC0C1\uD488\uC804\uCCB4\uC870\uD68C");
		lblNewLabel.setFont(new Font("새굴림", Font.BOLD, 40));
		lblNewLabel.setBounds(703, 10, 391, 79);
		panel.add(lblNewLabel);
		ra2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) 
					combo.setEnabled(false);
				else 
					combo.setEnabled(true);
			}
		});

		ra2.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btn1.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		ra1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) 
					txt.setEnabled(false);
				else 
					txt.setEnabled(true);
			}
		});
		ra1.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btn1.doClick();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});




		ra3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					txt.setEnabled(false);
					combo.setEnabled(false);
				}else {
					txt.setEnabled(true);
					combo.setEnabled(true);
				}


			}
		});
		ra3.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btn1.doClick();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LMProductInquiry();

			}
		});
		getContentPane().setLayout(groupLayout);
		setVisible(true);
		setLocation(650,200);
		setSize(1000,600);
		setResizable(false);
	}




	private Vector<String> getColumnList() {
		Vector<String>  cols = new Vector<>();  // 문자배열 대신 사용
		cols.add("상품코드");
		cols.add("상품명");
		cols.add("입고가");
		cols.add("판매가");
		cols.add("이익률");
		cols.add("현재재고");
		cols.add("재고금액");
		cols.add("상품분류");
		cols.add("거래처명");
		return  cols;
	}


	public static void main(String[] args) {
		new PullQuiry();


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "조회" :
			PullquiryDAO dao = new PullquiryDAO(this);
			if(ra3.isSelected()) {
				Vector<Vector> list = dao.ra11();
				tablePaint(list);
				DecimalFormat dc = new DecimalFormat("###,###,###,###"+"원");
				String ch = dc.format(Double.parseDouble(String.valueOf(getSum())));
				lalb4.setText(String.valueOf(ch));
				double dd = (Double.parseDouble(String.valueOf(getAverage())));
				String ss = String.format("%.2f", dd );
				lalb2.setText(String.valueOf(ss) + "%");
			}
			if(ra1.isSelected()) {
				String combobox =comboboxString();
				Vector<Vector> list = dao.ra22(combobox);
				tablePaint(list);
				DecimalFormat dc = new DecimalFormat("###,###,###,###"+"원");
				String ch = dc.format(Double.parseDouble(String.valueOf(getSum())));
				lalb4.setText(String.valueOf(ch));
				double dd = (Double.parseDouble(String.valueOf(getAverage())));
				String ss = String.format("%.2f", dd );
				lalb2.setText(String.valueOf(ss) + "%");
			}
			if(ra2.isSelected()) {
				Vector<Vector> list =dao.ra33();
				tablePaint(list);
				DecimalFormat dc = new DecimalFormat("###,###,###,###"+"원");
				String ch = dc.format(Double.parseDouble(String.valueOf(getSum())));
				lalb4.setText(String.valueOf(ch));
				double dd = (Double.parseDouble(String.valueOf(getAverage())));
				String ss = String.format("%.2f", dd );
				lalb2.setText(String.valueOf(ss) + "%");

			}
			break;
		case "엑셀로 저장":
			LocalDateTime now = LocalDateTime.now();
			int year = now.getYear();
			int mm = now.getMonthValue();
			int dd = now.getDayOfMonth();
			int hh = now.getHour();
			int mi = now.getMinute();

			String fmt = "D:\\excel\\ ";
			String fmt2 = "상품목록_%4d %02d %02d %2d %2d.xlsx";
			String filepath = String.format(fmt+fmt2, year, mm, dd, hh, mi );
			excelWrite1(filepath);
			JOptionPane.showMessageDialog(btn2, fmt + "로 엑셀파일 저장되었습니다.");

			break;
		}
	}


	private String comboboxString() {
		String com =combo.getSelectedItem().toString();
		return com;
	}
	public void tablePaint(Vector<Vector> list) {
		jtb1.setModel(
				new DefaultTableModel( list , getColumnList() ) {				

					@Override
					public boolean isCellEditable(int row, int column) {

						return false;  
					}
				}	
				);

		jtb1.repaint();

	}
	//	private Vector<Vector> getDataList() {
	//		PullquiryDAO       dao   =  new PullquiryDAO();
	//		Vector<Vector>  list  =  dao.getMemberList();
	//		return  list;
	//	}

	private void excelWrite1(String filepath) {
		XSSFWorkbook  workbook =  new XSSFWorkbook();
		XSSFSheet     sheet    =  workbook.createSheet("Data");


		getWorkbook_Data(sheet);

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
		PullquiryDAO dao = new PullquiryDAO(this);



		int numcols = jtb1.getColumnCount();
		int numrows = jtb1.getRowCount();

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
				cell.setCellValue((String) jtb1.getValueAt(i, j));
			}
		}

	}




}