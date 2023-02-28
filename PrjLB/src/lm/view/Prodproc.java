package lm.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Prodproc extends JFrame{
	
	private JTextField txtPid , txtPname, txtIpr, txtSpr;
	private JButton btnIn, btnDe, btnUp, btnCn;
	private JComboBox cmAname, cmDname;
	private JLabel lblPid, lblPname, lblDid, lblAid, lblIpr, lblSpr;
	 ImageIcon icon;
	ProdDao dao;
	
	public Prodproc() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/lmimage/alphabets-33744_640.png")));
		getContentPane().setBackground(new Color(231,231,231));
		init();
	}
	
	
	private void init() {

		setTitle("신규상품 등록");
		
		 icon = new ImageIcon("./image/신규거래처조회111.png");
	      
	      JPanel panel = new JPanel() {
	         public void paintComponent(Graphics g) {
	             g.drawImage(icon.getImage(), 0, 0, null);
	             setOpaque(false);
	             super.paintComponent(g);
	            }
	      };
		
		setSize(454,454);
		 setLocation(650,200);
		
		dao = new ProdDao();
		ArrayList<String> alDept = dao.getDept();
		String [] dept1 = new String[alDept.size()];
		for (int i = 0; i < alDept.size(); i++) {
			dept1[i] = alDept.get(i);
		}
		
		String [] dept = {"주류" , "가공식품", "기호식품", "냉동냉장"};
		
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
		lblPname = new JLabel("상품명");
		lblPname.setForeground(SystemColor.text);
		lblPname.setFont(new Font("새굴림", Font.PLAIN, 15));
		lblPname.setBounds(30, 174, 76, 18);
		panel.add(lblPname);
		btnCn = new JButton("취소");
		btnCn.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnCn.setIcon(new ImageIcon(Prodproc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnCn.setBounds(331, 365, 70, 32);
		btnCn.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnCn);
		btnCn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cnProd();
			}
		});
		btnDe = new JButton("삭제");
		btnDe.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnDe.setIcon(new ImageIcon(Prodproc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnDe.setBounds(234, 365, 70, 32);
		btnDe.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnDe);
		btnDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deProd();
			}
		});
		
		btnIn = new JButton("등록");
		btnIn.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnIn.setIcon(new ImageIcon(Prodproc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnIn.setBounds(36, 365, 70, 32);
		btnIn.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnIn);
		
				btnIn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addProd();
						addStock();
					}
				});
		btnUp = new JButton("수정");
		btnUp.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnUp.setIcon(new ImageIcon(Prodproc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
		btnUp.setBounds(137, 365, 70, 32);
		 btnUp.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnUp);
		btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				upProd();
			}
		});
		txtSpr = new JTextField();
		txtSpr.setBounds(174, 320, 195, 29);
		panel.add(txtSpr);
		txtSpr.setColumns(10);
		lblSpr = new JLabel("판매가격");
		lblSpr.setForeground(SystemColor.text);
		lblSpr.setFont(new Font("새굴림", Font.PLAIN, 15));
		lblSpr.setBounds(30, 328, 76, 18);
		panel.add(lblSpr);
		cmDname = new JComboBox(dept1);
		cmDname.setFont(new Font("새굴림", Font.PLAIN, 12));
		cmDname.setBackground(SystemColor.window);
		cmDname.setBounds(174, 242, 195, 29);
		panel.add(cmDname);
		txtIpr = new JTextField();
		txtIpr.setBounds(174, 281, 195, 29);
		panel.add(txtIpr);
		txtIpr.setColumns(10);
		lblIpr = new JLabel("입고가격");
		lblIpr.setForeground(SystemColor.text);
		lblIpr.setFont(new Font("새굴림", Font.PLAIN, 15));
		lblIpr.setBounds(30, 290, 76, 18);
		panel.add(lblIpr);
		lblAid = new JLabel("거래처명");
		lblAid.setForeground(SystemColor.text);
		lblAid.setFont(new Font("새굴림", Font.PLAIN, 15));
		lblAid.setBounds(30, 250, 76, 18);
		panel.add(lblAid);
		cmAname = new JComboBox(dept);
		cmAname.setFont(new Font("새굴림", Font.PLAIN, 12));
		cmAname.setBackground(SystemColor.window);
		cmAname.setBounds(174, 203, 195, 29);
		panel.add(cmAname);
		lblDid = new JLabel("상품분류");
		lblDid.setForeground(SystemColor.text);
		lblDid.setFont(new Font("새굴림", Font.PLAIN, 15));
		lblDid.setBounds(30, 211, 76, 18);
		panel.add(lblDid);
		txtPname = new JTextField();
		txtPname.setBounds(174, 167, 195, 29);
		panel.add(txtPname);
		txtPname.setColumns(10);
		
		txtPid = new JTextField();
		txtPid.setBounds(174, 128, 197, 29);
		panel.add(txtPid);
		txtPid.setColumns(10);
		
		lblPid = new JLabel("상품코드");
		lblPid.setForeground(SystemColor.text);
		lblPid.setFont(new Font("새굴림", Font.PLAIN, 15));
		lblPid.setBounds(30, 137, 76, 18);
		panel.add(lblPid);
		
		JLabel lblTitle = new JLabel("\uC2E0\uADDC \uC0C1\uD488 \uB4F1\uB85D");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
		lblTitle.setBounds(174, 27, 237, 69);
		panel.add(lblTitle);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(groupLayout);
		
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
