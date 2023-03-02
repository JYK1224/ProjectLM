package lm.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lm.model.LoginVo;
import lm.model.UserDao;
import oracle.jdbc.driver.OracleConnection;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
//신강원
public class Login extends JFrame {
	
	private OracleConnection conn = null;
	JLabel lblNewLabel_1;
	String userid , userpw;
	JLabel lbl1 , lbl2;
	JButton btn1 ;
	JPanel p;
	private JTextField textField;
	private JPasswordField passwordField;
	UserDao dao ;
	LoginVo vo;

	
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/lmimage/alphabets-33744_640.png")));
		getContentPane().setBackground(new Color(231,231,231));
		inti();
	}
	
	private void inti() {

		setTitle("\uADF8\uB9B0\uBB3C\uB958\uC2DC\uC2A4\uD15C");
		JPanel pp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
				
		JLabel lbl1 = new JLabel("아이디 :");
		lbl1.setForeground(Color.WHITE);
		lbl1.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl1.setFont(new Font("굴림", Font.BOLD, 17));
		lbl1.setBounds(70, 268, 67, 30);
		gbadd(lbl1, 0, 0, 1, 1);
		
		JLabel lblNewLabel = new JLabel("비밀번호 :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 17));
		lblNewLabel.setBounds(70, 297, 110, 30);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(182, 276, 150, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 304, 150, 21);
		getContentPane().add(passwordField);
		
		JButton btn1 = new JButton("");
		btn1.setIcon(new ImageIcon(Login.class.getResource("/lmimage/\uBC84\uD2BC222.png")));
		btn1.setHorizontalAlignment(SwingConstants.LEADING);
		btn1.setBounds(340, 276, 138, 51);
		btn1.setBorderPainted(false);
		btn1.setFocusable(false);
		btn1.setContentAreaFilled(false);
		btn1.setFocusPainted(false);
		
		getContentPane().add(btn1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/lmimage/logistics-7721552_640.jpg")));
		lblNewLabel_2.setBounds(0, -24, 494, 268);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("d");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/lmimage/texture-990104_640.jpg")));
		lblNewLabel_3.setBounds(-12, 215, 506, 146);
		getContentPane().add(lblNewLabel_3);
		
	
	
		passwordField.addKeyListener(new KeyListener() {
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
				int result1 = dao.findIdAndPw(userid, userpw);
				int result2 = dao.findTy(userid);
					if(result1 == 1 ) {
						if(result2 == 1 ) {
							JDialog jd = new JDialog(0);
							jd.Dlbl.setText("환영합니다 관리자");
							jd.setAlwaysOnTop(true);
							jd.btnNewButton.addActionListener(new ActionListener() {								
								@Override
								public void actionPerformed(ActionEvent e) {
									jd.dispose();
									ManagerTitle mtl = new ManagerTitle(userid);									
								}
							});
						}else {
							JDialog jd = new JDialog(0);
							jd.Dlbl.setText("환영합니다 사용자");
							jd.setAlwaysOnTop(true);
							jd.btnNewButton.addActionListener(new ActionListener() {								
								@Override
								public void actionPerformed(ActionEvent e) {
									jd.dispose();
									UserTitle utl = new UserTitle(userid);									
								}
							});							
						}

			
						 
						 
						dispose();
				}else{
					
					JDialog jd = new JDialog(0);
					jd.Dlbl.setText("<html><body><center>실패 : 아이디와 비밀번호를<br>확인해주세요</center></body></html>");
					jd.setAlwaysOnTop(true);
					
					
					textField.grabFocus();
					textField.setText("");
					passwordField.setText("");
				}
			}
		});
		
		setLocation(700,300);
		setSize(500,400);
		setVisible(true);
		setResizable(false);
	}	
	
	private void gbadd(JComponent c, int x, int y, int w, int h) {
		getContentPane().setLayout(null);
		getContentPane().add(c);
		
	}

	public static void main(String[] args) {
		
		new Login();
		
	}
}

