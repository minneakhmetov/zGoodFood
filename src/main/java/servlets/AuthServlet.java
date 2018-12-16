package servlets;

import com.google.gson.Gson;
import context.JavaConfig;
import dto.UserNameDto;
import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.UserService;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    UserService userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookieValue = request.getParameter("cookieValue");
        User user = userService.auth(cookieValue);
        if(user != null){
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(new Gson().toJson(new UserNameDto(user.getName() + " " + user.getSurname())));
        } else response.sendError(401);
    }

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);
        userService = context.getBean("userService", UserService.class);
    }
}