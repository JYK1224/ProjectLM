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


public class UserTitle extends JFrame{
   private JTable table;
   String userid;
   
   public UserTitle() {
      Init();
   }

   public UserTitle(String userid) {
	   this();
	   this.userid = userid;
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
   
     
      JButton btnNewButton_4 = new JButton("상품상세조회");
      btnNewButton_4.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_4.setBackground(new Color(229,255,204));
      btnNewButton_4.setBounds(46, 424, 199, 59);
      getContentPane().add(btnNewButton_4);
      btnNewButton_4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new LMProductInquiry();
			
		}
	});
      
      JButton btnNewButton_5 = new JButton("상품전체조회");
      btnNewButton_5.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_5.setBackground(new Color(229,255,204));
      btnNewButton_5.setBounds(46, 341, 199, 59);
      getContentPane().add(btnNewButton_5);
      
      JButton btnNewButton_6 = new JButton("거래처조회");
      btnNewButton_6.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_6.setBackground(new Color(255,229,204));
      btnNewButton_6.setBounds(46, 171, 199, 59);
      getContentPane().add(btnNewButton_6);
      
      JButton btnNewButton_7 = new JButton("점포전체조회");
      btnNewButton_7.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_7.setBackground(new Color(255,255,204));
      btnNewButton_7.setBounds(46, 255, 199, 59);
      getContentPane().add(btnNewButton_7);
      
      JButton btnNewButton_8 = new JButton("입고내역조회");
      btnNewButton_8.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_8.setBackground(new Color(204,229,255));
      btnNewButton_8.setBounds(546, 255, 199, 59);
      getContentPane().add(btnNewButton_8);
      
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
      
   }

   public static void main(String[] args) {
   new UserTitle();

   }
}