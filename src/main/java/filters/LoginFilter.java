package filters;

import model.Personal;
import model.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
Проверка - авторизован ли пользователь
 */
@WebFilter(urlPatterns = {"/index.jsp"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        Object currentUser = session.getAttribute("user");
        if (currentUser == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, servletResponse);
        } else {
            if (((Personal) currentUser).getRole() == Role.ADMIN) {
                request.getRequestDispatcher("/personals.jsp").forward(request, servletResponse);
            } else {
                request.getRequestDispatcher("/main.jsp").forward(request, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }

}