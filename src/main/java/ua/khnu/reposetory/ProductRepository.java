package ua.khnu.reposetory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.khnu.entity.Product;

@Component
public class ProductRepository extends AbstractRepository<Product> {

    @Autowired
    public ProductRepository(JdbcTemplate jdbcAccessor) {
        super(jdbcAccessor);
    }
}
