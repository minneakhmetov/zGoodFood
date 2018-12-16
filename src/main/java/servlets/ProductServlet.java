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

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    ProductService productService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = productService.getProduct(Long.valueOf(request.getParameter("id")));
        request.setAttribute("product", product);
        request.getRequestDispatcher("ftl/product.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);
        productService = context.getBean("productService", ProductService.class);
    }
}