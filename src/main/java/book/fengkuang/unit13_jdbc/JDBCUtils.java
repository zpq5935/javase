package book.fengkuang.unit13_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
	/**
	 * 声明连接数据库的信息，如数据库URL、用户名及密码
	 */
	private static final String URL = "jdbc:mysql://localhost:3306/cusservice";
	private static final String USER = "root";
	private static final String PASSWORD = "12345678910";
	/**
	 * 声明JDBC相关对象
	 */
	protected static Statement s = null;
	protected static ResultSet rs = null;
	protected static Connection conn = null;

	/**
	 * 创建数据库连接
	 * 
	 * @return conn
	 */
	public static synchronized Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 执行INSERT/UPDATE/DELETE SQL语句
	 * 
	 * @param sql
	 *            SQL语句，字符串类型
	 * @return 执行结果，int类型
	 */
	public static int executeUpdate(String sql) {
		int result = 0;
		try {
			s = getConnection().createStatement();
			result = s.executeUpdate(sql);
		} catch (SQLException e) {
			result = -1;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 执行SELECT SQL语句
	 * 
	 * @param sql
	 *            SQL语句，字符串类型
	 * @return ResultSet结果集
	 */
	public static ResultSet executeQuery(String sql) {
		try {
			s = getConnection().createStatement();
			rs = s.executeQuery(sql);
		} catch (SQLException e) {
			rs = null;
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 执行动态SQL语句
	 * 
	 * @param sql
	 *            含有参数的动态SQL语句。
	 * @return 返回PreparedStatement对象
	 */
	public static PreparedStatement executePreparedStatement(String sql) {
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}

	/**
	 * 事务回滚
	 */
	public static void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 关闭数据库连接对象
	 */
	public static void close() {
		try {
			if (rs != null)
				rs.close();
			if (s != null)
				s.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		String sqlString = "select * from user";
		JDBCUtils.executeQuery(sqlString);
		if (rs != null) {
			while (rs.next()) {
				String colString1 = rs.getString(1);
				String colString2 = rs.getString(2);
				String colString3 = rs.getString(3);
				byte b = rs.getByte(4);
				System.out.println(colString1 + " " + colString2 + " " + colString3 + " " + b);
			}
		}

	}

	public static void main2(String[] args) {

		// 编写SQL语句
		String sql = "INSERT INTO tb_users(fd_username,fd_password,fd_usertype,fd_gender,fd_email,"
				+ "fd_birthdate, fd_introduction,fd_hobby) VALUES (?,?,?,?,?,?,?,?)";

		// 执行SQL
		PreparedStatement ps = JDBCUtils.executePreparedStatement(sql);
		try {
			ps.setString(1, "username");
			ps.setString(2, "password");
			ps.setString(3, "1");
			ps.setString(4, "男");
			ps.setString(5, "email");
			ps.setString(6, "birthdate");
			ps.setString(7, "introduction");
			ps.setString(8, "hobby");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int result = 0;
		try {
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getUrl() {
		return URL;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPassword() {
		return PASSWORD;
	}

}
