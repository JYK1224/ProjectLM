package lm.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LMAccoRegister extends JFrame{
	
	JLabel lblAcode = new JLabel();	// 거래처코드  not null
	JLabel lblAname = new JLabel();	// 거래처명    not null
	JLabel lblIncharge = new JLabel();	// 담당자   
	JLabel lblIcPhone = new JLabel();	// 연락처   
	
	JTextField txtAcode = new JTextField(); // 거래처코드  not null
	JTextField txtAname = new JTextField(); // 거래처명    not null
	JTextField txtIncharge = new JTextField(); // 담당자
	JTextField txtIcPhone = new JTextField(); // 연락처
	
	JButton btnRegister = new JButton();	// 등록
	JButton btnModify = new JButton();		// 수정
	JButton btnRemove = new JButton();		// 삭제
	JButton btnClear = new JButton();		// 취소(JTextField 초기화)
	
	
	// GridBagLayout  
	GridBagLayout        gb;
	GridBagConstraints   gbc;
	
	
	// 기본생성자
	public LMAccoRegister() {
		initComponent();
	}
	
	
	private void initComponent() {
		setTitle("신규상품 등록");
		
		//component 배치
		gb = new GridBagLayout();
		this.setLayout(gb);
		
		gbc          =  new GridBagConstraints();
		gbc.fill     =  GridBagConstraints.BOTH;
		gbc.weightx  =  1.0; 
		gbc.weighty  =  1.0;
		
		// 거래처코드  not null
		lblAcode = new JLabel("거래처코드 : ");
		lblAcode.setForeground(Color.red);
		this.txtAcode = new JTextField( 15 );
		gbAdd( lblAcode, 0, 0, 1, 1 );
		gbAdd( txtAcode, 1, 0, 1, 1 );
		
		// 거래처명    not null
		lblAname = new JLabel("거래처명 : ");
		lblAname.setForeground(Color.red);
		this.txtAname = new JTextField( 15 );
		gbAdd( lblAname, 0, 1, 1, 1 );
		gbAdd( txtAname, 1, 1, 1, 1 );
		
		// 담당자
		lblIncharge = new JLabel("담당자");
		this.txtIncharge = new JTextField( 15 );
		gbAdd( lblIncharge, 0, 2, 1, 1 );
		gbAdd( txtIncharge, 1, 2, 1, 1 );
		
		// 연락처
		lblIcPhone = new JLabel("연락처");
		this.txtIcPhone = new JTextField( 15 );
		gbAdd( lblIcPhone, 0, 3, 1, 1 );
		gbAdd( txtIcPhone, 1, 3, 1, 1 );
		
		// 버튼들
		JPanel pButton = new JPanel();
		btnRegister = new JButton("등록");
		btnModify = new JButton("수정");
		btnRemove = new JButton("삭제");
		btnClear = new JButton("취소");
		
		// 버튼기능들 
		btnRegister.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("등록버튼 클릭....");	
				
			}
		});
		btnModify.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("수정버튼 클릭....");	
				
			}
		});
		btnRemove.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("삭제버튼 클릭....");	
				
			}
		});
		btnClear.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("취소버튼 클릭....");	
				
			}
		});
		
		// 버튼들을 panel 에 추가
		pButton.add( btnRegister );
		pButton.add( btnModify );
		pButton.add( btnRemove );
		pButton.add( btnClear );
		
		// pButton 패널을 화면에 추가
		gbAdd( pButton, 0, 4, 4, 1);
		
		//------------------------------------------------
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(400,300);
		setResizable(false);
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

	//버튼 등록, 수정, 삭제, 취소
	public static void main(String[] args) {
		new LMAccoRegister();
	}

}
