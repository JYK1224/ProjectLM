package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import lm.model.OrderTable;
import lm.view.LMProdOrder;
import oracle.jdbc.OracleConnection;

public class OrderDao {

	private OracleConnection  conn = null;
	
	// 생성자
	public  OrderDao() {
		conn  =  DBConn.getInstance(); 
	}
	public  void close() {
		try {
			if(conn != null)  conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Jtable 에 보여줄 data 목록
	public Vector<Vector> getOrderList() {
		
		Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs
		
		String  sql = "SELECT PCODE 상품코드2, "
				+ "       PNAME 상품명3, "
				+ "       to_char(IPRICE, 'fml999,999') 입고가격4"
				+ "  FROM PRODUCT";
		
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			while( rs.next() ) {			
				String  orderDate   =  LMProdOrder.ot.getOrderDate();   // 1
				String  pCode       =  rs.getString("상품코드2");  // 2
				String  pName       =  rs.getString("상품명3");    // 3
				String  iPrice    =  rs.getString("입고가격4");    // 4
//				String  indate    =  rs.getString(5);    // 5
//				String  indate    =  rs.getString(6);    // 6
//				String  indate    =  rs.getString(7);    // 7
				
				Vector  v         = new Vector();  // 안쪽 Vector : 한 줄 Row 를 의미
				v.add( orderDate );
				v.add( pCode );
				v.add( pName );
				v.add( iPrice );
//				v.add( indate );
//				v.add( "" );
//				v.add( indate );
				
				list.add( v );  //  전체 목록에 추가
				System.out.println(v.toString());
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
			
		return  list;
		
	}
	
}










