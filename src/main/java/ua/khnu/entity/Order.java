package ua.khnu.entity;

import java.util.Map;

public class Order {
    /**
     * key - Product with at least id and price
     * value - amount
     */
    private Map<Product, Integer> productAndAmount;
    private ShippingAddress shippingAddress;
    private ShippingMethod shippingMethod;
    private int userId;
    private String paymentMethod;
    private double deliveryPrice;
    private double discount;
    private String comment;

    public Map<Product, Integer> getProductAndAmount() {
        return productAndAmount;
    }

    public void setProductAndAmount(Map<Product, Integer> productAndAmount) {
        this.productAndAmount = productAndAmount;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

