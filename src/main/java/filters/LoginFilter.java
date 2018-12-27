package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
Проверка - авторизован ли пользователь
 */

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        Object currentUser = session.getAttribute("User");
        if (currentUser == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, servletResponse);
        } else {
            request.getRequestDispatcher("/WEB-INF/mainPageSamyGlavnyPage.jsp").forward(request, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }
}
