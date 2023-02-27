package lm.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import lm.model.DeptDao;
import lm.model.Deptvo;

public class DeptProc extends JFrame{
	private JTextField txtCode, txtdname, txtName, txtdPhone;
	private JButton btnIn, btnUp, btnDe, btnCn, btnFind ;
	
	DeptList dlist = null ;
	
	public DeptProc () {
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
	
		
		setTitle("그린물류시스템");
		
		JLabel lblTitle= new JLabel("신규 거래처 등록");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 25));
		
		JLabel lblCode = new JLabel("거래처코드");
		lblCode.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JLabel lblAname = new JLabel("거래처명");
		lblAname.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JLabel lblName = new JLabel("담장자");
		lblName.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JLabel lblPhone = new JLabel("연락처");
		lblPhone.setFont(new Font("굴림", Font.PLAIN, 15));
		
		txtCode = new JTextField();
		txtCode.setColumns(10);
		
		txtdname = new JTextField();
		txtdname.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		txtdPhone = new JTextField();
		txtdPhone.setColumns(10);
		//등록
		JButton btnIn = new JButton("등록");
		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addDept();
			}
		});
		//수정
		JButton btnUp = new JButton("수정");
		btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				upDept();
			}
		});
		//삭제
		JButton btnDe = new JButton("삭제");
		btnDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deDept();
			}
		});
		//취소
		JButton btnCn = new JButton("취소");
		btnCn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cnDept();
			}
		});
		btnFind = new JButton("조회");
		
		this.btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findDept();
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(80)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
								.addComponent(lblAname, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
								.addComponent(lblPhone, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
								.addComponent(lblCode)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(119)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnDe, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIn, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
							.addGap(33)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtCode, Alignment.LEADING)
								.addComponent(txtdname, Alignment.LEADING)
								.addComponent(txtdPhone, Alignment.LEADING, 195, 195, Short.MAX_VALUE)
								.addComponent(txtName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnCn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnUp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnFind, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addGap(23))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(107)
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
					.addGap(96))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCode, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCode, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAname, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtdname, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtdPhone, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDe, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(btnFind, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		setLocation(500,200);
		setSize(450,450);
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
