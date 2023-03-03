package lm.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import lm.model.UserDao;
import lm.model.Uservo;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;

public class Proc extends JFrame{
	private JTextField txtid, txtname, txtindate ;
	private JPasswordField txtp;
	private JTextArea intro;
	private ButtonGroup group;
	private JRadioButton user, manager;	
	private JButton btnFn ;
	UserList uList = null;
	ImageIcon icon;
	UserDao udao;
	//생성자
	public Proc () {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/lmimage/alphabets-33744_640.png")));
		getContentPane().setBackground(new Color(231,231,231));
		init();
	}
	public Proc (UserList ulist) {
		this();
		this.uList = ulist ;
	}
	public Proc (String id, UserList userList) {
		this ();
		this.uList = userList;
		txtid.setText(id);
		btnFn.doClick();
	}
	
	
	private void init() {
		
		setTitle("그린물류시스템");
		setResizable(false);
		 icon = new ImageIcon(Proc.class.getResource("/lmimage/신규거래처조회111.png"));
	      
	      JPanel panel = new JPanel() {
	         public void paintComponent(Graphics g) {
	             g.drawImage(icon.getImage(), 0, 0, null);
	             setOpaque(false);
	             super.paintComponent(g);
	            }
	      };
		
		setSize(454,454);
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		group = new ButtonGroup();
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
		);
		
		
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		JButton btnCn = new JButton("취소");
		btnCn.setIcon(new ImageIcon(Proc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnCn.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnCn.setBounds(258, 371, 70, 32);
		btnCn.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnCn);
		
		btnFn = new JButton("조회");
		btnFn.setIcon(new ImageIcon(Proc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnFn.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnFn.setBounds(340, 371, 70, 32);
		btnFn.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnFn);
		JButton btnUp = new JButton("수정");
		btnUp.setIcon(new ImageIcon(Proc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnUp.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnUp.setBounds(94, 371, 70, 32);
		btnUp.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnUp);
		JButton btnDe = new JButton("삭제");
		btnDe.setIcon(new ImageIcon(Proc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnDe.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnDe.setBounds(176, 371, 70, 32);
		btnDe.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnDe);
		
		JButton btnIn = new JButton("등록");
		btnIn.setIcon(new ImageIcon(Proc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnIn.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnIn.setBounds(12, 371, 70, 32);
		btnIn.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnIn);
		txtindate = new JTextField();
		txtindate.setBounds(182, 330, 186, 33);
		panel.add(txtindate);
		txtindate.setColumns(10);
		txtindate.setText(today);
		txtindate.setEditable(false);
		JLabel lblindate = new JLabel("가입일");
		lblindate.setFont(new Font("새굴림", Font.PLAIN, 12));
		lblindate.setForeground(SystemColor.text);
		lblindate.setBounds(29, 332, 81, 29);
		panel.add(lblindate);
		
		JLabel lblid = new JLabel("아이디(사번)");
		lblid.setFont(new Font("새굴림", Font.PLAIN, 12));
		lblid.setForeground(SystemColor.text);
		lblid.setBounds(29, 129, 81, 25);
		panel.add(lblid);
		
		txtid = new JTextField();
		txtid.setBounds(182, 129, 202, 25);
		panel.add(txtid);
		txtid.setColumns(10);
		JLabel lblpw = new JLabel("비밀번호");
		lblpw.setFont(new Font("새굴림", Font.PLAIN, 12));
		lblpw.setForeground(SystemColor.text);
		lblpw.setBounds(29, 164, 81, 25);
		panel.add(lblpw);
		JLabel lblname = new JLabel("이름");
		lblname.setFont(new Font("새굴림", Font.PLAIN, 12));
		lblname.setForeground(SystemColor.text);
		lblname.setBounds(29, 199, 81, 25);
		panel.add(lblname);
		txtname = new JTextField();
		txtname.setBounds(182, 199, 202, 25);
		panel.add(txtname);
		txtname.setColumns(10);
		txtp = new JPasswordField();
		txtp.setBounds(182, 164, 202, 25);
		panel.add(txtp);
		JLabel lblty = new JLabel("유형");
		lblty.setFont(new Font("새굴림", Font.PLAIN, 12));
		lblty.setForeground(SystemColor.text);
		lblty.setBounds(29, 234, 81, 25);
		panel.add(lblty);
		
		user = new JRadioButton("사용자");
		user.setFont(new Font("새굴림", Font.PLAIN, 12));
		user.setBackground(SystemColor.window);
		user.setBounds(181, 235, 103, 23);
		panel.add(user);
		group.add(user);
		manager = new JRadioButton("관리자");
		manager.setFont(new Font("새굴림", Font.PLAIN, 12));
		manager.setBackground(SystemColor.window);
		manager.setBounds(288, 235, 96, 23);
		panel.add(manager);
		group.add(manager);
		JLabel lblintro = new JLabel("비고");
		lblintro.setFont(new Font("새굴림", Font.PLAIN, 12));
		lblintro.setForeground(SystemColor.text);
		lblintro.setBounds(29, 269, 81, 25);
		panel.add(lblintro);
		
		intro = new JTextArea(5, 10);
		JScrollPane sp = new JScrollPane(intro);
		sp.setBounds(182, 264, 202, 56);
		panel.add(sp);
		
		JLabel lblTitle = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
		lblTitle.setBounds(223, 25, 237, 69);
		panel.add(lblTitle);
		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addUser();
			}
		});
		btnDe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeUser();
			}
		});
		btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateUser();
			}
		});
		btnFn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findUser();
			}
		});
		btnCn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelUser();
			}
		});
		getContentPane().setLayout(groupLayout);
		this. setLocation(650,200);
		setVisible(true);
		
		
	}

	//버튼 기능 
	//조회
	protected void findUser() {
		String userid = this.txtid.getText();
		UserDao udao = new UserDao();
		int result = udao.existsfind(userid);
		if (result == 0) {
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText("아이디를 다시 확인해주세요");
			jd.setTitle("아이디중복");
		}else {
			Uservo vo = udao.getUser(userid);
			setViewData(vo);
		}
		
	}
	private void setViewData(Uservo vo) {
		int userid      = vo.getUserid();
		String userpw   = vo.getUserpw();
		String username = vo.getUsername();
		String ty       = vo.getty();   
		String intro    = vo.getIntro();
		String indate   = vo.getIndate();
		
		this.txtid.setText(String.valueOf(userid));
		this.txtp.setText(userpw);
		this.txtname.setText(username);
		switch (ty) {
		case "사용자" :
			this.user.setSelected(true);
			break;
		case "관리자" :
			this.manager.setSelected(true);
			break;
		}
		this.intro.setText(intro);
		this.txtindate.setText(indate);
		
	}

	//새로고침
	protected void cancelUser() {
		clearViewData();
	}
	private void clearViewData() {
		this.txtid.setText( "" );
		this.txtp.setText( "" );
		this.txtname.setText("");
		this.user.setSelected(false);
		this.manager.setSelected(false);
		this.intro.setText("");
		this.txtindate.setText("");
		this.txtid.grabFocus();
	}
	//수정
	protected void updateUser() {
		String userid = this.txtid.getText();
		UserDao udao = new UserDao();
		int result = udao.existsfind(userid);
		if(result == 0) {
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText("아이디를 다시 확인해주세요");
			jd.setTitle("아이디 중복");
		}else {
			lm.view.JDialog jd = new lm.view.JDialog(1);
			jd.Dlbl.setText("수정하시겠습니까?");
			jd.btnNewButton.setText("수정");
			jd.btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int aftcnt = 0;
					String msg = "";
					Uservo vo = getViewData();
					aftcnt = udao.updateUser(vo);
					if( aftcnt > 0 )
						msg = userid + "수정완료";
					else 
						msg = userid + "수정완료";
					lm.view.JDialog jd2 = new lm.view.JDialog(0);
					jd2.Dlbl.setText(msg);
					jd2.setTitle("수정완료");
				}
			});
			jd.btnNewButton2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String msg = "취소되었습니다";
					lm.view.JDialog jd2 = new lm.view.JDialog(0);
					jd2.Dlbl.setText(msg);
					jd2.setTitle("수정취소");
					
				}
			});
		}
		}
	//삭제
	protected void removeUser() {
		lm.view.JDialog jd = new lm.view.JDialog(0);
		jd.Dlbl.setText("<html><body><center>데이터 베이스의 모든 내용이 삭제되기"
				+ "<br>때문에 담당자에게 문의해주세요</center></body></html>");
		jd.setTitle("경고");
	}
	//등록
	protected void addUser() {
		UserDao udao = new UserDao();
		String userid = txtid.getText();
		int result = udao.existsfind(userid);
		if(result == 1) {
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText("아이디가 중복됩니다");
			jd.setTitle("아이디 중복");
		}else {
			Uservo vo = getViewData();
			int aftcnt = udao.insertUser(vo);
			JDialog jd = new JDialog(0);
			jd.Dlbl.setText( aftcnt + "저장");
			jd.setTitle("저장");
		}

	}
	private Uservo getViewData() {
		
		String   userid = this.txtid.getText();
		String   userpw = this.txtp.getText();
		String username = this.txtname.getText();
		String ty = "";
		if(this.user.isSelected())  
			ty = "사용자";
		if(this.manager.isSelected())
			ty = "관리자";
		String   intro  = this.intro.getText();
		String   indate = this.txtindate.getText();
		
		Uservo vo = new Uservo(
				Integer.parseInt(userid), userpw, username, ty, intro, indate);		

		return vo;
	}

	public static void main(String[] args) {
		
		new Proc();
	}
}








