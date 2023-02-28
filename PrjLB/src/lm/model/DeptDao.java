package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import oracle.jdbc.OracleConnection;

public class DeptDao {

	private OracleConnection conn = null;
	
	public DeptDao () {
		conn = DBConn.getInstance();
	}

	public int insertDept(Deptvo dv) {
		
		int did = dv.getDeptid();
		String dname = dv.getDeptdname();
		String name = dv.getDeptname();
		String dphone = dv.getDeptphone();
		
		int aftcnt = insertDept(did, dname, name, dphone);
		
		return aftcnt;
	}

	private int insertDept(int did, String dname, String name, String dphone) {
		String sql = " insert into dept_acc " 
				   + " ( did, dname, dincharge, dphone ) "
				   + " values "
				   + " ( ?, ?, ?, ? )";
		
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, did);
			pstmt.setString(2, dname);
			pstmt.setString(3, name);
			pstmt.setString(4, dphone);
			
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

	public int updataDept(Deptvo dv) {
		String sql = " update dept_acc "
				   + " set "
				   + "     dname     = ?, "
				   + "     dincharge = ?, "
				   + "     dphone    = ? "
				   + " where did     = ? " ;
		
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dv.getDeptdname());
			pstmt.setString(2, dv.getDeptname());
			pstmt.setString(3, dv.getDeptphone());
			pstmt.setInt(4, dv.getDeptid());
			
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
	public int deleteDept(String did) {
		String sql = " delete from dept_acc "
				   + " where did = ? ";
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, did);
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

	public Vector<Vector> getDeptlist1() {
		Vector<Vector> list  = new Vector<>();
		String sql = " select did, dname, dincharge, dphone"
				   + " from dept_acc "
				   + " order by did asc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			String di = rs.getString("did");
			String dn = rs.getString("dname");
			String na = rs.getString("dincharge");
			String dp = rs.getString("dphone");
				
			Vector v = new Vector();
			v.add(di);
			v.add(dn);
			v.add(na);
			v.add(dp);
			list.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
			}
		}
		
		return list ;
	}
	//개별조회
	public Vector<Vector> getDeptlist2(String search) {
		
		Vector<Vector> list = new Vector<>();
		String sql = " select did, dname, dincharge, dphone "
				   + " from dept_acc "
				   + " where dname like "
				   + " UPPER('%" + search.trim() + "%') ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			String di = rs.getString("did");
			String dn = rs.getString("dname");
			String na = rs.getString("dincharge");
			String dp = rs.getString("dphone");
				
			Vector v = new Vector();
			v.add(di);
			v.add(dn);
			v.add(na);
			v.add(dp);
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
	//조회버튼 기능
	public Deptvo getDid(String did) {
		
		Deptvo dv = null;
		String sql = " select did, dname, dincharge, dphone "
				   + " from dept_acc "
				   + " where did = ? ";
		
		PreparedStatement pstmt = null;
		ResultSet            rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, did);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String di  = rs.getString("did");
				String dn  = rs.getString("dname");
				String din = rs.getString("dincharge");
				String dp  = rs.getString("dphone");
				
				dv = new Deptvo (
						Integer.parseInt(di), dn, din, dp );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
			}
			
		}
		
		return dv;
	}
	public int existsfind(String did) {
		String sql = " select "
				   + " case when exists "
				   + " (select * from dept_acc where did = " +did+")"
				   + "    then 1 "
				   + "    else 0 "
				   + "   end as d "
				   + " from dual ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) 
				aftcnt = rs.getInt("d");
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
}
