package ua.khnu.util;

public final class BigQueries {
    public static final String GET_ORDERS_BY_USER_ID = "SELECT active_orders.id                          as id,\n" +
            "       userID,\n" +
            "       paymentMethod,\n" +
            "       shippingMethodID,\n" +
            "       shippingAddressID,\n" +
            "       city,\n" +
            "       region,\n" +
            "       street,\n" +
            "       `index`,\n" +
            "       totalProductPrice,\n" +
            "       deliveryPrice,\n" +
            "       discountAmount,\n" +
            "       totalPrice,\n" +
            "       datePlaced,\n" +
            "       comment,\n" +
            "       GROUP_CONCAT(pa.id SEPARATOR ';')         AS attributeID,\n" +
            "       GROUP_CONCAT(pa.productID SEPARATOR ';')  AS productID,\n" +
            "       GROUP_CONCAT(p.title SEPARATOR ';')       AS title,\n" +
            "       GROUP_CONCAT(p.description SEPARATOR ';') AS description,\n" +
            "       GROUP_CONCAT(c.title SEPARATOR ';')       AS category,\n" +
            "       GROUP_CONCAT(pa.photo SEPARATOR ';')      AS photo,\n" +
            "       GROUP_CONCAT(pa.price SEPARATOR ';')      AS price,\n" +
            "       GROUP_CONCAT(pa.color SEPARATOR ';')      AS color,\n" +
            "       GROUP_CONCAT(pa.size SEPARATOR ';')       AS size,\n" +
            "       GROUP_CONCAT(po.amount SEPARATOR ';')     AS amount\n" +
            "FROM active_orders\n" +
            "         JOIN shipping_addresses sa on active_orders.shippingAddressID = sa.id\n" +
            "         JOIN products_orders po on active_orders.id = po.activeOrderID\n" +
            "         JOIN products_attributes pa on po.productsAttributesID = pa.id\n" +
            "         JOIN products p on pa.productID = p.id\n" +
            "         JOIN categories c on p.categoryID = c.id\n" +
            "where userID = ?\n" +
            "GROUP BY active_orders.id";

    private BigQueries() {
        throw new UnsupportedOperationException();
    }
}
