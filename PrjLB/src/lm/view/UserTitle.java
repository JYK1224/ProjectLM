package lm.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;


import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;


public class UserTitle extends JFrame{
   
   
   public UserTitle() {
      Init();
   }

   private void Init() {
      setTitle("물류관리시스템-일반사용자");
      setSize(600,750);
      setLocation(600,150);
      getContentPane().setLayout(null);
      
      JLabel lblNewLabel = new JLabel("그린물류시스템");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 50));
      lblNewLabel.setBounds(104, 25, 379, 84);
      getContentPane().add(lblNewLabel);
      
      JButton btnNewButton_1 = new JButton("상품상세조회");
      btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_1.setBackground(new Color(211,211,211));
      btnNewButton_1.setBounds(84, 142, 179, 59);
      getContentPane().add(btnNewButton_1);
      
      btnNewButton_1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new LMProductInquiry();
			
		}
	});

      
      JButton btnNewButton_2 = new JButton("상품주문업무");
      btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_2.setBackground(new Color(211,211,211));
      btnNewButton_2.setBounds(84, 230, 179, 59);
      getContentPane().add(btnNewButton_2);
      
      JButton btnNewButton_3 = new JButton("상품입고업무");
      btnNewButton_3.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_3.setBackground(new Color(211,211,211));
      btnNewButton_3.setBounds(84, 317, 179, 59);
      getContentPane().add(btnNewButton_3);
      
      JButton btnNewButton_4 = new JButton("주문내역조회");
      btnNewButton_4.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_4.setBackground(new Color(211,211,211));
      btnNewButton_4.setBounds(84, 406, 179, 59);
      getContentPane().add(btnNewButton_4);
      
      JButton btnNewButton_5 = new JButton("입고내역조회");
      btnNewButton_5.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_5.setBackground(new Color(211,211,211));
      btnNewButton_5.setBounds(84, 494, 179, 59);
      getContentPane().add(btnNewButton_5);
      
      JButton btnNewButton_7 = new JButton("거래처조회");
      btnNewButton_7.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_7.setBackground(new Color(211,211,211));
      btnNewButton_7.setBounds(318, 142, 179, 59);
      getContentPane().add(btnNewButton_7);
      
      JButton btnNewButton_8 = new JButton("상품전체조회");
      btnNewButton_8.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_8.setBackground(new Color(211,211,211));
      btnNewButton_8.setBounds(318, 230, 179, 59);
      getContentPane().add(btnNewButton_8);
      
      JButton btnNewButton_9 = new JButton("상품반품등록");
      btnNewButton_9.setFont(new Font("굴림", Font.BOLD, 20));
      btnNewButton_9.setBackground(new Color(211,211,211));
      btnNewButton_9.setBounds(318, 317, 179, 59);
      getContentPane().add(btnNewButton_9);
      setVisible(true);
      setResizable(false);
   }

   public static void main(String[] args) {
   new UserTitle();

   }
}