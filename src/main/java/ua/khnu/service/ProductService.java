package ua.khnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.khnu.entity.CatalogRequestParams;
import ua.khnu.entity.Product;
import ua.khnu.entity.User;
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


    private static final String GET_RANDOM_PRODUCTS_FROM_CATEGORY = "SELECT products_attributes.id AS id,\n" +
            "       products.title AS title,\n" +
            "       categories.title AS category,\n" +
            "       products_attributes.photo AS photo,\n" +
            "       products_attributes.price AS price\n" +
            "FROM products\n" +
            "         INNER JOIN products_attributes\n" +
            "                    ON products_attributes.productID = products.id\n" +
            "         INNER JOIN categories\n" +
            "                    ON products.categoryID = categories.id\n" +
            "WHERE categories.title = ?\n" +
            "GROUP BY products.title\n" +
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

    private static final String GET_PRODUCT_BY_ID = "SELECT products_attributes.id as id,\n" +
            "       c.title                as category,\n" +
            "       p.title                as title,\n" +
            "       c.description,\n" +
            "       color,\n" +
            "       size,\n" +
            "       price,\n" +
            "       photo\n" +
            "FROM products_attributes\n" +
            "         JOIN products p on products_attributes.productID = p.id\n" +
            "         JOIN categories c on p.categoryID = c.id" +
            " WHERE products_attributes.id=?";

    public Optional<Product> getProductById(int id) {
        return getFirstOptionalFromList(repository.query(GET_PRODUCT_BY_ID, id));
    }
}
