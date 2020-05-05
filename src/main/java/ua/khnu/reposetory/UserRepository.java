package ua.khnu.reposetory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ua.khnu.entity.User;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserRepository {
    private static final Logger LOG = Logger.getLogger(UserRepository.class);
    private static final String DELETE = "DELETE FROM users WHERE id=?";
    private static final String UPDATE = "UPDATE users " +
            "SET email=?,password=?,contactNumber=?,mailingEnabled=?" +
            " WHERE id=?";
    public static final String ID = "id";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String CONTACT_NUMBER = "contactNumber";
    public static final String MAILING_ENABLED = "mailingEnabled";
    private String[] columnNames;
    private JdbcTemplate jdbcAccessor;
    private SimpleJdbcInsert simpleJdbcInsert;


    @Autowired
    public UserRepository(JdbcTemplate jdbcAccessor, DataSource ds) {
        this.jdbcAccessor = jdbcAccessor;
        initColumnNames();
        simpleJdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("users").usingGeneratedKeyColumns(ID)
                .usingColumns(columnNames);
    }

    public User add(User user) {
        int id = simpleJdbcInsert.executeAndReturnKey(extractParameters(user)).intValue();
        user.setId(id);
        return user;
    }

    public List<User> query(String query) {
        return query(query, (Object) null);
    }

    public List<User> query(String query, Object... args) {
        LOG.debug("query --> " + query);
        LOG.debug("args --> " + Arrays.toString(args));
        return getUserListFromResultList(jdbcAccessor.queryForList(query, args));
    }

    public boolean delete(int id) {
        return jdbcAccessor.update(DELETE, id) > 0;
    }

    public boolean update(User user) {
        Object[] values = extractValues(user);
        Object[] newValues = Arrays.copyOf(values, values.length + 1);
        newValues[newValues.length - 1] = user.getId();
        LOG.debug("values --> " + Arrays.toString(newValues));
        return jdbcAccessor.update(UPDATE, newValues) > 0;
    }

    private Map<String, Object> extractParameters(User user) {
        Map<String, Object> params = new HashMap<>(columnNames.length-1);
        Object[] values = extractValues(user);
        for (int i = 0; i < columnNames.length; i++) {
            params.put(columnNames[i], values[i]);
        }
        return params;
    }

    private Object[] extractValues(User user) {
        Object[] values = new Object[4];
        values[0] = user.getEmail();
        values[1] = user.getPassword();
        values[2] = user.getNumber();
        values[3] = user.isMailEnable();
        return values;
    }

    private void initColumnNames() {
        this.columnNames = new String[4];
        columnNames[0] = EMAIL;
        columnNames[1] = PASSWORD;
        columnNames[2] = CONTACT_NUMBER;
        columnNames[3] = MAILING_ENABLED;
    }

    private List<User> getUserListFromResultList(List<Map<String, Object>> resList) {
        return resList.stream().map(m -> {
            User user = new User();
            user.setId((Integer) m.get(ID));
            user.setPassword((String) m.get(PASSWORD));
            user.setEmail((String) m.get(EMAIL));
            user.setNumber((String) m.get(CONTACT_NUMBER));
            user.setMailEnable((Boolean) m.get(MAILING_ENABLED));
            return user;
        }).collect(Collectors.toList());
    }
}
