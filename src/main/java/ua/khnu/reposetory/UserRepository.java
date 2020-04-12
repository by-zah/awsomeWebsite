package ua.khnu.reposetory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ua.khnu.entity.User;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository implements Repository<User> {
    private static final Logger LOG = Logger.getLogger(UserRepository.class);
    private static final String DELETE = "DELETE FROM users WHERE id=?";
    private static final String UPDATE = "UPDATE users " +
            "SET login=?,password=?,email=?,paymentMethod=?,contactNumber=?" +
            " WHERE id=?";
    private String[] columnNames;
    private JdbcTemplate jdbcAccessor;
    private ApplicationContext ctx;
    private RowMapper<User> rowMapper;
    private SimpleJdbcInsert simpleJdbcInsert;


    @Autowired
    public UserRepository(JdbcTemplate jdbcAccessor, DataSource ds, ApplicationContext ctx) {
        this.ctx = ctx;
        this.jdbcAccessor = jdbcAccessor;
        initColumnNames();
        initRowMapper();
        simpleJdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("users").usingGeneratedKeyColumns("id")
                .usingColumns(columnNames);
    }

    @Override
    public User add(User user) {
        int id = simpleJdbcInsert.executeAndReturnKey(extractParameters(user)).intValue();
        user.setId(id);
        return user;
    }

    @Override
    public User query(String query) {
        LOG.debug("query --> " + query);
        return jdbcAccessor.queryForObject(query, rowMapper);
    }

    @Override
    public boolean delete(User user) {
        return jdbcAccessor.update(DELETE, user.getId()) > 0;
    }

    @Override
    public boolean update(User user) {
        Object[] values = extractValues(user);
        Object[] newValues = Arrays.copyOf(values, values.length + 1);
        newValues[newValues.length - 1] = user.getId();
        LOG.debug("values --> " + Arrays.toString(newValues));
        return jdbcAccessor.update(UPDATE, newValues) > 0;
    }

    private void initRowMapper() {
        rowMapper = (rs, rowNum) -> {
            User user = ctx.getBean(User.class);
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setPaymentMethod(rs.getString("paymentMethod"));
            user.setNumber(rs.getString("contactNumber"));
            return user;
        };
    }

    private Map<String, Object> extractParameters(User user) {
        Map<String, Object> params = new HashMap<>(5);
        Object[] values = extractValues(user);
        for (int i = 0; i < 5; i++) {
            params.put(columnNames[i], values[i]);
        }
        return params;
    }

    private Object[] extractValues(User user) {
        Object[] values = new Object[5];
        values[0] = user.getLogin();
        values[1] = user.getPassword();
        values[2] = user.getEmail();
        values[3] = user.getPaymentMethod();
        values[4] = user.getNumber();
        return values;
    }

    private void initColumnNames() {
        this.columnNames = new String[5];
        columnNames[0] = "login";
        columnNames[1] = "password";
        columnNames[2] = "email";
        columnNames[3] = "paymentMethod";
        columnNames[4] = "contactNumber";
    }
}
