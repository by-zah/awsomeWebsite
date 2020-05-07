package ua.khnu.servlet;

import org.springframework.context.ApplicationContext;
import ua.khnu.entity.Product;
import ua.khnu.listener.ConfigListener;
import ua.khnu.service.ProductService;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
    @Override
    public void init() {
        ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute(ConfigListener.CTX);
        productService= ctx.getBean(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonObjectBuilder rootBuilder=Json.createObjectBuilder();

        List<List<Product>> products=productService.getRandomForMain();
        for(List<Product> prod:products){
            JsonArrayBuilder arrayBuilder=Json.createArrayBuilder();
            for(Product product:prod){
                JsonObjectBuilder prodBuilder=Json.createObjectBuilder();
                JsonObject jsonProd=prodBuilder.add("id",product.getId())
                        .add("image",product.getPhoto())
                        .add("title",product.getTitle())
                        .add("price",product.getPrice())
                        .build();
                arrayBuilder.add(jsonProd);
            }
          rootBuilder.add(prod.get(0).getCategory(),arrayBuilder);
        }
        JsonObject rootJson=rootBuilder.build();
        resp.setContentType("application/json");
        System.out.println(rootJson);
        resp.getWriter().print(rootJson);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    public static void main(String[] args) {

    }
}
