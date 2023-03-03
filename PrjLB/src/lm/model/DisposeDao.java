package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import oracle.jdbc.OracleConnection;

public class DisposeDao {
   
   // 필드
	DisposeDao dp;

      private static OracleConnection conn = null;

      // 생성자
      public  DisposeDao() {
         conn  =  DBConn.getInstance();
      }
      public  void close() {
         try {
            if(conn != null)  conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      // 폐기 업무 - 검색 기능 - 진짜임
      public Vector<Vector> getDispose(String search,String userid) {

         Vector<Vector>  list = new Vector<Vector>();

         String  sql = "SELECT A.ANAME, to_char((SELECT SYSDATE FROM DUAL), 'yyyy-mm-dd') DISDATE, P.PID, P.PNAME, NVL(P.IPRICE, 0) IPRICE, NVL(S.STOCKNUM, 0) STOCKNUM\r\n"
         		+ " FROM  ASSORTMENT A, PRODUCT P, STOCK S \r\n"
         		+ " WHERE S.PID = P.PID (+)\r\n"
         		+ "  AND P.AID = A.AID (+)\r\n"
//         		+ "  AND STOCKNUM IS NOT NULL\r\n"
//         		+ "  AND STOCKNUM != 0"
         		+ "  AND A.ANAME LIKE '%"+ search.toUpperCase().trim() +"%'";

         PreparedStatement pstmt  =  null;
         ResultSet         rs     =  null;
         try {
            pstmt =  conn.prepareStatement(sql);
            rs    =  pstmt.executeQuery();         
            while( rs.next() ) {
               String  aname          = rs.getString("ANAME");       // 상품분류
               String  disdate        = rs.getString("DISDATE");      // 폐기일자
               String  pid            = rs.getString("PID");         // 상품코드
               String  pname          = rs.getString("PNAME");       // 상품명
               String  iprice         = rs.getString("IPRICE");      // 입고가격
               String  stocknum       = rs.getString("STOCKNUM");    // 현재재고
//               String  disnum         = rs.getString("");     	 // 폐기수량            
//               String  userid         = rs.getString("");    		  // 입고직원
               
               Vector  v         = new Vector();
               v.add(aname);
               v.add(disdate);
               v.add(pid);
               v.add(pname);
               v.add(iprice);
               v.add(stocknum);
               v.add("");
               v.add(userid);

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

      // 데이터 수정 (입고량만 수정 가능) - 진짜임
      public int updateMember(ArrayList<Object> disPname, ArrayList<Object> disNum) {

         int j = disNum.size();
         int  aftcnt  = 0;

         for (int i = 0; i < j; i++) {
            if(disNum.get(i).equals("")) {
               insertList();
            }else {   if(disNum.get(i).equals("0")) {
               insertList();
            }else {

               String  sql = " UPDATE STOCK "
                     + " SET STOCKNUM =    NVL(STOCKNUM,0) - ? "
                     + " WHERE PID = (SELECT PID FROM PRODUCT WHERE PNAME =  ? )";

               //      sql  += " WHERE  USERID   = ?"; 조건

               PreparedStatement  pstmt = null;
               try {
                  pstmt = conn.prepareStatement(sql);

                  pstmt.setString(1, (String)disNum.get(i));
                  pstmt.setString(2, (String)disPname.get(i));

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
      
      // 상품 폐기 업무 - Jtable 에 보여줄 data 목록

      public Vector<Vector> getDispose() {

         Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

         String  sql = "SELECT DE.DNAME, D.DISDATE, P.PID, P.PNAME, NVL(P.IPRICE, 0), NVL(S.STOCKNUM, 0), NVL(D.DISNUM,0) ";
         sql         += "  FROM  DISUSE D, PRODUCT P, STOCK S, DEPT_ACC DE";
         sql          += "  WHERE P.PID = D.PID(+) ";
         sql          += "  AND   P.DID = DE.DID(+) ";
         sql          += "  AND   P.PID = S.PID(+) ";

         PreparedStatement  pstmt = null;
         ResultSet          rs    = null;
         try {
            pstmt = conn.prepareStatement(sql);

            rs    = pstmt.executeQuery();
            while( rs.next() ) {         
               String  dname          = rs.getString("dname");       // 거래처명
               String  disdate        = rs.getString("disdate");     // 폐기일자
               String  pid            = rs.getString("pid");         // 상품코드
               String  pname          = rs.getString("pname");       // 상품명
               String  iprice         = rs.getString("iprice");      // 입고가격
               String  stocknum       = rs.getString("stocknum");    // 현재재고
               String  disnum         = rs.getString("disnum");      // 폐기수량

               Vector<String>  v         = new Vector<String>();  // 안쪽 Vector : 한 줄 Row 를 의미
               v.add( dname );
               v.add( disdate );
               v.add( pid );
               v.add( pname );
               v.add( iprice );
               v.add( stocknum );
               v.add( disnum );
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
      
      
      // 폐기 확정 - 진짜임
      public void insertList(ArrayList<Object> disDate, ArrayList<Object> disPname, ArrayList<Object> disNum) {

         int j = disNum.size();

         for (int i = 0; i < j; i++) {
            if(disNum.get(i).equals("")) {
               insertList();
            }else {   if(disNum.get(i).equals("0")) {
               insertList();
            }else {
               String  sql   = "INSERT INTO DISUSE (  disid,    disdate,    disnum,   pid,   userid  ) \r\n"
               		+ "   VALUES ( (SELECT NVL(MAX(disid), 0) + 1 FROM disuse),\r\n"
               		+ "             TO_date( ? , 'YYYY-MM-DD HH24:MI:SS' ),\r\n"
               		+ "             ? ,\r\n"
               		+ "             (SELECT PID FROM PRODUCT WHERE PNAME =  ? ),\r\n"
               		+ "             71695210)";
               
               int               aftcnt = 0;
               PreparedStatement pstmt  = null;
               try {
                  pstmt  = conn.prepareStatement(sql);         
                  pstmt.setString(1, (String) disDate.get(i) );      // 폐기일자
                  pstmt.setString(2, (String) disNum.get(i));          // 폐기수량
                  pstmt.setString(3, (String) disPname.get(i));       // 상품명
//                  pstmt.setString(4, (String) username.get(i));      // 폐기직원

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
         }   // for

      }

      public int insertList() {
         return 0;
      }
      
      
      // 폐기내역 검색버튼 - 진짜임
      public Vector<Vector> getDisposeList(String search, String date1, String date2) {
         Vector<Vector>  list = new Vector<Vector>();   // 조회된 결과전체 대응 : rs

         System.out.println(date1);
         System.out.println(date2);

         String  sql1  = "SELECT TO_CHAR(D.DISDATE, 'YYYY-MM-DD HH24:MI:SS') DISDATE, A.ANAME, P.PID, P.PNAME, P.IPRICE, NVL(S.STOCKNUM, 0) STOCKNUM, NVL(D.DISNUM,0) DISNUM, U.USERNAME\r\n"
         		+ " FROM  DISUSE D, ASSORTMENT A, PRODUCT P, STOCK S, USERMNG U\r\n"
         		+ " WHERE S.PID = P.PID (+)\r\n"
         		+ " AND P.AID = A.AID (+)\r\n"
         		+ " AND P.PID = D.PID (+)\r\n"
         		+ " AND D.USERID = U.USERID (+)\r\n "
         		+ " AND A.ANAME LIKE '%"+search.trim().toUpperCase()+"%'\r\n"
         		+ " AND ( TO_DATE(D.DISDATE) BETWEEN TO_DATE('"+date1+"') AND TO_DATE('"+date2+"') )\r\n"
//         		+ " AND STOCKNUM IS NOT NULL\r\n"
//         		+ " AND STOCKNUM != 0\r\n"
         		+ " ORDER BY D.DISDATE ASC";
         		

         String  sql2  = "SELECT TO_CHAR(D.DISDATE, 'YYYY-MM-DD HH24:MI:SS') DISDATE, A.ANAME, P.PID, P.PNAME, P.IPRICE, NVL(S.STOCKNUM, 0) STOCKNUM, NVL(D.DISNUM,0) DISNUM, U.USERNAME\r\n"
         		+ " FROM  DISUSE D, ASSORTMENT A, PRODUCT P, STOCK S, USERMNG U\r\n"
         		+ " WHERE S.PID = P.PID (+)\r\n"
         		+ " AND P.AID = A.AID (+)\r\n"
         		+ " AND P.PID = D.PID (+)\r\n"
         		+ " AND D.USERID = U.USERID (+)\r\n "
         		+ " AND A.ANAME LIKE '%"+search.trim().toUpperCase()+"%'\r\n"
//         		+ " AND STOCKNUM IS NOT NULL\r\n"
//         		+ " AND STOCKNUM != 0\r\n"
         		+ " ORDER BY D.DISDATE ASC";

      
         PreparedStatement  pstmt = null;
         ResultSet          rs    = null;
         try {
            if (date1.equals("") && date2.equals("")) {
               pstmt = conn.prepareStatement(sql2);   // 둘다 날짜가 없을때
            } else {
               pstmt = conn.prepareStatement(sql1);   // 둘다 날짜가 있을때
            }

            rs    = pstmt.executeQuery();
            while( rs.next() ) {
               String  disdate        = rs.getString("DISDATE");    // 폐기일자
               String  aname          = rs.getString("ANAME");       // 상품분류
               String  pid            = rs.getString("PID");         // 상품코드
               String  pname          = rs.getString("PNAME");       // 상품명
               String  iprice         = rs.getString("IPRICE");      // 폐기가격(입고가)
               String  stocknum       = rs.getString("STOCKNUM");    // 현재재고
               String  disnum         = rs.getString("DISNUM");       // 폐기수량
               String  username         = rs.getString("USERNAME");       // 직원ID
               

               Vector  v         = new Vector();  // 안쪽 Vector : 한 줄 Row 를 의미
               v.add( disdate );
               v.add( aname );
               v.add( pid );
               v.add( pname );
               v.add( iprice );
               v.add( stocknum );
               v.add( disnum );
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