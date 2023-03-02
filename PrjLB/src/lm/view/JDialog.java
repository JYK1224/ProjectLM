package lm.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
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
	
	public JDialog () {
		
		init();
	}

	private void init() {
	this.setTitle("경고메시지");
	
	setIconImage(Toolkit.getDefaultToolkit().getImage(JDialog.class.getResource("/lmimage/alphabets-33744_640.png")));
	
	icon = new ImageIcon("./image/다이얼로그.png");
      
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
	
	JLabel Dlbl = new JLabel("");
	Dlbl.setVerticalAlignment(SwingConstants.TOP);
	Dlbl.setBounds(124, 10, 246, 47);
	panel.add(Dlbl);
	Dlbl.setFont(new Font("새굴림", Font.BOLD, 13));
	
	JButton btnNewButton = new JButton("확인");
	btnNewButton.setBounds(209, 67, 70, 32);
	btnNewButton.setFont(new Font("새굴림", Font.BOLD, 13));
	btnNewButton.setIcon(new ImageIcon(JDialog.class.getResource("/lmimage/신규거래처등록버튼.png")));
	btnNewButton.setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
	
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
	
	this.setSize(400,150);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
	}

	public static void main(String[] args) {
		new JDialog();
	}
}
