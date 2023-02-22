package lm.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ManagerTitle extends JFrame{
   
   
   public ManagerTitle() {
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
      
      JButton btnNewButton = new JButton("신규거래처등록");
      btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton.setBackground(new Color(255,229,204));
      btnNewButton.setBounds(49, 171, 199, 59);
      getContentPane().add(btnNewButton);
      
      JButton btnNewButton_1 = new JButton("신규점포등록");
      btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_1.setBackground(new Color(255,255,204));
      btnNewButton_1.setBounds(49, 255, 199, 59);
      getContentPane().add(btnNewButton_1);
      
      
      JButton btnNewButton_2 = new JButton("사용자관리");
      btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_2.setBackground(new Color(204,255,229));
      btnNewButton_2.setBounds(49, 424, 199, 59);
      getContentPane().add(btnNewButton_2);
      btnNewButton_2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			new UserList();		
		}
	});
      
      JButton btnNewButton_3 = new JButton("신규상품등록");
      btnNewButton_3.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_3.setBackground(new Color(229,255,204));
      btnNewButton_3.setBounds(49, 341, 199, 59);
      getContentPane().add(btnNewButton_3);
      
      JButton btnNewButton_4 = new JButton("상품상세조회");
      btnNewButton_4.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_4.setBackground(new Color(229,255,204));
      btnNewButton_4.setBounds(260, 424, 199, 59);
      getContentPane().add(btnNewButton_4);
      
      JButton btnNewButton_5 = new JButton("상품전체조회");
      btnNewButton_5.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_5.setBackground(new Color(229,255,204));
      btnNewButton_5.setBounds(260, 341, 199, 59);
      getContentPane().add(btnNewButton_5);
      
      JButton btnNewButton_6 = new JButton("거래처조회");
      btnNewButton_6.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_6.setBackground(new Color(255,229,204));
      btnNewButton_6.setBounds(260, 171, 199, 59);
      getContentPane().add(btnNewButton_6);
      
      JButton btnNewButton_7 = new JButton("점포전체조회");
      btnNewButton_7.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_7.setBackground(new Color(255,255,204));
      btnNewButton_7.setBounds(260, 255, 199, 59);
      getContentPane().add(btnNewButton_7);
      
      JButton btnNewButton_8 = new JButton("입고내역조회");
      btnNewButton_8.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_8.setBackground(new Color(204,229,255));
      btnNewButton_8.setBounds(738, 255, 199, 59);
      getContentPane().add(btnNewButton_8);
      
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
      
      JButton btnNewButton_9_1 = new JButton("출고내역조회");
      btnNewButton_9_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_9_1.setBackground(new Color(204,204,255));
      btnNewButton_9_1.setBounds(738, 341, 199, 59);
      getContentPane().add(btnNewButton_9_1);
      
      JPanel panel = new JPanel();
      panel.setBounds(798, 565, 139, 29);
      getContentPane().add(panel);
      setVisible(true);
      
   }

   public static void main(String[] args) {
   new ManagerTitle();

   }
}