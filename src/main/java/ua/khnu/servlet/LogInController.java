package ua.khnu.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.khnu.entity.User;
import ua.khnu.exception.LoginException;
import ua.khnu.listener.ConfigListener;
import ua.khnu.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logIn")
public class LogInController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogInController.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute(ConfigListener.CTX);
        userService = ctx.getBean(UserService.class);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        try {
            user = userService.getValidUserByEmailAndPassword(request.getParameter("email"), request.getParameter("password"));
            request.getSession().setAttribute("currentUser", user);
            logger.info(request.getSession().getAttribute("currentUser"));
            response.sendRedirect("/index.jsp");
        } catch (LoginException e) {
            logger.debug(e);
            request.setAttribute("alert", e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
        }
    }


}
