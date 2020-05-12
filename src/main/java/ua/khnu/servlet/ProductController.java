package ua.khnu.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.khnu.entity.Product;
import ua.khnu.listener.ConfigListener;
import ua.khnu.service.ProductService;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CartController.class);
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute(ConfigListener.CTX);
        productService = ctx.getBean(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("productId"));
        Product product = productService.getProductById(id).get();
        JsonObjectBuilder prodBuilder = Json.createObjectBuilder();
        logger.info("Product:" + product);
        JsonObject jsonProd = prodBuilder.add("id", product.getId())
                .add("image", product.getPhoto())
                .add("title", product.getTitle())
                .add("price", product.getPrice())
                .add("description", product.getDescription())
                .add("amountAvail", productService.getCountOfAvailableProductsById(id))
                .build();
        resp.setContentType("application/json");
        logger.info(jsonProd);
        resp.getWriter().print(jsonProd);
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
