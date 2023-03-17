package book.fengkuang.unit13_jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import org.junit.Test;

public class TestRowSet {
	/********************************************************************/
	/*
	 * 测试直接使用 JdbcRowSetImpl
	 */
	/********************************************************************/
	@Test
	public void testJdbcRowSetImpl() {
		Connection connection = JDBCUtils.getConnection();
		// JdbcRowSet jdbcRowSet = new JdbcRowSetImpl();
	}

	/********************************************************************/
	/*
	 * RowSetProvider、RowSetFactory
	 */
	/**
	 * @throws SQLException
	 ******************************************************************/
	@Test
	public void testRowSetFactory() throws SQLException {
		String sql = "select * from customer";
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet cachedRowSet = factory.createCachedRowSet();
		cachedRowSet.setUrl(JDBCUtils.getUrl());
		cachedRowSet.setUsername(JDBCUtils.getUser());
		cachedRowSet.setPassword(JDBCUtils.getPassword());
		cachedRowSet.setCommand(sql);
		cachedRowSet.execute();
		//
		int i = 0;
		while (cachedRowSet.next()) {
			System.out.println(i + ":" + cachedRowSet.getString(1));
			i++;
		}
	}

	/**
	 * 
	 * @Description 测试离线RowSet,即使关闭Connection、ResultSet，CachedRowSet依然可以获取数据
	 * @throws SQLException
	 * @author zhangchaopei
	 * @date 2020年6月15日
	 */
	@Test
	public void testOffLineRowSet() throws SQLException {
		String sql = "select id,account from customer order by id desc";
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet cachedRowSet = factory.createCachedRowSet();
		ResultSet resultSet = JDBCUtils.executeQuery(sql);
		cachedRowSet.populate(resultSet);
		JDBCUtils.close();// 关闭Connection、ResultSet
		//
		int i = 0;
		while (cachedRowSet.next()) {
			System.out.println(i + ":" + cachedRowSet.getString(1)+cachedRowSet.getString(2));
			if(i==2){
				cachedRowSet.updateString(2, "改动");
				cachedRowSet.updateRow();
			}
			i++;
		}
		// 再次获取连接，将改动同步到数据库
		Connection newConn = JDBCUtils.getConnection();
		newConn.setAutoCommit(false);
		cachedRowSet.acceptChanges(newConn);
	}
	
	/**
	 * 
	 * @Description 测试分页RowSet
	 * @throws SQLException
	 * @author zhangchaopei
	 * @date 2020年6月15日
	 */
	@Test
	public void testPageRowSet() throws SQLException {
		String sql = "select * from customer";
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet cachedRowSet = factory.createCachedRowSet();
		ResultSet resultSet = JDBCUtils.executeQuery(sql);
		cachedRowSet.populate(resultSet,10);
		JDBCUtils.close();// 关闭Connection、ResultSet
		//
		int i = 0;
		while (cachedRowSet.next()) {
			System.out.println(i + ":" + cachedRowSet.getString(1));
			i++;
		}
	}
}
