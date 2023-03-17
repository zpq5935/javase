package book.school.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

class JDBCTools {// JDBC工具类 用来建立连接和释放连接
	public static Connection getConnection() throws Exception {// 连接数据库
		String driverClass = null;
		String url = null;
		String user = null;
		String password = null;

		Properties properties = new Properties();
		//1、根据加载器读取类路径下的配置文件
		//InputStream in = Review.class.getClassLoader().getResourceAsStream("/javase_basis/resource/jdbc.properties");
		//2、输入流读取任意路径的
		InputStream in = new FileInputStream("resource/jdbc.properties");
		properties.load(in);

		driverClass = properties.getProperty("driver");
		url = properties.getProperty("jdbcurl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		Class.forName(driverClass);
		return DriverManager.getConnection(url, user, password);
	}

	public static void release(Connection con, Statement state) {// 关闭数据库连接
		if (state != null) {
			try {
				state.close();
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

	public static void release(ResultSet rs, Connection con, Statement state) {// 关闭数据库连接
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (state != null) {
			try {
				state.close();
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
}

public class Review {
	public Connection getConnection() throws Exception {// 连接数据库
		String driverClass = null;
		String url = null;
		String user = null;
		String password = null;

		Properties properties = new Properties();

		InputStream in = Review.class.getClassLoader().getResourceAsStream("jdbc.properties");

		properties.load(in);

		driverClass = properties.getProperty("driver");
		url = properties.getProperty("jdbcurl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		Class.forName(driverClass);
		return DriverManager.getConnection(url, user, password);
	}

}
