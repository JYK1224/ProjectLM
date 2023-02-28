package lm.view;
import javax.swing.JFrame;

import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;

import lm.model.ProductInquiryDAO;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class LMProductInquiry extends JFrame implements ActionListener{
	ImageIcon icon;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	JLabel jlb1, jlb2, jlb3, jlb4, jlb5, jlb6, jlb7, jlb8, jlb9, jlb10,  jlb23;
	JPanel p;
	JRadioButton code;
	JRadioButton name;
	JTextField txt;
	JLabel tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9, tab10;
	ButtonGroup group;
	JButton btn;
	private JLabel lblNewLabel;
	
	public JRadioButton getCode() {
		return code;	}
		public void setCode(JRadioButton code) {
		this.code = code;	}
		public JRadioButton getName2() {
		return name;	}
		public void setName(JRadioButton name) {
		this.name = name;	}
		public JTextField getTxt() {
		return txt;	}
	public void setTxt(JTextField txt) {
		this.txt = txt;	}
	public JLabel getTab1() {
		return tab1;	}
	public void setTab1(JLabel tab1) {
		this.tab1 = tab1;	}
	public JLabel getTab2() {
		return tab2;	}
	public void setTab2(JLabel tab2) {
		this.tab2 = tab2;	}
	public JLabel getTab3() {
		return tab3;	}
	public void setTab3(JLabel tab3) {
		this.tab3 = tab3;	}
	public JLabel getTab4() {
		return tab4;	}
	public void setTab4(JLabel tab4) {
		this.tab4 = tab4;	}
	public JLabel getTab5() {
		return tab5;	}
	public void setTab5(JLabel tab5) {
		this.tab5 = tab5;	}
	public void setTab6(JLabel tab6) {
		this.tab6 = tab6;	}
	public JLabel getTab6() {
		return tab6;	}
	public JLabel getTab7() {
		return tab7;	}
	public void setTab7(JLabel tab7) {
		this.tab7 = tab7;	}
	public JLabel getTab8() {
		return tab8;	}
	public void setTab8(JLabel tab8) {
		this.tab8 = tab8;	}
	public JLabel getTab9() {
		return tab9; }
	public void setTab9(JLabel tab9) {
		this.tab9 = tab9;	}
	public JLabel getTab10() {
		return tab10;	}
	public void setTab10(JLabel tab10) {
		this.tab10 = tab10;	}
	public LMProductInquiry() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/lmimage/alphabets-33744_640.png")));
		getContentPane().setBackground(new Color(231,231,231));
		init();
	}

	private void init() {
		setTitle("상품상세조회");
		
		icon = new ImageIcon("./image/신규거래처조회111.png");
	      
	      JPanel panel = new JPanel() {
	         public void paintComponent(Graphics g) {
	            
	             g.drawImage(icon.getImage(), 0, 0, null);
	            
	             setOpaque(false);
	             super.paintComponent(g);
	            }
	      };
	      
		setSize(454,454);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
		);
		
	
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		code = new JRadioButton("상품코드");
		code.setFont(new Font("새굴림", Font.PLAIN, 12));
		code.setBackground(SystemColor.window);
		buttonGroup.add(code);
		code.setBounds(130, 51, 84, 23);
		panel.add(code);
		
		name = new JRadioButton("상품명");
		name.setFont(new Font("새굴림", Font.PLAIN, 12));
		name.setBackground(SystemColor.window);
		buttonGroup.add(name);
		name.setBounds(130, 84, 84, 23);
		panel.add(name);
		
		txt = new JTextField();
		txt.setBounds(222, 83, 116, 25);
		panel.add(txt);
		txt.setColumns(10);
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
		
		btn = new JButton("조회");
		btn.setIcon(new ImageIcon(LMProductInquiry.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btn.setBounds(351, 80, 70, 32);
		btn .setHorizontalTextPosition(JButton.CENTER);
		panel.add(btn);
		btn.addActionListener(this);
		
		jlb1 = new JLabel("상품코드");
		jlb1.setForeground(SystemColor.text);
		jlb1.setFont(new Font("새굴림", Font.PLAIN, 13));
		jlb1.setBounds(31, 139, 57, 15);
		panel.add(jlb1);
		
		jlb2 = new JLabel("상품명");
		jlb2.setForeground(SystemColor.text);
		jlb2.setFont(new Font("새굴림", Font.PLAIN, 13));
		jlb2.setBounds(31, 164, 57, 15);
		panel.add(jlb2);
		
		jlb3 = new JLabel("입고가");
		jlb3.setForeground(SystemColor.text);
		jlb3.setFont(new Font("새굴림", Font.PLAIN, 13));
		jlb3.setBounds(31, 189, 57, 15);
		panel.add(jlb3);
		
		jlb4 = new JLabel("판매가");
		jlb4.setForeground(SystemColor.text);
		jlb4.setFont(new Font("새굴림", Font.PLAIN, 13));
		jlb4.setBounds(31, 214, 57, 15);
		panel.add(jlb4);
		
		jlb5 = new JLabel("이익률");
		jlb5.setForeground(SystemColor.text);
		jlb5.setFont(new Font("새굴림", Font.PLAIN, 13));
		jlb5.setBounds(31, 238, 57, 15);
		panel.add(jlb5);
		
		jlb6 = new JLabel("거래처코드");
		jlb6.setForeground(SystemColor.text);
		jlb6.setFont(new Font("새굴림", Font.PLAIN, 13));
		jlb6.setBounds(31, 263, 70, 15);
		panel.add(jlb6);
		
		jlb7 = new JLabel("거래처명");
		jlb7.setForeground(SystemColor.text);
		jlb7.setFont(new Font("새굴림", Font.PLAIN, 13));
		jlb7.setBounds(31, 288, 57, 15);
		panel.add(jlb7);
		
		jlb8 = new JLabel("VAT");
		jlb8.setForeground(SystemColor.text);
		jlb8.setFont(new Font("새굴림", Font.PLAIN, 13));
		jlb8.setBounds(31, 313, 57, 15);
		panel.add(jlb8);
		
		jlb9 = new JLabel("재고");
		jlb9.setForeground(SystemColor.text);
		jlb9.setFont(new Font("새굴림", Font.PLAIN, 13));
		jlb9.setBounds(31, 338, 57, 15);
		panel.add(jlb9);
		
		jlb10 = new JLabel("상품분류");
		jlb10.setForeground(SystemColor.text);
		jlb10.setFont(new Font("새굴림", Font.PLAIN, 13));
		jlb10.setBounds(31, 361, 57, 15);
		panel.add(jlb10);
		
		tab1 = new JLabel();
		tab1.setFont(new Font("새굴림", Font.PLAIN, 13));
		tab1.setBounds(178, 139, 128, 15);
		panel.add(tab1);
		
		tab2 = new JLabel();
		tab2.setFont(new Font("새굴림", Font.PLAIN, 13));
		tab2.setBounds(178, 164, 57, 15);
		panel.add(tab2);
		
		tab3 = new JLabel();
		tab3.setFont(new Font("새굴림", Font.PLAIN, 13));
		tab3.setBounds(178, 189, 57, 15);
		panel.add(tab3);
		
		tab4 = new JLabel();
		tab4.setFont(new Font("새굴림", Font.PLAIN, 13));
		tab4.setBounds(178, 214, 57, 15);
		panel.add(tab4);
		
		tab5 = new JLabel();
		tab5.setFont(new Font("새굴림", Font.PLAIN, 13));
		tab5.setBounds(178, 238, 57, 15);
		panel.add(tab5);
		
		tab6 = new JLabel();
		tab6.setFont(new Font("새굴림", Font.PLAIN, 13));
		tab6.setBounds(178, 263, 57, 15);
		panel.add(tab6);
		
		tab7 = new JLabel();
		tab7.setFont(new Font("새굴림", Font.PLAIN, 13));
		tab7.setBounds(178, 288, 57, 15);
		panel.add(tab7);
		
		tab8 = new JLabel("10%");
		tab8.setFont(new Font("새굴림", Font.PLAIN, 13));
		tab8.setBounds(178, 313, 57, 15);
		panel.add(tab8);
		
		tab9 = new JLabel();
		tab9.setFont(new Font("새굴림", Font.PLAIN, 13));
		tab9.setBounds(178, 338, 57, 15);
		panel.add(tab9);
		
		tab10 = new JLabel();
		tab10.setFont(new Font("새굴림", Font.PLAIN, 13));
		tab10.setBounds(178, 361, 57, 15);
		panel.add(tab10);
		
		lblNewLabel = new JLabel("\uC0C1\uD488\uC0C1\uC138\uC870\uD68C");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(222, 10, 223, 64);
		panel.add(lblNewLabel);
		
		
		
		getContentPane().setLayout(groupLayout);
		 setLocation(650,200);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new LMProductInquiry();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ProductInquiryDAO dao = new ProductInquiryDAO(this);
	}

	
}
