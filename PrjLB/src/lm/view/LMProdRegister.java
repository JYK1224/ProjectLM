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

public class LMProdRegister extends JFrame{
	
	JLabel lblPcode = new JLabel();	// 상품코드  not null
	JLabel lblPname = new JLabel();	// 상품명    not null
	JLabel lblDname = new JLabel(); // 디파트먼트 (콤보박스) 
	JLabel lblAname = new JLabel(); // 거래처명	(콤보박스)
	JLabel lblIprice = new JLabel(); // 입고가
	JLabel lblSprice = new JLabel(); // 판매가
	
	JTextField txtPcode = new JTextField(); // 상품코드  not null
	JTextField txtPname = new JTextField(); // 상품명    not null
	JComboBox cmbDname = new JComboBox();	// 디파트먼트 (콤보박스) 
	JComboBox cmbAname = new JComboBox();	// 거래처명	(콤보박스)
	JTextField txtIprice = new JTextField();	// 입고가
	JTextField txtSprice = new JTextField();	// 판매가
	
	JButton btnRegister = new JButton();	// 등록
	JButton btnModify = new JButton();		// 수정
	JButton btnRemove = new JButton();		// 삭제
	JButton btnClear = new JButton();		// 취소(JTextField 초기화)
	
	
	// GridBagLayout  
	GridBagLayout        gb;
	GridBagConstraints   gbc;
	
	
	// 기본생성자
	public LMProdRegister() {
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
		
		// 상품코드  not null
		lblPcode = new JLabel("상품코드 : ");
		lblPcode.setForeground(Color.red);
		this.txtPcode = new JTextField( 15 );
		gbAdd( lblPcode, 0, 0, 1, 1 );
		gbAdd( txtPcode, 1, 0, 1, 1 );
		
		// 상품명    not null
		lblPname = new JLabel("상품명 : ");
		lblPname.setForeground(Color.red);
		this.txtPname = new JTextField( 15 );
		gbAdd( lblPname, 0, 1, 1, 1 );
		gbAdd( txtPname, 1, 1, 1, 1 );
		
		// 디파트먼트 (콤보박스)
		lblDname = new JLabel("디파트먼트 : ");
		lblDname.setForeground(Color.red);
		String [] dept = {"주류", "가공식품", "기호식품", "냉동냉장"};		
		cmbDname = new JComboBox( dept );
		gbAdd( lblDname, 0, 2, 1, 1 );
		gbAdd( cmbDname, 1, 2, 1, 1 );
		
		// 거래처명 (콤보박스)
		lblAname = new JLabel("거래처명 : ");
		lblAname.setForeground(Color.red);
		String [] comp = {"CJ","동아오츠카","대상","농심"};
		cmbAname = new JComboBox( comp );
		gbAdd( lblAname, 0, 3, 1, 1 );
		gbAdd( cmbAname, 1, 3, 1, 1 );
		
		// 입고가
		lblIprice = new JLabel("입고가격 : ");
		this.txtIprice = new JTextField(15);
		gbAdd( lblIprice, 0, 4, 1, 1 );
		gbAdd( txtIprice, 1, 4, 1, 1 );
		
		// 판매가
		lblSprice = new JLabel("판매가격 : ");
		this.txtSprice = new JTextField(15);
		gbAdd( lblSprice, 0, 5, 1, 1 );
		gbAdd( txtSprice, 1, 5, 1, 1 );
		
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
		gbAdd( pButton, 0, 6, 4, 1);
		
		//------------------------------------------------
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocation(600,150);
		setSize(400,600);
		
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
		new LMProdRegister();
	}

}
