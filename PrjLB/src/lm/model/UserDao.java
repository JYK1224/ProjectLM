package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import lm.view.UserList;
import oracle.jdbc.OracleConnection;

public class UserDao {
	
	UserList li ;
	
	private OracleConnection conn = null;

	// 생성자
	public UserDao() {
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
	public int insertUser(int userid, String userpw, String username, String ty,  String intro) {
		String sql = "";
		sql += " insert into user_mng " + "  ( userid , userpw, username, ty,  intro ) " + " values  "
				+ "  (  ? ,  ?,  ?,  ?,  ? ) ";
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			pstmt.setString(2, username);
			pstmt.setString(3, userpw);
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

	public int insertUser (Uservo ep) {

		int userid = ep.getUserid();
		String userpw = ep.getUserpw();
		String username = ep.getUsername();
		String ty = ep.getty();
		String intro = ep.getIntro();

		int aftcnt = insertUser(userid, username, userpw, ty, intro);
		return aftcnt;
	}
	
	//삭제
	public int deleteUser (String userid) {
		String sql = " delete from user_mng " + " where userid = ?  ";
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
	public int updateUser(Uservo ep) {
		String sql = " update user_mng " 
	               + " set  " 
	               + "       userpw   = ?, "
	               + "       username = ?, " 
				   + "       ty       = ?, " 
				   + "       intro    = ?  "
			       + " where userid   = ? ";
		PreparedStatement pstmt = null;
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ep.getUserpw() );
			pstmt.setString(2, ep.getUsername() );
			pstmt.setString(3, ep.getty() );
			pstmt.setString(4, ep.getIntro() );
			pstmt.setInt(5, ep.getUserid() );

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
	//회원 전체 리스트
	public Vector<Vector> getUserlist1() {
		Vector <Vector> list = new Vector<>();
		
		String sql = " select userid, username, ty, "
				   + " to_char(joindate, 'yyyy-mm-dd-hh24:mi') joindate"
				   + " from user_mng"
				   + " order by userid asc ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
			String ui = rs.getString("userid");
			String un = rs.getString("username");
			String ty = rs.getString("ty");
			String id= rs.getString("joindate");
			
			Vector v = new Vector();
			v.add(ui);
			v.add(un);
			v.add(ty);
			v.add(id);
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
	
	public Vector<Vector> getUserlist2() throws SQLException {
		UserList li = new UserList();
		Vector <Vector> list = new Vector<>();
		
		String sql = " select userid, username, ty, "
				   + " to_char(joindate, 'yyyy-mm-dd-hh24:mi') joindate"
				   + " from user_mng"
				   + " where username = ? " ;
		PreparedStatement pstmt = null;
		pstmt.setString(1,li.txtname.getText());
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
			String ui = rs.getString("userid");
			String un = rs.getString("username");
			String ty = rs.getString("ty");
			String id= rs.getString("joindate");
			
			Vector v = new Vector();
			v.add(ui);
			v.add(un);
			v.add(ty);
			v.add(id);
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

	

}