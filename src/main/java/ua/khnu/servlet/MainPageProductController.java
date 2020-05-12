package ua.khnu.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.khnu.entity.Product;
import ua.khnu.entity.ProductAttribute;
import ua.khnu.listener.ConfigListener;
import ua.khnu.service.ProductService;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getRandomProductOnMain")
public class MainPageProductController extends HttpServlet {
    private static ProductService productService;
    private static final Logger logger = Logger.getLogger(MainPageProductController.class);

    @Override
    public void init() {
        ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute(ConfigListener.CTX);
        productService = ctx.getBean(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
        List<List<Product>> products = productService.getRandomProductsFromEachCategories(4);
        logger.debug(products);
        for (List<Product> prod : products) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Product product : prod) {
                JsonObjectBuilder prodBuilder = Json.createObjectBuilder();
                JsonArrayBuilder arrayBuilderColor = Json.createArrayBuilder();
                double min = -1;
                double max = Double.MAX_VALUE;
                for (ProductAttribute pr : product.getProductAttributes()) {
                    arrayBuilderColor.add(pr.getColor());
                    max = Double.max(max, pr.getPrice());
                    min = Double.min(min, pr.getPrice());
                }
                String price;
                if (min == max) {
                    price = String.valueOf(max);
                } else {
                    price = min + "-" + max;
                }
                JsonArray colors = arrayBuilderColor.build();
                logger.debug(product.getProductAttributes().get(0));
                JsonObject jsonProd = prodBuilder.add("id", product.getId())
                        .add("image", product.getProductAttributes().get(0).getPhoto())
                        .add("title", product.getTitle())
                        .add("colors", colors)
                        .add("price", price)
                        .build();
                arrayBuilder.add(jsonProd);
            }
            rootBuilder.add(prod.get(0).getCategory(), arrayBuilder);
        }
        JsonObject rootJson = rootBuilder.build();
        resp.setContentType("application/json");
        logger.info(rootJson);
        resp.getWriter().print(rootJson);
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
