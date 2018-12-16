package servlets;

import context.JavaConfig;
import forms.LoginForm;
import forms.RegisterForm;
import models.Auth;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
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
        RegisterForm registerForm = RegisterForm.builder()
                .email(request.getParameter("email"))
                .name(request.getParameter("name"))
                .surname(request.getParameter("surname"))
                .password(request.getParameter("password"))
                .build();
        Auth auth = userService.register(registerForm);
        Cookie cookieAuth = new Cookie("auth", auth.getCookieValue());
        response.addCookie(cookieAuth);

        response.sendRedirect("/menu");
    }
}