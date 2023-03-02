package lm.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;


import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class JDialog extends JFrame {
	
	ImageIcon icon;
	JScrollPane scrollPane;
	JLabel Dlbl;
	JButton btnNewButton, btnNewButton2;
	
	
	public JLabel getDlbl() {
		return Dlbl;
	}

	public void setDlbl(JLabel dlbl) {
		Dlbl = dlbl;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
//	public JDialog() {
//		
//	}
	public JDialog (int ss) {
		if(ss == 0) {
		init();
		}
		else {
		init2();	
		}
	}

	private void init2() {
		this.setTitle("경고메시지");
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialog.class.getResource("/lmimage/alphabets-33744_640.png")));
		
		icon = new ImageIcon(JDialog.class.getResource("/lmimage/다이얼로그.png"));
	      
	      JPanel panel = new JPanel() {
	         public void paintComponent(Graphics g) {
	             // Approach 1: Dispaly image at at full size
	             g.drawImage(icon.getImage(), 0, 0, null);
	             // Approach 2: Scale image to size of component
	             // Dimension d = getSize();
	             // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
	             // Approach 3: Fix the image position in the scroll pane
	             // Point p = scrollPane.getViewport().getViewPosition();
	             // g.drawImage(icon.getImage(), p.x, p.y, null);
	             setOpaque(false);
	             super.paintComponent(g);
	            }
	      };
	      
		
		scrollPane = new JScrollPane(panel);
		panel.setLayout(null);
		
		
		
		
		Dlbl = new JLabel("");
		Dlbl.setHorizontalAlignment(SwingConstants.CENTER);
		Dlbl.setBounds(124, 10, 246, 47);
		panel.add(Dlbl);
		Dlbl.setFont(new Font("새굴림", Font.BOLD, 13));
		Dlbl.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton.doClick();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		btnNewButton = new JButton("확인");
		btnNewButton.setBounds(174, 67, 70, 32);
		btnNewButton.setFont(new Font("새굴림", Font.PLAIN, 13));
		btnNewButton.setIcon(new ImageIcon(JDialog.class.getResource("/lmimage/신규거래처등록버튼.png")));
		btnNewButton.setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		btnNewButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton.doClick();
					
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				dispose();	
				
			}

		});
		
		btnNewButton2 = new JButton("취소");
		btnNewButton2.setBounds(254, 67, 70, 32);
		btnNewButton2.setFont(new Font("새굴림", Font.PLAIN, 13));
		btnNewButton2.setIcon(new ImageIcon(JDialog.class.getResource("/lmimage/신규거래처등록버튼.png")));
		btnNewButton2.setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
		btnNewButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		panel.add(btnNewButton);
		panel.add(btnNewButton2);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		btnNewButton.requestFocus();
		this.setSize(400,150);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocation(750,450);
		
	}

	private void init() {
	this.setTitle("경고메시지");
	
	
	setIconImage(Toolkit.getDefaultToolkit().getImage(JDialog.class.getResource("/lmimage/alphabets-33744_640.png")));
	
	icon = new ImageIcon(JDialog.class.getResource("/lmimage/다이얼로그.png"));
      
      JPanel panel = new JPanel() {
         public void paintComponent(Graphics g) {
             // Approach 1: Dispaly image at at full size
             g.drawImage(icon.getImage(), 0, 0, null);
             // Approach 2: Scale image to size of component
             // Dimension d = getSize();
             // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
             // Approach 3: Fix the image position in the scroll pane
             // Point p = scrollPane.getViewport().getViewPosition();
             // g.drawImage(icon.getImage(), p.x, p.y, null);
             setOpaque(false);
             super.paintComponent(g);
            }
      };
      
	
	scrollPane = new JScrollPane(panel);
	panel.setLayout(null);
	
	
	
	
	Dlbl = new JLabel("");
	Dlbl.setHorizontalAlignment(SwingConstants.CENTER);
	Dlbl.setBounds(124, 10, 246, 47);
	panel.add(Dlbl);
	Dlbl.setFont(new Font("새굴림", Font.BOLD, 13));

	
	
	btnNewButton = new JButton("확인");
	btnNewButton.setBounds(209, 67, 70, 32);
	btnNewButton.setFont(new Font("새굴림", Font.PLAIN, 13));
	btnNewButton.setIcon(new ImageIcon(JDialog.class.getResource("/lmimage/신규거래처등록버튼.png")));
	btnNewButton.setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
	btnNewButton.addKeyListener(new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				btnNewButton.doClick();
				
			}
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	btnNewButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();			
		}
	});
	
	
	panel.add(btnNewButton);
	GroupLayout groupLayout = new GroupLayout(getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	);
	getContentPane().setLayout(groupLayout);
	
	btnNewButton.requestFocus();
	this.setSize(400,150);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
	setLocation(750,450);
	}

	public static void main(String[] args) {
		new JDialog(0);
	}
}
