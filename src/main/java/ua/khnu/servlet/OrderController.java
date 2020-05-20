package ua.khnu.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.khnu.entity.*;
import ua.khnu.listener.ConfigListener;
import ua.khnu.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(OrderController.class);
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute(ConfigListener.CTX);
        orderService = ctx.getBean(OrderService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("profile.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart.getSum() == 0) {
            req.setAttribute("orderAlert", "For making order you need add something in cart");
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        } else {
            User user = (User) req.getSession().getAttribute("currentUser");
            Order order = new Order();
            order.setDeliveryPrice(100.0);
            order.setDiscount(cart.getSum() * 0.01);
            ShippingMethod shippingMethod = ShippingMethod.valueOf(req.getParameter("orderMethod"));
            order.setShippingMethod(shippingMethod);
            ShippingAddress sh = new ShippingAddress();
            sh.setBuilding(req.getParameter("building"));
            sh.setCity(req.getParameter("city"));
            sh.setRegion(req.getParameter("city"));
            sh.setIndex(req.getParameter("index"));
            sh.setStreet("street");
            order.setShippingAddress(sh);
            order.setPaymentMethod(req.getParameter("cardNumber"));
            order.setUserId(user.getId());
            order.setProductAndAmount(cart.getCart());
            int num = orderService.createNewOrder(order);
            req.setAttribute("orderAlert", "Ваш заказ №" + num + " отправлен на подготовку");
            cart.clear();
            req.getSession().setAttribute("cart", cart);
        }
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }
}
