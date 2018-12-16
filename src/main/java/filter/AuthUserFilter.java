package filter;



import context.JavaConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 25.10.2018
 * AuthFilter
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebFilter("/user")
public class AuthUserFilter implements Filter {

    private UserService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);
        usersService = context.getBean("userService", UserService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        Cookie cookies[] = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (usersService.isExistByCookie(cookie.getValue())) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
            response.sendRedirect("/login");
            return;
        }
        response.sendRedirect("/login");
        return;
    }

    @Override
    public void destroy() {

    }
}