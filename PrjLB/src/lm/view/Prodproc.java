package lm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import lm.model.ProdDao;
import lm.model.Prodvo;

public class Prodproc extends JFrame{
	
	private JTextField txtPid , txtPname, txtIpr, txtSpr;
	private JButton btnIn, btnDe, btnUp, btnCn;
	private JComboBox cmAname, cmDname;
	private JLabel lblPid, lblPname, lblDid, lblAid, lblIpr, lblSpr;
	
	ProdDao dao;
	
	public Prodproc() {
		init();
	}
	
	
	private void init() {

		setTitle("신규상품 등록");
		
		setSize(400,550);
		setLocation(600,230);
		
		lblPid = new JLabel("상품코드");
		lblPname = new JLabel("상품명");
		lblDid = new JLabel("상품분류");
		lblAid = new JLabel("거래처명");
		lblIpr = new JLabel("입고가격");
		lblSpr = new JLabel("판매가격");
		
		txtPid = new JTextField();
		txtPid.setColumns(10);
		txtPname = new JTextField();
		txtPname.setColumns(10);
		txtIpr = new JTextField();
		txtIpr.setColumns(10);
		txtSpr = new JTextField();
		txtSpr.setColumns(10);
		
		dao = new ProdDao();
		ArrayList<String> alDept = dao.getDept();
		String [] dept1 = new String[alDept.size()];
		for (int i = 0; i < alDept.size(); i++) {
			dept1[i] = alDept.get(i);
		}
		cmDname = new JComboBox(dept1);
		
		String [] dept = {"주류" , "가공식품", "기호식품", "냉동냉장"};
		cmAname = new JComboBox(dept);
		
		btnIn = new JButton("등록");
		btnUp = new JButton("수정");
		btnCn = new JButton("취소");
		btnDe = new JButton("삭제");

		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addProd();
				addStock();
			}
		});
		btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				upProd();
			}
		});
		btnCn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cnProd();
			}
		});
		btnDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deProd();
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDe, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCn, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblIpr, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtIpr, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblPname, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtPname, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblPid, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtPid, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(8)
								.addComponent(lblSpr, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtSpr, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(lblAid, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addGap(18))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(6)
										.addComponent(lblDid, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addGap(20)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(cmAname, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(cmDname, 0, 151, Short.MAX_VALUE))))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnIn, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPid, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPid, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPname, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPname, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDid, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmAname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAid, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmDname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIpr, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIpr, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSpr, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSpr, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnIn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDe, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(groupLayout);
		setLocation(600,150);
		setVisible(true);
		setResizable(false);
		
	}
	//버튼 기능
	//재고 추가
	protected void addStock() {
		ProdDao pdao = new ProdDao();
		String pid = this.txtPid.getText();
		String dname = (String) this.cmDname.getSelectedItem();
		int aftcnt = pdao.insertStock(pid, dname);
		
		JOptionPane.showMessageDialog(null, aftcnt + "저장", "추가",
				JOptionPane.CLOSED_OPTION);
	}
	//상품 등록
	protected void addProd() {
		ProdDao pdao = new ProdDao();
		Prodvo pv = getViewdata();
		int aftcnt = pdao.insertProd(pv);
		
	}
	private Prodvo getViewdata() {
		String pid = this.txtPid.getText();
		String pname = this.txtPname.getText();
		int ipr = Integer.parseInt(this.txtIpr.getText());
		int spr = Integer.parseInt(this.txtSpr.getText());
		String aname = (String) this.cmAname.getSelectedItem();
		String dname = (String) this.cmDname.getSelectedItem();
		
		Prodvo pv = new Prodvo(pid, pname, ipr, spr, aname, dname);
		
		return pv;
	}
	//상품 수정
	protected void upProd() {
		String pid = this.txtPid.getText();
		ProdDao pdao = new ProdDao();
		
		int choice = JOptionPane.showConfirmDialog(null, 
				pid + "수정하시겠습니까?",
				"수정하시겠습니까?",
				JOptionPane.OK_CANCEL_OPTION);
		int aftcnt = 0;
		String msg = " ";
		if(choice == 0) {
			Prodvo pv = getViewdata();
			aftcnt = pdao.updateProd(pv);
			if(aftcnt > 0 )
				msg = pid + "수정완료";
			else 
				msg = pid + "수정완료";
		}else {
			msg = "취소했습니다";
		}
		JOptionPane.showConfirmDialog(null, msg,
				" ", JOptionPane.OK_OPTION);
	}
	//새로고침
	protected void cnProd() {
		this.txtPid.setText(" ");
		this.txtPname.setText(" ");
		this.cmAname.setSelectedIndex(0);
		this.cmDname.setSelectedIndex(0);
		this.txtIpr.setText(" ");
		this.txtSpr.setText(" ");
		this.txtPid.grabFocus();
		
	}
	//상품삭제
	protected void deProd() {
		JOptionPane.showMessageDialog(null, 
				"데이터 베이스의 모든 내용이 삭제되기"
				+ " 때문에 담당자에게 문의해주세요");
	}


	public static void main(String[] args) {
		new Prodproc();
	}
}
