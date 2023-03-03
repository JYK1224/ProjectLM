package lm.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.commons.math3.analysis.function.Add;

import lm.view.LMipgo;
import oracle.jdbc.OracleConnection;


public class IpgoDao {

	// 필드
	LMipgo ll;

	private static OracleConnection conn = null;

	// 생성자
	public  IpgoDao() {
		conn  =  DBConn.getInstance();
	}
	public  void close() {
		try {
			if(conn != null)  conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 입고 업무 - 검색 기능
	public Vector<Vector> getOrder(String search) {

		Vector<Vector>  list = new Vector<Vector>();

		String  sql = "SELECT D.DNAME, O.ORDERDATE, P.PID, P.PNAME, NVL(S.STOCKNUM, 0) STOCKNUM , \r\n"
				+ "NVL(O.ORDERNUM, 0) ORDERNUM,  U.USERID \r\n"
				+ "FROM USERMNG U, ORDERING O, PRODUCT  P, STOCK S, DEPT_ACC D \r\n"
				+ "WHERE O.DID = D.DID (+)\r\n"
				+ "  AND O.PID = P.PID (+)\r\n"
				+ "  AND P.PID = S.PID (+)\r\n"
				+ "  AND O.USERID = U.USERID (+)\r\n"
				+ " AND DNAME LIKE '%"+ search.toUpperCase().trim() +"%'"
				+ " AND TO_DATE(ORDERDATE) BETWEEN  TO_DATE('" +LMipgo.lmvo.getOrderdate() +"')-1 AND TO_DATE('" + LMipgo.lmvo.getOrderdate() +"')"
				+ " ORDER BY D.DNAME, O.ORDERDATE ASC";

		PreparedStatement pstmt  =  null;
		ResultSet         rs     =  null;
		try {
			pstmt =  conn.prepareStatement(sql);
			rs    =  pstmt.executeQuery();			
			while( rs.next() ) {
				String  dname          = rs.getString("DNAME");       // 거래처명
				String  orderdate      = LMipgo.lmvo.getOrderdate();   // 주문일자
				String  indate         = LMipgo.lmvo.getIndate()  ;   // 입고일자
				String  pid            = rs.getString("PID");         // 상품코드
				String  pname          = rs.getString("PNAME");       // 상품명
				String  stocknum       = rs.getString("STOCKNUM");    // 현재재고
				String  ordernum       = rs.getString("ORDERNUM");    // 주문수량				
				String  userid         = rs.getString("USERID");     // 입고직원


				Vector  v         = new Vector();
				v.add(dname);
				v.add(orderdate);
				v.add(indate);
				v.add(pid);
				v.add(pname);
				v.add(stocknum);
				v.add(ordernum);
				v.add(ordernum);
				v.add(userid);

				list.add(v);

			}
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs    != null)   rs.close();
				if(pstmt != null)   pstmt.close();
			} catch (SQLException e) {		
			}
		}		

		return    list;
	}


	// 입고내역 조회 -검색기능
	public Vector<Vector> getIpgo(String search) {

		Vector<Vector>  list = new Vector<Vector>();

		String  sql = "SELECT I.INDATE, D.DNAME,  P.PID, P.PNAME, NVL(P.IPRICE, 0) IPRICE, ";
		sql +=		  "NVL(S.STOCKNUM, 0) STOCKNUM, NVL(I.INNUM,0) INNUM ";
		sql +=		  "FROM INPUT I, PRODUCT P, STOCK S, DEPT_ACC D ";
		sql +=		  "WHERE P.DID = D.DID   (+) ";
		sql +=		  "AND D.DID =  S.DID    (+) ";
		sql +=		  "AND P.PID = I.PID (+) ";
		sql +=        "AND DNAME = UPPER('" +search.trim() + "')" ;

		PreparedStatement pstmt  =  null;
		ResultSet         rs     =  null;
		try {
			pstmt =  conn.prepareStatement(sql);


			rs    =  pstmt.executeQuery();			
			while( rs.next() ) {
				String  indate      = rs.getString("indate");      // 입고일자
				String  dname       = rs.getString("dname");       // 거래처명
				String  pid         = rs.getString("pid");         // 상품코드
				String  pname       = rs.getString("pname");       // 상품명
				String  iprice      = rs.getString("iprice");      // 입고가격
				String  stocknum    = rs.getString("stocknum");    // 현재재고
				String  innum       = rs.getString("innum");       // 입고수량


				Vector  v         = new Vector();
				v.add( indate );
				v.add( dname );
				v.add( pid );
				v.add( pname );
				v.add( iprice );
				v.add( stocknum );
				v.add( innum );
				v.add( "" );

				list.add(v);				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs    != null)   rs.close();
				if(pstmt != null)   pstmt.close();
			} catch (SQLException e) {		
			}
		}		

		return    list;
	}



	// 데이터 수정 (입고량만 수정 가능)
	public int updateMember(ArrayList<Object> inPname, ArrayList<Object> inNum) {

		int j = inNum.size();
		int  aftcnt  = 0;

		for (int i = 0; i < j; i++) {
			if(inNum.get(i).equals("")) {
				insertList();
			}else {	if(inNum.get(i).equals("0")) {
				insertList();
			}else {

				String  sql = " UPDATE STOCK "
						+ " SET STOCKNUM = 	NVL(STOCKNUM,0) + ? "
						+ " WHERE PID = (SELECT PID FROM PRODUCT WHERE PNAME =  ? )";

				//		sql  += " WHERE  USERID   = ?"; 조건

				PreparedStatement  pstmt = null;
				try {
					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, (String)inNum.get(i));
					pstmt.setString(2, (String)inPname.get(i));

					aftcnt = pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if(pstmt != null ) pstmt.close();
					} catch (SQLException e) {			
					}
				}
			}
			}
		}
		return aftcnt;
	}


	// 상품 입고 업무 - Jtable 에 보여줄 data 목록

	public Vector<Vector> getLmList() {

		Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

		String  sql = "SELECT D.DNAME, O.ORDERDATE, P.PID, P.PNAME, NVL(S.STOCKNUM, 0) STOCKNUM, ";
		sql +=		  "NVL(O.ORDERNUM, 0) ORDERNUM, O.ORDERDATE+1 INNDATE, NVL(I.INNUM,0) INNUM ";
		sql +=		  "FROM INPUT I, ORDERING O, PRODUCT  P, STOCK S, DEPT_ACC D ";
		sql +=		  "WHERE P.DID = D.DID   (+) ";
		sql +=		  "AND D.DID =  S.DID    (+) ";
		sql +=		  "AND D.DID = O.DID (+) ";
		sql +=		  "AND P.PID = I.PID (+)";

		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);

			rs    = pstmt.executeQuery();
			while( rs.next() ) {			
				String  dname          = rs.getString("dname");       // 거래처명
				String  orderdate      = rs.getString("orderdate");   // 주문일자
				String  pid            = rs.getString("pid");         // 상품코드
				String  pname          = rs.getString("pname");       // 상품명
				String  stocknum       = rs.getString("stocknum");    // 현재재고
				String  ordernum       = rs.getString("ordernum");    // 주문수량
				String  inndate        = rs.getString("inndate");     // 입고예정일
				String  innum          = rs.getString("innum");       // 입고수량


				Vector<String>  v         = new Vector<String>();  // 안쪽 Vector : 한 줄 Row 를 의미
				v.add( dname );
				v.add( orderdate );
				v.add( pid );
				v.add( pname );
				v.add( stocknum );
				v.add( ordernum );
				v.add( inndate );
				v.add( innum );
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

	// 입고내역 조회 - Jtable 에 보여줄 data 목록

	public Vector<Vector> getIpgoList() {

		Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

		String  sql = "SELECT I.INDATE, D.DNAME,  P.PID, P.PNAME, NVL(P.IPRICE, 0) IPRICE, ";
		sql +=		  "NVL(S.STOCKNUM, 0) STOCKNUM, NVL(I.INNUM,0) INNUM  , u.username username ";
		sql +=		  "FROM INPUT I, PRODUCT P, STOCK S, DEPT_ACC D , usermng u";
		sql +=		  "WHERE P.DID = D.DID   (+) ";
		sql +=		  "AND D.DID =  S.DID    (+) ";
		sql +=		  "AND P.PID = I.PID (+) "
				    + "and i.userid = u.userid ";

		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);

			rs    = pstmt.executeQuery();
			while( rs.next() ) {			
				String  indate         = rs.getString("indate");      // 입고일자
				String  dname          = rs.getString("dname");       // 거래처명
				String  pid            = rs.getString("pid");         // 상품코드
				String  pname          = rs.getString("pname");       // 상품명
				String  iprice         = rs.getString("iprice");      // 입고가격
				String  stocknum       = rs.getString("stocknum");    // 현재재고
				String  innum          = rs.getString("innum");       // 입고수량
				String  username       = rs.getString("username");

				Vector<String>  v         = new Vector<String>();  // 안쪽 Vector : 한 줄 Row 를 의미
				v.add( indate );
				v.add( dname );
				v.add( pid );
				v.add( pname );
				v.add( iprice );
				v.add( stocknum );
				v.add( innum );
				v.add( username );

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

	// 입고 확정
	public void insertList(ArrayList<Object> inDate, ArrayList<Object> inPname, ArrayList<Object> inNum,String user) {

		int j = inNum.size();

		for (int i = 0; i < j; i++) {
			if(inNum.get(i).equals("")) {
				insertList();
			}else {	if(inNum.get(i).equals("0")) {
				insertList();
			}else {
				String  sql   = "INSERT INTO input (\r\n"
						+ "    inid,\r\n"
						+ "    indate,\r\n"
						+ "    innum,\r\n"
						+ "    pid,\r\n"
						+ "    userid\r\n"
						+ ") VALUES (\r\n"
						+ "    (SELECT NVL(MAX(inid), 0) + 1 FROM input),\r\n"
						+ "    TO_date( ? , 'YYYY-MM-DD HH24:MI:SS' ),\r\n"
						+ "    ?,\r\n"
						+ "    (SELECT PID FROM PRODUCT WHERE PNAME =  ? ),\r\n"
						+ "    ?\r\n"
						+ ")";


				int               aftcnt = 0;
				PreparedStatement pstmt  = null;
				try {
					pstmt  = conn.prepareStatement(sql);			
					pstmt.setString(1, (String) inDate.get(i) );		// 입고일자
					pstmt.setString(2, (String) inNum.get(i));		// 입고수량
					pstmt.setString(3, (String) inPname.get(i));	    // 상품명
					pstmt.setString(4, user);		// 입고직원

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


	// 입고내역 검색버튼
	//----------------------------------------------------------
	// sql1이 오라클에서는 작동하는데 이클립스에서는 안됨
	// 기간 설정해서 cj것만 안나옴
	public Vector<Vector> getInputList(String search, String date1, String date2) {
		Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

		System.out.println(date1);
		System.out.println(date2);

		String  sql1 = "SELECT TO_CHAR(I.INDATE, 'YYYY-MM-DD HH24:MI:SS') INDATE , D.DNAME, P.PID, P.PNAME, P.IPRICE, S.STOCKNUM, I.INNUM, U.username\r\n"
				+ "  FROM INPUT I, DEPT_ACC D, PRODUCT P, STOCK S, USERMNG U\r\n"
				+ "  WHERE I.PID = P.PID (+)\r\n"
				+ "    AND P.DID = D.DID (+)\r\n"
				+ "    AND P.PID = S.PID (+)\r\n"
				+ "    AND I.USERID = U.USERID (+)\r\n"
				+ "    AND D.DNAME LIKE '%"  + search.toUpperCase().trim() + "%'\r\n"
				+ "    AND ( TO_DATE(I.INDATE) BETWEEN TO_DATE('"+ date1 +"') AND TO_DATE('"+ date2 +"') )\r\n"
				+ "    ORDER BY I.INDATE DESC";

		String  sql2 = "SELECT TO_CHAR(I.INDATE, 'YYYY-MM-DD HH24:MI:SS') INDATE , D.DNAME, P.PID, P.PNAME, P.IPRICE, S.STOCKNUM, I.INNUM, U.username\r\n"
				+ "  FROM INPUT I, DEPT_ACC D, PRODUCT P, STOCK S, USERMNG U\r\n"
				+ "  WHERE I.PID = P.PID (+)\r\n"
				+ "    AND P.DID = D.DID (+)\r\n"
				+ "    AND P.PID = S.PID (+)\r\n"
				+ "    AND I.USERID = U.USERID\r\n"
				+ "    AND D.DNAME LIKE '%"+ search.toUpperCase().trim() +"%'\r\n"
				+ "    ORDER BY I.INDATE DESC";


		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			if (date1.equals("") && date2.equals("")) {
				pstmt = conn.prepareStatement(sql2);	// 둘다 날짜가 있을때
			} else {
				pstmt = conn.prepareStatement(sql1);	// 둘다 날짜가 없을때
			}

			rs    = pstmt.executeQuery();
			while( rs.next() ) {
				String  indate      = rs.getString("INDATE");         // 주문일자
				String  dname          = rs.getString("DNAME");       // 거래처명
				String  pid            = rs.getString("PID");         // 상품코드
				String  pname          = rs.getString("PNAME");       // 상품명
				String  iprice          = rs.getString("IPRICE");      // 입고가격
				String  stocknum       = rs.getString("STOCKNUM");    // 현재재고
				String  innum          = rs.getString("INNUM");       // 입고수량
				String  username          = rs.getString("username");       // 직원ID


				Vector  v         = new Vector();  // 안쪽 Vector : 한 줄 Row 를 의미
				v.add( indate );
				v.add( dname );
				v.add( pid );
				v.add( pname );
				v.add( iprice );
				v.add( stocknum );
				v.add( innum );
				v.add( username );

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

}
