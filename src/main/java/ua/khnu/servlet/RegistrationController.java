package ua.khnu.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.khnu.entity.User;
import ua.khnu.exception.ValidationException;
import ua.khnu.listener.ConfigListener;
import ua.khnu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegistrationController.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute(ConfigListener.CTX);
        userService = ctx.getBean(UserService.class);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("alert");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(
                request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("phone"),
                Boolean.parseBoolean(request.getParameter("isMailingEnabled")));
        boolean isValid = false;
        logger.debug(user);
        try {
            isValid = userService.valid(request.getParameter("passwordRep"), user);
        } catch (ValidationException e) {
            request.getSession().setAttribute("alert", System.lineSeparator() + e.getMessage());
            request.getSession().setAttribute("userFromReg", user);
            response.sendRedirect("registration.jsp");
        }
        if (isValid) {
            userService.createNewUser(user);
            request.getRequestDispatcher("/logIn").forward(request, response);
        }
    }
}
