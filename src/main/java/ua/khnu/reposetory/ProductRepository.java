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
    private static final String SEPARATOR = ";";

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
            }
            String[] ids = getArrBySeparator((map.get("id")));
            String[] prices = getArrBySeparator(map.get("price"));
            String[] photos = getArrBySeparator(map.get("photo"));

            String[] colors = getArrBySeparator(map.get("color"));
            String[] sizes = getArrBySeparator(map.get("size"));
            for (int i = 0; i < ids.length; i++) {
                ProductAttribute pa = new ProductAttribute();
                pa.setId(Integer.valueOf(ids[i]));
                Double price = i < prices.length ? Double.valueOf(prices[i]) : null;
                String photo = getFromArrayIfExist(photos, i);
                String color = getFromArrayIfExist(colors, i);
                String size = getFromArrayIfExist(sizes, i);
                pa.setPrice(price);
                pa.setPhoto(photo);
                pa.setColor(color);
                pa.setSize(size);
                product.addProductAttribute(pa);
            }
        }
        products.add(product);
        products.removeFirst();
        return products;
    }

    private String getFromArrayIfExist(String[] arr, int i) {
        return i < arr.length ? arr[i] : null;
    }

    private String[] getArrBySeparator(Object s) {
        String str = String.valueOf(s);
        return str.split(SEPARATOR);
    }
}
