package ua.khnu.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.khnu.db.transaction.TransactionHandler;
import ua.khnu.entuty.User;
import ua.khnu.exception.InitException;
import ua.khnu.reposetory.Repository;
import ua.khnu.reposetory.UserRepository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.Arrays;

@WebListener
public class ConfigListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        initLog4J(servletContext);
        initDB();
        Repository<User> userRepository = (Repository<User>) getTransactionProxy(UserRepository.class);
    }

    private void initDB(){
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/merchshop");
            TransactionHandler.setDs(ds);
        } catch (NamingException e) {
            LOG.error(e);
            throw new InitException("Can`t init db connection");
        }
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

    private <T> Repository<?> getTransactionProxy(Class<T> aClass){
        try {
            ClassLoader cl = aClass.getClassLoader();
            Repository<?> rep = (Repository<?>) aClass.getConstructor().newInstance();
            Object px =Proxy.newProxyInstance(cl,aClass.getInterfaces(),new TransactionHandler(rep));
            return (Repository)px ;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new InitException("Can`t init repository");
        }
    }
}
