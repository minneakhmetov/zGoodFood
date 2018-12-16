package servlets;

import context.JavaConfig;
import dto.TwoProductCart;
import models.*;
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
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    ProductService productService;
    UserService userService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cookie cookie = null;
//        for (Cookie candidate : request.getCookies()){
//            if (candidate.getName().equals("auth"))
//                cookie = candidate;
//        }
//        Auth auth = userService.getAuth(cookie.getValue());
//        List<TwoProductCart> productCarts = productService.getCartByUser(auth.getUserId());
//        int total = 0;
//
//        for(int i = 0; i< productCarts.size(); i++){
//            total += productCarts.get(i).getFirstPrice();
//            total += productCarts.get(i).getSecondPrice();
//        }
//        request.setAttribute("products", productCarts);
//        request.setAttribute("total", total);
        request.getRequestDispatcher("ftl/cart.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookieValue = request.getParameter("cookieValue");
        Auth auth = userService.getAuth(cookieValue);
        productService.addToCart(auth.getUserId(), Long.valueOf(request.getParameter("productId")));
        response.sendError(200);
    }

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);
        productService = context.getBean("productService", ProductService.class);
        userService = context.getBean("userService", UserService.class);
    }
}