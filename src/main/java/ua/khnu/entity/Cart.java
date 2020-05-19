package ua.khnu.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {
    private final Map<Product, Integer> cart;

    public Cart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    public Cart() {
        this.cart = new HashMap<>();
    }


    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void add(Product shoes, int amount) {
        cart.put(shoes, amount);
    }

    public void remove(Product shoes) {
        cart.remove(shoes);
    }

    public void clear() {
        cart.clear();
    }

    public List<Product> getAll() {
        return (List<Product>) cart.keySet();
    }

    public int getAmount() {
        return cart.values().stream().mapToInt(Integer::intValue).sum();
    }

    public double getSum() {
        return cart.entrySet().stream().mapToDouble(e -> (e.getValue() * e.getKey().getProductAttributes().get(0).getPrice())).sum();

    }

    public void putAll(Cart cartBeforeLogIn) {
        cart.putAll(cartBeforeLogIn.getCart());
    }
}
