package ua.khnu.db.transaction;

import org.apache.log4j.Logger;
import ua.khnu.db.ConnectionHolder;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class TransactionHandler implements InvocationHandler {
    private static final Logger LOG = Logger.getLogger(TransactionHandler.class);
    private static DataSource ds;

    public static void setDs(DataSource ds) {
        if (TransactionHandler.ds == null) {
            TransactionHandler.ds = ds;
        }
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean hasAnnotation = Arrays.stream(method.getDeclaredAnnotations())
                .anyMatch(a -> a.annotationType().equals(Transaction.class));
        if (!hasAnnotation) {
            return method.invoke(proxy, args);
        }
        Object res = null;
        Connection connection = null;
        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            ConnectionHolder.setConnection(connection);
            res = method.invoke(proxy, args);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(e);
        } finally {
            close(connection);
        }
        return res;
    }


    private void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                ConnectionHolder.removeConnection();
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    private void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
    }
}
