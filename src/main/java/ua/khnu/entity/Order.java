package ua.khnu.entity;

import ua.khnu.util.DBName;

import java.util.Map;

public class Order {
    private Integer id;
    private Integer userId;
    private String paymentMethod;
    private ShippingMethod shippingMethod;
    private ShippingAddress shippingAddress;
    private Double totalProductPrice;
    private Double deliveryPrice;
    @DBName(name = "discountAmount")
    private Double discount;
    private Double totalPrice;
    private Long datePlaced;
    private String comment;
    /**
     * key - Product with at least id and price
     * value - amount
     */
    private Map<Product, Integer> productAndAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(Double totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Long datePlaced) {
        this.datePlaced = datePlaced;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

