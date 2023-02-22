package lm.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lm.model.UserDao;
import lm.model.Uservo;

public class Proc extends JFrame{

	JPanel p;
	JTextField txtid,txtname,txtindate;
	JPasswordField txtp;
	JTextArea intro;
	JRadioButton user, manager;
	ButtonGroup group;
	GridBagLayout gb;
	GridBagConstraints gbc;
	JButton btnAdd, btnDelete, btnUpdate, btnCancel, btnFind;
	UserList uList = null;
	
	public Proc() {
		init();
	}
	
	public Proc(UserList ulist) {
		this();
		this.uList = ulist;
	}

	public Proc(String id, UserList userList) {
		this();
		this.uList = uList;
		txtid.setText(id);
		btnFind.doClick();
	}

	private void init() {
		setTitle( "회원가입");
		
		gb          = new GridBagLayout();
		this.setLayout(gb);
		gbc         = new GridBagConstraints();
		gbc.fill    = GridBagConstraints.BOTH;
		gbc.weightx = 1.0; 
		gbc.weighty = 1.0; 
		
		// 아이디
		JLabel lblId = new JLabel("아이디(사번)");
		        txtid = new JTextField( 20 );
		gbAdd( lblId, 0, 0, 1, 1); 
		gbAdd( txtid, 1, 0, 3, 1);
		
		// 암호
		JLabel lblPwd = new JLabel("암호");
		         txtp = new JPasswordField(20);
		gbAdd( lblPwd, 0, 1, 1, 1); 
		gbAdd( txtp, 1, 1, 3, 1);
		
		//이름
		JLabel lblName = new JLabel("이름");
		          txtname = new JTextField(20);
		gbAdd( lblName, 0, 2, 1, 1); 
		gbAdd( txtname, 1, 2, 3, 1);
		
		//유형
		JLabel lblty = new JLabel("유형");
		user = new JRadioButton("일반사용자");
		manager = new JRadioButton ("관리자");
		group = new ButtonGroup();
		group.add(user);
		group.add(manager);
		JPanel pty = new JPanel (new FlowLayout(FlowLayout.LEFT));
		pty.add(user);
		pty.add(manager);
		gbAdd( lblty, 0, 3, 1, 1); 
		gbAdd( pty,  1, 3, 3, 1);
		
		//비고
		JLabel  lblintro = new JLabel ("비고");
		           intro = new JTextArea(5,10);
		JScrollPane pane = new JScrollPane(intro);
		gbAdd( lblintro, 0, 5, 1, 1); 
		gbAdd( pane,    1, 5, 3, 1);
		
		//가입일
		JLabel lblIndate = new JLabel("가입일");
		txtindate = new JTextField(20);
		String today     =LocalDateTime.now().
				          format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		txtindate.setText(today);
		txtindate.setEditable(false);
		gbAdd( lblIndate, 0, 6, 1, 1); 
		gbAdd( txtindate, 1, 6, 3, 1);
		
		//버튼
		JPanel pButton = new JPanel();
		btnAdd    = new JButton("추가");
		btnDelete = new JButton("삭제");
		btnDelete.setForeground(Color.RED);
		btnUpdate = new JButton("수정");
		btnCancel = new JButton("새로고침");
		btnCancel.setForeground(Color.blue);
		btnFind = new JButton("조회");
		//기능
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("추가");
				addUser();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeUser();
				System.out.println("삭제");
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateUser();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelUser();
			}
		});
		btnFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				findUser();
			}
		});
		
		pButton.add( btnAdd );
		pButton.add( btnDelete );
		pButton.add( btnUpdate );
		pButton.add( btnCancel );
		pButton.add( btnFind);
		
		gbAdd(pButton , 0, 7, 4, 1);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(600,230);
		setSize(350,500);
		setVisible(true);
		
	}

	protected void findUser() {
		String userid = this.txtid.getText();
		if(userid.trim().equals(" ") )
			return;
		UserDao ud = new UserDao();
		Uservo vo = ud.getUser(userid);
		setViewData(vo); 
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx       = x;
		gbc.gridy       = y;
		gbc.gridwidth   = w;
		gbc.gridheight  = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		this.add(c, gbc);
		
	}
	//버튼기능
	//추가
	protected void addUser() {
		UserDao uDao = new UserDao();
		Uservo ep = getViewData();
		System.out.println(ep.getUsername() + ep.getUserpw());
		int aftcnt = uDao.insertUser(ep);
		
		JOptionPane.showMessageDialog(null, aftcnt + "저장" , "추가",
				JOptionPane.CLOSED_OPTION);
		
	
	}
	//추가
	private Uservo getViewData() {
		
		String userid = this.txtid.getText();
		
		
		String userpw = this.txtp.getText();
		String username = this.txtname.getText();
		String ty = "";
		if(this.user.isSelected()) ty = "일반사용자";
		if(this.manager.isSelected()) ty = "관리자";
		String intro = this.intro.getText();
		String indate = this.txtindate.getText();
		
		Uservo us = new Uservo(
				Integer.parseInt(userid), userpw, username, ty, intro , indate);
				
		return us;
	}
	//삭제
	protected void removeUser() {
		String userid = this.txtid.getText();
		if(userid.equals(" ") )
			return;
		UserDao udao = new UserDao();
		int choice = JOptionPane.showConfirmDialog(null,
				userid + " 삭제하시겠습니까?",
				"삭제",
				JOptionPane.OK_CANCEL_OPTION);
		String msg = "";
		if(choice == 0) {
			int aftcnt = udao.deleteUser(userid);
			if(aftcnt > 0) {
				msg = aftcnt + "지웁니다";
			}
			} else{
				msg = "취소하였습니다";
			}
		JOptionPane.showMessageDialog(null, 
				msg + " ",
				"삭제하였습니다",
				JOptionPane.OK_OPTION );
	}
	//수정
	protected void updateUser() {
		String userid = this.txtid.getText();
		UserDao udao = new UserDao();
		
		int choice = JOptionPane.showConfirmDialog(null, 
					userid + "수정하시겠습니까?",
					"수정하시겠습니까",
					JOptionPane.OK_CANCEL_OPTION);
		int aftcnt = 0;
		String msg = " ";
		if ( choice == 0 ) {
			Uservo us = getViewData();
			aftcnt = udao.updateUser(us);
			if( aftcnt > 0 )
				msg = userid + "수정완료";
			else
				msg = userid + "수정완료";
		} else {
			msg = "취소했습니다";
		}
		JOptionPane.showConfirmDialog(null, msg,
				" ",
				JOptionPane.OK_OPTION);
	}
	//새로고침
	protected void cancelUser() {
		clearViewData();
	}
	

	private void setViewData(Uservo us) {
		int userid      = us.getUserid();
		String userpw   = us.getUserpw();
		String username = us.getUsername();
		String ty       = us.getty();   
		String intro    = us.getIntro();
		String indate   = us.getIndate();
		
		this.txtid.setText(String.valueOf(userid));
		this.txtp.setText(userpw);
		this.txtname.setText(username);
		switch (ty) {
		case "일반사용자" :
			this.user.setSelected(true);
			break;
		case "관리자" :
			this.manager.setSelected(true);
			break;
		}
		this.intro.setText(intro);
		this.txtindate.setText(indate);
		
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

	public static void main(String[] args) {
		
		new Proc();
	}
}
