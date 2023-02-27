package lm.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import lm.model.ShopDao;
import lm.model.Shopvo;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;


public class ShopProc extends JFrame{
   private JTextField txtCode, txtdname, txtName, txtdPhone;
   private JButton btnIn, btnUp, btnDe, btnCn, btnFind ;
   ImageIcon icon;
	JScrollPane scrollPane;
   
   ShopList slist = null;
   
   public ShopProc () {
      init();
   }
   
   public ShopProc(ShopList slist) {
	   this();
	   this.slist = slist;
   }

   public ShopProc(String sid, ShopList shopList) {
	   this();
	   this.slist = slist;
	   txtCode.setText(sid);
	   btnFind.doClick();
   }

public void init () {
   
      
      setTitle("그린물류시스템");
      icon = new ImageIcon("./image/신규거래처조회111.png");
      
      JPanel panel = new JPanel() {
         public void paintComponent(Graphics g) {
             g.drawImage(icon.getImage(), 0, 0, null);
             setOpaque(false);
             super.paintComponent(g);
            }
      };
      
      scrollPane = new JScrollPane(panel);
      scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
      scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      
      GroupLayout groupLayout = new GroupLayout(getContentPane());
      groupLayout.setHorizontalGroup(
      	groupLayout.createParallelGroup(Alignment.LEADING)
      		.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
      );
      groupLayout.setVerticalGroup(
      	groupLayout.createParallelGroup(Alignment.LEADING)
      		.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
      );
      
    
      
      JLabel lblTitle= new JLabel("신규 점포 등록");
      lblTitle.setBounds(174, 27, 237, 69);
      lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
      
      JLabel lblCode = new JLabel("점포코드");
      lblCode.setForeground(SystemColor.text);
      lblCode.setBounds(20, 146, 96, 18);
      lblCode.setFont(new Font("새굴림", Font.BOLD, 15));
      
      txtCode = new JTextField();
      txtCode.setBounds(167, 141, 195, 29);
      txtCode.setColumns(10);
      
      JLabel lblAname = new JLabel("점포명");
      lblAname.setForeground(SystemColor.text);
      lblAname.setBounds(20, 198, 142, 18);
      lblAname.setFont(new Font("새굴림", Font.BOLD, 15));
      
      txtdname = new JTextField();
      txtdname.setBounds(167, 193, 195, 29);
      txtdname.setColumns(10);
      
      JLabel lblName = new JLabel("담장자");
      lblName.setForeground(SystemColor.text);
      lblName.setBounds(20, 254, 142, 18);
      lblName.setFont(new Font("새굴림", Font.BOLD, 15));
      
      txtName = new JTextField();
      txtName.setBounds(167, 249, 195, 29);
      txtName.setColumns(10);
      
      JLabel lblPhone = new JLabel("연락처");
      lblPhone.setForeground(SystemColor.text);
      lblPhone.setBounds(20, 308, 142, 18);
      lblPhone.setFont(new Font("새굴림", Font.BOLD, 15));
      
      txtdPhone = new JTextField();
      txtdPhone.setBounds(167, 300, 195, 29);
      txtdPhone.setColumns(10);
      //등록
      btnIn = new JButton("등록");
      btnIn.setFont(new Font("새굴림", Font.PLAIN, 13));
      btnIn.setIcon(new ImageIcon(ShopProc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
      btnIn.setHorizontalTextPosition(JButton.CENTER);
      btnIn.setBounds(18, 367, 70, 32);
      //수정
      btnUp = new JButton("수정");
      btnUp.setFont(new Font("새굴림", Font.PLAIN, 13));
      btnUp.setIcon(new ImageIcon(ShopProc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
      btnUp.setHorizontalTextPosition(JButton.CENTER);
      btnUp.setBounds(100, 367, 70, 32);
      //삭제
      btnDe = new JButton("삭제");
      btnDe.setFont(new Font("새굴림", Font.PLAIN, 13));
      btnDe.setIcon(new ImageIcon(ShopProc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
      btnDe.setHorizontalTextPosition(JButton.CENTER);
      btnDe.setBounds(259, 367, 70, 32);
      //취소
      btnCn = new JButton("취소");
      btnCn.setFont(new Font("새굴림", Font.PLAIN, 13));
      btnCn.setIcon(new ImageIcon(ShopProc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
      btnCn.setHorizontalTextPosition(JButton.CENTER);
      btnCn.setBounds(179, 367, 70, 32);
      
      btnFind = new JButton( "조회");
      btnFind.setFont(new Font("새굴림", Font.PLAIN, 13));
      btnFind.setIcon(new ImageIcon(ShopProc.class.getResource("/lmimage/\uC2E0\uADDC\uAC70\uB798\uCC98\uB4F1\uB85D\uBC84\uD2BC.png")));
      btnFind.setHorizontalTextPosition(JButton.CENTER);
      btnFind.setBounds(341, 367, 70, 32);
      panel.setLayout(null);
      panel.add(lblTitle);
      panel.add(lblName);
      panel.add(txtName);
      panel.add(lblPhone);
      panel.add(txtdPhone);
      panel.add(btnIn);
      panel.add(btnUp);
      panel.add(btnDe);
      panel.add(btnCn);
      panel.add(btnFind);
      panel.add(lblAname);
      panel.add(txtdname);
      panel.add(lblCode);
      panel.add(txtCode);
      btnFind.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			findShop();
		}
	});
      btnCn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            cnShop();
         }
      });
      btnDe.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            deShop();
         }
      });
      btnUp.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            upShop();
         }
      });
      btnIn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            addShop();
         }
      });
      getContentPane().setLayout(groupLayout);
      
      setLocation(500,200);
      setSize(454,454);
      setVisible(true);
      setResizable(false);
   }
	//조회
	protected void findShop() {
	   String sid = this.txtCode.getText();
	   if(sid.trim().equals(" "))
		   return;
	   ShopDao sdao = new ShopDao();
	   Shopvo sv = sdao.getSid(sid);
	   setViewData(sv);
	   
   }
	//조회
   private void setViewData(Shopvo sv) {
	   int       shopid = sv.getShopid();
	   String  shopname = sv.getShopname();
	   String sincharge = sv.getSincharge();
	   String    sphone = sv.getSphone();
	   
	   this.txtCode.setText(String.valueOf(shopid));
	   this.txtdname.setText(shopname);
	   this.txtName.setText(sincharge);
	   this.txtdPhone.setText(sphone);
	   
   }
   //새로고침
   protected void cnShop() {
      clearViewData();
   }
   //새로고침
   private void clearViewData() {
      this.txtCode.setText("");
      this.txtdname.setText("");
      this.txtName.setText("");
      this.txtdPhone.setText("");
      this.txtCode.grabFocus();
   }

   //삭제
   protected void deShop() {
      String sid = this.txtCode.getText();
      if(sid.equals(" ") )
         return;
      ShopDao sdao = new ShopDao();
      int choice = JOptionPane.showConfirmDialog(null, 
            sid + "삭제하시겠습니까?",
            "삭제", JOptionPane.OK_CANCEL_OPTION);
      String msg = "";
      if(choice == 0) {
         int aftcnt = sdao.deleteShop(sid);
         if(aftcnt > 0 ) {
            msg = sid + " 지웁니다";
         }
         } else { 
            msg = "취소하였습니다";
         }
      JOptionPane.showMessageDialog(null, msg + " ",
            "삭제하였습니다", JOptionPane.OK_OPTION);
   }

   //수정
   protected void upShop() {
      String sid = this.txtCode.getText();
      ShopDao sdao = new ShopDao();
      
      int choice = JOptionPane.showConfirmDialog(null,
               sid + "수정하시겠습니까",
               "수정하시겠습니까",
               JOptionPane.OK_CANCEL_OPTION
               );
      int aftcnt = 0;
      String msg = " ";
      if( choice == 0 ) {
         Shopvo sv = getViewData();
         aftcnt = sdao.updataDept(sv);
         if( aftcnt > 0 )
            msg = sid + "수정완료";
         else 
            msg = sid + "수정완료";
      } else {
         msg = "취소했습니다";
      }
      JOptionPane.showConfirmDialog(null, msg,
            " ", JOptionPane.OK_OPTION);
   }

   //등록
   protected void addShop() {
      ShopDao sdao = new ShopDao();
      Shopvo sv = getViewData();
      int aftcnt = sdao.insertShop(sv); 
      JOptionPane.showMessageDialog(null, sv.getShopid()+ "저장" );
   }

   private Shopvo getViewData() {
      String sid = this.txtCode.getText();
      String sname = this.txtdname.getText();
      String sincharge = this.txtName.getText();
      String sphone = this.txtdPhone.getText();
      
      Shopvo sv = new Shopvo(
            Integer.parseInt(sid), sname, sincharge, sphone);
      
      return sv;
   }

   public static void main(String[] args) {
      
      new ShopProc();
   }
}