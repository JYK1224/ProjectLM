package lm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JRadioButton;

import lm.view.PullQuiry;

public class PullquiryDAO {

	
private Connection  conn = null;

private lm.view.PullQuiry ev;
	// 생성자
	public  PullquiryDAO(PullQuiry pullQuiry) {
		this.ev = pullQuiry;
	}
	public PullquiryDAO() {

	}
	public  void close() {
		try {
			if(conn != null)  conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void aaa() {
		
	}

	public Vector<Vector> ra11() {
	
		Vector<Vector>  list = new Vector<Vector>(); 
		if(ev.getRa3().isSelected()) {
			conn  =  DBConn.getInstance(); 
		String  sql = "";
		sql  +=  " SELECT  P.PID, P.PNAME, P.IPRICE, P.SPRICE ,ROUND(100-(IPRICE*1.1/SPRICE*100),2) RATEOFRETURN, "
				+ " NVL(S.STOCKNUM,0) STOCKNUM, NVL(IPRICE*STOCKNUM ,0) STOCEPRICE, A.ANAME, DA.DNAME ";
		sql  +=  " FROM   PRODUCT P, DEPT_ACC DA, ASSORTMENT A, STOCK S ";
		sql  +=  " WHERE P.PID = S.PID(+) "
				+ " AND P.DID = DA.DID(+) "
				+ " AND  P.AID = A.AID(+)";
		
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);

			rs    = pstmt.executeQuery();
			while( rs.next() ) {			
				String  pid    =  rs.getString("PID");
				String  pname  =  rs.getString("PNAME"); 
				String  iprice       =  rs.getString("IPRICE");       
				String  sprice    =  rs.getString("SPRICE");  
				String  stocknum    =  rs.getString("RATEOFRETURN");  
				String  rateofreturn    =  rs.getString("STOCKNUM");  
				String  stoceprice    =  rs.getString("STOCEPRICE");  
				String  aname    =  rs.getString("ANAME");  
				String  dname    =  rs.getString("DNAME");  

				Vector  v         = new Vector(); 
				v.add( pid );
				v.add( pname );
				v.add( iprice );
				v.add( sprice );
				v.add( stocknum );
				v.add( rateofreturn );
				v.add( stoceprice );
				v.add( aname );
				v.add( dname );

				list.add( v ); 				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs    != null )  rs.close();
				if( pstmt != null )  pstmt.close();
			} catch (SQLException e) {
			}
		}
		}
	return  list;
		
		
	
		} 
		

		
	
	
	public Vector<Vector> ra22(String combobox) {
		
		Vector<Vector>  list1 = new Vector<Vector>(); 
		//if(ev.getRa1().isSelected()) {
			conn  =  DBConn.getInstance(); 
			String  sql = "";
			sql  +=  " SELECT  P.PID, P.PNAME, P.IPRICE, P.SPRICE ,ROUND(100-(IPRICE*1.1/SPRICE*100),2) RATEOFRETURN, "
					+ " NVL(S.STOCKNUM,0) STOCKNUM, NVL(IPRICE*STOCKNUM ,0) STOCEPRICE, A.ANAME, DA.DNAME ";
			sql  +=  " FROM   PRODUCT P, DEPT_ACC DA, ASSORTMENT A, STOCK S ";
			sql  +=  " WHERE P.PID = S.PID(+) "
					+ " AND P.DID = DA.DID(+) "
					+ " AND  P.AID = A.AID(+) "
					+ " AND A.ANAME =   " + "'" +combobox.trim()+ "'";
			
			PreparedStatement  pstmt = null;
			ResultSet          rs    = null;
			try {
				pstmt = conn.prepareStatement(sql);
				//pstmt.setString(1, ev.getCombo().getSelectedItem());
				
				rs    = pstmt.executeQuery();
				
				while( rs.next() ) {			
					String  pid    =  rs.getString("PID");
					String  pname  =  rs.getString("PNAME"); 
					String  iprice       =  rs.getString("IPRICE");       
					String  sprice    =  rs.getString("SPRICE");  
					String  stocknum    =  rs.getString("RATEOFRETURN");  
					String  rateofreturn    =  rs.getString("STOCKNUM");  
					String  stoceprice    =  rs.getString("STOCEPRICE");  
					String  aname    =  rs.getString("ANAME");  
					String  dname    =  rs.getString("DNAME");  

					Vector  v         = new Vector(); 
					v.add( pid );
					v.add( pname );
					v.add( iprice );
					v.add( sprice );
					v.add( stocknum );
					v.add( rateofreturn );
					v.add( stoceprice );
					v.add( aname );
					v.add( dname );

					list1.add( v ); 				
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if( rs    != null )  rs.close();
					if( pstmt != null )  pstmt.close();
				} catch (SQLException e) {
				}
			}
			
	//}
		return list1;
		
}
	public Vector<Vector> ra33() {
		
		Vector<Vector>  list2 = new Vector<Vector>(); 
		//if(ev.getRa2().isSelected()) {
			conn  =  DBConn.getInstance(); 
			String  sql = "";
			sql  +=  " SELECT  P.PID, P.PNAME, P.IPRICE, P.SPRICE ,ROUND(100-(IPRICE*1.1/SPRICE*100),2) RATEOFRETURN, "
					+ " NVL(S.STOCKNUM,0) STOCKNUM, NVL(IPRICE*STOCKNUM ,0) STOCEPRICE, A.ANAME, DA.DNAME ";
			sql  +=  " FROM   PRODUCT P, DEPT_ACC DA, ASSORTMENT A, STOCK S ";
			sql  +=  " WHERE P.PID = S.PID(+) "
					+ " AND P.DID = DA.DID(+) "
					+ " AND  P.AID = A.AID(+)"
					+ " AND DA.DNAME = ?";
				
			
			PreparedStatement  pstmt = null;
			ResultSet          rs    = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ev.getTxt().getText());

				rs    = pstmt.executeQuery();
				while( rs.next() ) {			
					String  pid    =  rs.getString("PID");
					String  pname  =  rs.getString("PNAME"); 
					String  iprice       =  rs.getString("IPRICE");       
					String  sprice    =  rs.getString("SPRICE");  
					String  stocknum    =  rs.getString("RATEOFRETURN");  
					String  rateofreturn    =  rs.getString("STOCKNUM");  
					String  stoceprice    =  rs.getString("STOCEPRICE");  
					String  aname    =  rs.getString("ANAME");  
					String  dname    =  rs.getString("DNAME");  

					Vector  v         = new Vector(); 
					v.add( pid );
					v.add( pname );
					v.add( iprice );
					v.add( sprice );
					v.add( stocknum );
					v.add( rateofreturn );
					v.add( stoceprice );
					v.add( aname );
					v.add( dname );

					list2.add( v ); 				
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if( rs    != null )  rs.close();
					if( pstmt != null )  pstmt.close();
				} catch (SQLException e) {
				}
			}
		//}
		return  list2;
	
	}


}	
	
	

