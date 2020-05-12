package ua.khnu.reposetory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.khnu.entity.Product;
import ua.khnu.entity.ProductAttribute;

import java.util.*;

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

    @Override
    protected List<Product> getObjectListFromResultList(List<Map<String, Object>> resList) {
        LOG.debug(resList);
        LinkedList<Product> products = new LinkedList<>();
        Product product = new Product();
        for (Map<String, Object> map : resList) {
            int productId = (int) map.get("productID");
            if (!Objects.equals(product.getId(), productId)) {
                products.add(product);
                product = new Product();
                product.setId(productId);
                product.setDescription((String) map.get("description"));
                product.setTitle((String) map.get("title"));
                product.setCategory((String) map.get("category"));
            } else {
                ProductAttribute pa = new ProductAttribute();
                pa.setId((String) map.get("id"));
                pa.setColor((String) map.get("color"));
                pa.setPhoto((String) map.get("photo"));
                pa.setPrice((Double) map.get("price"));
                pa.setSize((String) map.get("size"));
                product.addProductAttribute(pa);
            }
        }
        products.removeFirst();
        return products;
    }
}
