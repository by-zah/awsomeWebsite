package ua.khnu.reposetory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.khnu.entity.Product;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository extends AbstractRepository<Product> {
    private static final Logger LOG = Logger.getLogger(ProductRepository.class);

    @Autowired
    public ProductRepository(JdbcTemplate jdbcAccessor) {
        super(jdbcAccessor);
    }

    public List<Product> query(String query) {
        return query(query, new Object[0]);
    }

    public List<Product> query(String query, Object... args) {
        LOG.debug("query --> " + query);
        LOG.debug("args --> " + Arrays.toString(args));
        return getObjectListFromResultList(jdbcAccessor.queryForList(query, args));
    }
}
