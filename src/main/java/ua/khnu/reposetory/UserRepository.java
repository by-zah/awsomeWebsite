package ua.khnu.reposetory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ua.khnu.entity.User;
import ua.khnu.util.DBConstant;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository extends AbstractRepository<User> {
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String CONTACT_NUMBER = "contactNumber";
    public static final String MAILING_ENABLED = "mailingEnabled";
    private static final Logger LOG = Logger.getLogger(UserRepository.class);
    private static final String DELETE = "DELETE FROM users WHERE id=?";
    private static final String UPDATE = "UPDATE users " +
            "SET email=?,password=?,contactNumber=?,mailingEnabled=?" +
            " WHERE id=?";
    private String[] columnNames;
    private SimpleJdbcInsert simpleJdbcInsert;


    @Autowired
    public UserRepository(JdbcTemplate jdbcAccessor, DataSource ds) {
        super(jdbcAccessor);
        initColumnNames();
        simpleJdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("users").usingGeneratedKeyColumns(DBConstant.ID)
                .usingColumns(columnNames);
    }

    public User add(User user) {
        int id = simpleJdbcInsert.executeAndReturnKey(extractParameters(user)).intValue();
        user.setId(id);
        return user;
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
        Map<String, Object> params = new HashMap<>(columnNames.length - 1);
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

}
