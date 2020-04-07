package ua.khnu.reposetory;

import org.apache.log4j.Logger;
import ua.khnu.db.ConnectionHolder;
import ua.khnu.db.transaction.Transaction;
import ua.khnu.entuty.User;
import ua.khnu.exception.DBException;

import java.sql.*;

public class UserRepository implements Repository<User> {
    private static final Logger LOG = Logger.getLogger(UserRepository.class);
    private static final String INSET = "INSERT INTO users value (default,?,?,?,?,?,?)";

    @Override
    @Transaction
    public User add(User user) {
        try {
            Connection connection = ConnectionHolder.getConnection();
            System.out.println(connection);
            PreparedStatement ps = connection.prepareStatement(INSET, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            ps.setString(k++, user.getLogin());
            ps.setString(k++, user.getPassword());
            ps.setString(k++, user.getEmail());
            ps.setString(k++, user.getPaymentMethod());
            ps.setString(k++, user.getShippingAddress());
            ps.setString(k, user.getNumber());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException();
        }
        return user;
    }

    @Transaction
    @Override
    public User query(String query) {
        return null;
    }

    @Transaction
    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Transaction
    @Override
    public boolean update(User entity) {
        return false;
    }
}
