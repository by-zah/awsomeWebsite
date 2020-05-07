package ua.khnu.reposetory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ua.khnu.entity.Product;
import ua.khnu.util.DBConstant;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductRepository {
    private static final Logger LOG = Logger.getLogger(ProductRepository.class);
    private String[] columnNames;
    private JdbcTemplate jdbcAccessor;


    @Autowired
    public ProductRepository(JdbcTemplate jdbcAccessor) {
        this.jdbcAccessor = jdbcAccessor;
    }

    public List<Product> query(String query) {
        return query(query, (Object) null);
    }

    public List<Product> query(String query, Object... args) {
        LOG.debug("query --> " + query);
        LOG.debug("args --> " + Arrays.toString(args));
        return getProductListFromResultList(jdbcAccessor.queryForList(query, args));
    }

    private List<Product> getProductListFromResultList(List<Map<String, Object>> resList){
        return resList.stream().map(m -> {
            Product product = new Product();
            product.setId((Integer) m.get(DBConstant.ID));
            product.setTitle((String) m.get("title"));
            product.setCategory((String) m.get("category"));
            product.setPhoto((String) m.get("photo"));
            product.setColor((String) m.get("color"));
            product.setDescription((String) m.get("description"));
            product.setSize((String) m.get("size"));
            product.setPrice(BigDecimal.valueOf((Double) m.get("price")));
            return product;
        }).collect(Collectors.toList());
    }
}
