package ua.khnu.db;

import java.sql.Connection;

public class ConnectionHolder {
    private static final ThreadLocal<Connection> connection = new ThreadLocal<>();

    private ConnectionHolder() {
        throw new UnsupportedOperationException();
    }

    public static Connection getConnection() {
        return connection.get();
    }

    public static void setConnection(Connection connectionVal) {
        connection.set(connectionVal);
    }

    public static void removeConnection() {
        connection.remove();
    }
}
