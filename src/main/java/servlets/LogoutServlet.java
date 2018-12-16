package servlets;

import context.JavaConfig;
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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);
        userService = context.getBean("userService", UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = null;
        for (Cookie candidate : request.getCookies()){
            if (candidate.getName().equals("auth"))
                cookie = candidate;
        }
        userService.logout(cookie.getValue());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect("/main");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}