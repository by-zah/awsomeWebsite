package ua.khnu.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.khnu.entity.Cart;
import ua.khnu.entity.Product;
import ua.khnu.listener.ConfigListener;
import ua.khnu.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/cart")
public class CartController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CartController.class);
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute(ConfigListener.CTX);
        productService = ctx.getBean(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug(req.getParameter("productId"));
        logger.debug(req.getParameter("amount"));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (Objects.isNull(cart)) {
            cart = new Cart();
        }
        int id = Integer.parseInt(req.getParameter("productId"));
        if (id < 0) {
            clearCart(req, resp, cart);
        } else {
            setNewAmountToProduct(req, resp, cart, id);
        }
    }

    private void setNewAmountToProduct(HttpServletRequest req, HttpServletResponse resp, Cart cart, int id) throws IOException {
        Product product = null;
        Optional<Product> opt = productService.getProductByAttributeId(id);
        if (opt.isPresent()) {
            product = opt.get();
        }
        logger.info("Product:" + product);
        Integer amount = Integer.parseInt(req.getParameter("amount"));
        cart.add(product, amount);
        req.getSession().setAttribute("cart", cart);
        resp.setContentType("application/json");
        resp.getWriter().write("{\n" +
                "\"dataCount\":" + cart.getAmount() + "," +
                "\"sum\":" + ((amount - 1) * product.getProductAttributes().get(0).getPrice()) + "," +
                "\"total\":" + cart.getSum() + "," +
                "\"id\":" + product.getId() + "," +
                "}");

    }

    private void clearCart(HttpServletRequest req, HttpServletResponse resp, Cart cart) throws IOException {
        cart.clear();
        req.getSession().setAttribute("cart", cart);
        resp.setContentType("application/json");
        resp.getWriter().write("{\n" +
                "\"dataCount\":" + 0 + "," +
                "\"sum\":" + 0 + "," +
                "\"total\":" + cart.getSum() + "," +
                "\"id\":" + 0 + "," +
                "}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
