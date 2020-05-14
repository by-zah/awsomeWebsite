package ua.khnu.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.khnu.entity.Cart;
import ua.khnu.entity.Product;
import ua.khnu.listener.ConfigListener;
import ua.khnu.service.ProductService;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        logger.debug(cart.getCart().size());
        if (req.getParameterMap().isEmpty()) {
            setMainPageCart(req, resp, cart);
        } else {
            if (Objects.nonNull(req.getParameter("availableAll"))) {
                sendAvailibleProduct(resp, cart);
            } else {
                int id = Integer.parseInt(req.getParameter("productId"));
                if (id < 0) {
                    clearCart(req, resp, cart);
                } else {
                    setNewAmountToProduct(req, resp, cart, id);
                }
            }
        }
    }

    private void sendAvailibleProduct(HttpServletResponse resp, Cart cart) throws IOException {
        JsonArrayBuilder jarB = Json.createArrayBuilder();
        JsonObjectBuilder jOB = Json.createObjectBuilder();
        for (Product p : cart.getCart().keySet()) {
            JsonObject ob = jOB.add("amountAvail", productService.getCountOfAvailableProductsById(
                    p.getProductAttributes().get(0).getId()
            )).add("productId", p.getProductAttributes().get(0).getId()).
                    build();
            jarB.add(ob);
        }
        JsonArray jsonArray = jarB.build();
        resp.setContentType("application/json");
        logger.info(jsonArray);
        resp.getWriter().print(jsonArray);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    private void setMainPageCart(HttpServletRequest req, HttpServletResponse resp, Cart cart) throws ServletException, IOException {
        List<Product> toDel = new ArrayList<>();
        for (Product p : cart.getCart().keySet()) {
            if (productService.getCountOfAvailableProductsById(p.getProductAttributes().get(0).getId()) == 0) {
                toDel.add(p);
                req.setAttribute("alert", "Некоторые продукты были распроданы и удалены из корзины ");
            }
        }
        cart.getCart().keySet().removeAll(toDel);
        req.getSession().setAttribute("cart", cart);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    private void setNewAmountToProduct(HttpServletRequest req, HttpServletResponse resp, Cart cart, int id) throws IOException {
        Product product = null;
        Optional<Product> opt = productService.getProductByAttributeId(id);
        if (opt.isPresent()) {
            product = opt.get();
        }
        logger.info("Product:" + product);
        Integer amount = Integer.parseInt(req.getParameter("amount"));
        if (amount == -1) {
            deletProduct(req, resp, cart, product);
        } else {
            boolean isDone = false;
            if (productService.getCountOfAvailableProductsById(product.getProductAttributes().get(0).getId()) >= amount) {
                cart.add(product, amount);
                isDone = true;
            }
            JsonObjectBuilder job = Json.createObjectBuilder();
            JsonObject object = job.add("cartSum", cart.getSum())
                    .add("price", cart.getCart().get(product) * product.getProductAttributes().get(0).getPrice())
                    .add("amountAvail", productService.getCountOfAvailableProductsById(product.getProductAttributes().get(0).getId()))
                    .add("done", isDone)
                    .build();
            req.getSession().setAttribute("cart", cart);
            resp.setContentType("application/json");
            logger.debug(object);
            resp.getWriter().print(object);
            resp.getWriter().flush();
            resp.getWriter().close();
        }
    }

    private void deletProduct(HttpServletRequest req, HttpServletResponse resp, Cart cart, Product product) throws IOException {
        JsonObjectBuilder job = Json.createObjectBuilder();
        cart.remove(product);
        logger.debug(cart.getCart());
        JsonObject object = job.add("cartSum", cart.getSum())
                .add("cartCount", cart.getAmount())
                .build();
        req.getSession().setAttribute("cart", cart);
        resp.setContentType("application/json");
        logger.debug(object);
        resp.getWriter().print(object);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    private void clearCart(HttpServletRequest req, HttpServletResponse resp, Cart cart) throws IOException {
        cart.clear();
        req.getSession().setAttribute("cart", cart);
        resp.setContentType("application/json");
        resp.getWriter().print("{\"ok\":1");
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
