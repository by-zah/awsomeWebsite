package ua.khnu.reposetory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class ProductRepository {
    private String[] columnNames;
    private JdbcTemplate jdbcAccessor;
    private SimpleJdbcInsert simpleJdbcInsert;


    @Autowired
    public ProductRepository(JdbcTemplate jdbcAccessor, DataSource ds) {
        this.jdbcAccessor = jdbcAccessor;
        initColumnNames();
        simpleJdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("users").usingGeneratedKeyColumns(ID)
                .usingColumns(columnNames);
    }
}
