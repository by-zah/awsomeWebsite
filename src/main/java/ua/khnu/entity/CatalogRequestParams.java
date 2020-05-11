package ua.khnu.entity;

import java.util.List;
import java.util.Objects;

public class CatalogRequestParams {
    private SortType sortType;
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

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
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

    @Override
    public String toString() {
        return "CatalogRequestParams{" +
                "sortType=" + sortType +
                ", category=" + category +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", color=" + color +
                ", size=" + size +
                ", itemFrom=" + itemFrom +
                ", itemTo=" + itemTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogRequestParams that = (CatalogRequestParams) o;
        return sortType == that.sortType &&
                Objects.equals(category, that.category) &&
                Objects.equals(priceFrom, that.priceFrom) &&
                Objects.equals(priceTo, that.priceTo) &&
                Objects.equals(color, that.color) &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortType, category, priceFrom, priceTo, color, size);
    }
}
