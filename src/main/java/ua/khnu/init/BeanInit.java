package ua.khnu.init;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.khnu.exception.InitException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class BeanInit {
    private static final Logger LOG = Logger.getLogger(BeanInit.class);

    @Bean
    public DataSource dataSource() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            return (DataSource) envContext.lookup("jdbc/merchshop");
        } catch (NamingException e) {
            LOG.error(e);
            throw new InitException("Can`t init db connection");
        }
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcAccessor(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
