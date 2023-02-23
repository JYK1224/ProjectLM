package lm.view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import lm.model.ProductInquiryDAO;

public class LMProductInquiry extends JFrame implements ActionListener{
	
	//fields
	JLabel jlb1, jlb2, jlb3, jlb4, jlb5, jlb6, jlb7, jlb8, jlb9, jlb10, jlbvat, jlb23;
	JPanel p;
	JRadioButton code;
	JRadioButton name;
	JTextField txt;
	JLabel tab1, tab2, tab3, tab4, tab5, tab7, tab8, tab9, tab10;
	ButtonGroup group;
	JButton btn;
	
	
	//Getter Setter
	public JRadioButton getCode() {
		return code;
	}
	
	public void setCode(JRadioButton code) {
		this.code = code;
	}
	
	public JRadioButton getName2() {
		return name;
	}
	
	public void setName(JRadioButton name) {
		this.name = name;
	}
	
	public JTextField getTxt() {
		return txt;
	}

	public void setTxt(JTextField txt) {
		this.txt = txt;
	}

	public JLabel getTab1() {
		return tab1;
	}

	public void setTab1(JLabel tab1) {
		this.tab1 = tab1;
	}

	public JLabel getTab2() {
		return tab2;
	}

	public void setTab2(JLabel tab2) {
		this.tab2 = tab2;
	}

	public JLabel getTab3() {
		return tab3;
	}

	public void setTab3(JLabel tab3) {
		this.tab3 = tab3;
	}

	public JLabel getTab4() {
		return tab4;
	}

	public void setTab4(JLabel tab4) {
		this.tab4 = tab4;
	}

	public JLabel getTab5() {
		return tab5;
	}

	public void setTab5(JLabel tab5) {
		this.tab5 = tab5;
	}

	public JLabel getTab7() {
		return tab7;
	}

	public void setTab7(JLabel tab7) {
		this.tab7 = tab7;
	}

	public JLabel getTab8() {
		return tab8;
	}

	public void setTab8(JLabel tab8) {
		this.tab8 = tab8;
	}

	public JLabel getTab9() {
		return tab9;
	}

	public void setTab9(JLabel tab9) {
		this.tab9 = tab9;
	}

	public JLabel getTab10() {
		return tab10;
	}

	public void setTab10(JLabel tab10) {
		this.tab10 = tab10;
	}

	GridBagLayout        gb;
	GridBagConstraints   gbc;
	
	public LMProductInquiry() {
		init();
	}
	
	private void init() {
		gb           =  new GridBagLayout();
		getContentPane().setLayout(gb);
		
		gbc          =  new GridBagConstraints();
		gbc.fill     =  GridBagConstraints.BOTH;
		gbc.weightx  =  1.0; 
		gbc.weighty  =  1.0;

	setTitle("상품상세조회");
	
	
	
	//상품코드,상품명 라디오버튼
	
	code = new JRadioButton("상품코드");
	code.setFont(new Font("굴림", Font.PLAIN, 15));
	name = new JRadioButton("상품명");
	name.setFont(new Font("굴림", Font.PLAIN, 15));
	this.group        = new ButtonGroup();
	this.group.add(code);
	this.group.add(name);
	//JPanel   radi  =  new JPanel( new FlowLayout( FlowLayout.LEFT  ) );
	code.setHorizontalAlignment(SwingConstants.CENTER);
	name.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd(code, 0, 0, 1, 1);
	gbAdd(name, 1,0,1,1);
	code.setSelected(true);
	


	//상품검색
	txt = new JTextField(10);
	txt.setFont(new Font("굴림", Font.PLAIN, 15));
	
	gbAdd( txt, 2, 0, 1, 1);
	txt.addKeyListener(new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER ) {
	               btn.doClick(); 
	            }
		}
		@Override
		public void keyPressed(KeyEvent e) {}
	});
	
	
	//상품검색버튼
	btn = new JButton("조회");
	btn.setFont(new Font("굴림", Font.PLAIN, 15));
	getContentPane().add(btn);
	gbAdd( btn, 3, 0, 1, 1);
	btn.addActionListener(this);
	
	

	//상품코드
	jlb1 = new JLabel("상품코드");
	jlb1.setFont(new Font("굴림", Font.PLAIN, 15));
	jlb1.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlb1, 0, 1, 1, 1);
	tab1 = new JLabel();
	tab1.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( tab1, 1, 1, 1, 1);
	
	//거래처코드
	jlb2 = new JLabel("거래처코드");
	jlb2.setFont(new Font("굴림", Font.PLAIN, 15));
	jlb2.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlb2, 2, 1, 1, 1);
	tab2 = new JLabel();
	tab2.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( tab2, 3, 1, 1, 1);
	
	//상품명
	jlb3 = new JLabel("상품명");
	jlb3.setFont(new Font("굴림", Font.PLAIN, 15));
	jlb3.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlb3, 0, 2, 1, 1);
	tab3 = new JLabel();
	tab3.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( tab3, 1, 2, 1, 1);
	
	//거래처명
	jlb4 = new JLabel("거래처명");
	jlb4.setFont(new Font("굴림", Font.PLAIN, 15));
	jlb4.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlb4, 2, 2, 1, 1);
	tab4 = new JLabel();
	tab4.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( tab4, 3, 2, 1, 1);
	
	//입고가
	jlb5 = new JLabel("입고가");
	jlb5.setFont(new Font("굴림", Font.PLAIN, 15));
	jlb5.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlb5, 0, 3, 1, 1);
	tab5 = new JLabel();
	tab5.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( tab5, 1, 3, 1, 1);

	//vat
	jlb6 = new JLabel("VAT");
	jlb6.setFont(new Font("굴림", Font.PLAIN, 15));
	jlb6.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlb6, 2, 3, 1, 1);
	jlbvat = new JLabel(" 10 % ");
	jlbvat.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlbvat, 3, 3, 1, 1);
	
	//판매가
	jlb7 = new JLabel("판매가");
	jlb7.setFont(new Font("굴림", Font.PLAIN, 15));
	jlb7.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlb7, 0, 4, 1, 1);
	tab7 = new JLabel();
	tab7.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( tab7, 1, 4, 1, 1);
	
	//재고
	jlb8 = new JLabel("재고");
	jlb8.setFont(new Font("굴림", Font.PLAIN, 15));
	jlb8.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlb8, 2, 4, 1, 1);
	tab8 = new JLabel();
	tab8.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( tab8, 3, 4, 1, 1);

	//이익률
	jlb9 = new JLabel("이익률");
	jlb9.setFont(new Font("굴림", Font.PLAIN, 15));
	jlb9.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlb9, 0, 5, 1, 1);
	tab9 = new JLabel();
	tab9.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( tab9, 1, 5, 1, 1);

	//데파트
	jlb10 = new JLabel("상품분류");
	jlb10.setFont(new Font("굴림", Font.PLAIN, 15));
	jlb10.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( jlb10, 2, 5, 1, 1);
	tab10 = new JLabel();
	tab10.setHorizontalAlignment(SwingConstants.CENTER);
	gbAdd( tab10, 3, 5, 1, 1);

	jlb23 = new JLabel(" ");
	gbAdd( jlb23, 4, 6, 1, 1);
	
		
	setLocation(800, 250);
	setSize(600,200);
	setVisible(true);
	setResizable(false);
		
		
		
	}
	
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx        = x;
		gbc.gridy        = y;
		gbc.gridwidth    = w;
		gbc.gridheight   = h;
		gb.setConstraints(c, gbc);
		gbc.insets       = new Insets(2, 2, 2, 2);
		getContentPane().add( c, gbc );
	}
	
	public static void main(String[] args) {
		new LMProductInquiry();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ProductInquiryDAO dao = new ProductInquiryDAO(this);
		
	}

}
