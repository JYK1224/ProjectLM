package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleConnection;

public class ShopDao {

   private OracleConnection conn = null;
   
   public ShopDao () {
      conn = DBConn.getInstance();
   }

   public int insertShop(Shopvo sv) {
      
      int sid = sv.getShopid();
      String sname = sv.getShopname();
      String sincharge = sv.getSincharge();
      String sphone = sv.getSphone();
      
      int aftcnt = insertShop(sid, sname, sincharge, sphone);
      
      return aftcnt;
   }
   
   //추가
   private int insertShop(int sid, String sname, String sincharge , String sphone) {
      String sql = " insert into shop " 
               + " ( SHOPID, SHOPNAME, SINCHARGE, SPHONE ) "
               + " values "
               + " ( ?, ?, ?, ? )";
      
      PreparedStatement pstmt = null;
      int aftcnt = 0;
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, sid);
         pstmt.setString(2, sname);
         pstmt.setString(3, sincharge);
         pstmt.setString(4, sphone);
         
         aftcnt = pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(pstmt != null) pstmt.close();
         } catch (SQLException e) {
         }
      }
      return aftcnt;
   }
   //수정
   public int updataDept(Shopvo sv) {
      String sql = " update shop "
               + " set "
               + "     SHOPNAME     = ?, "
               + "     SINCHARGE = ? , "
               + "     SPHONE    = ? "
               + " where SHOPID     = ? " ;
      
      PreparedStatement pstmt = null;
      int aftcnt = 0;
      try {
    	  pstmt = conn.prepareStatement(sql);
    	  pstmt.setString(1, sv.getShopname());
    	  pstmt.setString(2, sv.getSincharge());
    	  pstmt.setString(3, sv.getSphone());
    	  pstmt.setInt(4, sv.getShopid());
             
         aftcnt = pstmt.executeUpdate();
      }catch(SQLException e){
         e.printStackTrace();
      }finally {
         try {
            if(pstmt != null) pstmt.close();
         } catch (SQLException e) {
         }
      }
      return aftcnt;
   }
   //삭제 
   public int deleteShop(String sid) {
      String sql = " delete from shop "
               + " where shopid = ? ";
      PreparedStatement pstmt = null;
      int aftcnt = 0;
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, sid);
         aftcnt = pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(pstmt != null) pstmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return aftcnt;
   }
      

}