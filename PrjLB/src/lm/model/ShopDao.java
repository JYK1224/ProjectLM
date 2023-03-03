package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import lm.view.JDialog;
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
//전체 리스트
	public Vector<Vector> getshoplist1() {
		Vector<Vector> list = new Vector<>();
		String sql = " select shopid, shopname,sincharge, sphone"
				   + " from shop"
				   + " order by shopid asc ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String si = rs.getString("shopid");
				String sn = rs.getString("shopname");
				String sc = rs.getString("sincharge");
				String sp = rs.getString("sphone");
				
				Vector v = new Vector();
				v.add(si);
				v.add(sn);
				v.add(sc);
				v.add(sp);
				list.add(v);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
		}
		return list;
	}
	//개별 조회
	public Vector<Vector> getShoplist2(String search) {
		Vector<Vector> list = new Vector<>();
		String sql = " select shopid, shopname, sincharge, sphone "
				   + " from shop "
				   + " where shopname like "
				   + " UPPER('%" + search.trim() + "%') ";
				   
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String si = rs.getString("shopid");
				String sn = rs.getString("shopname");
				String sc = rs.getString("sincharge");
				String sp = rs.getString("sphone");
				
				Vector v = new Vector();
				v.add(si);
				v.add(sn);
				v.add(sc);
				v.add(sp);
				list.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
			
		}
		
		return list;
	}
	//조회
	public Shopvo getSid(String sid) {
		
		Shopvo sv = null;
		String sql = " select shopid, shopname, sincharge, sphone "
				   + " from shop "
				   + " where shopid = ? " ;
		
		PreparedStatement pstmt = null;
		ResultSet            rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String si = rs.getString("shopid");
				String sn = rs.getString("shopname");
				String sc = rs.getString("sincharge");
				String sp = rs.getString("sphone");
				
				sv = new Shopvo (
						Integer.parseInt(si), sn, sc, sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
		}
		return sv;
	}
	public int existsfind(String sid) {
		String sql = " select "
				   + " case when exists "
				   + "  (select * from shop where shopid = " + sid + ")"
				   + "		then 1 "
				   + "      else 0 "
				   + "   end as d "
				   + " from dual " ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) 
				aftcnt = rs.getInt("d");
		} catch (SQLException e) {
			JDialog jd = new JDialog(0);
			jd.getDlbl().setText("숫자만 입력 가능합니다");
			jd.setTitle("경고");
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
		}
		return aftcnt;
	}  

}