package lm.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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



public class ManagerTitle extends JFrame  {
	
   private JTable table;
   private Thread t1;
   LMProductInquiry piy;
   String userid , timeClock; 
   JLabel lblNewLabel_1;
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
   
   public ManagerTitle() {
      Init();
   }

   public ManagerTitle(String userid) {
	   this.userid = userid;
	   Init();
   }



   private void Init() {
	   
	   
      setTitle("물류관리시스템-관리자");
      setSize(1000,580);
      setLocation(600,150);
      getContentPane().setLayout(null);
      
      
      JLabel lblNewLabel = new JLabel("그린물류시스템");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 70));
      lblNewLabel.setBounds(239, 39, 550, 87);
      getContentPane().add(lblNewLabel);
      
      lblNewLabel_1 = new JLabel("aaa");
	  lblNewLabel_1.setBounds(796, 516, 164, 15);
	  getContentPane().add(lblNewLabel_1);
      
      ThreadTime();
             
      
      JButton btnNewButton = new JButton("신규거래처등록");
      btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton.setBackground(new Color(255,229,204));
      btnNewButton.setBounds(49, 171, 199, 59);
      getContentPane().add(btnNewButton);
      btnNewButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(dpc != null)
				dpc.dispose();
			   dpc = new DeptProc();
		}
	});
      
      JButton btnNewButton_1 = new JButton("신규점포등록");
      btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_1.setBackground(new Color(255,255,204));
      btnNewButton_1.setBounds(49, 255, 199, 59);
      getContentPane().add(btnNewButton_1);
      btnNewButton_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(spc != null)
				spc.dispose();
				spc = new ShopProc();
		}
	});

      JButton btnNewButton_2 = new JButton("사용자관리");
      btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_2.setBackground(new Color(204,255,229));
      btnNewButton_2.setBounds(49, 424, 199, 59);
      getContentPane().add(btnNewButton_2);
      btnNewButton_2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
				if(utl!= null)
					utl.dispose();
					utl = new UserList();
		}
	});
      
      JButton btnNewButton_3 = new JButton("신규상품등록");
      btnNewButton_3.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_3.setBackground(new Color(229,255,204));
      btnNewButton_3.setBounds(49, 341, 199, 59);
      getContentPane().add(btnNewButton_3);
      btnNewButton_3.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(prs != null)
					prs.dispose();
					prs = new LMProdRegister();
		}
	});
      
      JButton btnNewButton_4 = new JButton("상품상세조회");
      btnNewButton_4.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_4.setBackground(new Color(229,255,204));
      btnNewButton_4.setBounds(260, 424, 199, 59);
      getContentPane().add(btnNewButton_4);
      btnNewButton_4.addActionListener( new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
				if(piy != null)
					piy.dispose();
					piy = new LMProductInquiry();
		}
	});
      
      JButton btnNewButton_5 = new JButton("상품전체조회");
      btnNewButton_5.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_5.setBackground(new Color(229,255,204));
      btnNewButton_5.setBounds(260, 341, 199, 59);
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
      btnNewButton_6.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_6.setBackground(new Color(255,229,204));
      btnNewButton_6.setBounds(260, 171, 199, 59);
      getContentPane().add(btnNewButton_6);
      btnNewButton_6.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(dpl != null)
					dpl.dispose();
					dpl = new DeptList();
		}
	});
      
      JButton btnNewButton_7 = new JButton("점포전체조회");
      btnNewButton_7.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_7.setBackground(new Color(255,255,204));
      btnNewButton_7.setBounds(260, 255, 199, 59);
      getContentPane().add(btnNewButton_7);
      btnNewButton_7.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(spl != null)
					spl.dispose();
					spl = new ShopList();
		}
	});
      
      JButton btnNewButton_8 = new JButton("입고내역조회");
      btnNewButton_8.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_8.setBackground(new Color(204,229,255));
      btnNewButton_8.setBounds(738, 255, 199, 59);
      getContentPane().add(btnNewButton_8);
      btnNewButton_8.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if (ipl != null)
					ipl.dispose();
					ipl = new IpgoList();
		}
	});
      
      JButton btnNewButton_9 = new JButton("상품출고업무");
      btnNewButton_9.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_9.setBackground(new Color(204,204,255));
      btnNewButton_9.setBounds(527, 341, 199, 59);
      getContentPane().add(btnNewButton_9);
      
      JButton btnNewButton_10 = new JButton("상품폐기등록");
      btnNewButton_10.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_10.setBackground(new Color(229,204,255));
      btnNewButton_10.setBounds(527, 424, 199, 59);
      getContentPane().add(btnNewButton_10);
      
      JButton btnNewButton_11 = new JButton("폐기내역조회");
      btnNewButton_11.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_11.setBackground(new Color(229,204,255));
      btnNewButton_11.setBounds(738, 424, 199, 59);
      getContentPane().add(btnNewButton_11);
      
      JButton btnNewButton_6_1 = new JButton("상품주문업무");
      btnNewButton_6_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_6_1.setBackground(new Color(204,255,255));
      btnNewButton_6_1.setBounds(527, 171, 199, 59);
      getContentPane().add(btnNewButton_6_1);
      btnNewButton_6_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if( pro != null )
					pro.dispose();
					pro = new LMProdOrder();
		}
	});
      
      JButton btnNewButton_7_1 = new JButton("주문내역조회");
      btnNewButton_7_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_7_1.setBackground(new Color(204,255,255));
      btnNewButton_7_1.setBounds(738, 171, 199, 59);
      getContentPane().add(btnNewButton_7_1);
      
      JButton btnNewButton_8_1 = new JButton("상품입고업무");
      btnNewButton_8_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_8_1.setBackground(new Color(204,229,255));
      btnNewButton_8_1.setBounds(527, 255, 199, 59);
      getContentPane().add(btnNewButton_8_1);
      btnNewButton_8_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if (ipo != null)
					ipo.dispose();
					ipo = new LMipgo();
		}
	});
      
      JButton btnNewButton_9_1 = new JButton("출고내역조회");
      btnNewButton_9_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_9_1.setBackground(new Color(204,204,255));
      btnNewButton_9_1.setBounds(738, 341, 199, 59);
      getContentPane().add(btnNewButton_9_1);
      
      JPanel panel = new JPanel();
      panel.setBounds(798, 565, 139, 29);
      getContentPane().add(panel);
      
      table = new JTable();
      table.setBounds(33, 136, 922, 374);
      getContentPane().add(table);
      
      JLabel lbluserid = new JLabel("관리자");
      lbluserid.setFont(new Font("굴림", Font.BOLD, 15));
      lbluserid.setBounds(33, 10, 165, 40);
      getContentPane().add(lbluserid);
      lbluserid.setText("관리자:" + this.userid);
      
      


      setVisible(true);
      
   }

  

private void ThreadTime() {
	t1 = new Thread() {
	
	 public void run() {
		 while(true) {
			 Calendar t = Calendar.getInstance();
			 
			 StringBuffer now = new StringBuffer();
			 
			 now.append(t.get(Calendar.YEAR));
			 now.append("년");
			 now.append(t.get(Calendar.MONTH)+1);
			 now.append("월");
			 now.append(t.get(Calendar.DATE));
			 now.append("일");
			 now.append(" ");
			 now.append(t.get(Calendar.HOUR_OF_DAY));
			 now.append("시");
			 now.append(t.get(Calendar.MINUTE));
			 now.append("분");
			 now.append(t.get(Calendar.SECOND));
			 now.append("초");
			 
			 lblNewLabel_1.setText(now.toString());
			 
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
	 }
	 
	};
	 
	t1.start();
}



public static void main(String[] args) {
   new ManagerTitle();

   }


}
