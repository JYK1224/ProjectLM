package lm.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import lm.model.LoginVo;
import javax.swing.ImageIcon;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.SystemColor;



public class ManagerTitleFinally extends JFrame{
   LMProductInquiry piy;
   String userid; 
   LoginVo log;
   DeptList dpl;
   ShopList spl;
   UserList utl;
   DeptProc dpc;
   ShopProc spc;
   LMipgo ipo;
   IpgoList ipl;
   LMProdOrder pro;
   LMProdRegister prs;
   PullQuiry pqr;
   JScrollPane scrollPane;
   ImageIcon icon;
   
   public ManagerTitleFinally() {
      Init();
   }

   public ManagerTitleFinally(String userid) {
	   this.userid = userid;
	   Init();
   }



   private void Init() {
	   
	   
	   
	   
	   
	   
	   
      setTitle("물류관리시스템-관리자");
      setSize(1000,580);
      setLocation(600,150);
      getContentPane().setLayout(null);
      
      JButton btnNewButton = new JButton("\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D");
      btnNewButton.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton.setBorderPainted(false);
      btnNewButton.setBounds(260, 188, 153, 59);
      getContentPane().add(btnNewButton);
      btnNewButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(dpc != null)
				dpc.dispose();
			   dpc = new DeptProc();
		}
	});
      
      JButton btnNewButton_1 = new JButton("\uC2E0\uADDC\uC810\uD3EC\uB4F1\uB85D");
      
      btnNewButton_1.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_1.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_1.setBackground(new Color(255,255,204));
      btnNewButton_1.setBounds(260, 274, 153, 59);
      getContentPane().add(btnNewButton_1);
      btnNewButton_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(spc != null)
				spc.dispose();
				spc = new ShopProc();
		}
	});

      JButton btnNewButton_2 = new JButton("\uC0AC\uC6A9\uC790\uAD00\uB9AC");
      btnNewButton_2.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_2 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_2.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_2.setBackground(new Color(204,255,229));
      btnNewButton_2.setBounds(260, 440, 153, 59);
      getContentPane().add(btnNewButton_2);
      btnNewButton_2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
				if(utl!= null)
					utl.dispose();
					utl = new UserList();
		}
	});
      
      JButton btnNewButton_3 = new JButton("\uC2E0\uADDC\uC0C1\uD488\uB4F1\uB85D");
      btnNewButton_3.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_3 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_3.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_3.setBackground(new Color(229,255,204));
      btnNewButton_3.setBounds(260, 359, 153, 59);
      getContentPane().add(btnNewButton_3);
      btnNewButton_3.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(prs != null)
					prs.dispose();
					prs = new LMProdRegister();
		}
	});
      
      JButton btnNewButton_4 = new JButton("\uC0C1\uD488\uC0C1\uC138\uC870\uD68C");
      btnNewButton_4.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_4 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_4.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_4.setBackground(new Color(229,255,204));
      btnNewButton_4.setBounds(425, 440, 153, 59);
      getContentPane().add(btnNewButton_4);
      btnNewButton_4.addActionListener( new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
				if(piy != null)
					piy.dispose();
					piy = new LMProductInquiry();
		}
	});
      
      JButton btnNewButton_5 = new JButton("\uC0C1\uD488\uC804\uCCB4\uC870\uD68C");
      btnNewButton_5.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_5 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_5.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_5.setBackground(new Color(229,255,204));
      btnNewButton_5.setBounds(425, 359, 153, 59);
      getContentPane().add(btnNewButton_5);
      btnNewButton_5.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(pqr != null) 
				pqr.dispose();
			
			pqr = new PullQuiry();
				
			
			
		}
	});
      
      JButton btnNewButton_6 = new JButton("\uAC70\uB798\uCC98\uC870\uD68C");
      btnNewButton_6.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_6 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_6.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_6.setBackground(new Color(255,229,204));
      btnNewButton_6.setBounds(425, 188, 153, 59);
      getContentPane().add(btnNewButton_6);
      btnNewButton_6.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(dpl != null)
					dpl.dispose();
					dpl = new DeptList();
		}
	});
      
      JButton btnNewButton_7 = new JButton("\uC810\uD3EC\uC804\uCCB4\uC870\uD68C");
      btnNewButton_7.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_7 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_7.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_7.setBackground(new Color(255,255,204));
      btnNewButton_7.setBounds(425, 274, 153, 59);
      getContentPane().add(btnNewButton_7);
      btnNewButton_7.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(spl != null)
					spl.dispose();
					spl = new ShopList();
		}
	});
      
      JButton btnNewButton_8 = new JButton("\uC785\uACE0\uB0B4\uC5ED\uC870\uD68C");
      btnNewButton_8.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_8 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_8.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_8.setBackground(new Color(204,229,255));
      btnNewButton_8.setBounds(784, 274, 153, 59);
      getContentPane().add(btnNewButton_8);
      btnNewButton_8.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if (ipl != null)
					ipl.dispose();
					ipl = new IpgoList();
		}
	});
      
      JButton btnNewButton_9 = new JButton("\uC0C1\uD488\uCD9C\uACE0\uC5C5\uBB34");
      btnNewButton_9.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      btnNewButton_9.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_9 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_9.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_9.setBackground(new Color(204,204,255));
      btnNewButton_9.setBounds(621, 359, 153, 59);
      getContentPane().add(btnNewButton_9);
      
      JButton btnNewButton_10 = new JButton("\uC0C1\uD488\uD3D0\uAE30\uB4F1\uB85D");
      btnNewButton_10.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_10 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_10.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_10.setBackground(new Color(229,204,255));
      btnNewButton_10.setBounds(621, 440, 153, 59);
      getContentPane().add(btnNewButton_10);
      
      JButton btnNewButton_11 = new JButton("\uD3D0\uAE30\uB0B4\uC5ED\uC870\uD68C");
      btnNewButton_11.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_11 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_11.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_11.setBackground(new Color(229,204,255));
      btnNewButton_11.setBounds(784, 440, 153, 59);
      getContentPane().add(btnNewButton_11);
      
      JButton btnNewButton_6_1 = new JButton("\uC0C1\uD488\uC8FC\uBB38\uC5C5\uBB34");
      btnNewButton_6_1.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_6_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_6_1.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_6_1.setBackground(new Color(204,255,255));
      btnNewButton_6_1.setBounds(621, 188, 153, 59);
      getContentPane().add(btnNewButton_6_1);
      btnNewButton_6_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if( pro != null )
					pro.dispose();
					pro = new LMProdOrder(userid);
		}
	});
      
      JButton btnNewButton_7_1 = new JButton("\uC8FC\uBB38\uB0B4\uC5ED\uC870\uD68C",new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_7_1.setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_7_1.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_7_1.setBackground(new Color(204,255,255));
      btnNewButton_7_1.setBounds(784, 188, 153, 59);
      getContentPane().add(btnNewButton_7_1);
      
      JButton btnNewButton_8_1 = new JButton("\uC0C1\uD488\uC785\uACE0\uC5C5\uBB34");
      btnNewButton_8_1.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_8_1.setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_8_1.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_8_1.setBackground(new Color(204,229,255));
      btnNewButton_8_1.setBounds(621, 274, 151, 59);
      getContentPane().add(btnNewButton_8_1);
      btnNewButton_8_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if (ipo != null)
					ipo.dispose();
					ipo = new LMipgo();
		}
	});
      
      JButton btnNewButton_9_1 = new JButton("\uCD9C\uACE0\uB0B4\uC5ED\uC870\uD68C");
      btnNewButton_9_1.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      btnNewButton_9_1.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uBC84\uD2BC61.png")));
      btnNewButton_9_1.setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      btnNewButton_9_1.setFont(new Font("새굴림", Font.BOLD, 16));
      btnNewButton_9_1.setBackground(new Color(204,204,255));
      btnNewButton_9_1.setBounds(784, 359, 153, 59);
      getContentPane().add(btnNewButton_9_1);
      
      JPanel panel = new JPanel();
      panel.setBounds(798, 565, 139, 29);
      getContentPane().add(panel);
      
      JLabel lbluserid = new JLabel("관리자");
      lbluserid.setForeground(SystemColor.text);
      lbluserid.setFont(new Font("새굴림", Font.BOLD, 15));
      lbluserid.setBounds(12, 440, 165, 40);
      getContentPane().add(lbluserid);
      lbluserid.setText("관리자:" + this.userid);
      
      
      
      JLabel lblNewLabel_1 = new JLabel("dddddd");
      lblNewLabel_1.setFont(new Font("새굴림", Font.PLAIN, 15));
      lblNewLabel_1.setIcon(new ImageIcon(ManagerTitleFinally.class.getResource("/lmimage/\uADF8\uB9B0\uBB3C\uB958\uC2DC\uC2A4\uD15C\uCD5C\uC885.png")));
      
      lblNewLabel_1 .setVerticalTextPosition(JButton.BOTTOM);  // 텍스트 아래로
      lblNewLabel_1 .setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
      
      lblNewLabel_1.setBounds(0, 0, 984, 560);
      getContentPane().add(lblNewLabel_1);
      
      JLabel lblNewLabel = new JLabel("New label");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
      lblNewLabel.setBounds(12, 490, 165, 41);
      getContentPane().add(lblNewLabel);
      ImageIcon ldd = new ImageIcon();
      
      
      setVisible(true);
      
   }

   public static void main(String[] args) {
   new ManagerTitleFinally();

   }
}
