package ua.khnu.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.khnu.entity.CatalogRequestParams;
import ua.khnu.entity.Product;
import ua.khnu.entity.ProductAttribute;
import ua.khnu.entity.SortType;
import ua.khnu.listener.ConfigListener;
import ua.khnu.service.ProductService;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/catalog")
public class CatalogController extends HttpServlet {
    private static final Map<String, String> category = new HashMap<>();

    static {
        category.put("Tshirt", "Одежда");
        category.put("Figure", "Фигурки");
        category.put("Toy", "Игрушки");
        category.put("Accessory", "Аксессуары");
    }

    private static final int NUM_ON_PAGE = 8;
    private static final Logger logger = Logger.getLogger(CatalogController.class);
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute(ConfigListener.CTX);
        productService = ctx.getBean(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getProductsByParams(setCatalogRequestParams(req));
        JsonArrayBuilder rootBuilder = Json.createArrayBuilder();
        logger.info("From bd" + products);
        for (Product product : products) {
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
            JsonObject jsonProd = prodBuilder.add("id", product.getId())
                    .add("image", product.getProductAttributes().get(0).getPhoto())
                    .add("title", product.getTitle())
                    .add("price", price)
                    .add("colors", colors)
                    .build();
            logger.info("Prod" + jsonProd);
            rootBuilder.add(jsonProd);
        }

        JsonArray rootJson = rootBuilder.build();
        resp.setContentType("application/json");
        logger.info(rootJson);
        req.setAttribute("userRequestParameter", setCatalogRequestParams(req));
        resp.getWriter().print(rootJson);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    private CatalogRequestParams setCatalogRequestParams(HttpServletRequest request) {
        CatalogRequestParams crp = new CatalogRequestParams();
        logger.info("request: " + request.getParameterMap().toString());
        crp.setSortType(SortType.valueOf(request.getParameter("sortType")));
        if (Objects.nonNull(request.getParameter("category"))) {
            crp.setCategory(Collections.singletonList(category.get(request.getParameter("category"))));
        }
        if (Objects.nonNull(request.getParameter("size"))) {
            crp.setSize(Collections.singletonList(request.getParameter("size")));
        }
        if (Objects.nonNull(request.getParameter("color"))) {
            crp.setColor(Collections.singletonList(request.getParameter("color")));
        }
        try {
            Double d = Double.valueOf(request.getParameter("priceFrom"));
            crp.setPriceFrom(d);
        } catch (NumberFormatException | NullPointerException e) {
            crp.setPriceFrom(null);
            logger.info("PriceFrom is nan");
        }
        try {
            Double d = Double.valueOf(request.getParameter("priceTo"));
            crp.setPriceFrom(d);
        } catch (NumberFormatException | NullPointerException e) {
            crp.setPriceFrom(null);
            logger.info("PriceTo is nan");
        }
        Integer more;
        try {
            more = Integer.valueOf(request.getParameter("more"));
        } catch (NumberFormatException | NullPointerException e) {
            more = 1;
        }
        crp.setItemFrom(more * NUM_ON_PAGE - (NUM_ON_PAGE - 1));
        crp.setItemTo(more * NUM_ON_PAGE + 1);
        logger.info("Request params:" + crp);
        return crp;
    }


}
