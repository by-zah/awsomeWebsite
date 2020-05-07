package ua.khnu.entity;

/**
 * abstract class for future product
  **/
public abstract class Product {
    private  int id;
    private double price;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String image;
    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setPrice(String color) {
        this.color = color;
    }
    //TODO add all for product , then specify
}
