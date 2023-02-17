package lm.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;

// 작성자 :조용국 
public class LMStartMain extends JFrame {
	
	//필요한 부품 준비
	
	JPanel  p1,p2;
	JLabel  lbl;
	JButton btn1,btn2,btn3,btn4,btn5,btn6;
	//Gridlayout
	
	public LMStartMain() {
		init();
	}
	
	private void init() {
		this.setTitle("물류 관리 프로그램");
		
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new GridLayout(3,2,30,30));
		
		lbl = new JLabel("관리메뉴");
		lbl.setVerticalAlignment(SwingConstants.TOP);
		lbl.setFont(new Font("돋움", Font.BOLD, 24));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		p1.add(lbl);
		p1.setPreferredSize(new Dimension(500,50));
		
		
		btn1 = new JButton("상품 등록");
		btn2 = new JButton("상품 조회");
		btn3 = new JButton("발주");
		btn4 = new JButton("검수");
		btn5 = new JButton("발주 기록 검색");
		btn6 = new JButton("전체 상품 조회");	
		
		p2.add(btn1);
		p2.add(btn2);
		p2.add(btn3);
		p2.add(btn4);
		p2.add(btn5);
		p2.add(btn6);
		
		this.add("North" , p1);
		this.add("Center", p2);

		
		
		this.setSize(500,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new LMStartMain();
		
	}

}
