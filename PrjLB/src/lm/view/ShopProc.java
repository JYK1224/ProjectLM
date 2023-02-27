package lm.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import lm.model.ShopDao;
import lm.model.Shopvo;


public class ShopProc extends JFrame{
   private JTextField txtCode, txtdname, txtName, txtdPhone;
   private JButton btnIn, btnUp, btnDe, btnCn, btnFind ;
   
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
      
      JLabel lblTitle= new JLabel("신규 점포 등록");
      lblTitle.setFont(new Font("굴림", Font.BOLD, 25));
      
      JLabel lblCode = new JLabel("점포코드");
      lblCode.setFont(new Font("굴림", Font.PLAIN, 15));
      
      JLabel lblAname = new JLabel("점포명");
      lblAname.setFont(new Font("굴림", Font.PLAIN, 15));
      
      JLabel lblName = new JLabel("담장자");
      lblName.setFont(new Font("굴림", Font.PLAIN, 15));
      
      JLabel lblPhone = new JLabel("연락처");
      lblPhone.setFont(new Font("굴림", Font.PLAIN, 15));
      
      txtCode = new JTextField();
      txtCode.setColumns(10);
      
      txtdname = new JTextField();
      txtdname.setColumns(10);
      
      txtName = new JTextField();
      txtName.setColumns(10);
      
      txtdPhone = new JTextField();
      txtdPhone.setColumns(10);
      //등록
      btnIn = new JButton("등록");
      btnIn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            addShop();
         }
      });
      //수정
      btnUp = new JButton("수정");
      btnUp.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            upShop();
         }
      });
      //삭제
      btnDe = new JButton("삭제");
      btnDe.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            deShop();
         }
      });
      //취소
      btnCn = new JButton("취소");
      btnCn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            cnShop();
         }
      });
      
      btnFind = new JButton( "조회");
      btnFind.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			findShop();
		}
	});
      
      GroupLayout groupLayout = new GroupLayout(getContentPane());
      groupLayout.setHorizontalGroup(
         groupLayout.createParallelGroup(Alignment.TRAILING)
            .addGroup(groupLayout.createSequentialGroup()
               .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addGap(80)
                     .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblName, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addComponent(lblAname, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addComponent(lblPhone, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addComponent(lblCode)))
                  .addGroup(groupLayout.createSequentialGroup()
                     .addGap(119)
                     .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(btnDe, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIn, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                     .addGap(33)))
               .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                  .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                     .addPreferredGap(ComponentPlacement.RELATED)
                     .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(txtCode, Alignment.LEADING)
                        .addComponent(txtdname, Alignment.LEADING)
                        .addComponent(txtdPhone, Alignment.LEADING, 195, 195, Short.MAX_VALUE)
                        .addComponent(txtName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                     .addContainerGap())
                  .addGroup(groupLayout.createSequentialGroup()
                     .addGap(18)
                     .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(btnCn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                     .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(btnFind, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                     .addGap(23))))
            .addGroup(groupLayout.createSequentialGroup()
               .addGap(107)
               .addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
               .addGap(96))
      );
      groupLayout.setVerticalGroup(
         groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
               .addGap(20)
               .addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
               .addGap(30)
               .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                  .addComponent(lblCode, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                  .addComponent(txtCode, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
               .addGap(18)
               .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                  .addComponent(lblAname, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                  .addComponent(txtdname, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
               .addGap(18)
               .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                  .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                  .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
               .addGap(18)
               .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                  .addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                  .addComponent(txtdPhone, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
               .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addGap(33)
                     .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                     .addPreferredGap(ComponentPlacement.UNRELATED)
                     .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnCn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDe, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
                  .addGroup(groupLayout.createSequentialGroup()
                     .addGap(50)
                     .addComponent(btnFind, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
               .addContainerGap(33, Short.MAX_VALUE))
      );
      getContentPane().setLayout(groupLayout);
      
      setLocation(500,200);
      setSize(450,450);
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