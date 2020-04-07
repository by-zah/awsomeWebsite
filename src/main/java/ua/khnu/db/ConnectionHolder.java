package ua.khnu.db;

import java.sql.Connection;

public class ConnectionHolder {
    private static final ThreadLocal<Connection> connection = new ThreadLocal<>();

    private ConnectionHolder() {
        throw new UnsupportedOperationException();
    }

    public static ThreadLocal<Connection> getConnection() {
        return connection;
    }

    public static void setConnection(Connection connectionVal) {
        connection.set(connectionVal);
    }

    public static void removeConnection() {
        connection.remove();
    }
}
