package book.fengkuang.unit13_jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.Test;

public class TestDatabaseMetaData {
	@Test
	public void t1() throws SQLException {
		Connection connection = JDBCUtils.getConnection();
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		System.out.println(databaseMetaData.getDriverName());
		System.out.println(databaseMetaData.supportsBatchUpdates());
	}
}
