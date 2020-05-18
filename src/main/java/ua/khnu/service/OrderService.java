package ua.khnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.khnu.entity.Order;
import ua.khnu.entity.Product;
import ua.khnu.reposetory.OrderRepository;
import ua.khnu.util.BigQueries;

import java.util.List;
import java.util.Map;

@Component
public class OrderService {
    private OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void createNewOrder(Order order) {
        Map<Product, Integer> productsAndAmount = order.getProductAndAmount();
        double totalProductPrice = productsAndAmount.keySet().stream()
                .map(product -> product.getProductAttributes().get(0).getPrice() * productsAndAmount.get(product))
                .reduce(Double::sum).orElse(0.);
        order.setTotalProductPrice(totalProductPrice);
        order.setTotalPrice(order.getTotalProductPrice() - order.getDiscount() + order.getDeliveryPrice());
        order.setDatePlaced(System.currentTimeMillis());
        repository.add(order);
    }

    public List<Order> getOrdersByUserId(int userId) {
        return repository.query(BigQueries.GET_ORDERS_BY_USER_ID, userId);
    }
}
