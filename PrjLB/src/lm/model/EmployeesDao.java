package lm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class EmployeesDao {

	private Connection conn = null;

	// 생성자
	public EmployeesDao() {
		conn = DBConn.getInstance();
	}

	public void close() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원 추가
	public int insertUSER_1(String userid, String userpw, String username, String ty,  String intro) {
		String sql = "";
		sql += " insert into employees " + "  ( userid , userpw, username, ty,  intro ) " + " values  "
				+ "  (  ? ,  ?,  ?,  ?,  ?,  ? ) ";
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpw);
			pstmt.setString(3, username);
			pstmt.setString(4, ty);
			pstmt.setString(5, intro);

			aftcnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}

		}
		
		return aftcnt;
	}

	public int insertEmp (USER_1 ep) {

		String userid = ep.getUserid();
		String userpw = ep.getUserpw();
		String username = ep.getUsername();
		String ty = ep.getty();
		String intro = ep.getIntro();

		int aftcnt = insertUSER_1(userid, userpw, username, ty, intro);
		return aftcnt;
	}
	
	//삭제
	public int deleteEmp (String userid) {
		String sql = " delete from user_1 " + " where userid = ?  ";
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			aftcnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
		}
		return aftcnt;
	}
	
	//수정
	public int updateEmp1(USER_1 ep) {
		String sql = " update member " 
	               + " set  " 
				   + "       username = ?, " 
	               + "       userpw   = ?, "
				   + "       ty     = ?, " 
				   + "       intro    = ?  "
			       + " where userid   = ? ";
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ep.getUsername() );
			pstmt.setString(2, ep.getUserpw() );
			pstmt.setString(3, ep.getty() );
			pstmt.setString(4, ep.getIntro() );
			pstmt.setString(5, ep.getUserid() );

			aftcnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
		}
		return aftcnt;
	}
	
	
	//조회
	public USER_1 getEmp(String userid) {
		
		USER_1 ep = null;
		
		String sql = " select userid, userpw, username, ty, intro, indate "
				   + " from member "
				   + " where userid = ? ";
		PreparedStatement pstmt = null;
		ResultSet            rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String userID   = rs.getString("userid");
				String userpw   = rs.getString("userpw");
				String username = rs.getString("username");
				String ty      = rs.getString("ty");
				String intro    = rs.getString("intro");
				String indate   = rs.getString("indate");
				
				ep = new USER_1(
						userID, userpw, username, ty, intro, indate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs  != null) rs.close();
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
			}
		}
		return ep;
}

	

}