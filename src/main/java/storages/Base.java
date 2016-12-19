package storages;

import events.Eventable;

import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Base implements Savable {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DB_USER = "SYSTEM";
	private static final String DB_PASSWORD = "6303367sql";
	private Connection dbConnection;
	private String tableName = "'EVENTS'";

	public void save(Eventable event) {
		String insertTableSQL = "INSERT INTO \"" + DB_USER + "\".\"" + tableName +
				"\" VALUES" + "('" + UUID.randomUUID() + "', TO_DATE('" + event.getDate() + " " + event.getTime().toString().substring(0, 8) + "', 'YYYY-MM-DD HH24:MI:SS'), '" + event.getDescription() + "')";
		PreparedStatement statement = null;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.prepareStatement("INSERT INTO \"SYSTEM\".\"EVENTS\" (ID, DATE_TIME, DESCRIPTION) VALUES(?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'), ?)");
			statement.setString(1, String.valueOf(UUID.randomUUID()));
			String dateString = event.getDate() + " " + event.getTime().toString().substring(0, 8);
			statement.setString(2, dateString);
			statement.setString(3, event.getDescription());

			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(statement, dbConnection);
		}

	}

	public HashMap<String, String> watchAll() {
		PreparedStatement statement = null;
		ResultSet rs = null;
		HashMap<String, String> map= new HashMap<>();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.prepareStatement("SELECT * FROM EVENTS");

			rs = statement.executeQuery();

			while (rs.next()) {
				map.put(rs.getString("ID"),rs.getString("DATE_TIME") + " " + rs.getString("DESCRIPTION"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(statement, dbConnection);
		}
		return map;
	}


	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}


	private static void close(Statement statement, Connection dbConnection){
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (dbConnection != null) {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
