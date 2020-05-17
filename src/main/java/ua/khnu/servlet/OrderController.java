package ua.khnu.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.khnu.entity.Cart;
import ua.khnu.entity.User;
import ua.khnu.listener.ConfigListener;
import ua.khnu.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(OrderController.class);
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute(ConfigListener.CTX);
        productService = ctx.getBean(ProductService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart.getSum() == 0) {
            System.out.println("popopo");
            req.setAttribute("orderAlert", "For making order you need add something in cart");
            req.getRequestDispatcher("cart.jsp");
        } else {
            User userDTO = (User) req.getSession().getAttribute("currentUser");
            // Order order = new Order(userDTO.getEmail(), cart, req.getParameter("address"), req.getParameter("orderMethod"));
            //int orderId = productService.makeOrder(order);
            //  productService.cleanUserTemporaryCart(order.getUserMail());

            req.setAttribute("orderAlert", "You order №" + (new Random().nextInt()) + " is ready");
            cart.clear();
            req.getSession().setAttribute("cart", cart);
        }
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }
}
