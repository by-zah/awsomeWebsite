package ua.khnu.entity;


import ua.khnu.util.DBName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    @DBName(name = "productID")
    private Integer id;
    private String title;
    private String description;
    private String category;
    private List<ProductAttribute> productAttributes;

    public Product() {
        productAttributes = new ArrayList<>();
    }

    public void addProductAttribute(ProductAttribute productAttribute) {
        productAttributes.add(productAttribute);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", productAttributes=" + productAttributes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(title, product.title) &&
                Objects.equals(description, product.description) &&
                Objects.equals(category, product.category) &&
                Objects.equals(productAttributes, product.productAttributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, productAttributes);
    }
}
