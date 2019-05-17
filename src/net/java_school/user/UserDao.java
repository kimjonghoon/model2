package net.java_school.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.java_school.commons.WebContants;
import net.java_school.exception.SignUpFailException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserDao {
	private Log log = LogFactory.getLog(UserDao.class);
	private static UserDao instance = new UserDao();
	private DataSource ds;

	public static UserDao getInstance() {
		return instance;
	}

	private UserDao() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/jsppjt");
		} catch (NamingException e) {
			if (log.isDebugEnabled()) {
				log.debug(e.getMessage());
			}
		}
	}
	
	private Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insert(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member values (?, ?, ?, ?)";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPasswd());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getMobile());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
			throw new SignUpFailException("Sign UP Failed!");
		} finally {
			close(null, pstmt, con);
		}
	}

	public void insertAuthority(String email, String authority) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT into AUTHORITIES values (?, ?)";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, authority);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}
	}

	public User login(String email, String passwd) {
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT email, passwd, name, mobile FROM member "
				+ "WHERE email = ? AND passwd = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPasswd(rs.getString("passwd"));
				user.setName(rs.getString("name"));
				user.setMobile(rs.getString("mobile"));
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return user;
	}

	public List<String> selectRoles(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT authority FROM authorities WHERE email = ?";
		
		List<String> roles = new ArrayList<String>();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String role = rs.getString("authority");
				roles.add(role);
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return roles;
	}

	public void update(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET name = ?, mobile = ? WHERE email = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getMobile());
			pstmt.setString(3, user.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			} 
			throw new RuntimeException(e);
		} finally {
			close(null, pstmt, con);
		}

	}


	public void delete(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE member WHERE email = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
			throw new RuntimeException(e);
		} finally {
			close(null, pstmt, con);
		}

	}
	
	public void deleteAllAuthorities(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE authorities WHERE email = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}
	}

	public void deleteAuthority(String email, String authority) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE authorities WHERE email = ? AND authority = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, authority);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}
	}

	public User selectOne(String email) {
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT email, passwd, name, mobile "
				+ "FROM member WHERE email = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPasswd(rs.getString("passwd"));
				user.setName(rs.getString("name"));
				user.setMobile(rs.getString("mobile"));
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return user;

	}
	
	public int selectCountOfUsers(String search) {
		int totalUser = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT count(*) FROM member";
		if (search != null && !search.equals("")) {
			sql = sql + " WHERE email LIkE '%" + search + "%' OR name LIKE '%" + search + "%' OR mobile LIKE '%" + search + "%'";
		}
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totalUser = rs.getInt(1);
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}
		
		return totalUser;
	}
	
	public List<UserInfo> selectListOfUserInfos(String search, int startRecord, int endRecord) {
		
		List<User> users = new ArrayList<User>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT email, name, mobile FROM ");
		sb.append("( ");
		sb.append("     SELECT rownum R,A.* FROM ");
		sb.append("     ( ");
		sb.append("     SELECT ");
		sb.append("         email,"); 
		sb.append("         name,");
		sb.append("         mobile");
		sb.append("     FROM ");
		sb.append("         member ");
		if (search != null && !search.equals("")) {
			sb.append("  WHERE ");
			sb.append("  email LIKE '%" + search + "%' OR name LIKE '%" + search + "%' OR mobile LIKE '%" + search + "%' ");
		}
		sb.append("     ) A ");
		sb.append(") ");
		sb.append("WHERE R BETWEEN ? AND ?");

		String sql = sb.toString();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRecord);
			pstmt.setInt(2, endRecord);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				User user = new User();
				String email = rs.getString("email");
				String name = rs.getString("name");
				String mobile = rs.getString("mobile");
				user.setEmail(email);
				user.setName(name);
				user.setMobile(mobile);
				users.add(user);
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}
		
		return this.setAuthoritiesToUserList(users);
	}

	private List<UserInfo> setAuthoritiesToUserList(List<User> users) {
		
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		
		String sql = "SELECT authority FROM authorities WHERE email = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			
			for (User user : users) {
				pstmt.setString(1, user.getEmail());
				rs = pstmt.executeQuery();
				List<String> roles = new ArrayList<String>();

				while(rs.next()) {
					String authority = rs.getString("authority");
					roles.add(authority);
				}
				
				UserInfo userInfo = new UserInfo();
				userInfo.setUser(user);
				userInfo.setRoles(roles);
				userInfos.add(userInfo);
				
				if (rs != null) rs.close();
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}
		
		return userInfos;
	}

	public int updatePasswd(String email, String newPasswd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET passwd = ? WHERE email = ?";
		int ret = 0;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPasswd);
			pstmt.setString(2, email);
			ret = pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}

		return ret;
	}

	public int authentication(String email, String passwd) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM member WHERE email = ? AND passwd = ?";
		int ret = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			rs.next();
			ret = rs.getInt(1);
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + WebContants.LINE_SEPARATOR);
				msg.append("Message : " + e.getMessage() + WebContants.LINE_SEPARATOR);
				msg.append("Oracle Error Code : " + e.getErrorCode() + WebContants.LINE_SEPARATOR);
				msg.append("sql : " + sql + WebContants.LINE_SEPARATOR);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return ret;
	}

}