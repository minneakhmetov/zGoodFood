package servlets;

import com.google.gson.Gson;
import context.JavaConfig;
import dto.ProductCart;
import models.Auth;
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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/loadCart")
public class LoadCartServlet extends HttpServlet {
    ProductService productService;
    UserService userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        Cookie cookie = null;
        for (Cookie candidate : request.getCookies()){
            if (candidate.getName().equals("auth"))
                cookie = candidate;
        }
        Auth auth = userService.getAuth(cookie.getValue());
        List<ProductCart> productCarts = productService.getCartByUser(auth.getUserId());
        PrintWriter writer = response.getWriter();
        writer.print(new Gson().toJson(productCarts));
    }

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);
        productService = context.getBean("productService", ProductService.class);
        userService = context.getBean("userService", UserService.class);
    }
}