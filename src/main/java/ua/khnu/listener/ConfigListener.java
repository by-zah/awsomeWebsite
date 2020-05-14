package ua.khnu.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.khnu.entity.Order;
import ua.khnu.entity.Product;
import ua.khnu.entity.ShippingAddress;
import ua.khnu.entity.ShippingMethod;
import ua.khnu.init.BeanInit;
import ua.khnu.service.OrderService;
import ua.khnu.service.ProductService;
import ua.khnu.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class ConfigListener implements ServletContextListener {
    public static final String CTX = "ctx";
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("ua.khnu");
        ctx.register(BeanInit.class);
        ctx.refresh();
        ServletContext servletContext = sce.getServletContext();
        initLog4J(servletContext);
        servletContext.setAttribute(CTX, ctx);
        ProductService ps = ctx.getBean(ProductService.class);
        Map<Product,Integer> pi = new HashMap<>();
        pi.put(ps.getProductById(1).get(),5);
        pi.put(ps.getProductById(2).get(),2);
        OrderService os = ctx.getBean(OrderService.class);
        ShippingAddress sa = new ShippingAddress();
        sa.setCity("cityTest");
        sa.setRegion("regionTest");
        sa.setStreet("streetTest");
        sa.setBuilding("building");
        sa.setIndex("23412");
        Order order = new Order();
        order.setShippingAddress(sa);
        order.setProductAndAmount(pi);
        order.setDiscount(100.);
        order.setDeliveryPrice(40.);
        order.setUserId(1);
        order.setShippingMethod(ShippingMethod.NOVA_POSHTA);
        order.setPaymentMethod("Visa");
        order.setComment("comment comment comment comment comment");
        os.createNewOrder(order);
    }

    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(
                    servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            log("Cannot configure Log4j");
        }
        log("Log4J initialization finished");
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}
