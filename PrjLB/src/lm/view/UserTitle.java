package lm.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;


public class UserTitle extends JFrame{
	
	Thread t1;
	JLabel lblNewLabel_1;
	private JTable table;
	LMProductInquiry piy;
	String userid;
	UserTitle utl;
	DeptList dpl;
	ShopList spl;
	IpgoList ipl;
	LMipgo   ipo;
	LMProdOrder pro;
	PullQuiry pqr;
   
   
   public UserTitle() {
      Init();
   }

   public UserTitle(String userid) {
	   this.userid = userid;
	   Init();
   }

private void Init() {
      setTitle("물류관리시스템-일반사용자");
      setSize(800,580);
      setLocation(600,150);
      getContentPane().setLayout(null);
      
      JLabel lblNewLabel = new JLabel("그린물류시스템");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 70));
      lblNewLabel.setBounds(129, 39, 550, 87);
      getContentPane().add(lblNewLabel);
   
      lblNewLabel_1 = new JLabel("aaa");
	  lblNewLabel_1.setBounds(594, 523, 168, 15);
	  getContentPane().add(lblNewLabel_1);
      
      ThreadTime();
     
      JButton btnNewButton_4 = new JButton("상품상세조회");
      btnNewButton_4.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_4.setBackground(new Color(229,255,204));
      btnNewButton_4.setBounds(46, 424, 199, 59);
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
      btnNewButton_5.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_5.setBackground(new Color(229,255,204));
      btnNewButton_5.setBounds(46, 341, 199, 59);
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
      btnNewButton_6.setBounds(46, 171, 199, 59);
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
      btnNewButton_7.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_7.setBackground(new Color(255,255,204));
      btnNewButton_7.setBounds(46, 255, 199, 59);
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
      btnNewButton_8.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_8.setBackground(new Color(204,229,255));
      btnNewButton_8.setBounds(546, 255, 199, 59);
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
      btnNewButton_9.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_9.setBackground(new Color(204,204,255));
      btnNewButton_9.setBounds(335, 341, 199, 59);
      getContentPane().add(btnNewButton_9);
      
      JButton btnNewButton_10 = new JButton("상품폐기등록");
      btnNewButton_10.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_10.setBackground(new Color(229,204,255));
      btnNewButton_10.setBounds(335, 424, 199, 59);
      getContentPane().add(btnNewButton_10);
      
      JButton btnNewButton_11 = new JButton("폐기내역조회");
      btnNewButton_11.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_11.setBackground(new Color(229,204,255));
      btnNewButton_11.setBounds(546, 424, 199, 59);
      getContentPane().add(btnNewButton_11);
      
      JButton btnNewButton_6_1 = new JButton("상품주문업무");
      btnNewButton_6_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_6_1.setBackground(new Color(204,255,255));
      btnNewButton_6_1.setBounds(335, 171, 199, 59);
      getContentPane().add(btnNewButton_6_1);
      btnNewButton_6_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				if(pro != null)
					pro.dispose();
					pro = new LMProdOrder(userid); 
		}
	});
      
      JButton btnNewButton_7_1 = new JButton("주문내역조회");
      btnNewButton_7_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_7_1.setBackground(new Color(204,255,255));
      btnNewButton_7_1.setBounds(546, 171, 199, 59);
      getContentPane().add(btnNewButton_7_1);
      
      JButton btnNewButton_8_1 = new JButton("상품입고업무");
      btnNewButton_8_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_8_1.setBackground(new Color(204,229,255));
      btnNewButton_8_1.setBounds(335, 255, 199, 59);
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
      btnNewButton_9_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_9_1.setBackground(new Color(204,204,255));
      btnNewButton_9_1.setBounds(546, 341, 199, 59);
      getContentPane().add(btnNewButton_9_1);
      
      JPanel panel = new JPanel();
      panel.setBounds(798, 565, 139, 29);
      getContentPane().add(panel);
      
      table = new JTable();
      table.setBounds(35, 136, 720, 377);
      getContentPane().add(table);
      setVisible(true);
      
      JLabel lbluserid = new JLabel("사용자");
      lbluserid.setFont(new Font("굴림", Font.BOLD, 15));
      lbluserid.setBounds(35, 10, 165, 40);
      getContentPane().add(lbluserid);
      lbluserid.setText("사용자:" + this.userid);
      
   }

   public static void main(String[] args) {
   new UserTitle();

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
}