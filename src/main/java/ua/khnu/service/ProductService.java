package ua.khnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.khnu.entity.CatalogRequestParams;
import ua.khnu.entity.Product;
import ua.khnu.reposetory.ProductRepository;
import ua.khnu.util.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.khnu.util.Util.getFirstOptionalFromList;

@Component
public class ProductService {
    private final ProductRepository repository;
    private final QueryBuilder queryBuilder;

    @Autowired
    public ProductService(ProductRepository repository, QueryBuilder queryBuilder) {
        this.repository = repository;
        this.queryBuilder = queryBuilder;
    }

    public static final String FROM = "FROM products\n" +
            "         INNER JOIN products_attributes\n" +
            "                    ON products_attributes.productID = products.id\n" +
            "         INNER JOIN categories\n" +
            "                    ON products.categoryID = categories.id\n";
    public static final String QUERY_BODY = "SELECT GROUP_CONCAT(products_attributes.id SEPARATOR ';')    AS id,\n" +
            "       products.title                                        AS title,\n" +
            "       categories.title                                      AS category,\n" +
            "       GROUP_CONCAT(products_attributes.photo SEPARATOR ';') AS photo,\n" +
            "       GROUP_CONCAT(products_attributes.price SEPARATOR ';') AS price,\n" +
            "       GROUP_CONCAT(products_attributes.color SEPARATOR ';') AS color,\n" +
            "       GROUP_CONCAT(products_attributes.size SEPARATOR ';')  AS size,\n" +
            "       productID\n";
    private static final String GET_RANDOM_PRODUCTS_FROM_CATEGORY = QUERY_BODY + FROM+
            "WHERE categories.title =?\n" +
            "GROUP BY (products_attributes.productID)\n" +
            "ORDER BY RAND()\n" +
            "LIMIT ?";

    public List<List<Product>> getRandomProductsFromEachCategories(int num) {
        List<List<Product>> res = new ArrayList<>();
        res.add(repository.query(GET_RANDOM_PRODUCTS_FROM_CATEGORY, "Одежда", num));
        res.add(repository.query(GET_RANDOM_PRODUCTS_FROM_CATEGORY, "Игрушки", num));
        res.add(repository.query(GET_RANDOM_PRODUCTS_FROM_CATEGORY, "Фигурки", num));
        res.add(repository.query(GET_RANDOM_PRODUCTS_FROM_CATEGORY, "Аксессуары", num));
        return res;
    }

    private static final String GET_COUNT_OF_AVAILABLE_PRODUCTS_BY_ID = "select (products_in_stock.in_stock - IFNULL(SUM(products_out.amountOUT), 0)) as current_in_stock\n" +
            "from products_in_stock\n" +
            "         left join products_out\n" +
            "                   on products_in_stock.id = products_out.productAttributeID\n" +
            "WHERE products_in_stock.id = ?\n" +
            "group by products_in_stock.id";

    public int getCountOfAvailableProductsById(int id) {
        return repository.queryForInt(GET_COUNT_OF_AVAILABLE_PRODUCTS_BY_ID, id).orElse(0);
    }

    public List<Product> getProductsByParams(CatalogRequestParams crp) {
        String query = queryBuilder.getQueryFromCategoryRequestParams(crp);
        Object[] params = queryBuilder.getParams(crp);
        return repository.query(query, params);
    }

    private static final String GET_PRODUCT_BY_ID = QUERY_BODY+
            ", products.description "+ FROM+
            " WHERE products_attributes.id=?";

    /**
     *
     * @param id product_attribute id
     * @return optional of Product
     */
    public Optional<Product> getProductById(int id) {
        return getFirstOptionalFromList(repository.query(GET_PRODUCT_BY_ID, id));
    }
}
