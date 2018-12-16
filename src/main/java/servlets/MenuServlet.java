package servlets;

import context.JavaConfig;
import models.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.ProductService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    ProductService productService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);
        productService = context.getBean("productService", ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<List<Product>> products = productService.getAllProducts();
        request.setAttribute("productFirst", products.get(0));
        request.setAttribute("productSecond", products.get(1));
        request.setAttribute("productThird", products.get(2));
        request.getRequestDispatcher("ftl/menu.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}