package lm.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class LMProductInquiry extends JFrame{
	
	JLabel jlb1, jlb2, jlb3, jlb4, jlb5, jlb6;
	JPanel p;
	JRadioButton code, name;
	JTextField txt;
	JTable tab1, tab2, tab3, tab4, tab5, tab6;
	ButtonGroup group;
	JButton btn;
	
	GridBagLayout        gb;
	GridBagConstraints   gbc;
	
	public LMProductInquiry() {
		
		init();
		
		
	}

	private void init() {

	getContentPane().setLayout(new FlowLayout());
	setTitle("상품개별조회");
	
	
	
	//상품코드,상품명 라디오버튼
	
	code = new JRadioButton("상품코드");
	code.setFont(new Font("굴림", Font.PLAIN, 15));
	name = new JRadioButton("상품명");
	name.setFont(new Font("굴림", Font.PLAIN, 15));
	this.group        = new ButtonGroup();
	this.group.add(code);
	this.group.add(name);
	getContentPane().add(code);
	getContentPane().add(name);
	//상품검색
	txt = new JTextField(10);
	txt.setFont(new Font("굴림", Font.PLAIN, 15));
	getContentPane().add(txt) ;
	
	//상품검색버튼
	btn = new JButton("조회");
	btn.setFont(new Font("굴림", Font.PLAIN, 15));
	getContentPane().add(btn);
	
	
	gb           =  new GridBagLayout();
	this.setLayout(gb);
	
	gbc          =  new GridBagConstraints();
	gbc.fill     =  GridBagConstraints.BOTH;
	gbc.weightx  =  1.0; 
	gbc.weighty  =  1.0;
	
	
	//상품코드
	jlb1 = new JLabel("상품코드");
	jlb1.setFont(new Font("굴림", Font.PLAIN, 15));
	getContentPane().add(jlb1);
	tab1 = new JTable();
	getContentPane().add(tab1);
	//gbAdd( jlb1, 0, 0, 1, 1);
	//gbAdd( tab1, 1, 0, 3, 1);
	
	//입고가
	jlb2 = new JLabel("입고가");
	jlb2.setFont(new Font("굴림", Font.PLAIN, 15));
	getContentPane().add(jlb2);
	tab2 = new JTable();
	getContentPane().add(tab2);
	
	//상품명
	jlb3 = new JLabel("상품명");
	jlb3.setFont(new Font("굴림", Font.PLAIN, 15));
	getContentPane().add(jlb3);
	tab3 = new JTable();
	getContentPane().add(tab3);
	
	//판매가
	jlb4 = new JLabel("판매가");
	jlb4.setFont(new Font("굴림", Font.PLAIN, 15));
	getContentPane().add(jlb4);
	tab4 = new JTable();
	getContentPane().add(tab4);
	
	
	

/*	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx        = x;
		gbc.gridy        = y;
		gbc.gridwidth    = w;
		gbc.gridheight   = h;
		gb.setConstraints(c, gbc);
		gbc.insets       = new Insets(2, 2, 2, 2);
		this.add( c, gbc );*/
	
	setLocation(800, 300);
	setSize(400,600);
	setVisible(true);
	setResizable(false);
	
	
	}
	public static void main(String[] args) {
		new LMProductInquiry();
		
	}
}

