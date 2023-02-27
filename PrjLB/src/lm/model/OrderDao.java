package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import lm.view.LMProdOrder;
import oracle.jdbc.OracleConnection;

public class OrderDao {

	LMProdOrder o3;
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

	// 주문 테이블 전체 불러오기
	public Vector<Vector> getOrder() {

		Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

		String  sql = "SELECT D.DNAME 거래처명1,\r\n"
				+ "       P.PID 상품코드3,\r\n"
				+ "       P.PNAME 상품명4,\r\n"
				+ "       P.IPRICE 입고가격5,\r\n"
				+ "       NVL(S.STOCKNUM,0) 현재재고6\r\n"
				+ "  FROM DEPT_ACC D , PRODUCT P , STOCK S\r\n"
				+ "  WHERE D.DID = P.DID (+)\r\n"
				+ "  AND P.PID = S.PID (+)\r\n"
				+ "  ORDER BY DNAME ASC";

		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			while( rs.next() ) {			
				String  deptName   =  rs.getString("거래처명1");   // 1
				String  orderDate  =  LMProdOrder.ot.getOrderDate();   // 2 주문일자
				String  pCode      =  rs.getString("상품코드3");  // 3
				String  pName      =  rs.getString("상품명4");    // 4
				String  iPrice     =  rs.getString("입고가격5");    // 5
				String  stockNum   =  rs.getString("현재재고6");    // 6
				// 7
				String  userName     =  "";   // 8

				Vector  v         = new Vector();  // 안쪽 Vector : 한 줄 Row 를 의미
				v.add( deptName );
				v.add( orderDate );
				v.add( pCode );
				v.add( pName );
				v.add( iPrice );
				v.add( stockNum );
				v.add( "" );
				v.add( "" );

				list.add( v );  //  전체 목록에 추가
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

	// 주문 테이블 검색 후 불러오기
	public Vector<Vector> getOrder(String search) {

		Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

		String  sql = "SELECT D.DNAME ,  P.PID, P.PNAME, P.IPRICE , NVL(S.STOCKNUM, 0) STOCKNUM \r\n"
				+ "FROM DEPT_ACC D , PRODUCT P , STOCK S\r\n"
				+ "WHERE D.DID(+) = P.DID\r\n"
				+ "AND P.PID = S.PID(+)\r\n"
				+ "AND D.DNAME LIKE '%"+ search.toUpperCase().trim() + "%'\r\n"
				+ "ORDER BY D.DNAME ASC";

		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			while( rs.next() ) {
				String  dName       = rs.getString("DNAME");	// 1
				String  orderDate   =  LMProdOrder.ot.getOrderDate();   // 2
				String  pCode       =  rs.getString("PID");  // 3
				String  pName       =  rs.getString("PNAME");    // 4
				String  iPrice      =  rs.getString("IPRICE");    // 5
				String  stockNum    =  rs.getString("STOCKNUM");    // 6
				String  userName    =  "";  						 // 8

				Vector  v         = new Vector();  // 안쪽 Vector : 한 줄 Row 를 의미
				v.add(dName);
				v.add( orderDate );
				v.add( pCode );
				v.add( pName );
				v.add( iPrice );
				v.add( stockNum );
				v.add( "" );
				v.add( userName );


				list.add( v );  //  전체 목록에 추가
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

	// 주문내역 테이블 불러오기
	public Vector<Vector> getOrderList() {
		Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

		String  sql = "SELECT O.ORDERDATE 주문일자1,\r\n"
				+ "       D.DNAME 거래처명2,\r\n"
				+ "       P.PID 상품코드3,\r\n"
				+ "       P.PNAME 상품명4,\r\n"
				+ "       P.IPRICE 입고가격5,\r\n"
				+ "       NVL(S.STOCKNUM,0) 현재재고6,\r\n"
				+ "       O.ORDERNUM 주문수량7,\r\n"
				+ "       U.USERNAME 주문직원8\r\n"
				+ "  FROM DEPT_ACC D , ORDERING O , PRODUCT P , STOCK S, USERMNG U\r\n"
				+ "  WHERE D.DID = P.DID (+)\r\n"
				+ "  AND P.PID = S.PID (+)\r\n"
				+ "  AND P.PID = O.PID (+)\r\n"
				+ "  AND O.USERID = U.USERID (+)\r\n"
				+ "  ORDER BY ORDERDATE ASC";

		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			while( rs.next() ) {			
				String  orderDate  =  rs.getString("주문일자1");   // 1 주문일자
				String  deptName   =  rs.getString("거래처명2");   // 2
				String  pCode      =  rs.getString("상품코드3");  // 3
				String  pName      =  rs.getString("상품명4");    // 4
				String  iPrice     =  rs.getString("입고가격5");    // 5
				String  stockNum   =  rs.getString("현재재고6");    // 6
				String  orderNum   =  rs.getString("주문수량7");  // 7
				String  userName     =  rs.getString("주문직원8");    // 8

				Vector  v         = new Vector();  // 안쪽 Vector : 한 줄 Row 를 의미
				v.add( orderDate );
				v.add( deptName );
				v.add( pCode );
				v.add( pName );
				v.add( iPrice );
				v.add( stockNum );
				v.add( orderNum );
				v.add( userName );

				list.add( v );  //  전체 목록에 추가
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

	// 주문내역 테이블 검색하기 
	public Vector<Vector> getOrderList(String search, String date1, String date2 ) {
		Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

		System.out.println(date1);
		System.out.println(date2);

		String  sql1 = " SELECT D.DNAME , O.ORDERDATE,  P.PID, P.PNAME, P.IPRICE , NVL(S.STOCKNUM, 0) STOCKNUM , O.ORDERNUM, U.USERNAME\r\n "
				+ " FROM DEPT_ACC D , ORDERING O , PRODUCT P , STOCK S, USERMNG U\r\n "
				+ " WHERE D.DID = P.DID (+)\r\n "
				+ " AND P.PID = S.PID (+)\r\n "
				+ " AND P.PID = O.PID (+)\r\n "
				+ " AND O.USERID = U.USERID (+)\r\n "
				+ " AND DNAME LIKE '%" + search.toUpperCase().trim() + "%'\r\n "
				+ " AND ( TO_DATE(O.ORDERDATE) BETWEEN TO_DATE('"+ date1 +"') AND TO_DATE('"+ date2 +"') ) \r\n "
				+ " ORDER BY O.ORDERDATE ASC ";

		String  sql2 = "SELECT D.DNAME , O.ORDERDATE,  P.PID, P.PNAME, P.IPRICE , NVL(S.STOCKNUM, 0) STOCKNUM , O.ORDERNUM, U.USERNAME\r\n"
				+ "  FROM DEPT_ACC D , ORDERING O , PRODUCT P , STOCK S, USERMNG U\r\n"
				+ "WHERE O.DID = D.DID\r\n"
				+ "  AND D.DID = P.DID\r\n"
				+ "  AND P.PID = S.PID\r\n"
				+ "  AND O.USERID = U.USERID\r\n"
				+ "  AND DNAME LIKE '%"+ search.toUpperCase().trim() +"%'\r\n"
				+ "  ORDER BY D.DNAME, O.ORDERDATE ASC";


		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			if (date1.equals("") && date2.equals("")) {
				pstmt = conn.prepareStatement(sql2);
			} else {
				pstmt = conn.prepareStatement(sql1);
			}

			rs    = pstmt.executeQuery();
			while( rs.next() ) {
				String  orderDate   =  rs.getString("ORDERDATE");   // 1
				String  dName       =  rs.getString("DNAME");	// 2
				String  pCode       =  rs.getString("PID"); 	 // 3
				String  pName       =  rs.getString("PNAME");    // 4
				String  iPrice      =  rs.getString("IPRICE");    // 5
				String  stockNum    =  rs.getString("STOCKNUM");    // 6
				String  orderNum    =  rs.getString("ORDERNUM");	// 7
				String  userName    =  rs.getString("USERNAME");    // 8

				Vector  v         = new Vector();  // 안쪽 Vector : 한 줄 Row 를 의미
				v.add( orderDate );
				v.add( dName );
				v.add( pCode );
				v.add( pName );
				v.add( iPrice );
				v.add( stockNum );
				v.add( orderNum );
				v.add( userName );

				list.add( v );  //  전체 목록에 추가
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


	// 주문하기
	public void insertList(ArrayList<Object> orderNum, ArrayList<Object> orderDate, ArrayList<Object> orderDname, ArrayList<Object> orderPname) {

		int j = orderNum.size();

		for (int i = 0; i < j; i++) {
			if(orderNum.get(i).equals("")) {
				insertList();
			}else {	if(orderNum.get(i).equals("0")) {
				insertList();
			}else {
				String  sql   = "INSERT INTO ordering (\r\n"
						+ "    orderid,\r\n"
						+ "    ordernum,\r\n"
						+ "    orderdate,\r\n"
						+ "    userid,\r\n"
						+ "    pid,\r\n"
						+ "    did\r\n"
						+ ") VALUES (\r\n"
						+ "    (SELECT NVL(MAX(orderid), 0) + 1 FROM ordering),\r\n"
						+ "    ? ,\r\n"
						+ "    TO_date( ? , 'YYYY-MM-DD HH:MI:SS' ) ,\r\n"
						+ "    71695210,\r\n"
						+ "    (SELECT PID FROM PRODUCT WHERE PNAME =  ? ),\r\n"
						+ "    (SELECT DID FROM DEPT_ACC WHERE DNAME =  ? )\r\n"
						+ ")";

				int               aftcnt = 0;
				PreparedStatement pstmt  = null;
				try {
					pstmt  = conn.prepareStatement(sql);			
					pstmt.setString(1, (String) orderNum.get(i) );		// 주문수량
					pstmt.setString(2, (String) orderDate.get(i));		// 주문일자
					//			pstmt.setString(3, username);	// 주문직원ID
					pstmt.setString(3, (String) orderPname.get(i));	    // 상품명
					pstmt.setString(4, (String) orderDname.get(i));		// 거래처명

					aftcnt = pstmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if(pstmt != null) pstmt.close();
					} catch (SQLException e) {
					}
				}		
			} // orderNum.get(i) 가 null 아니고, 0도 아닐때
			}
		}	// for

	}

	public int insertList() {
		return 0;
	}


}
