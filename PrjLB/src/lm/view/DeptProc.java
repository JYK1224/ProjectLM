package lm.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import lm.model.DeptDao;
import lm.model.Deptvo;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;

public class DeptProc extends JFrame{
	private JTextField txtCode, txtdname, txtName, txtdPhone;
	private JButton btnIn, btnUp, btnDe, btnCn, btnFind ;
	
	
	DeptList dlist = null ;
	ImageIcon icon;
	JScrollPane scrollPane;
	
	
	public DeptProc () {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/lmimage/alphabets-33744_640.png")));
		getContentPane().setBackground(new Color(231,231,231));
		
		init();
	}
	
	public DeptProc(DeptList dlist) {
		this();
		this.dlist = dlist;
	}

	public DeptProc(String did, DeptList deptList) {
		this();
		this.dlist = dlist;
		txtCode.setText(did);
		btnFind.doClick();
	}

	public void init () {
	
		
		setTitle("신규거래처등록");
		icon = new ImageIcon("./image/신규거래처조회111.png");
	      
	      JPanel panel = new JPanel() {
	         public void paintComponent(Graphics g) {
	             // Approach 1: Dispaly image at at full size
	             g.drawImage(icon.getImage(), 0, 0, null);
	             // Approach 2: Scale image to size of component
	             // Dimension d = getSize();
	             // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
	             // Approach 3: Fix the image position in the scroll pane
	             // Point p = scrollPane.getViewport().getViewPosition();
	             // g.drawImage(icon.getImage(), p.x, p.y, null);
	             setOpaque(false);
	             super.paintComponent(g);
	            }
	      };
		
		scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		JLabel lblTitle= new JLabel("신규 거래처 등록");
		lblTitle.setBounds(152, 27, 237, 69);
		lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
		
		JLabel lblCode = new JLabel("거래처코드");
		lblCode.setBounds(20, 146, 96, 18);
		lblCode.setForeground(SystemColor.text);
		lblCode.setFont(new Font("새굴림", Font.BOLD, 15));
		
		txtCode = new JTextField();
		txtCode.setBounds(167, 141, 195, 29);
		txtCode.setColumns(10);
		
		JLabel lblAname = new JLabel("거래처명");
		lblAname.setBounds(20, 198, 142, 18);
		lblAname.setForeground(SystemColor.text);
		lblAname.setFont(new Font("새굴림", Font.BOLD, 15));
		
		txtdname = new JTextField();
		txtdname.setBounds(167, 193, 195, 29);
		txtdname.setColumns(10);
		
		JLabel lblName = new JLabel("담장자");
		lblName.setBounds(20, 254, 142, 18);
		lblName.setForeground(SystemColor.text);
		lblName.setFont(new Font("새굴림", Font.BOLD, 15));
		
		txtName = new JTextField();
		txtName.setBounds(167, 249, 195, 29);
		txtName.setColumns(10);
		
		JLabel lblPhone = new JLabel("연락처");
		lblPhone.setBounds(20, 308, 142, 18);
		lblPhone.setForeground(SystemColor.text);
		lblPhone.setFont(new Font("새굴림", Font.BOLD, 15));
		
		txtdPhone = new JTextField();
		txtdPhone.setBounds(167, 300, 195, 29);
		txtdPhone.setColumns(10);
		//등록
		JButton btnIn_1 = new JButton("등록");
		btnIn_1.setFont(new Font("새굴림", Font.PLAIN, 13));
		btnIn_1.setIcon(new ImageIcon(DeptProc.class.getResource("/lmimage/신규거래처등록버튼.png")));
		btnIn_1.setBounds(18, 367, 70, 32);
		btnIn_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		btnIn_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addDept();
			}
		});
		//수정
		JButton btnUp_1 = new JButton("수정");
		btnUp_1.setFont(new Font("새굴림", Font.PLAIN, 13));
		btnUp_1.setIcon(new ImageIcon(DeptProc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnUp_1.setBounds(100, 367, 70, 32);
		btnUp_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		btnUp_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				upDept();
			}
		});
		//취소
		JButton btnCn_1 = new JButton("취소");
		btnCn_1.setFont(new Font("새굴림", Font.PLAIN, 13));
		btnCn_1.setIcon(new ImageIcon(DeptProc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnCn_1.setBounds(179, 367, 70, 32);
		btnCn_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		btnCn_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cnDept();
			}
		});
		//삭제
		JButton btnDe_1 = new JButton("삭제");
		btnDe_1.setFont(new Font("새굴림", Font.PLAIN, 13));
		btnDe_1.setIcon(new ImageIcon(DeptProc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnDe_1.setBounds(259, 367, 70, 32);
		btnDe_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		btnDe_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						"데이터 베이스의 모든 내용이 삭제되기"
						+ " 때문에 담당자에게 문의해주세요");
			}
		});
		btnFind = new JButton("조회");
		btnFind.setFont(new Font("새굴림", Font.PLAIN, 13));
		btnFind.setIcon(new ImageIcon(DeptProc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnFind.setBounds(341, 367, 70, 32);
		btnFind .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		this.btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findDept();
			}
		});
		panel.setLayout(null);
		panel.add(lblAname);
		panel.add(lblCode);
		panel.add(lblName);
		panel.add(lblPhone);
		panel.add(txtCode);
		panel.add(txtdname);
		panel.add(txtName);
		panel.add(txtdPhone);
		panel.add(lblTitle);
		panel.add(btnIn_1);
		panel.add(btnUp_1);
		panel.add(btnCn_1);
		panel.add(btnDe_1);
		panel.add(btnFind);
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
		
		 setLocation(600,150);
		setSize(454,454);
		setVisible(true);
		setResizable(false);
		
	}
	//조회
	protected void findDept() {
		String did = this.txtCode.getText();
		if(did.trim().equals(" "))
			return;
		DeptDao ddao = new DeptDao();
		Deptvo    dv = ddao.getDid(did);
		setViewData(dv);
		
	}
	private void setViewData(Deptvo dv) {
		int       did = dv.getDeptid();
		String  dname = dv.getDeptdname();
		String   name = dv.getDeptname();
		String dphone = dv.getDeptphone();
		
		this.txtCode.setText(String.valueOf(did));
		this.txtdname.setText(dname);
		this.txtName.setText(name);
		this.txtdPhone.setText(dphone);
	}

	//새로고침
	protected void cnDept() {
		clearViewData();
	}

	private void clearViewData() {
		this.txtCode.setText("");
		this.txtdname.setText("");
		this.txtName.setText("");
		this.txtdPhone.setText("");
		this.txtCode.grabFocus();
	}

	//삭제
	protected void deDept() {
		String did = this.txtCode.getText();
		if(did.equals(" ") )
			return;
		DeptDao ddao = new DeptDao();
		int choice = JOptionPane.showConfirmDialog(null, 
				did + "삭제하시겠습니까?",
				"삭제", JOptionPane.OK_CANCEL_OPTION);
		String msg = "";
		if(choice == 0) {
			int aftcnt = ddao.deleteDept(did);
			if(aftcnt > 0 ) {
				msg = did + " 지웁니다";
			}
			} else { 
				msg = "취소하였습니다";
			}
		JOptionPane.showMessageDialog(null, msg + " ",
				"삭제하였습니다", JOptionPane.OK_OPTION);
	}

	//수정
	protected void upDept() {
		String did = this.txtCode.getText();
		DeptDao ddao = new DeptDao();
		
		int choice = JOptionPane.showConfirmDialog(null,
					did + "수정하시겠습니까",
					"수정하시겠습니까",
					JOptionPane.OK_CANCEL_OPTION
					);
		int aftcnt = 0;
		String msg = " ";
		if( choice == 0 ) {
			Deptvo dv = getViewData();
			aftcnt = ddao.updataDept(dv);
			if( aftcnt > 0 )
				msg = did + "수정완료";
			else 
				msg = did + "수정완료";
		} else {
			msg = "취소했습니다";
		}
		JOptionPane.showConfirmDialog(null, msg,
				" ", JOptionPane.OK_OPTION);
	}

	//등록
	protected void addDept() {
		DeptDao ddao = new DeptDao();
		Deptvo dv = getViewData();
		int aftcnt = ddao.insertDept(dv); 
		JOptionPane.showMessageDialog(null, dv.getDeptid()+ "저장" );
	}

	private Deptvo getViewData() {
		String did = this.txtCode.getText();
		String dname = this.txtdname.getText();
		String name = this.txtName.getText();
		String dphone = this.txtdPhone.getText();
		
		Deptvo dv = new Deptvo(
				Integer.parseInt(did), dname, name, dphone);
		
		return dv;
	}

	public static void main(String[] args) {
		
		new DeptProc();
	}
}
