package ua.khnu.entity;

import java.util.List;

public class CatalogRequestParams {
    private String sortType;
    private List<String> category;
    private double priceFrom;
    private double priceTo;
    private List<String> color;
    private List<String> size;
    private int itemFrom;
    private int itemTo;

    public int getItemFrom() {
        return itemFrom;
    }

    public void setItemFrom(int itemFrom) {
        this.itemFrom = itemFrom;
    }

    public int getItemTo() {
        return itemTo;
    }

    public void setItemTo(int itemTo) {
        this.itemTo = itemTo;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }
}
