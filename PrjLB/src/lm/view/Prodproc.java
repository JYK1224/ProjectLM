package lm.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lm.model.ProdDao;
import lm.model.Prodvo;

public class Prodproc extends JFrame{
	
	JLabel lblPid, lblPname, lblDid, lblAid, lblIpr, lblSpr ; 	
	
	JTextField txtPid , txtPname, txtIpr, txtSpr ;
	JComboBox cmDname , cmAname;	
	JButton btnIn ,btnUp, btnDe, btnCn ;
	
	GridBagLayout        gb;
	GridBagConstraints   gbc;
	
	ProdDao dao ;
	
	public Prodproc() {
		init();
	}
	
	private void init() {
		setTitle("신규상품 등록");
		
		//component 배치
		gb = new GridBagLayout();
		this.setLayout(gb);
		
		gbc          =  new GridBagConstraints();
		gbc.fill     =  GridBagConstraints.BOTH;
		gbc.weightx  =  1.0; 
		gbc.weighty  =  1.0;

		lblPid = new JLabel("상품코드 : ");
		lblPid.setForeground(Color.red);
		this.txtPid = new JTextField( 15 );
		gbAdd( lblPid, 0, 0, 1, 1 );
		gbAdd( txtPid, 1, 0, 1, 1 );
		
		lblPname = new JLabel("상품명 : ");
		lblPname.setForeground(Color.red);
		this.txtPname = new JTextField( 15 );
		gbAdd( lblPname, 0, 1, 1, 1 );
		gbAdd( txtPname, 1, 1, 1, 1 );
		
		lblDid = new JLabel("상품분류 : ");
		lblDid.setForeground(Color.red);
		String [] dept = {"주류", "가공식품", "기호식품", "냉동냉장"};		
		cmAname = new JComboBox( dept );
		gbAdd( lblDid, 0, 2, 1, 1 );
		gbAdd( cmAname, 1, 2, 1, 1 );
		
		lblAid = new JLabel("거래처명 : ");
		lblAid.setForeground(Color.red);
		
		dao = new ProdDao();
		ArrayList<String> alDept = dao.getDept();
		String [] dept1 = new String[alDept.size()];
	
		for (int i = 0; i < alDept.size(); i++) {
			dept1[i] = alDept.get(i);
		}
		cmDname = new JComboBox( dept1 );		
		gbAdd( lblAid, 0, 3, 1, 1 );
		gbAdd( cmDname, 1, 3, 1, 1 );
		
		lblIpr = new JLabel("입고가격 : ");
		this.txtIpr = new JTextField(15);
		gbAdd( lblIpr, 0, 4, 1, 1 );
		gbAdd( txtIpr, 1, 4, 1, 1 );
		
		lblSpr = new JLabel("판매가격 : ");
		this.txtSpr = new JTextField(15);
		gbAdd( lblSpr, 0, 5, 1, 1 );
		gbAdd( txtSpr, 1, 5, 1, 1 );
		
		JPanel pButton = new JPanel();
		btnIn = new JButton("등록");
		btnUp = new JButton("수정");
		btnDe = new JButton("삭제");
		btnCn = new JButton("취소");
		
		// 버튼기능들 
		btnIn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addProd();
			}	
		});
		btnUp.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				upProd();
			}
		});
		btnDe.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deProd();
			}
		});
		btnCn.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cnProd();
			}
		});
		// 버튼들을 panel 에 추가
		pButton.add( btnIn );
		pButton.add( btnUp );
		pButton.add( btnDe );
		pButton.add( btnCn );
		
		// pButton 패널을 화면에 추가
		gbAdd( pButton, 0, 6, 4, 1);
		
		//------------------------------------------------
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocation(600,150);
		setSize(400,600);
		setResizable(false);
	}
	
	protected void cnProd() {
		this.txtPid.setText(" ");
		this.txtPname.setText(" ");
		this.cmAname.setSelectedIndex(0);
		this.cmDname.setSelectedIndex(0);
		this.txtIpr.setText(" ");
		this.txtSpr.setText(" ");
		this.txtPid.grabFocus();
		
	}

	protected void deProd() {
		String pid = this.txtPid.getText();
		if (pid.equals(" "))
			return;
		ProdDao pdao = new ProdDao();
		int choice = JOptionPane.showConfirmDialog(null, 
				pid + " 삭제하시겠습니까",
				"삭제",
				JOptionPane.OK_CANCEL_OPTION);
		String msg = "";
		if (choice == 0 ) {
			int aftcnt = pdao.deletProd(pid);
			if(aftcnt == 0) {
				msg = aftcnt + "개 지웁니다";
			}
		}else {
			msg = "취소하였습니다";
		}
		JOptionPane.showMessageDialog(null, 
				msg + " ", "삭제하였습니다",
				JOptionPane.OK_OPTION);
	}

	protected void upProd() {
		String pid = this.txtPid.getText();
		ProdDao pdao = new ProdDao();
		
		int choice = JOptionPane.showConfirmDialog( null, 
				pid + "수정하시겠습니까?",
				"수정하시겠습니까?",
				JOptionPane.OK_CANCEL_OPTION );
		int aftcnt = 0;
		String msg = " ";
		if( choice == 0) {
			Prodvo pv = getViewdata();
			aftcnt = pdao.updateProd(pv);
			if( aftcnt > 0 )
				msg = pid + "수정완료";
			else 
				msg = pid + "수정완료";
		}else {
			msg = "취소했습니다";
		}
		JOptionPane.showConfirmDialog(null, msg,
				" ", JOptionPane.OK_OPTION);
		
	}

	protected void addProd() {
		ProdDao pdao = new ProdDao();
		Prodvo  pv = getViewdata();
		int aftcnt = pdao.insertProd(pv);
		
		JOptionPane.showMessageDialog(null, aftcnt + " 저장" , "추가",
				JOptionPane.CLOSED_OPTION);
		
	}

	private Prodvo getViewdata() {
		String pid = this.txtPid.getText();
		String pname = this.txtPname.getText();
		int ipr = Integer.parseInt(this.txtIpr.getText());
		int spr = Integer.parseInt(this.txtSpr.getText());
		String aname = (String) this.cmAname.getSelectedItem();
		String dname = (String) this.cmDname.getSelectedItem();
		
		Prodvo pv = new Prodvo(pid, pname, ipr , spr , aname, dname);
		
		return pv;
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx        = x;
		gbc.gridy        = y;
		gbc.gridwidth    = w;
		gbc.gridheight   = h;
		gb.setConstraints(c, gbc);
		gbc.insets       = new Insets(2, 2, 2, 2);
		this.add( c, gbc );
	}

	public static void main(String[] args) {
		new Prodproc();
	}

}
