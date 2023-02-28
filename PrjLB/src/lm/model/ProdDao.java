package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import oracle.jdbc.OracleConnection;

public class ProdDao {
	
	private OracleConnection conn = null;
	
	public ProdDao () {
		conn = DBConn.getInstance();
	}
	//상품 추가
	public int insertProd(Prodvo pv ) {
		String pid = pv.getPid();
		String pname = pv.getPname();
		int iprice = pv.getIprice();
		int sprice = pv.getSprice();
		String aname = pv.getAname();
		String dname = pv.getDname();

		
		int aftcnt = insertProd(pid, pname, iprice, sprice, aname, dname);
		
		return aftcnt ;
	}
	// 상품 추가
	private int insertProd(String pid, String pname, int iprice, int sprice, String aname, String dname) {
		String sql = " insert into product "
				   + " ( pid, pname, iprice, sprice, aid, did ) "
				   + " values "
				   + " ( ?, ?, ?, ?, "
				   + " (select aid\r\n "
				   + " from ASSORTMENT\r\n "
				   + " where aname = ?) , "
				   + " (Select did "
				   + " from dept_acc "
				   + " where dname = ?) ) ";
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pid);
			pstmt.setString(2,  pname);
			pstmt.setInt(3, iprice);
			pstmt.setInt(4, sprice);
			pstmt.setString(5,  aname );
			pstmt.setString(6,  dname );
			
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
	public ArrayList<String> getDept() {
		ArrayList<String> list = new ArrayList<String>();
		
		String sql = " select dname "
				   + " from DEPT_ACC ";
		
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			
			while(rs.next()) {
			
			String dname = rs.getString("dname");
			
			list.add(dname);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "저장에 실패하였습니다");
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
			}
		}
		return list;
	}
	//수정
	public int updateProd(Prodvo pv) {
		String sql = " update product"
				   + " set "
				   + "       pname  = ?, "
				   + "       iprice = ?, "
				   + "       sprice = ?, "
				   + "       aid = (select aid from assortment where aname = ?) , "
				   + "       did = (select did from dept_acc where dname = ? )  "
				   + " where pid = ? ";
		
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, pv.getPname());
			 pstmt.setInt(2, pv.getIprice());
			 pstmt.setInt(3, pv.getSprice());
			 pstmt.setString(4, pv.getAname());
			 pstmt.setString(5, pv.getDname());
			 pstmt.setString(6, pv.getPid());
			 
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
	//삭제
	public int deletProd(String pid) {
		String sql = " delete from product where pid = ? ";
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pid);
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
	public int insertStock(String pid, String dname) {
		String sql = " INSERT INTO STOCK "
				+ " (STOCKID, STOCKNUM, PID, DID) "
				+ " VALUES ((SELECT MAX(STOCKID) +1 FROM STOCK),?,?,(Select did from dept_acc where dname = ?))";
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, pid);
			pstmt.setString(3, dname);
			aftcnt = pstmt.executeUpdate();			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {			
			try {
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
				
		return aftcnt;
	}
}

	
