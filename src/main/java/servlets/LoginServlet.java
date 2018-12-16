package servlets;

import context.JavaConfig;
import forms.LoginForm;
import models.CookieUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.ProductService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);
        userService = context.getBean("userService", UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("ftl/login.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html; charset=UTF-8");
        LoginForm form = LoginForm.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();
        CookieUser cookieUser = userService.login(form);
        if (cookieUser != null) {
            Cookie auth = new Cookie("auth", cookieUser.getCookie());
            response.addCookie(auth);
            response.sendRedirect("/main");
        } else {
            response.sendRedirect("/login");
        }
    }
}