package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import lm.view.LMOutput;
import oracle.jdbc.OracleConnection;

public class OutputDao {


	private OracleConnection conn = null;

	// 생성자
	public OutputDao() {
		conn = DBConn.getInstance();
	}
	public void close() {
		try {
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	// 출고 테이블 전체 불러오기
	public Vector<Vector> getOutput() {

		Vector<Vector> list = new Vector<Vector>();

		String sql = "SELECT D.DNAME, \r\n"
				+ "       I.INDATE, \r\n"
				+ "       O.OUTDATE, \r\n"
				+ "       P.PID, \r\n"
				+ "       P.PNAME, \r\n"
				+ "       P.IPRICE, \r\n"
				+ "       S.STOCKNUM, \r\n"
				+ "       O.OUTNUM, \r\n"
				+ "       U.USERID\r\n"
				+ "  FROM DEPT_ACC D, INPUT I, OUTPUT O, PRODUCT P, STOCK S, USERMNG U\r\n"
				+ "  WHERE D.DID = P.DID (+)\r\n"
				+ "    AND P.PID = I.PID (+)\r\n"
				+ "    AND P.PID = O.PID (+)\r\n"
				+ "    AND P.PID = S.PID (+)\r\n"
				+ "    AND O.USERID = U.USERID (+)";


		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			while( rs.next() ) {
				String dname = rs.getString("DNAME");
				String indate = rs.getString("INDATE");
				String outdate = rs.getString("OUTDATE");
				String pid = rs.getString("PID");
				String pname = rs.getString("PNAME");
				String iprice = rs.getString("IPRICE");
				String stocknum = rs.getString("STOCKNUM");
				String outnum = rs.getString("OUTNUM");
				String userid = rs.getString("USERID");

				Vector v = new Vector();
				v.add(dname );
				v.add(indate );
				v.add(outdate );
				v.add(pid );
				v.add(pname );
				v.add(iprice );
				v.add(stocknum );
				v.add(outnum );
				v.add(userid );

				list.add(v);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( rs != null)
				try {
					if( rs != null) rs.close();
					if( pstmt != null) pstmt.close();
				} catch (SQLException e) {
				}
		} 

		return list;
	}

	// 출고 테이블 검색 후 불러오기
	public Vector<Vector> getOutput(String search) {

		Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

		String  sql = "SELECT D.DNAME , TO_CHAR((SELECT SYSDATE FROM DUAL), 'YYYY-MM-DD') OUTDATE , P.PID , P.PNAME , P.IPRICE , S.STOCKNUM \r\n"
				+ "  FROM  STOCK S, PRODUCT P, DEPT_ACC D\r\n"
				+ "  WHERE S.PID = P.PID (+) \r\n"
				+ "    AND P.DID = D.DID (+) \r\n"
				+ "    AND D.DNAME LIKE '%"+search.toUpperCase().trim()+"%'\r\n"
//				+ "    AND STOCKNUM IS NOT NULL\r\n"
//				+ "    AND STOCKNUM != 0\r\n"
				+ "    ORDER BY D.DNAME, P.PNAME, S.STOCKNUM";

		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			while( rs.next() ) {
				String  dname       = rs.getString("DNAME");	// 1 거래처명
				String  outdate     = rs.getString("OUTDATE");	// 2 출고일자
				String  pid         =  rs.getString("PID");    // 3 상품아이디
				String  pname       =  rs.getString("PNAME");    // 4 상품명
				String  iprice      =  rs.getString("IPRICE");    // 5 입고가격
				String  stocknum    =  rs.getString("STOCKNUM");  // 6 현재재고
				String  shopid      =  LMOutput.ot.getShopId();	// 7 점포명
																// 8 출고수량
//				String  userid    =  rs.getString("USERID");  	// 9 출고직원

				Vector  v         = new Vector();  // 안쪽 Vector : 한 줄 Row 를 의미
				v.add( dname );
				v.add( outdate );
				v.add( pid );
				v.add( pname );
				v.add( iprice );
				v.add( stocknum );
				v.add( shopid );
				v.add( "" );
				v.add( "" );

				list.add( v );  //  전체 목록에 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				if( rs    != null )  rs.close();
				if( pstmt != null )  pstmt.close();
			} catch (SQLException e) {
			}
		}

		return  list;
	}
	
	// 출고 확정 (ordering 테이블에 insert)
	public void insertList(ArrayList<Object> outDate, ArrayList<Object> outputPname,
			ArrayList<Object> shopName, ArrayList<Object> outputNum) {

		int j = outputNum.size();

		for (int i = 0; i < j; i++) {
			if(outputNum.get(i).equals("")) {
				insertList();
			}else {	if(outputNum.get(i).equals("0")) {
				insertList();
			}else {
				String  sql   = "INSERT INTO output (\r\n"
						+ "    outid,\r\n"
						+ "    outdate,\r\n"
						+ "    outnum,\r\n"
						+ "    pid,\r\n"
						+ "    shopid,\r\n"
						+ "    userid\r\n"
						+ ") VALUES (\r\n"
						+ "    (SELECT NVL(MAX(outid), 0) + 1 FROM output),\r\n"
						+ "    TO_date( ? , 'YYYY-MM-DD HH24:MI:SS' ),\r\n"
						+ "    ? ,\r\n"
						+ "    (SELECT PID FROM PRODUCT WHERE PNAME =  ? ),\r\n"
						+ "    (SELECT SHOPID FROM SHOP WHERE SHOPNAME = ? ),\r\n"
						+ "    ? \r\n"
						+ ")";

				System.out.println("0");
				int               aftcnt = 0;
				PreparedStatement pstmt  = null;
				try {
					pstmt  = conn.prepareStatement(sql);			
					System.out.println((String) outDate.get(i));
					pstmt.setString(1, (String) outDate.get(i) );		// 출고일자
					pstmt.setString(2, (String) outputNum.get(i));		// 출고수량
					pstmt.setString(3, (String) outputPname.get(i));	    // pid
					pstmt.setString(4, (String) shopName.get(i));		// shopid
					pstmt.setString(5, "71695210");		// userid
					System.out.println("1");
					aftcnt = pstmt.executeUpdate();
					System.out.println("2." + aftcnt);

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						conn.commit();
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
	
	// 점포 Select 하여 배열에 넣을 Vector(shops) 에 저장
	public Vector<String> setShopVector() {
		Vector<String>  list = new Vector<String>();   // 조회된 결과전체 대응 : rs

		String  sql = "SELECT SHOPNAME FROM SHOP";

		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			list.add("점포명 선택해주세요");
			while( rs.next() ) {
				String  shop       = rs.getString("SHOPNAME");	// 점포명

				list.add( shop );  //  전체 목록에 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				if( rs    != null )  rs.close();
				if( pstmt != null )  pstmt.close();
			} catch (SQLException e) {
			}
		}

		return list;
		
	}
	
	// 출고내역 테이블 검색하기 
	public Vector<Vector> getOutputList(String search, String date1, String date2) {
		Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

		String  sql1 = "SELECT TO_CHAR(O.OUTDATE, 'YYYY-MM-DD HH24:MI:SS') OUTDATE , D.DNAME , P.PID , P.PNAME , P.SPRICE , SH.SHOPNAME, NVL(S.STOCKNUM, 0) STOCKNUM, O.OUTNUM \r\n"
				+ "				FROM OUTPUT O, DEPT_ACC D, PRODUCT P, STOCK S, SHOP SH\r\n"
				+ "				WHERE O.PID = P.PID (+)\r\n"
				+ "				   AND P.DID = D.DID (+)\r\n"
				+ "				   AND P.PID = S.PID (+)\r\n"
				+ "                   AND O.SHOPID = SH.SHOPID (+)\r\n"
				+ "				   AND ( TO_DATE(O.OUTDATE) BETWEEN TO_DATE('"+ date1 +"') AND TO_DATE('"+ date2 +"') ) \r\n"
				+ "				   AND D.DNAME LIKE '%" + search.toUpperCase().trim() + "%'\r\n"
				+ "				   ORDER BY O.OUTDATE DESC";

		String  sql2 = "SELECT TO_CHAR(O.OUTDATE, 'YYYY-MM-DD HH24:MI:SS') OUTDATE , D.DNAME , P.PID , P.PNAME , P.SPRICE , SH.SHOPNAME, NVL(S.STOCKNUM, 0) STOCKNUM, O.OUTNUM \r\n"
				+ "				FROM OUTPUT O, DEPT_ACC D, PRODUCT P, STOCK S, SHOP SH\r\n"
				+ "				WHERE O.PID = P.PID (+)\r\n"
				+ "				   AND P.DID = D.DID (+)\r\n"
				+ "				   AND P.PID = S.PID (+)\r\n"
				+ "                   AND O.SHOPID = SH.SHOPID (+)\r\n"
				+ "				   AND D.DNAME LIKE '%" + search.toUpperCase().trim() + "%'\r\n"
				+ "				   ORDER BY O.OUTDATE DESC";


		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		try {
			if (date1.equals("") && date2.equals("")) {
				pstmt = conn.prepareStatement(sql2);	// 시작일,종료일 없을때
			} else {
				pstmt = conn.prepareStatement(sql1);	// 시작일,종료일 있을때
			}

			rs    = pstmt.executeQuery();
			while( rs.next() ) {
				String  outDate   =  rs.getString("OUTDATE");   // 1
				String  dName       =  rs.getString("DNAME");	// 2
				String  pid       =  rs.getString("PID"); 	 // 3
				String  pName       =  rs.getString("PNAME");    // 4
				String  sPrice      =  rs.getString("SPRICE");    // 5
				String  stockNum    =  rs.getString("STOCKNUM");    // 6
				String  shopName    =  rs.getString("SHOPNAME");    // 7
				String  outNum    =  rs.getString("OUTNUM");	// 8
//				String  userName    =  rs.getString("USERNAME");    // 9

				Vector  v         = new Vector();  // 안쪽 Vector : 한 줄 Row 를 의미
				v.add( outDate );
				v.add( dName );
				v.add( pid );
				v.add( pName );
				v.add( sPrice );
				v.add( stockNum );
				v.add( shopName );
				v.add( outNum );
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
	
	// 재고 테이블에 현재수량 업데이트
	public void updateStock(ArrayList<Object> outputPname, ArrayList<Object> outputNum) {
		int j = outputNum.size();

		for (int i = 0; i < j; i++) {
			if(outputNum.get(i).equals("")) {
				insertList();
			}else {	if(outputNum.get(i).equals("0")) {
				insertList();
			}else {
				String  sql   = " UPDATE STOCK\r\n"
						+ " SET STOCKNUM = NVL(STOCKNUM, 0) - ? \r\n"
						+ " WHERE PID = (SELECT PID FROM PRODUCT WHERE PNAME = ? )";

				System.out.println("0");
				int               aftcnt = 0;
				PreparedStatement pstmt  = null;
				try {
					pstmt  = conn.prepareStatement(sql);			
					pstmt.setString(1, (String) outputNum.get(i) );		// 출고수량
					pstmt.setString(2, (String) outputPname.get(i));	// pid
					aftcnt = pstmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						conn.commit();
						if(pstmt != null) pstmt.close();
					} catch (SQLException e) {
					}
				}		
			} // orderNum.get(i) 가 null 아니고, 0도 아닐때
			}
		}	// for

	}
		

}
