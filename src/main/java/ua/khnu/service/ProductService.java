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


    private static final String GET_RANDOM_PRODUCTS_FROM_CATEGORY ="SELECT products_attributes.id AS id,\n" +
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
        res.add(repository.query(GET_RANDOM_PRODUCTS_FROM_CATEGORY,"Одежда",num));
        res.add(repository.query(GET_RANDOM_PRODUCTS_FROM_CATEGORY,"Игрушки",num));
        res.add(repository.query(GET_RANDOM_PRODUCTS_FROM_CATEGORY,"Фигурки",num));
        res.add(repository.query(GET_RANDOM_PRODUCTS_FROM_CATEGORY,"Аксессуары",num));
        return res;
    }
}
