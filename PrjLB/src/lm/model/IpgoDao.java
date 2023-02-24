package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import oracle.jdbc.OracleConnection;



public class IpgoDao {

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
	/*
	// 거래처명으로 조회
	public IpgoVo getMember(String dname) {
		
		IpgoVo  vo = null;
			
		String  sql = "SELECT D.DNAME, O.ORDERDATE, P.PID, P.PNAME, NVL(S.STOCKNUM, 0) STOCKNUM , ";
		sql		+= "NVL(O.ORDERNUM, 0) ORDERNUM, O.ORDERDATE+1 INNDATE, NVL(I.INNUM,0) INNUM, U.USERID ";
		sql		+= "FROM INPUT I, USERMNG  U, ORDERING O, PRODUCT  P, STOCK S, DEPT_ACC D ";
		sql		+= "WHERE  I.USERID(+) = U.USERID ";
		sql		+= "AND    O.USERID(+) = U.USERID ";
		sql		+= "AND    P.PID       = O.PID   (+) ";
		sql		+= "AND    S.PID   (+) = P.PID ";
		sql		+= "AND    D.DID       = S.DID   (+) ";
		sql        += " WHERE DNAME = ?";
				
		PreparedStatement pstmt  =  null;
		ResultSet         rs     =  null;
		try {
			pstmt =  conn.prepareStatement(sql);
			pstmt.setString(1, dname);
			
			rs    =  pstmt.executeQuery();
			
			if( rs.next() ) {
				String  dname1         = rs.getString("dname");       // 거래처명
				String  orderdate      = rs.getString("orderdate");   // 주문일자
				String  pid            = rs.getString("pid");         // 상품코드
				String  pname          = rs.getString("pname");       // 상품명
				String  stocknum       = rs.getString("stocknum");    // 현재재고
				String  ordernum       = rs.getString("ordernum");    // 주문수량
				String  inndate        = rs.getString("inndate");     // 입고예정일
				String  innum          = rs.getString("innum");       // 입고수량
				String  userid         = rs.getString("userid ");     // 입고직원
				
				vo   = new IpgoVo(dname1, orderdate, pid, pname, stocknum, ordernum, inndate, innum, userid);				
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
				
		return    vo;
	}
	*/
	// 데이터 수정 (입고량만 수정 가능)
	public int updateMember(IpgoVo vo) {
		String  sql = "";
		sql  += "UPDATE  INPUT ";
		sql  += " SET    INNUM  = ? ";    // 수정항목 입고량
//		sql  += " WHERE  USERID   = ?"; 조건
		
		int  aftcnt  = 0;
		PreparedStatement  pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getInnum() );
	//		pstmt.setString(2, vo.getPasswd() );
			
			aftcnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null ) pstmt.close();
			} catch (SQLException e) {			
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
	
		public Vector<Vector> geIpgoList() {
			
			Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs
			
			String  sql = "SELECT I.INDATE, D.DNAME,  P.PID, P.PNAME, NVL(P.IPRICE, 0) IPRICE, ";
			sql +=		  "NVL(S.STOCKNUM, 0) STOCKNUM, NVL(I.INNUM,0) INNUM ";
			sql +=		  "FROM INPUT I, PRODUCT P, STOCK S, DEPT_ACC D ";
			sql +=		  "WHERE P.DID = D.DID   (+) ";
			sql +=		  "AND D.DID =  S.DID    (+) ";
			sql +=		  "AND P.PID = I.PID (+) ";
			
			PreparedStatement  pstmt = null;
			ResultSet          rs    = null;
			try {
				pstmt = conn.prepareStatement(sql);
				
				rs    = pstmt.executeQuery();
				while( rs.next() ) {			
					String  indate      = rs.getString("indate");         // 주문일자
					String  dname          = rs.getString("dname");       // 거래처명
					String  pid            = rs.getString("pid");         // 상품코드
					String  pname          = rs.getString("pname");       // 상품명
					String  iprice          = rs.getString("iprice");      // 입고가격
					String  stocknum       = rs.getString("stocknum");    // 현재재고
					String  innum          = rs.getString("innum");       // 입고수량
				
					
					Vector<String>  v         = new Vector<String>();  // 안쪽 Vector : 한 줄 Row 를 의미
					v.add( indate );
					v.add( dname );
					v.add( pid );
					v.add( pname );
					v.add( iprice );
					v.add( stocknum );
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
	
}
