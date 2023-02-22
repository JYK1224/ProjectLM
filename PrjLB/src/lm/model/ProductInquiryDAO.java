package lm.model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

import lm.view.LMProductInquiry;
import oracle.jdbc.OracleConnection;

public class ProductInquiryDAO {

	private static OracleConnection  conn = null;

	LMProductInquiry ProductInquiry;

	public  ProductInquiryDAO() {
		
	}

	public ProductInquiryDAO(lm.view.LMProductInquiry productInquiry2) throws SQLException {
		this.ProductInquiry = productInquiry2;
		init();  
		init1();
	}

	private void init() throws SQLException {
		if(ProductInquiry.code.isSelected()) {
			conn  =   DBConn.getInstance(); 
			String  sql  = " SELECT  P.PID, P.PNAME, DA.DID, P.IPRICE, P.SPRICE ,"
					+ " A.ANAME, DA.DNAME, NVL(S.STOCKNUM,0) STOCKNUM,ROUND(100-(IPRICE*1.1/SPRICE*100),2) RATEOFRETURN ";
			sql += " FROM   PRODUCT P, ASSORTMENT A, DEPT_ACC DA, STOCK S ";
			sql += " WHERE  P.AID(+) = A.AID ";
			sql += " AND P.DID(+) = DA.DID ";
			sql += " AND S.PID(+) = P.PID ";
			
			sql += " AND P.PCODE = ? ";
			
			PreparedStatement  pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,ProductInquiry.txt.getText());
			ResultSet          rs    = pstmt.executeQuery();
			if( rs.next() ) {			

				String  pcode    =  rs.getString("PID");
				ProductInquiry.tab1.setText(pcode);
				
				String  dcode    =  rs.getString("DID");
				ProductInquiry.tab2.setText(dcode);
				String   pname   =  rs.getString("PNAME");
				ProductInquiry.tab3.setText(pname);
				String  dname   =  rs.getString("DNAME");
				ProductInquiry.tab4.setText(dname);
				String  iprice   =  rs.getString("IPRICE");
				ProductInquiry.tab5.setText(iprice);
				String  sprice   =  rs.getString("SPRICE");
				ProductInquiry.tab7.setText(sprice);
				String  aname   =  rs.getString("ANAME");
				ProductInquiry.tab10.setText(aname);
				String  stocknum  =  rs.getString("STOCKNUM");
				ProductInquiry.tab8.setText(stocknum);
				String  rateofreturn  =  rs.getString("RATEOFRETURN");
				ProductInquiry.tab9.setText(rateofreturn + "%");
			}else {

				JOptionPane.showMessageDialog(null, "잘못된 상품코드 입니다.");
			}
		}
	} 

	private void init1() throws SQLException {
		if(ProductInquiry.name.isSelected()) {
			conn  =   DBConn.getInstance(); 
			String  sql  = " SELECT  P.PID, P.PNAME, DA.DID, P.IPRICE, P.SPRICE ,"
					+ " A.ANAME, DA.DNAME, NVL(S.STOCKNUM,0) STOCKNUM,ROUND(100-(IPRICE*1.1/SPRICE*100),2) RATEOFRETURN ";
			sql += " FROM   PRODUCT P, ASSORTMENT A, DEPT_ACC DA, STOCK S ";
			sql += " WHERE  P.AID(+) = A.AID ";
			sql += " AND P.DID(+) = DA.DID ";
			sql += " AND S.PID(+) = P.PID ";
			
					sql += " AND P.PNAME(+) = ? ";
		

			PreparedStatement  pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,ProductInquiry.txt.getText());
			ResultSet          rs    = pstmt.executeQuery();;
			if( rs.next() ) {			

				String  pcode    =  rs.getString("PID");
				ProductInquiry.tab1.setText(pcode);
				String  dcode    =  rs.getString("DID");
				ProductInquiry.tab2.setText(dcode);
				String   pname   =  rs.getString("PNAME");
				ProductInquiry.tab3.setText(pname);
				String  dname   =  rs.getString("DNAME");
				ProductInquiry.tab4.setText(dname);
				String  iprice   =  rs.getString("IPRICE");
				ProductInquiry.tab5.setText(iprice);
				String  sprice   =  rs.getString("SPRICE");
				ProductInquiry.tab7.setText(sprice);
				String  aname   =  rs.getString("ANAME");
				ProductInquiry.tab10.setText(aname);
				String  stocknum  =  rs.getString("STOCKNUM");
				ProductInquiry.tab8.setText(stocknum);
				String  rateofreturn  =  rs.getString("RATEOFRETURN");
				ProductInquiry.tab9.setText(rateofreturn + "%");
				
			}else {
				JOptionPane.showMessageDialog(null, "잘못된 상품명 입니다.");
			}
		}
	}
}











