package br.fai.bloco7.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String url = "jdbc:postgresql://localhost:5432/bloco7";
	private static final String username = "postgres";
<<<<<<< HEAD

	private static final String password = "235";
=======
	private static final String password = "matheuszz2";
>>>>>>> 419a44f1c367c1d56b33b64d33e4d454babf3a24

	private static Connection connection = null;

	public static Connection getConnection() {

		try {
			connection = DriverManager.getConnection(url, username, password);

		} catch (final SQLException e) {

			System.out.println(e.getMessage());
		}
		return connection;
	}

	private static void closeConnection() {
		try {
			connection.close();

		} catch (final SQLException e) {

			System.out.println(e.getMessage());
		}
	}

	private static void closeResultSet(final ResultSet resultSet) {

		try {
			resultSet.close();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void closePreparedStatement(final PreparedStatement preparedStatement) {

		try {
			preparedStatement.close();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(final ResultSet resultSet, final PreparedStatement preparedStament,
			final Connection connection) {
		closeConnection();
		closePreparedStatement(preparedStament);
		closeResultSet(resultSet);
	}

	public static void close(final PreparedStatement preparedStament, final Connection connection) {
		closeConnection();
		closePreparedStatement(preparedStament);
	}

}
