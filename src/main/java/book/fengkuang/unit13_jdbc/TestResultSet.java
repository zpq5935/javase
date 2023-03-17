package book.fengkuang.unit13_jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TestResultSet {
	@Test
	public void testResultSet01() throws SQLException {
		String sql = "select * from customer";
		ResultSet resultSet = JDBCUtils.executeQuery(sql);
		int i = 0;
		while (resultSet.next()) {
			System.out.println(i + ":" + resultSet.getString(1));
			i++;
		}
	}
}
