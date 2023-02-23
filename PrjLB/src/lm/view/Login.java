package lm.view;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lm.model.UserDao;
import oracle.jdbc.driver.OracleConnection;
//신강원
public class Login extends JFrame {
	
	private OracleConnection conn = null;

	String userid , userpw;
	JLabel lbl1 , lbl2;
	JButton btn1 ;
	JPanel p;
	private JTextField textField;
	private JPasswordField passwordField;
	UserDao dao ;
	
	public Login() {
		inti();
	}
	
	private void inti() {

		setTitle("\uADF8\uB9B0\uBB3C\uB958\uC2DC\uC2A4\uD15C");
		JPanel pp = new JPanel(new FlowLayout(FlowLayout.LEFT));
				
		JLabel lbl1 = new JLabel("아이디");
		lbl1.setBounds(42, 63, 93, 36);
		gbadd(lbl1, 0, 0, 1, 1);
		
		JLabel lblNewLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel.setBounds(42, 108, 93, 56);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(147, 71, 123, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btn1 = new JButton("\uB85C\uADF8\uC778");
		btn1.setBounds(108, 191, 97, 30);
		getContentPane().add(btn1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(147, 126, 123, 21);
		getContentPane().add(passwordField);

		this.passwordField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btn1.doClick();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String userid = textField.getText();
				String userpw = passwordField.getText();
				UserDao dao = UserDao.getInstance();
				int result = dao.findIdAndPw(userid, userpw);
				
					if(result == 1 ) {						
					JOptionPane.showMessageDialog(null, "로그인 성공");
					ManagerTitle mtl = new ManagerTitle(userid);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "실패 : "
							+ "아이디와 비밀번호를 확인해주세요");
				}
			}
		});
		
		setLocation(700,300);
		setSize(350,316);
		setVisible(true);
		
	}	
	
	private void gbadd(JComponent c, int x, int y, int w, int h) {
		getContentPane().setLayout(null);
		getContentPane().add(c);
		
	}

	public static void main(String[] args) {
		
		new Login();
	}

}
