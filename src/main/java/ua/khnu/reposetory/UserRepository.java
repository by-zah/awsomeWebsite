package ua.khnu.reposetory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.khnu.entity.ShippingAddress;
import ua.khnu.entity.User;

import java.sql.PreparedStatement;

@Component
public class UserRepository implements Repository<User> {
    private static final Logger LOG = Logger.getLogger(UserRepository.class);

    private JdbcTemplate jdbcAccessor;
    private ApplicationContext ctx;
    private RowMapper<User> rowMapper;
    private static final String INSET = "INSERT INTO users value (default,?,?,?,?,?,?)";

    @Autowired
    public UserRepository(JdbcTemplate jdbcAccessor, ApplicationContext ctx) {
        this.ctx = ctx;
        this.jdbcAccessor = jdbcAccessor;
        initRowMapper();
    }

    @Override
    public User add(User user) {
        jdbcAccessor.update(INSET, user.getLogin(), user.getPassword(), user.getEmail(),
                user.getPaymentMethod(), user.getShippingAddress(), user.getNumber());
        return null;
    }

    private static final String JOIN_SHIPPING_ADDRESS = "JOIN shipping_adresses  on users.shippingAddressID = shipping_adresses.id ";

    @Override
    public User query(String query) {
        int whereIndex = query.lastIndexOf("where");
        query = query.substring(0, whereIndex - 1) + " " + JOIN_SHIPPING_ADDRESS + query.substring(whereIndex);
        LOG.debug("query --> " + query);
        return jdbcAccessor.queryForObject(query, rowMapper);
    }

    private static final String DELETE = "DELETE FROM users WHERE id=?";

    @Override
    public boolean delete(User user) {
        return jdbcAccessor.update(DELETE, user.getId()) > 0;
    }

    @Override
    public boolean update(User entity) {
        throw new UnsupportedOperationException();
    }

    private void initRowMapper() {
        rowMapper = (rs, rowNum) -> {
            User user = ctx.getBean(User.class);
            user.setId(rs.getInt("users.id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setPaymentMethod(rs.getString("paymentMethod"));
            user.setNumber(rs.getString("contactNumber"));
            ShippingAddress shippingAddress = user.getShippingAddress();
            shippingAddress.setId(rs.getInt("shipping_adresses.id"));
            shippingAddress.setCity(rs.getString("city"));
            shippingAddress.setRegion(rs.getString("region"));
            shippingAddress.setStreet(rs.getString("street"));
            shippingAddress.setBuilding(rs.getString("building"));
            shippingAddress.setIndex(rs.getString("index"));
            return user;
        };
    }
}
