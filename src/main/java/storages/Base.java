package storages;

import controls.Event;
import events.Eventable;

import java.sql.*;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Base implements Savable {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DB_USER = "SYSTEM";
	private static final String DB_PASSWORD = "6303367sql";
	private Connection dbConnection;
	private String tableName = "EVENTS";
	private static int id = 0;

	public void save(Eventable event) {
		String insertTableSQL = "INSERT INTO \"" + DB_USER + "\".\"" + tableName +
				"\" VALUES" + "('" + id++ + "', TO_DATE('" + event.getDate() + " " + event.getTime().toString().substring(0, 8) + "', 'YYYY-MM-DD HH24:MI:SS'), '" + event.getDescription() + "')";
		Statement statement = null;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			statement.execute(insertTableSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(statement, dbConnection);
		}

	}

	public void watchAll() {
		String selectTableSQL = "SELECT * FROM " + tableName;
		Statement statement = null;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {
				String date = rs.getString("DATE_TIME");
				String desc = rs.getString("DESCRIPTION");
				System.out.println(date + " " + desc);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(statement, dbConnection);
		}
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
