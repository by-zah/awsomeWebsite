package ua.khnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.khnu.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    // private final ProductRepository repository;
    @Autowired
//    public ProductService(UserRepository repository) {
//        this.repository = repository;
//    }

    public List<List<Product>> getRandomForMain() {
        List<Product> toys = new ArrayList<>();
        List<Product> statuettes = new ArrayList<>();
        List<Product> tshirts = new ArrayList<>();
        List<Product> comics = new ArrayList<>();
        List<List<Product>> res = new ArrayList<>();
        res.add(comics);
        res.add(statuettes);
        res.add(tshirts);
        res.add(toys);
        return res;
    }
}
