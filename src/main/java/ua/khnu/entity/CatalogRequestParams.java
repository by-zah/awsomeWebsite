package ua.khnu.entity;

import java.util.List;

public class CatalogRequestParams {
    private String sortType;
    private List<String> category;
    private Double priceFrom;
    private Double priceTo;
    private List<String> color;
    private List<String> size;
    private Integer itemFrom;
    private Integer itemTo;

    public Integer getItemFrom() {
        return itemFrom;
    }

    public void setItemFrom(Integer itemFrom) {
        this.itemFrom = itemFrom;
    }

    public Integer getItemTo() {
        return itemTo;
    }

    public void setItemTo(Integer itemTo) {
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

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }
}
