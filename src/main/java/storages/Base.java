package storages;

import events.EventList;
import events.Eventable;
import events.EventsFactory;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Base implements Savable {
    private static final Logger log = Logger.getLogger(Base.class);

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "SYSTEM";
    private static final String DB_PASSWORD = "6303367sql";
    private static Connection dbConnection;

    private static HashMap<String, Eventable> eventsMap = loadEvents();

    public void save(Eventable event) {
        PreparedStatement statement = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("INSERT INTO \"SYSTEM\".\"EVENTS\" (ID, START_DATE_TIME, DESCRIPTION, END_DATE_TIME, STATE, EVENT_TYPE, REPEAT) VALUES(?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'), ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'), ?, ?, ?)");
            statement.setString(1, event.getId());
            String dateStartString = event.getStartDate() + " " + event.getStartTime().toString().substring(0, 8);
            statement.setString(2, dateStartString);
            statement.setString(3, event.getDescription());
            String dateEndString = event.getEndDate() + " " + event.getEndTime().toString().substring(0, 8);
            statement.setString(4, dateEndString);
            statement.setString(5, "work");
            statement.setString(6, String.valueOf(event.getEventType()));
            statement.setBoolean(7, event.getRepeat());

            statement.execute();
        } catch (SQLException e) {
            log.error(e);
            System.out.println(e.getMessage());
        } finally {
            close(statement, dbConnection);
        }

    }

    public static String getState(Eventable eventable) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        String state = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("SELECT STATE FROM EVENTS WHERE ID = ?");
            statement.setString(1, eventable.getId());

            rs = statement.executeQuery();
            rs.next();
            state = rs.getString("STATE");

        } catch (SQLException e) {
            log.error(e);
            System.out.println(e.getMessage());
        } finally {
            close(statement, dbConnection);
        }
        return state;
    }

    public HashMap<String, String> watchAll() {
        PreparedStatement statement = null;
        ResultSet rs = null;
        HashMap<String, String> map = new HashMap<>();
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("SELECT * FROM EVENTS");

            rs = statement.executeQuery();

            while (rs.next()) {
                map.put(rs.getString("ID"),"Start time : " +  rs.getString("START_DATE_TIME") +" End time : " + rs.getString("END_DATE_TIME") + " " + rs.getString("DESCRIPTION") + " State: " + rs.getString("STATE"));
            }
        } catch (SQLException e) {
            log.error(e);
            System.out.println("Error in watch all");
            System.out.println(e.getMessage());
        } finally {
            close(statement, dbConnection);
        }
        return map;
    }

    @Override
    public void toArchive(Eventable event) {
        PreparedStatement statement = null;
            dbConnection = getDBConnection();
        try {
            statement = dbConnection.prepareStatement("UPDATE EVENTS SET STATE = 'archive' WHERE ID = ?");
            statement.setString(1, event.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e);
            e.printStackTrace();
        }

    }


    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            log.error(e);
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            log.error(e);
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }


    private static void close(Statement statement, Connection dbConnection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error(e);
                e.printStackTrace();
            }
        }
        if (dbConnection != null) {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                log.error(e);
                e.printStackTrace();
            }
        }
    }

    public static Map<String, Eventable> getEventsMap() {
        return eventsMap;
    }

    private static HashMap<String, Eventable> loadEvents() {
        PreparedStatement statement = null;
        ResultSet rs = null;
        HashMap<String, Eventable> map = new HashMap<>();
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("SELECT * FROM EVENTS");

            rs = statement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("ID");
                LocalDate startDate = LocalDate.fromDateFields(rs.getDate("START_DATE_TIME"));
                LocalDate endDate = LocalDate.fromDateFields(rs.getDate("END_DATE_TIME"));

                LocalTime startTime = LocalTime.fromDateFields(rs.getTime("START_DATE_TIME"));
                LocalTime endTime = LocalTime.fromDateFields(rs.getTime("END_DATE_TIME"));

                EventList eventType = EventList.valueOf(rs.getString("EVENT_TYPE"));

                String descr = rs.getString("DESCRIPTION");

                boolean repeat = rs.getBoolean("REPEAT");

                Eventable event = EventsFactory.getEvent(id, eventType, startDate, startTime, endDate, endTime, descr, repeat);
                map.put(id, event);
            }
        } catch (SQLException e) {
            log.error(e);
            System.out.println(e.getMessage());
        } finally {
            close(statement, dbConnection);
        }
        return map;

    }
}
