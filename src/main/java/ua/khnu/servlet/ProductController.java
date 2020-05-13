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
import java.util.HashSet;
import java.util.Set;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ProductController.class);
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
        JsonArrayBuilder arrayBuilderColor = Json.createArrayBuilder();
        JsonArrayBuilder arrayBuilderSize = Json.createArrayBuilder();
        Set<String> colorSet = new HashSet<>();
        Set<String> sizeSet = new HashSet<>();
        for (ProductAttribute pa : product.getProductAttributes()) {
            colorSet.add(pa.getColor());
            sizeSet.add(pa.getSize());
        }
        for (String s : colorSet) {
            arrayBuilderColor.add(s);
        }
        for (String s : sizeSet) {
            arrayBuilderSize.add(s);
        }
        JsonArray colors = arrayBuilderColor.build();
        JsonArray sizes = arrayBuilderSize.build();
        logger.info("Product:" + product);
        JsonObject jsonProd = prodBuilder.add("idUnic", product.getProductAttributes().get(0).getId())
                .add("idProd", product.getId())
                .add("image", product.getProductAttributes().get(0).getPhoto())
                .add("title", product.getTitle())
                .add("price", product.getProductAttributes().get(0).getPrice())
                .add("description", product.getDescription())
                .add("category", product.getCategory())
                .add("amountAvail", productService.getCountOfAvailableProductsById(product.getProductAttributes().get(0).getId()))
                .add("colorSelect", product.getProductAttributes().get(0).getColor())
                .add("sizeSelect", product.getProductAttributes().get(0).getSize())
                .add("colors", colors)
                .add("sizes", sizes)
                .build();
        resp.setContentType("application/json");
        logger.info(jsonProd);
        resp.getWriter().print(jsonProd);
        resp.getWriter().flush();
        resp.getWriter().close();
    }
    //это на 1 раз, а каждый след паша ает ид продукта цвет размер и получает ответ со значениями или ничего если такого нет
}