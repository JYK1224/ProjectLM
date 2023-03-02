package lm.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import lm.view.JDialog;
import lm.view.LMProductInquiry;
import oracle.jdbc.OracleConnection;

public class ProductInquiryDAO {
	private static OracleConnection  conn = null;
	
private lm.view.LMProductInquiry ev;

public ProductInquiryDAO() {
	
}
public ProductInquiryDAO(LMProductInquiry event) {
	this.ev = event;
	init();
	init1();
}


private void init1() {
	if(ev.getName2().isSelected()) {
		conn  =   DBConn.getInstance(); 
		String  sql  = " SELECT  P.PID, P.PNAME, DA.DID, P.IPRICE, P.SPRICE ,"
				+ " A.ANAME, DA.DNAME, NVL(S.STOCKNUM,0) STOCKNUM,ROUND(100-(IPRICE*1.1/SPRICE*100),2) RATEOFRETURN ";
		sql += " FROM   PRODUCT P, ASSORTMENT A, DEPT_ACC DA, STOCK S ";
		sql += " WHERE  P.AID(+) = A.AID ";
		sql += " AND P.DID = DA.DID(+) ";
		sql += " AND S.PID(+) = P.PID ";		
		sql += " AND P.PNAME = ? ";

		
		PreparedStatement pstmt = null;
		ResultSet          rs   = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ev.getTxt().getText());
			rs    = pstmt.executeQuery();
			
			if( rs.next() ) {			

				String  pcode    =  rs.getString("PID");
				ev.getTab1().setText(pcode);				
				String  dcode    =  rs.getString("DID");
				ev.getTab6().setText(dcode);
				String   pname   =  rs.getString("PNAME");
				ev.getTab2().setText(pname);
				String  dname   =  rs.getString("DNAME");
				ev.getTab7().setText(dname);
				String  iprice   =  rs.getString("IPRICE");
				ev.getTab3().setText(iprice);
				String  sprice   =  rs.getString("SPRICE");
				ev.getTab4().setText(sprice);
				String  aname   =  rs.getString("ANAME");
				ev.getTab10().setText(aname);
				String  stocknum  =  rs.getString("STOCKNUM");
				ev.getTab9().setText(stocknum);
				String  rateofreturn  =  rs.getString("RATEOFRETURN");
				ev.getTab5().setText(rateofreturn + "%");				
			}else {
				JDialog jd = new JDialog();
				jd.getDlbl().setText("잘못된 상품명입니다.");
				
	
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
public void init() {
	if(ev.getCode().isSelected()) {		
		conn  =   DBConn.getInstance(); 
		String  sql  = " SELECT  P.PID, P.PNAME, DA.DID, P.IPRICE, P.SPRICE ,"
				+ " A.ANAME, DA.DNAME, NVL(S.STOCKNUM,0) STOCKNUM,ROUND(100-(IPRICE*1.1/SPRICE*100),2) RATEOFRETURN ";
		sql += " FROM   PRODUCT P, ASSORTMENT A, DEPT_ACC DA, STOCK S ";
		sql += " WHERE  P.AID(+) = A.AID ";
		sql += " AND P.DID = DA.DID(+) ";
		sql += " AND S.PID(+) = P.PID ";		
		sql += " AND P.PID = TO_NUMBER(";
		sql += ev.getTxt().getText() +")";
		
		PreparedStatement pstmt = null;
		ResultSet          rs   = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			
			
			
			if( rs.next() ) {			

				String  pcode    =  rs.getString("PID");
				ev.getTab1().setText(pcode);				
				String  dcode    =  rs.getString("DID");
				ev.getTab6().setText(dcode);
				String   pname   =  rs.getString("PNAME");
				ev.getTab2().setText(pname);
				String  dname   =  rs.getString("DNAME");
				ev.getTab7().setText(dname);
				String  iprice   =  rs.getString("IPRICE");
				ev.getTab3().setText(iprice);
				String  sprice   =  rs.getString("SPRICE");
				ev.getTab4().setText(sprice);
				String  aname   =  rs.getString("ANAME");
				ev.getTab10().setText(aname);
				String  stocknum  =  rs.getString("STOCKNUM");
				ev.getTab9().setText(stocknum);
				String  rateofreturn  =  rs.getString("RATEOFRETURN");
				ev.getTab5().setText(rateofreturn + "%");				
			}else {
				JDialog jd = new JDialog();
				jd.getDlbl().setText("잘못된 상품 코드 입니다.");
			}
			
		} catch (SQLException e) {
			
			JDialog jd = new JDialog();
			jd.getDlbl().setText("상품코드는 숫자로 입력해주세요.");
			
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
		
	}
}
}

