package home.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestPerThreadSingleton {

}

class ConnectionDispenser {

	private static class ThreadLocalConnection extends ThreadLocal<Connection> {
		@Override
		public Connection initialValue() {
			Connection connection = null;
			try {
				connection = DriverManager.getConnection("");
			} catch (SQLException exception) {
			}
			return connection;
		}
	}

	private static ThreadLocalConnection tlConnection = new ThreadLocalConnection();

	public static Connection getConnection() {
		return tlConnection.get();
	}
}
