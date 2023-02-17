package lm.view;


import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//신강원
public class Login extends JFrame{

	JLabel lbl1 , lbl2,  lbl3;
	JButton btn1, btn2;
	JTextField tox;
	JComboBox com;
	JPasswordField ptox;
	JPanel p;
	GridBagLayout gb ;
	GridBagConstraints gbc;
	
	public Login() {
		inti();
	}
	
	
	private void inti() {

		setTitle("물류관리 프로그램");
		JPanel pp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		gb          = new GridBagLayout();
		getContentPane().setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
				
		JLabel lbl1 = new JLabel("아이디");
		tox = new JTextField(20);
		gbadd(lbl1, 0, 0, 1, 1);
		gbadd(tox,  1, 0, 3, 1);

		JLabel lbl2 = new JLabel("비밀번호");
		ptox = new JPasswordField(30);
		gbadd(lbl2, 0, 1, 1, 1);
		gbadd(ptox, 1, 1, 3, 1);
		
		JButton btn1 = new JButton("로그인");
		gbadd(btn1, 0,2,1,1);
		JButton btn2 = new JButton("회원가입");
		gbadd(btn2, 1,2,1,1 );
		 
		String [] type = {"관리자" , "일반사용자"};
		JLabel lbl3 = new JLabel ("타입");
		        com = new JComboBox(type);
		gbadd (lbl3, 2, 0, 3, 1);
		gbadd (com, 2, 1, 3, 1);
		
		
		
		
		setLocation(700,300);
		setSize(350,212);
		setVisible(true);
		
	
		
	}	
	

	private void gbadd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx       = x;
		gbc.gridy       = y;
		gbc.gridwidth   = w;
		gbc.gridheight  = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		getContentPane().add(c, gbc);
		
	}


	public static void main(String[] args) {
		
		new Login();
		
	}
}
