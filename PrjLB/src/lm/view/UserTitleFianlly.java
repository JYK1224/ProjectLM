package lm.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.SystemColor;


public class UserTitleFianlly extends JFrame{
   LMProductInquiry piy;
   String userid;
   UserTitleFianlly utl;
   DeptList dpl;
   ShopList spl;
   IpgoList ipl;
   LMipgo   ipo;
   LMProdOrder pro;
   PullQuiry pqr;
   
   
   public UserTitleFianlly() {
      Init();
   }

   public UserTitleFianlly(String userid) {
	   this.userid = userid;
	   Init();
   }

private void Init() {
      setTitle("물류관리시스템-일반사용자");
      setSize(800,580);
      setLocation(600,150);
      getContentPane().setLayout(null);
   
     
      JButton btnNewButton_4 = new JButton("상품상세조회");
      btnNewButton_4.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_4 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_4.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_4.setBackground(new Color(229,255,204));
      btnNewButton_4.setBounds(209, 424, 153, 59);
      getContentPane().add(btnNewButton_4);
      btnNewButton_4.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(piy != null)
					piy.dispose();
					piy = new LMProductInquiry();
			
		}
	});
      
      JButton btnNewButton_5 = new JButton("상품전체조회");
      btnNewButton_5.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_5 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_5.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_5.setBackground(new Color(229,255,204));
      btnNewButton_5.setBounds(209, 341, 153, 59);
      getContentPane().add(btnNewButton_5);
      btnNewButton_5.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(pqr != null)
				pqr.dispose();
			
				pqr = new PullQuiry();
			
		}
	});
      
      JButton btnNewButton_6 = new JButton("거래처조회");
      btnNewButton_6.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_6 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_6.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_6.setBackground(new Color(255,229,204));
      btnNewButton_6.setBounds(209, 171, 153, 59);
      getContentPane().add(btnNewButton_6);
      btnNewButton_6.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(dpl != null)
				dpl.dispose();
		 	  dpl = new DeptList(utl);
		}
	});
      
      JButton btnNewButton_7 = new JButton("점포전체조회");
      btnNewButton_7.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_7 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_7.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_7.setBackground(new Color(255,255,204));
      btnNewButton_7.setBounds(209, 255, 153, 59);
      getContentPane().add(btnNewButton_7);
      btnNewButton_7.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(spl != null)
					spl.dispose();
				spl = new ShopList(utl);
		}
	});
      
      JButton btnNewButton_8 = new JButton("입고내역조회");
      btnNewButton_8.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_8 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_8.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_8.setBackground(new Color(204,229,255));
      btnNewButton_8.setBounds(593, 255, 153, 59);
      getContentPane().add(btnNewButton_8);
      btnNewButton_8.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(ipl != null)
					ipl.dispose();
					ipl = new IpgoList();
		}
	});
      
      JButton btnNewButton_9 = new JButton("상품출고업무");
      btnNewButton_9.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_9 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_9.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_9.setBackground(new Color(204,204,255));
      btnNewButton_9.setBounds(422, 341, 153, 59);
      getContentPane().add(btnNewButton_9);
      
      JButton btnNewButton_10 = new JButton("상품폐기등록");
      btnNewButton_10.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_10 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_10.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_10.setBackground(new Color(229,204,255));
      btnNewButton_10.setBounds(422, 424, 153, 59);
      getContentPane().add(btnNewButton_10);
      
      JButton btnNewButton_11 = new JButton("폐기내역조회");
      btnNewButton_11.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_11 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_11.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_11.setBackground(new Color(229,204,255));
      btnNewButton_11.setBounds(593, 424, 153, 59);
      getContentPane().add(btnNewButton_11);
      
      JButton btnNewButton_6_1 = new JButton("상품주문업무");
      btnNewButton_6_1.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_6_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_6_1.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_6_1.setBackground(new Color(204,255,255));
      btnNewButton_6_1.setBounds(422, 171, 153, 59);
      getContentPane().add(btnNewButton_6_1);
      btnNewButton_6_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(pro != null)
					pro.dispose();
					pro = new LMProdOrder(); 
		}
	});
      
      JButton btnNewButton_7_1 = new JButton("주문내역조회");
      btnNewButton_7_1.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_7_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_7_1.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_7_1.setBackground(new Color(204,255,255));
      btnNewButton_7_1.setBounds(593, 171, 153, 59);
      getContentPane().add(btnNewButton_7_1);
      
      JButton btnNewButton_8_1 = new JButton("상품입고업무");
      btnNewButton_8_1.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_8_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_8_1.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_8_1.setBackground(new Color(204,229,255));
      btnNewButton_8_1.setBounds(422, 255, 153, 59);
      getContentPane().add(btnNewButton_8_1);
      btnNewButton_8_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(ipo != null)
					ipo.dispose();
					ipo = new LMipgo();
		}
	});
      
      JButton btnNewButton_9_1 = new JButton("출고내역조회");
      btnNewButton_9_1.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_9_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_9_1.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_9_1.setBackground(new Color(204,204,255));
      btnNewButton_9_1.setBounds(593, 341, 153, 59);
      getContentPane().add(btnNewButton_9_1);
      setVisible(true);
      
      JLabel lbluserid = new JLabel("사용자");
      lbluserid.setForeground(SystemColor.text);
      lbluserid.setFont(new Font("굴림", Font.BOLD, 15));
      lbluserid.setBounds(12, 434, 165, 40);
      getContentPane().add(lbluserid);
      lbluserid.setText("사용자:" + this.userid);
      
      JLabel lblNewLabel = new JLabel("New label");
      lblNewLabel.setIcon(new ImageIcon(UserTitleFianlly.class.getResource("/lmimage/\uADF8\uB9B0\uBB3C\uB958\uC2DC\uC2A4\uD15C\uC720\uC800\uD0C0\uC774\uD2C0\uCD5C\uC885.png")));
      lblNewLabel.setBounds(0, 0, 784, 541);
      getContentPane().add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("New label");
      lblNewLabel_1.setBounds(12, 484, 123, 47);
      getContentPane().add(lblNewLabel_1);
      
   }

   public static void main(String[] args) {
   new UserTitleFianlly();

   }
}