package lm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JRadioButton;

import lm.view.UserList;
import oracle.jdbc.OracleConnection;

public class UserDao {
	
	UserList li ;
	
	private OracleConnection conn = null;

	private static UserDao instance = new UserDao();
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
		sql += " insert into usermng " + "  ( userid , userpw, username, ty,  intro ) " + " values  "
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
		String sql = " delete from usermng " + " where userid = ?  ";
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
		String sql = " update usermng " 
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
				   + " from usermng"
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
	//개별조회
	public Vector<Vector> getUserlist2(String search) {
		
		Vector <Vector> list = new Vector<>();
		
		String sql = " select userid, username, ty, "
				   + " to_char(joindate, 'yyyy-mm-dd-hh24:mi') joindate"
				   + " from usermng"
				   + " where username like "
				   + "'%" +search.trim()+ "%'";
		
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			String ui = rs.getString("userid");
			String un = rs.getString("username");
			String ty = rs.getString("ty");
			String id = rs.getString("joindate");
			
			Vector v = new Vector<>();
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
	public Uservo getUser(String userid) {
		Uservo vo = null;
		
		String sql = " select userid, userpw, username, ty, intro, joindate"
				   + " from usermng "
				   + " where userid = ? ";
		PreparedStatement pstmt = null;
		ResultSet            rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String userID = rs.getString("userid");
				String userpw = rs.getString("userpw");
				String username = rs.getString("username");
				String ty = rs.getString("ty");
				String intro = rs.getString("intro");
				String joindate = rs.getString("joindate");
				
				vo = new Uservo(
						Integer.parseInt(userid), userpw, username, ty, intro, joindate);
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
		return vo;
	}
	//로그인
	public int findIdAndPw(String userid, String userpw) {
		
		String sql = " select *  "
			 	   + " from usermng"
			 	   + " where userid = ?"
			 	   + " and userpw = ? ";
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpw);
			rs = pstmt.executeQuery();
			if (rs.next()) {;
					return 1;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	
	}
	public static UserDao getInstance() {
		return instance;
	}

	public int findTy(String userid) {
		String sql = " select ty"
				   + " from usermng "
				   + " where userid =" + userid;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals("관리자")) {
					return 1;
				}
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
		return -1;
	}
	public int existsfind(String userid) {
		
		String sql = "SELECT "
				+ "   CASE WHEN EXISTS(SELECT * FROM USERMNG where USERID = "+ userid +") "
				+ "     THEN 1 "
				+ "     ELSE 0 "
				+ "   END as d "
				+ "FROM dual ";
			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int aftcnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if ( rs.next()) {
				aftcnt = rs.getInt("d");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) pstmt.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
		}
		return aftcnt;
	}
}