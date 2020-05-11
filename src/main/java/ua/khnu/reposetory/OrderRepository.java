package ua.khnu.reposetory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.khnu.entity.Order;
import ua.khnu.entity.ShippingAddress;
import ua.khnu.util.DBConstant;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderRepository extends AbstractRepository<Order> {
    private static final String ADD_PRODUCT_TO_ORDER = "INSERT INTO products_orders VALUE (?,?,?,?)";
    private static final String ADD_ORDER_STATUS = "INSERT INTO track_orders VALUE (?,?,null)";
    private SimpleJdbcInsert simpleJdbcInsertShippingAddress;
    private SimpleJdbcInsert simpleJdbcInsertOrder;
    private String[] shippingAddressColumnNames;
    private String[] orderColumnNames;

    @Autowired
    public OrderRepository(JdbcTemplate jdbcAccessor, DataSource ds) {
        super(jdbcAccessor);
        simpleJdbcInsertShippingAddress = new SimpleJdbcInsert(ds)
                .withTableName("shipping_addresses")
                .usingGeneratedKeyColumns(DBConstant.ID)
                .usingColumns(shippingAddressColumnNames);
        simpleJdbcInsertOrder = new SimpleJdbcInsert(ds)
                .withTableName("active_orders")
                .usingGeneratedKeyColumns(DBConstant.ID)
                .usingColumns(orderColumnNames);
        initColumnNames();
    }

    @Transactional
    public void add(Order order) {
        ShippingAddress shippingAddress = order.getShippingAddress();
        shippingAddress.setId(simpleJdbcInsertShippingAddress.
                executeAndReturnKey(extractParams(shippingAddress)).intValue());
        int orderId = simpleJdbcInsertOrder.executeAndReturnKey(extractParams(order)).intValue();
        order.getProductAndAmount().forEach((product, amount) ->
                jdbcAccessor.update(ADD_PRODUCT_TO_ORDER,
                        orderId, product.getId()
                        , amount, product.getPrice()));
        jdbcAccessor.update(ADD_ORDER_STATUS, orderId, "заказан");
    }

    private Map<String, Object> extractParams(ShippingAddress sa) {
        Map<String, Object> params = new HashMap<>();
        params.put(shippingAddressColumnNames[0], sa.getCity());
        params.put(shippingAddressColumnNames[1], sa.getRegion());
        params.put(shippingAddressColumnNames[2], sa.getStreet());
        params.put(shippingAddressColumnNames[3], sa.getBuilding());
        params.put(shippingAddressColumnNames[4], sa.getIndex());
        return params;
    }

    private Map<String, Object> extractParams(Order order) {
        Map<String, Object> params = new HashMap<>();
        params.put(orderColumnNames[0], order.getUserId());
        params.put(orderColumnNames[1], order.getPaymentMethod());
        params.put(orderColumnNames[2], order.getShippingMethod().ordinal() + 1);
        params.put(orderColumnNames[3], order.getShippingAddress().getId());
        params.put(orderColumnNames[4], order.getTotalProductPrice());
        params.put(orderColumnNames[5], order.getDeliveryPrice());
        params.put(orderColumnNames[6], order.getDiscount());
        params.put(orderColumnNames[7], order.getTotalPrice());
        params.put(orderColumnNames[8], order.getDatePlaced());
        params.put(orderColumnNames[9], order.getComment());
        return params;
    }

    private void initColumnNames() {
        shippingAddressColumnNames = new String[5];
        shippingAddressColumnNames[0] = "city";
        shippingAddressColumnNames[1] = "region";
        shippingAddressColumnNames[2] = "street";
        shippingAddressColumnNames[3] = "building";
        shippingAddressColumnNames[4] = "index";

        orderColumnNames = new String[10];
        orderColumnNames[0] = "userID";
        orderColumnNames[1] = "paymentMethod";
        orderColumnNames[2] = "shippingMethodID";
        orderColumnNames[3] = "shippingAddressID";
        orderColumnNames[4] = "totalProductPrice";
        orderColumnNames[5] = "deliveryPrice";
        orderColumnNames[6] = "discountAmount";
        orderColumnNames[7] = "totalPrice";
        orderColumnNames[8] = "datePlaced";
        orderColumnNames[9] = "comment";
    }
}
