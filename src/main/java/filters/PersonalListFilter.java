package filters;

import model.Personal;
import model.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
только Админ может сюда заходить
 */

@WebFilter(urlPatterns = {"/personals.jsp"})
public class PersonalListFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        Object currentUser = session.getAttribute("user");
        Personal currUser = (Personal) currentUser;
        if (((Personal) currentUser).getRole() == Role.ADMIN) {
            request.getRequestDispatcher("/personals.jsp").forward(request, servletResponse);
        } else {
            request.getRequestDispatcher("/main.jsp").forward(request, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }

}
