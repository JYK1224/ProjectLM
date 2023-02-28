package lm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import lm.model.UserDao;
import lm.model.Uservo;

public class Proc extends JFrame{
	private JTextField txtid, txtname, txtindate;
	private JPasswordField txtp;
	private JTextArea intro;
	private ButtonGroup group;
	private JRadioButton user, manager;	
	private JButton btnFn ;
	UserList uList = null;
	
	//생성자
	public Proc () {
		init();
	}
	public Proc (UserList ulist) {
		this();
		this.uList = ulist ;
	}
	public Proc (String id, UserList userList) {
		this ();
		this.uList = uList;
		txtid.setText(id);
		btnFn.doClick();
	}
	
	
	private void init() {
		
		setTitle("그린물류시스템");
		setSize(400,550);
		
		JLabel lblid = new JLabel("아이디(사번)");
		JLabel lblpw = new JLabel("비밀번호");
		JLabel lblname = new JLabel("이름");
		JLabel lblty = new JLabel("유형");
		JLabel lblintro = new JLabel("비고");
		JLabel lblindate = new JLabel("가입일");
		
		txtid = new JTextField();
		txtid.setColumns(10);
		txtp = new JPasswordField();
		txtname = new JTextField();
		txtname.setColumns(10);
		txtindate = new JTextField();
		txtindate.setColumns(10);
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		txtindate.setText(today);
		txtindate.setEditable(false);
		
		user = new JRadioButton("사용자");
		manager = new JRadioButton("관리자");
		group = new ButtonGroup();
		group.add(user);
		group.add(manager);
		
		intro = new JTextArea(5,10);
		JScrollPane pane = new JScrollPane(intro);
		
		JButton btnIn = new JButton("등록");
		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addUser();
			}
		});
		JButton btnDe = new JButton("삭제");
		btnDe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeUser();
			}
		});
		JButton btnUp = new JButton("수정");
		btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateUser();
			}
		});
		JButton btnCn = new JButton("새로고침");
		btnCn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelUser();
			}
		});
		
		JButton btnFn = new JButton("조회");
		btnFn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findUser();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnIn)
									.addGap(18)
									.addComponent(btnDe)
									.addGap(18)
									.addComponent(btnUp))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblty, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(user)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(manager))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblname, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtname, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblpw, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtp))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblid, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtid, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(lblintro, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(pane))
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(lblindate, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtindate, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(99)
							.addComponent(btnCn)
							.addGap(29)
							.addComponent(btnFn)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblid, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtid, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblpw, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtp, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblname, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtname, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblty, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(user)
						.addComponent(manager))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblintro, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(lblindate, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtindate, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnIn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDe, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(24))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(251)
					.addComponent(pane, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(195, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		this.setLocation(600,230);
		setVisible(true);
		setResizable(false);
	}

	//버튼 기능 
	//조회
	protected void findUser() {
		String userid = this.txtid.getText();
		if( userid.trim().equals(" ")) {
			JOptionPane.showMessageDialog(null, "아이디를 다시 확인해주세요");
		}else {
			UserDao udao = new UserDao();
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
		if(userid.trim().equals(" ")) {
			JOptionPane.showMessageDialog(null, "아디를 다시 확인해주세요");
		}else {
			UserDao udao = new UserDao();
			int choice = JOptionPane.showConfirmDialog(null, 
					userid + "수정하시겠습니까?",
					"수정하시겠습니까",
					JOptionPane.OK_CANCEL_OPTION);
			int aftcnt = 0;
			String msg = " ";
			if (choice == 0 ) {
				Uservo vo = getViewData();
				aftcnt = udao.updateUser(vo);
				if( aftcnt > 0 )
					msg = userid + "수정완료";
				else 
					msg = userid + "수정완료";
			} else {
				msg = "취소했습니다";
			}
			JOptionPane.showConfirmDialog(null, msg, 
					" " , JOptionPane.OK_OPTION);		
		}
		}
	//삭제
	protected void removeUser() {
		JOptionPane.showMessageDialog(null, 
				"데이터 베이스의 모든 내용이 "
				+ "삭제되기 때문에 담당자에게 문의해주세요");
	}
	//등록
	protected void addUser() {
		UserDao udao = new UserDao();
		Uservo vo = getViewData();
		int aftcnt = udao.insertUser(vo);
		
		JOptionPane.showMessageDialog(null, aftcnt + "저장", "추가",
				JOptionPane.CLOSED_OPTION);
		
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












