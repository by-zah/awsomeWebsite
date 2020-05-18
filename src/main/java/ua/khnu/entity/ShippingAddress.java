package ua.khnu.entity;

import ua.khnu.util.DBName;

@DBName(name = "shipping_addresses")
public class ShippingAddress {
    @DBName(name = "shippingAddressID")
    private int id;
    private String city;
    private String region;
    private String street;
    private String building;
    @DBName(name = "`index`")
    private String index;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
