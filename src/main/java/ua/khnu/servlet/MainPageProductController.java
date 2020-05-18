package ua.khnu.servlet;

import org.apache.commons.math3.util.Precision;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                double min = Double.MAX_VALUE;
                double max = -1;
                Set<String> colorSet = new HashSet<>();
                for (ProductAttribute pr : product.getProductAttributes()) {
                    colorSet.add(pr.getColor());
                    max = Double.max(max, pr.getPrice());
                    min = Double.min(min, pr.getPrice());
                }
                for (String s : colorSet) {
                    arrayBuilderColor.add(s);
                }
                String price;
                if (min == max) {
                    price = String.valueOf(Precision.round(max, 2));
                } else {
                    price = Precision.round(min, 2) + "-" + Precision.round(max, 2);
                }
                logger.debug("Price:" + price);
                JsonArray colors = arrayBuilderColor.build();
                logger.debug(product.getProductAttributes().get(0));
                JsonObject jsonProd = prodBuilder.add("id", product.getId())
                        .add("image", product.getProductAttributes().get(0).getPhoto())
                        .add("title", product.getTitle())
                        .add("color", colors)
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
