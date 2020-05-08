package ua.khnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.khnu.entity.Product;
import ua.khnu.reposetory.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
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
}
