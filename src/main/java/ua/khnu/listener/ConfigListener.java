package ua.khnu.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.khnu.entity.User;
import ua.khnu.init.BeanInit;
import ua.khnu.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConfigListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("ua.khnu");
        ctx.register(BeanInit.class);
        ctx.refresh();
        ServletContext servletContext = sce.getServletContext();
        initLog4J(servletContext);
        UserService userService = ctx.getBean(UserService.class);
        User user = new User();
        user.setLogin("aaaa");
        user.setPassword("bbb");
        user.setEmail("mail");
        user.setNumber("000");
        user.setPaymentMethod("qqq");
        System.out.println(userService.addNewUser(user));
        user.setEmail("update");
        userService.updateUser(user);
    }

    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(
                    servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            log("Cannot configure Log4j");
        }
        log("Log4J initialization finished");
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}
